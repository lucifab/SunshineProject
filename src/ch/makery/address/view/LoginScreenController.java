package ch.makery.address.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ch.makery.address.MainApp;
import ch.makery.address.model.User;

public class LoginScreenController {
	private MainApp mainApp;
	@FXML
	private String username;
	@FXML
	private String password;
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;
	User testUser = new User("fab","123");
	
	Button logButton;
	
	 public LoginScreenController() {
	 }
	 public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;  
	 }
	 
	 public Button getLogButton() {
			return logButton;
		}

		public void setLogButton(Button logButton) {
			this.logButton = logButton;
		}
	 
	private void getData() {
		this.username = userField.getText();
		this.password = passField.getText();
	}
		
	 public void clickLoginButton(ActionEvent event) {
		 event.consume();
		 getData();
		 login();
	     System.out.println("Hello, World!");
	     System.out.println(userField.getText());
	 }
	 public void login() {
		 System.out.println("Logging in...");
		 validate();
	 }
	 
	 public void validate() {
		 String aux1 = testUser.getUsername();
		 String aux2 = testUser.getPassword();
 
		 if((aux1.equals(this.username))&&(aux2.equals(this.password))) {
			 System.out.println("CORRECT!");
		 }
		 else {
			 System.out.println("WRONG!");
		 }
		 
	 }
}
