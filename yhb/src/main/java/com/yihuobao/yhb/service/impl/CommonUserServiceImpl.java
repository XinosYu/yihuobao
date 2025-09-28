package com.yihuobao.yhb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.yihuobao.yhb.entity.dto.UserDTO;
import com.yihuobao.yhb.entity.po.User;
import com.yihuobao.yhb.mapper.UserMapper;
import com.yihuobao.yhb.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yihuobao.yhb.utils.*;
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

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    private final String WECHAT_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 修复RedisTemplate注入
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MailMsg mailMsg;
    private int id;

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
        User user = new User();
        //判断账号类型
        if(RegexUtils.isValidPhoneNumber(account)){
            user = userMapper.selectByPhoneNumber(account);
        }
        if(RegexUtils.isValidEmail(account)){
            user = userMapper.selectByUserEmail(account);
        }
        else{
            return Result.error(400,"账号格式错误");
        }

        String redisCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + account);

        if(user == null){
            //注册
            user = new User();

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
            if(RegexUtils.isValidPhoneNumber(account)){
                user.setPhoneNumber(account);
            }
            if(RegexUtils.isValidEmail(account)){
                user.setEmail(account);
            }
            user.setPassword(encryptedPassword);
            user.setAccountStatus(true);
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userMapper.insert(user);

            return createTokenAndLogin(user);
        }
        else{
            //登录
            return Result.error(400,"用户已存在，清登录");
        }
    }

    @Override
    public Result loginByPassword(String account, String password){
        User user = new User();
        if(RegexUtils.isValidPhoneNumber(account)){
            user = userMapper.selectByPhoneNumber(account);
        }
        if(RegexUtils.isValidEmail(account)){
            user = userMapper.selectByUserEmail(account);
        }

        if (!BCrypt.checkpw(password, user.getPassword())) {
            return Result.error(403, "密码错误");
        }
        return createTokenAndLogin(user);
    }

    @Override
    public Result loginByCode(String account, String code){
        User user = new User();
        if(RegexUtils.isValidPhoneNumber(account)){
            user = userMapper.selectByPhoneNumber(account);
        }
        if(RegexUtils.isValidEmail(account)){
            user = userMapper.selectByUserEmail(account);
        }

        String redisCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + account);

        if (redisCode == null || !code.equals(redisCode)) {
            return Result.error(402,"验证码不匹配");
        }

        return createTokenAndLogin(user);
    }


    @Override
    public Result loginByWeChat(String code) {
//        String openId = getOpenId(code);
//        // 查询用户是否已存在
//        Long userId = userMapper.getUserByOpenId(openId);
//        if (userId == null) {
//            User user = User.builder().openId(openId).build();
//            usersMapper.insertUsers(user);
//            String token = JwtTokenUtil.generateTokenWithUserId(user.getId());
//            return Result.success("登录成功", token);
//        }
//        return Result.success("登录成功", JwtTokenUtil.generateTokenWithUserId(userId));
//    }

//    public String getOpenId(String code) {
//        // 封装请求参数
//        HashMap<String, String> map = new HashMap<>();
//        map.put("appid", weChatProperties.getAppId());
//        map.put("secret", weChatProperties.getSecret());
//        map.put("js_code", code);
//        map.put("grant_type", "authorization_code");
//        // 使用封装好的 HttpClientUtil 从微信后台请求
//        String json = HttpClientUtil.doGet(WECHAT_LOGIN_URL, map);
//        // 将获取过来的数据解析出来
//        JSONObject jsonObject = JSON.parseObject(json);
//        // 获取openId
//        log.info("jsonObject:{}", jsonObject);
//        return jsonObject.getString("openid");
        return null;
    }
    @Override
    public Result editUser(int id){
        User user = userMapper.selectById(id);
        return null;
    }
    @Override
    public Result deleteUser(int id){
        User user = userMapper.selectById(id);
        return null;
    }

    @Override
    public User loadUserByUserName(String userName){
        return userMapper.selectByUserName(userName);
    }


    private Result createTokenAndLogin(User user) {
        String token = UUID.randomUUID().toString();
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        // 存储用户信息到Redis
        stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY + token, userMap);
        stringRedisTemplate.expire(LOGIN_USER_KEY + token, LOGIN_USER_TTL, TimeUnit.MINUTES);
        // 返回Token给前端
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("token", token);
        return Result.success("登录成功",resultMap);
    }

}
