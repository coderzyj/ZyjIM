package com.asher.im.interceptor;

import com.alibaba.fastjson.JSON;
import com.asher.im.base.ApiResponse;
import com.asher.im.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        logger.info("请求uri =>{},请求参数=>{}",request.getRequestURI(), JSON.toJSONString(request.getParameterMap()));
        String token = request.getHeader("token");

        try{
            if(StringUtils.isEmpty(token)){
                responseWithFailed(response);
                return false;
            }
            if(redisUtils.get(token) == null){
                responseWithFailed(response);
                return false;
            }
        }catch (Exception e){
            logger.error("异常信息：",e);
            return false;
        }
        return true;
    }

    private void responseWithFailed(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ApiResponse.ofFailed()));
        writer.close();
        response.reset();
    }
}
