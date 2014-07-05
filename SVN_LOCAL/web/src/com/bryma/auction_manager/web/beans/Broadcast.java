package com.bryma.auction_manager.web.beans;

public class Broadcast {

	private long id;

	private String msisdn;

	private String message;

	/**
	 * @param id
	 * @param msisdn
	 * @param message
	 */
	public Broadcast(long id, String msisdn, String message) {

		super();
		this.id = id;
		this.msisdn = msisdn;
		this.message = message;
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
	 * @return the message
	 */
	public String getMessage() {

		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {

		this.message = message;
	}

}
