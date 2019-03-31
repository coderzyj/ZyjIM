package com.asher.im.service.impl;

import com.asher.im.mapper.UserMapper;
import com.asher.im.model.User;
import com.asher.im.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author : coderzyj
 * @date : 2019/3/31 23:05
 * @Version : v1.0
 * @description
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfo(User user) {
        return userMapper.getUserInfo(user);
    }
}
