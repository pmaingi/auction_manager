package com.bryma.auction_manager.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 
 * EncryptionHelper
 * 
 * Encrypts passwords
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 7 Aug 2013
 * @since enter jdk version
 */
public class EncryptionHelper {

	private static EncryptionHelper instance;

	public static synchronized EncryptionHelper getInstance() {

		if (instance == null)
			instance = new EncryptionHelper();
		return instance;
	}

	public synchronized String encrypt(String plaintext) {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA"); // step 2
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			md.update(plaintext.getBytes("UTF-8")); // step 3
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte raw[] = md.digest(); // step 4
		String hash = (new BASE64Encoder()).encode(raw); // step 5
		return hash; // step 6
	}

	public static void main(String[] args) {

		System.out.println(getInstance().encrypt("erick"));
	}
}
