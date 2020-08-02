package ch.makery.address;
import javafx.event.ActionEvent;

public class MenuController extends Controller {
	public void onLogOff(ActionEvent event) {
		event.consume();
		mainApp.showLoginScreen();
	}
	public void onBooking(ActionEvent event) {
		event.consume();
		System.out.println("Running button thing");
		mainApp.showBookings();
	}

}
