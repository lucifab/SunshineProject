package ch.makery.address;

import javafx.scene.control.*;
import javafx.fxml.FXML;

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
		
		@FXML
		private Button CAccountButton;
	
	
public void clickMe()
{
	System.out.print("testing" + FnameTextField.getText());
	setvalues();
}
	
public void setvalues()
{
	FnameTextField.setText("");
	LnameTextField.setText("");
	unameTextField.setText("");
	PassTextField.setText("");
	ConfirmPassTextField.setText("");
	EmailTextField.setText("");

}
}

