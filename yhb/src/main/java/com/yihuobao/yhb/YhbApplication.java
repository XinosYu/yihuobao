package com.yihuobao.yhb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


import org.springframework.context.annotation.ComponentScan;

@MapperScan({"com.gitee.sunchenbin.mybatis.actable.dao.*", "com.yihuobao.yhb.mapper"})
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*", "com.yihuobao"})
@SpringBootApplication
public class YhbApplication {
	public static  void main(String[] args) {
		SpringApplication.run(YhbApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}


