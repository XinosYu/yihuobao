package com.yihuobao.yhb.entity.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DishDTO {
    private int id;
    private String traceCode;
    private String name;
    private String description;
    private String processingTechnology;
    private String packagingMaterial;
    private String ingredientList;
    private String allergenWarning;
    private LocalDateTime productionDate;
    private String shelfLife;
    private String storageCondition;
    private String productionAddress;
    private String manufacturer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

