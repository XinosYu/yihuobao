package com.yihuobao.yhb.controller;


import com.yihuobao.yhb.entity.dto.CodeDTO;
import com.yihuobao.yhb.entity.dto.LoginDTO;
import com.yihuobao.yhb.entity.dto.WeChatCodeDTO;
import com.yihuobao.yhb.service.UserService;
import com.yihuobao.yhb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  用户注册、登录
 * </p>
 *
 * @author admin
 * @since 2025-07-17
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody LoginDTO loginDTO) {
        return userService.register(
                loginDTO.getAccount(),
                loginDTO.getPassword(),
                loginDTO.getCode()
        );
    }

    @PostMapping("/login/Password")
    public Result loginByPassword(@RequestBody LoginDTO loginDTO) {
        return userService.loginByPassword(
                loginDTO.getAccount(),
                loginDTO.getPassword()
        );
    }

    @PostMapping("/login/Code")
    public Result loginByCode(@RequestBody LoginDTO loginDTO) {
        return userService.loginByCode(
                loginDTO.getAccount(),
                loginDTO.getCode()
        );
    }

    @PostMapping("/login/wechat")
    public Result loginByWeChat(@RequestBody WeChatCodeDTO weChatCodeDTO) {
        return userService.loginByWeChat(weChatCodeDTO.getCode());
    }

    @PostMapping("/sendCode")
    public Result sendCode(@RequestBody CodeDTO codeDTO) {
        return userService.sendCode(codeDTO.getAccount());
    }


}