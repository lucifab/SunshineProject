package ch.makery.address;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class MenuController extends Controller {
	
	@FXML
	Label title;
	@FXML
	Label description;
	@FXML
	Button booking;
	@FXML
	Button cancel;
	@FXML
	Button account;
	@FXML
	Button feedback;
	@FXML
	Button logOff;
	@FXML
	Button setting;
	
	public void langChange(boolean flag) {
		System.out.println("Executing language change...");
		if (flag) {
			System.out.println("Bonjour!!\nChanging language...");
			title.setText("R�servation H�tel Soleil");
			description.setText("Vos r�servations actuelles");
			booking.setText("R�servations");
			account.setText("Compte");
			logOff.setText("D�connecter");
			setting.setText("English");
			}
		else {
			System.out.println("Hello!!\nChanger la langue...");
			title.setText("SUNSHINE BOOKING SYSTEM");
			description.setText("Your current bookings");
			booking.setText("Bookings");
			account.setText("Account");
			logOff.setText("Log Off");
			setting.setText("Fran�ais");	
		}
	}
	
	public void onLanguage(ActionEvent event) {		
		event.consume();
		System.out.println("Changing language");
		mainApp.flag=!mainApp.flag;
		langChange(mainApp.flag);
	}
	

	public void onLogOff(ActionEvent event) {
		event.consume();
		mainApp.showLoginScreen();
	}
	public void onRoomSearch(ActionEvent event) {
		event.consume();
		System.out.println("Running button thing");
		mainApp.showRoomSearch();
	}
	
	
}
