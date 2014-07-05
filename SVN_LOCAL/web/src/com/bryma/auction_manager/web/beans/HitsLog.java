package com.bryma.auction_manager.web.beans;

public class HitsLog {

	private long id;
	private String msisdn;
	private String keyword;
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
	public HitsLog(long id, String msisdn, String keyword, String dateCreated) {

		super();
		this.id = id;
		this.msisdn = msisdn;
		this.keyword = keyword;
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
	 * @return the keyword
	 */
	public String getKeyword() {

		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {

		this.keyword = keyword;
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