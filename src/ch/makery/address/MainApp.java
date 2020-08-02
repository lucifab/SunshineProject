package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage window;

	@Override
    public void start(Stage primaryStage) {
        this.window = primaryStage;
        this.window.setTitle("Sunshine Booking");
       
        showLoginScreen();
    }
	
    public void showLoginScreen() {
        try {
        	//Get, load FXML document and create object
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LoginScreen.fxml"));
            VBox loginScreen = (VBox) loader.load();
            
            //Create scene and set it as the previous FXML doc
            Scene scene = new Scene(loginScreen);
            window.setScene(scene);
            window.show();
            
            //Link the running controller(from loader) to the controller class, set the mainApp from that controller to this
            //Basically enables controller class to communicate w/ MainApp
            LoginScreenController controller = loader.getController();
            controller.setMainApp(this);

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainMenu.fxml"));
            VBox mainMenu = (VBox) loader.load();
            
            Scene scene = new Scene(mainMenu);
            window.setScene(scene);
            window.show();
            
            MenuController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showBookings() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Booking.fxml"));
            AnchorPane mainMenu = (AnchorPane) loader.load();
            
            Scene scene = new Scene(mainMenu);
            window.setScene(scene);
            window.show();

            BookingController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
            }
        }
    
    
    
	public Stage getPrimaryStage() {
		//Allows other controllers/classes to grab the running window object
		return window;
	}

    public static void main(String[] args) {
        launch(args);
    }
}

