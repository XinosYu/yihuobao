package com.yihuobao.yhb.entity.vo;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DishVO {
    private Integer id;
    private String traceCode;
    private String name;
    private String description;
    private String processingTechnology;
    private String packagingMaterial;
    private String ingredientList;
    private String allergenWarning;
    private String shelfLife;
    private String storageCondition;
    private String productionAddress;
    private String manufacturer;

    // 前端可能需要格式化的日期字符串
    private String productionDateStr;



    // 只返回需要展示的时间信息
    private String createdAtStr;

    // 格式化日期的方法
    public void setProductionDate(LocalDateTime productionDate) {
        if (productionDate != null) {
            this.productionDateStr = productionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        if (createdAt != null) {
            this.createdAtStr = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
}
