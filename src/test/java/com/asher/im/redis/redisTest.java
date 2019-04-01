package com.asher.im.redis;

import com.asher.im.DemoApplicationTests;
import com.asher.im.util.RedisUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author : coderzyj
 * @date : 2019/4/1 13:58
 * @Version : v1.0
 * @description
 **/
public class redisTest extends DemoApplicationTests {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testRedis(){
        redisUtils.set("zyj","zhang");
        System.out.println(redisUtils.get("zyj"));
    }
}
