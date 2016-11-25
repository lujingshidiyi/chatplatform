package sample.fxml;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class FxmlSelectionController implements Page {
	
	@FXML TextField pathField = null;
	@FXML ListView<File> fileList = null;
	private File lastDir;
	private ObservableList<File> fileNameList;

	
	@FXML protected void handleExplorer(ActionEvent e) {
		DirectoryChooser fc = new DirectoryChooser();
		
		if (lastDir != null) {
			fc.setInitialDirectory(lastDir);	
		}
		
		File file = fc.showDialog(main.getStage());
		if (file != null) {
			pathField.setText(file.getAbsolutePath());
			lastDir = file.getParentFile();
			fileNameList = FXCollections.observableArrayList(
				file.listFiles(new FileFilter(){
						@Override
						public boolean accept(File file) {
							return file.getName().toLowerCase().endsWith(".fxml");
						}				
					}));
			fileList.setItems(fileNameList);
		}
	}
	
	@FXML protected void handleShowFxml(ActionEvent e) {
		File f = fileList.getSelectionModel().getSelectedItem();
		if (f.getName().endsWith(".fxml"));
		
		Pane pane;
		try {
			pane = main.loadFXML(new FileInputStream(f));
			main.popupWindow(pane, new Stage());
		} catch (FileNotFoundException e1) {		
			e1.printStackTrace();
		}
	}	
	
	private FXMLMain main = null;
	@Override
	public void setMain(FXMLMain main) {
		this.main = main;
	}
}
