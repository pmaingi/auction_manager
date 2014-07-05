package com.bryma.auction_manager.web.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * 
 * DBConnection to handle datasource connection with fallback
 * 
 *@author <a href="mailto:pmaingi@gmail.com">Peter Maingi</a>
 * @version enter version, 27 Oct 2013
 * @since enter jdk version
 */
public class DBConnection {

	private static final Logger LOGGER = Logger.getLogger(DBConnection.class);

	/**
	 * 
	 * <p>
	 * Get connection from datasource and provide fallback incase it fails
	 * </p>
	 * 
	 * @return
	 */
	public static Connection getConnection() {

		Connection connection = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext
					.lookup("java:/auctionmanagerMysqlDS");
			connection = ds.getConnection();
		} catch (Exception e) {
			connection = getSingleConnection();

		}
		return connection;
	}

	/**
	 * 
	 * Creates and returns a database connection
	 * <p>
	 * 
	 * @return<code>Connection</code>
	 * @throws <code>SQLException</code>if there was an error establishing a
	 *         database connection
	 */
	private static Connection getSingleConnection() {

		Connection connection = null;

		try {
			while (connection == null || connection.isReadOnly()
					|| connection.isClosed()) {
				try {

					Class.forName("com.mysql.jdbc.Driver");
					connection = (Connection) DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/auction", "root", "");
				} catch (Exception e) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						LOGGER.error(e1);
					}
					LOGGER.error(e);

				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return connection;

	}

	/**
	 * 
	 * <p>
	 * Check if connected
	 * </p>
	 * 
	 * @param connection
	 * @return
	 */
	public static Connection isConnected(Connection connection) {

		if (connection == null && !connIsOK(connection)) {
			connection = getConnection();
		}
		return connection;
	}

	/**
	 * Closes connection
	 * <p>
	 * 
	 * @param <code>Connection</code>connection to close
	 */
	public static void closeConnection(Connection connection) {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
			connection = null;
		}
	}

	/**
	 * 
	 * <p>
	 * Close all DB connections
	 * </p>
	 * 
	 * @param resultSet
	 * @param preparedStatement
	 * @param connection
	 */
	public static void closeAllDBUsage(ResultSet resultSet,
			PreparedStatement preparedStatement, Connection connection) {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try {
					resultSet.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		closeConnection(connection);
	}

	/**
	 * 
	 * <p>
	 * Check if connection is ok
	 * </p>
	 * 
	 * @param connection
	 * @return
	 */
	private static boolean connIsOK(Connection connection) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement("SELECT 'test'");
			rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			return false;
		} finally {
			closeAllDBUsage(rs, stmt, null);
		}
	}
}