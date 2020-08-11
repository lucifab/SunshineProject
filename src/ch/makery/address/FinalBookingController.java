package ch.makery.address;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.makery.address.model.DateApp;
import ch.makery.address.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FinalBookingController extends Controller{
	
	//Data from Previous Scene
	Room selection;
	DateApp fromPrev;
	DateApp toPrev;
	
	//Auxiliary data
	ObservableList<String> aux = FXCollections.observableArrayList("Credit","Debit");
	int reservationMax;
	String cardNoAux, ccvAux, nameOnCardAux;
	
	
	//Labels for data from previous scene
	@FXML
	Label roomNum;
	@FXML
	Label location;
	@FXML
	Label type;
	@FXML
	Label numBed;
	@FXML
	Label numWash;
	@FXML
	Label from;
	@FXML
	Label to;
	@FXML
	Label price;
	
	//Labels for data input on right side 
	@FXML
	ComboBox<String> payType;
	@FXML
	TextField cardNum;
	@FXML
	TextField ccv;
	@FXML
	TextField nameOnCard;
	@FXML
	Label errorMsg;
	
	//Labels for language change:
	@FXML
	Label roomInfoDes;
	@FXML
	Label roomNumDes;
	@FXML
	Label locationDes;
	@FXML
	Label typeDes;
	@FXML
	Label numBedDes;
	@FXML
	Label numWashDes;
	@FXML
	Label fromDes;
	@FXML
	Label toDes;
	@FXML
	Label priceDes;
	@FXML
	Label payInfo;
	@FXML
	Label payTypeDes;
	@FXML
	Label cardNumDes;
	@FXML
	Label nameOnCardDes;
	@FXML
	Button back;
	@FXML
	Button confirm;
	
	public void init(MainApp main, Room selection,DateApp dateFrom,DateApp dateTo) {
		
		//Connects controller
		this.setMainApp(main);
		
		//Language change if needed
		if (mainApp.flag==true)langChange();
		
		//Receives data from prev scene
		this.selection=selection;
		this.fromPrev=dateFrom;
		this.toPrev=dateTo;
		
		//Fills data from left side of screen with the selection from 
		//RoomSearch scene
		fillRoomData();
		
		//Fills payment type combobox
		payType.setItems(aux);
		payType.getSelectionModel().select(1);
		
		
	}
	
	public void langChange() {
		//Changes text according to language
				roomInfoDes.setText("Vos informations");
				roomNumDes.setText("Chambre:");
				locationDes.setText("Emplacement:");
				numBedDes.setText("No. de Chambre:");
				numWashDes.setText("No. de toillette:");
				fromDes.setText("De");
				toDes.setText("À:");
				priceDes.setText("Prix");
				payInfo.setText("Insérer les informations de paiement");
				payTypeDes.setText("Type de paiement:");
				cardNumDes.setText("Numéro de carte:");
				nameOnCardDes.setText("Nom de la carte:");
				
				back.setText("Arrière");
				confirm.setText("Confirmer");

	}
	
	public void fillRoomData() {
		roomNum.setText(""+this.selection.getNo());
		location.setText(this.selection.getLocation());
		type.setText(this.selection.getType());
		numBed.setText(""+this.selection.getRoomno());
		numWash.setText(""+this.selection.getBathno());
		from.setText(fromPrev.toString()); 
		to.setText(toPrev.toString());
		price.setText(""+this.selection.getDailyPrice());

	}
	
	public void onBackButton(ActionEvent event) {
		event.consume();
		mainApp.showRoomSearch();
	}
	
	public void onConfirmButton(ActionEvent event) {
		event.consume();
		if(validate()) {
			boolean flag = true;
			//first get MaxReservation ID
			try {
	    		System.out.println("\n\nCreating statement...\n\n");
	    		//Interacting with database
				mainApp.stmt = mainApp.conn.createStatement();
				String sql;
		    	sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'sunshinehotel' AND TABLE_NAME = 'reservation'";
		    	System.out.println("Query:"+sql);
		    	ResultSet rs = mainApp.stmt.executeQuery(sql);
		    	//Extracting data from database
		    	while(rs.next()){
		    		//Retrieve by column name
		    		reservationMax=rs.getInt("AUTO_INCREMENT");
		    	}
		    	System.out.println("Reservation Number:"+reservationMax);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=false;
			}
			
			//Then make a reservation on database
			
			try {
				
				DateApp today = new DateApp();
				
	    		System.out.println("\n\nCreating statement...\n\n");
	    		//Interacting with database
				mainApp.stmt = mainApp.conn.createStatement();
				String sql;
				
				//RESERVATION TABLE
		    	sql = "insert into reservation values ("+reservationMax+",null,'"+mainApp.getCurrentUser().getUsername()+"',"+selection.getNo()+",'"+fromPrev.toInt()+"','"+toPrev.toInt()+"','"+today.toInt()+"')";
		    	System.out.println("Reservation Query:"+sql);
		    	mainApp.stmt.executeUpdate(sql);
		    	
		    	//PAYMENT TABLE
		    	sql = "insert into `payments` (username,reservationID,paymentType,cardNo,cvv,nameOnCard)"
		    			+ " values ('"+mainApp.getCurrentUser().getUsername()+"',"+reservationMax+",'"+payType.getValue()+"',"+cardNum.getText()+","+ccv.getText()+",'"+nameOnCard.getText()+"')";
		    	System.out.println("Payment Query:"+sql);
		    	mainApp.stmt.executeUpdate(sql);
		    	
				//ROOMSTATUS TABLE
		    	sql = "insert into roomstatus values ("+selection.getNo()+","+reservationMax+","+fromPrev.toInt()+","+toPrev.toInt()+")";
		    	System.out.println("Room Status Query:"+sql);
		    	mainApp.stmt.executeUpdate(sql);
		    	

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=false;
			}
		if(flag==true)mainApp.showMenu();
		}
	}
	
	public boolean validate() {
		nameOnCardAux=nameOnCard.getText();
		ccvAux=ccv.getText();
		cardNoAux=cardNum.getText();
		String error = "weeee";
		
		boolean flag = mainApp.flag;
		
		//check if fields are empty
		if((cardNoAux==null)||(ccvAux==null)||(nameOnCardAux==null)) {
			if(flag==false) {
				error="Please complete all the required fields.";
			}
			else {
				error="Veuillez remplir tous les champs obligatoires.";
			}
			
		}
		
		//check length
		else if(ccvAux.length()>11||cardNoAux.length()>30||nameOnCardAux.length()>25) {
			if(flag==false) {
				error="Fields too long. Please enter the proper values.";
			}
			else {
				error="Champs trop longs. Veuillez saisir les \nvaleurs appropriées.";
			}
		}
		//check if card Number only contains number
		else if(!(ccvAux.matches("^[0-9]*$"))||!(cardNoAux.matches("^[0-9]*$"))) {
			if(flag==false) {
				error="CVC and Card Number can only contain numbers.";
			}
			else {
				error="Le CVC et le numéro de carte ne peuvent contenir \n que des chiffres.";
			}
			
		}
		else {
			return true;
		}
		errorMsg.setText(error);
		return false;
	}
	
	
}
