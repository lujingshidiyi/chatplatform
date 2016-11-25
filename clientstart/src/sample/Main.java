import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * 客户端
 */
public class Main extends Application {
    private static final Logger LOGGER = Logger.getLogger( Main.class.getName( ) );

    //是否登陆成功
    private static volatile boolean loginSuccess;
    private Socket client;

    public Main( ) throws IOException {
        client = new Socket( "127.0.0.1", 9090 );
    }

    @Override
    public void start( Stage stage ) throws Exception {

        stage.setTitle( "CHAT" );
        //首先进入登录场景
        getScene( stage );
        stage.show( );

    }

    //向服务器发送登录验证
    public void login( Stage stage, String userid, String pwd ) throws Exception {
        //向服务器发送登录数据
        StringBuffer stringBuffer = new StringBuffer( );
        stringBuffer.append( 0 );
        stringBuffer.append( userid );
        stringBuffer.append( "&" );
        stringBuffer.append( pwd );
        //获取输出连接
        OutputStream outputStream = client.getOutputStream( );
        outputStream.write( stringBuffer.toString( ).getBytes( ) );
        client.shutdownOutput();

        LoginThread loginThread = new LoginThread( );
        loginThread.start( );
        loginThread.join( );

        getScene( stage );

    }

    class LoginThread extends Thread {

        @Override
        public void run( ) {
            //获取服务器返回的信息
            try {
                InputStream inputStream = client.getInputStream( );
                int len = -1;
                byte[] bytes = new byte[ 6 ];
                StringBuffer str = new StringBuffer( );
                while ( ( len = inputStream.read( bytes ) ) != -1 ) {
                    str.append( new String( bytes, 0, len ) );
                }
                LOGGER.info( str.toString() );

                loginSuccess = true;

            } catch ( Exception e ) {
                e.printStackTrace( );
            }
        }
    }


    public void getScene( Stage stage ) throws Exception {
        if ( !loginSuccess ) {
            LoginController login = ( LoginController ) replaceSceneContent( stage, "login.fxml", 300, 175 );
            login.setMain( stage, this );

        } else {
            MainController main = ( MainController ) replaceSceneContent( stage, "main.fxml", 700, 500 );
            main.setMain( client, stage, this );
        }
    }


    public static void main( String[] args ) {
        launch( args );
    }

    private Initializable replaceSceneContent( Stage stage, String fxml, int length, int width ) throws Exception {
        FXMLLoader loader = new FXMLLoader( );
        InputStream in = Main.class.getResourceAsStream( fxml );
        loader.setBuilderFactory( new JavaFXBuilderFactory( ) );
        loader.setLocation( Main.class.getResource( fxml ) );
        Parent page;
        try {
            page = ( Parent ) loader.load( in );
        } finally {
            in.close( );
        }
        Scene scene = new Scene( page, length, width );
        stage.setScene( scene );
        stage.sizeToScene( );
        return ( Initializable ) loader.getController( );
    }


}
