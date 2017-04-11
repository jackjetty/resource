package com.rising.general.commom;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.google.gson.Gson;
import com.rising.general.bean.RSAKey;

public class BaseAction {

	Log log = LogFactory.getLog(BaseAction.class);

	public HashMap<String, String> getParameter(HttpServletRequest request)
			throws DocumentException, IOException {
		String ip = request.getParameter("ipAddress");
		String requestXML = getRequestXMLStringFromRequest(request);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("requestIp", ip);
		Document d;
		try {
			d = DocumentHelper.parseText(requestXML);
			Element root = d.getRootElement();
			Element e = root.element("params");
			Iterator<?> it = e.elementIterator("param");
			while (it.hasNext()) {
				Element e1 = (Element) it.next();
				Iterator<?> it1 = e1.attributeIterator();
				while (it1.hasNext()) {
					Attribute a = (Attribute) it1.next();
					Attribute b = (Attribute) it1.next();
					map.put(a.getValue(), b.getValue());
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			log.error("getParameterFromRequest()->xml报文解析出错!" + e.toString());
		}
		return map;
	}

	public String getRequestXMLStringFromRequest(HttpServletRequest request)
			throws IOException {
		BufferedReader br = null;
		StringBuilder rs = new StringBuilder();
		String line;
		try {
			br = request.getReader();
			while ((line = br.readLine()) != null) {
				rs.append(line);
			}
			br.close();
		} catch (IOException e) {
			log.error("getRequestXMLStringFromRequest()->获取xml请求报文出错！"
					+ e.toString());
			throw e;
		}
		String xmlString = URLDecoder.decode(rs.toString(), "UTF-8");
		return xmlString;
	}

	public String encodeGsonString(String gsonString, String RSAPrivateKey) {
		String encodedString = "";
		try {
			byte[] middlebytes = RSAUtils.encryptByPrivateKey(
					gsonString.getBytes(), RSAPrivateKey);
			String middleString = Base64Utils.encode(middlebytes);
			encodedString = URLEncoder.encode(middleString, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodedString;
	}

	public void writeJSONString(HashMap<String, Object> resultMap,
			HttpServletResponse response, RSAKey key) {
		String resultJson = "";
		if (key == null) {
			resultJson = new Gson().toJson(resultMap);
		} else {
			try {
				String result = new Gson().toJson(resultMap);
				byte[] middlebytes = RSAUtils.encryptByPrivateKey(
						result.getBytes(), key.getPrivateKey());
				resultJson = Base64Utils.encode(middlebytes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			resultJson = URLEncoder.encode(resultJson, "UTF-8");
			out.write(resultJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static HashMap<String, String> getParameter(
			HttpServletRequest request,String transferType) {
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
}
