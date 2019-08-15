package com.mixingStation.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	/**
	  * 读取常量properties文件
	  */
	public static String getProperties(String key) throws IOException {
	    Properties properties = new Properties();
	    InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("message.properties");
	    properties.load(inputStream);	 
	    String result = properties.getProperty(key);
	    return result;
	}
}
