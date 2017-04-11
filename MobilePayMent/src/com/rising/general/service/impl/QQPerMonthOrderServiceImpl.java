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

@Service("qQPerMonthOrderService")
public class QQPerMonthOrderServiceImpl implements PayOrder {
	
	private static String caller = "phoneNumber";

	private static String objid = "productId";

	private static String paymoney = "cost";

	private static String username = "targetNumber";

	@Override
	public HashMap<String, String> getParameterMap(HashMap<String, String> map) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("caller", map.get(caller));
		resultMap.put("objid", map.get(objid).substring(0, 5));
		resultMap.put("paymoney", map.get(paymoney));
		resultMap.put("username", "z"+map.get(username));
		resultMap.put("payid", "");
		return resultMap;
	}

	@Override
	public HashMap<String, Object> payOrder(HashMap<String, String> paraMap, String url ) throws Exception {
		String result = "";
		 
		String parameter = CommonUtil.strMapToString(  paraMap );
		result=new HttpUtil().perGet(parameter, "utf-8");
		HashMap<String, Object>  resultMap=CommonUtil.stringToMap(  result);
		
		 
		if ("0".equals(resultMap.get("result"))) {
			resultMap.put("resultInfo", "Q币包月申请成功");
			 
		} else {
			resultMap.put("resultInfo", "Q币包月申请失败");
			 
		}
		return resultMap;
	}
	
	 

}
