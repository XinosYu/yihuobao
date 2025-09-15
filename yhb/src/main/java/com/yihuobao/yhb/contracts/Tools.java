package com.yihuobao.yhb.contracts;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Tools {

    /**
     * 生成SHA-256哈希
     */
    public static byte[] generateSHA256Hash(String input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 将字节数组转换为十六进制字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
