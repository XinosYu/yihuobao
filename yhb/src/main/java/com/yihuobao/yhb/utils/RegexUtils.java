package com.yihuobao.yhb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static final String PHONE_REGEX = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    public static String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    /**
     * 是否是无效手机格式
     * @param phoneNumber 要校验的手机号
     * @return true:符合，false：不符合
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     * 是否是无效邮箱格式
     * @param userEmail 要校验的邮箱
     * @return true:符合，false：不符合
     */
    public static boolean isValidEmail(String userEmail) {
        if (userEmail == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(userEmail);
        return matcher.matches();
    }
}
