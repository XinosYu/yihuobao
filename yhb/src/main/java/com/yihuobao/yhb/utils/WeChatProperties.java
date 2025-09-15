package com.yihuobao.yhb.utils;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "xxx.wechat")
@Data
public class WeChatProperties {
    private String appid; //小程序的appid
    private String secret; //小程序的秘钥
    //其它微信支付相关内容...
} 