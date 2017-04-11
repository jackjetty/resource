package com.detachment.wap.service.impl;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod; 
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;  

import com.alibaba.fastjson.JSON; 
import com.detachment.dao.FormalAccidentDao;
import com.detachment.dao.PersonalPromotionDao;
import com.detachment.dao.UserDao;
import com.detachment.dao.WeiUserDao;
import com.detachment.factory.HttpFactory;
import com.detachment.pojo.TbPersonalPromotion;
import com.detachment.pojo.TbWeiUser;
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.HttpWeiUtil;
import com.detachment.util.LegalProcessUtil;
import com.detachment.util.WeiUtil; 
import com.detachment.wap.service.LegalProcessWapService; 
import com.detachment.wap.service.LegalQueryWapService;
import com.detachment.wei.thread.FileThread; 
import com.opensymphony.xwork2.ActionContext; 

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;  

import com.detachment.wap.json.CanHandleRecordWapJson;
import com.detachment.wap.json.UnpaidRecordWapJson; 
@Service("legalQueryWapService")  
public class LegalQueryWapServiceImpl implements LegalQueryWapService{
	public final static String USER_AGENT_H = "User-Agent";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
	public final static String UTF_8 = "UTF-8"; 
	private HttpFactory http = HttpFactory.getInstance();
	
	
	private HttpClient getHttpClient(Map<String, Object> session ){
		HttpClient   client=null;
		if(session.get("legalQueryHttpClient")!=null){
			client=(HttpClient)session.get("legalQueryHttpClient");
		}
		else{
			client =http.getClient(); 
			session.put("legalQueryHttpClient", client);
		} 
		return client;
	}
	
	public byte[] verifyCode(){
		Random random=new Random();
		Map<String, Object> session = ActionContext.getContext().getSession();
		String JSESSIONID="";
		try {
			String url = "http://www.zjsgat.gov.cn:8080/was/Kaptcha.jpg?" +random.nextInt(100);  
			// HttpClient client=getHttpClient(session ); 
			HttpClient client= new HttpClient(); 
			GetMethod getMethod = new GetMethod(url);
			client.executeMethod(getMethod);
			Cookie[] cookies = client.getState().getCookies();
			StringBuffer cookie = new StringBuffer();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("JSESSIONID")) {
					JSESSIONID = cookies[i].getValue(); 
					System.out.println(JSESSIONID);
				} 
					cookie.append(cookies[i].getName()).append("=")
					.append(cookies[i].getValue()).append(";"); 
			} 
			session.put("legalQueryHttpClient", client);
			session.put("legalQueryJSESSIONID", JSESSIONID);
			session.put("legalQueryCookiestr", cookie.toString());
			return getMethod.getResponseBody();
		} catch (Exception e) {
			return null;
		}
	}
	 public HashMap<String, Object> checkVerifyCode(String randValue){
		 HashMap<String, Object> result=new HashMap<String, Object>();
		 Map<String, Object> session = ActionContext.getContext().getSession(); 
		 HttpClient client=getHttpClient(session );  
		 //client.getParams().setParameter("http.socket.timeout",  new Integer(60000 * 10));
		 String JSESSIONID=(String)session.get("legalQueryJSESSIONID");
		 String url = "http://www.zjsgat.gov.cn:8080/was/portals/checkManyYzm.jsp";
		 url+="?randValue=" +randValue; 
		 String resJson=""; 
		 result.put("respCode", 1);
		 result.put("respInfo", "请输入正确的验证码！！");
		 //http://www.zjsgat.gov.cn:8080/was/portals/checkManyYzm.jsp;JSESSIONID=4A9EDC1AB2FE62E4414B149B4C505696?randValue=33D4
		 try{
			 GetMethod get = new GetMethod(url); 
			 get.setRequestHeader("Referer", "http://www.zjsgat.gov.cn:8080/");
			 get.setRequestHeader(USER_AGENT_H, USER_AGENT);
			 client.getParams().setParameter(client.getParams().ALLOW_CIRCULAR_REDIRECTS, true); 
			 int status = client.executeMethod(get); 
		     if (status == HttpStatus.SC_OK) {
		    	 resJson=get.getResponseBodyAsString();
		    	 System.out.println(resJson);
		    	 if(resJson.contains("Y")){
		    		 result.put("respCode", 0);
		    		 result.put("respInfo", "好的了");
		    	 }  
			 }  
		 }catch(Exception ex){
			 ex.printStackTrace();
		 } 
		 return result;
		  
	  }
	 
	 
	 
	 public HashMap<String, Object> driverIllegality(String carid,String carno,String cartype,String carTypeValue){
		    HashMap<String,Object> resultMap=new HashMap<String,Object>();
			try {
				carid=URLEncoder.encode(carid, "GBK");
				carTypeValue=URLEncoder.encode(carTypeValue, "GBK");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			Map<String, Object> session = ActionContext.getContext().getSession(); 
			HttpClient client=getHttpClient(session ); 
			String JSESSIONID=session.get("legalQueryJSESSIONID").toString();
			//;"+"JSESSIONID="+JSESSIONID
			String url="http://www.zjsgat.gov.cn:8080/was/common.do"
					+ "?tblname=carlllegalforphonequery&carid="+carid+"&carno="+carno+"&cartype="+cartype+"&carTypeValue="+carTypeValue; 
			//http://www.zjsgat.gov.cn:8080/was/common.do?tblname=carlllegalforphonequery&carid=%25E6%25B5%2599dju295&carno=030451&cartype=02&carTypeValue=%D0%A1%D0%CD%C6%FB%B3%B5
			String result1=null;
			String result=null;  
			PostMethod post = new PostMethod(url);
			try { 
				post.setRequestHeader("Referer", "http://www.zjsgat.gov.cn:8080/");
				post.setRequestHeader(USER_AGENT_H, USER_AGENT);
				int status = client.executeMethod(post);
				if (status == HttpStatus.SC_OK) {
					result = post.getResponseBodyAsString();  
					System.out.println(result); 
				}
				result=replaceBlank(result);
				int yesNo=result.indexOf("无非现场违法记录");
				if(yesNo!=-1){
					result1="无非现场违法记录";
				}else{
					int find1=result.indexOf("<table align=\"center\" >");
					int find2=result.indexOf("首页 上一页");
					if(find1!=-1 && find2!=-1){
						result1=result.substring(find1, find2)+"</td></tr></table><hr style='border-bottom:1px dashed #000000;' />";
					}
					int findpage1=result.indexOf("共<span class=\"red\">");
					int findpage2=result.indexOf("</span>条记录");
					String pages=null;
					if(findpage1!=-1 && findpage2!=-1){
						pages=result.substring(findpage1+1, findpage2); 
					}
					int findpage3=pages.indexOf(">");
					if(findpage3!=-1){
						pages=pages.substring(findpage3+1, pages.length());
					}
					int pagesInt=0;
					if(pages!=null){
						pagesInt=Integer.parseInt(pages);
					}
					for(int i=1;pagesInt>1&&i<pagesInt;i++){
						String res=getMaterial(client,i);
						res=replaceBlank(res);
						int a1=result.indexOf("<table align=\"center\" >");
						int a2=result.indexOf("<tr><td  colspan=\"2\"> <span");
						if(find1!=-1 && find2!=-1){
							result1+=res.substring(a1, a2)+"</table><hr style='border-bottom:1px dashed #000000;' />";
						}
					}
				}
			} catch (Exception e) {
				result1="系统发生未知错误，给您带来不便深感抱歉！";
				//e.printStackTrace();
			}
			finally{
				if(post!=null){
					post.releaseConnection(); 
					post=null;
				}
					 
			}  
			resultMap.put("code", "0");
		   	resultMap.put("result", result1);
			return resultMap;
	 }
	 public HashMap<String, Object> driverScore(String vsfzmhm,String vdabh){
		    HashMap<String,Object> resultMap=new HashMap<String,Object>();
		    resultMap=new HashMap<String,Object>();
			String url="http://www.zjsgat.gov.cn:8080/was/common.do?tblname=carcreditphonequery&vdabh="+vdabh+"&vsfzmhm="+vsfzmhm+"&vsfzmmc=";
			String result=null; 
			String result1=null;
			Map<String, Object> session = ActionContext.getContext().getSession(); 
			HttpClient client=getHttpClient(session );  
			PostMethod post = new PostMethod(url);
			try {
				post.setRequestHeader("Referer", "http://www.zjsgat.gov.cn:8080/");
				post.setRequestHeader(USER_AGENT_H, USER_AGENT);
				int status = client.executeMethod(post);
				if (status == HttpStatus.SC_OK) {
					result = post.getResponseBodyAsString();   
				}
			} catch (Exception e) {
			}finally{
				if(post!=null){
					post.releaseConnection(); 
					post=null;
				}
					 
			} 
			result=replaceBlank(result);
			int find1=result.indexOf("<table align=\"center\">");
			int find2=result.indexOf("</table>");
			if(find1!=-1 && find2!=-1){
				result1=result.substring(find1, find2);
			}
			resultMap.put("code", "0");
	   	 	resultMap.put("result", result1);
			return resultMap;
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
	 
	 
	 public String getMaterial(HttpClient client,int index) {
			String result=null;
			String paramStr = "http://www.zjsgat.gov.cn:8080/was/phone/carIllegalQueryResult.jsp" ; 
			paramStr+="?currentpage="+index;
			GetMethod get = new GetMethod(paramStr);
			try {
				get.setRequestHeader(USER_AGENT_H, USER_AGENT);
				client.getParams().setParameter(client.getParams().ALLOW_CIRCULAR_REDIRECTS, true); 
				int status = client.executeMethod(get);
				if (status == HttpStatus.SC_OK) { 
					result=get.getResponseBodyAsString(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(get!=null){
					get.releaseConnection();
					get=null;
				}
			}
			return result;
		}
	 
	 
	  
}