package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;


public class sampleController {
    @FXML
    private Text actiontarget;

    @FXML
    protected void handleSubmitButtonAction( ActionEvent event){
       System.out.print( "点击了..." );
    }

}
