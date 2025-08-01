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
    public class Dish implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String name;

    @TableField("traceCode")
    private String traceCode;

    @TableField("packagingMaterial")
    private String packagingMaterial;

    @TableField("ingredientList")
    private String ingredientList;

    @TableField("productionDate")
    private LocalDateTime productionDate;

    @TableField("storageCondition")
    private String storageCondition;

    @TableField("shelfLife")
    private String shelfLife;

    @TableField("usageMethod")
    private String usageMethod;

    @TableField("bestUseTime")
    private String bestUseTime;

    @TableField("allergenWarning")
    private String allergenWarning;

    @TableField("rawMaterialList")
    private String rawMaterialList;

    @TableField("createdAt")
    private LocalDateTime createdAt;

    @TableField("updatedAt")
    private LocalDateTime updatedAt;


}
