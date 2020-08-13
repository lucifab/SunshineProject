package ch.makery.address;

import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
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
	
     public void onBackBtn(ActionEvent event)
 	{
 		event.consume();
 		mainApp.showLoginScreen();
     }
     
    //Method to determine the empty fields 
     public boolean checkEmptyFields()
		{
		  int val=0;
		  
			if(userTextfield.getText().isEmpty())
			{
				userTextfield.setText("VALUE REQUIRED");
				++val;
			}
			if(RemailTextField.getText().isEmpty())
			{
				RemailTextField.setText("VALUE REQUIRED");
				++val;
			}
			if(PassTextField.getText().isEmpty())
			{
				PassTextField.setText("VALUE REQUIRED");
				++val;
			}
			
			if(ConfirmTextfield.getText().isEmpty())
			{
				ConfirmTextfield.setText("VALUE REQUIRED");
				++val;
			}
			if(val>0)
			{
				return true;
			}
			else
			{
			return false;
			}
		}
	 
	
	public void clickSubmitButton()
	{
		if(checkEmptyFields()) {
			System.out.print("Please enter all fields before submitting.");
		}
		
		else
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
	}
	
	public void clear()
	{
        userTextfield.setText("");
        RemailTextField.setText("");
        ConfirmTextfield.setText("");
		PassTextField.setText("");
		
	}

}