package com.bryma.auction_manager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.TableNames;

/**
 * 
 * ReturnTexts
 * 
 * @author <a href="mailto:pmaingi@gmail.com">Peter Maingi</a>>
 * @version enter version, 15 Oct 2013
 * @since  enter jdk version
 */
public class ReturnTexts implements TableNames {

	private static final Logger LOGGER = Logger.getLogger(ReturnTexts.class);

	private static PreparedStatement statement = null;

	private static ResultSet resultSet = null;

	/**
	 * 
	 * <p>
	 * Get message
	 * </p>
	 * 
	 * @param connection
	 * @param key
	 * @return
	 */
	public static String getMessage(Connection connection, String key) {
		String message = "An error occured. Please try again after afew minutes";
		String sql = "SELECT  `messageValue` FROM " + MESSAGESTABLE
				+ " WHERE `messageKey` = ?";
		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			statement.setString(1, key);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				message = resultSet.getString("messageValue");
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return message;
	}
}