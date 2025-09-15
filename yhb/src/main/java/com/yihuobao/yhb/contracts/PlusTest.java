package com.yihuobao.yhb.contracts;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import static com.yihuobao.yhb.contracts.Tools.bytesToHex;
import static com.yihuobao.yhb.contracts.Tools.generateSHA256Hash;

public class PlusTest {
    public static void main(String[] args) {
            try {
                System.out.println("开始 FISCO BCOS 合约测试...");

                // 初始化客户端
                FiscoBcosClient.initialize();

                // 获取合约实例
                PlusAddress contract = FiscoBcosClient.getPlusAddress_contract();

                // 获取当前账户地址
                String ownerAddress = FiscoBcosClient.getCryptoKeyPair().getAddress();

                System.out.println("测试使用的账户地址: " + ownerAddress);
                System.out.println("合约地址: " + contract.getContractAddress());

                // 测试1: 检查账户权限

                // 测试2: 提交和验证哈希
                testSubmitAndMergeHashes(contract);

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
         * 测试2: 提交和验证哈希
         */
        private static void testSubmitAndMergeHashes(PlusAddress contract) throws Exception {
            System.out.println("\n========== 测试1: 提交和合并哈希 ==========");

            // 创建测试数据
            String testTraceCode = "0001";
            String testData1 = "生菜" ;
            String testData2 = "猪肉" ;

            byte[] dataHash1 = generateSHA256Hash(testData1);
            byte[] dataHash2 = generateSHA256Hash(testData2);

            System.out.println("测试追溯码1: " + testTraceCode);
            System.out.println("测试数据1: " + testData1);
            System.out.println("数据哈希1: " + bytesToHex(dataHash1));
            System.out.println("测试数据2: " + testData2);
            System.out.println("数据哈希2: " + bytesToHex(dataHash2));

            // 提交哈希
            System.out.println("正在提交哈希...");
            TransactionReceipt receipt = contract.mergeHashes(testTraceCode, dataHash1, dataHash2);

            System.out.println("交易结果状态: " + receipt.getStatus());
            System.out.println("合并哈希: " + receipt.getOutput());

            if (receipt.getStatus() != 0) {
                System.out.println("警告: 交易未成功，状态码: " + receipt.getStatus());
                return;
            }

        }
}


