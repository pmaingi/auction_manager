package com.bryma.auction_manager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;

import com.bryma.auction_manager.web.beans.Role;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.Database;
import com.bryma.auction_manager.web.utils.StringUtils;

/**
 * 
 * RoleServlet
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 13 Sept 2013
 * @since enter jdk version
 */

public class RoleService {

	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;

	

	private static final Logger LOGGER = Logger.getLogger(RoleService.class);

	/**
	 * 
	 * <p>
	 * Get Role by ID
	 * </p>
	 * 
	 * @param id
	 * @param connection
	 * @return
	 */
	public static Role getRole(long id, Connection connection) {

		Role role = null;
		String sql = "SELECT * from " + Database.ROLE_TABLENAME + " WHERE `id` = ?";
		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				role = new Role(id, resultSet.getString("role"),
						resultSet.getString("components"));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return role;
	}

	/**
	 * 
	 * <p>
	 * List Roles
	 * </p>
	 * 
	 * @param connection
	 * @return
	 */
	public static List<Role> listRoles(Connection connection) {

		List<Role> roles = new ArrayList<Role>();
		String sql = "SELECT * from " + Database.ROLE_TABLENAME;
		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				roles.add(new Role(resultSet.getLong("id"), resultSet
						.getString("role"), resultSet.getString("components")));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return roles;
	}

	/**
	 * 
	 * <p>
	 * List Roles and component names
	 * </p>
	 * 
	 * @param connection
	 * @return
	 */
	public static List<Role> listRolesAndComponentNames(Connection connection) {

		List<Role> roles = new ArrayList<Role>();
		String sql = "SELECT * from " + Database.ROLE_TABLENAME;
		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				roles.add(new Role(resultSet.getLong("id"), resultSet
						.getString("role"), ComponentService.componentNames(
						resultSet.getString("components"), connection)));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return roles;
	}

	/**
	 * 
	 * <p>
	 * Save Role
	 * </p>
	 * 
	 * @param connection
	 * @param request
	 * @return
	 */
	public static Map<String, Object> saveRole(Connection connection,
			HttpServletRequest request) {

		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("error", true);
		resp.put("error", "Error saving. Please try again");
		String name = request.getParameter("name");
		String components = request.getParameter("components");
		
		if (StringUtils.paramSet(name) && StringUtils.paramSet(components)) {
			String sql = "INSERT INTO " + Database.ROLE_TABLENAME
					+ "(`role`,`components`) VALUES(?,?)";
			try {
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);
				statement.setString(1, name);
				statement.setString(2, StringUtils
						.jsonArrayToString(new JSONArray(components)));
				if(statement.executeUpdate() != 0){
					resp.put("error", false);
					resp.put("message", "Saved " + name);
					resp.put("url", "roles");
					resp.put("page", "All Roles");
				}
			} catch (SQLException e) {
				LOGGER.error(e);
			} catch (JSONException e) {
				LOGGER.error(e);
			} finally {
				DBConnection.closeAllDBUsage(null, statement, null);
			}
		}

		return resp;
	}
}