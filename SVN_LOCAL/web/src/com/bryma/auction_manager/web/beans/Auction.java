package com.bryma.auction_manager.web.beans;


public class Auction {

	private long id;
	private String productcode;
	private String description;
	private int isopen;
	private String dateCreated;
	private String endDate;
	/**
	 * @param id
	 * @param productcode
	 * @param description
	 * @param isopen
	 * @param dateCreated
	 * @param endDate
	 */
	public Auction(long id, String productcode, String description, int isopen,
			 String dateCreated, String endDate) {

		super();
		this.id = id;
		this.productcode = productcode;
		this.description = description;
		this.isopen = isopen;
		this.dateCreated = dateCreated;
		this.endDate = endDate;
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
	 * @return the productcode
	 */
	public String getProductcode() {

		return productcode;
	}

	/**
	 * @param productcode
	 *            the productcode to set
	 */
	public void setProductcode(String productcode) {

		this.productcode = productcode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {

		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {

		this.description = description;
	}

	
	
	/**
	 * @return the isopen
	 */
	public int getIsopen() {
	
		return isopen;
	}

	
	/**
	 * @param isopen the isopen to set
	 */
	public void setIsopen(int isopen) {
	
		this.isopen = isopen;
	}

	/**
	 * @return the dateCreated
	 */
	public String getDateCreated() {

		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(String dateCreated) {

		this.dateCreated = dateCreated;
	}

	/**
	 * @return the startDate
	 */
	
	/**
	 * @return the endDate
	 */
	public String getEndDate() {

		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {

		this.endDate = endDate;
	}

}
