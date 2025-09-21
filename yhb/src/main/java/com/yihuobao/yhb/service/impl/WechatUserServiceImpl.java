package com.yihuobao.yhb.service.impl;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yihuobao.yhb.Wechat.RedisKey;
import com.yihuobao.yhb.entity.dto.WechatAuthDTO;
import com.yihuobao.yhb.entity.po.WechatUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yihuobao.yhb.entity.vo.WxUserInfo;
import com.yihuobao.yhb.mapper.WechatUserMapper;
import com.yihuobao.yhb.service.WechatUserService;
import com.yihuobao.yhb.utils.Result;


import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2025-07-17
 */
@Service
public class WechatUserServiceImpl extends ServiceImpl<WechatUserMapper, WechatUser> implements WechatUserService {

    @Value("${weixin.appid}")
    private String appid;

    @Value("${weixin.secret}")
    private String secret;

    @Autowired
    StringRedisTemplate redisTemplate;


    public String wxDecrypt(String encryptedData, String sessionId, String vi) throws Exception {
        // 开始解密
        String json =  redisTemplate.opsForValue().get(RedisKey.WX_SESSION_ID + sessionId);
        if (json == null) {
            throw new RuntimeException("Session ID不存在或已过期");
        }
        //之前存储在redis中的信息：{"session_key":"G59Evf\/Em54X6WsFsrpA1g==","openid":"o2ttv5L2yufc4-VoSPhTyUnToY60"}
        JSONObject jsonObject = JSON.parseObject(json);
        String sessionKey = (String) jsonObject.get("session_key");
        if (sessionKey == null) {
            throw new RuntimeException("Session key不存在");
        }
        byte[] encData = cn.hutool.core.codec.Base64.decode(encryptedData);
        byte[] iv = cn.hutool.core.codec.Base64.decode(vi);
        byte[] key = Base64.decode(sessionKey);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return new String(cipher.doFinal(encData), "UTF-8");
    }



    @Override
    public String getSessionId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        String replaceUrl = url.replace("{0}", appid).replace("{1}", secret).replace("{2}", code);
        String res = HttpUtil.get(replaceUrl);
        String s = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(RedisKey.WX_SESSION_ID + s, res);
        return s;
    }

    @Override
    public Result authLogin(WechatAuthDTO wxAuth) {
        try {
            String wxRes = this.wxDecrypt(wxAuth.getEncryptedData(), wxAuth.getSessionId(), wxAuth.getIv());
            //用户信息：{"openId":"o20","nickName":"juana","gender":2,"language":"zh_CN","city":"Changsha","province":"Hunan","country":"China","avatarUrl":"头像链接","watermark":{"timestamp":dsfs,"appid":"应用id"}}
            WxUserInfo wxUserInfo = JSON.parseObject(wxRes, WxUserInfo.class);
            // 业务操作：你可以在这里利用数据 对数据库进行查询， 如果数据库中没有这个数据，就添加进去，即实现微信账号注册
            // 如果是已经注册过的，就利用数据，生成jwt 返回token，实现登录状态
            return Result.success(wxUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("微信解密失败: " + e.getMessage());
        }
    }
}