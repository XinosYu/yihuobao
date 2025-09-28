package com.yihuobao.yhb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yihuobao.yhb.entity.po.AfterSale;
import com.yihuobao.yhb.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  售后-服务层
 * </p>
 * @author zzg
 * @since 2025-09-19
 */
public interface AfterSaleService extends IService<AfterSale> {

    Result applyAfterSale(String userID, String dishId, String dishName, String companyID, String AfterSaleReason, String problemDescription, MultipartFile purchaseProofImage);

}
