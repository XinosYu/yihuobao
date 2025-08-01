package com.yihuobao.yhb.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2025-07-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Merchant implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    @TableField("enterpriseName")
    private String enterpriseName;

    @TableField("unifiedSocialCreditCode")
    private String unifiedSocialCreditCode;

    @TableField("businessTerm")
    private String businessTerm;

    @TableField("legalRepresentativeName")
    private String legalRepresentativeName;

    @TableField("legalRepresentativeIdNumber")
    private String legalRepresentativeIdNumber;

    @TableField("legalRepresentativeIdValidity")
    private LocalDate legalRepresentativeIdValidity;

    @TableField("legalRepresentativePhone")
    private String legalRepresentativePhone;

    @TableField("merchantPassword")
    private String merchantPassword;

    @TableField("registrationEmail")
    private String registrationEmail;

    @TableField("passwordHint")
    private String passwordHint;

    @TableField("passwordHintAnswer")
    private String passwordHintAnswer;

    @TableField("merchantVerificationCode")
    private String merchantVerificationCode;

    @TableField("createdAt")
    private LocalDateTime createdAt;

    @TableField("updatedAt")
    private LocalDateTime updatedAt;

    @TableField("ipAddress")
    private String ipAddress;

    @TableField("registerAddress")
    private String registerAddress;

  @TableField("accountStatus")
  private int accountStatus;



}
