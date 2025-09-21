package com.yihuobao.yhb.controller;


import com.yihuobao.yhb.entity.dto.WechatAuthDTO;
import com.yihuobao.yhb.service.WechatUserService;
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
@RequestMapping("/wechatuser")
public class WechatUserController {
    @Autowired
    WechatUserService wechatUserService;

    //这个就是那个使用传code进来的接口
    @GetMapping("/sessionId/{code}")
    public String getSessionId(@PathVariable("code") String code){
        return wechatUserService.getSessionId(code);
    }

    @PostMapping("/authLogin")
    public Result authLogin(@RequestBody WechatAuthDTO wxAuth) {
        Result result = wechatUserService.authLogin(wxAuth);
        return result;
    }

}
