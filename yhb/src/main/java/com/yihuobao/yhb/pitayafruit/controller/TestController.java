package com.yihuobao.yhb.pitayafruit.controller;

import com.yihuobao.yhb.pitayafruit.resp.BlockResponse;
import com.yihuobao.yhb.pitayafruit.resp.StreamResponse;
import com.yihuobao.yhb.pitayafruit.service.DifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private String testKey = "app-xnhpDbVNJcsSFh6XPEBBXgne";

    private final DifyService difyService;

    @GetMapping("/block/audit")
    public Boolean test1() {
        String query = "{\n" +
                "    \"dish\": {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"宫保鸡丁预制菜\",\n" +
                "        \"traceCode\": \"TC0001\",\n" +
                "        \"packagingMaterial\": \"塑料盒\",\n" +
                "        \"ingredientList\": \"鸡肉、花生米、青椒、红椒、葱、姜、蒜等\",\n" +
                "        \"productionDate\": \"2024-01-01\",\n" +
                "        \"storageCondition\": \"冷藏，温度 2-8℃\",\n" +
                "        \"usageMethod\": \"解冻后，倒入锅中加热翻炒 5 分钟即可\",\n" +
                "        \"bestUseTime\": \"10 天\",\n" +
                "        \"allergenWarning\": \"含有花生，对花生过敏者慎食\",\n" +
                "        \"rawMaterialList\": \"鸡肉、花生米、蔬菜等\",\n" +
                "        \"createdAt\": \"2024-01-01 10:00:00\",\n" +
                "        \"updatedAt\": \"2024-01-01 10:00:00\"\n" +
                "    },\n" +
                "    \"production\": {\n" +
                "        \"id\": 1,\n" +
                "        \"dishId\": 1,\n" +
                "        \"productionProcess\": \"先将鸡肉腌制，再与蔬菜一起炒制，最后加入调料\",\n" +
                "        \"processingOfficer\": \"张三\",\n" +
                "        \"productCode\": \"PC0001\",\n" +
                "        \"productionLicense\": \"PL0001\",\n" +
                "        \"implementationStandard\": \"GB/T XXXX\",\n" +
                "        \"testResults\": \"各项指标合格\",\n" +
                "        \"createdAt\": \"2024-01-01 10:10:00\",\n" +
                "        \"updatedAt\": \"2024-01-01 10:10:00\"\n" +
                "    },\n" +
                "    \"storage\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"dishId\": 1,\n" +
                "            \"storageWarehouse\": \"仓库 A\",\n" +
                "            \"entryDate\": \"2024-01-02\",\n" +
                "            \"lightLevel\": 10.0,\n" +
                "            \"temperature\": 5.0,\n" +
                "            \"humidity\": 60.0,\n" +
                "            \"pm25\": 15.0,\n" +
                "            \"oxygenContent\": 20.0,\n" +
                "            \"packagingStatus\": \"包装完好\",\n" +
                "            \"createdAt\": \"2024-01-02 11:00:00\",\n" +
                "            \"updatedAt\": \"2024-01-02 11:00:00\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"transport\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"dishId\": 1,\n" +
                "            \"transporter\": \"运输公司 X\",\n" +
                "            \"transportOfficer\": \"李四\",\n" +
                "            \"origin\": \"工厂地址\",\n" +
                "            \"destination\": \"仓库 A\",\n" +
                "            \"transportBatchNumber\": \"TB0001\",\n" +
                "            \"transportLicense\": \"TL0001\",\n" +
                "            \"transportGps\": \"轨迹数据...\",\n" +
                "            \"createdAt\": \"2024-01-02 12:00:00\",\n" +
                "            \"updatedAt\": \"2024-01-02 12:00:00\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"supplier\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"rawMaterialSupplier\": \"供应商甲\",\n" +
                "            \"materialOfficer\": \"王五\",\n" +
                "            \"productTestCertificate\": \"PTC0001\",\n" +
                "            \"createdAt\": \"2024-01-01 09:00:00\",\n" +
                "            \"updatedAt\": \"2024-01-01 09:00:00\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"company\": {\n" +
                "        \"id\": 1,\n" +
                "        \"companyName\": \"美食预制菜公司\",\n" +
                "        \"responsiblePerson\": \"赵六\",\n" +
                "        \"businessLicense\": \"BL0001\",\n" +
                "        \"companyEmail\": \"company@example.com\",\n" +
                "        \"socialCertificationNumber\": \"SCN0001\",\n" +
                "        \"companyProfile\": \"专注于预制菜生产的公司\",\n" +
                "        \"companyPhoto\": null,\n" +
                "        \"createdAt\": \"2024-01-01 08:00:00\",\n" +
                "        \"updatedAt\": \"2024-01-01 08:00:00\"\n" +
                "    },\n" +
                "    \"recall\": {\n" +
                "        \"id\": 1,\n" +
                "        \"recallRecord\": \"无\",\n" +
                "        \"recallHistory\": \"无\",\n" +
                "        \"recallReason\": \"无\",\n" +
                "        \"recallOfficer\": \"\",\n" +
                "        \"createdAt\": \"2024-01-01 13:00:00\",\n" +
                "        \"updatedAt\": \"2024-01-01 13:00:00\"\n" +
                "    },\n" +
                "    \"handover\": {\n" +
                "        \"id\": 1,\n" +
                "        \"handoverTime\": \"2024-01-02 14:00:00\",\n" +
                "        \"confirmationRecord\": \"交接正常\",\n" +
                "        \"acceptanceResult\": \"合格\",\n" +
                "        \"createdAt\": \"2024-01-02 14:00:00\",\n" +
                "        \"updatedAt\": \"2024-01-02 14:00:00\"\n" +
                "    }\n" +
                "}";
        String userid = "admin";
        Boolean aboolean = difyService.blockingMessageresult(query, userid, testKey);
        return aboolean;
    }

    @GetMapping("/stream")
    public Flux<StreamResponse> test2() {
        String query = "盐100g";
        String userid = "admin";
        return difyService.streamingMessage(query, userid, testKey);
    }

}
