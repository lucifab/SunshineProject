package ch.makery.address;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.makery.address.model.User;

public class LoginScreenController extends Controller{
	private String username;
	private String password;
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;
	@FXML
	private Label infoForUser;
	
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
		 mainApp.connect("root", "");
		 boolean flag = validate();
		 if (flag==true) {
		     mainApp.showMenu();
		 }		 
	 }
	 
	 private boolean validate() {
		 //Validates user
		 
		 String aux1 = null;
		 String aux2 = null;
		 
		//Interacting with database
		//Getting username and password according to data input by user
		try {	
		 mainApp.stmt = mainApp.conn.createStatement();
			String sql;
	    	sql = "SELECT userName,password FROM `newuser` WHERE userName=\""+this.username+"\"";
	    	ResultSet rs = mainApp.stmt.executeQuery(sql);
	    	//Extracting data from database
	    	while(rs.next()){
	    		aux1 = rs.getString("userName");
	    		aux2 = rs.getString("password");
	    	}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if((this.username==null)||(this.password)==null) {
			infoForUser.setText("Please fill the required fields.");
			return false;
		}
		else if(aux1==null){
			infoForUser.setText("User does not exist.");
			return false;
		}
		else if((aux1.equals(this.username))&&(aux2.equals(this.password))) {
			infoForUser.setText("Authenticating...");
			 mainApp.setCurrentUser(new User(this.username,this.password));
			 return true;
		 }
		 else {
			 infoForUser.setText("Username or password wrong.");
			 return false;
		 }
		 
	 }
	 
}
