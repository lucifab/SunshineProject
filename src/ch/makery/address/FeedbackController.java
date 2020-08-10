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
	String rev= issue + "  " + commentBox.getText();
	
	System.out.print("Submitting the feedback ...");
	
	try
	{
	System.out.println("\n\nCreating statement...\n\n");
	mainApp.stmt = mainApp.conn.createStatement();
	String sql;
	sql = "INSERT INTO `feedback` (`username`, `reservationID`, `roomNo`, `rating`, `review`) "
			+ "    VALUES ('" + mainApp.getCurrentUser().getUsername() + "', 345, 78, NULL, '" + rev + "');";
	
	System.out.println("Query:"+sql); 
	
	//mainApp.stmt.executeQuery(sql);
      mainApp.stmt.executeUpdate(sql);
	
	
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