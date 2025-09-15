package com.yihuobao.yhb.contracts;

import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;

import java.math.BigInteger;
import java.util.List;

/**
 * Margin 合约测试类
 */
public class MarginTest {

    // 测试用的商家地址(确保格式正确)
    private static final String TEST_MERCHANT_ADDRESS = "0x7567d83b7b8d80addcb281a71d54fc7b3364ffed";

    public static void main(String[] args) {
        try {
            System.out.println("开始 FISCO BCOS 合约测试...");

            // 初始化客户端
            FiscoBcosClient.initialize();

            // 获取合约实例
            Margin contract = FiscoBcosClient.getMargin_contract();

            // 获取当前账户地址(管理员地址)
            String adminAddress = FiscoBcosClient.getCryptoKeyPair().getAddress();

            System.out.println("管理员账户地址: " + adminAddress);
            System.out.println("测试商家账户地址: " + TEST_MERCHANT_ADDRESS);
            System.out.println("合约地址: " + contract.getContractAddress());

            // 测试1: 验证管理员身份
            testAdminIdentity(contract, adminAddress);

            // 测试2: 注册商家账户
            testRegister(contract, TEST_MERCHANT_ADDRESS);

            // 测试3: 查询商家状态
            testCheckStatus(contract, TEST_MERCHANT_ADDRESS);

            // 测试4: 存款操作
            testDeposit(contract, TEST_MERCHANT_ADDRESS, new BigInteger("100"));

            // 测试5: 查询保证金余额
            testCheckMargin(contract, TEST_MERCHANT_ADDRESS);

            // 测试6: 扣款操作
            testDeduct(contract, TEST_MERCHANT_ADDRESS, new BigInteger("50"));

            // 测试7: 再次查询保证金余额，确认扣款成功
            testCheckMargin(contract, TEST_MERCHANT_ADDRESS);

            // 测试8: 冻结账户
            testFrozen(contract, TEST_MERCHANT_ADDRESS);

            // 测试9: 验证账户已冻结
            testCheckStatus(contract, TEST_MERCHANT_ADDRESS);

            // 测试10: 尝试在冻结账户上操作(预期会失败)
            testOperationOnFrozenAccount(contract, TEST_MERCHANT_ADDRESS);

            System.out.println("全部测试完成！");

        } catch (Exception e) {
            System.err.println("测试失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("测试结束，正在退出...");
        }
    }

    /**
     * 测试1: 验证管理员身份
     */
    private static void testAdminIdentity(Margin contract, String adminAddress) throws Exception {
        System.out.println("\n========== 测试1: 验证管理员身份 ==========");

        // 从合约获取管理员地址
        String contractAdmin = contract.admin();
        System.out.println("合约中记录的管理员地址: " + contractAdmin);
        System.out.println("当前操作账户地址: " + adminAddress);

        if (contractAdmin.equals(adminAddress)) {
            System.out.println("测试1通过: 当前账户是合约管理员");
        } else {
            System.out.println("测试1失败: 当前账户不是合约管理员，后续操作可能失败");
        }
    }

    /**
     * 测试2: 注册商家账户
     */
    private static void testRegister(Margin contract, String merchantAddress) throws Exception {
        System.out.println("\n========== 测试2: 注册商家账户 ==========");

        // 获取注册前的状态
        BigInteger beforeStatus = contract.getIsRegister(merchantAddress);
        System.out.println("注册前状态 (1=未注册): " + beforeStatus);

        // 执行注册操作
        System.out.println("正在注册商家账户...");
        BigInteger registerTime = BigInteger.valueOf(System.currentTimeMillis() / 1000); // 秒级时间戳
        TransactionReceipt receipt = contract.register(merchantAddress, registerTime);

        System.out.println("注册交易状态: " + receipt.getStatus());
        if (receipt.getStatus() != 0) {
            System.out.println("测试2失败: 注册交易执行失败");
            return;
        }

        // 获取注册结果
        Tuple1<String> registerResult = contract.getRegisterOutput(receipt);
        System.out.println("注册返回结果: " + registerResult.getValue1());

        // 验证注册事件
        List<Margin.RegisterEventEventResponse> registerEvents = contract.getRegisterEventEvents(receipt);
        if (!registerEvents.isEmpty()) {
            Margin.RegisterEventEventResponse event = registerEvents.get(0);
            System.out.println("注册事件验证: 商家地址=" + event.merchantAddress + ", 注册时间=" + event.registerTime);
        }

        // 获取注册后的状态
        BigInteger afterStatus = contract.getIsRegister(merchantAddress);
        System.out.println("注册后状态 (0=正常): " + afterStatus);

        if (afterStatus.equals(BigInteger.ZERO)) {
            System.out.println("测试2通过: 商家注册成功");
        } else {
            System.out.println("测试2失败: 商家注册失败");
        }
    }

    /**
     * 测试3: 检查商家状态
     */
    private static void testCheckStatus(Margin contract, String merchantAddress) throws Exception {
        System.out.println("\n========== 测试3: 检查商家状态 ==========");

        BigInteger status = contract.getIsRegister(merchantAddress);
        System.out.println("商家状态值: " + status);

        String statusDesc = getStatusDescription(status);
        System.out.println("商家状态描述: " + statusDesc);

        System.out.println("测试3通过: 状态查询成功");
    }

    /**
     * 测试4: 存款操作
     */
    private static void testDeposit(Margin contract, String merchantAddress, BigInteger amount) throws Exception {
        System.out.println("\n========== 测试4: 存款操作 ==========");

        System.out.println("存款金额: " + amount);

        // 获取存款前的余额
        BigInteger beforeBalance = contract.getMargin(merchantAddress);
        System.out.println("存款前余额: " + beforeBalance);

        // 执行存款操作
        System.out.println("正在执行存款...");
        BigInteger updateTime = BigInteger.valueOf(System.currentTimeMillis() / 1000);
        TransactionReceipt receipt = contract.deposit(merchantAddress, amount, updateTime);

        System.out.println("存款交易状态: " + receipt.getStatus());
        if (receipt.getStatus() != 0) {
            System.out.println("测试4失败: 存款交易执行失败");
            return;
        }

        // 获取存款结果
        Tuple3<String, BigInteger, BigInteger> depositResult = contract.getDepositOutput(receipt);
        System.out.println("存款返回结果: " + depositResult.getValue1() +
                ", 最新余额: " + depositResult.getValue2() +
                ", 更新时间: " + depositResult.getValue3());

        // 验证存款事件
        List<Margin.DepositEventEventResponse> depositEvents = contract.getDepositEventEvents(receipt);
        if (!depositEvents.isEmpty()) {
            Margin.DepositEventEventResponse event = depositEvents.get(0);
            System.out.println("存款事件验证: 商家地址=" + event.merchantAddress +
                    ", 存款后余额=" + event.totalMargin +
                    ", 存款金额=" + event.amount);
        }

        // 获取存款后的余额
        BigInteger afterBalance = contract.getMargin(merchantAddress);
        System.out.println("存款后余额: " + afterBalance);

        if (afterBalance.equals(beforeBalance.add(amount))) {
            System.out.println("测试4通过: 存款成功");
        } else {
            System.out.println("测试4失败: 存款失败");
        }
    }

    /**
     * 测试5/7: 查询保证金余额
     */
    private static void testCheckMargin(Margin contract, String merchantAddress) throws Exception {
        System.out.println("\n========== 测试: 查询保证金余额 ==========");

        BigInteger balance = contract.getMargin(merchantAddress);
        System.out.println("当前保证金余额: " + balance);

        if (balance.compareTo(BigInteger.ZERO) >= 0) {
            System.out.println("测试通过: 余额查询成功");
        } else {
            System.out.println("测试失败: 余额查询异常");
        }
    }

    /**
     * 测试6: 扣款操作
     */
    private static void testDeduct(Margin contract, String merchantAddress, BigInteger amount) throws Exception {
        System.out.println("\n========== 测试6: 扣款操作 ==========");

        System.out.println("扣款金额: " + amount);

        // 获取扣款前的余额
        BigInteger beforeBalance = contract.getMargin(merchantAddress);
        System.out.println("扣款前余额: " + beforeBalance);

        // 执行扣款操作
        System.out.println("正在执行扣款...");
        BigInteger updateTime = BigInteger.valueOf(System.currentTimeMillis() / 1000);
        TransactionReceipt receipt = contract.deduct(merchantAddress, amount, updateTime);

        System.out.println("扣款交易状态: " + receipt.getStatus());
        if (receipt.getStatus() != 0) {
            System.out.println("测试6失败: 扣款交易执行失败");
            return;
        }

        // 获取扣款结果
        Tuple3<String, BigInteger, BigInteger> deductResult = contract.getDeductOutput(receipt);
        System.out.println("扣款返回结果: " + deductResult.getValue1() +
                ", 最新余额: " + deductResult.getValue2() +
                ", 更新时间: " + deductResult.getValue3());

        // 验证扣款事件
        List<Margin.DeductEventEventResponse> deductEvents = contract.getDeductEventEvents(receipt);
        if (!deductEvents.isEmpty()) {
            Margin.DeductEventEventResponse event = deductEvents.get(0);
            System.out.println("扣款事件验证: 商家地址=" + event.merchantAddress +
                    ", 扣款后余额=" + event.totalMargin +
                    ", 扣款金额=" + event.amount);
        }

        // 获取扣款后的余额
        BigInteger afterBalance = contract.getMargin(merchantAddress);
        System.out.println("扣款后余额: " + afterBalance);

        if (afterBalance.equals(beforeBalance.subtract(amount))) {
            System.out.println("测试6通过: 扣款成功");
        } else {
            System.out.println("测试6失败: 扣款失败");
        }
    }

    /**
     * 测试8: 冻结账户
     */
    private static void testFrozen(Margin contract, String merchantAddress) throws Exception {
        System.out.println("\n========== 测试8: 冻结账户 ==========");

        // 获取冻结前的状态
        BigInteger beforeStatus = contract.getIsRegister(merchantAddress);
        System.out.println("冻结前状态: " + beforeStatus + " (" + getStatusDescription(beforeStatus) + ")");

        // 执行冻结操作
        System.out.println("正在冻结账户...");
        TransactionReceipt receipt = contract.frozen(merchantAddress);

        System.out.println("冻结操作交易状态: " + receipt.getStatus());
        if (receipt.getStatus() != 0) {
            System.out.println("测试8失败: 冻结交易执行失败");
            return;
        }

        // 验证冻结事件
        List<Margin.FrozenEventEventResponse> frozenEvents = contract.getFrozenEventEvents(receipt);
        if (!frozenEvents.isEmpty()) {
            Margin.FrozenEventEventResponse event = frozenEvents.get(0);
            System.out.println("冻结事件验证: 商家地址=" + event.merchantAddress + ", 冻结时间=" + event.frozenTime);
        }

        // 获取冻结后的状态
        BigInteger afterStatus = contract.getIsRegister(merchantAddress);
        System.out.println("冻结后状态: " + afterStatus + " (" + getStatusDescription(afterStatus) + ")");

        if (afterStatus.equals(BigInteger.valueOf(2))) {
            System.out.println("测试8通过: 账户冻结成功");
        } else {
            System.out.println("测试8失败: 账户冻结失败");
        }
    }

    /**
     * 测试10: 尝试在冻结账户上操作
     */
    private static void testOperationOnFrozenAccount(Margin contract, String merchantAddress) {
        System.out.println("\n========== 测试10: 尝试在冻结账户上操作 ==========");

        try {
            // 尝试存款
            System.out.println("尝试在冻结账户上存款...");
            BigInteger updateTime = BigInteger.valueOf(System.currentTimeMillis() / 1000);
            TransactionReceipt receipt = contract.deposit(merchantAddress, BigInteger.TEN, updateTime);
            System.out.println("存款交易状态: " + receipt.getStatus());
            System.out.println("测试10失败: 冻结账户上的存款操作未被拒绝");
        } catch (Exception e) {
            System.out.println("存款操作被拒绝，符合预期: " + e.getMessage());
        }

        try {
            // 尝试扣款
            System.out.println("尝试在冻结账户上扣款...");
            BigInteger updateTime = BigInteger.valueOf(System.currentTimeMillis() / 1000);
            TransactionReceipt receipt = contract.deduct(merchantAddress, BigInteger.valueOf(5), updateTime);
            System.out.println("扣款交易状态: " + receipt.getStatus());
            System.out.println("测试10失败: 冻结账户上的扣款操作未被拒绝");
        } catch (Exception e) {
            System.out.println("扣款操作被拒绝，符合预期: " + e.getMessage());
            System.out.println("测试10通过: 冻结账户上的操作均被拒绝");
        }
    }

    /**
     * 将状态值转换为描述信息
     */
    private static String getStatusDescription(BigInteger status) {
        if (status.equals(BigInteger.ZERO)) {
            return "正常";
        } else if (status.equals(BigInteger.ONE)) {
            return "未注册";
        } else if (status.equals(BigInteger.valueOf(2))) {
            return "冻结";
        } else if (status.equals(BigInteger.valueOf(3))) {
            return "已删除";
        } else {
            return "未知状态";
        }
    }
}