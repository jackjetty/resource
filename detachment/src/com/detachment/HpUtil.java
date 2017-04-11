package com.detachment;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.alibaba.fastjson.JSONObject;
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.HttpKit;
import com.detachment.util.HttpWeiUtil;
import com.detachment.wei.thread.FileThread;

public class HpUtil {
	private static final String TICKET_URL="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
	
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException{
		
		/* String url="%D0%A1%D0%CD%C6%FB%B3%B5";
		
		String picLoalStore= "D:\\冉思\\绍兴交警\\二维码"+File.separator; 
		String picUrl="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
		String ticket="";
		for(int tIndex=31;tIndex<=33;tIndex++){
			try{
				ticket=HttpWeiUtil.getInstance("wx687156ff2c631bcc", "4832cb4ecf2973716eb08d8a97fdea67").getLimitTicket(tIndex);  
				new FileThread(picUrl.concat(URLEncoder.encode(ticket, "utf-8")),picLoalStore+tIndex+".jpg").start(); 
			}catch(Exception ex){
				ex.printStackTrace();
			}  
		}
		
		try{
			 //System.out.println(URLDecoder.decode(url, "utf-8"));
			 //System.out.println(URLEncoder.encode(url, "utf-8"));
		}catch(Exception ex){
			ex.printStackTrace();
		}  */
		
		
		/*Map<String, String> params = new HashMap<String, String>();
     
    		params.put("access_token", "2pj795spwar3zYvaPMzo4R7J2c_mW-uUKUaNJn4RYba8QCvvsdjdbZ8jOLtgWBxqXLovdJLv-G8fqp3LYS16XwhOGG114cpIVipnsyPDBPE2c5tZpS_ADkuFSNBaR3riTWVeAGAZCB");
    		params.put("openid", "otmRbuHvKxjNpJgKRYVQEbNPx7mI");
    		String  jsonStr = new HttpKit().get("https://api.weixin.qq.com/cgi-bin/user/info", params); 
    		System.out.println(jsonStr);*/
    		
    	/*	Map<String, String> params = new HashMap<String, String>();
		String  jsonStr="12";
		for(int i=1;i<=2001;i++){
			   jsonStr = new HttpKit().perGet("http://114.215.238.212/detachment/wap/refresh.action" ); 
			   
			   
				   
				//   jsonStr = new HttpKit().perGet("http://218.75.83.141/xstraffic/wap/refresh.action" ); 
		}
  		
		System.out.println(jsonStr);*/
		
		
	/*	Map<String,Object> json = new HashMap<String,Object>();
   	 Map<String,Object> scene= new HashMap<String,Object>();
   	 scene.put("scene_id", 800);
   	 Map<String,Object> action_info= new HashMap<String,Object>();
   	 action_info.put("scene", scene);
   	 json.put("action_name", "QR_LIMIT_SCENE");
   	 json.put("action_info", action_info);
   	 String post = JSONObject.toJSONString(json);
    	 String jsonStr = new HttpKit().post(TICKET_URL.concat("y8P7ytRJawlKOHp9GSQJjoqHNi6K5vgZnnk_y2ovaGHQ0lAr2J1_tKVScG_AmC8lmAF2xu68SFcYUbZ1h1pjDeUybEloo7zR8UnQzQOKckj6RJCSG6xaJ-eD3vIgsR0WVQLjADAQLG"), post);
    	 System.out.println(jsonStr);*/
		
		
		
		String url="https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=";
		String accesstoken="1n93-PWzuPeCkPAOTrjL36wJYomUyItMUA6_dNHGb_rB3NVTlv1eaKpOoShaNpgTsf-AOOchOxTPSt6Rtvydmm6XgyLp8FcbdPqKEinVAeZUGPDi9OPKI47z7EuDuW8lUJJaABADSS";
		 String jsonStr =  new HttpKit().get(url+accesstoken);
		 System.out.println(jsonStr);
		
		//{"template_list":[{"template_id":"ZhojTUBerJBxeJStSvjG68nmFnvNXdfpLP0bDuy163w","title":"新答案提醒","primary_industry":"IT科技","deputy_industry":"互联网|电子商务","content":"{{first.DATA}}\n问题：{{keyword1.DATA}}\n回答人：{{keyword2.DATA}}\n时间：{{keyword3.DATA}}\n{{remark.DATA}}","example":"你好，你悬赏的问题有人回答了。\r\n问题：Jboss重启报错的原因是什么？\r\n回答人：IT小虫\r\n时间：2014年7月21日 18:36\r\n如有问题，请联系客服"}]}

		
    	 
		
    		//{"subscribe":0,"openid":"otmRbuPzvcBRxYcL-_JRgH5tjTHg","tagid_list":[]}
    		
		/*
		String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
		String ACCESS_TOKEN="1wbSvYLJx3jAyK01XXmO74uh0X8OTOVrxytFtVH3bZzJVPQ2vKx9RRzP2WfWKucLww9xblXY8uNsv8DiMXvMi1H9YQtG1ow08b0uBnbZ4ydFxX41lT2nBvkXWgNjCS6KULGbADAOFA";
		    Map<String,Object> json = new HashMap<String,Object>();
	        Map<String,Object> textObj = new HashMap<String,Object>();
	        
	       
	        	
	         
	       json.put("type", "news");
	        json.put("offset", 0);
	        json.put("count", 1);
	        String post = JSONObject.toJSONString(json);
	        post="{\"action_name\": \"QR_LIMIT_STR_SCENE\",\"action_info\": {\"scene\": {\"scene_str\": \"123\"}}}";
	    	String reslut = new HttpKit().post(url.concat(ACCESS_TOKEN), post);
	    	System.out.println(reslut);*/
    		
	    	 
	    	//https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
		
		
		 
		
		  
		 
			
		 
	}
  
 
}