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
    public class Storage implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

  @TableField("traceCode")
  private String traceCode;

    @TableField("storageWarehouse")
    private String storageWarehouse;

    @TableField("entryDate")
    private LocalDateTime entryDate;

    @TableField("lightLevel")
    private Float lightLevel;

    private Float temperature;

    private Float humidity;

    private Float pm25;

    @TableField("oxygenContent")
    private Float oxygenContent;

    @TableField("packagingStatus")
    private String packagingStatus;

    @TableField("createdAt")
    private LocalDateTime createdAt;

    @TableField("updatedAt")
    private LocalDateTime updatedAt;


}
