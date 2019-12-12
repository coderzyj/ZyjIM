package com.asher.im.zmq.consumer;

/**
 * @program learn-se
 * @description: 消费者接口，目前redis有两种消费模型：
 *  1.基于线程 生产者——消费者模型
 *  2.基于redis发布订阅模式（广播消息，能多个客户端消费）
 * @author: zhangyongjie
 * @create: 2019/12/05 20:50
 */

public interface Consumer {
    void consume(Object message);
}
