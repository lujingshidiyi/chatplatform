import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    //配置文件中的⚠️警告文本显示
    @FXML
    private Text actiontarget;
    //用户id
    @FXML
    private TextField userid;
    //密码
    @FXML
    private PasswordField password;

    //当前主桌面
    private Main main;
    private Stage stage;

    public void setMain(Stage stage,Main main){
        this.stage = stage;
        this.main = main;
    }

    @FXML
    protected void login( ActionEvent event ) throws Exception {
        main.login(stage, userid.getText( ), password.getText( ) );
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }
}
