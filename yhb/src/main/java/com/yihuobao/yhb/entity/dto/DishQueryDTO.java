package com.yihuobao.yhb.entity.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 菜品查询DTO，用于接收前端传递的查询条件和分页参数
 */
@Data
public class DishQueryDTO {
    // 模糊查询
    private String name;
    private String manufacturer;

    // 精确查询
    private String traceCode;

    // 生产日期起始时间（大于等于）
    private LocalDateTime minProductionDate;

    // 生产日期结束时间（小于等于）
    private LocalDateTime maxProductionDate;

    // 分页参数：页码，默认第1页
    private Integer pageNum = 1;

    // 分页参数：每页条数，默认10条
    private Integer pageSize = 10;
}
