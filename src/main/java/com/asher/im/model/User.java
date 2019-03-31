package com.asher.im.model;

import lombok.Data;

/**
 *
 * @author : coderzyj
 * @date : 2019/3/31 22:58
 * @Version : v1.0
 * @description
 **/
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
}
