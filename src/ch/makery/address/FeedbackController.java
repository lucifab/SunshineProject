package ch.makery.address;


import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;

public class FeedbackController extends Controller{

String issue;


@FXML
private RadioButton LoginTButton;

@FXML
private RadioButton foundBugButton;

@FXML
private RadioButton suggestionB;

public void clickLoginTButton()
{
	issue = "Login Trouble";
}

public void clickfoundBugButton()
{
	issue = "Found Bug";
}

public void clickSuggestionButton()
{
	issue = "Suggestions";
}
 
@FXML
private TextField commentBox;


public void clickSubmitButton()
{
	
	System.out.print("Submitting the feedback ...");
	
	try
	{
	System.out.println("\n\nCreating statement...\n\n");
	mainApp.stmt = mainApp.conn.createStatement();
	String sql;
	sql = "Select * from newuser WHERE username='krishan'";
	System.out.println("Query:"+sql); 
	
	
	ResultSet rs = mainApp.stmt.executeQuery(sql);
	//Extracting data from database
	while(rs.next()){
		
		//String data = rs.getString("");
		System.out.println(rs.getString("password"));
	}
	
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	System.out.print("\nSQL ERROR");
}catch (Exception e)
	{
	System.out.print("Error Descripition" + e.toString());
	}
	
	
	
	
	
	
	commentBox.setText("");
	
	
}


}