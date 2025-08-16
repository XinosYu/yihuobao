package com.yihuobao.yhb.utils;

import cn.hutool.core.bean.BeanUtil;
import com.yihuobao.yhb.dto.UserDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.yihuobao.yhb.utils.Constants.RedisConstants.*;

public class LoginInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    // 通过构造器注入Redis模板
    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取请求头中的token
        String token = request.getHeader("authorization");
//        if (token == null || token.isEmpty()) {
//            // 没有token，重定向到登录页
//            response.sendRedirect("/login.html");
//            return false;
//        }

        // 2. 从Redis中获取用户信息
        String key = LOGIN_USER_KEY + token;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
//        if (userMap.isEmpty()) {
//            // Redis中没有用户信息，重定向到登录页
//            response.sendRedirect("/login.html");
//            return false;
//        }

        // 3. 将查询到的Hash数据转为UserDTO对象
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);

        // 4. 保存用户信息到ThreadLocal
        UserHolder.saveUser(userDTO);

        // 5. 刷新token有效期
        stringRedisTemplate.expire(key, LOGIN_USER_TTL, TimeUnit.MINUTES);

        // 6. 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除ThreadLocal中的用户信息，防止内存泄漏
        UserHolder.removeUser();
    }
}
