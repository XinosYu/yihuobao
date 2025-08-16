package com.yihuobao.yhb.service;

import com.yihuobao.yhb.entity.User;
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

//    Result register(String phoneNumber, String password, String code);
//    Result loginwithPassword(String phoneNumber, String password);
//    Result loginwithCode(String phoneNumber, String password);

    Result sendCode(String account);

    Result registerAndLogin(String account, String password, String code);
}
