package ch.makery.address;
//import javax.swing.table.TableColumn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

import ch.makery.address.model.DateApp;
import ch.makery.address.model.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class MenuController extends Controller {
	
	//Auxiliary Data
	Reservation selection;
	
	//FXML values
	@FXML
	Label title;
	@FXML
	Label description;
	@FXML
	Label description1;
	@FXML
	Button booking;
	@FXML
	Button cancel;
	@FXML
	Button account;
	@FXML
	Button feedback;
	@FXML
	Button logOff;
	@FXML
	Button setting;
	
	//TABLE VIEW FXML 
	
	@FXML
    protected TableView<Reservation> roomTable;
    @FXML
    protected TableColumn<Reservation, String> id;
    @FXML
    protected TableColumn<Reservation, String> locationTable;
    @FXML
    protected TableColumn<Reservation, String> packageTable;
    @FXML
    protected TableColumn<Reservation, Integer> roomNoTable;
    @FXML
    protected TableColumn<Reservation, Integer> noBedroomTable;
    @FXML
    protected TableColumn<Reservation, Integer> noWashTable;
    @FXML
    protected TableColumn<Reservation, DateApp> fromTable;
    @FXML
    protected TableColumn<Reservation, DateApp> toTable;
    
    //INITIALIZATION

    protected void init(MainApp main) {
    	//This method executes all the things necessary for initialization of scene.
    	
    	this.setMainApp(main); //Connects controller to mainApp
    	
    	if (mainApp.flag==true)langChange(mainApp.flag); //Changes language if needed
    	
    	fetchReservation();
    	
    	roomTable.setItems(mainApp.reservationData); //Links table to stuff in reservationData
    	populate(); //Populates table
    	
    	//This is a listener to check if the user has selected something from the table
    	roomTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selection=newValue);
    }
    
    public void fetchReservation() {
    	
    	//This method fetches the proper reservations from the database and populates the reservationData table
    	//from the mainApp
    	
    	System.out.println("\n\nCreating statement...\n\n");
    	try {
    		//Clearing reservation table...
    		if (!mainApp.reservationData.isEmpty()) {
    			System.out.println("Clearing old table...");
    			mainApp.reservationData.clear();
    		}
    		
    		//Interacting with database
			mainApp.stmt = mainApp.conn.createStatement();
			String sql;
	    	sql = "SELECT * FROM allbookings WHERE cancellationID IS NULL AND `username`=\""+mainApp.currentUser.username+"\"";
	    	System.out.println("Fetch Reservation Query:"+sql);
	    	ResultSet rs = mainApp.stmt.executeQuery(sql);
	    	//Extracting data from database
	    	while(rs.next()){
	    		//Retrieve by column name
	    		
	    		String ID = ""+rs.getInt("reservationID");
	    		int cancellation = rs.getInt("cancellationID");
	    		String location = rs.getString("roomLocation");
	    		String roomType = rs.getString("roomType");
	    		double price = rs.getDouble("Price");
	    		int roomNo = rs.getInt("roomNo");
	    		int noBed = rs.getInt("noOfBedrooms");
	    		int noWash = rs.getInt("noOfWashrooms");
	    		DateApp from = new  DateApp(rs.getInt("checkIn"));
	    		DateApp to = new DateApp(rs.getInt("checkOut"));

	    		//Add values to reservation table
	    		mainApp.reservationData.add(new Reservation(ID,cancellation,location,roomType,price,noWash,noBed,roomNo,from,to));
	    		
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    protected void populate() {
    	// Initialize the person table with the two columns / update table
        id.setCellValueFactory(cellData -> cellData.getValue().getPropertyID());
        locationTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyLocation());
        packageTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyType());
        roomNoTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyNo().asObject());
        noBedroomTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyRoomno().asObject());
        noWashTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyBathno().asObject());
        fromTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyFrom());
        toTable.setCellValueFactory(cellData -> cellData.getValue().getPropertyTo());
    }
    
	public void langChange(boolean flag) {
		System.out.println("Executing language change...");
		if (flag) {
			System.out.println("Bonjour!!\nChanging language...");
			title.setText("Réservation Hôtel Soleil");
			description.setText("Vos réservations actuelles");
			description1.setText("Pour annuler une réservation, sélectionnez-la dans le tableau ci-dessous et cliquez sur Annuler");
			booking.setText("Réservations");
			cancel.setText("Annuler");
			account.setText("Compte");
			logOff.setText("Déconnecter");
			setting.setText("English");
			locationTable.setText("Endroit");
			packageTable.setText("Remise");
			roomNoTable.setText("Nombre");
			noBedroomTable.setText("Chambre");
			noWashTable.setText("Toilette");
			fromTable.setText("De");
			toTable.setText("À");
			
			}
		else {
			System.out.println("Hello!!\nChanger la langue...");
			title.setText("SUNSHINE BOOKING SYSTEM");
			description.setText("Your current bookings");
			booking.setText("Bookings");
			cancel.setText("Cancel");
			account.setText("Account");
			logOff.setText("Log Off");
			setting.setText("Français");
			locationTable.setText("Location");
			packageTable.setText("Package");
			roomNoTable.setText("Room No.");
			noBedroomTable.setText("Bedrooms");
			noWashTable.setText("Washrooms");
			fromTable.setText("From");
			toTable.setText("To");
			
		}
	}
	
	//SCENE METHODS (Buttons, etc)
	
	public void onLanguage(ActionEvent event) {		
		event.consume();
		System.out.println("Changing language");
		mainApp.flag=!mainApp.flag;
		langChange(mainApp.flag);
	}
	

	public void onLogOff(ActionEvent event) {
		event.consume();
		mainApp.showLoginScreen();
	}
	public void onRoomSearch(ActionEvent event) {
		event.consume();
		System.out.println("Running button thing");
		mainApp.showRoomSearch();
	}
	
	public void onCancel(ActionEvent event) {
		event.consume();
		if(selection!=null) {
			System.out.println("Removing...");
			try {
				
				//Auxiliary data
				int invoiceNoAux=0;
				int cancellationAux=0;
				DateApp receiver = new DateApp();
				System.out.println(receiver);
				
				
				System.out.println("\n\nCreating statement...\n\n");
				//Interacting with database
				mainApp.stmt = mainApp.conn.createStatement();
				String sql;

				//Grabbing invoice number
				sql="SELECT invoiceNo from reservpay WHERE reservationID="+selection.getID();
				System.out.println("Query:"+sql);
				
				ResultSet rs = mainApp.stmt.executeQuery(sql);
				while(rs.next()) {
					invoiceNoAux = rs.getInt("invoiceNo");
					System.out.println("InvoiceNo:"+invoiceNoAux);
				}

				//Insert into cancellation
				sql="INSERT INTO cancellation (reservationID,invoiceNo,roomNo,username,cancelationDate) "+
						"values ("+selection.getID()+","+invoiceNoAux+","+selection.getNo()+",'"+mainApp.currentUser.getUsername()+"',"+receiver.toInt()+")";
				System.out.println("Query:"+sql);
				mainApp.stmt.executeUpdate(sql);
				
				//Getting cancellation ID
				sql="SELECT cancellationID from cancellation WHERE reservationID="+selection.getID();
				System.out.println("Query:"+sql);
				rs = mainApp.stmt.executeQuery(sql);
				while(rs.next()) {
					cancellationAux = rs.getInt("cancellationID");
				}
				
				//Change from reservations
				sql = "UPDATE `reservation` SET cancellationID="+cancellationAux+" WHERE `reservationID`="+selection.getID();
				System.out.println("Query:"+sql);
				mainApp.stmt.executeUpdate(sql);

				System.out.println("Selection removed.\n");

				//Extracting data from database
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

			//Now to update the table...

			fetchReservation();
			populate();
		}
		else {
			System.out.println("Can't cancel, no selection.");
		}
	}
	
	
}
