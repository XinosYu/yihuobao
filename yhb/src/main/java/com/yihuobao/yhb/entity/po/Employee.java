package com.yihuobao.yhb.entity.po;

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
    public class Employee implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String name;

    private String password;

    private String role;

    private String department;

    private String position;

    @TableField("healthCertificateNumber")
    private String healthCertificateNumber;

    @TableField("healthCheckDate")
    private LocalDate healthCheckDate;

    @TableField("healthStatus")
    private String healthStatus;

    @TableField("infectiousDiseaseInfo")
    private String infectiousDiseaseInfo;

    @TableField("contactNumber")
    private String contactNumber;

    private String email;

    @TableField("createdAt")
    private LocalDateTime createdAt;

    @TableField("updatedAt")
    private LocalDateTime updatedAt;

  @TableField("accountStatus")
  private int accountStatus;
}
