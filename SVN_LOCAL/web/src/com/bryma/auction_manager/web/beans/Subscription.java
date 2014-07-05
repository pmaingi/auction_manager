package com.bryma.auction_manager.web.beans;

public class Subscription {

	private long id;

	private String msisdn;

	private String productcode;

	private int amount;

	private int regStatus;

	private String dateCreated;

	/**
	 * @param msisdn
	 * @param productcode
	 * @param amount
	 * @param regStatus
	 * @param dateCreated
	 */
	public Subscription(long id, String msisdn, String productcode, int amount,
			int regStatus, String dateCreated) {

		super();
		this.id = id;
		this.msisdn = msisdn;
		this.productcode = productcode;
		this.amount = amount;
		this.regStatus = regStatus;
		this.dateCreated = dateCreated;
	}

	public Subscription() {

		super();

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
	 * @return the amount
	 */
	public int getAmount() {

		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {

		this.amount = amount;
	}

	/**
	 * @return the regStatus
	 */
	public int getRegStatus() {

		return regStatus;
	}

	/**
	 * @param regStatus
	 *            the regStatus to set
	 */
	public void setRegStatus(int regStatus) {

		this.regStatus = regStatus;
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
