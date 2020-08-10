package ch.makery.address.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Reservation extends Room{
	
	protected StringProperty ID;
	private ObjectProperty<DateApp> from;
	private ObjectProperty<DateApp> to;
	protected IntegerProperty cancellation;

	public Reservation(String ID, int cancellation, String loc, String typ,double price, int bathn, int roomn, int no, DateApp f, DateApp t) {
		super(loc, typ, bathn, roomn, no,price);
		this.ID=new SimpleStringProperty(ID);
		this.from=new SimpleObjectProperty<DateApp>(f);
		this.to=new SimpleObjectProperty<DateApp>(t);
		this.cancellation = new SimpleIntegerProperty(cancellation);
		// TODO Auto-generated constructor stub
	}
	
	//SETTER
	public void setID(String iD) {
		this.ID.set(iD);
	}
	public void setFrom(DateApp from) {
		this.from.set(from);
	}
	public void setTo(DateApp to) {
		this.to.set(to);
	}
	
	//GETTER
	public String getID() {
		return this.ID.get();
	}

	public DateApp getFrom() {
		return from.get();
	}
	public DateApp getTo() {
		return to.get();
	}

	
	//GET_PROPERTY
	public StringProperty getPropertyID() {
		return ID;
	}
	public ObjectProperty<DateApp> getPropertyFrom() {
		return from;
	}
	public ObjectProperty<DateApp> getPropertyTo() {
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
