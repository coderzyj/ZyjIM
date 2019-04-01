package com.asher.im.socket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @author : coderzyj
 * @date : 2019/4/1 10:36
 * @Version : v1.0
 * @description
 **/
@ServerEndpoint("/websocket")
@Component
public class IMSocketHandler{
    private static CopyOnWriteArraySet<IMSocketHandler> websocketSet = new CopyOnWriteArraySet<>();

    /**
     * 当前会话连接
     */
    private Session session;


    /**
     * websocket连接成功时调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        websocketSet.add(this);

        System.out.println("当前在线人数:"+websocketSet.size()+"人");
        this.session.getAsyncRemote().sendText("连接成功");
    }

    @OnClose
    public void onClose(){

    }
}
