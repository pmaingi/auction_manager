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

import com.bryma.auction_manager.service.ComponentService;
import com.bryma.auction_manager.service.RoleService;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.ResponseUtils;
import com.bryma.auction_manager.web.utils.StringUtils;

/**
 * 
 * RoleServlet
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 23 Sep 2013
 * @since enter jdk version
 */
public class RoleServlet extends HttpServlet {

	private static final long serialVersionUID = -4814190624396394754L;

	private Connection connection = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		connection = DBConnection.getConnection();
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/roles/all.jsp");

		String create = request.getParameter("create");
		if (StringUtils.paramSet(create)) {
			request.setAttribute("COMPONENTS",
					ComponentService.listAllComponents(connection));
			dispatcher = request.getRequestDispatcher("jsp/roles/create.jsp");

		} else {
			request.setAttribute("ROLES",
					RoleService.listRolesAndComponentNames(connection));
		}
		dispatcher.include(request, response);
		DBConnection.closeConnection(connection);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if (StringUtils.paramSet(action)) {
			connection = DBConnection.getConnection();
			Map<String, Object> resp = new HashMap<String, Object>();
			resp.put("error", "Please fill in all fields");
			if (action.equalsIgnoreCase("save")) {
				resp = RoleService.saveRole(connection, request);
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
