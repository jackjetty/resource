package com.rising.mobilepayment.service.impl;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.SecurityCode;
import com.rising.mobilepayment.bean.SendMessageLog;
import com.rising.mobilepayment.commom.CommonUtil;
import com.rising.mobilepayment.commom.Constant;
import com.rising.mobilepayment.commom.MD5;
import com.rising.mobilepayment.mapper.SecurityCodeMapper;
import com.rising.mobilepayment.mapper.SendMessageLogMapper;
import com.rising.mobilepayment.service.SMSService; 

@Service("smsService")
public class SMSServiceImpl implements SMSService {
	@Autowired
	private SendMessageLogMapper sendMessageLogMapper;
	@Override
	public boolean noticeSMS(String msgType, String phoneNumber,
			String msgContent) {
		msgType=CommonUtil.trim(msgType);
		phoneNumber=CommonUtil.trim(phoneNumber);
		msgContent=CommonUtil.trim(msgContent);
		
		if(phoneNumber.equals(""))
			 return false;
		if(msgContent.equals(""))
			 return false;
		Calendar calendar = Calendar.getInstance();
		Date curDate= calendar.getTime(); 
		SendMessageLog sendMessageLog = new SendMessageLog();
    	sendMessageLog.setLogTime( curDate);
    	sendMessageLog.setLogType("ntc");
    	sendMessageLog.setMessageContent(msgContent );
    	sendMessageLog.setMessageType(msgType);
    	sendMessageLog.setSendPhoneNumber(phoneNumber);
    	
    	String result=sendHttpMessage(  phoneNumber,   msgContent );
    	
    	sendMessageLog.setDataString(result);
    	try{
    		sendMessageLogMapper.add(sendMessageLog); 
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
		
		if(result.equals("0"))
			return true;
		
		return false;
	}
	
	
	private String sendHttpMessage(String phoneNumber, String message){ 
		
		String returnString = "";
		try {
			String dataString = "objid=16670$username="
					+ URLEncoder.encode(message, "GB2312")
					+ "$paymoney=$payid=$caller="
					+ phoneNumber; 
			java.net.URL httpUrl;
            try{
				httpUrl = new URL(Constant.HBSERVERURL);
				URLConnection uc = (URLConnection) httpUrl.openConnection();
				uc.setDoOutput(true);
				uc.setDoInput(true);
				PrintWriter out = new PrintWriter(uc.getOutputStream());
				out.write(dataString);
				out.flush();
				out.close();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						uc.getInputStream(), "gb2312"));
				String line;
				while ((line = in.readLine()) != null) {
					returnString += line;
				}
				in.close();

			} catch (Exception e1) {
				e1.printStackTrace();
				return "99";
			}  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "98";
		} 
		HashMap<String, Object>  resultMap=CommonUtil.stringToLowerMap(returnString); 
		return CommonUtil.trim(resultMap.get("result"));
		
	}
	
}