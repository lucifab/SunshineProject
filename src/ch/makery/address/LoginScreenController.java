package ch.makery.address;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import ch.makery.address.model.User;

public class LoginScreenController extends Controller{
	private String username;
	private String password;
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;
	User testUser = new User("dhan","123");
	
	Button logButton;


	public LoginScreenController() {
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
	    }
	     
	 public void clickSignOn(ActionEvent event) {
		 event.consume();
		 mainApp.showSignOn();
	 }
	 
	 private void login() {
		 System.out.println("Logging in...");
		 boolean flag = validate();
		 if (flag==true) {
			 mainApp.connect("root", "");
			 System.out.print("Hello,");
		     System.out.println(userField.getText());
		     mainApp.showMenu();
		 }
		 else {
			 System.out.println("Username or password wrong.");
		 }
		 
	 }
	 
	 private boolean validate() {
		 String aux1 = testUser.getUsername();
		 String aux2 = testUser.getPassword();
 
		 if((aux1.equals(this.username))&&(aux2.equals(this.password))) {
			 System.out.println("Authenticating...");
			 mainApp.setCurrentUser(this.testUser);
			 System.out.println("Authenticated");
			 return true;
		 }
		 else {
			
			 return false;
		 }
		 
	 }
	 
}
