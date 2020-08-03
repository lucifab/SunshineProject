package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Date;
import ch.makery.address.model.Room;
import ch.makery.address.model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage window;
	protected User currentUser;
	
	private ObservableList<String> locations = FXCollections.observableArrayList("Macedonia","Babylonia","Camelot");
	private ObservableList<Room> roomData = FXCollections.observableArrayList();

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

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
    public void showRoomSearch() {
        try {
        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RoomSearch.fxml"));
            AnchorPane bookingScene = (AnchorPane) loader.load();
            
            
            //If you need to make changes to the layout according to 
            //specific data you retrieved from the previous scene,
            //change the stuff with the controller first and then
            //you create the scene object!!
            RoomSearchController controller = loader.getController();
            controller.setMainApp(this);
            controller.initData(this.currentUser, locations);
            
            Scene scene = new Scene(bookingScene);
            window.setScene(scene);
            window.show();

            
            
        } catch (IOException e) {
            e.printStackTrace();
            }
        }
    public void showSignOn() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SignOn.fxml"));
            AnchorPane signOnScreen = (AnchorPane) loader.load();
            
            Scene scene = new Scene(signOnScreen);
            window.setScene(scene);
            window.show();

            SignOnController controller = loader.getController();
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

