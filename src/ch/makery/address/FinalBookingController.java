package ch.makery.address;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.makery.address.model.DateApp;
import ch.makery.address.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
	
	public void init(MainApp main, Room selection,DateApp dateFrom,DateApp dateTo) {
		
		//Connects controller
		this.setMainApp(main);
		
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
		    	sql = "insert into reservation values ("+reservationMax+",null,'"+mainApp.getCurrentUser().getUsername()+"', 1,null,"+selection.getNo()+",'"+fromPrev.toInt()+"','"+toPrev.toInt()+"','"+today.toInt()+"',1,0)";
		    	System.out.println("Reservation Query:"+sql);
		    	mainApp.stmt.executeUpdate(sql);
		    	
		    	//PAYMENT TABLE
		    	sql = "insert into `payments` (username,reservationID,paymentType,cardNo,cvv,nameOnCard)"
		    			+ " values ('"+mainApp.getCurrentUser().getUsername()+"',"+reservationMax+",'"+payType.getValue()+"',"+cardNum.getText()+","+ccv.getText()+",'"+nameOnCard.getText()+"')";
		    	System.out.println("Payment Query:"+sql);
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
		if((cardNum.getText()==null)||(ccv.getText()==null)||(nameOnCard.getText()==null)) {
			errorMsg.setText("Please complete all the required fields.");
			return false;
		}
		else {
			return true;
		}
	}
	
	
}
