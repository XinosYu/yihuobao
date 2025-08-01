package com.yihuobao.yhb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.yihuobao.yhb.entity.User;
import com.yihuobao.yhb.mapper.UserMapper;
import com.yihuobao.yhb.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yihuobao.yhb.utils.RandomUtils;
import com.yihuobao.yhb.utils.Result;
import com.yihuobao.yhb.utils.RegexUtils;
import com.yihuobao.yhb.utils.UserDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    // 修复RedisTemplate注入
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    // 添加Redis键常量
    @Autowired
    private static final String LOGIN_CODE_KEY = "login:code:";
    @Autowired
    private static final String LOGIN_USER_KEY = "login:token:";
    @Autowired
    private static final long LOGIN_CODE_TTL = 2L;
    @Autowired
    private static final long LOGIN_USER_TTL = 30L;

    @Override
    public Result sendCode(String phoneNumber){
        if (!RegexUtils.isValidPhoneNumber(phoneNumber)) {
            return Result.error("400","手机号格式有误！");
        }
        String code = RandomUtils.randomNumbers(6);
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phoneNumber, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);
        return Result.success("","code is "+code);
    }


    @Override
    public Result<User> register(String phoneNumber, String password, String code) {
        User user = new  User();

        if(!RegexUtils.isValidPhoneNumber(phoneNumber)){
            return Result.error("400","手机号格式错误");
        }
        if(userMapper.selectByPhoneNumber(phoneNumber)!=null){
            return  Result.error("400", "该手机号已注册，请登录");
        }
        if (phoneNumber == null || password == null) {
            return Result.error("401", "手机号或密码不能为空");
        }
        String redisCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phoneNumber);
        if (redisCode == null || !code.equals(redisCode)) {
            return Result.error("验证码与手机号不匹配");
        }

        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        user.setPassword(encryptedPassword);
        user.setPhoneNumber(phoneNumber);
        user.setAccountStatus(1);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.insert(user);

        return Result.success(user, "注册成功");
    }

    @Override
    public Result loginwithCode(String phoneNumber, String code) {
        User user = userMapper.selectByPhoneNumber(phoneNumber);
        if (user == null) {
            return Result.error("402", "用户不存在，请先注册");
        }

        String redisCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phoneNumber);
        if (redisCode == null || !code.equals(redisCode)) {
            return Result.error("405", "验证码错误或已过期");
        }

        return createTokenAndLogin(user);
    }

    @Override
    public Result loginwithPassword(String phoneNumber, String password) {
        User user = userMapper.selectByPhoneNumber(phoneNumber);
        if (user == null) {
            return Result.error("402", "用户不存在，请先注册");
        }
        if (!BCrypt.checkpw(password, user.getPassword())) {
            return Result.error("403", "密码错误");
        }

        return createTokenAndLogin(user);
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
        return Result.success(resultMap, "登录成功");
    }
}
