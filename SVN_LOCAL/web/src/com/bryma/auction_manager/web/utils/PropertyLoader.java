package com.bryma.auction_manager.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * PropertyLoader handles properties
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 7 Aug 2013
 * @since enter jdk version
 */
public class PropertyLoader {
	
	String prop_file = "config.properties";

	/**
	 * 
	 * <p>
	 * Load Properties
	 * </p>
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Properties load(String file) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(file);
		properties.load(inputStream);
		return properties;
	}
	
	public Properties load() throws IOException{
		return load(prop_file);
	}
	
	public static Properties get(){
		try {
			return new PropertyLoader().load();
		} catch (IOException e) {
			return null;
		}
	}
}