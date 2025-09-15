package com.yihuobao.yhb.pitayafruit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ConversationService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // Redis中存储对话的键前缀
    private static final String CONVERSATION_KEY = "ai:conversation:";
    // 对话过期时间（7天）
    private static final long CONVERSATION_TTL = 7 * 24 * 60 * 60;

    /**
     * 保存消息到对话历史
     */
    public void saveMessage(String conversationId, String message, String role) {
        String key = CONVERSATION_KEY + conversationId;

        // 构建消息字符串，格式：角色::时间戳::消息内容
        String messageStr = role + "::" + System.currentTimeMillis() + "::" + message;

        // 保存到Redis列表
        stringRedisTemplate.opsForList().rightPush(key, messageStr);

        // 设置过期时间
        stringRedisTemplate.expire(key, CONVERSATION_TTL, TimeUnit.SECONDS);
    }

    /**
     * 获取对话历史
     */
    public List<String> getConversationHistory(String conversationId, int limit) {
        String key = CONVERSATION_KEY + conversationId;

        // 从Redis获取消息列表
        Long size = stringRedisTemplate.opsForList().size(key);
        if (size == null || size == 0) {
            return new ArrayList<>();
        }

        // 计算起始位置
        long start = Math.max(0, size - limit);
        return stringRedisTemplate.opsForList().range(key, start, size - 1);
    }
}
