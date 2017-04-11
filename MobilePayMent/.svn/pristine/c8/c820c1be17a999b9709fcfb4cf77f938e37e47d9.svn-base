package com.rising.mobilepayment.commom;

import java.util.Random;

public class SecurityCodeRandom {

	private static char[] codes = { '1', '2', '3', '4', '5', '6', '7', '8',
			'9', '0' };

	private static int codeLength = 6;

	public static String getSecurityCode() {
		String code = "";
		int ran;
		for (int i = 0; i < codeLength; i++) {
			ran = getRandomNumber();
			code = code + codes[ran];
		}
		return code;
	}

	private static int getRandomNumber() {
		Random r = new Random();
		int i = r.nextInt(codes.length);
		return i;
	}
}
