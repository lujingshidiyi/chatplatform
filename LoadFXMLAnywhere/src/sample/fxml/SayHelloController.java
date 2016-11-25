package sample.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SayHelloController implements Page{

	@FXML TextField helloText;
	
	@FXML protected void handleSayHello(ActionEvent e) {
		helloText.setText("Hello everone!");
	}

	@Override
	public void setMain(FXMLMain stage) {
	}
}
