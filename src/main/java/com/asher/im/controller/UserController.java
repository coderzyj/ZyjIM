package com.asher.im.controller;

import com.asher.im.base.ApiResponse;
import com.asher.im.model.User;
import com.asher.im.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author : coderzyj
 * @date : 2019/3/31 23:03
 * @Version : v1.0
 * @description
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ApiResponse userLogin(@RequestBody  User user){
        User info = userService.getUserInfo(user);
        if(info == null){
            return ApiResponse.ofFailed(null,"用户名/密码错误");
        }
        return ApiResponse.ofSuccess(info);
    }
}
