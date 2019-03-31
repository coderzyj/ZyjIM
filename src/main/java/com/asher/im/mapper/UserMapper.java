package com.asher.im.mapper;

import com.asher.im.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author : coderzyj
 * @date : 2019/3/31 23:00
 * @Version : v1.0
 * @description
 **/
@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username} and password = #{password}")
    User getUserInfo(User user);
}
