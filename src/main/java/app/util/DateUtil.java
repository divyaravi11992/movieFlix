package app.util;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
/**
*
* @author Divya Ravi
* 
* Utility class to work on Date and Time
* 
*/
public class DateUtil {
	public static java.sql.Timestamp getTimeStamp() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(
				now.getTime());
		return currentTimestamp;
	}
	
	public static Date get(String dstring) throws ParseException {
		


	return java.sql.Date.valueOf(dstring); 
	
	}
}
