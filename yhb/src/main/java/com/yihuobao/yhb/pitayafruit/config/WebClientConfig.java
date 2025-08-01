package com.yihuobao.yhb.pitayafruit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);

    // 使用@Qualifier确保名称明确
    @Bean("customWebClient")
    public WebClient webClient() {
        log.info("正在创建WebClient Bean...");

        // 添加调试信息
        ClassLoader classLoader = WebClient.class.getClassLoader();
        log.info("WebClient类加载器: {}", classLoader);

        WebClient client = WebClient.builder()
                .baseUrl("http://106.53.46.231:8000/v1/chat-messages") // 替换为实际API地址
                .build();

        log.info("WebClient Bean创建成功: {}", client);
        return client;
    }
}