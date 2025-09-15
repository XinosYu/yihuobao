package com.yihuobao.yhb.controller;


import com.yihuobao.yhb.entity.dto.CodeDTO;
import com.yihuobao.yhb.entity.dto.LoginDTO;
import com.yihuobao.yhb.service.MerchantService;
import com.yihuobao.yhb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2025-07-17
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    @PostMapping("/register")
    public Result register(@RequestBody LoginDTO loginDTO) {
        return merchantService.register(
                loginDTO.getAccount(),
                loginDTO.getPassword(),
                loginDTO.getCode()
        );
    }

    @PostMapping("/loginByPassword")
    public Result loginByPassword(@RequestBody LoginDTO loginDTO) {
        return merchantService.loginByPassword(
                loginDTO.getAccount(),
                loginDTO.getPassword()
        );
    }

    @PostMapping("/loginByCode")
    public Result loginByCode(@RequestBody LoginDTO loginDTO) {
        return merchantService.loginByCode(
                loginDTO.getAccount(),
                loginDTO.getCode()
        );
    }

    @PostMapping("/sendCode")
    public Result sendCode(@RequestBody CodeDTO codeDTO) {
        return merchantService.sendCode(codeDTO.getAccount());
    }
}

