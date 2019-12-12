package com.asher.im.zmq.consumer;

import com.alibaba.fastjson.JSON;
import com.asher.im.zmq.annotation.MessageHandler;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program ZyjIM
 * @description:
 * @author: zhangyongjie
 * @create: 2019/12/09 10:36
 */

@Component
@MessageHandler(key = "testKey2")
@Slf4j
public class TestConsumer extends BaseConsumer {
    @Override
    public void consume(Object message) {
            log.info("消费者消费消息:{}"+ JSON.toJSONString(message));

    }
}
