package com.detachment.wap.service.impl;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Cookie;
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
import com.detachment.pojo.TbPersonalPromotion;
import com.detachment.pojo.TbWeiUser;
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.HttpWeiUtil;
import com.detachment.util.LegalProcessUtil;
import com.detachment.util.WeiUtil; 
import com.detachment.wap.service.LegalProcessWapService; 
import com.detachment.wei.thread.FileThread; 
import com.opensymphony.xwork2.ActionContext;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements; 
import com.detachment.wap.json.CanHandleRecordWapJson;
import com.detachment.wap.json.UnpaidRecordWapJson;

@Service("legalProcessWapService")  
public class LegalProcessWapServiceImpl implements LegalProcessWapService{
	//待处理记录
	public HashMap<String,Object> vehicleLegal(String hpzl,String hphm,String cjhm){
		HashMap<String,Object> result=new HashMap<String,Object>();
		LegalProcessUtil  legalProcessUtil=new LegalProcessUtil(hpzl,hphm,cjhm);
		Map<String, Object> session = ActionContext.getContext().getSession();
		
		session.put("legalhpzl", hpzl);
		session.put("legalhphm", hphm);
		session.put("legalcjhm", cjhm);
		try{
			legalProcessUtil.handleVio();
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage());
			return result;
		}
		
		session.put("legalhttpsessionid", legalProcessUtil.getHttpSessionId());
		
		try{
			legalProcessUtil.engine();
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage());
			return result;
		}
		session.put("legalscriptsessionid", legalProcessUtil.getScriptSessionId()); 
		
		
		 
		
		try{
			legalProcessUtil.saveSyncVio();
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage());
			return result;
		}
	    
		try{
			legalProcessUtil.checkSynchronizeStatus();
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage());
			return result;
		} 
		session.put("legalsfzmhm", legalProcessUtil.getSfzmhm());  
		session.put("legalhttpclient", legalProcessUtil.getHttpclient()); 
		result.put("respCode", 0);
		result.put("respInfo", "");
		return result;
		
		
		
	}
	public HashMap<String,Object> pendPageLegal(){
		HashMap<String,Object> result=new HashMap<String,Object>();
        Map<String, Object> session = ActionContext.getContext().getSession();
        String hpzl=session.get("legalhpzl").toString();
        String hphm=session.get("legalhphm").toString();
        String cjhm=session.get("legalcjhm").toString();
        String sfzmhm=session.get("legalsfzmhm").toString();
        String httpSessionId=session.get("legalhttpsessionid").toString();
        String scriptSessionId=session.get("legalscriptsessionid").toString();
        DefaultHttpClient httpclient=(DefaultHttpClient)session.get("legalhttpclient");
        
        LegalProcessUtil  legalProcessUtil=new LegalProcessUtil( ); 
        legalProcessUtil.setHttpclient(httpclient);
        legalProcessUtil.setCjhm(cjhm);
        legalProcessUtil.setHphm(hphm);
        legalProcessUtil.setHpzl(hpzl);
        legalProcessUtil.setSfzmhm(sfzmhm);
        legalProcessUtil.setHttpSessionId(httpSessionId);
        legalProcessUtil.setScriptSessionId(scriptSessionId);
        
        String html="";
		try{
			html=legalProcessUtil.handleVioOne();
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage());
			return result;
		}
		
		Document doc = Jsoup.parse(html);
		
		Elements floatLEles = doc.select("div.floatL");
		String content="";
		for(Element ele:floatLEles){
			content+=ele.text(); 
		}
		result.put("content", content); 
		
		
		Element tipEle1=doc.select("a[rel=tcontent1]").first();
		result.put("tipcanHandle", tipEle1.text());
		
		Element tipEle3=doc.select("a[rel=tcontent3]").first();
		result.put("tipunpaid", tipEle3.text());
		
		 
		Element tcontent1= doc.getElementById("tcontent1"); 
		Elements tcontent1Trs=tcontent1.getElementsByTag("tr");
		Elements tcontent1Tds;
		
		
		
		List<CanHandleRecordWapJson> canHandleList=new ArrayList<CanHandleRecordWapJson>();
		CanHandleRecordWapJson canHandleRecordWapJson;
		String desc=""; 
		result.put("canHandleInfo","");
		for(int rowIndex=1;rowIndex<tcontent1Trs.size();rowIndex++){
			Element  recordTr=tcontent1Trs.get(rowIndex);
			tcontent1Tds= recordTr.getElementsByTag("td"); 
			if(rowIndex==1&&tcontent1Tds.size()==1){
				result.put("canHandleInfo", tcontent1Tds.get(0).text()); 
				break;
			} 
			if(tcontent1Tds.size()<4){
				continue; 
			} 
			canHandleRecordWapJson=new CanHandleRecordWapJson();
			canHandleRecordWapJson.setId(tcontent1Tds.get(0).text());
			canHandleRecordWapJson.setXh(tcontent1Tds.get(1).select("input").first().attr("value")); 
			desc=tcontent1Tds.get(2).html();
			 
			Pattern pat = Pattern. compile ( "<br>" ); 
			String arrDesc[] = pat.split(desc); 
			for(String item:arrDesc){
				if(item.indexOf("违法地点：")>=0){
					canHandleRecordWapJson.setIllegalSites(item.replace("违法地点：", "")) ;
				    continue;
				}
				
				if(item.indexOf("违法行为：")>=0){
					canHandleRecordWapJson.setIllegalActivities(item.replace("违法行为：", "")) ;
					continue;
				}
				if(item.indexOf("违法时间：")>=0){
					canHandleRecordWapJson.setIllegalTime(item.replace("违法时间：", "")) ;
					continue;
				}
				if(item.indexOf("违法条款：")>=0){
					canHandleRecordWapJson.setIllegalClause(item.replace("违法条款：", "")) ;
					continue;
				}
				if(item.indexOf("处罚依据：")>=0){
					canHandleRecordWapJson.setBasisPunishment(item.replace("处罚依据：", "")) ;
					continue;
				}
				if(item.indexOf("采集机关：")>=0){
					canHandleRecordWapJson.setCollectionAgency(item.replace("采集机关：", "")) ;
					continue;
				}
				if(item.indexOf("违法记分数：")>=0){
					canHandleRecordWapJson.setIllegalScore(item.replace("违法记分数：", "")) ;
					continue;
				} 
			}  
			canHandleRecordWapJson.setAmount (tcontent1Tds.get(3).text());
			canHandleList.add(canHandleRecordWapJson);
		}
		result.put("canHandleList", canHandleList); 
		
		
		Element tcontent3= doc.getElementById("tcontent3"); 
		Elements tcontent3Trs=tcontent3.getElementsByTag("tr");
		Elements tcontent3Tds;
		List<UnpaidRecordWapJson> unpaidList=new ArrayList<UnpaidRecordWapJson>();
		UnpaidRecordWapJson unpaidRecordWapJson;
		result.put("unpaidInfo","");
		for(int rowIndex=1;rowIndex<tcontent3Trs.size();rowIndex++){
			Element  recordTr=tcontent3Trs.get(rowIndex);
			tcontent3Tds= recordTr.getElementsByTag("td"); 
			if(rowIndex==1&&tcontent3Tds.size()==1){
				result.put("unpaidInfo", tcontent3Tds.get(0).text()); 
				break;
			} 
			if(tcontent3Tds.size()<4){
				continue; 
			} 
			unpaidRecordWapJson=new UnpaidRecordWapJson();
			unpaidRecordWapJson.setId(tcontent3Tds.get(0).text()); 
			unpaidRecordWapJson.setIllegalInfo(tcontent3Tds.get(1).html());
			unpaidRecordWapJson.setHandleInfo(tcontent3Tds.get(2).html()); 
			unpaidRecordWapJson.setJdsbh(recordTr.select("input[name=jdsbh]").first().attr("value"));
			unpaidRecordWapJson.setOrderId(recordTr.select("input[name=orderId]").first().attr("value"));
			unpaidRecordWapJson.setOrderTime(recordTr.select("input[name=orderTime]").first().attr("value"));
			unpaidList.add(unpaidRecordWapJson);
			
		}
		
		
		
		
		result.put("unpaidList", unpaidList);  
        result.put("respCode", 0);
		result.put("respInfo", "");
		return result;
		
		
	}
	public HashMap<String,Object> confirmPageLegal(String xh){
		HashMap<String,Object> result=new HashMap<String,Object>();
        Map<String, Object> session = ActionContext.getContext().getSession();
        String hpzl=session.get("legalhpzl").toString();
        String hphm=session.get("legalhphm").toString();
        String cjhm=session.get("legalcjhm").toString();
        String sfzmhm=session.get("legalsfzmhm").toString();
        String httpSessionId=session.get("legalhttpsessionid").toString();
        String scriptSessionId=session.get("legalscriptsessionid").toString();
        DefaultHttpClient httpclient=(DefaultHttpClient)session.get("legalhttpclient");
        
        LegalProcessUtil  legalProcessUtil=new LegalProcessUtil( ); 
        legalProcessUtil.setHttpclient(httpclient);
        legalProcessUtil.setCjhm(cjhm);
        legalProcessUtil.setHphm(hphm);
        legalProcessUtil.setHpzl(hpzl);
        legalProcessUtil.setSfzmhm(sfzmhm);
        legalProcessUtil.setHttpSessionId(httpSessionId);
        legalProcessUtil.setScriptSessionId(scriptSessionId);
        
        String html="";
		try{
			html=legalProcessUtil.handleVioTwo(xh) ;
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage());
			return result;
		}
		
        Document doc = Jsoup.parse(html);
		
		Element  line1Ele  = doc.select("li.process_line1").first();
		result.put("lineContent1", line1Ele.text() );
		Element  line2Ele  = doc.select("li.process_line2").first();
		result.put("lineContentHtml2", line2Ele.html() );
		 
		
		Element  rdotEle=doc.getElementById("rdot_1");
		result.put("rdotName",  rdotEle.attr("name"));
		result.put("rdotValue",  rdotEle.attr("value"));
		Element  captchaEle=doc.getElementById("captcha_rand_flag_refersh");
		result.put("captchaName",  captchaEle.attr("name"));
		result.put("captchaValue",  captchaEle.attr("value"));
		
		
		result.put("respCode", 0);
		result.put("respInfo", "");
		return result; 
        
	}
	
	
	public HashMap<String,Object> verifyPageLegal(String xh,String captcha_rand_flag_refersh){
		HashMap<String,Object> result=new HashMap<String,Object>();
        Map<String, Object> session = ActionContext.getContext().getSession();
        String hpzl=session.get("legalhpzl").toString();
        String hphm=session.get("legalhphm").toString();
        String cjhm=session.get("legalcjhm").toString();
        String sfzmhm=session.get("legalsfzmhm").toString();
        String httpSessionId=session.get("legalhttpsessionid").toString();
        String scriptSessionId=session.get("legalscriptsessionid").toString();
        DefaultHttpClient httpclient=(DefaultHttpClient)session.get("legalhttpclient");
        
        LegalProcessUtil  legalProcessUtil=new LegalProcessUtil( ); 
        legalProcessUtil.setHttpclient(httpclient);
        legalProcessUtil.setCjhm(cjhm);
        legalProcessUtil.setHphm(hphm);
        legalProcessUtil.setHpzl(hpzl);
        legalProcessUtil.setSfzmhm(sfzmhm);
        legalProcessUtil.setHttpSessionId(httpSessionId);
        legalProcessUtil.setScriptSessionId(scriptSessionId);
        
        String html="";
		try{
			html=legalProcessUtil.handleVioValidateDriver(xh,captcha_rand_flag_refersh) ;
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage()); 
			return result;
		}
		 
        Document doc = Jsoup.parse(html); 
		 
		result.put("xm", doc.getElementById("xm").attr("value") );
		result.put("sfzmhm", doc.getElementById("sfzmhm").attr("value") ); 
		
		result.put("respCode", 0);
		result.put("respInfo", "");
		return result; 
        
	}
	public HashMap<String,Object> sendMsgLegal(String xm,String newsfzmhm){
		HashMap<String,Object> result=new HashMap<String,Object>();
        Map<String, Object> session = ActionContext.getContext().getSession();
        String hpzl=session.get("legalhpzl").toString();
        String hphm=session.get("legalhphm").toString();
        String cjhm=session.get("legalcjhm").toString();
        String sfzmhm=session.get("legalsfzmhm").toString();
        String httpSessionId=session.get("legalhttpsessionid").toString();
        String scriptSessionId=session.get("legalscriptsessionid").toString();
        DefaultHttpClient httpclient=(DefaultHttpClient)session.get("legalhttpclient");
        
        LegalProcessUtil  legalProcessUtil=new LegalProcessUtil( ); 
        legalProcessUtil.setHttpclient(httpclient);
        legalProcessUtil.setCjhm(cjhm);
        legalProcessUtil.setHphm(hphm);
        legalProcessUtil.setHpzl(hpzl);
        legalProcessUtil.setSfzmhm(sfzmhm);
        legalProcessUtil.setHttpSessionId(httpSessionId);
        legalProcessUtil.setScriptSessionId(scriptSessionId);
        
        String html="";
		try{
			html=legalProcessUtil.getDriver(xm,newsfzmhm) ;
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage()); 
			return result;
		}
		
		result.put("respCode", 0);
		result.put("respInfo", "");
		return result;  
        
        
	}
	
	public HashMap<String,Object> checkMsgLegal(String  mobilecode){
		HashMap<String,Object> result=new HashMap<String,Object>();
        Map<String, Object> session = ActionContext.getContext().getSession();
        String hpzl=session.get("legalhpzl").toString();
        String hphm=session.get("legalhphm").toString();
        String cjhm=session.get("legalcjhm").toString();
        String sfzmhm=session.get("legalsfzmhm").toString();
        String httpSessionId=session.get("legalhttpsessionid").toString();
        String scriptSessionId=session.get("legalscriptsessionid").toString();
        DefaultHttpClient httpclient=(DefaultHttpClient)session.get("legalhttpclient");
        
        LegalProcessUtil  legalProcessUtil=new LegalProcessUtil( ); 
        legalProcessUtil.setHttpclient(httpclient);
        legalProcessUtil.setCjhm(cjhm);
        legalProcessUtil.setHphm(hphm);
        legalProcessUtil.setHpzl(hpzl);
        legalProcessUtil.setSfzmhm(sfzmhm);
        legalProcessUtil.setHttpSessionId(httpSessionId);
        legalProcessUtil.setScriptSessionId(scriptSessionId);
        
        String html="";
		try{
			html=legalProcessUtil.validateMobilCode(mobilecode) ;
			if(html.indexOf("SUCCESS")<0){
				result.put("respCode", 1);
				result.put("respInfo", "验证码输入出错了！" ); 
				return result;
			}
			html=legalProcessUtil.validateDriverScore();
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "  出错 "+ex.getMessage()); 
			return result;
		}
		
		 
		try{
			html=legalProcessUtil.handleVioLast() ; 
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage()); 
			return result;
		}
        Document doc = Jsoup.parse(html); 
        String jdsbh=doc.select("input[name=jdsbh]").first().attr("value");
        String orderId=doc.select("input[name=orderId]").first().attr("value");
        String orderTime=doc.select("input[name=orderTime]").first().attr("value");		
        jdsbh=CommonUtil.trim(jdsbh);
        orderId=CommonUtil.trim(orderId);
        orderTime=CommonUtil.trim(orderTime);
        result.put("respCode", 0);
        result.put("respInfo", "");
        result.put("jdsbh", jdsbh);
		result.put("orderId", orderId);
		result.put("orderTime", orderTime);
		return result;  
        
        
	}
	
	
	public HashMap<String,Object>   payPageLegal() {
		HashMap<String,Object> result=new HashMap<String,Object>();
        Map<String, Object> session = ActionContext.getContext().getSession();
        String hpzl=session.get("legalhpzl").toString();
        String hphm=session.get("legalhphm").toString();
        String cjhm=session.get("legalcjhm").toString();
        String sfzmhm=session.get("legalsfzmhm").toString();
        String httpSessionId=session.get("legalhttpsessionid").toString();
        String scriptSessionId=session.get("legalscriptsessionid").toString();
        DefaultHttpClient httpclient=(DefaultHttpClient)session.get("legalhttpclient");
        
        LegalProcessUtil  legalProcessUtil=new LegalProcessUtil( ); 
        legalProcessUtil.setHttpclient(httpclient);
        legalProcessUtil.setCjhm(cjhm);
        legalProcessUtil.setHphm(hphm);
        legalProcessUtil.setHpzl(hpzl);
        legalProcessUtil.setSfzmhm(sfzmhm);
        legalProcessUtil.setHttpSessionId(httpSessionId);
        legalProcessUtil.setScriptSessionId(scriptSessionId);
        
        String html="";
		try{
			html=legalProcessUtil.handleVioLast() ; 
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage()); 
			return result;
		}
        Document doc = Jsoup.parse(html); 
        String jdsbh=doc.select("input[name=jdsbh]").first().attr("value");
        String orderId=doc.select("input[name=orderId]").first().attr("value");
        String orderTime=doc.select("input[name=orderTime]").first().attr("value");		
        jdsbh=CommonUtil.trim(jdsbh);
        orderId=CommonUtil.trim(orderId);
        orderTime=CommonUtil.trim(orderTime);	 
        try{
			html=legalProcessUtil.handleVioJds(jdsbh,"0") ; 
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage()); 
			return result;
		}
        
        try{
			html=legalProcessUtil.payServlet(  jdsbh,  orderId,  orderTime) ; 
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage()); 
			return result;
		} 
        
	 
		result.put("html", html); 
		result.put("respCode", 0);
		result.put("respInfo", "");
		return result; 
	}
	
	public HashMap<String,Object> paidPageLegal(String jdsbh,String orderId,String orderTime){
		HashMap<String,Object> result=new HashMap<String,Object>();
        Map<String, Object> session = ActionContext.getContext().getSession();
        String hpzl=session.get("legalhpzl").toString();
        String hphm=session.get("legalhphm").toString();
        String cjhm=session.get("legalcjhm").toString();
        String sfzmhm=session.get("legalsfzmhm").toString();
        String httpSessionId=session.get("legalhttpsessionid").toString();
        String scriptSessionId=session.get("legalscriptsessionid").toString();
        DefaultHttpClient httpclient=(DefaultHttpClient)session.get("legalhttpclient");
        
        LegalProcessUtil  legalProcessUtil=new LegalProcessUtil( ); 
        legalProcessUtil.setHttpclient(httpclient);
        legalProcessUtil.setCjhm(cjhm);
        legalProcessUtil.setHphm(hphm);
        legalProcessUtil.setHpzl(hpzl);
        legalProcessUtil.setSfzmhm(sfzmhm);
        legalProcessUtil.setHttpSessionId(httpSessionId);
        legalProcessUtil.setScriptSessionId(scriptSessionId);
        
        String html="";
        
		jdsbh=CommonUtil.trim(jdsbh);
        orderId=CommonUtil.trim(orderId);
        orderTime=CommonUtil.trim(orderTime);	 
        /*
        try{
			html=legalProcessUtil.handleVioJds(jdsbh,"0") ; 
			System.out.println("html"+html);
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "handleVioJds 出错 "+ex.getMessage()); 
			return result;
		}*/
        try{
			html=legalProcessUtil.payServlet(  jdsbh,  orderId,  orderTime) ;  
		}catch(Exception ex){
			result.put("respCode", 1);
			result.put("respInfo", "出错 "+ex.getMessage()); 
			return result;
		}  
		result.put("html", html); 
		result.put("respCode", 0);
		result.put("respInfo", "");
		return result; 
	}
	
}
 