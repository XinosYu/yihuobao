package com.yihuobao.yhb.entity;

import java.time.LocalDateTime;
import java.sql.Blob;
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
    public class Company implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    @TableField("companyName")
    private String companyName;

    @TableField("responsiblePerson")
    private String responsiblePerson;

    @TableField("businessLicense")
    private String businessLicense;

    @TableField("companyEmail")
    private String companyEmail;

    @TableField("socialCertificationNumber")
    private String socialCertificationNumber;

    @TableField("companyProfile")
    private String companyProfile;

    @TableField("companyPhoto")
    private Blob companyPhoto;

    @TableField("createdAt")
    private LocalDateTime createdAt;

    @TableField("updatedAt")
    private LocalDateTime updatedAt;

  @TableField("companyStatus")
  private int companyStatus;


}
