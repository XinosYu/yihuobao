package com.yihuobao.yhb.utils;

import java.util.Random;

public class RandomUtils {
    /**
     * 生成指定长度的随机数字字符串
     * @param length 随机数字的长度，必须大于0
     * @return 指定长度的随机数字字符串
     * @throws IllegalArgumentException 如果长度小于或等于0
     */
    public static String randomNumbers(int length) {
        // 验证输入的长度是否有效
        if (length <= 0) {
            throw new IllegalArgumentException("长度必须大于0");
        }

        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        // 第一位数字不能为0，确保生成的数字长度准确
        sb.append(random.nextInt(9) + 1);

        // 生成剩余的数字
        for (int i = 1; i < length; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}
