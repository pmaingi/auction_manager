package com.bryma.auction_manager.web.beans;

/**
 * 
 * Role
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 15 September 2013
 * @since  enter jdk version
 */
public class Role {
	
	private long id;
	
	private String role;
	
	private String components;
	
	
	public Role(){
		
	}
	
	/**
	 * @param id
	 * @param role
	 * @param components
	 */
	public Role(long id, String role, String components) {

		super();
		this.id = id;
		this.role = role;
		this.components = components;
	}


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
	 * @return the role
	 */
	public String getRole() {
	
		return role;
	}

	
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
	
		this.role = role;
	}

	
	/**
	 * @return the components
	 */
	public String getComponents() {
	
		return components;
	}

	
	/**
	 * @param components the components to set
	 */
	public void setComponents(String components) {
	
		this.components = components;
	}
}
