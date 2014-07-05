package com.bryma.auction_manager.web.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bryma.auction_manager.service.ProductService;
import com.bryma.auction_manager.web.beans.Product;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.ResponseUtils;
import com.bryma.auction_manager.web.utils.StringUtils;
import com.bryma.auction_manager.web.utils.UrlUtils;

/**
 * product CRUD operations
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 13 Dec 2013
 * @since enter jdk version
 */
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 2876242327508470067L;
	private static final Logger LOGGER = Logger.getLogger(ProductServlet.class);

	private Connection connection = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		connection = DBConnection.getConnection();
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/pages/all.jsp");

		request.setAttribute("PRODUCTS",
				ProductService.listAllProducts(connection, request));

		String create = request.getParameter("create");
		String edit = request.getParameter("edit");
		String add = request.getParameter("add");

		if (StringUtils.paramSet(create)) {

			request.setAttribute("PRODUCTS",
					ProductService.saveProduct(request, connection));
			dispatcher = request.getRequestDispatcher("jsp/pages/create.jsp");

		} else if (StringUtils.paramSet(edit)) {
			String id = request.getParameter("id");
			if (StringUtils.paramSet(id)) {
				request.setAttribute("PRODUCTS", ProductService.getProductById(
						Integer.valueOf(id), connection));
			}
			dispatcher = request.getRequestDispatcher("jsp/pages/edit.jsp");
		} else if (StringUtils.paramSet(add)) {

			String id = request.getParameter("id");
			if (StringUtils.paramSet(id)) {
				request.setAttribute("PRODUCTS", ProductService.getProductInAuctionById(Integer.valueOf(id), connection));
						
			}
			dispatcher = request.getRequestDispatcher("jsp/pages/add.jsp");
		}
		/*
		 * else { request.setAttribute("PRODUCTS",
		 * ProductService.listAllProducts(connection, request)); }
		 */
		dispatcher.include(request, response);
		DBConnection.closeConnection(connection);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (StringUtils.paramSet(action)) {

			connection = DBConnection.getConnection();

			Map<String, Object> resp = new HashMap<String, Object>();
			resp.put("error", "Please fill in all fields");

			if (action.equalsIgnoreCase("create")) {
				Product product = ProductService.saveProduct(request,
						connection);

				// LOGGER.info("product being inserted..: " + product);
				if (product != null) {
					resp.put("error", false);
					resp.put("message", " Saved Successful");
					resp.put("url", UrlUtils.getServerURL(request) + "/home");

				}
			} else if (action.equalsIgnoreCase("edit")) {

				Product product = ProductService.updateProduct(request,
						connection);

				// LOGGER.info("product..: " + product.toString());

				if (product != null) {
					resp.put("error", false);
					resp.put("message", " Updated Successful");
					resp.put("url", UrlUtils.getServerURL(request) + "/home");

				}
			} else if (action.equalsIgnoreCase("add")) {

				Product product =
						ProductService.updateProductToAuction(request,
						connection);

				//LOGGER.info("added to auction..: " + product.toString());

				if (product != null) {
					resp.put("error", false);
					resp.put("message", " Added Successful");
					resp.put("url", UrlUtils.getServerURL(request) + "/home");

				}
			}

			try {
				ResponseUtils.writeJson(resp, response);
			} catch (Exception e) {
				response.setContentType("application/json");
				response.getWriter()
						.write("{\"error\" : \"An error occured please try again. If the error persists, please refresh your browser \"}");
			}
			DBConnection.closeConnection(connection);
		}
	}

}
