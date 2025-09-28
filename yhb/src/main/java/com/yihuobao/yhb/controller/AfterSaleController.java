package com.yihuobao.yhb.controller;


import com.yihuobao.yhb.entity.dto.AfterSaleDTO;
import com.yihuobao.yhb.entity.vo.AfterSaleVO;
import com.yihuobao.yhb.service.AfterSaleService;
import com.yihuobao.yhb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  售后相关
 * </p>
 *
 * @author zzg
 * @since 2025-09--19
 */
@RestController
@RequestMapping("/afterSale")
public class AfterSaleController {
    @Autowired
    private AfterSaleService afterSaleService;

    @PostMapping("/apply")
    Result applyAfterSale (@ModelAttribute AfterSaleDTO afterSale) {
        return afterSaleService.applyAfterSale(
                afterSale.getUserId(),
                afterSale.getDishId(),
                afterSale.getDishName(),
                afterSale.getCompanyId(),
                afterSale.getAfterSaleReason(),
                afterSale.getProblemDescription(),
                afterSale.getPurchaseProof()
                );
    }
    /**
     * 根据用户ID查询售后信息
     */
//    @GetMapping("/check/{id}")
//    Result checkAfterSale(@RequestBody AfterSaleVO afterSale) {
//        return afterSaleService.checkAfterSale.ByUserId(afterSale.getUserId())
//    }



}
