package com.bryma.auction_manager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bryma.auction_manager.web.beans.Product;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.Database;
import com.bryma.auction_manager.web.utils.StringUtils;

/**
 * 
 * @author <a href="mailto:enter email address">Peter Maingi</a>
 * @version enter version, 15 September 2013
 * @since jdk 1.6
 */
public class ProductService {

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
	public static Product saveProduct(HttpServletRequest request,
			Connection connection) {

		Product products = null;

		// String id = request.getParameter("id");
		String productname = request.getParameter("productname");
		String code = request.getParameter("code");
		String description = request.getParameter("description");
		String startPrice = request.getParameter("startPrice");
		String registrationFee = request.getParameter("registrationFee");
		// String dateCreated = request.getParameter("dateCreated");

		LOGGER.info("productname : " + productname + " code : " + code
				+ " description : " + description);

		if (StringUtils.paramSet(productname) && StringUtils.paramSet(code)
				&& StringUtils.paramSet(description)
				&& StringUtils.paramSet(startPrice)
				&& StringUtils.paramSet(registrationFee)) {

			String sql = "insert into"
					+ Database.PRODUCTTABLENAME1
					+ "(`productname`, `code`, `description`,`startPrice`,`registrationFee`,`dateCreated`) values (?, ?, ?, ?, ?, now())"; // ,`startPrice`,`registrationFee`

			PreparedStatement statement = null;

			try {
				LOGGER.info(sql);
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);
				// statement.setLong(1, Long.valueOf(id));
				statement.setString(1, productname);
				statement.setString(2, code.toUpperCase());
				statement.setString(3, description);
				statement.setString(4, startPrice);
				statement.setString(5, registrationFee);

				if (statement.executeUpdate() != 0) {
					products = new Product(0, productname, code, description,
							Integer.valueOf(startPrice),
							Integer.valueOf(registrationFee), 0, null);

				}

			} catch (SQLException e) {
				LOGGER.info(e.getMessage());

			} finally {

				DBConnection.closeAllDBUsage(null, statement, null);
			}
		}

		return products;
	}

	/**
	 * 
	 * product update
	 * <p>
	 * 
	 * @param request
	 * @param connection
	 * @return
	 */
	public static Product updateProduct(HttpServletRequest request,
			Connection connection) {

		Product products = null;

		String id = request.getParameter("id");
		String productname = request.getParameter("productname");
		String code = request.getParameter("code");
		String description = request.getParameter("description");
		String startPrice = request.getParameter("startPrice");
		String registrationFee = request.getParameter("registrationFee");

		LOGGER.info("productname : " + productname + " code : " + code
				+ " description : " + description + "startPrice : "
				+ startPrice + "registrationFee : " + registrationFee);

		if (StringUtils.paramSet(id) && StringUtils.paramSet(productname)
				&& StringUtils.paramSet(code)
				&& StringUtils.paramSet(description)
				&& StringUtils.paramSet(startPrice)
				&& StringUtils.paramSet(registrationFee)) {

			String sql = "update "
					+ Database.PRODUCTTABLENAME1
					+ " set `productname` = ?, `code`= ? ,`description`= ? ,`startPrice` = ? ,`registrationFee` = ? ,`dateCreated` = now() where `id`= ?"; // ,`startPrice`=
																																							// ?,`registrationFee`
																																							// =
																																							// ?

			PreparedStatement statement = null;
			try {
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);
				// Parameters start with 1

				statement.setString(1, productname);
				statement.setString(2, code);
				statement.setString(3, description);
				statement.setString(4, startPrice);
				statement.setString(5, registrationFee);
				statement.setString(6, id);

				if (statement.executeUpdate() != 0) {
					products = new Product(Long.valueOf(id), productname, code,
							description, Integer.valueOf(startPrice),
							Integer.valueOf(registrationFee), 0, null);
				}

			} catch (SQLException e) {
				LOGGER.error(e);

			} finally {
				DBConnection.closeAllDBUsage(null, statement, null);
			}
		}
		return products;
	}
	
	/**
	 * lists all products
	 * 
	 * @param connection
	 * @param request
	 * @return
	 */
	public static List<Product> listAllProducts(Connection connection,
			HttpServletRequest request) {

		List<Product> products = new ArrayList<Product>();

		String sql = "select * from " + Database.PRODUCTTABLENAME1
				+ "WHERE `addAuction` = 0 order by `id` desc ";

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			resultSet = statement.executeQuery();
			long id = 0;
			String productname = null;
			String code = null;
			String description = null;
			int startPrice = 0;
			int registrationFee = 0;
			String dateCreated = null;

			while (resultSet.next()) {
				id = resultSet.getLong("id");
				productname = resultSet.getString("productname");
				code = resultSet.getString("code");
				description = resultSet.getString("description");
				startPrice = resultSet.getInt("startPrice");
				registrationFee = resultSet.getInt("registrationFee");
				dateCreated = resultSet.getString("dateCreated");

				products.add(new Product(Long.valueOf(id), productname, code,
						description, startPrice, registrationFee, 0,
						dateCreated));
				
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return products;

	}

	/**
	 * gets product by id
	 * 
	 * @param contentId
	 * @param connection
	 * @return
	 */
	public static Product getProductById(int productId, Connection connection) {

		Product products = null;

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {

			String sql = "select * from " + Database.PRODUCTTABLENAME1
					+ " where `addAuction` = 0 AND `id`= ? ";
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			statement.setLong(1, productId);
			resultSet = statement.executeQuery();

			long id = 0;
			String productname = null;
			String code = null;
			String description = null;
			String startPrice = null;
			String registrationFee = null;
			
			while (resultSet.next()) {
				id = resultSet.getLong("id");
				productname = resultSet.getString("productname");
				code = resultSet.getString("code");
				description = resultSet.getString("description");
				startPrice = resultSet.getString("startPrice");
				registrationFee = resultSet.getString("registrationFee");
				
				products = new Product(Long.valueOf(id), productname, code,
						description, Integer.valueOf(startPrice),
						Integer.valueOf(registrationFee), 0, null);
				
				

			}

		} catch (SQLException e) {
			LOGGER.info(e.getMessage());

		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return products;
	}


	/**
	 * gets product from auction by id
	 * 
	 * @param contentId
	 * @param connection
	 * @return
	 */
	public static Product getProductInAuctionById(int productId,
			Connection connection) {

		Product products = null;

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {

			String sql = "select * from " + Database.PRODUCTTABLENAME1
					+ " where `addAuction`= 0  AND `id`= ? ";
			statement = DBConnection.isConnected(connection).prepareStatement(
					sql);
			statement.setLong(1, productId);
			resultSet = statement.executeQuery();

			long id = 0;
			String productname = null;
			String code = null;
			String description = null;
			String startPrice = null;
			String registrationFee = null;
			int addAuction = 0;

			while (resultSet.next()) {
			    id = resultSet.getLong("id");
				productname = resultSet.getString("productname");
				code = resultSet.getString("code");
				description = resultSet.getString("description");
				startPrice = resultSet.getString("startPrice");
				registrationFee = resultSet.getString("registrationFee");
				addAuction = resultSet.getInt("addAuction");

				products = new Product(Long.valueOf(id), productname, code,
						description, Integer.valueOf(startPrice),
						Integer.valueOf(registrationFee), addAuction, null);

				saveToAuction(id ,code, description, connection);
			}

		} catch (SQLException e) {
			LOGGER.info(e.getMessage());

		} finally {
			DBConnection.closeAllDBUsage(resultSet, statement, null);
		}
		return products;
	}

	/**
	 * 
	 * adds a product to auction
	 * <p>
	 * 
	 * @param productId
	 * @param connection
	 * @return
	 */
	public static Product updateProductToAuction(HttpServletRequest request,
			Connection connection) {

		Product products = null;

		String id = request.getParameter("id");
		String addAuction = request.getParameter("addAuction");

		LOGGER.info("id : " + id + " addAuction : " + addAuction);

		PreparedStatement statement = null;

		if (StringUtils.paramSet(id) && StringUtils.paramSet(addAuction)) {

			try {

				String sql = "update " + Database.PRODUCTTABLENAME1
						+ " set `addAuction` = 1 where `id`= ? ";

				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);

				statement.setString(1, id);
			
				if (statement.executeUpdate() != 0) {
					// products = new Product();
					products = new Product(Long.valueOf(id), null, null, null,
							0, 0, 0, null);
				}
				

			} catch (SQLException e) {
				LOGGER.info(e.getMessage());

			} finally {
				DBConnection.closeAllDBUsage(null, statement, null);
			}
			
			

		}
		return products;

	}
	/**
	 * 
	 * save to auction
	 * <p>
	 * @param productid
	 * @param productcode
	 * @param description
	 * @param connection
	 * @return
	 */
	
	public static Product saveToAuction(long productid,
			String productcode,String description, 
			Connection connection) {

		Product products = null;

			String sql = "insert into"
					+ Database.AUCTIONTABLENAME1
					+ "(`productid`,`productcode`, `description`, `isopen`,`dateCreated`,`enddate`) values (?, ?, ?, 1, now(),(NOW() + INTERVAL 5 DAY) )"; 

			PreparedStatement statement = null;

			try {
				//LOGGER.info(sql);
				statement = DBConnection.isConnected(connection)
						.prepareStatement(sql);
				
				statement.setLong(1, productid);
				statement.setString(2, productcode);
				statement.setString(3, description);
				
				if (statement.executeUpdate() != 0) {
				//	products = new Product(0, productcode, description, 
					//		null, 0, 0, 0, null); 

				}

			} catch (SQLException e) {
				LOGGER.info(e.getMessage());

			} finally {

				DBConnection.closeAllDBUsage(null, statement, null);
				}

		return products;
	}

}
