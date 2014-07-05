package com.bryma.auction_manager.web.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bryma.auction_manager.service.ComponentService;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.StringUtils;

/**
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 7 Aug 2013
 * @since enter jdk version
 */
public class ComponentServlet extends HttpServlet {

	private static final long serialVersionUID = 2876242327508470067L;

	private static final Logger LOGGER = Logger
			.getLogger(ComponentServlet.class);

	private Connection connection = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		connection = DBConnection.getConnection();

		 RequestDispatcher dispatcher = request
		 .getRequestDispatcher("pages/all.jsp");
		 dispatcher.include(request, response);

		String id = request.getParameter("id");
		String name = request.getParameter("name");

		if (StringUtils.paramSet(id)) {

			request.setAttribute("COMPONENT", ComponentService
					.getComponentById(Long.valueOf(id), connection));
		} else if (StringUtils.paramSet(name)) {
			String names = request.getParameter("name");
			if (StringUtils.paramSet(names)) {
				request.setAttribute("COMPONENT",
						ComponentService.listAllComponents(connection));
			}
			 dispatcher = request.getRequestDispatcher("jsp/pages/all.jsp");
		} else {
			request.setAttribute("COMPONENT",
					ComponentService.listAllComponents(connection));
			request.setAttribute("PAGES",
					ComponentService.listAllComponents(connection));
		}
		 dispatcher.include(request, response);

		DBConnection.closeConnection(connection);
	}

}