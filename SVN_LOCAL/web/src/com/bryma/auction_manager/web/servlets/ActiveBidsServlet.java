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

import com.bryma.auction_manager.service.ActiveBidsService;
import com.bryma.auction_manager.service.AuctionService;
import com.bryma.auction_manager.service.ProductService;
import com.bryma.auction_manager.web.beans.ActiveBids;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.ResponseUtils;
import com.bryma.auction_manager.web.utils.StringUtils;
import com.bryma.auction_manager.web.utils.UrlUtils;

/**
 * Active Bids operations
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 13 Dec 2013
 * @since enter jdk version
 */
public class ActiveBidsServlet extends HttpServlet {

	private static final long serialVersionUID = 2876242327508470067L;
	private static final Logger LOGGER = Logger.getLogger(ActiveBidsServlet.class);

	private Connection connection = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		connection = DBConnection.getConnection();
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/activebids/all.jsp");

		request.setAttribute("ACTIVEBIDDERS",
				ActiveBidsService.listAllActiveBids(connection, request));

		
		dispatcher.include(request, response);
		
		DBConnection.closeConnection(connection);
	}

	
}
