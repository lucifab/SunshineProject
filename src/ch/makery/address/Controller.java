package ch.makery.address;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class Controller implements Initializable{
	protected MainApp mainApp;
	
	 public void initialize(URL url, ResourceBundle rb) {
		 System.out.print("Were inside Controller");
	    }
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;  
 }

}
