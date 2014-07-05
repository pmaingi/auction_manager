package com.bryma.auction_manager.web.utils;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 
 * UrlUtils
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 7 Aug 2013
 * @since enter jdk version
 */
public class UrlUtils {

	/**
	 * httpClient, make http calls
	 */
	//private static HttpClient httpClient;

	private static final Logger LOGGER = Logger.getLogger(UrlUtils.class);

	private static String[] resources = { "css", "js", "img", "map", "api" };

	private static String[] extensions = { "css", "js", ".png", ".jpg",".gif" };

	/**
	 * 
	 * <p>
	 * Get Server URL
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	public static String getServerURL(HttpServletRequest request) {

		URL url;
		try {
			url = new URL(request.getScheme(), request.getServerName(),
					request.getServerPort(), request.getContextPath());
			return url.toString();
		} catch (MalformedURLException e) {
			LOGGER.error(e);
			return null;
		}

	}

	/**
	 * 
	 * <p>
	 * Check if url is resource url (static content)
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isResource(HttpServletRequest request) {

		boolean isresource = false;
		String urlPrefix = getServerURL(request);
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(request.getRequestURL().toString());
		urlBuilder.replace(0, urlPrefix.length() + 1, "");
		for (String str : resources) {
			if (urlBuilder.toString().startsWith(str)) {
				isresource = true;
			}
		}
		if (!isresource) {
			for (String str : extensions) {
				if (urlBuilder.toString().endsWith(str)) {
					isresource = true;
				}
			}
		}
		return isresource;
	}

}
