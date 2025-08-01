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
 * @since 2025-07-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Transport implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

  @TableField("traceCode")
  private String traceCode;

    private String transporter;

    @TableField("transportOfficer")
    private String transportOfficer;

    private String origin;

    private String destination;

    @TableField("transportBatchNumber")
    private String transportBatchNumber;

    @TableField("transportLicense")
    private String transportLicense;

    @TableField("transportGps")
    private String transportGps;

    @TableField("createdAt")
    private LocalDateTime createdAt;

    @TableField("updatedAt")
    private LocalDateTime updatedAt;


}
