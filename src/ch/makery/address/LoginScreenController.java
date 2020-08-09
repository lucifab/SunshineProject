package ch.makery.address;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.makery.address.model.Date;
import ch.makery.address.model.Reservation;
import ch.makery.address.model.User;

public class LoginScreenController extends Controller{
	private String username;
	private String password;
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;
	
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
		 mainApp.connect("root", "");
		 boolean flag = validate();
		 if (flag==true) {
			 System.out.print("Hello,");
		     System.out.println(userField.getText());
		     mainApp.showMenu();
		 }
		 else {
			 System.out.println("Username or password wrong.");
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
	    	System.out.println("LOGIN Query:"+sql);
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
 
		System.out.println("Input:"+this.username+"\nPass:"+this.password);
		System.out.println("DB:"+aux1+"\nPass:"+aux2);
		
		if((this.username==null)||(this.password)==null) {
			System.out.println("Please fill the required fields.");
			return false;
		}
		else if(aux1==null){
			
			return false;
		}
		else if((aux1.equals(this.username))&&(aux2.equals(this.password))) {
			 System.out.println("Authenticating...");
			 mainApp.setCurrentUser(new User(this.username,this.password));
			 System.out.println("Authenticated");
			 return true;
		 }
		 else {
			 return false;
		 }
		 
	 }
	 
}
