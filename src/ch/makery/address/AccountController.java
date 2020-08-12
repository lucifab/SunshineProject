package ch.makery.address;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private Label userField;
	

	@FXML
	private Label FNameField;
	

	@FXML
	private Label LNameField;
	

	@FXML
	private Label titleAccount;
	

	@FXML
	private Button AddButton;
	

	@FXML
	private Button BackButton;
	
	
	//Initialize
	
			public void init(MainApp main) {
				
				//Connects to main app
				this.setMainApp(main);
				
				//language change method
				if(mainApp.flag==true) {
					titleAccount.setText("Détails du compte");
					userField.setText("Nom d'utilisateur");
					FNameField.setText("Prénom");
					LNameField.setText("Nom de famille");
					AddButton.setText("Ajouter");
					BackButton.setText("Arrière");
				}
				
			}
	
	
	
	

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
		LNameText.setText("");
		
	}
	 
}