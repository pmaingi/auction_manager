package com.bryma.auction_manager.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bryma.auction_manager.web.beans.Auth;
import com.bryma.auction_manager.web.utils.SessionUtils;
import com.bryma.auction_manager.web.utils.UrlUtils;

/**
 * 
 * @author <a href="mailto:enter email address">Peter Maingi</a>
 * @version enter version, 15 September 2013
 * @since jdk 1.6
 */
public class SecurityFilter implements Filter {

	private FilterConfig filterConfig = null;

	private HttpServletRequest request;
	private HttpServletResponse response;

	public void destroy() {

		this.filterConfig = null;
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		if (filterConfig == null)
			return;
		request = (HttpServletRequest) servletRequest;
		response = (HttpServletResponse) servletResponse;

		Auth auth = null;
		try {
			auth = SessionUtils.getAuthSession(request);
		} catch (NullPointerException e) {

		}
		if (UrlUtils.isResource(request)) {
			filterChain.doFilter(request, response);
		} else {
			if (auth != null) {
				filterChain.doFilter(request, response);
			} else {
				try {
					request.getRequestDispatcher("/login").forward(
							servletRequest, servletResponse);
				} catch (Exception e) {
					filterChain.doFilter(request, response);
				}
				// response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
	}
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
	}
}
