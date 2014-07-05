package com.bryma.auction_manager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bryma.auction_manager.web.beans.Auction;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.Database;
import com.bryma.auction_manager.web.utils.DateUtils;
import com.bryma.auction_manager.web.utils.StringUtils;

/**
 * Helper class for listing auctions,
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @verion enter version, 3 Feb 2014
 * @since enter jdk version
 */
public class AuctionService {

	/**
	 * connection , database connection
	 */
	private static final Logger LOGGER = Logger
			.getLogger(AuctionService.class);

	/**
	 * save Auction
	 * 
	 * @param request
	 * @param connection
	 * @return
	 */
	public static Auction saveAuction(HttpServletRequest request,
			Connection connection) {

		Auction auctions = null;

		String productcode = request.getParameter("productcode");
		String description = request.getParameter("description");
		//String isopen = request.getParameter("isopen");
		//String dateCreated = request.getParameter("dateCreated");
		//String endDate = request.getParameter("endDate");

		LOGGER.info("productcode : " + productcode + " description : " + description );// " isopen : " + isopen );
				//+ " regStatus : " + regStatus);

		if (StringUtils.paramSet(productcode) && StringUtils.paramSet(description)){
				//&& StringUtils.paramSet(isopen)
				//&& StringUtils.paramSet(dateCreated) 
				//&& StringUtils.paramSet(endDate))

			String sql = "insert into"
					+ Database.AUCTIONTABLENAME1
					+ "(`productcode`, `description`, `isopen`, `dateCreated`) values (?, ?, 1, now())";

			PreparedStatement statement = null;

			try {
				//LOGGER.info(sql);
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);
				
				statement.setString(1, productcode);
				statement.setString(2, description);
				//statement.setString(3, isopen);
				//statement.setString(4, dateCreated);
				//statement.setString(3, DateUtils.formatToSQLDateString(endDate));

				if (statement.executeUpdate() != 0) {

					//changed here
					auctions = new Auction(0, productcode, description, 0, null, null);   

				}

			} catch (SQLException e) {
				LOGGER.info(e.getMessage());

			} finally {

				DBConnection.closeAllDBUsage(null, statement, null);
			}
		}

		return auctions;
	}

	/**
	 * 
	 * updates auctions set end date
	 * <p>
	 * 
	 * @param request
	 * @param connection
	 * @return
	 */
	public static Auction updateAuction(HttpServletRequest request,
			Connection connection) {

		Auction auctions = null;
		String id = request.getParameter("id");
		String endDate = request.getParameter("endDate");
						
		if (StringUtils.paramSet(id)  && StringUtils.paramSet(endDate)){
				
			String sql = "update "
					+ Database.AUCTIONTABLENAME1
					+ " set  `endDate`= ? where `id`= ?";

			PreparedStatement statement = null;
			try {
				//LOGGER.info(sql);
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);
				
				statement.setTimestamp(1, Timestamp.valueOf(DateUtils.formatToSQLDateString(endDate))); //DateUtils.formatToSQLDateString(
				statement.setString(2, id);
				
				if (statement.executeUpdate() != 0) {
										
					auctions = new Auction(Long.valueOf(id), null, null,  0, null, endDate);   
							
				}

			} catch (SQLException e) {
				LOGGER.error(e);

			} finally {
				DBConnection.closeAllDBUsage(null, statement, null);
			}
		}
		return auctions;
	}

	/**
	 * lists all auctions
	 * 
	 * @param connection
	 * @param request
	 * @return
	 */
	public static List<Auction> listAllAuctions(
			Connection connection, HttpServletRequest request) {

		List<Auction> auctions = new ArrayList<Auction>();

		String sql = "select * from " + Database.AUCTIONTABLENAME1 
				+ "WHERE `isopen` = 1 order by `id` desc ";

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			//LOGGER.info(sql);
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			
			resultSet = statement.executeQuery();
			
			long id = 0;
			String productcode = null;
			String description = null;
			//changed here uncommented isopen
			int isopen = 0;
			String dateCreated = null;
			String endDate = null;
		
        	while (resultSet.next()) {
				id = resultSet.getLong("id");
				productcode = resultSet.getString("productcode");
				description = resultSet.getString("description");
				isopen = resultSet.getInt("isopen");
				dateCreated = resultSet.getString("dateCreated");
				endDate = resultSet.getString("endDate");

				//changed isopen
				auctions.add(new Auction(Long.valueOf(id), productcode, description, isopen, dateCreated, endDate));  
				
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return auctions;

	}

	/**
	 * gets Auction by id
	 * 
	 * @param contentId
	 * @param connection
	 * @return
	 */

	public static Auction getAuctionById(int auctionId,
			Connection connection) {

		Auction auctions = null;

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {

			String sql = "select * from " + Database.AUCTIONTABLENAME1
					+ " where `id`= ?";
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			statement.setLong(1, auctionId);
			resultSet = statement.executeQuery();

			long id = 0;
			String productcode = null;
			String description = null;
			int isopen = 0;
			String dateCreated = null;
			String endDate = null;
			
			//LOGGER.info(sql);
			while (resultSet.next()) {
				id = resultSet.getLong("id");
				productcode = resultSet.getString("productcode");
				description = resultSet.getString("description");
				isopen = resultSet.getInt("isopen");
				dateCreated = resultSet.getString("dateCreated");
				endDate = resultSet.getString("endDate");

				auctions = new Auction(Long.valueOf(id), productcode, description, isopen, dateCreated, endDate);
				
			}

		} catch (SQLException e) {
			LOGGER.info(e.getMessage());

		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return auctions;
	}
}
