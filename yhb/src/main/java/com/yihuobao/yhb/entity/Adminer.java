package com.yihuobao.yhb.entity;

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
 * @since 2025-07-20
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Adminer implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    @TableField("accoutNumber")
    private String accoutNumber;

    private String password;

    private String department;

    @TableField("contactNumber")
    private String contactNumber;

    private String email;

    @TableField("createdAt")
    private LocalDateTime createdAt;

    @TableField("updatedAt")
    private LocalDateTime updatedAt;


}
