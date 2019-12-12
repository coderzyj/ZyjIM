package com.asher.im.zmq.producer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

/**
 * @program learn-se
 * @description: 默认生产者模型
 * @author: zhangyongjie
 * @create: 2019/12/05 17:58
 */
@Slf4j
@Component
public class DefaultProducer<T> implements Producer<T> {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

//    private ApplicationContext applicationContext;




    /**
     * 最大消息堆积数，默认10000条
     */
    private Integer MAX_MESSAGE_SIZE;

    private static Integer DEFAULT_MAX_MESSAGE_SIZE = 10000;

    //当前消费者线程
    private static volatile ConcurrentHashMap<String,Thread> currentConsumeThreads = new ConcurrentHashMap<>();

    public DefaultProducer(){
        this(DEFAULT_MAX_MESSAGE_SIZE);
    }

    public DefaultProducer(Integer maxMessageSize){
        this.MAX_MESSAGE_SIZE = maxMessageSize;
    }
    @Override
    public void produce(String key, T message) {
        Assert.notNull(key,"queue's name can not be null!");
        try{
            redisTemplate.opsForList().leftPush(key, JSON.toJSONString(message));
            if(currentConsumeThreads.containsKey("消费者线程"+key)){
                LockSupport.unpark(currentConsumeThreads.get("消费者线程"+key));
                log.info("消费者线程:{} -> 唤醒",currentConsumeThreads.get("消费者线程"+key).getName());
            }
//            notifyAll();
        }catch (Exception e){
            log.error("消息推送失败，路由key:{},message:{}",key,JSON.toJSONString(message),e);
        }

    }

    /**
     * 设置当前消费者线程
     * @param t
     */
    public static void setCurrentConsumerThread(Thread t){
        synchronized (DefaultProducer.class){
            currentConsumeThreads.put(t.getName(),t);
        }
    }


}
