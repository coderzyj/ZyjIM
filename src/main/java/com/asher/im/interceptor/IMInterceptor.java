package com.asher.im.interceptor;

import com.alibaba.fastjson.JSON;
import com.asher.im.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author : coderzyj
 * @date : 2019/4/1 11:12
 * @Version : v1.0
 * @description
 **/
@Component
public class IMInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(IMInterceptor.class);

    @Autowired
    private RedisUtils redisUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("请求uri =>{},请求参数=>{}",request.getRequestURI(), JSON.toJSONString(request.getParameterMap()));
        String jwt = request.getHeader("jwt");

        return true;
    }
}
