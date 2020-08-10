package ch.makery.address;

import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;

public class ForgetPasswordController extends Controller {

	@FXML
	private TextField userTextfield;
	
	
	@FXML
	private TextField RemailTextField;
	
	@FXML
	private TextField PassTextField;
	
	@FXML
	private TextField ConfirmTextfield;
	

	
	public void clickSubmitButton()
	{
	
		try
		{
		System.out.println("\n\nCreating statement...\n\n");
		mainApp.stmt = mainApp.conn.createStatement();
		String sql;
		sql = "Select * from newuser WHERE username='krishan'";
		System.out.println("Query:"+sql); 
		
		ResultSet rs = mainApp.stmt.executeQuery(sql);
		while(rs.next()){
			
			
			System.out.println(rs.getString("password"));
		}
		
		
		
		
		
//		sql = "UPDATE `newuser` SET password='" + PassTextField.getText() + "' WHERE username = '" + userTextfield.getText() + "' AND userEmail ='" + RemailTextField.getText() + "';";
//		System.out.println("Query:"+sql); 
//		
//		mainApp.stmt.executeUpdate(sql);
		clear();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
	public void clear()
	{
        userTextfield.setText("");
        userTextfield.setText("");
        ConfirmTextfield.setText("");
		PassTextField.setText("");
	}

}