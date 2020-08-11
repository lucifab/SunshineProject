package ch.makery.address;


import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class SignOnController extends Controller{


	@FXML
	private TextField FnameTextField;

	@FXML
	private TextField LnameTextField;

	@FXML
	private TextField unameTextField;

	@FXML
	private TextField PassTextField;

	@FXML
	private TextField ConfirmPassTextField;

	@FXML
	private TextField EmailTextField;

	public void init() {		
		mainApp.connect("root", "");
	}

	public void onBackBtn(ActionEvent event)
	{
		event.consume();
		mainApp.showLoginScreen();
	}
	
	
	public void clickMe()
	{
		if(PassTextField.getText().equals(ConfirmPassTextField.getText()))
		{

			try
			{
			mainApp.connect("root","");


			System.out.println("\n\nCreating statement...\n\n");
			mainApp.stmt = mainApp.conn.createStatement();
			String sql;

			sql="INSERT INTO `newuser` (`username`, `firstName`, `lastName`, `userEmail`, `password`, `Address1`, `City`, `State`, `Country`, `PostalCode`, `phoneNumber`) VALUES ('" + unameTextField.getText() + "', '"+ FnameTextField.getText() + "', '" + LnameTextField.getText() + "', '" + EmailTextField.getText() + "', '" + PassTextField.getText() + "', '', '', '', '', '', ' ');";
				System.out.println("Query:"+sql); 
				mainApp.stmt.executeUpdate(sql);


			clear();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("\nSQL ERROR");
		}catch (Exception e)
			{
			e.printStackTrace();
			System.out.print("Error Descripition" + e.toString());
			}

		}else
		{
			PassTextField.setText("");
			ConfirmPassTextField.setText("");

		}	

	}

	public void clear()
	{
		FnameTextField.setText("");
		LnameTextField.setText("");
		unameTextField.setText("");
		PassTextField.setText("");
		ConfirmPassTextField.setText("");
		EmailTextField.setText("");

	}
	


}

