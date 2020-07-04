package ch.makery.address;

import javafx.event.ActionEvent;

public class MenuController extends Controller {
	public void onLogOff(ActionEvent event) {
		event.consume();
		mainApp.showLoginScreen();
	}
}
