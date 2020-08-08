package ch.makery.address.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Reservation extends Room{
	
	protected StringProperty ID;
	private ObjectProperty<Date> from;
	private ObjectProperty<Date> to;

	public Reservation(String ID, String loc, String typ,double price, int bathn, int roomn, int no, Date f, Date t) {
		super(loc, typ, bathn, roomn, no,price);
		this.ID=new SimpleStringProperty(ID);
		this.from=new SimpleObjectProperty<Date>(f);
		this.to=new SimpleObjectProperty<Date>(t);
		// TODO Auto-generated constructor stub
	}
	
	//SETTER
	public void setID(String iD) {
		this.ID.set(iD);
	}
	public void setFrom(Date from) {
		this.from.set(from);
	}
	public void setTo(Date to) {
		this.to.set(to);
	}
	
	//GETTER
	public String getID() {
		return this.ID.get();
	}

	public Date getFrom() {
		return from.get();
	}
	public Date getTo() {
		return to.get();
	}

	
	//GET_PROPERTY
	public StringProperty getPropertyID() {
		return ID;
	}
	public ObjectProperty<Date> getPropertyFrom() {
		return from;
	}
	public ObjectProperty<Date> getPropertyTo() {
		return to;
	}
	
	//Converting personal Class GETTERPROPERTY
	
	public ObservableValue<String> getPropertyOOFrom() {
		StringProperty aux = new SimpleStringProperty(this.getFrom().toString());
		return aux;
	}
	
	public ObservableValue<String> getPropertyOOTo() {
		StringProperty aux = new SimpleStringProperty(this.getTo().toString());
		return aux;
	}
	
	
	@Override
	public String toString() {
		String str = "ID: "+this.getID()+"\nLocation: "+this.getLocation()
		+"\nNo. Bathrooms: "+this.getBathno()+"\nNo. Bedrooms: "+this.getRoomno()
		+"\nNo. of Room: "+this.getNo()+"\nFrom: "+this.getFrom()+"\nTo: "+this.getTo();		
		return str;
	}
	
	
}
