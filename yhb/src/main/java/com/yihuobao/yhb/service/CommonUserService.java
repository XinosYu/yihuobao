package com.yihuobao.yhb.service;

import com.yihuobao.yhb.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yihuobao.yhb.utils.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2025-07-17
 */
public interface UserService extends IService<User> {

    Result sendCode(String account);

    Result register(String account, String password, String code);

    Result loginByPassword(String account, String password);

    Result loginByCode(String account, String code);

    Result loginByWeChat(String code);

    Result deleteUser(int id);
    Result editUser(int id);

    User loadUserByUserName(String userName);
}
