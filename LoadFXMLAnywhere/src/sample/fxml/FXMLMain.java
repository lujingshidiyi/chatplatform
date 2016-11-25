package sample.fxml;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLMain extends Application {
	
	private URL location;

	private Stage stage;
	public Stage getStage(){
		return stage;
	}

	public Pane loadFXML(InputStream in) {
		
		FXMLLoader loader = new FXMLLoader();
		
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(location);
		
	    try {	    	
			Pane pane = (Pane)loader.load(in);
			
			Page page = (Page)loader.getController();
			page.setMain(this);
			
			return pane;
			
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	return null;
	    }
	}

	public void popupWindow(Pane root, Stage stage) {
		showStage(root, stage, this.stage);
	}
	
	private void showStage(Pane root, Stage stage, Stage parent) {
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.sizeToScene();
		stage.setResizable(false);

		if (parent != null) {
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(parent);			
		}

		stage.setScene(scene);
		stage.sizeToScene();
		
		stage.show();
	}

	@Override
	public void start(Stage stage) throws Exception {
		String fxml = "FxmlSelection.fxml";
		
		location = FXMLMain.class.getResource(fxml);		
		Pane pane = loadFXML(FXMLMain.class.getResourceAsStream(fxml));
		
		if (pane == null)
			return;
		
		showStage(pane, stage, null);
		
		this.stage = stage;
	}	
	
	public static void main(String[] args) {
		Application.launch(FXMLMain.class, (java.lang.String[])null);
	}
}
