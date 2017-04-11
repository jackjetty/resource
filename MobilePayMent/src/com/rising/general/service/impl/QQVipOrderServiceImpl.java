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

import org.springframework.stereotype.Service;

import com.rising.general.service.PayOrder;
import com.rising.mobilepayment.commom.CommonUtil;
import com.rising.mobilepayment.commom.HttpUtil;

@Service("qQVipOrderService")
public class QQVipOrderServiceImpl implements PayOrder {
	
	private static String caller = "phoneNumber";

	private static String objid = "productId";

	private static String paymoney = "cost";

	private static String username = "targetNumber";

	@Override
	public HashMap<String, String> getParameterMap(HashMap<String, String> map) {
		HashMap<String,String> param = new HashMap<String, String>();
		String productId = map.get(objid);
		String payid = productId.substring(6, 7);
		String objid = productId.substring(0, 5);
		param.put("objid",objid);
		param.put("username", map.get(username));
		param.put("caller", map.get(caller));
		param.put("paymoney", map.get(paymoney));
		param.put("payid", payid);
		return param;
	}

	@Override
	public HashMap<String, Object> payOrder(HashMap<String, String> paraMap, String url 
			  ) throws Exception {
		String result = "";
		 
		String parameter = CommonUtil.strMapToString(  paraMap );
		result=new HttpUtil().perGet(parameter, "utf-8");
		HashMap<String, Object>  resultMap=CommonUtil.stringToMap(  result);
		
		
		  
		if ("0".equals(resultMap.get("result"))) {
			resultMap.put("resultInfo", "成功");
			 
		} else {
			resultMap.put("resultInfo", "失败");
			 
		}
		return resultMap;
	}
	
	 
}
