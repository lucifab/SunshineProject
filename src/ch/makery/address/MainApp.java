package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.DateApp;
import ch.makery.address.model.Reservation;
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
import java.sql.*;

public class MainApp extends Application {
	
	//JDBC
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/sunshinehotel";
	
	// Database credentials
	static final String USER = "root";
	static final String PASS = "";
	
	Connection conn = null;
	Statement stmt = null;
	
	boolean dc = false;
	
	//JAVAFX 
	private Stage window;
	
	//DATA FOR RUNNING PROGRAM
	protected User currentUser;
	protected boolean flag = false;//False means english, true means french
	//Lists that will be retrieved  and stored from the database
	protected ObservableList<String> locations = FXCollections.observableArrayList("Macedonia","Babylonia","Camelot");
	protected static ObservableList<Room> roomData = FXCollections.observableArrayList();
	protected static ObservableList<Reservation> reservationData = FXCollections.observableArrayList();
	//TestingValues
	static DateApp aux = new DateApp(10,10,2020);

	//METHODS FOR RETRIEVING SAID DATA
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
	//MAIN APP EXECUTION
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
            
            //Link the running controller(from loader) to the controller class, set the mainApp from that controller to this
            //Basically enables controller class to communicate w/ MainApp
            LoginScreenController controller = loader.getController();
            controller.setMainApp(this);
            
            //Create scene and set it as the previous FXML doc
            Scene scene = new Scene(loginScreen);
            window.setScene(scene);
            window.show();

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showMenu() {
        try {        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainMenu.fxml"));
            VBox mainMenu = (VBox) loader.load();
            
            MenuController controller = loader.getController();
            controller.init(this); //Initializes all the things needed in scene controller (table, connection to mainApp,etc)
            
            Scene scene = new Scene(mainMenu);
            window.setScene(scene);
            window.show();
     
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
            controller.init(this);
            
            
            Scene scene = new Scene(bookingScene);
            window.setScene(scene);
            window.show();

            
            
        } catch (IOException e) {
            e.printStackTrace();
            }
        }
    
    public void showFinalBooking(Room selection,DateApp dateFrom, DateApp dateTo) {
        try {
        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FinalBooking.fxml"));
            AnchorPane finalBookScene = (AnchorPane) loader.load();
            
            FinalBookingController controller = loader.getController();
            controller.init(this, selection,dateFrom,dateTo);
            
            
            Scene scene = new Scene(finalBookScene);
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
            
            SignOnController controller = loader.getController();
            controller.setMainApp(this);
            controller.init();
            
            Scene scene = new Scene(signOnScreen);
            window.setScene(scene);
            window.show();

            
        } catch (IOException e) {
            e.printStackTrace();
            }
        }
    
    public void connect(String username, String password) {
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("Connecting to database...");
    		this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		System.out.println("Connection successful!");
    	}catch(SQLException se){
    		//Handle errors for JDBC
    		se.printStackTrace();
    	}catch(Exception e){
    		//Handle errors for Class.forName
    		e.printStackTrace();
    	}finally{
    		//finally block used to close resources
    		try{
    			if(dc==true) {
    				System.out.println("Closing connection.");
    				stmt.close();
    				};
    		}catch(SQLException se) {
    			se.printStackTrace();
    		}
    	}
    }


	public Stage getPrimaryStage() {
		//Allows other controllers/classes to grab the running window object
		return window;
	}
	
    public static void main(String[] args) { 
    	
        //Launch app.
    	launch(args);
        
        
    }

	
}

