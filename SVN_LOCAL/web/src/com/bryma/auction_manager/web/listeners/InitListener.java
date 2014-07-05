package com.bryma.auction_manager.web.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
 * InitListener configures log4j
 * 
 * @author <a href="mailto:peter@inmobia.com">Peter Maingi</a>
 * @version 1.0, August 27, 2013
 * @since jdk 1.6
 */
public class InitListener implements ServletContextListener {

	/**
	 * PATH_SEPARATOR, file separator Unix(/), Windows(\)
	 */

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String prefix = servletContextEvent.getServletContext()
				.getContextPath();
		ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.setAttribute("PREFIX", prefix);

	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

}
