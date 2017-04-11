package com.detachment.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Controller;

@Controller("realTrafficAction")
public class RealTrafficAction {
	public final static String USER_AGENT_H = "User-Agent";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
	public final static String UTF_8 = "UTF-8";
	private MultiThreadedHttpConnectionManager n =  new MultiThreadedHttpConnectionManager(); 
	private HttpClient client = new HttpClient(n);
	private Cookie[] cookies;
	private String cookiestr;
	
	
	
	private HashMap<String, Object> result;
	private String unidNum;
	private String addressName;
	
	
	public String doRealTraffic(){
		return "success";
	}
	
	public String getRealTraffic() throws Exception{
		result=new HashMap<String,Object>();
		ArrayList<String> allUrl=new ArrayList<String>();
		ArrayList<String> allName=new ArrayList<String>();
		realTrafficLogin();
		String res=realTraffics();
		//System.out.println(res);
		int index=0;
		int index1=0;
		int unid=1;
		while(res.indexOf("onclick=\"aimg", index1)!=-1){
			int aa=res.indexOf("VideoImage.jsp", index);
			int aa1=res.indexOf("onclick=\"aimg", index1);
			String Allurl=res.substring(aa, aa1).replace("\"", "");
			String address=Allurl.split(",")[1];
			allName.add(address);
			String addurl=URLEncoder.encode(address, "GBK");
			//String addUrl="http://www.sxga.gov.cn:8833/lwpsp/outmain/VideoImage.jsp?unid="+unid+","+addurl;
			String addUrl="doRealTrafficInfo.action?unidNum="+unid;
			allUrl.add(addUrl);
			index1=aa1+1;
			index=aa+1;
			unid++;
		}
		result.put("allUrl", allUrl);
		result.put("allName", allName);
		return "success";
	}
	
	
	public String doRealTrafficInfo() throws Exception{
		//addressName=URLDecoder.decode(addressName, "UTF-8");
		//System.out.println(addressName);
		return "success";
	}
	
	
	
	
	private String realTrafficLogin() {
		String result=null;
		GetMethod get=new GetMethod("http://wap.sxga.gov.cn/was/wap/serviceGuide.jsp");
		try {
			get.setRequestHeader(USER_AGENT_H, USER_AGENT);
			get.setRequestHeader("Connection", "close");
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				result = get.getResponseBodyAsString();
				//System.out.println(result);
					this.cookies = client.getState().getCookies();
					StringBuffer cookie = new StringBuffer();
					for (Cookie c : client.getState().getCookies()) {
						cookie.append(c.getName()).append("=")
						.append(c.getValue()).append(";");
					this.cookiestr = cookie.toString();
					//System.out.println(cookie.toString());
				}
			}
		} catch (Exception e) {
		}finally{
			if(get!=null){
				get.releaseConnection();
				//((MultiThreadedHttpConnectionManager)client.getHttpConnectionManager()).shutdown();
				get=null;
			}
		}
		return result;
	}
	
	
	
	public String realTraffics() {
		String result="";
		String paramStr = "http://www.sxga.gov.cn:8833/lwpsp/outmain/appVideo.jsp" ;
		GetMethod get = new GetMethod(paramStr);
		try {
			 paramStr+=";"+ this.cookiestr.replace(";arrayid=r-gatbsdt1;", "").replace(";arrayid=r-gatbsdt2;", "");
			 //System.out.println(paramStr);
			get.setRequestHeader(USER_AGENT_H, USER_AGENT);
			get.setRequestHeader("Connection", "close");
			client.getParams().setParameter(client.getParams().ALLOW_CIRCULAR_REDIRECTS, true); 
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) { 
				String responseBodyAsString=get.getResponseBodyAsString(); 
				result=replaceBlank(responseBodyAsString);
				//System.out.println(responseBodyAsString);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(get!=null){
				get.releaseConnection();
				//((MultiThreadedHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
				get=null;
			}
		}
		return result;
	}
	 public static String replaceBlank(String str) {
	        String dest = "";
	        if (str!=null) {
	            Pattern p = Pattern.compile("\t|\r|\n");
	            Matcher m = p.matcher(str);
	            dest = m.replaceAll("");
	        }
	        return dest;
	 }
	
	
	
	

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getUnidNum() {
		return unidNum;
	}

	public void setUnidNum(String unidNum) {
		this.unidNum = unidNum;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	
	
	
	
}
