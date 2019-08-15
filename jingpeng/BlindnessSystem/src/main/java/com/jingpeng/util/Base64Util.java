package com.jingpeng.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("all")
public class Base64Util {

	private static BASE64Encoder encoder = new BASE64Encoder();// 加密
	private static BASE64Decoder decoder = new BASE64Decoder();// 解密

	/**
	 * 加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(String inputStr) {
		String value = "";
		try {
			byte[] key = inputStr.getBytes();
			value = encoder.encodeBuffer(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptBASE64(String outputStr) {
		String value = "";
		try {
			byte[] key = decoder.decodeBuffer(outputStr);
			value = new String(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
}
