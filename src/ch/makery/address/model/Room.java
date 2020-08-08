package ch.makery.address.model;

import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Room {
	private StringProperty location;
	private StringProperty type;
	private IntegerProperty bathno;
	private IntegerProperty roomno;
	private IntegerProperty no;
	private DoubleProperty dailyPrice;
	
	
	
	public Room(String loc,String typ,int bathn,int roomn,int no, double price) {
		this.location=new SimpleStringProperty(loc);
		this.type=new SimpleStringProperty(typ);
		this.bathno=new SimpleIntegerProperty(bathn);
		this.roomno=new SimpleIntegerProperty(roomn);
		this.no=new SimpleIntegerProperty(no);
		this.dailyPrice=new SimpleDoubleProperty(price);
	}


	//SETTERS
	public void setLocation(String location) {
		this.location.set(location);
	}


	public void setType(String type) {
		this.type.set(type);
	}


	public void setBathno(int bathno) {
		this.bathno.set(bathno);
	}


	public void setRoomno(int roomno) {
		this.roomno.set(roomno);
	}


	public void setNo(int no) {
		this.no.set(no);
	}
	public void setPrice(double price) {
		this.dailyPrice.set(price);
	}

	//Regular Getters	
	
	public String getLocation() {
		return location.get();
	}


	public String getType() {
		return type.get();
	}


	public int getBathno() {
		return bathno.get();
	}


	public int getRoomno() {
		return roomno.get();
	}


	public int getNo() {
		return no.get();
	}

	public double getDailyPrice() {
		return dailyPrice.get();
	}


	//GETTERS_PROPERTY
	public StringProperty getPropertyLocation() {
		return location;
	}


	public StringProperty getPropertyType() {
		return type;
	}


	public IntegerProperty getPropertyBathno() {
		return bathno;
	}


	public IntegerProperty getPropertyRoomno() {
		return roomno;
	}


	public IntegerProperty getPropertyNo() {
		return no;
	}

	public DoubleProperty getPropertyPrice() {
		return dailyPrice;
	}
	
	
}
