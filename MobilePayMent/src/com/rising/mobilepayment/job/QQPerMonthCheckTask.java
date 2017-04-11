package com.rising.mobilepayment.job;
 
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
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
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.OrderInfo;
import com.rising.mobilepayment.bean.Product;
import com.rising.mobilepayment.bean.QQMonthLog;
import com.rising.mobilepayment.bean.QQPerMonthReSubmit;
import com.rising.mobilepayment.bean.QQPerMonthRecord;
import com.rising.mobilepayment.bean.QQPerMonthStatus; 
import com.rising.mobilepayment.bean.SendMessageLog;
import com.rising.mobilepayment.commom.CommonUtil;
import com.rising.mobilepayment.commom.Constant;
import com.rising.mobilepayment.commom.DateUtil;
import com.rising.mobilepayment.commom.GetApplicationContext;
import com.rising.mobilepayment.commom.HttpUtil;
import com.rising.mobilepayment.mapper.OrderInfoMapper;
import com.rising.mobilepayment.mapper.ProductMapper;
import com.rising.mobilepayment.mapper.QQPerMonthReSubmitMapper;
import com.rising.mobilepayment.mapper.QQPerMonthRecordMapper2;
import com.rising.mobilepayment.mapper.QQPerMonthStatusMapper;
import com.rising.mobilepayment.mapper.QQPerMonthStatusMapper2;
import com.rising.mobilepayment.mapper.SysPhoneNumberMapper;
import com.rising.mobilepayment.mapper.QQPerMonthRecordMapper;
import com.rising.mobilepayment.mapper.QQMonthLogMapper;
import com.rising.mobilepayment.mapper.SendMessageLogMapper; 
  
@Component("qqPerMonthCheckTask")
public class QQPerMonthCheckTask  {
	
	private Log log = LogFactory.getLog(QQPerMonthCheckTask.class);
	
	
	
	@Autowired 
	private SendMessageLogMapper sendMessageLogMapper;
	
	@Autowired
	private QQMonthLogMapper qqMonthLogMapper;
	
	@Autowired
	private QQPerMonthRecordMapper qQPerMonthRecordMapper;
	
	@Autowired
	private QQPerMonthRecordMapper2 qqPerMonthRecordMapper2;
	
	@Autowired
	private QQPerMonthStatusMapper qQPerMonthStatusMapper;
	
	
	@Autowired
	private QQPerMonthStatusMapper2 qQPerMonthStatusMapper2;
	
	@Autowired 
	private QQPerMonthReSubmitMapper qQPerMonthReSubmitMapper;
	 
	
	
	@Autowired 
	private OrderInfoMapper orderInfoMapper;
	
	
	private FileOutputStream logOS = null;
	private File logFile;
	
	
	public void qqMonthPayInit( )  { 
		Date curDate=new Date();
		String curMonthForm=CommonUtil.doDateForm(curDate,"yyyyMM"); 
		HashMap<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("targetMonth", curMonthForm);
		//int countNum=qqMonthLogMapper.getCountByMonth(paraMap); 
		//paraMap=new HashMap<String, Object>(); 
		//if(countNum==0){
		qqMonthLogMapper.monthInit(curMonthForm);
		
		
		Calendar calendar = Calendar.getInstance();   
		calendar.add(Calendar.MONTH, -1); 
		orderInfoMapper.updateClient(CommonUtil.doDateForm( calendar.getTime(),"yyyy-MM"), "12", "电信微视窗");
		
		 
		
		
		
			
		//}
		
		
	}
	 

	 
	public void qqMonthPayCheck( )  { 
		 
		Date curDate=new Date();
		logFile=CommonUtil.getCheckLogFile();
		try {
			logOS=new FileOutputStream(logFile, true);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String curMonthForm=CommonUtil.doDateForm(curDate,"yyyyMM"); 
		String curDateForm=CommonUtil.doDateForm(curDate,"yyyy-MM-dd"); 
		HashMap<String, Object> paraMap =new HashMap<String, Object> ();
		paraMap.put("curMonthForm", curMonthForm);
		paraMap.put("curDateForm", curDateForm);
		paraMap.put("pageSize", 10);
		
		List <QQMonthLog> qqMonthLogUnCheckList= qqMonthLogMapper.getUnCheckList( paraMap);
		String phoneNumber;
		String qqNumber;
		String monthInfo;
		String fee;
		String targetMonth;
		String payMoney;
		HashMap<String, Object>  montInfoMap;  
		
		String hbQQNum;
		String hbFee;
		String hbMonth;
		String hbStatus;
		String hbRegDate;
		String hbApplyDate;
		String hbCancelDate; 
		 
		
        for(QQMonthLog qqMonthLog:qqMonthLogUnCheckList){
			
			try {
		        Thread.sleep(5000);//括号里面的5000代表5000毫秒，也就是5秒，可以该成你需要的时间
			} catch (InterruptedException e) {
			        e.printStackTrace();
			}
			phoneNumber=CommonUtil.trim(qqMonthLog.getPhoneNumber());
			qqNumber=CommonUtil.trim(qqMonthLog.getQqNumber());
			fee=CommonUtil.trim(qqMonthLog.getFee());
			monthInfo=getMonthInfo(   phoneNumber); 
			if(monthInfo.equals("")) continue; 
			try {
				logOS.write((CommonUtil.getDateForm("yyyy-MM-dd HH:mm:ss")
						+" "+phoneNumber
						+" "+monthInfo+"\n").getBytes("UTF-8")); 
			} catch (UnsupportedEncodingException e) {
				 
				e.printStackTrace();
			} catch (IOException e) { 
				e.printStackTrace();
			}
			
			montInfoMap=CommonUtil.stringToLowerMap(monthInfo); 
			 
			qqMonthLog.setLastCheckDate(new Date());
			 
			 
			if(!CommonUtil.trim(montInfoMap.get("result")).equals("0") ){
				qqMonthLogMapper.update(qqMonthLog);
				continue;
			}
			 
			
			String fromSource=CommonUtil.trim(qqMonthLog.getFromSource());
		 
			//包月状态修改
			ArrayList<QQPerMonthStatus> qqPerMonthStatusList;
			if(fromSource.equals("160"))
				qqPerMonthStatusList= qQPerMonthStatusMapper2 .findByPhoneNumber(phoneNumber);
			else
				qqPerMonthStatusList= qQPerMonthStatusMapper .findByPhoneNumber(phoneNumber);
			 
			QQPerMonthStatus qqPerMonthStatus;
			if(qqPerMonthStatusList!=null&&qqPerMonthStatusList.size()>0){
				qqPerMonthStatus = qqPerMonthStatusList.get(0);
				qqPerMonthStatus.setApplyDate(CommonUtil.trim( montInfoMap.get("applydate")));
				qqPerMonthStatus.setMonth(CommonUtil.trim(montInfoMap.get("month")));
				qqPerMonthStatus.setCancelDate(CommonUtil.trim(montInfoMap.get("canceldate")));
				qqPerMonthStatus.setFee(CommonUtil.trim(montInfoMap.get("fee")));
				qqPerMonthStatus.setQqNumber(CommonUtil.trim(montInfoMap.get("qqnum")));
				qqPerMonthStatus.setRegDate(CommonUtil.trim(montInfoMap.get("regdate")));
				qqPerMonthStatus.setStatus(CommonUtil.trim(montInfoMap.get("status")) );
				qqPerMonthStatus.setUpdateDate(CommonUtil.trim(montInfoMap.get("updatedate")));
				if(fromSource.equals("160"))
					qQPerMonthStatusMapper2.updateQQPerMonthStatus(qqPerMonthStatus);
				else
					qQPerMonthStatusMapper.updateQQPerMonthStatus(qqPerMonthStatus);
				
			}
			else{
				qqPerMonthStatus = new QQPerMonthStatus();
				qqPerMonthStatus.setPhoneNumber(phoneNumber);
				qqPerMonthStatus.setApplyDate(CommonUtil.trim( montInfoMap.get("applydate")));
				qqPerMonthStatus.setMonth(CommonUtil.trim(montInfoMap.get("month")));
				qqPerMonthStatus.setCancelDate(CommonUtil.trim(montInfoMap.get("canceldate")));
				qqPerMonthStatus.setFee(CommonUtil.trim(montInfoMap.get("fee")));
				qqPerMonthStatus.setQqNumber(CommonUtil.trim(montInfoMap.get("qqnum")));
				qqPerMonthStatus.setRegDate(CommonUtil.trim(montInfoMap.get("regdate")));
				qqPerMonthStatus.setStatus(CommonUtil.trim(montInfoMap.get("status")) );
				qqPerMonthStatus.setUpdateDate(CommonUtil.trim(montInfoMap.get("updatedate")));
				
				if(fromSource.equals("160"))
					qQPerMonthStatusMapper2.addQQPerMonthStatus(qqPerMonthStatus);
				else
					qQPerMonthStatusMapper.addQQPerMonthStatus(qqPerMonthStatus);
			}
			 
			//result=0$QQNUM=2318301619$FEE=50$STATUS=2$MONTH=201506$REGDATE=2015-01-12 11:59:42$APPLYDATE=2015-01-12 11:59:42$CANCELDATE=2015-06-18 09:32:33$UPDATEDATE=
			
			//号码不一致的情况	          
			hbQQNum=CommonUtil.trim(montInfoMap.get("qqnum"));
			hbQQNum=hbQQNum.replace('z', ' ');
			hbFee=CommonUtil.trim(montInfoMap.get("fee"));
			
			//已经取消包月了
			hbStatus=CommonUtil.trim(montInfoMap.get("status"));
			hbRegDate=CommonUtil.trim(montInfoMap.get("regdate"));
			hbApplyDate=CommonUtil.trim(montInfoMap.get("applydate"));
			hbCancelDate=CommonUtil.trim(montInfoMap.get("canceldate"));
			qqMonthLog.setOrderStatus(hbStatus);
			qqMonthLog.setRegDate(CommonUtil.getDateByForm(hbRegDate, "yyyy-MM-dd HH:mm:ss"));
			qqMonthLog.setApplyDate(CommonUtil.getDateByForm(hbApplyDate, "yyyy-MM-dd HH:mm:ss"));
			if(hbStatus.equals("2")){
				qqMonthLog.setCancelDate(CommonUtil.getDateByForm(hbCancelDate, "yyyy-MM-dd HH:mm:ss"));
				
			} 
			 
			if(!qqNumber.equalsIgnoreCase(hbQQNum)){  
				qqMonthLogMapper.update(qqMonthLog);
				continue;
			} 
			
			//扣费
			
			if((!hbFee.equalsIgnoreCase(fee)) && CommonUtil.isNum(hbFee)  ){
				qqMonthLog.setFee(hbFee);
				qqMonthLog.setPayMoney(CommonUtil.castString(CommonUtil.castDouble(hbFee)*0.95,"0.00"));
				
			}
			
			 
			
			hbMonth=CommonUtil.trim(montInfoMap.get("month"));
			
			if(hbStatus.equals("2")&&hbMonth.equals("")){ 
				qqMonthLogMapper.update(qqMonthLog);
				continue;
			} 
			
			//包月成功
			QQPerMonthRecord qqPerMonthRecord;
			
			 
			if(hbMonth.equalsIgnoreCase(curMonthForm) ){
				qqMonthLog.setPayStatus("已扣费");
				qqMonthLog.setMsgSign("0");
				qqPerMonthRecord= new QQPerMonthRecord();
				qqPerMonthRecord.setPhoneNumber( phoneNumber);
				 
				 
				qqPerMonthRecord.setQQNumber(qqNumber);
				qqPerMonthRecord.setMonth(curMonthForm);
				 
				qqPerMonthRecord.setCost(Float.parseFloat((String) hbFee));
				qqPerMonthRecord.setSendScore(0);
				 
				qqPerMonthRecord.setPayMoney((float) (Float.parseFloat((String) hbFee) * 0.95));
				if(CommonUtil.isInteger(qqMonthLog.getPaymethodId()))
				            qqPerMonthRecord.setPayMethodId(CommonUtil.castInt(qqMonthLog.getPaymethodId()));
				else
					        qqPerMonthRecord.setPayMethodId(1000);
				
				qqPerMonthRecord.setCheckTime(new Date());
				qqPerMonthRecord.setStatus("扣费成功");
				
				if(fromSource.equals("160"))
					qqPerMonthRecordMapper2.add(qqPerMonthRecord);
				else
					qQPerMonthRecordMapper.add(qqPerMonthRecord);
				
				
				 
				
			} 
			if(hbMonth.equals("")&&hbStatus.equals("4")){
				
				qqMonthLog.setPayStatus("扣费失败");
				qqMonthLog.setMsgSign("1");
				qqPerMonthRecord = new QQPerMonthRecord();
				qqPerMonthRecord.setPhoneNumber( phoneNumber); 
				qqPerMonthRecord.setQQNumber(qqNumber);
				qqPerMonthRecord.setMonth(curMonthForm);
				 
				qqPerMonthRecord.setCost(Float.parseFloat((String) hbFee));
				qqPerMonthRecord.setSendScore(0);
				 
				qqPerMonthRecord.setPayMoney(0.0f);
				qqPerMonthRecord.setCheckTime(new Date());
				qqPerMonthRecord.setStatus("扣费失败");
				if(fromSource.equals("160"))
					qqPerMonthRecordMapper2.add(qqPerMonthRecord);
				else
					qQPerMonthRecordMapper.add(qqPerMonthRecord);
				
			} 
			qqMonthLogMapper.update(qqMonthLog); 
			
			 /*
			//重新提交包月信息
			QQPerMonthReSubmit reSubmit;
			String submitResult;
			//(hbMonth.equals("")||
			try {
		        Thread.sleep(500);//括号里面的5000代表5000毫秒，也就是5秒，可以该成你需要的时间
			} catch (InterruptedException e) {
			        e.printStackTrace();
			}
			if( (!hbMonth.equalsIgnoreCase(curMonthForm))&&hbStatus.equals("1")){
				
				reSubmit = new QQPerMonthReSubmit();  
				reSubmit.setPayMoney((float) (Float.parseFloat(hbFee) * 0.95));
				reSubmit.setPhoneNumber(phoneNumber);
				reSubmit.setProductId("1605002");
				if(hbFee.equalsIgnoreCase("10")){
					
					reSubmit.setProductId("1605003");
				}
                if(hbFee.equalsIgnoreCase("20")){
					
					reSubmit.setProductId("1605004");
				}
                if(hbFee.equalsIgnoreCase("30")){
					
					reSubmit.setProductId("1605001");
				}
				
				reSubmit.setQqNumber(hbQQNum);
				reSubmit.setSubmitTime(new Date()); 
				submitResult =reSubmit(reSubmit, hbFee); 
				reSubmit.setSubmitResult(submitResult);
				qQPerMonthReSubmitMapper.add(reSubmit);
			} 
			*/
			
			 
			
		}
		
		try {
			logOS.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//发送短信了哦
		Calendar cal = Calendar.getInstance(); 
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		
		List <QQMonthLog> qqMonthLogUnSendMsgList ;
		boolean sendSign;
		
		if(hour>=9 && hour<=19){
			paraMap =new HashMap<String, Object> ();
			paraMap.put("fromSource", "168"); 
			paraMap.put("pageSize", 200);
			qqMonthLogUnSendMsgList= qqMonthLogMapper.getUnSendMsgList( paraMap);
			for(QQMonthLog qqMonthLog:qqMonthLogUnSendMsgList){ 
				try {
			        Thread.sleep(500);//括号里面的5000代表5000毫秒，也就是5秒，可以该成你需要的时间
				} catch (InterruptedException e) {
				        e.printStackTrace();
				}
				phoneNumber=CommonUtil.trim(qqMonthLog.getPhoneNumber());
				qqNumber=CommonUtil.trim(qqMonthLog.getQqNumber());
				fee=CommonUtil.trim(qqMonthLog.getFee());
				targetMonth=CommonUtil.trim(qqMonthLog.getTargetMonth());
				payMoney=CommonUtil.trim(qqMonthLog.getPayMoney());
				sendSign=false;
				if(phoneNumber.length()>0
						&&qqNumber.length()>0
						&&fee.length()>0
						&&targetMonth.length()>=6){
					sendSign=sendMessageNotice(  phoneNumber,  qqNumber,  fee,  targetMonth,  payMoney);
					
				}
				if(sendSign){
					qqMonthLog.setMsgSign("1");
					qqMonthLog.setMsgDate(new Date());
					qqMonthLogMapper.update(qqMonthLog);
				}
				
				
			}
			  
		} 
		 
	}
	
	
	
	

   private boolean sendMessageNotice(String phoneNumber,String qqNumber,String fee,String targetMonth,String payMoney){
		
		
		//您于TIME在168充值宝客户端上成功包月NUMBER个Q币，金额为MONEY元，费用已从手机话费余额中扣除，包月即时生效，下月自动包月，如需退订请通过客户端操作，详询16887885。
		Calendar calendar = Calendar.getInstance();
		Date curDate= calendar.getTime(); 
		StringBuffer msgContent=new StringBuffer("");
		msgContent.append("尊敬的客户：您好，");
		msgContent.append("您").append(targetMonth.substring(0,4)).append("年").append(targetMonth.substring(4,6)).append("月份");
		 //成功充值
		msgContent.append("包月").append(fee).append("个Q币，");
		msgContent.append("已成功充值到").append(qqNumber).append("，");
		//msgContent.append("扣费").append(payMoney).append("元，");
		msgContent.append("如需退订可通过\"168充值宝客户端\"操作，");
		msgContent.append("详询 16887885。");
		
		SendMessageLog log1 = new SendMessageLog();
		log1.setLogTime( curDate);
		log1.setLogType("request");
		log1.setMessageContent(msgContent.toString());
		log1.setMessageType("Q币包月扣费");
		log1.setSendPhoneNumber(phoneNumber);
		try {
			String dataString = "objid=16670$username="
					+ URLEncoder.encode(msgContent.toString(), "GB2312")
					+ "$paymoney=$payid=$caller="
					+ phoneNumber;
			log1.setDataString(dataString);
			sendMessageLogMapper.add(log1);
			
			String returnString =   new HttpUtil(). perGet(   dataString,"gb2312") ; 
			
			/*
			java.net.URL httpUrl;
            try{
				httpUrl = new URL(Constant.HBSERVERURL);
				URLConnection uc = (URLConnection) httpUrl.openConnection();
				uc.setDoOutput(true);
				uc.setDoInput(true);
				PrintWriter out = new PrintWriter(uc.getOutputStream());
				out.write(dataString);
				out.flush();
				out.close();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						uc.getInputStream(), "gb2312"));
				String line;
				while ((line = in.readLine()) != null) {
					returnString += line;
				}
				in.close();

			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			}*/
			if (returnString.equalsIgnoreCase("")){
				return false;
			}
		 
		  
			SendMessageLog log2 = new SendMessageLog();
			log2.setLogTime(new Date());
			log2.setLogType("response");
			log2.setMessageContent(msgContent.toString());
			log2.setMessageType("Q币包月扣费");
			log2.setSendPhoneNumber(phoneNumber);
			log2.setDataString(returnString);
			sendMessageLogMapper.add(log2);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		    return false;
		} 
		return true; 
		
	} 
	
	
	private String  getMonthInfo(String  phonenumber) {
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("objid", "16052");
		paraMap.put("caller", phonenumber);
		paraMap.put("username", "1165945426");
		paraMap.put("paymoney", 30);
		String parameter = CommonUtil.mapToString(paraMap); 
		 
		String result = "";
		/*java.net.URL httpUrl=null;
		PrintWriter out=null;
		HttpURLConnection uc=null;
		BufferedReader in=null;
		try {
			httpUrl = new URL(Constant.HBSERVERURL);
			uc = (HttpURLConnection) httpUrl.openConnection();
			uc.setDoOutput(true);
			uc.setDoInput(true);
			uc.setConnectTimeout(25000);
	             
			uc.setReadTimeout(25000);
			out = new PrintWriter(uc.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close();
			out=null;
			in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			
			in.close(); 
            in=null;
            if (uc != null) {
                // 关闭连接  
            	uc.disconnect();
                uc=null;
            } 
		} catch (Exception e1) {
			log.error("checkPerMonthStatus()->发送包月情况查询请求失败！" + e1.toString()); 
		}finally{
			if (out != null) {
                // 关闭连接  
				out.close();
                out=null;
            } 
			if (in != null) {
                // 关闭连接  
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                in=null;
            } 
			   if (uc != null) {
	                // 关闭连接  
	            	uc.disconnect();
	                uc=null;
	            } 
			
		}
		return result;*/
		return new HttpUtil(). perGet(   parameter,"UTF-8") ;
	
 
   }
	
 
	private String reSubmit(QQPerMonthReSubmit reSubmit,String hbFee ) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("objid", reSubmit.getProductId().substring(0, 5));
		param.put("username", "z" + reSubmit.getQqNumber());
		param.put("caller", reSubmit.getPhoneNumber());
		param.put("paymoney",hbFee);
		param.put("payid", "");
		String parameter = CommonUtil.mapToString(param);
		 
		/*	
		String result = "";
		java.net.URL httpUrl;
		HttpURLConnection uc=null;
		PrintWriter out=null; 
		BufferedReader in=null;
		try {
			httpUrl = new URL(Constant.HBSERVERURL);
			uc = (HttpURLConnection) httpUrl.openConnection();
			uc.setDoOutput(true);
			uc.setDoInput(true);
			out = new PrintWriter(uc.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close();
			 in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			
			in.close(); 
            in=null;
            if (uc != null) {
                // 关闭连接  
            	uc.disconnect();
                uc=null;
            } 
		} catch (Exception e1) {
			log.error("resubmit()->重新提交包月申请查询请求失败！" + e1.toString());
             
		}finally{
			if (out != null) {
                // 关闭连接  
				out.close();
                out=null;
            } 
			if (in != null) {
                // 关闭连接  
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                in=null;
            } 
			   if (uc != null) {
	                // 关闭连接  
	            	uc.disconnect();
	                uc=null;
	            } 
			
		}
		return result; */
		return new HttpUtil(). perGet(   parameter,"UTF-8") ;
		 
	} 
	
	
	


}
