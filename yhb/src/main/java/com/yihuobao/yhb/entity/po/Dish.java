package com.yihuobao.yhb.entity.po;

import java.time.LocalDateTime;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜品实体类，映射数据库中的 dish 表
 * </p>
 *
 * @author zzg
 * @since 2025-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "dish")
public class Dish implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(type = IdType.AUTO)
  @IsKey
  @IsAutoIncrement
  @Column(type = "int", comment = "菜品ID", isNull = false)
  private int id;

  // 追溯码
  @Column(name = "trace_code", type = "varchar", length = 50, comment = "追溯码", isNull = false)
  private String traceCode;

  // 商品名称
  @Column(name = "name", type = "varchar", length = 100, comment = "菜品名称", isNull = false)
  private String name;

  // 商品描述
  @Column(name = "description", type = "text", comment = "商品描述", isNull = false)
  private String description;

  // 包装材质
  @Column(name = "processing_technology", type = "varchar", length = 100, comment = "处理工艺", isNull = true)
  private String processingTechnology;

  // 包装材质
  @Column(name = "packaging_material", type = "varchar", length = 100, comment = "包装材料", isNull = false)
  private String packagingMaterial;

  // 配料表
  @Column(name = "ingredient_list", type = "text", comment = "配料表", isNull = false)
  private String ingredientList;

  // 过敏原警告
  @Column(name = "allergen_warning", type = "varchar", length = 200, comment = "过敏原警告", isNull = true)
  private String allergenWarning;

  // 生产日期
  @Column(name = "production_date", type = "datetime", comment = "生产日期", isNull = false)
  private LocalDateTime productionDate;

  // 保质期
  @Column(name = "shelf_life", type = "varchar", length = 50, comment = "保质期", isNull = false)
  private String shelfLife;

  // 储存条件
  @Column(name = "storage_condition", type = "varchar", length = 200, comment = "储存条件", isNull = false)
  private String storageCondition;

  // 使用方法
  @Column(name = "production_address", type = "varchar", length = 100, comment = "生产地址", isNull = false)
  private String productionAddress;

  // 创建时间
  @Column(name = "created_at", type = "datetime", comment = "创建时间", isNull = false)
  private LocalDateTime createdAt;

  // 更新时间
  @Column(name = "updated_at", type = "datetime", comment = "更新时间", isNull = false)
  private LocalDateTime updatedAt;

  // 生产厂商
  @Column(name = "manufacturer", type = "varchar", comment = "生产厂商", isNull = false)
  private String manufacturer;

}

