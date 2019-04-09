package com.asher.im.socket;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author : coderzyj
 * @date : 2019/4/9 18:33
 * @Version : v1.0
 * @description
 **/
@Data
@AllArgsConstructor
public class PushMessage {
    private String message;

    /**向前端推送的消息类型 0：连接成功标识 1：在线用户列表 2：正常消息**/
    private Integer type;

    /**
     * 提示连接成功
     * @param message
     * @return
     */
    public static PushMessage pushConnectSuccess(String message){
        return new PushMessage(message,0);
    }

    /**
     * 获取在线用户
     * @param message
     * @return
     */
    public static PushMessage listOnlineUser(String message){
        return new PushMessage(message,1);
    }

    /**
     * 推送正常消息
     * @param message
     * @return
     */
    public static PushMessage pushNormalMessage(String message){
        return new PushMessage(message,2);
    }
}
