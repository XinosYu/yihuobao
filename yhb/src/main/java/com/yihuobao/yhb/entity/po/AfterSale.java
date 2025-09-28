package com.yihuobao.yhb.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 售后实体类，映射数据库中的 after_sale 表
 * </p>
 *
 * @author zzg
 * @since 2025-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "after_sale")
public class AfterSale {

    //售后id
    @TableId(type = IdType.AUTO)
    @IsKey
    @IsAutoIncrement
    @Column(type = "int", comment = "售后ID", isNull = false)
    private int id;

    //用户
    @Column(name = "user_id", length = 20, comment = "用户ID", type = "varchar", isNull = true)
    private String userId;
    //菜品ID
    @Column(name = "dish_id", length = 20, comment = "菜品ID", type = "varchar", isNull = true)
    private String dishId;
    //菜品名称
    @Column(name = "dish_name", length = 20, comment = "菜品名称", type = "varchar", isNull = true)
    private String dishName;
    //厂家ID
    @Column(name = "company_id", length = 20, comment = "厂家ID", type = "varchar", isNull = true)
    private String companyId;
    //购买记录（图片url）
    @Column(name = "purchase_proof", length = 50, comment = "购买记录（图片url）", type = "varchar", isNull = true)
    private String purchaseProof;
    //售后原因
    @Column(name = "after_sale_reason", length = 50, comment = "售后原因", type = "varchar", isNull = true)
    private String AfterSaleReason;
    //问题描述
    @Column(name = "problem_description", length = 255, comment = "问题描述", type = "varchar", isNull = true)
    private String problemDescription;
    //是否解决（0-未解决/1-已解决）
    @Column(name = "is_solved", length = 1, comment = "是否解决（0-未解决/1-已解决）", type = "tinyint", isNull = true)
    private boolean isSolved;

}
