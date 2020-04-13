package com.zhongjianbaoapi.controller;


import com.zhongjianbaoapi.service.FaUserService;
import com.zhongjianbaoapi.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author HENRYC
 * @since 2020-04-10
 */
@RestController
@Api(value = "用户信息", tags = {"用户信息"})
@RequestMapping("/API/user")
public class FaUserController {

    @Autowired
    private FaUserService userService;

    @ApiOperation(value = "用户注册/登录")
    @PostMapping("/register")
//    public R register(@RequestParam String mobile,
//                      @RequestParam String code) throws Exception {
    public R register(@RequestBody Map<String, String> map) throws Exception {

        R r = userService.register(map.get("mobile"), map.get("code"));
        return r;
    }

    @ApiOperation(value = "发送验证码")
    @GetMapping("/setMessage")
    public R setMessage(@RequestParam String mobile){
        R r = userService.setMessage(mobile);
        return r;
    }


}

