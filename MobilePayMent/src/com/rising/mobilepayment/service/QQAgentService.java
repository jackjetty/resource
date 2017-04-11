package com.rising.mobilepayment.service;
 
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rising.mobilepayment.commom.CommonUtil;

public interface QQAgentService{ 
	 
	 public HashMap<String, Object> getQQServiceConfig(String gateCode,String phoneNumber, String busId,String targetNumber);
	 
	 
	 public HashMap<String, Object> qqPayNotice(HashMap<String, String> paraMap );
	 
	 
	 public HashMap<String, Object> qqRefundNotice(HashMap<String, String> paraMap );
	 
	 public HashMap<String, Object> qqUnsubmitRefund(HashMap<String, String> paraMap );
	 
	 
	 public HashMap<String, Object> getQQGameConfig(String gateCode,String phoneNumber, String gameCode,String targetNumber);
	 
	 
	 
}