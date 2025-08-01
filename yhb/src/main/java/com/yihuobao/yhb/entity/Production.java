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
    public class Production implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

  @TableField("traceCode")
  private String traceCode;

    @TableField("productionProcess")
    private String productionProcess;

    @TableField("processingOfficer")
    private String processingOfficer;

    @TableField("productCode")
    private String productCode;

    @TableField("productionLicense")
    private String productionLicense;

    @TableField("implementationStandard")
    private String implementationStandard;

    @TableField("productionWorkshopHygiene")
    private String productionWorkshopHygiene;

    @TableField("operatorHealthCertificate")
    private String operatorHealthCertificate;

    @TableField("equipmentCleaningRecord")
    private String equipmentCleaningRecord;

    @TableField("toolCleaningRecord")
    private String toolCleaningRecord;

    @TableField("testResults")
    private String testResults;

    @TableField("createdAt")
    private LocalDateTime createdAt;

    @TableField("updatedAt")
    private LocalDateTime updatedAt;


}
