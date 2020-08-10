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


	public void clickMe(ActionEvent event){
		event.consume();

		if(PassTextField.getText().equals(ConfirmPassTextField.getText()))
		{
			try
			{

				System.out.println("Logging in...");
				mainApp.connect("root", "");	

				System.out.println("\n\nCreating statement...\n\n");
				mainApp.stmt = mainApp.conn.createStatement();
				String sql;

				sql = "INSERT INTO `newuser` (`username`, `firstName`, `lastName`, `userEmail`, `password`, `Address1`, `City`, `State`, `Country`, `PostalCode`, `phoneNumber`)"
						+ " VALUES (`" + unameTextField.getText() + "`, `" + FnameTextField.getText() + "`, `" + LnameTextField.getText() + "`, `" + LnameTextField.getText() + "`, `" + PassTextField.getText() + "`, 'null', 'null', 'null', 'null', 'null', 'null');";
				System.out.println("Query:"+sql); 

				mainApp.stmt.executeUpdate(sql);

				System.out.println("Query:"+sql); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
