package com.asher.im.controller;

import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSON;
import com.asher.im.base.ApiResponse;
import com.asher.im.model.User;
import com.asher.im.service.UserService;
import com.asher.im.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 *
 * @author : coderzyj
 * @date : 2019/3/31 23:03
 * @Version : v1.0
 * @description
 **/
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/login")
    @ResponseBody
    public ApiResponse userLogin(@RequestBody  User user){
        User info = userService.getUserInfo(user);
        if(info == null){
            return ApiResponse.ofFailed(null,"用户名/密码错误");
        }
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        log.info("生成的token =>{},用户信息=>{}",token, JSON.toJSONString(info));
        //如果验证通过则生成令牌并存入redis，默认过期时间30分钟
        redisUtils.set(token,JSON.toJSONString(info),1800);
        Map<String,String> map = new HashMap<>(16);
        map.put("token",token);
        map.put("info",JSON.toJSONString(info));
        return ApiResponse.ofSuccess(map);
    }
}
