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
import com.bryma.auction_manager.web.beans.HitsLog;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.Database;
import com.bryma.auction_manager.web.utils.StringUtils;

/**
 * Helper class for listing auctions,
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @verion enter version, 3 Feb 2014
 * @since enter jdk version
 */
public class HitsLogService {

	/**
	 * connection , database connection
	 */
	private static final Logger LOGGER = Logger.getLogger(HitsLogService.class);

	/**
	 * lists all hits to the app
	 * 
	 * @param connection
	 * @param request
	 * @return
	 */
	public static List<HitsLog> listAllHitsLogs(Connection connection,
			HttpServletRequest request) {

		List<HitsLog> hits = new ArrayList<HitsLog>();

		String sql = "select * from " + Database.HITSLOG									
				+ "order by `dateCreated` desc ";

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			resultSet = statement.executeQuery();
			long id = 0;
			String msisdn = null;
			String keyword = null;
			String dateCreated = null;

			while (resultSet.next()) {
				id = resultSet.getLong("id");
				msisdn = resultSet.getString("msisdn");
				keyword = resultSet.getString("keyword");
				dateCreated = resultSet.getString("dateCreated");

				hits.add(new HitsLog(Long.valueOf(id), msisdn, keyword,
						dateCreated));

			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return hits;

	}

}
