package com.rising.mobilepayment.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rising.mobilepayment.bean.QQAgentOrder;
import com.rising.mobilepayment.bean.QQGameArea;
import com.rising.mobilepayment.bean.QQPool;
import com.rising.mobilepayment.bean.OrderInfo;
import com.rising.mobilepayment.bean.QQService;
import com.rising.mobilepayment.bean.QQRefundBatch;
import com.rising.mobilepayment.bean.SendMessageLog;
import com.rising.mobilepayment.bean.UserCheckIn;
import com.rising.mobilepayment.bean.UserInfo;
import com.rising.mobilepayment.bean.UserPublicInfoReadStatus;
import com.rising.mobilepayment.commom.Base64Utils;
import com.rising.mobilepayment.commom.CommonUtil;
import com.rising.mobilepayment.commom.Constant;
import com.rising.mobilepayment.job.QQAgentPayTask;
import com.rising.mobilepayment.mapper.AnswerFeedBackMapper;
import com.rising.mobilepayment.mapper.GetScoreRuleMapper;
import com.rising.mobilepayment.mapper.MessageMapper;
import com.rising.mobilepayment.mapper.QQAgentOrderMapper;
import com.rising.mobilepayment.mapper.OrderInfoMapper;
import com.rising.mobilepayment.mapper.PhoneNumberMapper;
import com.rising.mobilepayment.mapper.PublicInfoMapper;
import com.rising.mobilepayment.mapper.QQGameAreaMapper;
import com.rising.mobilepayment.mapper.QQPerMonthRecordMapper;
import com.rising.mobilepayment.mapper.QQRefundBatchMapper;
import com.rising.mobilepayment.mapper.SendMessageLogMapper;
import com.rising.mobilepayment.mapper.QQServiceMapper;
import com.rising.mobilepayment.mapper.UserInfoMapper;
import com.rising.mobilepayment.mapper.QQPoolMapper;
import com.rising.mobilepayment.service.LotteryService;
import com.rising.mobilepayment.service.OrderInfoService;
import com.rising.mobilepayment.service.QQAgentService;
import com.rising.mobilepayment.service.SMSService;

@Service("qqAgentService")
public class QQAgentServiceImpl implements QQAgentService{
	 @Autowired
	 private QQPoolMapper qqPoolMapper; 
	 
	 @Autowired
	 private QQServiceMapper qqServiceMapper; 
	 
	 
	 @Autowired
	 private QQGameAreaMapper qqGameAreaMapper;
	 
	 @Autowired
	 private QQAgentOrderMapper qqAgentOrderMapper;
	 
	 @Autowired
	 private QQRefundBatchMapper qqRefundBatchMapper;
	 
	 @Autowired
	 private QQAgentPayTask  qqAgentPayTask;
	 
	 @Autowired
	 private SMSService smsService;
	 
	 private Log log = LogFactory.getLog(UserInfoServiceImpl.class); 
	 @Override
	 public HashMap<String, Object> getQQServiceConfig(String gateCode,String phoneNumber, String busId,String targetNumber){
		 HashMap<String, Object> result = new HashMap<String, Object>();
		 phoneNumber=CommonUtil.trim(phoneNumber);
		 busId=CommonUtil.trim(busId);
		 targetNumber=CommonUtil.trim(targetNumber); 
		 List<QQService> qqServiceList=qqServiceMapper.getValidQQServiceList(busId);
		 result.put("respCode", 0);
		 result.put("respInfo", ""); 
		 result.put("qqServiceList", qqServiceList);
		 result.put("prompt", "1、支持全国QQ号码开通服务；\n2、QQ服务，一般1~10分钟内即可开通；\n3、售价实时变动，请以实际支付金额为准；");
		 result.put("servicePhone", "18072749082");
		 result.put("valid", true);
		 result.put("refuseTip", "");
		 /*if(gateCode.equalsIgnoreCase ("main")){
			 result.put("valid", false);
			 result.put("refuseTip", "抱歉，暂未开通，尽请期待！！");
		 }*/
		 HashMap<String, Object>  paraMap;
		 paraMap =new HashMap<String, Object> ();
	     int qbRestQuota =qqPoolMapper.getQBRestQuota(paraMap);
	     //if(qbRestQuota<140){
	    	 result.put("valid", false);
			 result.put("refuseTip", "抱歉，今日额度已经用完，请明天充值！");
	    // }
	     
	     
		 return  result; 
		 
	 }
	 
	 @Override 
	 public HashMap<String, Object> getQQGameConfig(String gateCode,String phoneNumber, String gameCode,String targetNumber){
		 HashMap<String, Object> result = new HashMap<String, Object>();
		 phoneNumber=CommonUtil.trim(phoneNumber);
		 gameCode=CommonUtil.trim(gameCode);
		 targetNumber=CommonUtil.trim(targetNumber); 
		 gameCode=gameCode.toUpperCase();
		 List<QQGameArea> qqGameAreaList=qqGameAreaMapper.getValidQQGameAreaList(gameCode);
		 result.put("respCode", 0);
		 result.put("respInfo", ""); 
		 result.put("openAid", ""); 
		 result.put("payTip", ""); 
		 result.put("unitMoney", ""); 
		 result.put("unitPrice", ""); 
		 QQService qqService=qqServiceMapper.findById(gameCode); 
		 if(qqService!=null){
			 result.put("openAid", CommonUtil.trim(qqService.getOpenAid())); 
			 result.put("payTip", CommonUtil.trim(qqService.getPayTip())); 
			 result.put("unitMoney", CommonUtil.trim(qqService.getUnitMoney())); 
			 result.put("unitPrice", CommonUtil.trim(qqService.getUnitPrice())); 
		 }
		 
		 
		 result.put("qqGameAreaList", qqGameAreaList);
		 result.put("prompt", "1、支持全国QQ号码充值游戏；\n2、游戏充值，一般1~10分钟内即可成功充值；\n3、售价实时变动，请以实际支付金额为准；");
		 result.put("servicePhone", "18072749082");
		 result.put("valid", true);
		 result.put("refuseTip", "");
		 /*if(gateCode.equalsIgnoreCase ("main")){
			 result.put("valid", false);
			 result.put("refuseTip", "抱歉，暂未开通，尽请期待！！");
		 }*/
		 
		 HashMap<String, Object>  paraMap;
		 paraMap =new HashMap<String, Object> ();
	     int qbRestQuota =qqPoolMapper.getQBRestQuota(paraMap);
	     //if(qbRestQuota<=140){
	    	 result.put("valid", false);
			 result.put("refuseTip", "抱歉，今日额度已经用完，请明天充值！");
	    // } 
		 return  result; 
		 
	 }
	 @Override
	 public HashMap<String, Object> qqPayNotice(HashMap<String, String> requestParams ){
		 HashMap<String, Object> result = new HashMap<String, Object>();
		 StringBuffer paramBuffer=new StringBuffer(""); 
		 for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String values = (String) requestParams.get(name); 
				paramBuffer.append(name).append("=").append(values).append("\n"); 
		 }
		 Calendar calendar = Calendar.getInstance(); 
		 Date curDate= calendar.getTime();  
		 String agentOrderId= CommonUtil.trim(requestParams.get("out_trade_no"));
		 String tradeNo=CommonUtil.trim(requestParams.get("trade_no"));
		 String body=CommonUtil.trim(requestParams.get("body"));
		 Gson gson = new Gson();  
		 
	     
		 JSONObject object = JSONObject.fromObject(body);   
		 String serviceCode=CommonUtil.trim(object.getString ("serviceCode"));	
		 String openMonth=CommonUtil.trim(object.getString("openMonth"));	
		 String targetQQNum=CommonUtil.trim(object.getString("qqNumber"));	
		 String payMoney=CommonUtil.trim(object.getString("payMoney"));	
		 String serviceName=CommonUtil.trim(object.getString("serviceName"));
		 String openAid=CommonUtil.trim(object.getString("openAid"));
		 String phoneNumber=CommonUtil.trim(object.getString("phoneNumber")); 
		 String agentAdd2=CommonUtil.trim(object.getString("agentAdd2")); 
		 
		 
		 String subject=CommonUtil.trim(requestParams.get("subject")); 
		 String tradeStatus=CommonUtil.trim(requestParams.get("trade_status"));
		 String buyerId=CommonUtil.trim(requestParams.get("buyer_id"));
		 String buyerEmail=CommonUtil.trim(requestParams.get("buyer_email"));
		 String totalFee=CommonUtil.trim(requestParams.get("total_fee"));
		 String refundStatus=CommonUtil.trim(requestParams.get("refund_status"));
		 String refundTime=CommonUtil.trim(requestParams.get("gmt_refund")); 
		 String payTime=CommonUtil.trim(requestParams.get("gmt_payment")); 
		 QQAgentOrder qqAgentOrder=qqAgentOrderMapper.findById(agentOrderId);
		 result.put("respCode", 0);
		 result.put("respInfo", "");  
		 if(qqAgentOrder!=null){
			 return result;
		 }
		 String busId="";
		 String unitPrice="";
		 
		 QQService qqService=qqServiceMapper.findById(serviceCode);
		 if(qqService!=null){
			 busId=CommonUtil.trim(qqService.getBusId());
			 serviceName=CommonUtil.trim(qqService.getServiceName());
			 openAid=CommonUtil.trim(qqService.getOpenAid());
			 unitPrice=CommonUtil.trim(qqService.getUnitPrice());
		 } 
		 qqAgentOrder=new QQAgentOrder();
		 qqAgentOrder.setAgentAdd1(openAid);
		 qqAgentOrder.setAgentAdd2(agentAdd2);
		 qqAgentOrder.setAgentAdd3("");
		 qqAgentOrder.setAgentOrderId(agentOrderId);
		 qqAgentOrder.setAgentQQNum("");
		 qqAgentOrder.setBusId(busId);
		 qqAgentOrder.setBuyerId(buyerId);
		 qqAgentOrder.setBuyerEmail(buyerEmail);
		 qqAgentOrder.setChargeStatus("-1");
		 qqAgentOrder.setOpenMonth(openMonth);
		 qqAgentOrder.setOrderTime(curDate);  
		 qqAgentOrder.setPayMoney((float)CommonUtil.castDouble(payMoney));
		 qqAgentOrder.setPhoneNumber(phoneNumber); 
		 int price=(int)Math.rint(CommonUtil.castDouble(unitPrice)*CommonUtil.castDouble(openMonth) );
		 qqAgentOrder.setPrice((float) (price));
		 qqAgentOrder.setRefundStatus(refundStatus);
		 if(!refundTime.equals("")){
			 qqAgentOrder.setRefundTime(CommonUtil.getDateByForm(refundTime,"yyyy-MM-dd HH:mm:ss"));
		 }
		 
		 qqAgentOrder.setRemark("");
		 qqAgentOrder.setServiceCode(serviceCode);
		 qqAgentOrder.setServiceName(serviceName);
		 qqAgentOrder.setSubject(subject);
		 qqAgentOrder.setTargetQQNum(targetQQNum);
		 qqAgentOrder.setTotalFee(totalFee);
		 qqAgentOrder.setTradeNo(tradeNo);
		 qqAgentOrder.setTradeStatus(tradeStatus); 
		 
		 if(!payTime.equals("")){
			 qqAgentOrder.setPayTime(CommonUtil.getDateByForm(payTime,"yyyy-MM-dd HH:mm:ss"));
			 
		 }
		 
		 
		 StringBuffer msgContent=new StringBuffer("");
		 msgContent.append("尊敬的客户：您好，");
		 msgContent.append("我们已经收到通过支付宝支付的").append(payMoney).append("元");
		 msgContent.append("正在为你充值中... 详询18072749082。");
		 //smsService.noticeSMS("qqservice", phoneNumber, msgContent.toString());
		 try{
			 qqAgentOrderMapper.add(qqAgentOrder);
		 }catch(Exception ex){
			 ex.printStackTrace();
			 result.put("respCode", 8);
			 result.put("respInfo", "数据库插入出错！！");
			 return result;
		 } 
		 qqAgentPayTask.qqAgentSinglePay(qqAgentOrder);
		 return result; 
		 
		  
		   
	 }
	@Override
	public HashMap<String, Object> qqRefundNotice(HashMap<String, String> requestParams) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		 StringBuffer paramBuffer=new StringBuffer(""); 
		 for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String values = (String) requestParams.get(name);  
		 }
		  
		 String batchNo= CommonUtil.trim(requestParams.get("batch_no"));
		 String resultDetails= CommonUtil.trim(requestParams.get("result_details"));
		 String refundStatus="SUCCESS";
		 String successNum= CommonUtil.trim(requestParams.get("success_num"));
		 QQRefundBatch qqRefundBatch=null;
		 qqRefundBatch=qqRefundBatchMapper.findById(batchNo);
		 if(qqRefundBatch==null){
			 qqRefundBatch=qqRefundBatchMapper.findByTradeNo(batchNo);
		 }
		 if(qqRefundBatch!=null  ){
			 qqRefundBatch.setStatus(refundStatus);
		 }
		 qqRefundBatchMapper.update(qqRefundBatch);
		 
		 
		 List <QQAgentOrder>qqAgentOrderList =qqAgentOrderMapper.getListByBatchNo(  batchNo);
		 Date curDate=new Date();
		 for(QQAgentOrder qqAgentOrder :qqAgentOrderList){
			 qqAgentOrder.setRefundStatus(refundStatus); 
			 qqAgentOrder.setRefundTime(curDate);
			 qqAgentOrderMapper.update(qqAgentOrder);
		 }
		 
		 
		 
		 
		 result.put("respCode", 0);
		 result.put("respInfo", refundStatus);    
		 
		 
		 
		 //短信提醒
		 return result;
	}
	@Override
	public HashMap<String, Object> qqUnsubmitRefund(HashMap<String, String> requestParams) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance(); 
		
		
		HashMap<String, Object>  paraMap;
		paraMap =new HashMap<String, Object> ();
		paraMap.put("pageSize",20); 
		calendar = Calendar.getInstance(); 
		calendar.add(Calendar.DAY_OF_YEAR , -15 );
		paraMap.put("fromOrderTime", CommonUtil.doDateForm(calendar.getTime(), "yyyy-MM-dd HH:mm"));
		calendar = Calendar.getInstance(); 
		calendar.add(Calendar.HOUR_OF_DAY , -12 );
		paraMap.put("endOrderTime", CommonUtil.doDateForm(calendar.getTime(), "yyyy-MM-dd HH:mm"));
		
		List <QQAgentOrder>qqAgentOrderList =qqAgentOrderMapper.getPayFailList(paraMap);
		
		result.put("respCode", 0);
		result.put("respInfo", "");   
		addRefundBatch(  qqAgentOrderList);
		
		
		paraMap =new HashMap<String, Object> ();
		paraMap.put("pageSize",20); 
		
		calendar = Calendar.getInstance(); 
		calendar.add(Calendar.DAY_OF_YEAR , -30 );
		paraMap.put("fromCreateDate", CommonUtil.doDateForm(calendar.getTime(), "yyyy-MM-dd"));
		List<QQRefundBatch> qqRefundBatchList=qqRefundBatchMapper.getUnSubmitList(paraMap);
		List<String> qqRefundBatchHttpUrlList=new ArrayList<String>();
		
		StringBuffer urlBuffer;
		
		for(QQRefundBatch qqRefundBatch:qqRefundBatchList){
			
			urlBuffer=new StringBuffer("");
			urlBuffer.append(Constant.APPSERVERURL); 
			urlBuffer.append("/AppServer");
			urlBuffer.append("/qqReback");
			urlBuffer.append("/qqRefund");
			urlBuffer.append("?refund_date=").append(
					CommonUtil.doDateForm(qqRefundBatch.getRefundDate(),"yyyy-MM-dd HH:mm:ss"));
			urlBuffer.append("&batch_no=").append(CommonUtil.trim(qqRefundBatch.getBatchNo()));
			urlBuffer.append("&batch_num=").append(CommonUtil.trim(qqRefundBatch.getBatchNum()));
			urlBuffer.append("&detail_data=").append(CommonUtil.trim(qqRefundBatch.getDetailData()));
			
			qqRefundBatchHttpUrlList.add(urlBuffer.toString()); 
		}
		
		result.put("qqRefundBatchHttpUrlList", qqRefundBatchHttpUrlList);    
		
		return result;
	}
	
	
	private boolean addRefundBatch(List <QQAgentOrder> qqAgentOrderList){
		if(qqAgentOrderList==null||qqAgentOrderList.size()==0){
			 return false;
		 } 
		String batchNo=createBatchNo();
		QQRefundBatch qqRefundBatch=null;
		qqRefundBatch=qqRefundBatchMapper.findById(batchNo);
		while(qqRefundBatch!=null){
			batchNo=createBatchNo(); 
			qqRefundBatch=qqRefundBatchMapper.findById(batchNo);
		}
		String batchNum=new Integer(qqAgentOrderList.size()).toString();
		StringBuffer detailBuffer=new StringBuffer("");
		for(QQAgentOrder qqAgentOrder:qqAgentOrderList){
			detailBuffer.append( CommonUtil.trim(qqAgentOrder.getTradeNo()) ).append("^");
			detailBuffer.append( CommonUtil.trim(qqAgentOrder.getTotalFee()) ).append("^");
			detailBuffer.append( "充值失败" );
			detailBuffer.append("#");
			
			qqAgentOrder.setRefundStatus("SUBMIT");
			qqAgentOrder.setBatchNo(batchNo);
			qqAgentOrderMapper.update(qqAgentOrder);
		}
		if(detailBuffer.lastIndexOf("#")>0)
			detailBuffer.deleteCharAt(detailBuffer.lastIndexOf("#"));
		
		
		
		
		String detailData=detailBuffer.toString();
		
		
		Date curDate=new Date(); 
		String refundDate=CommonUtil.doDateForm(curDate, "yyyy-MM-dd HH:mm:ss"); 
		qqRefundBatch=new QQRefundBatch();
		qqRefundBatch.setBatchNo(batchNo);
		qqRefundBatch.setBatchNum(batchNum);
		qqRefundBatch.setCreateDate(curDate);
		qqRefundBatch.setDetailData(detailData); 
		qqRefundBatch.setRefundDate(curDate);
		qqRefundBatch.setStatus("");
		
		qqRefundBatchMapper.add(qqRefundBatch);
		
		return true;
		
	}
	
	private String createBatchNo(){
		String batchNo=CommonUtil.doDateForm(new Date(), "yyyyMMddHHmm"); 
		Random random= new Random();
		int radomNum=random.nextInt(100);
		while(radomNum==0){
			radomNum=random.nextInt(100);
			break;
		}
		batchNo+=CommonUtil.castString(radomNum, "00"); 
		return batchNo;
	}
	 
	 
	 
	 
		 /*
		 paraMap.put("price",price);
		 paraMap.put("pageSize",4);
		 
		 List<QQPool> qqPoolList=qqPoolMapper.getPayQQPoolList(paraMap);
		 if(qqPoolList==null &&qqPoolList.size()==0){
			 qqAgentOrder.setChargeStatus("-2");
			 try{
				 qqAgentOrderMapper.add(qqAgentOrder);
			 }catch(Exception ex){
				 ex.printStackTrace();
				 result.put("respCode", 8);
				 result.put("respInfo", "数据库插入出错！！");
			 } 
			 return result; 
		 }
		 
		 for(QQPool qqPool:qqPoolList){
			 qqAgentOrder.setAgentQQNum(qqPool.getQqNumber());
			 StringBuffer urlBuffer;
			 urlBuffer=new StringBuffer("");
			 urlBuffer.append(Constant.APPSERVERURL); 
			 urlBuffer.append("/AppServer");
			 urlBuffer.append("/qqPay");
			 urlBuffer.append("/payQQService");
			 urlBuffer.append("?qqNumber=").append(CommonUtil.trim(qqPool.getQqNumber()));
			 urlBuffer.append("&qqPassword=").append(CommonUtil.trim(qqPool.getQqPassword()));
			 urlBuffer.append("&targetQQNum=").append(targetQQNum);
			 urlBuffer.append("&serviceCode=").append(serviceCode);
			 urlBuffer.append("&openAid=").append(openAid);
			 urlBuffer.append("&openMonth=").append(openMonth);
			 
	         java.net.URL httpUrl;
		     String httpResult; 
			 try { 
					httpUrl = new URL(urlBuffer.toString() ); 
					httpResult = "";
					URLConnection uc = (URLConnection) httpUrl.openConnection();
					uc.setReadTimeout(30000);
					uc.setDoOutput(true);
					uc.setDoInput(true); 
					PrintWriter out = new PrintWriter(uc.getOutputStream());
					//out.write(parameter);
					out.flush();
					out.close();
					BufferedReader in = new BufferedReader(new InputStreamReader(
							uc.getInputStream(),"utf-8"));
					String line;
					while ((line = in.readLine()) != null) {
						httpResult += line;
					}
					in.close(); 
					object = JSONObject.fromObject(httpResult);
					int resCode = object.getInt ("resCode");
					String resInfo=CommonUtil.trim(object.getString ("resInfo")); 
					qqPool.setCreateTime(qqPool.getCreateTime()==null?curDate:qqPool.getCreateTime());	
					qqPool.setLastCheckTime(qqPool.getLastCheckTime()==null?curDate:qqPool.getLastCheckTime());
					qqPool.setLastChargeTime(curDate);
					qqPool.setQqStatus(new Integer(resCode).toString());   
					qqAgentOrder.setChargeStatus(new Integer(resCode).toString()); 
					if(resInfo.length()>80){
						resInfo=resInfo.substring (0,77);
					}
					
					if(resCode!=0){
						qqPool.setRemark(resInfo);
						//qqPool.setQbBalance(0);
						qqAgentOrder.setRemark(resInfo); 
					}else{
						qqPool.setQbBalance(object.getInt("qbBalance")-price); 
						qqPool.setRemark(""); 
					}
					qqPoolMapper.update(qqPool); 
					if(resCode==0){
						break;
					}
					 
				} catch (Exception e1) { 
					log.error("qqPayNotice->充值Q币余额请求失败！" + e1.toString());
					qqAgentOrder.setChargeStatus("-3");  
				}
		 }  
		 
		 //退款
		 try{
			 qqAgentOrderMapper.add(qqAgentOrder);
		 }catch(Exception ex){
			 ex.printStackTrace();
			 result.put("respCode", 8);
			 result.put("respInfo", "数据库插入出错！！");
		 }*/
		 
		 
	 
	 
	
  
}