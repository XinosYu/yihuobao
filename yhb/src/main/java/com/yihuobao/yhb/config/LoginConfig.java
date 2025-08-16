package com.yihuobao.yhb.config;

import com.yihuobao.yhb.utils.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 拦截器配置类
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器
        registry.addInterceptor(new LoginInterceptor(stringRedisTemplate))
                // 拦截所有请求
                .addPathPatterns("/**")
                // 排除登录相关接口和静态资源
                .excludePathPatterns(
                        "/user/**",
                        "/login",
                        "/register",
                        "/registerAndLogin",
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/login.html",
                        "/register.html"
                );
    }
}
