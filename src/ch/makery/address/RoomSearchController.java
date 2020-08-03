package ch.makery.address;

import ch.makery.address.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class RoomSearchController extends  Controller{
	
	//Data retrieved from MainApp
	protected User currentUser;
	
	//Auxiliary Data
	ObservableList<String> aux = FXCollections.observableArrayList("High to Low","Low to High");
	
	//FXML Labels
	@FXML
	Label userID;
	@FXML
	ComboBox<String> location;
	@FXML
	ComboBox<String> price;
	
	
	
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
