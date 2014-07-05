package com.bryma.auction_manager.web.authentication;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bryma.auction_manager.service.AuthService;
import com.bryma.auction_manager.service.ComponentService;
import com.bryma.auction_manager.web.beans.Auth;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.ResponseUtils;
import com.bryma.auction_manager.web.utils.SessionUtils;
import com.bryma.auction_manager.web.utils.UrlUtils;

/**
 * Authenticate Servlet, receives login credentials from the user and
 * authenticates him/her
 * <p>
 * 
 * @author <a href="mailto:peter@inmobia.com">Peter Maingi</a>
 * @version 1.0, September 11, 2013
 * @since jdk 1.6
 */

public class AuthServlet extends HttpServlet {

	private static final long serialVersionUID = 8224434036304380428L;

	//private static final Logger LOGGER = Logger
			//.getLogger(AuthServlet.class);
	private Connection connection = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/login.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		connection = DBConnection.getConnection();
		//LOGGER.info("am connected" + connection.toString());
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put("error", "Incorrect Username and or Password");

		Auth auth = AuthService.authorize(request, connection);
		if (auth != null) {
			// Set session
			SessionUtils.setSession(request, SessionUtils.AUTHSESSION, auth);
			SessionUtils.setSession(request, SessionUtils.COMPSESSION,
					ComponentService.listComponentsByRole(auth.getRole(),
							connection));
			resp.put("error", false);
			resp.put("message", "Login Successful");
			resp.put("url", UrlUtils.getServerURL(request) + "/home");
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