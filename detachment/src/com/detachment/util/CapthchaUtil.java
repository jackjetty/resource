package com.detachment.util;

import java.util.Random;

public class CapthchaUtil {

private final static char [] capthcha = {'0','1','2','3','4','5','6','7','8','9'};
	
	public static String getCapthchaCode(){
		char code;
		String capthchaCode ="";
		for(int i=0;i<4;i++ ){
			code = getRandomChar();
			capthchaCode = capthchaCode + changeCase(code);
		}
		return capthchaCode;
	}
	private static char getRandomChar(){
		Random r = new Random();
		int x = r.nextInt(capthcha.length);
		return capthcha[x];
	}
	private static String changeCase(char c){
		String character = String.valueOf(c);
		Random r = new Random();
		int x = r.nextInt(100);
		int y = x%2;
		if(y==0){
			character.toUpperCase();
		}
		return character;
	}
	 

}
