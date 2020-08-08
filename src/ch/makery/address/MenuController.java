package ch.makery.address;
//import javax.swing.table.TableColumn;

import ch.makery.address.model.Date;
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
    protected TableColumn<Reservation, Date> fromTable;
    @FXML
    protected TableColumn<Reservation, Date> toTable;
    
    //INITIALIZATION

    protected void init(MainApp main) {
    	//This method executes all the things necessary for initialization of scene.
    	
    	this.mainApp = main; //Connects controller to mainApp
    	
    	if (mainApp.flag==true)langChange(mainApp.flag); //Changes language if needed
    	
    	roomTable.setItems(mainApp.reservationData); //Links table to stuff in reservationData
    	populate(); //Populates table
    	
    	roomTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selection=newValue);
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
		System.out.println("Removing...");
		mainApp.reservationData.remove(selection);
		System.out.println("Selection removed.\n");
		populate();
	}
	
	
}
