package com.yihuobao.yhb.entity.po;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import com.yihuobao.yhb.utils.RegexUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户实体类，映射数据库中的 user 表
 * </p>
 *
 * @author zzg
 * @since 2025-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 用户ID（主键）
   */
  @TableId(type = IdType.AUTO)
  @IsKey
  @IsAutoIncrement
  @Column(type = "int", comment = "用户ID", isNull = false)
  private int id;

  //手机号
  @Column(name = "phone_number", length = 20, comment = "手机号", type = "varchar", isNull = true)
  private String phoneNumber;

  /**
   * 邮箱地址
   */
  @Column(name = "email", length = 20, comment = "邮箱", type = "varchar", isNull = true)
  private String email;

  //密码
  @Column(name = "password", length = 30, comment = "密码", type = "varchar", isNull = false)
  private String password;

  //创建时间
  @Column(name = "create_time", length = 20, comment = "创建时间", type = "datetime", isNull = false)
  private LocalDateTime createTime;

  //更新时间
  @Column(name = "update_time", length = 20, comment = "更新时间",  type = "datetime", isNull = false)
  private LocalDateTime updateTime;

  //ip地址
  @Column(name = "ip_address", length = 20, comment = "IP地址", type = "varchar", isNull = true)
  private String ipAddress;

  //账号状态（1-正常，0-禁用）
  @Column(name = "account_status", comment = "账号状态",  type = "int", isNull = false)
  private boolean accountStatus;

  /**
   * 统一设置账号（支持手机号或邮箱）
   * 根据输入内容自动判断是手机号还是邮箱，分别赋值
   * @param account 账号（手机号或邮箱）
   */
  public void setAccount(String account) {
    if(RegexUtils.isValidPhoneNumber(account)){
      this.phoneNumber = account;  // 符合手机号格式则赋值给phoneNumber
    }
    else if(RegexUtils.isValidEmail(account)){
      this.email = account;  // 符合邮箱格式则赋值给email
    }
  }
}
