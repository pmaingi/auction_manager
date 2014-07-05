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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bryma.auction_manager.web.beans.Subscription;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.Database;
import com.bryma.auction_manager.web.utils.StringUtils;
import com.bryma.auction_manager.web.utils.TableNames;

/**
 * Helper class for manual subscription, SubscriptionService
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @verion enter version, 3 Feb 2014
 * @since enter jdk version
 */
public class SubscriptionService {

	/**
	 * connection , database connection
	 */
	private static final Logger LOGGER = Logger
			.getLogger(SubscriptionService.class);

	/**
	 * save subscription
	 * 
	 * @param request
	 * @param connection
	 * @return
	 */
	public static Subscription saveSubcription(HttpServletRequest request,
			Connection connection) {

		Subscription subscriptions = null;

		String msisdn = request.getParameter("msisdn");
		String productcode = request.getParameter("productcode");
		String amount = request.getParameter("amount");
		String regStatus = request.getParameter("regStatus");


		LOGGER.info("msisdn : " + msisdn + " productcode : " + productcode);
				//+ " regStatus : " + regStatus);

		if (StringUtils.paramSet(msisdn) && StringUtils.paramSet(productcode)
				&& StringUtils.paramSet(amount)
				&& StringUtils.paramSet(regStatus)) {

			String sql = "insert into"
					+ Database.SUBSCRIPTIONTABLENAME
					+ "(`msisdn`, `productcode`, `amount`, `regStatus`, `dateCreated`) values (?, ?, ?, ?, now())";

			PreparedStatement statement = null;

			try {
				LOGGER.info(sql);
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);
				
				statement.setString(1, msisdn);
				statement.setString(2, productcode);
				statement.setString(3, amount);
				statement.setString(4, regStatus);

				if (statement.executeUpdate() != 0) {

					subscriptions = new Subscription(0, msisdn, productcode,
							Integer.valueOf(amount), Integer.valueOf(regStatus), null);

				}

			} catch (SQLException e) {
				LOGGER.info(e.getMessage());

			} finally {

				DBConnection.closeAllDBUsage(null, statement, null);
			}
		}

		return subscriptions;
	}

	/**
	 * 
	 * updates subscriptions
	 * <p>
	 * 
	 * @param request
	 * @param connection
	 * @return
	 */
	public static Subscription updateSubscription(HttpServletRequest request,
			Connection connection) {
		
		Subscription subscriptions = null;

		String id = request.getParameter("id");
		String regStatus = request.getParameter("regStatus");
		String productcode = request.getParameter("productcode");
		//String msisdn = request.getParameter("msisdn");
		//String productname = request.getParameter("productname");
		
				
			if (StringUtils.paramSet(id) && StringUtils.paramSet(productcode)){
					//&& StringUtils.paramSet(msisdn)){
				
			String sql = "update "
					+ Database.SUBSCRIPTIONTABLENAME
					+ " set  `regStatus`= ? ,`dateCreated` = now() where `id`= ?"; //`productname`=? ,`productcode`= ?,

			PreparedStatement statement = null;
			try {
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);
		
				//statement.setBoolean(1, true);
				statement.setInt(1, Integer.valueOf(regStatus));
				//statement.setString(2, msisdn);
				statement.setString(2, productcode);
				
				
				statement.setString(3, id);

				
				if (statement.executeUpdate() != 0) {
										
					subscriptions = new Subscription(Long.valueOf(id), null, productcode, 0, Integer.valueOf(regStatus), null); 
					
					String message = ReturnTexts.getMessage(connection, "BIDREGISTERED");
						//	.replace("ITEMCODE", productcode)
						//	.replace("ITEMNAME", productname);
                    LOGGER.info(message);
					String apiUrl = "http://messaging.advantasms.com/sendsms.jsp";

					
					try {
						getResponse(apiUrl + "?" + "user="
								+ URLEncoder.encode("Mobiauction", "UTF-8")
								+ "&password="
								+ URLEncoder.encode("m12345", "UTF-8")
								+ "&mobiles=" + subscriptions.getMsisdn() + "&sms="
								+ URLEncoder.encode(message, "UTF-8"));

						//LOGGER.info(msisdn);
						
						bulkMessagesLog(subscriptions.getMsisdn(), message,
								productcode, null,
						 connection);
					
					} catch (UnsupportedEncodingException e) {
						System.err
								.println("UnsupportedEncodingException while trying to send SMS.");
						e.getMessage();
					}
					
				}

			} catch (SQLException e) {
				LOGGER.error(e);

			} finally {
				DBConnection.closeAllDBUsage(null, statement, null);
			}
		}
		return subscriptions;
	}

	/**
	 * lists all Subscriptions
	 * 
	 * @param connection
	 * @param request
	 * @return
	 */
	public static List<Subscription> listAllSubscriptions(
			Connection connection, HttpServletRequest request) {

		List<Subscription> subscriptions = new ArrayList<Subscription>();

		String sql = "select * from " + Database.SUBSCRIPTIONTABLENAME
				+ "where `auctionClosed` = 0 order by `id` desc ";

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			resultSet = statement.executeQuery();
			long id = 0;
			String msisdn = null;
			String productcode = null;
			int amount = 0;
			int regStatus = 0;

			String dateCreated = null;

			while (resultSet.next()) {
				id = resultSet.getLong("id");
				msisdn = resultSet.getString("msisdn");
				productcode = resultSet.getString("productcode");
				amount = resultSet.getInt("amount");
				regStatus = resultSet.getInt("regStatus");
				dateCreated = resultSet.getString("dateCreated");

				subscriptions.add(new Subscription(Long.valueOf(id), msisdn,
						productcode, amount, Integer.valueOf(regStatus), dateCreated));

			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return subscriptions;

	}

	/**
	 * gets subscription by id
	 * 
	 * @param contentId
	 * @param connection
	 * @return
	 */

	public static Subscription getSubscriptionById(int subscriptionId,
			Connection connection) {

		Subscription subscripions = null;

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {

			String sql = "select * from " + Database.SUBSCRIPTIONTABLENAME
					+ " where `id`= ?";
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			statement.setLong(1, subscriptionId);
			resultSet = statement.executeQuery();

			long id = 0;
			String msisdn = null;
			String productcode = null;
			int amount = 0;
			int regStatus = 0;

			while (resultSet.next()) {
				id = resultSet.getLong("id");
				msisdn = resultSet.getString("msisdn");
				productcode = resultSet.getString("productcode");
				amount = resultSet.getInt("amount");
				regStatus = resultSet.getInt("regStatus");

				subscripions = new Subscription(Long.valueOf(id), msisdn,
						productcode, amount, regStatus, null);

			}

		} catch (SQLException e) {
			LOGGER.info(e.getMessage());

		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return subscripions;
	}
	
	/**
	 * 
	 * POST,for sending bulk sms
	 * <p>
	 * @param urlString
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

			// statement =
			// DBConnection.isConnected(connection).prepareStatement(
			// sql);

			statement = connection.prepareStatement(sql);
			statement.setString(1, msisdn);
			statement.setString(2, message);
			statement.setString(3, productcode);
			statement.setString(4, productname);

			// statement.addBatch();
			statement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			DBConnection.closeAllDBUsage(null, statement, connection);
		}
	}

}
