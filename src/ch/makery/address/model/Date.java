package ch.makery.address.model;

public class Date {
	
	//Variables
	private int day;
	private int month;
	private int year;
	
	//Constructor
	public Date(int d, int m, int y) {
		this.day=d;
		this.month=m;
		this.year=y;
	}
	
	
	//Copier
	public Date(Date aux) {
		this.day=aux.day;
		this.month=aux.month;
		this.year=aux.year;
	}
	
	
	//Getters and Setters
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public boolean isThisAfter(int d,int m, int y) {
		boolean ans = true;
		
		int[] difDates = new int[3];
		difDates[2] = this.year-y;
		difDates[1] = this.month-m;
		difDates[0] = this.day-d;
	
		for(int i=2;i>1;i--) {
			if(difDates[i]<0){
				//Meaning this.dateType is from before (or same number) the received dateType
				//Ex: this date instance is from May, received is december
				//or this data type is from 2009 and received is also 2009
				ans=false;
			}
			else {
				//If this doesn't happen then it means it's from after that
				//Ex: this instance  is from 2019, received is from 2000
				break;
			}
		}
		//Lastly, if check if the month and year is still same, check date.
		//If it's there's no difference, then it's not after.
		if((difDates[1]==0)&&(difDates[2]==0)&&(difDates[0])<=0) {
			ans=false;
		}
		return ans;
	}
	
	@Override
	public String toString() {
		String str = this.day+"/"+this.month+"/"+this.year;
		return str;
	}


	
}
