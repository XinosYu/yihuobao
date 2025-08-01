package com.yihuobao.yhb.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static final String PHONE_REGEX = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    /**
     * 是否是无效手机格式
     * @param phoneNumber 要校验的手机号
     * @return true:符合，false：不符合
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // 检查输入是否为null
        if (phoneNumber == null) {
            return false;
        }
        // 创建Matcher对象进行匹配
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);

        // 返回匹配结果
        return matcher.matches();
    }
}
