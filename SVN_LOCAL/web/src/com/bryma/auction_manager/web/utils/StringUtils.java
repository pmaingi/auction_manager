package com.bryma.auction_manager.web.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;

public class StringUtils {

	/**
	 * 
	 * <p>
	 * Generate Code
	 * </p>
	 * 
	 * @return
	 */
	public static String code() {

		SecureRandom random = new SecureRandom();

		return new BigInteger(100, random).toString(32);

	}

	/**
	 * 
	 * <p>
	 * String to array
	 * </p>
	 * 
	 * @param message
	 * @return
	 */
	public static String[] stringArray(String message) {

		return message.split(",\\s*");
	}

	/**
	 * 
	 * <p>
	 * Check if param is set
	 * </p>
	 * 
	 * @param param
	 * @return
	 */
	public static boolean paramSet(String param) {

		return (param != null && param.trim().length() > 0);
	}

	/**
	 * 
	 * <p>
	 * Convert String to Integer
	 * </p>
	 * 
	 * @param param
	 * @return
	 */
	public static int toInt(String param) {

		try {
			return Integer.valueOf(param.trim());
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 
	 * <p>
	 * Convert to Long
	 * </p>
	 * 
	 * @param param
	 * @return
	 */
	public static long toLong(String param) {

		try {
			return Long.valueOf(param.trim());
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 
	 * <p>
	 * Convert Json Array to comma seperated string
	 * </p>
	 * 
	 * @param jsonArray
	 * @return
	 */
	public static String jsonArrayToString(JSONArray jsonArray) {

		try {
			return jsonArray.join(",");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String imageId(){
		StringBuilder output = new StringBuilder();
		
		SecureRandom random = new SecureRandom();
		int count = 5;
		String numb = String.valueOf(System.nanoTime());
		if(count < numb.length()){
			count = count - numb.length();
		}
		output.append(new BigInteger(50, random).toString(32));
		for(int i=0; i<count; i++){
			output.append("0");
		}
		
		return output.toString().toUpperCase();
	}

	public static String escape(String str, boolean encode){
		str = str.trim();
		if(encode)
			return StringEscapeUtils.escapeHtml(str);
		else 
			return StringEscapeUtils.unescapeHtml(str);
	}
	
	public static void main(String[] args) {

		System.out.println(escape("på", true));
	}
}
