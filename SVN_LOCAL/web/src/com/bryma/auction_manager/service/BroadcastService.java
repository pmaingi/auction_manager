package com.bryma.auction_manager.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bryma.auction_manager.web.beans.Broadcast;
import com.bryma.auction_manager.web.beans.Product;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.Database;
import com.bryma.auction_manager.web.utils.StringUtils;
import com.bryma.auction_manager.web.utils.TableNames;


public class BroadcastService {

	/**
	 * connection , database connection
	 */
	private static final Logger LOGGER = Logger.getLogger(ProductService.class);

	/**
	 * save product
	 * 
	 * @param request
	 * @param connection
	 * @return
	 */
	public static Broadcast saveBroadcast(HttpServletRequest request,
			Connection connection) {

		Broadcast broadcast = null;

	    String msisdn = request.getParameter("msisdn");
		String message = request.getParameter("message");

		LOGGER.info("msisdn : " + msisdn + " message : " + message);
	

		if (StringUtils.paramSet(msisdn) && StringUtils.paramSet(message)) {

			String sql = "insert into"
					+ Database.BULK_MESSAGESENDERTABLE
					+ "(`msisdn`, `message`, `dateCreated`) values (?, ?, now())" ;

			PreparedStatement statement = null;

			try {
				LOGGER.info(sql);
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);
				// statement.setLong(1, Long.valueOf(id));
				statement.setString(1, msisdn);
				statement.setString(2, message);
				
				if (statement.executeUpdate() != 0) {
					broadcast = new Broadcast(0, msisdn, message);

				}
				String apiUrl = "http://messaging.advantasms.com/sendsms.jsp";
				
				try {
					getResponse(apiUrl + "?" + "user="
							+ URLEncoder.encode("Mobiauction", "UTF-8")
							+ "&password=" + URLEncoder.encode("m12345", "UTF-8")
							+ "&mobiles=" + msisdn + "&sms="
							+ URLEncoder.encode(message, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}// m12345
				
				bulkMessagesLog(msisdn, message,
						null, null,
						connection);

			} catch (SQLException e) {
				LOGGER.info(e.getMessage());

			} finally {

				DBConnection.closeAllDBUsage(null, statement, null);
			}
		}

		return broadcast;
	}

	
	/**
	 * 
	 * response method
	 * <p>
	 * 
	 * @param urlStr
	 * @return
	 */
	private static String getResponse(String urlString) {

		URLConnection conn;
		URL url;
		BufferedReader reader;
		String response = "";
		String output = "";

		try {
			url = new URL(urlString);
			conn = url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			response = reader.readLine();
			while (response != null) {
				output += response;
				response = reader.readLine();
			}
			// System.out.println("Output: \n" + output);

			reader.close();
		} catch (MalformedURLException e) {
			System.err.println("MalformedURLException exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException exception");
			e.printStackTrace();

		}
		return response;
	}
	
	/**
	 * 
	 * Logs bulk messages sent out
	 * <p>
	 * 
	 * @param msisdn
	 * @param message
	 * @param connection
	 */
	public static void bulkMessagesLog(String msisdn, String message,
			String productcode, String productname, Connection connection) {

		PreparedStatement statement = null;

		String sql = "INSERT INTO "
				+ TableNames.BULK_MESSAGESLOGTABLE
				+ "(`msisdn`,`message`,`productcode`,`productname`,`dateCreated`) VALUES(?,?,?,?,now()) ";
		try {
			if (connection == null || connection.isClosed())
				connection = DBConnection.getConnection();

			statement = connection.prepareStatement(sql);
			statement.setString(1, msisdn);
			statement.setString(2, message);
			statement.setString(3, productcode);
			statement.setString(4, productname);

			statement.addBatch();
			statement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			DBConnection.closeAllDBUsage(null, statement, connection);
		}
	}

}

