package com.asher.im.config;

import com.asher.im.interceptor.IMInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author : coderzyj
 * @date : 2019/4/1 11:07
 * @Version : v1.0
 * @description
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private IMInterceptor imInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(imInterceptor)
                .addPathPatterns("/user/*")
                .excludePathPatterns("/user/login");
    }


}
