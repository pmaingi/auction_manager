package com.bryma.auction_manager.web.beans;

/**
 * 
 * @author <a href="mailto:enter email address">Peter Maingi</a>
 * @version enter version, 15 September 2013
 * @since jdk 1.6
 */
public class Component {
	private long id;
	
	private String name;
	
	
	public Component(){
		
	}
	
	/**
	 * @param id
	 * @param name
	 */
	public Component(long id, String name) {

		super();
		this.id = id;
		this.name = name;
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
	 * @return the name
	 */
	public String getName() {
	
		return name;
	}

	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
	
		this.name = name;
	}
	
}