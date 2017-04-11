/**
 * MD5.java
 * com.rising.mobilepayment.commom
 * 工程：MobilePayMent
 * 功能： TODO 
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * chapterless     2013-5-30   上午10:10:15
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.detachment.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String encryptByMD5(String plainText) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(plainText.getBytes());
		String ciphertext = new String(md5.digest());
		return ciphertext;
	}

	public static String encryptByMD5With16Bit(String plainText)
			throws NoSuchAlgorithmException {
		return encryptByMD5With32Bit(plainText).substring(8, 24);
	}

	public static String encryptByMD5With32Bit(String plainText) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md5.update(plainText.getBytes());
		byte b[] = md5.digest();
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		try {
			System.out.println(MD5.encryptByMD5("admin"));
			System.out.println(MD5.encryptByMD5With16Bit("admin"));
			System.out.println(MD5.encryptByMD5With32Bit("admin"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
