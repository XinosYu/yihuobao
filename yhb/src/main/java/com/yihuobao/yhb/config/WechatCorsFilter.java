package com.yihuobao.yhb.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信小程序专用CORS过滤器
 * 优先级最高，确保所有请求都能正确处理CORS
 */
@Component
@Order(-100)  // 最高优先级，比HIGHEST_PRECEDENCE还高
public class WechatCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // 获取请求来源
        String origin = httpRequest.getHeader("Origin");
        
        // 设置CORS响应头 - 最宽松配置
        if (origin != null) {
            httpResponse.setHeader("Access-Control-Allow-Origin", origin);
        } else {
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        }
        
        // 允许所有方法
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD, PATCH");
        
        // 允许所有请求头
        String requestHeaders = httpRequest.getHeader("Access-Control-Request-Headers");
        if (requestHeaders != null) {
            httpResponse.setHeader("Access-Control-Allow-Headers", requestHeaders);
        } else {
            httpResponse.setHeader("Access-Control-Allow-Headers", "*");
        }
        
        // 暴露所有响应头
        httpResponse.setHeader("Access-Control-Expose-Headers", "*");
        
        // 允许携带凭证
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        
        // 预检请求缓存时间
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        
        // 特殊处理微信小程序
        String userAgent = httpRequest.getHeader("User-Agent");
        if (userAgent != null && userAgent.contains("MicroMessenger")) {
            // 微信小程序特殊处理
            httpResponse.setHeader("Access-Control-Allow-Origin", "https://servicewechat.com");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        }
        
        // 处理预检请求
        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("🚀 微信小程序CORS过滤器初始化完成！");
    }

    @Override
    public void destroy() {
        System.out.println("🛑 微信小程序CORS过滤器销毁！");
    }
}