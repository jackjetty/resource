package com.rising.general.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.rising.general.service.PayOrder;
import com.rising.mobilepayment.commom.CommonUtil;
import com.rising.mobilepayment.commom.HttpUtil;

@Service("qBPayOrderService")
public class QBPayOrderServiceImpl implements PayOrder {

	Log log = LogFactory.getLog(QBPayOrderServiceImpl.class);

	private static String caller = "phoneNumber";

	private static String objid = "productId";

	private static String payid = "orderId";

	private static String paymoney = "cost";

	private static String username = "targetNumber";

	@Override
	public HashMap<String, String> getParameterMap(HashMap<String, String> map) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("caller", map.get(caller));
		resultMap.put("objid", map.get(objid));
		resultMap.put("payid", map.get(payid));
		resultMap.put("spid", "003");
		resultMap.put("paymoney", map.get(paymoney));
		resultMap.put("username", map.get(username)); 
		return resultMap;
	}

	@Override
	public HashMap<String, Object> payOrder(HashMap<String, String> paraMap, String url ) throws Exception { 
		
		String result = ""; 
		String parameter = CommonUtil.strMapToString(  paraMap );
		 
		result=new HttpUtil().perGet(parameter, "utf-8");
		System.out.println("result:"+result);
		
		HashMap<String, Object>  resultMap=CommonUtil.stringToMap(  result);
		 
		 
		
		if ("0".equals(resultMap.get("result"))) {
			resultMap.put("resultInfo", "成功");
			 
		} else {
			resultMap.put("resultInfo", "失败");
			 
		} 
		return resultMap;
	}

	

}
