package dataManagePackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BulletListItem {
	Date date;
	String contents;
	
	public BulletListItem(Date date, String contents){
		this.date = date;
		this.contents = contents;
		
	}
	
	public BulletListItem(String contents){
	
		this.contents = contents;
		
	}
	
	public String getContents(){
		return contents;
		
	}
	
	public Date getDate(){
		return date;
		
	}
	
	public String getStringDate(){
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(date);
		
	}
	public String getStringContentDate(){
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return contents +", "+df.format(date);
		
	}
	
}
