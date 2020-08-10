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
		
		if(PassTextField.getText().equals(ConfirmTextfield.getText()))
		{
		try
		
{
		
		mainApp.connect("root","");
		System.out.println("\n\nCreating statement...\n\n");
		mainApp.stmt = mainApp.conn.createStatement();
		String sql;
			
		
		
   sql = "UPDATE `newuser` SET password='" + PassTextField.getText() + "' WHERE username = '" + userTextfield.getText() + "' AND userEmail ='" + RemailTextField.getText() + "';";
   System.out.println("Query:"+sql); 		
   mainApp.stmt.executeUpdate(sql);
		clear();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		}
		
		else
		{
			ConfirmTextfield.setText("");
			PassTextField.setText("");
			
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