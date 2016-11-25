package com.contactserver.user;

import java.net.Socket;

/**
 * 用户实体
 * Created by admin on 16/9/20.
 */
public class UserEntity {

    //用户ID
    private Integer userId;
    //用户名
    private String userName;
    //socket连接地址
    private Socket socket;
    //聊天记录
    private StringBuffer chatLog = new StringBuffer( );

    public Integer getUserId( ) {
        return userId;
    }

    public void setUserId( Integer userId ) {
        this.userId = userId;
    }

    public String getUserName( ) {
        return userName;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }

    public Socket getSocket( ) {
        return socket;
    }

    public void setSocket( Socket socket ) {
        this.socket = socket;
    }

    public StringBuffer getChatLog( ) {
        return chatLog;
    }

    public void setChatLog( StringBuffer chatLog ) {
        this.chatLog = chatLog;
    }
}
