package com.yihuobao.yhb.controller;


import com.yihuobao.yhb.service.UserService;
import com.yihuobao.yhb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
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
    public Result registController(@RequestParam String phoneNumber, @RequestParam String password, @RequestParam String code){
        return userService.register(phoneNumber, password,code);
    }

    @PostMapping("/loginwithPassword")
    public Result loginwithPasswordController(@RequestParam String phoneNumber, @RequestParam String password){
        return userService.loginwithPassword(phoneNumber, password);
    }

    @PostMapping("/loginwithCode")
    public Result loginwithCodeController(@RequestParam String phoneNumber, @RequestParam String code){
        return userService.loginwithCode(phoneNumber, code);
    }

    @GetMapping("/sendCode")
    public Result sendCode(@RequestParam String phoneNumber){
        return userService.sendCode(phoneNumber);
    }


}