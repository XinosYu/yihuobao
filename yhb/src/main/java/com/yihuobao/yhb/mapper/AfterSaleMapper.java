package com.yihuobao.yhb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yihuobao.yhb.entity.po.AfterSale;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzg
 * @since 2025-09-19
 */
@Mapper
public interface AfterSaleMapper extends BaseMapper<AfterSale> {
//    @Insert("INSERT INTO AFTER_SALE("+"userId, dishId, dishName, companyId, purchaseProof, AfterSaleReason, ProblemDescription, isSolved"+
//            ") VALUES ("+"#{userId}, #{dishId}, #{dishName}, #{companyId}, #{purchaseProof}, #{AfterSaleReason}, #{ProblemDescription}, #{isSolved})")
//    AfterSale insert(AfterSale afterSale);
}
