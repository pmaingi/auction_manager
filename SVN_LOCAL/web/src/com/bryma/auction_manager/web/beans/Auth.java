package com.bryma.auction_manager.web.beans;

/**
 * 
 * Auth Bean Holds Authorization Data
 * 
 * @author <a href="mailto:enter email address">Paul</a>
 * @version enter version, 28 Jun 2012
 * @since jdk 1.6
 */
public class Auth {

	private long id;

	private String username;

	private int role;

	private String roleName;

	public Auth() {

	}

	/**
	 * @param id
	 * @param username
	 * @param components
	 */
	public Auth(long id, String username, int role) {

		super();
		this.id = id;
		this.username = username;
		this.role = role;

	}

	/**
	 * @return the id
	 */
	public long getId() {

		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {

		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {

		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {

		this.username = username;
	}

	/**
	 * @return the role
	 */
	public int getRole() {

		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(int role) {

		this.role = role;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {

		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {

		this.roleName = roleName;
	}

}