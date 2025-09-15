package com.yihuobao.yhb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.yihuobao.yhb.entity.dto.MerchantDTO;
import com.yihuobao.yhb.entity.po.Merchant;
import com.yihuobao.yhb.mapper.MerchantMapper;
import com.yihuobao.yhb.service.MerchantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yihuobao.yhb.utils.MailMsg;
import com.yihuobao.yhb.utils.RandomUtils;
import com.yihuobao.yhb.utils.RegexUtils;
import com.yihuobao.yhb.utils.Result;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.yihuobao.yhb.utils.Constants.RedisConstants.*;
import static com.yihuobao.yhb.utils.Constants.RedisConstants.LOGIN_USER_KEY;
import static com.yihuobao.yhb.utils.Constants.RedisConstants.LOGIN_USER_TTL;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2025-07-17
 */
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantService {
    @Autowired
    private MerchantMapper merchantMapper;

    // 修复RedisTemplate注入
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MailMsg mailMsg;

    @Override
    public Result sendCode(String account){
        String code = RandomUtils.randomNumbers(6);
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + account, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);
        if (RegexUtils.isValidPhoneNumber(account)) {
            //发送验证码
            log.debug("手机号验证码发送成功");
        }
        if (RegexUtils.isValidEmail(account)) {
            boolean b = false;
            try {
                b = mailMsg.mail(account,code);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            if (b) {
                log.debug("邮箱验证码发送成功");
                return Result.success("","验证码："+code);
            }
            return Result.error("邮箱不正确或为空！");
        }
        log.debug("手机号/邮箱格式错误");
        return Result.error(400,"手机号/邮箱格式错误");
    }

    @Override
    public Result register(String account, String password, String code){
        Merchant merchant = new Merchant();

        merchant = merchantMapper.selectByRegistrationEmail(account);


        String redisCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + account);

        if(merchant == null){
            //注册
            merchant = new Merchant();

            if (redisCode == null || !code.equals(redisCode)) {
                return Result.error("验证码不匹配");
            }
            if(password==null){
                return Result.error("密码为空");
            }
            String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            if(encryptedPassword==null){
                return Result.error("加密密码为空");
            }

            merchant.setAccount(account);
            merchant.setMerchantPassword(encryptedPassword);
            merchant.setAccountStatus(1);
            merchant.setCreatedAt(LocalDateTime.now());
            merchant.setUpdatedAt(LocalDateTime.now());
            merchantMapper.insert(merchant);

            return createTokenAndLogin(merchant);
        }
        else{
            //登录
            return Result.error(400,"用户已存在，清登录");
        }
    }

    @Override
    public Result loginByPassword(String account, String password){
        Merchant merchant = new Merchant();

        merchant = merchantMapper.selectByRegistrationEmail(account);

        if (!BCrypt.checkpw(password, merchant.getMerchantPassword())) {
            return Result.error(403, "密码错误");
        }
        return createTokenAndLogin(merchant);
    }

    @Override
    public Result loginByCode(String account, String code){
        Merchant merchant = new Merchant();
        merchant = merchantMapper.selectByRegistrationEmail(account);

        String redisCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + account);

        if (redisCode == null || !code.equals(redisCode)) {
            return Result.error(402,"验证码不匹配");
        }

        return createTokenAndLogin(merchant);
    }

    private Result createTokenAndLogin(Merchant merchant) {
        String token = UUID.randomUUID().toString();
        MerchantDTO merchantDTO = BeanUtil.copyProperties(merchant, MerchantDTO.class);
        Map<String, Object> merchantMap = BeanUtil.beanToMap(merchantDTO, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        // 存储用户信息到Redis
        stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY + token, merchantMap);
        stringRedisTemplate.expire(LOGIN_USER_KEY + token, LOGIN_USER_TTL, TimeUnit.MINUTES);
        // 返回Token给前端
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("token", token);
        return Result.success("登录成功",resultMap);
    }
}
