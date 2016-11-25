package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * 客户端
 */
public class Main extends Application {

    private URL location;
    private Stage stage;

    public Stage getStage( ) {
        return stage;
    }

    //    private Socket client;
//    TextArea showArea = new TextArea( );
//
//    public Main( ) throws IOException {
////        client = new Socket( "119.28.62.35", 19090 );
//        client = new Socket( "127.0.0.1", 9090 );
//        new ReceiveThread( client ).start( );
//    }
//
//    @Override
//    public void start( Stage primaryStage ) throws Exception {
////        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        BorderPane borderPane = new BorderPane( );
//        //设置边缘距离,东南西北都是20
//        borderPane.setPadding( new Insets( 20, 20, 20, 20 ) );
//
//        //展示对话框,高度300
//        showArea.setPrefHeight( 300 );
//        showArea.setEditable( false );
//        borderPane.setTop( showArea );
//
//        //输入对话框,高度120
//        TextField inputField = new TextField( );
//        inputField.setPrefHeight( 120 );
//        borderPane.setCenter( inputField );
//
//        //底层:按钮+提示信息
//        HBox hBox = new HBox( );
//        borderPane.setBottom( hBox );
//
//        //发送按钮
//        Button enter = new Button( "ENTER" );
//        hBox.getChildren( ).add( enter );
//        enter.setOnAction( new EventHandler<ActionEvent>( ) {
//            @Override
//            public void handle( ActionEvent event ) {
//                try {
//
//                    send( hBox, inputField, showArea );
//
//                } catch ( IOException e ) {
//                    e.printStackTrace( );
//                }
//
//            }
//        } );
//
//        //回车键松开后响应
//        inputField.setOnKeyPressed( new EventHandler<KeyEvent>( ) {
//            @Override
//            public void handle( KeyEvent event ) {
//                if ( event.getCode( ) == KeyCode.ENTER ) {
//                    try {
//                        send( hBox, inputField, showArea );
//                    } catch ( IOException e ) {
//                        e.printStackTrace( );
//                    }
//                }
//            }
//        } );
//
//        primaryStage.setTitle( "ContactClient" );
//        primaryStage.setScene( new Scene( borderPane, 700, 500 ) );
//        primaryStage.show( );
//    }
//
//    /**
//     * 向服务器发送信息
//     */
//    public void send( HBox hBox, TextField inputField, TextArea showArea ) throws IOException {
//        String str = inputField.getText( );
//        if ( str.trim( ).equals( "" ) || str.trim( ) == null ) {
//            if ( hBox.getChildren( ).size( ) != 2 ) {
//                hBox.getChildren( ).add( new Label( "发送失败,发送消息为空!" ) );
//                return;
//            }
//            return;
//        }
//        if ( !str.trim( ).equals( "" ) && str.trim( ) != null ) {
//            if ( hBox.getChildren( ).size( ) == 2 ) {
//                hBox.getChildren( ).remove( 1 );
//            }
//        }
//        showArea.appendText( CommonUtils.formatNotes( client, str ) );
//        OutputStream out = client.getOutputStream( );
//        out.write( str.getBytes( ) );
//        inputField.clear( );
//    }
//
//
//    public static void main( String[] args ) throws IOException {
//        launch( args );
//    }
//
//
//    /**
//     * 此线程用于随时接受服务器的返回信息
//     */
//    class ReceiveThread extends Thread {
//        private Socket client;
//
//        public ReceiveThread( Socket client ) {
//            this.client = client;
//        }
//
//        @Override
//        public void run( ) {
//            try {
//                InputStream inputStream = client.getInputStream( );
//                while ( true ) {
//                    byte[] bs = new byte[ 1024 ];
//                    int len = -1;
//                    while ( ( len = inputStream.read( bs ) ) != -1 ) {
//                        String str = new String( bs, 0, len );
//                        System.out.println( str );
//                        showArea.appendText( str );
//                    }
//                }
//
//            } catch ( IOException e ) {
//                e.printStackTrace( );
//            }
//        }
//    }
    @Override
    public void start( Stage stage ) throws Exception {
        stage.setTitle( "CHAT" );
        //通过fxml的UI布局文件加载实际的界面内容
        Parent root = FXMLLoader.load(getClass().getResource( "sample.fxml" ));
        Scene scene = new Scene(root,300,175);

        stage.setScene(scene);
        stage.show();

    }



    public static void main( String[] args ) {
        launch( args );
    }
}
