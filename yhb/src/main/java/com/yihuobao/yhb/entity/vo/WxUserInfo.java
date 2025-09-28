package com.yihuobao.yhb.entity.vo;

import lombok.Data;

/**
 * 微信用户信息VO类
 * 用于接收微信解密后的用户信息
 */
@Data
public class WxUserInfo {
    private String openId;
    private String nickName;
    private Integer gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private Watermark watermark;
    
    @Data
    public static class Watermark {
        private Long timestamp;
        private String appid;
    }
}