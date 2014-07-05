package com.bryma.auction_manager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bryma.auction_manager.web.beans.Auth;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.Database;
import com.bryma.auction_manager.web.utils.EncryptionHelper;
import com.bryma.auction_manager.web.utils.StringUtils;

/**
 * 
 * AuthService Service to carry out all Auth functions
 * 
 * @author <a href="mailto:enter email address">Peter Maingi</a>
 * @version enter version, 15 September 2013
 * @since enter jdk version
 */
public class AuthService {

	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;

	private static final Logger LOGGER = Logger.getLogger(AuthService.class);

	/**
	 * 
	 * <p>
	 * Authorization function
	 * </p>
	 * 
	 * @param <code>HttpServletRequest</code> request
	 * @param <code>Connection</code> connection
	 * @return <code>Auth</code>
	 */
	public static Auth authorize(HttpServletRequest request,
			Connection connection) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (StringUtils.paramSet(username) && StringUtils.paramSet(password)) {
			String sql = "SELECT  * from " + Database.USERS_TABLENAME
					+ " WHERE `username` = ? AND `password` = ?";
			try {
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);
				statement.setString(1, username.trim());
				statement.setString(2,
						EncryptionHelper.getInstance().encrypt(password));
				resultSet = statement.executeQuery();
				if (resultSet.next()) {

					return new Auth(resultSet.getLong("id"),
							resultSet.getString("username"),
							resultSet.getInt("role"));

				}
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				DBConnection.closeAllDBUsage(resultSet, statement, null);
			}
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Get auth by ID
	 * </p>
	 * 
	 * @param id
	 * @param connection
	 * @return
	 */
	public static Auth getAuthById(long id, Connection connection) {

		Auth auth = null;
		String sql = "SELECT  * from " + Database.USERS_TABLENAME
				+ " WHERE `id` = ?";
		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				auth = new Auth(resultSet.getLong("id"),
						resultSet.getString("username"),
						resultSet.getInt("role"));
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return auth;
	}

	/**
	 * 
	 * <p>
	 * Save Auth
	 * </p>
	 * 
	 * @param request
	 * @param connection
	 * @return
	 */
	public static Auth saveAuth(HttpServletRequest request,
			Connection connection) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		String role = request.getParameter("role");

		if (StringUtils.paramSet(username) && StringUtils.paramSet(password)
				&& StringUtils.paramSet(cpassword)
				&& StringUtils.paramSet(role)) {
			if (password.trim().equals(cpassword.trim())) {
				String sql = "INSERT INTO "
						+ Database.USERS_TABLENAME
						+ "(`username`,`password`,`role`,`date_created`) VALUES(?,?,?,now())";
				try {
					statement = DBConnection.isConnected(connection)
							.prepareStatement(sql,
									Statement.RETURN_GENERATED_KEYS);
					statement.setString(1, username.trim());
					statement.setString(2,
							EncryptionHelper.getInstance().encrypt(password));

					statement.setInt(3, Integer.valueOf(role.trim()));
					statement.execute();
					resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						return new Auth(resultSet.getLong(1), username,
								Integer.valueOf(role.trim()));
					} else {
						return new Auth(1, username, Integer.valueOf(role
								.trim()));
					}
				} catch (SQLException e) {
					LOGGER.error(e);
				} finally {
					DBConnection.closeAllDBUsage(null, statement, null);
				}

			}
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Update Auth
	 * </p>
	 * 
	 * @param request
	 * @param connection
	 * @return
	 */
	public static Auth updateAuth(HttpServletRequest request,
			Connection connection) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		String role = request.getParameter("role");
		String id = request.getParameter("id");

		if (StringUtils.paramSet(username) && StringUtils.paramSet(role)
				&& StringUtils.paramSet(id)) {
			String sql = "UPDATE " + Database.USERS_TABLENAME
					+ " SET `username` = ? , `role` = ? ";
			if (StringUtils.paramSet(password)
					&& StringUtils.paramSet(cpassword)) {
				if (password.trim().equals(cpassword.trim()))
					sql += ", `password` = ? ";
			}
			  
			sql += "WHERE `id` = ?";

			try {
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				//LOGGER.info(sql );
				statement.setString(1, username.trim());
				statement.setInt(2, Integer.valueOf(role.trim()));
				
				 
				if (StringUtils.paramSet(password)
						&& StringUtils.paramSet(cpassword)) {
					if (password.trim().equals(cpassword.trim()))
						statement.setString(3,
								EncryptionHelper.getInstance().encrypt(password));
				}
				statement.setInt(4, Integer.valueOf(id.trim()));
				
				if (statement.executeUpdate() != 0) {
					return new Auth(Long.valueOf(id.trim()), username,
							Integer.valueOf(role.trim()));
				}
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				DBConnection.closeAllDBUsage(null, statement, null);
			}
		}

		return null;
	}

	/**
	 * 
	 * <p>
	 * Delete Auth
	 * </p>
	 * 
	 * @param request
	 * @param connection
	 */
	public static void deleteAuth(HttpServletRequest request,
			Connection connection) {

		String id = request.getParameter("id");
		if (StringUtils.paramSet(id)) {
			String sql = "DELETE from " + Database.USERS_TABLENAME
					+ " WHERE `id` = ?";
			try {
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);
				statement.setInt(1, Integer.valueOf(id.trim()));
				statement.executeUpdate();
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				DBConnection.closeAllDBUsage(null, statement, null);
			}
		}
	}

	/**
	 * 
	 * <p>
	 * List Auths
	 * </p>
	 * 
	 * @param connection
	 * @return
	 */
	public static List<Auth> listAuths(Connection connection) {

		List<Auth> auths = new ArrayList<Auth>();
		String sql = "SELECT a.`id`, a.`username`, a.`role`, r.`role` AS rolename FROM "
				+ Database.USERS_TABLENAME
				+ " a LEFT JOIN "
				+ Database.ROLE_TABLENAME + " r ON (a.`role` = r.`id`)";
		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			resultSet = statement.executeQuery();
			Auth auth = null;
			while (resultSet.next()) {
				auth = new Auth(resultSet.getLong("id"),
						resultSet.getString("username"),
						resultSet.getInt("role"));
				auth.setRoleName(resultSet.getString("rolename"));
				auths.add(auth);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return auths;
	}

	/**
	 * 
	 * <p>
	 * List Auth by Role
	 * </p>
	 * 
	 * @param rolename
	 * @param connection
	 * @return
	 */
	public static List<Auth> listAuthsByRole(String rolename,
			Connection connection) {
		List<Auth> auths = new ArrayList<Auth>();
		String sql = "SELECT a.`id`, a.`username`, a.`role`, r.`role` AS rolename FROM "
				+ Database.USERS_TABLENAME
				+ " a LEFT JOIN "
				+ Database.ROLE_TABLENAME
				+ " r ON (a.`role` = r.`id`) WHERE r.`role` = ?";
		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			statement.setString(1, rolename);
			resultSet = statement.executeQuery();
			Auth auth = null;
			while (resultSet.next()) {
				auth = new Auth(resultSet.getLong("id"),
						resultSet.getString("username"),
						resultSet.getInt("role"));
				auth.setRoleName(resultSet.getString("rolename"));
				auths.add(auth);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return auths;
	}

}