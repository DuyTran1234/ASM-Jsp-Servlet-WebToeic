package BEAN;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Lession {
	private String lessionName = null;
	private String content = null;
	private String dateToday = null;
	private String lessionNameOld = null;
	
	public String getLessionNameOld() {
		return lessionNameOld;
	}
	public void setLessionNameOld(String lessionNameOld) {
		this.lessionNameOld = lessionNameOld;
	}
	public String getDateToday() {
		return dateToday;
	}
	public void setDateToday(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		this.dateToday = sdf.format(date);
	}
	public void setDateToday(String date) {
		this.dateToday = date;
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
