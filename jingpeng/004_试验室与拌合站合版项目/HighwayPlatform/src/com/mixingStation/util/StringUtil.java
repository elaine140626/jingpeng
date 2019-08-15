package com.mixingStation.util;

import java.util.UUID;

public class StringUtil {
	/**
	 * Sting类型判空
	 * 
	 **/
	public static boolean stringIsEmpty(String str) {
		if (str == null || "".equals(str)) {
			//为空的场合
			return true;
		} else {
			//不为空的
			return false;
		}		
	}
	
	/**
	 * 主键32位uuid
	 * 
	 **/
	public static String getUuid() {
		String uuid = UUID.randomUUID().toString();	//获取UUID并转化为String对象
		uuid = uuid.replace("-", "");				//因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
		
		return uuid;
	}

}
