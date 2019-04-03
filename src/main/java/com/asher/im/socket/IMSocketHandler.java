package com.asher.im.socket;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @author : coderzyj
 * @date : 2019/4/1 10:36
 * @Version : v1.0
 * @description
 **/
@ServerEndpoint("/websocket/{username}")
@Component
public class IMSocketHandler{
    private static CopyOnWriteArraySet<IMSocketHandler> websocketSet = new CopyOnWriteArraySet<>();

    /**
     * 当前会话连接
     */
    private Session session;

    /**
     * 当前连接用户名
     */
    private String username;
    /**
     * websocket连接成功时调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username){
        this.session = session;
        websocketSet.add(this);
        this.username = username;
        System.out.println("当前在线人数:"+websocketSet.size()+"人");
        this.session.getAsyncRemote().sendText(this.username+",你已连接成功");
    }

    /**
     * websocket关闭时执行的方法
     */
    @OnClose
    public void onClose(){
        //从保存会话的set中移除
        websocketSet.remove(this);
        System.out.println("有一个用户断开连接，当前在线人数："+websocketSet.size()+"人");
    }


    /**
     * 处理客户端传过来的消息的方法
     * @param session
     * @param message
     * @param username
     */
    @OnMessage
    public void onMessage(Session session,String message,@PathParam("username") String username){

    }
}
