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

import com.bryma.auction_manager.service.AuthService;
import com.bryma.auction_manager.service.RoleService;
import com.bryma.auction_manager.web.beans.Auth;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.ResponseUtils;
import com.bryma.auction_manager.web.utils.StringUtils;
import com.bryma.auction_manager.web.utils.UrlUtils;

/**
 * 
 * UserServlet Manages Admin Users
 * 
 * @author <a href="mailto:enter email address">Paul</a>
 * @version enter version, 2 Jul 2012
 * @since enter jdk version
 */
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = -6598860146522611104L;

	private Connection connection = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/admin/all.jsp");
		connection = DBConnection.getConnection();
		
		String create = request.getParameter("create");
		String update = request.getParameter("edit");
		String delete = request.getParameter("delete");
		
		if (StringUtils.paramSet(create)) {
			dispatcher = request.getRequestDispatcher("jsp/admin/create.jsp");
			request.setAttribute("ROLES", RoleService.listRoles(connection));
			
		} else if (StringUtils.paramSet(update)) {
			dispatcher = request
					.getRequestDispatcher("jsp/admin/edit.jsp");
			String id = request.getParameter("id");
			if (StringUtils.paramSet(id))
				request.setAttribute("ADMIN",
						AuthService.getAuthById(Long.valueOf(id), connection));
			request.setAttribute("ROLES", RoleService.listRoles(connection));
			
		} else if (StringUtils.paramSet(delete)) {
			AuthService.deleteAuth(request, connection);
			request.setAttribute("ADMINS", AuthService.listAuths(connection));
		} else {
			request.setAttribute("ADMINS", AuthService.listAuths(connection));
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
			
			if (action.equalsIgnoreCase("create")) {
				Auth auth = AuthService.saveAuth(request, connection);
				if (auth != null) {
					resp.put("error", false);
					//resp.put("message", auth.getUsername()
					//		+ " Saved Successful");
					resp.put("message", " Saved Successful");
					resp.put("url", UrlUtils.getServerURL(request) + "/home");
				}
			} else if (action.equalsIgnoreCase("edit")) {
				Auth auth = AuthService.updateAuth(request, connection);
				if (auth != null) {
					resp.put("error", false);
					resp.put("message", " Updated Successful");
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