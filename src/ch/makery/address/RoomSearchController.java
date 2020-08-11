package ch.makery.address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import ch.makery.address.model.DateApp;
import ch.makery.address.model.Reservation;
import ch.makery.address.model.Room;
import ch.makery.address.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RoomSearchController extends  Controller{
	
	//Data retrieved from MainApp
	protected User currentUser;
	
	//Auxiliary Data
	ObservableList<String> aux = FXCollections.observableArrayList("1","2","3","4");
	Room selection;
	DateApp fromSend;
	DateApp toSend;
	
	//FXML Labels
	
	//Data
	@FXML
	Label userID;
	@FXML
	ComboBox<String> location;
	@FXML
	ComboBox<String> bedroomNo;
	@FXML
	DatePicker from;
	@FXML
	DatePicker to;
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
	@FXML
	Label errorMsg;
	
	//METHODS
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void init(MainApp main) {
		
		//Connects mainApp to this controller
		this.setMainApp(main);
		
		//Changes language if needed
		if (mainApp.flag==true)langChange();
		
		//Sets flavor text to welcome user
		this.currentUser = mainApp.currentUser;
		userID.setText(this.currentUser.username);
		
		//Populates ComboBox for locations
		fetchLocations();
		location.setItems(mainApp.locations);
		location.getSelectionModel().select(1);
		
		//Populates ComboBox for number of bedrooms
		bedroomNo.setItems(aux);
		bedroomNo.getSelectionModel().select(1);
		
		//Connects roomData to the table
		roomTable.setItems(mainApp.roomData);
		
		//Again, a listener for the table
		roomTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selection=newValue);
    }
	
	
	public void populate() {
        packageTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyType());
        washTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyBathno().asObject());
        priceTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyPrice().asObject());
	}
	
	public void fetchLocations() {
    	try {
    		//Clearing room Data table... (to update it)
    		if (!mainApp.locations.isEmpty()) {
    			mainApp.locations.clear();
    		}
    		//Interacting with database
			mainApp.stmt = mainApp.conn.createStatement();
			String sql;
	    	sql = "SELECT DISTINCT roomLocation FROM `rooms`";
	    	ResultSet rs = mainApp.stmt.executeQuery(sql);
	    	//Extracting data from database
	    	while(rs.next()){
	    		//Retrieve by column name
	    		String location = rs.getString("roomLocation");
	    		//Add values to location table
	    		mainApp.locations.add(location);
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fetchRooms() {
    	try {
    		//Clearing room Data table... (to update it)
    		if (!mainApp.roomData.isEmpty()) {
    			mainApp.roomData.clear();
    		}
    		
    		//Interacting with database
			mainApp.stmt = mainApp.conn.createStatement();
			String sql;
	    	sql = "SELECT * FROM `availablerooms`";
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
	    mainApp.showMenu();
	}
	public void onNextButton(ActionEvent event) {
		event.consume();
		

		if((selection!=null)&&(from.getValue()!=null)&&(to!=null)) {
			
			fromSend = new DateApp(from.getValue().getDayOfMonth(),from.getValue().getMonthValue(),from.getValue().getYear());
			toSend = new DateApp(to.getValue().getDayOfMonth(),to.getValue().getMonthValue(),to.getValue().getYear());

			if(toSend.isThisAfter(fromSend)) {
				mainApp.showFinalBooking(this.selection,fromSend,toSend);
			}
			else {
				if(mainApp.flag=false) {
					errorMsg.setText("Choose valid date.");
				}
				else {
					errorMsg.setText("Choisissez une date valide.");
				}
			}
			
		}
		else {
			if(mainApp.flag==false) {
				errorMsg.setText("Selection is null.");
			}
			else {
				errorMsg.setText("La sélection est nulle.");
			}
		}
		
		
	}
	public void onSearchButton(ActionEvent event) {
		event.consume();
		
		if((from.getValue()!=null)&&(to.getValue()!=null)) {
			
			//Grab date values
			fromSend = new DateApp(from.getValue().getDayOfMonth(),from.getValue().getMonthValue(),from.getValue().getYear());
			toSend = new DateApp(to.getValue().getDayOfMonth(),to.getValue().getMonthValue(),to.getValue().getYear());

			try {
				//Clearing room table...
				if (!MainApp.roomData.isEmpty()) {
					MainApp.roomData.clear();
				}

				//Interacting with database
				mainApp.stmt = mainApp.conn.createStatement();
				String sql;

				//Creating the Statement according to user specifications
				sql = statementBuilder();
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

					//After all the data is fetched, populate the table!
					populate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//builds statement query according to user specifications
	public String statementBuilder() {
		String str = "SELECT * FROM `rooms` WHERE roomNo NOT IN (SELECT roomNo FROM `roomstatus` WHERE NOT ((`checkIn`>= "+toSend.toInt()+" AND `checkOut`>= "+toSend.toInt()+") OR (`checkIn`<= "+fromSend.toInt()+" AND `checkOut`<= "+fromSend.toInt()+")))";
		str = str+" AND roomLocation=\""+location.getValue()+"\" AND NoOfBedrooms="+bedroomNo.getValue();
		
		return str;
	}
}
