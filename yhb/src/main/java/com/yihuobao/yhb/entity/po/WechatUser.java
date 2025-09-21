package com.yihuobao.yhb.entity.po;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 微信用户表
 * </p>
 *
 * @author admin
 * @since 2025-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WechatUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @TableField("openId")
    private String openId;

    @TableField("unionId")
    private String unionId;

    @TableField("nickName")
    private String nickName;

    private Integer gender;

    private String country;

    private String province;

    private String city;

    @TableField("phoneNumber")
    private String phoneNumber;

    @TableField("userEmail")
    private String userEmail;

    @TableField("accountStatus")
    private Integer accountStatus;

    @TableField("createdAt")
    private LocalDateTime createdAt;

    @TableField("updatedAt")
    private LocalDateTime updatedAt;

    @TableField("ipAddress")
    private String ipAddress;

    // 微信解密需要的临时字段（不存储到数据库）
    @TableField(exist = false)
    private String encryptedData;
    
    @TableField(exist = false)
    private String sessionId;
    
    @TableField(exist = false)
    private String iv;

}
