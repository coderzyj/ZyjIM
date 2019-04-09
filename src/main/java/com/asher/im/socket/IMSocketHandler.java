package com.asher.im.socket;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 *
 * @author : coderzyj
 * @date : 2019/4/1 10:36
 * @Version : v1.0
 * @description
 **/
@ServerEndpoint("/websocket/{username}")
@Component
@Slf4j
@Data
public class IMSocketHandler{
    private static CopyOnWriteArraySet<IMSocketHandler> websocketSet = new CopyOnWriteArraySet<>();

    private static ThreadLocal<SimpleDateFormat> sf = new ThreadLocal<>();

    private static CopyOnWriteArrayList<String> userList = new CopyOnWriteArrayList<>();

    /**
     * 当前会话连接
     */
    private Session session;

    /**
     * 当前连接用户名
     */
    private String username;


    /**
     * 获取时间转换
     * @return
     */
    private static SimpleDateFormat getFormat(){
        try{
            SimpleDateFormat re = sf.get();
            if(re == null){
                sf.set(new SimpleDateFormat("yyyyMMddHHmmss"));
                return sf.get();
            }
            return re;
        }finally {
            sf.remove();
        }
    }
    /**
     * websocket连接成功时调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws InterruptedException {
        this.session = session;
        websocketSet.add(this);
        this.username = username;
        userList.add(username);
        //更新在线用户列表
        log.info("用户列表 =>{}",JSON.toJSONString(userList));
        updateOnlineUserList();
        //this.session.getAsyncRemote().sendText(JSON.toJSONString(PushMessage.listOnlineUser(JSON.toJSONString(userList))));
        System.out.println("当前在线人数:"+websocketSet.size()+"人");
        Thread.sleep(1000);
        this.session.getAsyncRemote().sendText(JSON.toJSONString(PushMessage.pushConnectSuccess(this.username+",你已连接成功")));
    }

    /**
     * websocket关闭时执行的方法
     */
    @OnClose
    public void onClose(){
        //从保存会话的set中移除
        websocketSet.remove(this);
        for(String user : this.userList){
            if (user.equals(this.username)){
                this.userList.remove(user);
            }
        }
        updateOnlineUserList();
        System.out.println("有一个用户断开连接，当前在线人数："+websocketSet.size()+"人");
    }

    /**
     * websocket发生错误时执行的方法
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        log.error("当前回会话 => {} 发生错误",session.getId());
        error.printStackTrace();
    }


    /**
     * 处理客户端传过来的消息的方法
     * @param session
     * @param message
     * @param username
     */
    @OnMessage
    public void onMessage(Session session,String message,@PathParam("username") String username){

        //群发消息
        brocastMessage(message,username);
    }

    /**
     * 更新用户列表
     */
    private void updateOnlineUserList(){
        this.websocketSet.stream().forEach(item -> item.session.getAsyncRemote().sendText(JSON.toJSONString(PushMessage.listOnlineUser(JSON.toJSONString(userList)))));
    }

    /**
     * 群发消息
     * @param message
     * @param username
     */
    private void brocastMessage(String message, String username){
        //遍历会话列表
        if (!websocketSet.isEmpty()){
            for(IMSocketHandler sh : websocketSet){
                String date = getFormat().format(new Date());
                sh.session.getAsyncRemote().sendText(JSON.toJSONString(PushMessage.pushNormalMessage(username+":"+message)));
            }
        }
    }
}
