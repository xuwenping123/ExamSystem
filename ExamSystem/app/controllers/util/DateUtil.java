package controllers.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	static final String DATE = "yyyy-MM-dd";
	static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	
	static SimpleDateFormat sdf = null;
	
	public static Date stringToDatetime(String timeString) {
		sdf = new SimpleDateFormat(DATE);
		Date dateTime = null;
		try {
			dateTime = sdf.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	public static Date stringToDate(String timeString) {
		sdf = new SimpleDateFormat(DATETIME);
		Date dateTime = null;
		try {
			dateTime = sdf.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	public static String dateToDateString(Date date) {
		
		sdf = new SimpleDateFormat(DATE);
		
		String dateString = sdf.format(date);
		return dateString;
	}
	
	public static String dateToDateTimeString(Date date) {
		
		sdf = new SimpleDateFormat(DATETIME);
		
		String dateTimeString = sdf.format(date);
		return dateTimeString;
	}
}
