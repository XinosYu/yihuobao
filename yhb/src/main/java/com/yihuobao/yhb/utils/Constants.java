package com.yihuobao.yhb.utils;

import org.springframework.beans.factory.annotation.Autowired;

public class Constants {
    // 添加Redis键常量
    public class RedisConstants{
        public static final String LOGIN_CODE_KEY = "login:code:";
        public static final String LOGIN_USER_KEY = "login:token:";
        public static final long LOGIN_CODE_TTL = 2L;
        public static final long LOGIN_USER_TTL = 30L;
    }
}
