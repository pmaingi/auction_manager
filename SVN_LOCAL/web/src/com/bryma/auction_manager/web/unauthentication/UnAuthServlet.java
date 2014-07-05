package com.bryma.auction_manager.web.unauthentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bryma.auction_manager.web.utils.UrlUtils;

/**
 * 
 * UnAuthServlet
 * Handle Logouts
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 7 Aug 2013
 * @since enter jdk version
 */
public class UnAuthServlet extends HttpServlet {

	private static final long serialVersionUID = 4245872664836977266L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(UrlUtils.getServerURL(request));
	}
}