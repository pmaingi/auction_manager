package com.bryma.auction_manager.customtag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.bryma.auction_manager.web.beans.Component;
import com.bryma.auction_manager.web.utils.SessionUtils;


/**
 * 
 * ComponentTag Custom Tag
 * Checks if user has selected component
 * 
 * @author <a href="mailto:pmaingi@gmail.com">Peter</a>
 * @version enter version, 14 April 2016
 * @since  enter jdk version
 */
public class ComponentTag extends BodyTagSupport {

	private static final long serialVersionUID = -1792307393795360702L;

	String component;

	/**
	 * @param component
	 * the component to set
	 */
	public void setComponent(String component) {

		this.component = component.toLowerCase();
	}
       /**
        * do after body
        *
        */ 
	public int doAfterBody() throws JspException {

		BodyContent bodyContent = super.getBodyContent();
		String bodyString = bodyContent.getString();
		JspWriter out = bodyContent.getEnclosingWriter();

		boolean hasResource = false;
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		List<Component> components = SessionUtils.listComponentsInSession(request);
		for(Component comp : components){
			String id = String.valueOf(comp.getId());
			if(component.equalsIgnoreCase(id)){
				hasResource = true;
				break;
			}
		}
		
		if (!hasResource) {
			try {
				out.print("");
			} catch (IOException e) {
				System.err.println(e);
			}
		} else {
			try {
				out.print(bodyString);
			} catch (IOException e) {
				System.err.println(e);
			}

		}
		return SKIP_BODY;
	}

}
