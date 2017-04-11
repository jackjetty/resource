package com.detachment.util;

import java.io.InputStream;  
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Set;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
 
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.Header;  
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.HttpVersion;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.CookieStore;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.conn.ClientConnectionManager;  
import org.apache.http.conn.params.ConnManagerParams;  
import org.apache.http.conn.scheme.PlainSocketFactory;  
import org.apache.http.conn.scheme.Scheme;  
import org.apache.http.conn.scheme.SchemeRegistry;  
import org.apache.http.cookie.Cookie;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;  
import org.apache.http.impl.cookie.BasicClientCookie;  
import org.apache.http.message.BasicHeader;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.params.BasicHttpParams;  
import org.apache.http.params.HttpParams;  
import org.apache.http.params.HttpProtocolParams;  
import org.apache.http.protocol.HTTP;  

public class LegalProcessUtil {
	
	//变量
	private String hpzl;
	private String hphm;
	private String cjhm;
	private String sfzmhm;
	 
	 
	public String getHpzl() {
		return hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public String getHphm() {
		return hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getCjhm() {
		return cjhm;
	}

	public void setCjhm(String cjhm) {
		this.cjhm = cjhm;
	}

	public String getSfzmhm() {
		return sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	private DefaultHttpClient httpclient;  
	
	
	
	
    public DefaultHttpClient getHttpclient() {
		return httpclient;
	}

	public void setHttpclient(DefaultHttpClient httpclient) {
		this.httpclient = httpclient;
	}

	private Header[] htmlheaders = {new BasicHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; " +  
            "Windows NT 5.1; SV1; .NET CLR 2.0.50727; CIBA)"),   
            new BasicHeader("Accept-Language", "zh-cn"),   
            new BasicHeader("Accept", " image/gif, image/x-xbitmap, image/jpeg, " +  
                    "image/pjpeg, application/x-silverlight, application/vnd.ms-excel, " +  
                    "application/vnd.ms-powerpoint, application/msword, application/x-shockwave-flash, */*"),   
            new BasicHeader("Content-Type", "text/plain; charset=UTF-8"),   
            new BasicHeader("Accept-Encoding", "gzip, deflate")};  
    private Header[] xmlheaders = {new BasicHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; " +  
            "Windows NT 5.1; SV1; .NET CLR 2.0.50727; CIBA)"),   
            new BasicHeader("Accept-Language", "zh-cn"),  
            new BasicHeader("Content-Type", "text/plain"),
            new BasicHeader("X_REQUESTED_WITH", "XMLHttpRequest"),
            new BasicHeader("Accept-Encoding", "gzip, deflate")}; 
      
    private Cookie cookie;  
    private String scriptSessionId; 
 
	private String httpSessionId;
	
    public String getHttpSessionId() {
		return httpSessionId;
	}

	public void setHttpSessionId(String httpSessionId) {
		this.httpSessionId = httpSessionId;
	}

	/** 
     * 请求的其他参数 
     */ 
    private Map<String, String> map = new HashMap<String, String>(); 
    
     
    
    
    private static String HANDLEVIO_URL = "http://wscgs.sxga.gov.cn/zzwfcl/wfcl/handleVio.do"; 
	private static String ENGINE_URL="http://wscgs.sxga.gov.cn/zzwfcl/dwr/engine.js";
	                                  
	private static String SAVESYNCVIO_URL="http://wscgs.sxga.gov.cn/zzwfcl/dwr/call/plaincall/SysUserServiceJS.saveSyncVio.dwr";
	private static String CHECKSYNCHRONIZESTATUS_URL="http://wscgs.sxga.gov.cn/zzwfcl/dwr/call/plaincall/SysUserServiceJS.checkSynchronizeStatus.dwr";
	private static String GETVEHICLE_URL="http://wscgs.sxga.gov.cn/zzwfcl/dwr/call/plaincall/SysUserServiceJS.getVehicle.dwr";
	private static String HANDLEVIOONE_URL="http://wscgs.sxga.gov.cn/zzwfcl/wfcl/handleVioOne.do"; 
	private static String HANDLEVIOTWO_URL="http://wscgs.sxga.gov.cn/zzwfcl/wfcl/handleVioTwo.do";
	
	private static String HANDLEVIOVALIDATEDRIVER_URL="http://wscgs.sxga.gov.cn/zzwfcl/wfcl/handleVioValidateDriver.do";
	private static String SYSUSERSERVICEJS_URL="http://wscgs.sxga.gov.cn/zzwfcl/dwr/interface/SysUserServiceJS.js"; 
	
	private static String GETDRIVER_URL="http://wscgs.sxga.gov.cn/zzwfcl/dwr/call/plaincall/SysUserServiceJS.getDriver.dwr";
	
	
	private static String VALIDATEMOBILCODE_URL="http://wscgs.sxga.gov.cn/zzwfcl/dwr/call/plaincall/SysUserServiceJS.validateMobileCode.dwr";
	
	private static String VALIDATEDRIVERSCORE_URL="http://wscgs.sxga.gov.cn/zzwfcl/dwr/call/plaincall/SysUserServiceJS.validateDriverScore.dwr";
	
	private static String HANDLEVIOLAST_URL="http://wscgs.sxga.gov.cn/zzwfcl/wfcl/handleVio_last.do";
	
	private static String HANDLEVIOJDS_URL="http://wscgs.sxga.gov.cn/zzwfcl/wfcl/handleVio_jds.do";
	private static String PAYSERVLET_URL="http://wscgs.sxga.gov.cn/zzwfcl/netpay/payServlet"; 
	
    public LegalProcessUtil() {  
        
        //httpclient = new DefaultHttpClient();  
        
        
    }  
    
    public LegalProcessUtil(String hpzl,String hphm,String cjhm){
    	HttpParams params = new BasicHttpParams();  
        ConnManagerParams.setMaxTotalConnections(params, 100);  
        ConnManagerParams.setTimeout(params, 1000);  
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);    
        SchemeRegistry schemeRegistry = new SchemeRegistry();  
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));  
        ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);    
        httpclient = new DefaultHttpClient(cm, params);  
        
        
        this.hpzl=hpzl;
    	this.hphm=hphm;
    	this.cjhm=cjhm;
        
    }
    
     
    
    public void handleVio() throws  Exception   {  
        HttpPost httpost = new HttpPost(HANDLEVIO_URL);  // 初始化Post   
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();  // 构建参数   
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
        httpost.setHeaders(htmlheaders); 
        HttpResponse response = httpclient.execute(httpost);  // 运行action  
        HttpEntity entity = response.getEntity();   // 获得实体 
        InputStream in = entity.getContent();  // 获得实体的内容  
        StringBuffer   out   =   new   StringBuffer();  
        byte[] b = new byte[4096];  
        for(int n; (n = in.read(b)) != -1;) {  
            out.append(new   String(b, 0, n));  
        }   
        if (entity != null) {  
            entity.consumeContent();  
        }    
        String html = out.toString();   
        CookieStore cookies = httpclient.getCookieStore();   
        httpclient.setCookieStore(cookies);
        List<Cookie> list = cookies.getCookies();  
        for (Cookie cookie : list) {  
            String cookieName = cookie.getName();  
            if ("JSESSIONID".equals(cookieName)) {  
                httpSessionId = cookie.getValue();   
            }  
        }  
        
         
    }  
    
    
    public String saveSyncVio() throws Exception{ 
        map.clear();  
        map.put("callCount", "1");
        map.put("page", "/zzwfcl/wfcl/handleVio.do"); 
        map.put("httpSessionId", httpSessionId);  
        map.put("scriptSessionId", scriptSessionId);  
        map.put("c0-scriptName", "SysUserServiceJS");  
        map.put("c0-methodName", "saveSyncVio");  
        map.put("c0-id", "0");  
        map.put("c0-param0", "string:"+this.hpzl);  
        map.put("c0-param1", "string:%E6%B5%99"+this.hphm);  
        map.put("c0-param2", "string:"+this.cjhm);  
        map.put("c0-param3", "boolean:false");   
        map.put("batchId","0"); 
        return   this.execute(SAVESYNCVIO_URL,xmlheaders); 
    }
    
    public void checkSynchronizeStatus() throws Exception{ 
    	int batchId=1;
    	String html;
        for( ;batchId<99;batchId++){  
            Thread.currentThread().sleep(1000); 
            map.clear(); 
            map.put("callCount", "1");
            map.put("page", "/zzwfcl/wfcl/handleVio.do"); 
            map.put("httpSessionId", httpSessionId);  
            map.put("scriptSessionId", scriptSessionId);  
            map.put("c0-scriptName", "SysUserServiceJS");  
            map.put("c0-methodName", "checkSynchronizeStatus");  
            map.put("c0-id", "0");  
            //02%3A%E6%B5%99DD5859%3A270832
            //02:浙DD5859:270832
            map.put("c0-param0", "string:"+this.hpzl+"%3A%E6%B5%99"+this.hphm+"%3A"+this.cjhm);  
            map.put("c0-param1", "boolean:false");    
            map.put("batchId",new Integer(batchId).toString());  
            html = this.execute(CHECKSYNCHRONIZESTATUS_URL,xmlheaders); 
             if(html.indexOf(",'0',\"1\")")>0){ 
             	break;
             }
        	
        }
        if(batchId==99){
        	throw new Exception("连接超时，请重试！！");
        	 
        } 
        
        for(int plus=1;plus<=3;plus++){ 
            map.clear(); 
            map.put("callCount", "1");
            map.put("page", "/zzwfcl/wfcl/handleVio.do"); 
            map.put("httpSessionId", httpSessionId);  
            map.put("scriptSessionId", scriptSessionId);  
            map.put("c0-scriptName", "SysUserServiceJS");  
            map.put("c0-methodName", "getVehicle");  
            map.put("c0-id", "0");  
            //02%3A%E6%B5%99DD5859%3A270832
            //02:浙DD5859:270832
            
            map.put("c0-param0", "string:"+this.hpzl);  
            map.put("c0-param1", "string:"+this.hphm);  
            map.put("c0-param2", "boolean:false");
            map.put("batchId",new Integer(batchId+plus).toString()); 
            html=this.execute(GETVEHICLE_URL,xmlheaders); 
            String regEx = "SFZMHM:\"(\\w+)\"";   
            Pattern p = Pattern.compile(regEx);  
            Matcher m = p.matcher(html);  
            while (m.find()) {  
                this.sfzmhm = m.group(1); 
                
            } 
            if(this.sfzmhm!=null){
            	break;
            }
        }
        if(this.sfzmhm==null){
        	throw new Exception("获取机动车违法信息失败,请稍后再试!");
        }
        
    }
    
    
    public String getScriptSessionId() {
		return scriptSessionId;
	}

	public void setScriptSessionId(String scriptSessionId) {
		this.scriptSessionId = scriptSessionId;
	}

	public void engine() throws Exception{ 
        map.clear();   
        String html =  this.execute(ENGINE_URL,htmlheaders);    
        String regEx = "dwr\\.engine\\._origScriptSessionId = \"(\\w+)\"";   
        Pattern p = Pattern.compile(regEx);  
        Matcher m = p.matcher(html);  
        while (m.find()) {  
            scriptSessionId = m.group(1);  
        }  
    }
    
    
    
    public  String   handleVioOne() throws Exception{ 
    	    map.clear(); 
    	    String url= HANDLEVIOONE_URL +"?hpzl="+this.hpzl+"&hphm=%D5%E3"+this.hphm+"&sfzmhm="+this.sfzmhm+"&currPage=1"; 
    	    return this.execute(url,htmlheaders);  
	}
    
    public  String   handleVioTwo(String xh) throws Exception{ 
	    map.clear(); 
	    String url=HANDLEVIOTWO_URL+"?hpzl="+this.hpzl+"&xh="+xh; 
	    return   this.execute(url,htmlheaders);     
    }
    
    
    
     
    public String handleVioValidateDriver(String xh,String captcha_rand_flag_refersh) throws Exception{
    	map.clear(); 
	    String url=HANDLEVIOVALIDATEDRIVER_URL+"?xh="+xh+"#&captcha_rand_flag_refersh="+captcha_rand_flag_refersh; 
	    //url=URLEncoder.encode(url, "utf-8");  
	    String html=this.execute(url,htmlheaders);
	    this.execute(ENGINE_URL,htmlheaders);   
	    this.execute(SYSUSERSERVICEJS_URL,xmlheaders);
	    
	    return html; 
    }
    
    public String getDriver(String xm,String sfzmhm)  throws Exception{
    	map.clear(); 
        map.put("callCount", "1");
        map.put("page", "/zzwfcl/wfcl/handleVioValidateDriver.do"); 
        map.put("httpSessionId", httpSessionId);  
        map.put("scriptSessionId", scriptSessionId);  
        map.put("c0-scriptName", "SysUserServiceJS");  
        map.put("c0-methodName", "getDriver");  
        map.put("c0-id", "0");   
        map.put("c0-param0", "string:"+URLEncoder.encode(xm, "utf-8"));  
        map.put("c0-param1", "string:"+ sfzmhm);  
        map.put("c0-param2", "boolean:false");
        map.put("batchId","0");  
        return this.execute(GETDRIVER_URL,xmlheaders);  
    			 
    }
    
    public String validateMobilCode(String mobilcode)  throws Exception {
    	map.clear();   
    			
        map.put("callCount", "1");
        map.put("page", "/zzwfcl/wfcl/handleVioValidateDriver.do"); 
        map.put("httpSessionId", httpSessionId);  
        map.put("scriptSessionId", scriptSessionId);  
        map.put("c0-scriptName", "SysUserServiceJS");  
        map.put("c0-methodName", "validateMobileCode");  
        map.put("c0-id", "0");   
        map.put("c0-param0", "string:"+mobilcode);    
        map.put("c0-param1", "boolean:false");
        map.put("batchId","1");  
        return this.execute(VALIDATEMOBILCODE_URL,xmlheaders);  
    	
    }
    
    
 
    	 
     
    	 
    	 
    		
    		
    public String validateDriverScore()  throws Exception {
    	map.clear();   	
        map.put("callCount", "1");
        map.put("page", "/zzwfcl/wfcl/handleVioValidateDriver.do"); 
        map.put("httpSessionId", httpSessionId);  
        map.put("scriptSessionId", scriptSessionId);  
        map.put("c0-scriptName", "SysUserServiceJS");  
        map.put("c0-methodName", "validateDriverScore");  
        map.put("c0-id", "0");       
        map.put("c0-param0", "boolean:false");
        map.put("batchId","2");  
        return this.execute( VALIDATEDRIVERSCORE_URL,xmlheaders);  
    	
    }
    
    
    
    
    private String execute(String url,Header[] headers ) throws Exception {  
        HttpPost httpost = new HttpPost(url);  // 初始化Post   
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();  // 构建参数  
        // 增加其他的参数  
        Set<String> set = map.keySet();  
        for (String string : set) {  
            nvps.add(new BasicNameValuePair(string, map.get(string)));  
        }  
          
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
        httpost.setHeaders(headers);   
        HttpResponse response = httpclient.execute(httpost);  // 运行action  
        HttpEntity entity = response.getEntity();   // 获得实体   
        InputStream in = entity.getContent();  // 获得实体的内容  
        StringBuffer   out   =   new   StringBuffer();  
        byte[] b = new byte[4096];  
        for(int n; (n = in.read(b)) != -1;) {  
            out.append(new  String(b, 0, n,"gbk"));  
               
            
        }  
 
        if (entity != null) {  
            entity.consumeContent();  
        }  
          
        String html = out.toString();  
        return html;  
    }  
    
    private String executeUTF(String url,Header[] headers ) throws Exception {  
        HttpPost httpost = new HttpPost(url);  // 初始化Post   
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();  // 构建参数  
        // 增加其他的参数  
        Set<String> set = map.keySet();  
        for (String string : set) {  
            nvps.add(new BasicNameValuePair(string, map.get(string)));  
        }  
          
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
        httpost.setHeaders(headers);   
        HttpResponse response = httpclient.execute(httpost);  // 运行action  
        HttpEntity entity = response.getEntity();   // 获得实体   
        InputStream in = entity.getContent();  // 获得实体的内容  
        StringBuffer   out   =   new   StringBuffer();  
        byte[] b = new byte[4096];  
        for(int n; (n = in.read(b)) != -1;) {  
            out.append(new  String(b, 0, n ));  
               
            
        }  
 
        if (entity != null) {  
            entity.consumeContent();  
        }  
          
        String html = out.toString();  
        return html;  
    }  
    public  String   handleVioLast( ) throws Exception{ 
	    map.clear(); 
	    String url=HANDLEVIOLAST_URL ; 
	    return   this.execute(url,htmlheaders);     
    }
    public  String   handleVioJds(String jdsh,String status ) throws Exception{ 
	    map.clear(); 
	    String url=HANDLEVIOJDS_URL+"?JDSH="+jdsh+"&status"+status ; 
	    return   this.execute(url,htmlheaders);     
    }
    
    public String payServlet(String jdsbh,String orderId,String orderTime)throws Exception{ 
    	map.clear(); 
	    String url=PAYSERVLET_URL+"?jdsbh="+jdsbh+"&orderId"+orderId+"&orderTime"+orderTime ; 
	    return   this.executeUTF(url,htmlheaders);     
    }
    
     
    	 
     
    
    
    
    
	public static void main(String[] args)throws Exception {  
		LegalProcessUtil legalProcessUtil=new LegalProcessUtil("02","dlb365","040838");
		
		
		legalProcessUtil.handleVio();

        legalProcessUtil.engine();
		legalProcessUtil.saveSyncVio();
		legalProcessUtil.checkSynchronizeStatus();
		
		
		//330625194911273558
		System.out.println(legalProcessUtil.sfzmhm); 
		/*legalProcessUtil.handleVioOne();
		legalProcessUtil.handleVioTwo("339011197002228195");
		*/
		                   

		//String html=legalProcessUtil.payServlet("3306001880006455","2014111413214426504490101","20141114132144");
		// System.out.println(html);
		
	} 
	
	 
}
