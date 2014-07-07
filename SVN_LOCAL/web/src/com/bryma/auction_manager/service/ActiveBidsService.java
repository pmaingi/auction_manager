package com.bryma.auction_manager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bryma.auction_manager.web.beans.ActiveBids;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.Database;

/**
 * Helper class for listing auctions,
 * 
 * @author <a href="mailto:pmaingi@gmail.com">Peter</a>
 * @verion enter version, 3 Feb 2014
 * @since enter jdk version
 */
public class ActiveBidsService {

	/**
	 * connection , database connection
	 */
	private static final Logger LOGGER = Logger
			.getLogger(ActiveBidsService.class);

	/**
	 * lists all active bids
	 * 
	 * @param connection
	 * @param request
	 * @return
	 */
	public static List<ActiveBids> listAllActiveBids(Connection connection,
			HttpServletRequest request) {

		List<ActiveBids> activeBids = new ArrayList<ActiveBids>();

		String sql = "select * from " + Database.SAVEDBIDS
				+ "WHERE `auctionClosed` = 0 order by `id` desc ";

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			resultSet = statement.executeQuery();
			long id = 0;
			String productcode = null;
			String productname = null;
			String msisdn = null;
			double amount = 0.0;
			int auctionClosed = 0;
			String dateCreated = null;

			while (resultSet.next()) {
				id = resultSet.getLong("id");
				productcode = resultSet.getString("productcode");
				productname = resultSet.getString("productname");
				msisdn = resultSet.getString("msisdn");
				amount = resultSet.getDouble("amount");
				auctionClosed = resultSet.getInt("auctionClosed");
				dateCreated = resultSet.getString("dateCreated");

				activeBids
						.add(new ActiveBids(Long.valueOf(id), productcode,
								productname, msisdn, amount, auctionClosed,
								dateCreated));

			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return activeBids;

	}

}
