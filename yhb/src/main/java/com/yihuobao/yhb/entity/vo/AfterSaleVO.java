package com.yihuobao.yhb.entity.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AfterSaleVO {
    private Integer id;
    private String userId;
    private String dishId;
    private String dishName;
    private String companyId;
    private MultipartFile purchaseProof;
    private String AfterSaleReason;
    private String problemDescription;
}
