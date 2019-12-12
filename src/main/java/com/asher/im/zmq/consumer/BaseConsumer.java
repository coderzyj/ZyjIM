package com.asher.im.zmq.consumer;

import com.asher.im.zmq.annotation.MessageHandler;
import com.asher.im.zmq.producer.DefaultProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.locks.LockSupport;

/**
 * @program learn-se
 * @description: 消费者基础类，后续类必须继承此类
 * @author: zhangyongjie
 * @create: 2019/12/06 10:51
 */
@Slf4j
public abstract class BaseConsumer implements Consumer{
    /**
     * 消息key
     */
    private String key;

    private Thread worker;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    public BaseConsumer(){
        this.key = this.getClass().getAnnotation(MessageHandler.class).key();
    }

    @PostConstruct
    public void start(){
        init();
    }

    @Override
    public void consume(Object message){
        throw new UnsupportedOperationException();
    }

    public final void init(){
        if(worker == null){
            worker = new Thread(()->{
                //暂时先写成死循环 ，但是在没有消息取的时候会造成空转，后期优化
                while(true){
                    Object message = redisTemplate.opsForList().rightPop(key);
                    if (message == null){
//                        try {
//                             wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        log.info("当前消费者线程：{}，未取到消息",Thread.currentThread().getName());
                        //没有消息取到就阻塞当前线程
                        DefaultProducer.setCurrentConsumerThread(worker);
                        LockSupport.park();
//                        continue;
                    }
                    consume(message);
                }

            },"消费者线程"+key);
            worker.start();
        }
    }

}
