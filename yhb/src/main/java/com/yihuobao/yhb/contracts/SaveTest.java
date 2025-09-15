package com.yihuobao.yhb.contracts;


import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple2;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import static com.yihuobao.yhb.contracts.Tools.bytesToHex;
import static com.yihuobao.yhb.contracts.Tools.generateSHA256Hash;

/**
 * SaveAddress 合约测试类
 */
public class SaveTest {

    public static void main(String[] args) {
        try {
            System.out.println("开始 FISCO BCOS 合约测试...");

            // 初始化客户端
            FiscoBcosClient.initialize();

            // 获取合约实例
            SaveAddress contract = FiscoBcosClient.getSaveAddress_contract();

            // 获取当前账户地址
            String ownerAddress = FiscoBcosClient.getCryptoKeyPair().getAddress();

            System.out.println("测试使用的账户地址: " + ownerAddress);
            System.out.println("合约地址: " + contract.getContractAddress());

            // 测试1: 检查账户权限
            testAccountPermission(contract, ownerAddress);

            // 测试2: 提交和验证哈希
            testSubmitAndVerifyHash(contract);

            // 测试3: 批量提交哈希
            testBatchSubmit(contract);

            System.out.println("全部测试完成！");

        } catch (Exception e) {
            System.err.println("测试失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 这里主动调用退出，确保所有资源被释放
            System.out.println("测试结束，正在退出...");
            // System.exit(0); // 如果依然有线程阻塞，可能需要取消注释此行
        }
    }

    /**
     * 测试1: 检查账户权限
     */
    private static void testAccountPermission(SaveAddress contract, String ownerAddress) throws Exception {
        System.out.println("\n========== 测试1: 检查账户权限 ==========");

        boolean isWhitelisted = contract.isWhitelisted(ownerAddress);
        System.out.println("当前账户是否在白名单中: " + isWhitelisted);

        if (isWhitelisted) {
            System.out.println("测试1通过: 当前账户在白名单中");
        } else {
            System.out.println("警告: 当前账户不在白名单中，后续操作可能失败");
        }
    }

    /**
     * 测试2: 提交和验证哈希
     */
    private static void testSubmitAndVerifyHash(SaveAddress contract) throws Exception {
        System.out.println("\n========== 测试2: 提交和验证哈希 ==========");

        // 创建测试数据
        String testTraceCode = "TEST_TRACE_" + System.currentTimeMillis();
        String testData = "测试数据内容" + System.currentTimeMillis();
        byte[] dataHash = generateSHA256Hash(testData);

        System.out.println("测试追溯码: " + testTraceCode);
        System.out.println("测试数据: " + testData);
        System.out.println("数据哈希: " + bytesToHex(dataHash));

        // 提交哈希
        System.out.println("正在提交哈希...");
        TransactionReceipt receipt = contract.submitHash(testTraceCode, dataHash);

        System.out.println("交易结果状态: " + receipt.getStatus());
        System.out.println("交易哈希: " + receipt.getTransactionHash());

        if (receipt.getStatus() != 0) {
            System.out.println("警告: 交易未成功，状态码: " + receipt.getStatus());
            return;
        }

        // 获取存储的记录
        System.out.println("获取存储的记录...");
        Tuple4<byte[], BigInteger, String, BigInteger> record = contract.getRecord(testTraceCode);

        System.out.println("存储的哈希: " + bytesToHex(record.getValue1()));
        System.out.println("时间戳: " + record.getValue2());
        System.out.println("提交者: " + record.getValue3());
        System.out.println("更新计数: " + record.getValue4());

        // 验证哈希完整性
        System.out.println("验证哈希完整性...");
        Tuple2<Boolean, BigInteger> verifyResult = contract.verifyIntegrity(testTraceCode, dataHash);

        System.out.println("验证结果: " + verifyResult.getValue1());
        System.out.println("记录时间戳: " + verifyResult.getValue2());

        if (verifyResult.getValue1()) {
            System.out.println("测试2通过: 哈希提交和验证成功");
        } else {
            System.out.println("测试2失败: 哈希验证失败");
        }
    }

    /**
     * 测试3: 批量提交哈希
     */
    private static void testBatchSubmit(SaveAddress contract) throws Exception {
        System.out.println("\n========== 测试3: 批量提交哈希 ==========");

        // 创建批量测试数据
        int batchSize = 2; // 使用小批量避免潜在问题
        List<String> traceCodes = new ArrayList<>();
        List<byte[]> dataHashes = new ArrayList<>();

        for (int i = 0; i < batchSize; i++) {
            String traceCode = "BATCH_TRACE_" + i + "_" + System.currentTimeMillis();
            String data = "批量测试数据 " + i + " - " + System.currentTimeMillis();
            byte[] dataHash = generateSHA256Hash(data);

            traceCodes.add(traceCode);
            dataHashes.add(dataHash);

            System.out.println("批量项 #" + i + ":");
            System.out.println("  追溯码: " + traceCode);
            System.out.println("  数据哈希: " + bytesToHex(dataHash));
        }

        // 批量提交哈希
        System.out.println("正在批量提交哈希...");
        TransactionReceipt receipt = contract.batchSubmit(traceCodes, dataHashes);

        System.out.println("交易结果状态: " + receipt.getStatus());
        System.out.println("交易哈希: " + receipt.getTransactionHash());

        if (receipt.getStatus() != 0) {
            System.out.println("警告: 交易未成功，状态码: " + receipt.getStatus());
            return;
        }

        // 验证每个批量提交的项
        boolean allValid = true;
        for (int i = 0; i < batchSize; i++) {
            System.out.println("\n验证批量项 #" + i + "...");

            Tuple4<byte[], BigInteger, String, BigInteger> record = contract.getRecord(traceCodes.get(i));

            System.out.println("存储的哈希: " + bytesToHex(record.getValue1()));
            System.out.println("提交者: " + record.getValue3());

            Tuple2<Boolean, BigInteger> verifyResult = contract.verifyIntegrity(traceCodes.get(i), dataHashes.get(i));

            System.out.println("验证结果: " + verifyResult.getValue1());

            if (!verifyResult.getValue1()) {
                allValid = false;
                System.out.println("批量项 #" + i + " 验证失败");
            }
        }

        if (allValid) {
            System.out.println("\n测试3通过: 所有批量项验证成功");
        } else {
            System.out.println("\n测试3失败: 部分批量项验证失败");
        }
    }

}
