package com.asher.im.service;

import com.asher.im.model.User;

/**
 * 版权声明：CopyRight (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author : 张勇杰
 * @date : 2019/3/31 23:05
 * @Version : v1.0
 * @description
 **/
public interface UserService {
    /**
     * 获取用户信息
     * @param user
     * @return
     */
    User getUserInfo(User user);
}
