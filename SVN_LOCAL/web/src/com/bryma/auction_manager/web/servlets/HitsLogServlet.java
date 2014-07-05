package com.bryma.auction_manager.web.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bryma.auction_manager.service.HitsLogService;
import com.bryma.auction_manager.web.utils.DBConnection;

/**
 * All hits on the app
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 13 Dec 2013
 * @since enter jdk version
 */
public class HitsLogServlet extends HttpServlet {

	private static final long serialVersionUID = 2876242327508470067L;
	private static final Logger LOGGER = Logger.getLogger(HitsLogServlet.class);

	private Connection connection = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		connection = DBConnection.getConnection();
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/hitslog/all.jsp");

		request.setAttribute("HITSLOG",
				HitsLogService.listAllHitsLogs(connection, request));

		dispatcher.include(request, response);
		DBConnection.closeConnection(connection);
	}

}
