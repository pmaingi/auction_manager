package com.bryma.auction_manager.web.utils;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * ResponseUtils writes different response outputs
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 7 Aug 2013
 * @since enter jdk version
 */
public class ResponseUtils {

	/**
	 * 
	 * <p>
	 * Write JSon
	 * </p>
	 * 
	 * @param jsonObject
	 * @param response
	 * @throws Exception
	 */
	public static void writeJson(Map<String, Object> map,
			HttpServletResponse response) throws Exception {

		JSONObject jsonObject = new JSONObject(map);
		response.setContentType("application/json");
		response.getWriter().write(jsonObject.toString());
	}
	
	
	public static void writeHtmlJson(Map<String, Object> map,
			HttpServletResponse response) throws Exception {

		JSONObject jsonObject = new JSONObject(map);
		response.setContentType("text/html");
		response.getWriter().write(jsonObject.toString());
	}

	

	/**
	 * 
	 * <p>
	 * Write Json Array to stream
	 * </p>
	 * @param jsonArray
	 * @param response
	 * @throws Exception
	 */
	public static void writeJsonArray(JSONArray jsonArray,
			HttpServletResponse response) throws Exception{

		response.setContentType("application/json");
		response.getWriter().write(jsonArray.toString());
	}
	
	/**
	 * 
	 * <p>
	 * Write json object
	 * </p>
	 * @param jsonObject
	 * @param response
	 * @throws Exception
	 */
	public static void writeJson(JSONObject jsonObject,
			HttpServletResponse response) throws Exception{

		response.setContentType("application/json");
		response.getWriter().write(jsonObject.toString());
	}
}