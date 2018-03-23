package Utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String format(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static Date parser(String text,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(text, new ParsePosition(0));
	}
}
