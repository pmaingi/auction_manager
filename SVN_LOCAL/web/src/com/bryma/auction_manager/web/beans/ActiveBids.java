package com.bryma.auction_manager.web.beans;

public class ActiveBids {

	private long id;
	private String productcode;
	private String productname;
	private String msisdn;
	private double amount;
	private int auctionClosed;
	private String dateCreated;
	/**
	 * @param id
	 * @param productcode
	 * @param productname
	 * @param msisdn
	 * @param amount
	 * @param auctionClosed
	 * @param dateCreated
	 */
	public ActiveBids(long id, String productcode, String productname,
			String msisdn, double amount, int auctionClosed, String dateCreated) {

		super();
		this.id = id;
		this.productcode = productcode;
		this.productname = productname;
		this.msisdn = msisdn;
		this.amount = amount;
		this.auctionClosed = auctionClosed;
		this.dateCreated = dateCreated;
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
	 * @return the productname
	 */
	public String getProductname() {

		return productname;
	}

	/**
	 * @param productname
	 *            the productname to set
	 */
	public void setProductname(String productname) {

		this.productname = productname;
	}

	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {

		return msisdn;
	}

	/**
	 * @param msisdn
	 *            the msisdn to set
	 */
	public void setMsisdn(String msisdn) {

		this.msisdn = msisdn;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {

		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {

		this.amount = amount;
	}

	/**
	 * @return the auctionClosed
	 */
	public int getAuctionClosed() {

		return auctionClosed;
	}

	/**
	 * @param auctionClosed
	 *            the auctionClosed to set
	 */
	public void setAuctionClosed(int auctionClosed) {

		this.auctionClosed = auctionClosed;
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