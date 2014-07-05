
package com.bryma.auction_manager.web.beans;

import java.util.Date;

/**
 * 
 * <p>
 * <code>User</code> JavaBean representing a user to access the resource
 * </p>
 * 
 * @author <a href="mailto:peter@inmobia.com">Peter Maingi</a>
 * @version 1.0, August 27, 2013
 * @since jdk 1.6
 */
public class User {

	/**
	 * id, user's account id
	 */
	private long id;
	/**
	 * userName, user's account user name
	 */
	private String userName;
	/**
	 * password, user's account password
	 */
	private String password;
	/**
	 * email, user's email address
	 */
	private Date date_created;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets and returns the user's user name
	 * 
	 * @return<code>String</code>
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets and returns the user's password
	 * 
	 * @return<code>String</code>
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password
	 * 
	 * @param <code>String</code>password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the date_created
	 */
	public Date getDate_created() {
		return date_created;
	}

	/**
	 * @param date_created the date_created to set
	 */
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	
}
