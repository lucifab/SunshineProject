package ch.makery.address;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import javafx.scene.control.TextField;
import java.sql.SQLException;
import javafx.fxml.FXML;

public class FeedbackController extends Controller{

	String issue;

	//Buttons
	@FXML
	private RadioButton LoginTButton;

	@FXML
	private RadioButton foundBugButton;

	@FXML
	private RadioButton suggestionB;
	
	@FXML
	private Button SubmitButton;
	
	@FXML
	private Button backButton;
	
	//Labels
	@FXML
	private Label title;
	
	@FXML
	private Label description;
	
	//Initialize
	
	public void init(MainApp main) {
		
		//Connects to main app
		this.setMainApp(main);
		
		//language change method
		if(mainApp.flag==true) {
			title.setText("Faites-nous part de vos commentaires sur l'application");
			description.setText("Veuillez sélectionner le type de commentaire");
			LoginTButton.setText("Problème de connexion");
			foundBugButton.setText("Bug trouvé");
			SubmitButton.setText("Envoyer");
			backButton.setText("Arrière");
		}
		
	}
	
	
	//Button methods
	
	public void onBack()
	{

		mainApp.showMenu();
	}


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
		String rev= issue + ":  " + commentBox.getText();

		System.out.print("Submitting the feedback ...");

		try
		{
			System.out.println("\n\nCreating statement...\n\n");
			mainApp.stmt = mainApp.conn.createStatement();
			String sql;
			sql = "INSERT INTO `feedback` (`username`, `rating`, `review`) "
					+ "    VALUES ('" + mainApp.getCurrentUser().getUsername() + "', NULL, '" + rev + "');";

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