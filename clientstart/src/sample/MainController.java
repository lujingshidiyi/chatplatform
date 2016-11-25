import com.contactserver.contactutils.CommonUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by admin on 16/9/22.
 */
public class MainController implements Initializable {
    @FXML
    private TextArea showArea;
    @FXML
    private TextField inputArea;
    @FXML
    private HBox hBox;
    @FXML
    private Button enterButton;
    @FXML
    private Text errorText;

    private Main main;
    private Stage stage;
    private Socket client;

    public void setMain( Socket client, Stage stage, Main main ) {
        this.client = client;
        this.stage = stage;
        this.main = main;
        new ReceiveThread( client ).start( );
    }


    //发送按钮
    @FXML
    public void send( ActionEvent event ) throws IOException {
        send( hBox, inputArea, showArea );

    }


    //输入框回车键松开后响应
    @FXML
    public void enterSend( KeyEvent event ) throws IOException {
        if ( event.getCode( ) == KeyCode.ENTER ) {
                send( hBox, inputArea, showArea );
        }
    }


    /**
     * 向服务器发送信息
     */
    public void send( HBox hBox, TextField inputField, TextArea showArea ) throws IOException {
        String str = inputField.getText( );
        if ( str.trim( ).equals( "" ) || str.trim( ) == null ) {
            errorText.setText(  "发送失败,发送消息为空!" );
            return;
        }
        if ( !str.trim( ).equals( "" ) && str.trim( ) != null ) {
            errorText.setText( "" );
        }
        showArea.appendText( CommonUtils.formatNotes( client, str ) );
        OutputStream out = client.getOutputStream( );
        out.write( str.getBytes( ) );
        inputField.clear( );
    }

    /**
     * 此线程用于随时接受服务器的返回信息
     */
    class ReceiveThread extends Thread {
        private Socket client;

        public ReceiveThread( Socket client ) {
            this.client = client;
        }

        @Override
        public void run( ) {
            try {
                InputStream inputStream = client.getInputStream( );
                while ( true ) {
                    byte[] bs = new byte[ 1024 ];
                    int len = -1;
                    while ( ( len = inputStream.read( bs ) ) != -1 ) {
                        String str = new String( bs, 0, len );
                        System.out.println( str );
                        showArea.appendText( str );
                    }
                }

            } catch ( IOException e ) {
                e.printStackTrace( );
            }
        }
    }


    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }
}
