package ch.makery.address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

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
		
		//Populates table before search
		fetchRooms();
		roomTable.setItems(mainApp.roomData);
		populate();
		
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
    			System.out.println("Clearing old table...");
    			mainApp.locations.clear();
    		}
    		System.out.println("\n\nCreating statement...\n\n");
    		//Interacting with database
			mainApp.stmt = mainApp.conn.createStatement();
			String sql;
	    	sql = "SELECT DISTINCT roomLocation FROM `rooms`";
	    	System.out.println("Fetch Room Query:"+sql);
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
		specs.setText("Choisissez les sp�cifications");
		locationTable.setText("Localisation");
		bedTable.setText("Chambre");
		fromTable.setText("De");
		toTable.setText("�");
		back.setText("Arri�re");
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
		//System.out.println(currentUser);
		//System.out.println(selection);
		//System.out.println(from.getValue()+"\n"+to.getValue());
		
		if((selection!=null)&&(from.getValue()!=null)&&(to!=null)) {
			
			fromSend = new DateApp(from.getValue().getDayOfMonth(),from.getValue().getMonthValue(),from.getValue().getYear());
			toSend = new DateApp(to.getValue().getDayOfMonth(),to.getValue().getMonthValue(),to.getValue().getYear());
			System.out.println("from:"+fromSend);
			System.out.println("TO:"+toSend);

			if(toSend.isThisAfter(fromSend)) {
				mainApp.showFinalBooking(this.selection,fromSend,toSend);
			}
			else {
				System.out.println("Choose valid date.");
			}
			
		}
		else {
			System.out.println("Selection is null.");
		}
		
	}
	public void onSearchButton(ActionEvent event) {
		event.consume();
		Scanner in = new Scanner(System.in);
		if((from.getValue()!=null)&&(to.getValue()!=null)) {
			
			//Grab date values
			fromSend = new DateApp(from.getValue().getDayOfMonth(),from.getValue().getMonthValue(),from.getValue().getYear());
			toSend = new DateApp(to.getValue().getDayOfMonth(),to.getValue().getMonthValue(),to.getValue().getYear());
               System.out.println("\n\nEnter the preferable date for reservation\n");
               String fromSend = in.nextLine();
               System.out.println("\n\nEnter the date till you are looking for reservation\n\n");
               String toSend = in.nextLine();
               
			System.out.println("Searching!\nPlease Wait...");
			System.out.println("\n\nCreating statement...\n\n");
			try {
				//Clearing room table...
				if (!mainApp.roomData.isEmpty()) {
					System.out.println("Clearing old table...");
					mainApp.roomData.clear();
				}

				//Interacting with database
				mainApp.stmt = mainApp.conn.createStatement();
				String sql;

				//Creating the Statement according to user specifications
				//sql = "SELECT FROM reservation WHERE  checkIn > "+fromSend+" or checkOut< "+toSend+" ";
				 sql = "SELECT * FROM Reservation re WHERE NOT EXISTS (SELECT roomNo FROM Rooms r  WHERE re.roomNo = r.roomNo AND (("+fromSend+" >= checkIn AND "+toSend+"  <= checkOut) OR ("+fromSend+" <= checkIn AND "+toSend+" >= checkIn)  ))";
				System.out.println("SEARCH Query:"+sql);

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
					mainApp.roomData.add(new Room(location,rType, noWash,noBed,roomNo,price));

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
		String str = "SELECT * FROM `availablerooms`";
		str = str+" WHERE roomLocation=\""+location.getValue()+"\" AND NoOfBedrooms="+bedroomNo.getValue();
		
		return str;
	}
}
