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

import com.bryma.auction_manager.service.BroadcastService;
import com.bryma.auction_manager.web.beans.Broadcast;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.ResponseUtils;
import com.bryma.auction_manager.web.utils.StringUtils;
import com.bryma.auction_manager.web.utils.UrlUtils;

/**
 * 
 * BroadcastServlet, helper class for sending a message
 * 
 * @author <a href="mailto:enter email address">peter</a>
 * @verion enter version, 14 Mar 2014
 * @since  enter jdk version
 */
public class BroadcastServlet extends HttpServlet {


	private static final long serialVersionUID = -6598860146522611104L;

	private Connection connection = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/broadcast/create.jsp");
		connection = DBConnection.getConnection();
		
		String create = request.getParameter("create");
				
		if (StringUtils.paramSet(create)) {
			dispatcher = request.getRequestDispatcher("jsp/broadcast/create.jsp");
			request.setAttribute("BROADCAST", BroadcastService.saveBroadcast(request, connection));
			
		} 
		dispatcher.include(request, response);
		
		DBConnection.closeConnection(connection);
	}

    
	/**
	 * dopost
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if (StringUtils.paramSet(action)) {
			connection = DBConnection.getConnection();
			Map<String, Object> resp = new HashMap<String, Object>();
			resp.put("error", "Please fill in all fields");
			
			if (action.equalsIgnoreCase("create")) {
				Broadcast broadcast = BroadcastService.saveBroadcast(request, connection);
				if (broadcast != null) {
					resp.put("error", false);
					resp.put("message", " Saved Successful");
					resp.put("url", UrlUtils.getServerURL(request) + "/home");
				}
			} 

			try {
				ResponseUtils.writeJson(resp, response);
			} catch (Exception e) {
				response.setContentType("application/json");
				response.getWriter()
						.write("{\"error\" : \"An error occured please try again. If the error persists, please refresh your browser\"}");
			}
			DBConnection.closeConnection(connection);
		}
	}
}
