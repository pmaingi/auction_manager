package com.bryma.auction_manager.web.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bryma.auction_manager.web.beans.Auth;
import com.bryma.auction_manager.web.beans.Component;


/**
 * 
 * SessionUtils
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 29 Jun 2012
 * @since  enter jdk version
 */
public class SessionUtils {
	
	public static final String AUTHSESSION = "AUTHSESSION";
	public static final String COMPSESSION = "COMPSESSION";
	
	

	/**
	 * 
	 * <p>
	 * Set session
	 * </p>
	 * @param request
	 * @param sessionKey
	 * @param object
	 */
	public static void setSession(HttpServletRequest request,
			String sessionKey, Object object) {

		request.getSession().setAttribute(sessionKey, object);
	}
	
	/**
	 * 
	 * <p>
	 * Get Auth Session
	 * </p>
	 * @param request
	 * @return
	 */
	public static Auth getAuthSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		return (Auth) session.getAttribute(AUTHSESSION);
	}
	
	
	/**
	 * 
	 * <p>
	 * List Components in session
	 * </p>
	 * @param request
	 * @return
	 */
	public static List<Component> listComponentsInSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
	
		return (List<Component>) session.getAttribute(COMPSESSION);
	}
}
