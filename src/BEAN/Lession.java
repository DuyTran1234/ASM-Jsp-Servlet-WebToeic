package BEAN;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Lession {
	private String lessionName;
	private String content;
	private String dateToday;
	
	public String getDateToday() {
		return dateToday;
	}
	public void setDateToday(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		this.dateToday = sdf.format(date);
	}
	
	public String getLessionName() {
		return lessionName;
	}
	public void setLessionName(String lessionName) {
		this.lessionName = lessionName;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
