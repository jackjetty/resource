/**
 * BaseAction.java
 * com.rising.mobilepayment.action
 * 工程：MobilePayMent
 * 功能： 用于处理一些接受所有请求之后都需要做的事情，如前台固定格式的xml包发送过来，将数据解析出来
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-21   下午4:25:21
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.rising.mobilepayment.commom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseAction {
	// 用于将由service层返回的xmlString返回给AppServer
	public void writeJSONString(String resultJson, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			resultJson = URLEncoder.encode(resultJson,"UTF-8");
			out.write(resultJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeJSONString2(String resultJson, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(resultJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<String, String> getParameter(
			HttpServletRequest request) {
		InputStream is;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			is = request.getInputStream();
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			is.close();
			String result = new String(bos.toByteArray());
			String[] param = result.split("&");
			String key;
			String value;
			for (int i = 0; i < param.length; i++) {
				key = param[i].split("=")[0];
				try{
					value = param[i].split("=")[1];
				}catch(ArrayIndexOutOfBoundsException e){
					value = "";
				}
				map.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public String mapToString(HashMap<String, String> map) {
		String parameter = "";
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			parameter = parameter + key + "=" + value + "&";
		}
		return parameter;
	}

}
