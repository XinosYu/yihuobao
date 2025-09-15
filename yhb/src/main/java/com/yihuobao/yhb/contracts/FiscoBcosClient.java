package com.yihuobao.yhb.contracts;


import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
public class FiscoBcosClient {

    private static final String CONFIG_FILE;

    static {
        try {
            String path = FiscoBcosClient.class.getClassLoader().getResource("config-example.toml").getPath();
            CONFIG_FILE = URLDecoder.decode(path, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            throw new RuntimeException("Failed to decode config file path", e);
        }
    }

    private static final String SAVEADDRESS_CONTRACT_ADDRESS = "0x6849f21d1e455e9f0712b1e99fa4fcd23758e8f1";
    private static final String PLUSADDRESS_CONTRACT_ADDRESS = "0xd24180cc0fef2f3e545de4f9aafc09345cd08903";
    private static final String MARGIN_CONTRACT_ADDRESS = "0x0519e7423be2a00eb57d9a7e6e98cd1e6f02cff4";
    private static final String GROUP_ID = "group0";

    // 单例实例
    private static BcosSDK sdk;
    private static Client client;
    private static CryptoKeyPair cryptoKeyPair;
    private static SaveAddress SaveAddress_contract;
    private static PlusAddress PlusAddress_contract;
    private static Margin Margin_contract;

    // 初始化标志
    private static boolean initialized = false;

    /**
     * 初始化SDK和合约（仅在第一次调用时执行）
     */
    public static synchronized void initialize() {
        if (!initialized) {
            try {
                // 初始化BcosSDK
                sdk = BcosSDK.build(CONFIG_FILE);

                // 初始化client
                client = sdk.getClient(GROUP_ID);

                // 使用指定的私钥而不是生成新的密钥对
                String hexPrivateKey = "5853b48f08a2374c410af7793cc6c3f00c7faddaf044c30d08166cd63a386f7d";

                // 根据密码套件创建密钥对
                CryptoSuite cryptoSuite = client.getCryptoSuite();
                cryptoKeyPair = cryptoSuite.loadKeyPair(hexPrivateKey);

                System.out.println("Using account address: " + cryptoKeyPair.getAddress());

                // 加载合约
                SaveAddress_contract = SaveAddress.load(SAVEADDRESS_CONTRACT_ADDRESS, client, cryptoKeyPair);
                PlusAddress_contract = PlusAddress.load(PLUSADDRESS_CONTRACT_ADDRESS, client, cryptoKeyPair);
                Margin_contract = Margin.load(MARGIN_CONTRACT_ADDRESS, client, cryptoKeyPair);
                // 设置初始化标志
                initialized = true;

                // 添加JVM关闭钩子，确保资源释放
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    if (sdk != null) {
                        sdk.stopAll();
                        sdk = null;
                    }
                }));
            } catch (Exception e) {
                System.err.println("初始化失败: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize FISCO BCOS client", e);
            }
        }
    }


    /**
     * 获取合约实例
     */
    public static SaveAddress getSaveAddress_contract() {
        if (!initialized) {
            initialize();
        }
        return SaveAddress_contract;
    }

    public static PlusAddress getPlusAddress_contract() {
        if (!initialized) {
            initialize();
        }
        return PlusAddress_contract;
    }
    public static Margin getMargin_contract() {
        if (!initialized) {
            initialize();
        }
        return Margin_contract;
    }
    /**
     * 获取Client实例
     */
    public static Client getClient() {
        if (!initialized) {
            initialize();
        }
        return client;
    }

    /**
     * 获取密钥对
     */
    public static CryptoKeyPair getCryptoKeyPair() {
        if (!initialized) {
            initialize();
        }
        return cryptoKeyPair;
    }
}
