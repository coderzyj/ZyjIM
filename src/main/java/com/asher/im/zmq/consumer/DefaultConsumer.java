package com.asher.im.zmq.consumer;

import com.alibaba.fastjson.JSON;
import com.asher.im.zmq.annotation.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program learn-se
 * @description:
 * @author: zhangyongjie
 * @create: 2019/12/06 11:04
 */
@Component
@MessageHandler(key = "testKey")
@Slf4j
public class DefaultConsumer extends BaseConsumer{
//    public DefaultConsumer() {
////        super(this.getClass().getAnnotation(MessageHandler.class).key());
//        super();
//    }

    @Override
    public void consume(Object message) {
        log.info("消费者消费消息:{}"+ JSON.toJSONString(message));

    }
}
