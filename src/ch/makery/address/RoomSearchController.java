package ch.makery.address;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.makery.address.model.Date;
import ch.makery.address.model.Reservation;
import ch.makery.address.model.Room;
import ch.makery.address.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;

public class RoomSearchController extends  Controller{
	
	//Data retrieved from MainApp
	protected User currentUser;
	
	//Auxiliary Data
	ObservableList<String> aux = FXCollections.observableArrayList("1","2","3","4");
	
	//FXML Labels
	
	//Data
	@FXML
	Label userID;
	@FXML
	ComboBox<String> location;
	@FXML
	ComboBox<String> bedroomNo;
	@FXML
    protected TableView<Room> roomTable;
	@FXML
	protected TableColumn<Room, String> packageTable;
	@FXML
	protected TableColumn<Room, Integer> washTable;
	@FXML
	protected TableColumn<Room, Double> priceTable;
	
	//Labels, buttons & other entities that need name change
	@FXML
	Label welcome;
	@FXML
	Label specs;
	@FXML
	Label locationTable;
	@FXML
	Label bedTable;
	@FXML
	Label fromTable;
	@FXML
	Label toTable;
	@FXML
	Button back;
	@FXML
	Button next;
	@FXML
	Button search;
	
	//METHODS
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void init(MainApp main) {
		
		//Connects mainApp to this controller
		this.mainApp=main;
		
		//Changes language if needed
		if (mainApp.flag==true)langChange();
		
		//Sets flavor text to welcome user
		this.currentUser = mainApp.currentUser;
		userID.setText(this.currentUser.username);
		
		//Populates ComboBox for locations
		location.setItems(mainApp.locations);
		location.getSelectionModel().select(1);
		
		//Populates ComboBox for number of bedrooms
		bedroomNo.setItems(aux);
		bedroomNo.getSelectionModel().select(1);
		
		//Populates table before search
		fetchRooms();
		roomTable.setItems(mainApp.roomData);
		populate();
	}
	
	public void populate() {
        packageTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyType());
        washTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyBathno().asObject());
        priceTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyPrice().asObject());
	}
	
	public void fetchRooms() {
		System.out.println("\n\nCreating statement...\n\n");
    	try {
    		//Clearing room Data table... (to update it)
    		if (!mainApp.roomData.isEmpty()) {
    			System.out.println("Clearing old table...");
    			mainApp.roomData.clear();
    		}
    		
    		//Interacting with database
			mainApp.stmt = mainApp.conn.createStatement();
			String sql;
	    	sql = "SELECT * FROM `availablerooms`";
	    	System.out.println("Query:"+sql);
	    	ResultSet rs = mainApp.stmt.executeQuery(sql);
	    	//Extracting data from database
	    	while(rs.next()){
	    		//Retrieve by column name
	    		String location = rs.getString("roomLocation");
	    		String rType = rs.getString("roomType");
	    		double price = rs.getDouble("Price");
	    		int roomNo = rs.getInt("roomNo");
	    		int noBed = rs.getInt("noOfBedrooms");
	    		int noWash = rs.getInt("noOfWashrooms");

	    		//Add values to reservation table
	    		mainApp.roomData.add(new Room(location,rType,noWash,noBed,roomNo,price));
	    		
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void langChange() {
		//Changes text according to language
		welcome.setText("Bienvenue,");
		specs.setText("Choisissez les spécifications");
		locationTable.setText("Localisation");
		bedTable.setText("Chambre");
		fromTable.setText("De");
		toTable.setText("À");
		back.setText("Arrière");
		next.setText("Prochain");
		search.setText("Chercher");
	}
	
	
	//SCENE METHODS (For  buttons, etc)

	public void onBackButton(ActionEvent event) {
		event.consume();
	    System.out.println("Hello, World!");
	    mainApp.showMenu();
	}
	public void onNextButton(ActionEvent event) {
		event.consume();
		System.out.println(currentUser);
	}
	public void onSearchButton(ActionEvent event) {
		event.consume();
		System.out.println("Searching!\nPlease Wait...");
		
		//PRIYA GRAB SHIT FROM DATABASE HERE
		//Use connection & statements to retrieve the correct result
		//ObservableList<Rooms> ObservableList_From_Database = FXCollections.observableArrayList(database_data);
		
		
		//POPULATE TABLE WITH NEW SEARCH RESULTS....
		//roomTable.setItems(ObservableList_From_Database)
		//populate();
	}
}
