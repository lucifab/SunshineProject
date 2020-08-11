package ch.makery.address;

import javafx.scene.control.TextField;


import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;


public class AccountController extends Controller {


	@FXML
    private TextField UserText;

	@FXML
	private TextField FNameText;

	@FXML
	private TextField LNameText;
	
	@FXML
	private TextField ReservText;
	
	@FXML
	private TextField PaymentText;

	@FXML
	private TextField CardnameText;

	@FXML
	private TextField CardNumText;

	@FXML
	private TextField EnterCVCField;


	 public void onBackBtn()
	 {
		
			mainApp.showMenu();
	 }
	
	
	  public void clickAddButton(){
		  
		  
			try
			{
			System.out.println("\n\nCreating statement...\n\n");
			mainApp.stmt = mainApp.conn.createStatement();
			String sql;
			
			sql="INSERT INTO `newuser` (`username`, `firstName`, `lastName`, `userEmail`, `password`, `Address1`, `City`, `State`, `Country`, `PostalCode`, `phoneNumber`) VALUES ('" + UserText.getText() + "', '"+ FNameText.getText() + "', '" + LNameText.getText() + "', '  ', '  ', '', '', '', '', '', ' ');";
			System.out.println("Query:"+sql); 
			
			
			mainApp.stmt.executeUpdate(sql);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("\nSQL ERROR");
		}catch (Exception e)
			{
			System.out.print("Error Descripition" + e.toString());
			}

			clear();
	  }



	private void clear() {
		
		UserText.setText("");
		FNameText.setText("");
		CardnameText.setText("");
		CardNumText.setText("");
		EnterCVCField.setText("");
		LNameText.setText("");
		ReservText.setText("");
		PaymentText.setText("");
	}
	 
}