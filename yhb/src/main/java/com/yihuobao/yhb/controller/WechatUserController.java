package com.yihuobao.yhb.controller;

import com.yihuobao.yhb.entity.dto.WechatAuthDTO;
import com.yihuobao.yhb.service.WechatUserService;
import com.yihuobao.yhb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信用户控制器 - 支持微信小程序跨域
 * 
 * @author admin
 * @since 2025-07-17
 */
@RestController
@RequestMapping("/wechatuser")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD})
public class WechatUserController {
    @Autowired
    private WechatUserService wechatUserService;

    /**
     * 获取SessionId接口
     * 支持微信小程序跨域访问
     */
    @GetMapping("/sessionId/{code}")
    public String getSessionId(@PathVariable("code") String code){
        return wechatUserService.getSessionId(code);
    }

    /**
     * 微信认证登录接口
     * 支持微信小程序跨域访问
     */
    @PostMapping("/authLogin")
    public Result authLogin(@RequestBody WechatAuthDTO wxAuth) {
        Result result = wechatUserService.authLogin(wxAuth);
        return result;
    }

}