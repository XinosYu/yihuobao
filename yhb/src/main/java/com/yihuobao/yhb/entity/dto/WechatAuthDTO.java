package com.yihuobao.yhb.entity.dto;

import lombok.Data;

/**
 * 微信认证DTO
 * 用于接收前端传来的微信认证数据
 */
@Data
public class WechatAuthDTO {
    private String encryptedData;
    private String sessionId;
    private String iv;
}