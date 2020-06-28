package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage window;

	@Override
    public void start(Stage primaryStage) {
        this.window = primaryStage;
        this.window.setTitle("Sunshine Booking");
       
        showLoginScreen();
        System.out.println("HEWWO");
    }
	
    public void showLoginScreen() {
        try {
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
    
	public Stage getPrimaryStage() {
		return window;
	}

    public static void main(String[] args) {
        launch(args);
    }
}

