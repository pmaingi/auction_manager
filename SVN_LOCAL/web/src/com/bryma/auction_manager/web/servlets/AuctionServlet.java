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

import com.bryma.auction_manager.service.AuctionService;
import com.bryma.auction_manager.web.beans.Auction;
import com.bryma.auction_manager.web.utils.DBConnection;
import com.bryma.auction_manager.web.utils.ResponseUtils;
import com.bryma.auction_manager.web.utils.StringUtils;
import com.bryma.auction_manager.web.utils.UrlUtils;

/**
 * Auction CRUD operations
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 13 Dec 2013
 * @since enter jdk version
 */
public class AuctionServlet extends HttpServlet {

	private static final long serialVersionUID = 2876242327508470067L;
	private static final Logger LOGGER = Logger.getLogger(AuctionServlet.class);

	private Connection connection = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		connection = DBConnection.getConnection();
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/auctions/all.jsp");

		 request.setAttribute("AUCTIONS",
				 AuctionService.listAllAuctions(connection, request));

		String create = request.getParameter("create");
		String edit = request.getParameter("edit");

		if (StringUtils.paramSet(create)) {

			request.setAttribute("AUCTIONS",
					AuctionService.saveAuction(request, connection));
			dispatcher = request.getRequestDispatcher("jsp/auctions/create.jsp");
		} else if (StringUtils.paramSet(edit)) {
			String id = request.getParameter("id");
			if (StringUtils.paramSet(id)) {
				request.setAttribute("AUCTIONS", AuctionService.getAuctionById(Integer.valueOf(id), connection));
			}
			dispatcher = request.getRequestDispatcher("jsp/auctions/edit.jsp");
		} 
		else {
			request.setAttribute("AUCTIONS",
					AuctionService.listAllAuctions(connection, request));
		}
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
				Auction auction = AuctionService.saveAuction(request, connection);
				if (auction != null) {
					resp.put("error", false);
					resp.put("message", " Saved Successful");
					resp.put("url", UrlUtils.getServerURL(request) + "/auction");

				}
			} else if (action.equalsIgnoreCase("edit")) {

				Auction auction = AuctionService.updateAuction(request, connection);

				if (auction != null) {
					resp.put("error", false);
					resp.put("message", " Updated Successful");
					resp.put("url", UrlUtils.getServerURL(request) + "/auction");

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
