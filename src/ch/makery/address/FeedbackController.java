package ch.makery.address;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

public class FeedbackController extends Controller{

String issue;

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

@FXML
private Button SubmitButton;

public void clickSubmitButton()
{
	
	System.out.print("Submitting the feedback ...");
}
}