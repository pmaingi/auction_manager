package com.bryma.auction_manager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bryma.auction_manager.web.beans.Component;
import com.bryma.auction_manager.web.beans.Role;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.Database;
import com.bryma.auction_manager.web.utils.StringUtils;

/**
 * 
 * @author <a href="mailto:enter email address">Peter Maingi</a>
 * @version enter version, 15 September 2013
 * @since jdk 1.6
 */
public class ComponentService {

	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;

	private static final Logger LOGGER = Logger
			.getLogger(ComponentService.class);

	/**
	 * 
	 * <p>
	 * List component by Role
	 * </p>
	 * 
	 * @param roleid
	 * @param connection
	 * @return
	 */
	public static List<Component> listComponentsByRole(int roleid,
			Connection connection) {

		List<Component> components = new ArrayList<Component>();
		Role role = RoleService.getRole(roleid, connection);
		String[] componentIds = StringUtils.stringArray(role.getComponents());
		for (String id : componentIds) {
			components.add(getComponentById(Long.valueOf(id), connection));
		}
		return components;
	}

	/**
	 * 
	 * <p>
	 * Get Componenet by ID
	 * </p>
	 * 
	 * @param id
	 * @param connection
	 * @return
	 */
	public static Component getComponentById(long id, Connection connection) {

		Component component = null;
		String sql = "SELECT * FROM " + Database.COMPONENTTABLENAME
				+ " WHERE `id` = ?";
		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				component = new Component(id, resultSet.getString("name"));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return component;
	}

	/**
	 * 
	 * <p>
	 * List all Components
	 * </p>
	 * 
	 * @param connection
	 * @return
	 */
	public static List<Component> listAllComponents(Connection connection) {

		List<Component> components = new ArrayList<Component>();
		String sql = "SELECT * FROM " + Database.COMPONENTTABLENAME;
		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				components.add(new Component(resultSet.getLong("id"), resultSet
						.getString("name")));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return components;
	}

	/**
	 * 
	 * <p>
	 * Get component names
	 * </p>
	 * 
	 * @param ids
	 * @param connection
	 * @return
	 */
	public static String componentNames(String ids, Connection connection) {

		String components = null;
		if (StringUtils.paramSet(ids)) {
			String sql = "SELECT GROUP_CONCAT(name) AS components FROM "
					+ Database.COMPONENTTABLENAME + " WHERE `id` IN(" + ids
					+ ")";
			try {
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
					components = resultSet.getString("components");
				}
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				DBConnection.closeAllDBUsage(resultSet, statement, null);
			}
		}
		return components;
	}
}