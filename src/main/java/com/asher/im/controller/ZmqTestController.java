package com.asher.im.controller;

import com.asher.im.zmq.producer.DefaultProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program learn-se
 * @description:
 * @author: zhangyongjie
 * @create: 2019/12/06 14:41
 */
@Controller
@RequestMapping("/mq")
public class ZmqTestController {
    @Autowired
    DefaultProducer<String> defaultProducer;

    @RequestMapping("/send")
    @ResponseBody
    public String test(){
        defaultProducer.produce("testKey","111111");
        defaultProducer.produce("testKey","222222");
        defaultProducer.produce("testKey","333333");
        defaultProducer.produce("testKey","444444");
        defaultProducer.produce("testKey","555555");
        defaultProducer.produce("testKey","666666");

        return "aaa";
    }
    @RequestMapping("/send2")
    @ResponseBody
    public String test2(){
        defaultProducer.produce("testKey2","a");
        defaultProducer.produce("testKey2","b");
        defaultProducer.produce("testKey2","c");
        defaultProducer.produce("testKey2","d");
        defaultProducer.produce("testKey2","e");
        defaultProducer.produce("testKey2","f");

        return "aaa";
    }
}
