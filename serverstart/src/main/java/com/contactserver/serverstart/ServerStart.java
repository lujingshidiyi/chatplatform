package com.contactserver.serverstart;

import com.contactserver.contactutils.CommonUtils;
import com.contactserver.user.UserEntity;
import com.contactserver.user.mgr.UserMgr;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 * 服务器
 * Created by admin on 16/9/13.
 */
public class ServerStart {
    //日志
    private static final Logger LOGGER = Logger.getLogger( ServerStart.class );
    //socket连接集合
    private List<Socket> socketList = new ArrayList<>( );
    //服务器地址
    private static ServerSocket server;

    public static void main( String[] args ) throws IOException {
        //初始化日志配置文件
        PropertyConfigurator.configure( "cfg/log4j.properties" );
        new ServerStart( );
    }

    public ServerStart( ) throws IOException {
        this.server = new ServerSocket( 9090 );
        SocketThread socketThread = new SocketThread( );
        socketThread.start( );
    }


    class SocketThread extends Thread {
        @Override
        public void run( ) {
            //不断接收客户端的链接
            while ( true ) {
                Socket client = null;
                try {
                    //服务器接收到了一个socket连接
                    client = server.accept( );
                    //是否存在此连接,如果不存在则创建新用户
                    UserMgr userMgr = new UserMgr( );//实例化一个用户管理类
                    UserEntity userEntity = userMgr.getOrCreateUserEntity( 1 );
                    LOGGER.info( "[连接成功]userId:" + userEntity.getUserId( ) + "       userName:" + userEntity.getUserName( ) + "      userSocket:" + userEntity.getSocket( ) );
                    socketList.add( client );
                    InputThread thread = new InputThread( userEntity, client );
                    thread.start( );
                } catch ( IOException e ) {
                    e.printStackTrace( );
                }
            }
        }
    }

    class InputThread extends Thread {
        public Socket client;
        public UserEntity userEntity;

        public InputThread( UserEntity userEntity, Socket client ) {
            this.userEntity = userEntity;
            this.client = client;
        }

        @Override
        public void run( ) {
            try {
                String str = "";
                StringBuffer sb = new StringBuffer( );
                int len = -1;

                while ( true ) {
                    InputStream inputStream = client.getInputStream( );
                    byte[] bs = new byte[ 1024 ];
                    while ( ( len = inputStream.read( bs ) ) != -1 ) {
                        sb.append( new String( bs, 0, len ) );
                    }
//                    inputStream.close( );

                    String type = String.valueOf( sb.toString( ).getBytes( )[ 0 ] );
                    if ( type.equals( "48" ) ) {
                        LOGGER.info( "正在登录..." );
                        String content = sb.toString( ).substring( 1 );
                        String[] contents = content.split( "&" );
                        String userId = contents[ 0 ];
                        String pwd = contents[ 1 ];
                        if ( userId.equals( "123" ) && pwd.equals( "123" ) ) {
                            OutputStream out = client.getOutputStream( );
                            out.write( "登陆成功".getBytes( ) );
                            client.shutdownOutput( );
                        }
                    } else {
                        userEntity.getChatLog( ).append( str );
                        //将聊天记录绑定到用户实体
                        LOGGER.info( "接收到" + userEntity.getUserId( ) + ":" + str );
                        str = CommonUtils.formatNotes( client, sb.toString( ) );
                        for ( int i = 0; i < socketList.size( ); i++ ) {
                            if ( socketList.get( i ) != client && len != -1 ) {
                                OutputStream out = socketList.get( i ).getOutputStream( );
                                out.write( str.getBytes( ) );
                                out.close( );
                            }
                        }

                    }
                }
            } catch ( IOException e ) {
                e.printStackTrace( );
            }

        }
    }


}

