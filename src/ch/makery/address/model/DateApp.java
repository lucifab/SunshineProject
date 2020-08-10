package ch.makery.address.model;

import java.util.Calendar;

public class DateApp{
	
	//Variables
	private int day;
	private int month;
	private int year;
	
	Calendar c = Calendar.getInstance();
	
	//Constructor
	public DateApp() {
		
		//Default constructor. Used for getting today's date!
		this.year=c.get(Calendar.YEAR);
		this.month=c.get(Calendar.MONTH)+1;
		this.day=c.get(Calendar.DAY_OF_MONTH);
	}
	
	
	public DateApp(int d, int m, int y) {
		this.day=d;
		this.month=m;
		this.year=y;
	}
	
	public DateApp(int full) {
		this.day=full%100;
		this.month=(full%10000-this.day)/100;
		this.year=(full-this.month-this.day)/10000;
		System.out.println(this);
	}
	
	
	//Copier
	public DateApp(DateApp aux) {
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
	
	public int toInt() {
		int res = this.year*10000+this.month*100+this.day;
		return res;
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
	
	public boolean isThisAfter(DateApp next) {
		boolean ans = true;
		
		int[] difDates = new int[3];
		difDates[2] = this.year-next.year;
		difDates[1] = this.month-next.month;
		difDates[0] = this.day-next.day;
	
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
