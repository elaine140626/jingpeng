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
	
		Date dateT = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
		
		return time.format(dateT);
	}
}
