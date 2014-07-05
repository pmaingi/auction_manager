package com.bryma.auction_manager.web.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bryma.auction_manager.service.ProductService;
import com.bryma.auction_manager.web.utils.DBConnection;

/**
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 7 Aug 2013
 * @since enter jdk version
 */
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 2876242327508470067L;

	private Connection connection = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		connection = DBConnection.getConnection();

		request.setAttribute("PRODUCTS",
				ProductService.listAllProducts(connection, request)); 
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/home.jsp");
		dispatcher.include(request, response);

		DBConnection.closeConnection(connection);
	}

}