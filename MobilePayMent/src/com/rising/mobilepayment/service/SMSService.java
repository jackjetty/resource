package com.rising.mobilepayment.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rising.mobilepayment.commom.CommonUtil;


public interface SMSService{
	 
	public boolean noticeSMS(String msgType,String phoneNumber, String msgContent);
}