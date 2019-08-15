package com.jingpeng.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {

	/**
	 * @throws ParseException 
	 * 
	 */
	public static String changeDate(String date) throws ParseException{
	
		Date dateT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		return time.format(dateT);
	}
}
