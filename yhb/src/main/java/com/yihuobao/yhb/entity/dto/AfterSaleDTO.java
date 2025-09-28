package com.yihuobao.yhb.entity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AfterSaleDTO {
    private String userId;
    private String dishId;
    private String dishName;
    private String companyId;
    private MultipartFile purchaseProof;
    private String AfterSaleReason;
    private String problemDescription;
}
