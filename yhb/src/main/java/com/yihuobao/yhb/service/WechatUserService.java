package com.yihuobao.yhb.service;

import com.yihuobao.yhb.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yihuobao.yhb.entity.dto.WechatAuthDTO;
import com.yihuobao.yhb.entity.po.WechatUser;
import com.yihuobao.yhb.utils.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2025-07-17
 */
public interface WechatUserService extends IService<WechatUser> {
        String getSessionId(String code);
        Result authLogin(WechatAuthDTO wxAuth);

}
