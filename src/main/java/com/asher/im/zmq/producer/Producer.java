package com.asher.im.zmq.producer;

/**
 * 消息生产者
 */
public interface Producer<T> {
    public void produce(String key, T message);
}
