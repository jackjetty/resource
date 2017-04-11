package com.rising.mobilepayment.job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

 





import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext; 

import com.rising.mobilepayment.bean.OrderInfo;
import com.rising.mobilepayment.bean.Product;
import com.rising.mobilepayment.bean.QQAgentOrder;
import com.rising.mobilepayment.bean.QQMonthLog; 
import com.rising.mobilepayment.bean.QQPool; 
import com.rising.mobilepayment.bean.QQService;
import com.rising.mobilepayment.bean.SendMessageLog;
import com.rising.mobilepayment.commom.CommonUtil;
import com.rising.mobilepayment.commom.Constant;
import com.rising.mobilepayment.commom.DateUtil;
import com.rising.mobilepayment.commom.GetApplicationContext;
import com.rising.mobilepayment.mapper.ProductMapper;
import com.rising.mobilepayment.mapper.QQPerMonthReSubmitMapper;
import com.rising.mobilepayment.mapper.QQPerMonthRecordMapper2;
import com.rising.mobilepayment.mapper.QQPerMonthStatusMapper;
import com.rising.mobilepayment.mapper.QQPerMonthStatusMapper2;
import com.rising.mobilepayment.mapper.QQAgentOrderMapper;
import com.rising.mobilepayment.mapper.QQServiceMapper;
import com.rising.mobilepayment.mapper.QQPoolMapper;
import com.rising.mobilepayment.mapper.SendMessageLogMapper;
import com.rising.mobilepayment.service.QQAgentService;
import com.rising.mobilepayment.service.SMSService;
  

 
@Component("qqAgentPayTask")
public class QQAgentPayTask   {
	
	private Log log = LogFactory.getLog(QQAgentPayTask.class);
	@Autowired
	private QQPoolMapper qqPoolMapper;
	@Autowired
	private QQServiceMapper qqServiceMapper; 
	@Autowired
	private QQAgentOrderMapper qqAgentOrderMapper;
	
	@Autowired
	private SMSService smsService;
	 
	public void qqAgentPay( )  {  
		HashMap<String, Object> paraMap =new HashMap<String, Object> ();
		paraMap.put("pageSize",10); 
		List<QQPool> qqPoolList=qqPoolMapper.getUnCheckList(paraMap);  
		
		String result = ""; 
	    Calendar calendar = Calendar.getInstance();
		Date curDate= calendar.getTime(); 
		JSONObject object;
		for(QQPool qqPool:qqPoolList){ 
			result=getHttpQQBalance(CommonUtil.trim(qqPool.getQqNumber()),CommonUtil.trim(qqPool.getQqPassword()));
			if(result.equals(""))
				continue; 
			 try { 
				    object = JSONObject.fromObject(result);
					int resCode = object.getInt ("resCode");
					String resInfo=CommonUtil.trim(object.getString ("resInfo")); 
					qqPool.setCreateTime(qqPool.getCreateTime()==null?curDate:qqPool.getCreateTime());	
					qqPool.setLastCheckTime(curDate);
					qqPool.setQqStatus(new Integer(resCode).toString()); 
					if(resInfo.length()>80){
						resInfo=resInfo.substring (0,77);
					}
					if(resCode!=0){
						qqPool.setRemark(resInfo); 
						
					}else{
						qqPool.setQbBalance(object.getInt("qbBalance")); 
						qqPool.setRemark("");
					}
					qqPoolMapper.update(qqPool); 
					 
				} catch (Exception ex) { 
					log.error("解析！"+result+ ex.toString());
					continue;
				} 
			 
		 }
		paraMap =new HashMap<String, Object> ();
		paraMap.put("pageSize",20); 
		calendar = Calendar.getInstance();
		curDate= calendar.getTime(); 
		calendar.add(Calendar.HOUR_OF_DAY , -6); 
		paraMap.put("fromOrderTime", CommonUtil.doDateForm(calendar.getTime(), "yyyy-MM-dd HH:mm"));
		
		
		List <QQAgentOrder>qqAgentOrderList=qqAgentOrderMapper.getUnPayList(paraMap);
		for(QQAgentOrder qqAgentOrder:qqAgentOrderList){ 
			qqAgentSinglePay(qqAgentOrder);
		}
		
		//再次重新获取未支付成功的保存下了啊
		qqAgentOrderList=qqAgentOrderMapper.getUnPayList(paraMap);
		 
		for(QQAgentOrder qqAgentOrder:qqAgentOrderList){ 
			qqAgentSinglePay(qqAgentOrder);
		} 
		
		//再次重新获取未支付成功的保存下了啊
		qqAgentOrderList=qqAgentOrderMapper.getUnPayList(paraMap);
		 
		for(QQAgentOrder qqAgentOrder:qqAgentOrderList){ 
			qqAgentSinglePay(qqAgentOrder);
		} 
				
		//再次重新获取未支付成功的保存下了啊
		qqAgentOrderList=qqAgentOrderMapper.getUnPayList(paraMap);
		 
		for(QQAgentOrder qqAgentOrder:qqAgentOrderList){ 
			qqAgentSinglePay(qqAgentOrder);
		} 
		
		
		
		
	}
	
	
	 
	
	 
	
	/*
	private String  getHttpQQPayRefund(String  refund_date,String batch_no,String batch_num,String detail_data ) {
		try {
	        Thread.sleep(500); 
		} catch (InterruptedException e) {
		        e.printStackTrace();
		}
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("refund_date", refund_date);
		paraMap.put("batch_no", batch_no); 
		paraMap.put("batch_num", batch_num); 
		paraMap.put("detail_data", detail_data);  
		
		String parameter = CommonUtil.mapToString(paraMap,"&"); 
		String result = "";
		java.net.URL httpUrl;
		StringBuffer urlBuffer;
		urlBuffer=new StringBuffer("");
		urlBuffer.append(Constant.APPSERVERURL); 
		urlBuffer.append("/AppServer");
		urlBuffer.append("/qqReback");
		urlBuffer.append("/qqRefund");
		try {
			httpUrl = new URL(urlBuffer.toString() );
			URLConnection uc = (URLConnection) httpUrl.openConnection();
			uc.setReadTimeout(30000);
			uc.setDoOutput(true);
			uc.setDoInput(true);
			//PrintWriter out = new PrintWriter(uc.getOutputStream() );
			//out.write(parameter);
			
			
			 OutputStreamWriter out = new OutputStreamWriter(uc  
		                .getOutputStream(), "utf-8");  
			 System.out.println("parameter"+parameter);
			 out.write(parameter);//post的关键所在！  
		        // remember to clean up  
		     out.flush();  
		     out.close(); 
			
			 
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			
			in.close();
		} catch (Exception e1) {
			log.error("getHttpQQPayRefund()->发送包月情况查询请求失败！" + e1.toString());
            return "";
		}
		return result; 
	
 
   }*/
	
	
	
	
	
	public boolean qqAgentSinglePay(QQAgentOrder qqAgentOrder){
		String serviceCode=CommonUtil.trim(qqAgentOrder.getServiceCode());
		String openMonth=CommonUtil.trim(qqAgentOrder.getOpenMonth());
		String targetQQNum=CommonUtil.trim(qqAgentOrder.getTargetQQNum());
		String phoneNumber=CommonUtil.trim(qqAgentOrder.getPhoneNumber());
		String gameZone=CommonUtil.trim(qqAgentOrder.getAgentAdd2());
		QQService qqService=qqServiceMapper.findById(serviceCode);
		if(qqService==null)
			 return false;
		
		
		String busId=CommonUtil.trim(qqService.getBusId());
		String serviceName=CommonUtil.trim(qqService.getServiceName());
		String openAid=CommonUtil.trim(qqService.getOpenAid());
		String unitPrice=CommonUtil.trim(qqService.getUnitPrice());
		int price=(int)Math.rint(CommonUtil.castDouble(unitPrice)*CommonUtil.castDouble(openMonth) );
		HashMap<String, Object> paraMap =new HashMap<String, Object> ();
		paraMap.put("price",price);
		paraMap.put("pageSize",5);
		 
		List<QQPool> qqPoolList=qqPoolMapper.getPayQQPoolList(paraMap);
		String result = "";
		JSONObject object;
		
		StringBuffer msgContent=new StringBuffer("");
		
		if(busId.equalsIgnoreCase(Constant.BUSID_QQSERVICE)){
			msgContent.append("尊敬的客户：您好，");
			msgContent.append("您通过支付宝支付,").append("办理的").append(openMonth).append("个月") 
			.append(serviceName).append("服务,");
			msgContent.append("已经开通到您QQ帐号:").append(targetQQNum).append(" ，请注意查收，详询18072749082。");
			
		}
		if(busId.equalsIgnoreCase(Constant.BUSID_QQGAME)){
			msgContent.append("尊敬的客户：您好，");
			msgContent.append("您通过支付宝支付,往").append(targetQQNum).append("充值的").append(serviceName)
			.append(openMonth).append("个点卷") ; 
			msgContent.append(",请注意查收,详询18072749082。");
			
		} 
		//getHttpQQPayGame(String  qqNumber,String qqPassword,String targetQQNum,String serviceCode,String openAid,String gameZone,String openMonth) 
		for(QQPool qqPool:qqPoolList){
			result="";
			if(busId.equalsIgnoreCase(Constant.BUSID_QQSERVICE)){
				result=getHttpQQPayService(CommonUtil.trim(qqPool.getQqNumber()),CommonUtil.trim(qqPool.getQqPassword() ),
			            targetQQNum,  serviceCode,  openAid,  openMonth);
			}
			if(busId.equalsIgnoreCase(Constant.BUSID_QQGAME)){
				result=getHttpQQPayGame(CommonUtil.trim(qqPool.getQqNumber()),CommonUtil.trim(qqPool.getQqPassword() ),
			            targetQQNum,  serviceCode,  openAid,gameZone,  openMonth);
			}
			
			
			if(result.equals(""))
				continue; 
			try { 
			    object = JSONObject.fromObject(result);
				int resCode = object.getInt ("resCode");
				String resInfo=CommonUtil.trim(object.getString ("resInfo")); 
				if(resInfo.length()>80){
					resInfo=resInfo.substring (0,77);
				}
				qqPool.setLastChargeTime(new Date());
				qqPool.setQqStatus(new Integer(resCode).toString());  
				qqAgentOrder.setChargeStatus(new Integer(resCode).toString()); 
				if(resCode!=0){
					qqPool.setRemark(resInfo); 
					qqAgentOrder.setRemark(resInfo); 
				}else{
					qqPool.setQbBalance(object.getInt("qbBalance")-price); 
					qqPool.setRemark(""); 
					qqAgentOrder.setRemark("");
					qqAgentOrder.setChargeTime (new Date());
				}
				qqPoolMapper.update(qqPool); 
				
				if(resCode==0){
					qqAgentOrder.setAgentQQNum(qqPool.getQqNumber());
				}
				qqAgentOrderMapper.update(qqAgentOrder);
				if(resCode==0){  
					smsService.noticeSMS("qqservice", phoneNumber, msgContent.toString());
					break;
				}
					 
				
				
			}catch (Exception ex) { 
				log.error("解析！"+result + ex.toString());
				continue;
			} 
			
		}
		
		
		return true;
		
	}
	
	
	private String  getHttpQQPayService(String  qqNumber,String qqPassword,String targetQQNum,String serviceCode,String openAid,String openMonth) {
		try {
	        Thread.sleep(500); 
		} catch (InterruptedException e) {
		        e.printStackTrace();
		}
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("qqNumber", qqNumber);
		paraMap.put("qqPassword", qqPassword); 
		paraMap.put("targetQQNum", targetQQNum); 
		paraMap.put("serviceCode", serviceCode); 
		paraMap.put("openAid", openAid); 
		paraMap.put("openMonth", openMonth);  
		String parameter = CommonUtil.mapToString(paraMap,"&");
		 
		String result = "";
		java.net.URL httpUrl;
		StringBuffer urlBuffer;
		urlBuffer=new StringBuffer("");
		urlBuffer.append(Constant.APPSERVERURL); 
		urlBuffer.append("/AppServer");
		urlBuffer.append("/qqPay");
		urlBuffer.append("/payQQService");
		try {
			httpUrl = new URL(urlBuffer.toString() );
			URLConnection uc = (URLConnection) httpUrl.openConnection();
			uc.setReadTimeout(30000);
			uc.setDoOutput(true);
			uc.setDoInput(true);
			PrintWriter out = new PrintWriter(uc.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			
			in.close();
		} catch (Exception e1) {
			log.error("getHttpQQPayService()->发送包月情况查询请求失败！" + e1.toString());
            return "";
		}
		return result; 
	
 
   }
	
	
	private String  getHttpQQPayGame(String  qqNumber,String qqPassword,String targetQQNum,String serviceCode,String openAid,String gameZone,String openMonth) {
		try {
	        Thread.sleep(500); 
		} catch (InterruptedException e) {
		        e.printStackTrace();
		}
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("qqNumber", qqNumber);
		paraMap.put("qqPassword", qqPassword); 
		paraMap.put("targetQQNum", targetQQNum); 
		paraMap.put("serviceCode", serviceCode); 
		paraMap.put("openAid", openAid); 
		paraMap.put("gameZone", gameZone);  
		paraMap.put("openMonth", openMonth);  
		String parameter = CommonUtil.mapToString(paraMap,"&");
		 
		String result = "";
		java.net.URL httpUrl;
		StringBuffer urlBuffer;
		urlBuffer=new StringBuffer("");
		urlBuffer.append(Constant.APPSERVERURL); 
		urlBuffer.append("/AppServer");
		urlBuffer.append("/qqPay");
		urlBuffer.append("/payQQGame");
		try {
			httpUrl = new URL(urlBuffer.toString() );
			URLConnection uc = (URLConnection) httpUrl.openConnection();
			uc.setReadTimeout(30000);
			uc.setDoOutput(true);
			uc.setDoInput(true);
			PrintWriter out = new PrintWriter(uc.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			
			in.close();
		} catch (Exception e1) {
			log.error("getHttpQQPayGame()->发送包月情况查询请求失败！" + e1.toString());
            return "";
		}
		return result; 
	
 
   }
	
	private String  getHttpQQBalance(String  qqNumber,String qqPassword) {
		try {
	        Thread.sleep(500); 
		} catch (InterruptedException e) {
		        e.printStackTrace();
		}
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("qqNumber", qqNumber);
		paraMap.put("qqPassword", qqPassword); 
		String parameter = CommonUtil.mapToString(paraMap,"&");
		 
		String result = "";
		java.net.URL httpUrl;
		StringBuffer urlBuffer;
		urlBuffer=new StringBuffer("");
		urlBuffer.append(Constant.APPSERVERURL); 
		urlBuffer.append("/AppServer");
		urlBuffer.append("/qqPay");
		urlBuffer.append("/getQQBalance");
		try {
			httpUrl = new URL(urlBuffer.toString() );
			 
			URLConnection uc = (URLConnection) httpUrl.openConnection();
			uc.setReadTimeout(30000);
			uc.setDoOutput(true);
			uc.setDoInput(true);
			PrintWriter out = new PrintWriter(uc.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			
			in.close();
		} catch (Exception e1) {
			log.error("getHttpQQBalance()->发送包月情况查询请求失败！" + e1.toString());
            return "";
		}
		return result; 
	
 
   }
	
}
	