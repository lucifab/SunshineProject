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
       
        showMenu();
        System.out.println("HEWWO");
    }
    
    /**
     * Initializes the root layout.
     */
	public void testing() {
		System.out.println("yepyep");
	}
	
	
    public void showLoginScreen() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LoginScreen.fxml"));
            VBox loginScreen = (VBox) loader.load();
            
            
            Scene scene = new Scene(loginScreen);
            window.setScene(scene);
            window.show();
            
            LoginScreenController controller = loader.getController();
            controller.setMainApp(this);

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showMenu() {
        try {
        	// Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainMenu.fxml"));
            VBox mainMenu = (VBox) loader.load();
            
            
            Scene scene = new Scene(mainMenu);
            window.setScene(scene);
            window.show();
            
            //MenuController controller = loader.getController();
            //controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void showRoomSearch() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RoomSearch.fxml"));
            AnchorPane roomSearch = (AnchorPane) loader.load();
            
            
            Scene scene = new Scene(roomSearch);
            window.setScene(scene);
            window.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public Stage getPrimaryStage() {
		return window;
	}

    public static void main(String[] args) {
        launch(args);
    }
}

