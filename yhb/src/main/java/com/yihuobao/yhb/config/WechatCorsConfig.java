package com.yihuobao.yhb.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 微信小程序专用CORS配置
 * 提供最强力的跨域支持
 */
@Configuration
public class WechatCorsConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // 允许所有来源（最宽松配置）
        config.addAllowedOriginPattern("*");
        
        // 允许所有请求头
        config.setAllowedHeaders(Arrays.asList("*"));
        
        // 允许所有方法
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH"));
        
        // 暴露所有响应头
        config.setExposedHeaders(Arrays.asList("*"));
        
        // 允许携带凭证
        config.setAllowCredentials(true);
        
        // 预检请求缓存时间
        config.setMaxAge(3600L);
        
        // 配置路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        // 创建CORS过滤器
        CorsFilter corsFilter = new CorsFilter(source);
        
        // 注册过滤器，设置最高优先级
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(corsFilter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        bean.setName("wechatCorsFilter");
        
        return bean;
    }
}