package com.bryma.auction_manager.web.beans;

/**
 * 
 * Add a brief description of Product
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @verion enter version, 2 Feb 2014
 * @since enter jdk version
 */
public class Product {

	private long id;
	private String productname;
	private String code;
	private String description;
	private int startPrice;
	private int registrationFee;
	private int addAuction ;
	private String dateCreated;

	public Product() {

	}
	public Product(long id, String productname, String code,
			String description, int startPrice ,int registrationFee,int addAuction, String dateCreated) {// , double startPrice,int
														// registrationFee,

		// registrationFee
		super();
		this.id = id;
		this.productname = productname;
		this.code = code;
		this.description = description;
		this.startPrice = startPrice;
		this.registrationFee = registrationFee;
		this.addAuction = addAuction ;
		this.dateCreated = dateCreated;
	}

	
	
	public int getAddAuction() {
	
		return addAuction;
	}
	
	public void setAddAuction(int addAuction) {
	
		this.addAuction = addAuction;
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
	 * @return the productName
	 */
	public String getProductname() {

		return productname;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductname(String productname) {

		this.productname = productname;
	}

	/**
	 * @return the code
	 */
	public String getCode() {

		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {

		this.code = code;
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
	 * @return the startPrice
	 */
	public int getStartPrice() {
	
		return startPrice;
	}
	
	/**
	 * @param startPrice the startPrice to set
	 */
	public void setStartPrice(int startPrice) {
	
		this.startPrice = startPrice;
	}
	
	
	/**
	 * @return the registrationFee
	 */
	public int getRegistrationFee() {
	
		return registrationFee;
	}
	
	/**
	 * @param registrationFee the registrationFee to set
	 */
	public void setRegistrationFee(int registrationFee) {
	
		this.registrationFee = registrationFee;
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

}
