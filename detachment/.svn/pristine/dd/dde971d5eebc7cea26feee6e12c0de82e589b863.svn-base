package com.detachment.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus; 
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;

import com.detachment.factory.HttpFactory; 
@Controller("weiFaSearchAction")
public class WeiFaSearchAction {
	 
	public final static String USER_AGENT_H = "User-Agent";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
	public final static String UTF_8 = "UTF-8"; 
	private HttpFactory http = HttpFactory.getInstance();
	private HttpClient client =http.getClient(); ;
	private Cookie[] cookies;
	private String cookiestr; 
	private HashMap<String, Object> resultMap; 
	private String index;
	private String cartype;
	private String carTypeValue;
	private String carid;
	private String carno;
	private String vsfzmhm;
	private String vdabh; 
	public String doWeiFaSearch(){
		return "success";
	}

	public String getCLresult() throws Exception{
		resultMap=new HashMap<String,Object>();
		carid=URLEncoder.encode(carid, "GBK");
		carTypeValue=URLEncoder.encode(carTypeValue, "GBK");
		String url="http://www.zjsgat.gov.cn:8080/was/common.do?tblname=carlllegalforphonequery&carid="+carid+"&carno="+carno+"&cartype="+cartype+"&carTypeValue="+carTypeValue;
		String result1=CLresult(url);
		resultMap.put("code", "0");
	   	resultMap.put("result", result1);
		return "success";
	}
	
	public String getWFresult(){
		String result1=null;
		resultMap=new HashMap<String,Object>();
		String url="http://www.zjsgat.gov.cn:8080/was/common.do?tblname=carcreditphonequery&vdabh="+vdabh+"&vsfzmhm="+vsfzmhm+"&vsfzmmc=";
		String result=WFresult(url);
		result=replaceBlank(result);
		int find1=result.indexOf("<table align=\"center\">");
		int find2=result.indexOf("</table>");
		if(find1!=-1 && find2!=-1){
			result1=result.substring(find1, find2);
		}
		resultMap.put("code", "0");
   	 	resultMap.put("result", result1);
		return "success";
	}

	
	private String  CLresult(String url) {
		String result1=null;
		String result=null;
		PostMethod post = new PostMethod(url);
		try { 
			post.setRequestHeader("Referer", "http://www.zjsgat.gov.cn:8080/");
			post.setRequestHeader(USER_AGENT_H, USER_AGENT);
			int status = client.executeMethod(post);
			if (status == HttpStatus.SC_OK) {
				result = post.getResponseBodyAsString();  
				if ( result.indexOf("ok")>=0) {
					this.cookies = client.getState().getCookies();
					StringBuffer cookie = new StringBuffer();
					for (Cookie c : client.getState().getCookies()) {
						cookie.append(c.getName()).append("=")
						.append(c.getValue()).append(";");
					}
					this.cookiestr = cookie.toString();
				}
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
					String res=getMaterial(i);
					res=replaceBlank(res);
					int a1=result.indexOf("<table align=\"center\" >");
					int a2=result.indexOf("<tr><td  colspan=\"2\"> <span");
					if(find1!=-1 && find2!=-1){
						result1+=res.substring(a1, a2)+"</table><hr style='border-bottom:1px dashed #000000;' />";
					}
				}
			}
		} catch (Exception e) {
		}
		finally{
			if(post!=null){
				post.releaseConnection();
				//((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown();  
				post=null;
			}
				 
		}
		return result1;
	}
	
	
	
	private String  WFresult(String url) {
		String result=null;
		PostMethod post = new PostMethod(url);
		try {
			post.setRequestHeader("Referer", "http://www.zjsgat.gov.cn:8080/");
			post.setRequestHeader(USER_AGENT_H, USER_AGENT);
			int status = client.executeMethod(post);
			if (status == HttpStatus.SC_OK) {
				result = post.getResponseBodyAsString();  
				if ( result.indexOf("ok")>=0) {
					this.cookies = client.getState().getCookies();
					StringBuffer cookie = new StringBuffer();
					for (Cookie c : client.getState().getCookies()) {
						cookie.append(c.getName()).append("=")
						.append(c.getValue()).append(";");
					}
					this.cookiestr = cookie.toString();
				}
			}
		} catch (Exception e) {
		}finally{
			if(post!=null){
				post.releaseConnection();
				//((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown();  
				post=null;
			}
				 
		}
		return result;
	}
	
	
	
	
	
	
	public String getMaterial(int index) {
		String result=null;
		 String paramStr = "http://www.zjsgat.gov.cn:8080/was/phone/carIllegalQueryResult.jsp" ;
		 paramStr+=";"+ this.cookiestr.replace(";arrayid=r-gatbsdt1;", "").replace(";arrayid=r-gatbsdt2;", "");
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
				//((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown();
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
	

	public String getIndex() {
		return index;
	}


	public void setIndex(String index) {
		this.index = index;
	}


	public String getCartype() {
		return cartype;
	}


	public void setCartype(String cartype) {
		this.cartype = cartype;
	}


	public String getCarTypeValue() {
		return carTypeValue;
	}


	public void setCarTypeValue(String carTypeValue) {
		this.carTypeValue = carTypeValue;
	}


	public String getCarid() {
		return carid;
	}


	public void setCarid(String carid) {
		this.carid = carid;
	}


	public String getCarno() {
		return carno;
	}


	public void setCarno(String carno) {
		this.carno = carno;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getVsfzmhm() {
		return vsfzmhm;
	}

	public void setVsfzmhm(String vsfzmhm) {
		this.vsfzmhm = vsfzmhm;
	}

	public String getVdabh() {
		return vdabh;
	}

	public void setVdabh(String vdabh) {
		this.vdabh = vdabh;
	}
	
	
}
