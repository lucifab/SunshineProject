package ch.makery.address;

import ch.makery.address.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class RoomSearchController extends  Controller{
	
	//Data retrieved from MainApp
	protected User currentUser;
	
	//Auxiliary Data
	ObservableList<String> aux = FXCollections.observableArrayList("High to Low","Low to High");
	
	//FXML Labels
	
	//Data
	@FXML
	Label userID;
	@FXML
	ComboBox<String> location;
	@FXML
	ComboBox<String> price;
	//Labels, buttons & other entities that need name change
	@FXML
	Label welcome;
	@FXML
	Label specs;
	@FXML
	Label locationTable;
	@FXML
	Label fromTable;
	@FXML
	Label toTable;
	@FXML
	Button back;
	@FXML
	Button next;
	
	
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void initData(User currentUser,ObservableList<String> list) {
		this.currentUser = currentUser;
		userID.setText(this.currentUser.username);
		location.setItems(list);
		location.getSelectionModel().select(1);
		price.setItems(aux);
		price.getSelectionModel().select(1);
	}
	
	public void langChange() {
			welcome.setText("Bienvenue,");
			specs.setText("Choisissez les spécifications");
			locationTable.setText("Localisation");
			fromTable.setText("De");
			toTable.setText("À");
			back.setText("Arrière");
			next.setText("Prochain");
	}
	
	

	public void onBackButton(ActionEvent event) {
		event.consume();
	    System.out.println("Hello, World!");
	    mainApp.showMenu();
	}
	public void onNextButton(ActionEvent event) {
		event.consume();
		System.out.println(currentUser);
	}
}
