package com.asher.im.controller;

import com.asher.im.model.User;
import com.asher.im.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    public User userLogin(User user){
        return userService.getUserInfo(user);
    }
}
