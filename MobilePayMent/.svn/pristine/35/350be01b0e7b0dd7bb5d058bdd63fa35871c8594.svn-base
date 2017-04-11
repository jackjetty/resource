package com.rising.mobilepayment.service.impl;
 
 
import java.util.*;
import java.text.*; 
import java.util.regex.Matcher;
import java.util.regex.Pattern; 

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service; 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rising.mobilepayment.commom.HttpUtil; 
import com.rising.mobilepayment.commom.MD5;
import com.rising.mobilepayment.bean.FlowLog; 
import com.rising.mobilepayment.bean.Product;
import com.rising.mobilepayment.bean.OperateLog;
import com.rising.mobilepayment.bean.OrderInfo;
import com.rising.mobilepayment.bean.UserInfo;
import com.rising.mobilepayment.mapper.FlowLogMapper; 
import com.rising.mobilepayment.mapper.OperateLogMapper;
import com.rising.mobilepayment.mapper.OrderInfoMapper; 
import com.rising.mobilepayment.mapper.UserInfoMapper;
import com.rising.mobilepayment.mapper.OrderIdHelperMapper;
import com.rising.mobilepayment.mapper.ProductMapper; 
import com.rising.mobilepayment.service.FlowService;  
@Service("flowService")
public class FlowServiceImpl implements FlowService {
	
	Log log = LogFactory.getLog(FlowServiceImpl.class);
	@Autowired
	OperateLogMapper operateLogMapper; 
	
	@Autowired
	OrderInfoMapper orderInfoMapper;
	
	@Autowired
	UserInfoMapper userInfoMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	OrderIdHelperMapper orderIdHelperMapper;
	
	@Autowired
	FlowLogMapper flowLogMapper; 
	private String partnerCode;
	private String flowKey;
	private String flowIp;
	private String flowQueryPrefix;
	private String flowRecommendPrefix;
	private String flowApplyPrefix;
	private String flowHandlePrefix;
	private String flowOrderQueryPrefix;
	private String flowForwardUrl;  
    
	@Value("#{propertiesReader[flowKey]}")
	public void setFlowKey(String flowKey) {
		this.flowKey = flowKey;
	}
	@Value("#{propertiesReader[partnerCode]}")
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	@Value("#{propertiesReader[flowIp]}")
	public void setFlowIp(String flowIp) {
		this.flowIp = flowIp;
	}
	@Value("#{propertiesReader[flowQueryPrefix]}")
	public void setFlowQueryPrefix(String flowQueryPrefix) {
		this.flowQueryPrefix = flowQueryPrefix;
	}
	@Value("#{propertiesReader[flowRecommendPrefix]}")
	public void setFlowRecommendPrefix(String flowRecommendPrefix) {
		this.flowRecommendPrefix = flowRecommendPrefix;
	}
	@Value("#{propertiesReader[flowApplyPrefix]}")
	public void setFlowApplyPrefix(String flowApplyPrefix) {
		this.flowApplyPrefix = flowApplyPrefix;
	}
	@Value("#{propertiesReader[flowHandlePrefix]}")
	public void setFlowHandlePrefix(String flowHandlePrefix) {
		this.flowHandlePrefix = flowHandlePrefix;
	}
	@Value("#{propertiesReader[flowOrderQueryPrefix]}")
	public void setFlowOrderQueryPrefix(String flowOrderQueryPrefix) {
		this.flowOrderQueryPrefix = flowOrderQueryPrefix;
	}
    
	@Value("#{propertiesReader[flowForwardUrl]}") 
	public void setFlowForwardUrl(String flowForwardUrl) {
		this.flowForwardUrl = flowForwardUrl;
	}
	//流量查询 
	@Override 
	public String query(HashMap<String, String> map) {
		FlowLog flowLog;
		String url="http://";
		String phoneNumber=map.get("phoneNumber");
		String os=map.get("os");
		String clientIp=map.get("clientIp");
		Date date=new Date();
		String time=this.doDateForm(date); 
		String sign =MD5.encryptByMD5WithFlow(this.partnerCode+phoneNumber+os+clientIp+time+this.flowKey);
		sign = MD5.encryptByMD5WithFlow(sign);	 
		url+=this.flowIp;
		url+=this.flowQueryPrefix;
		url+="?";
		url+="partner_code="+this.partnerCode;
		url+="&account="+phoneNumber;
		url+="&os="+os;
		url+="&client_ip="+clientIp;
		url+="&time="+time;
		url+="&sign="+sign;
		flowLog=new FlowLog();
		flowLog.setCode("0");
		flowLog.setContext(url);
		flowLog.setOrder("query");
		flowLog.setPhoneNumber(phoneNumber); 
		flowLog.setType("request");
		this.addFlowLog(flowLog);
		String xmlStr=HttpUtil.submitForwardData(this.flowForwardUrl, url);
		Document doc = null;
		List list;
		Iterator iterator;
		String code=null;
		flowLog=new FlowLog();
		flowLog.setContext(xmlStr); 
		flowLog.setOrder("query");
		flowLog.setPhoneNumber(phoneNumber);
		flowLog.setType("response");
		HashMap<String,Object> result = new HashMap<String,Object>();
		ArrayList<QueryItem> itemList=new  ArrayList<QueryItem>();
		String xpath;
		Element element; 
		QueryItem item;
		try{
			doc = DocumentHelper.parseText(xmlStr);
			xpath="/root/header/param[@name='result_code']/@value"; 
			code=this.getAttribute(doc,xpath);  
			if(!code.equalsIgnoreCase("success")){
				xpath="/root/header/param[@name='result_msg']/@value";  
				//不成功的
				result.put("respCode",-10);
				result.put("respInfo",this.getAttribute(doc,xpath));
				flowLog.setCode(code);
				this.addFlowLog(flowLog);
				return new Gson().toJson(result);
			}
			xpath="/root/body/item";
			list = doc.selectNodes(xpath); 
			for(iterator=list.iterator(); iterator.hasNext();){
				element= (Element)iterator.next(); 
				item=new QueryItem();
				item.setName(element.attribute("name").getValue());
				item.setUsed(element.attribute("used").getValue());
				itemList.add(item);
			} 
			result.put("respCode",0);
			result.put("respInfo", "");
			result.put("item",itemList); 
		}
		catch(FlowException flowException){
			//表示xml格式不 对
			result.put("respCode",-11);
			result.put("respInfo","返回的XML格式不符合标准！");
		}
	    catch(Exception ex){
	    	//表示出错 
	    	log.error("query()->插入返回信息时出错"+ex.toString());
	    	//log.error(ex.getMessage(), ex);  
	    	result.put("respCode",-12);
			result.put("respInfo","解析返回xml出错");
		} 
		 
		flowLog.setCode(code);
		this.addFlowLog(flowLog);
		return new Gson().toJson(result);		 
		
	}
	//流量包推荐 
	@Override
	
	public String recommend(HashMap<String, String> map) {
		FlowLog flowLog;
		String url="http://";
		String phoneNumber=map.get("phoneNumber");
		String os=map.get("os");
		String clientIp=map.get("clientIp");
		Date date=new Date();
		String time=this.doDateForm(date); 
		String sign =MD5.encryptByMD5WithFlow(this.partnerCode+phoneNumber+os+clientIp+time+this.flowKey);
		sign = MD5.encryptByMD5WithFlow(sign);	 
		url+=this.flowIp;
		url+=this.flowRecommendPrefix;
		url+="?";
		url+="partner_code="+this.partnerCode;
		url+="&account="+phoneNumber;
		url+="&os="+os;
		url+="&client_ip="+clientIp;
		url+="&time="+time;
		url+="&sign="+sign;
		flowLog=new FlowLog();
		flowLog.setCode("0");
		flowLog.setContext(url);
		flowLog.setOrder("recommend");
		flowLog.setPhoneNumber(phoneNumber);
		flowLog.setType("request");
		this.addFlowLog(flowLog);
		String xmlStr=HttpUtil.submitForwardData(this.flowForwardUrl, url);
		 
		Document doc = null;
		List list;
		Iterator iterator;
		String code=null;
		flowLog=new FlowLog();
		flowLog.setContext(xmlStr);
		flowLog.setOrder("recommend");
		flowLog.setPhoneNumber(phoneNumber);
		flowLog.setType("response");
		HashMap<String,Object> result = new HashMap<String,Object>();
		ArrayList<RecommendItem> itemList=new  ArrayList<RecommendItem>();
		String xpath;
		Element element; 
		RecommendItem item; 
		//log.info(xmlStr); 
		try{
			doc = DocumentHelper.parseText(xmlStr);
			xpath="/root/header/param[@name='result_code']/@value"; 
			code=this.getAttribute(doc,xpath);  
			if(!code.equalsIgnoreCase("success")){
				xpath="/root/header/param[@name='result_msg']/@value";  
				//不成功的
				result.put("respCode",-10);
				result.put("respInfo",this.getAttribute(doc,xpath));
				flowLog.setCode(code);
				this.addFlowLog(flowLog);
				return new Gson().toJson(result);
			}
			xpath="/root/body/item";
			list = doc.selectNodes(xpath); 
			for(iterator=list.iterator(); iterator.hasNext();){
				element= (Element)iterator.next(); 
				item=new RecommendItem();
				item.setName(element.attribute("name").getValue());
				item.setPrice(element.attribute("price").getValue());
				item.setCode(element.attribute("code").getValue());
				item.setSize(getFlowSize(item.getName()));
				item.setSprice(getFlowSprice(item));
				/* 
				if(flowIncludeMonth.equalsIgnoreCase("false")&&item.getName().indexOf("(包月)")>0)
					 continue;*/ 
				itemList.add(item);
			} 
			result.put("respCode",0);
			result.put("respInfo", "");
			result.put("item",itemList); 
		}
		catch(FlowException flowException){
			//表示xml格式不 对
			result.put("respCode",-11);
			result.put("respInfo","返回的XML格式不符合标准！");
		}
	    catch(Exception ex){
	    	//表示出错 
	    	log.error("recommend()->插入返回信息时出错"+ex.toString());
	    	//log.error(ex.getMessage(), ex);  
	    	result.put("respCode",-12);
			result.put("respInfo","解析返回xml出错");
		} 
		flowLog.setCode(code);
		this.addFlowLog(flowLog);
		return new Gson().toJson(result);		 
		
	}
	//流量包申请 
		@Override 
		public String apply(HashMap<String, String> map) {
			/*FlowLog flowLog;
			String url="http://";
			String phoneNumber=map.get("phoneNumber");
			String os=map.get("os");
			String clientIp=map.get("clientIp");
			Date date=new Date();
			String time=this.doDateForm(date); 
			String tcCode=map.get("tcCode");
			String sign =MD5.encryptByMD5WithFlow(this.partnerCode+phoneNumber+os+clientIp+time+this.flowKey);
			sign = MD5.encryptByMD5WithFlow(sign);	 
			url+=this.flowIp;
			url+=this.flowApplyPrefix;
			url+="?";
			url+="partner_code="+this.partnerCode;
			url+="&account="+phoneNumber;
			url+="&os="+os;
			url+="&client_ip="+clientIp;
			url+="&time="+time;
			url+="&tc_code="+tcCode;
			url+="&sign="+sign;
			flowLog=new FlowLog();
			flowLog.setCode("0");
			flowLog.setContext(url);
			flowLog.setOrder("apply");
			flowLog.setPhoneNumber(phoneNumber);
			flowLog.setType("request");
			this.addFlowLog(flowLog);
			String xmlStr=HttpUtil.submitForwardData(this.flowForwardUrl, url);
			Document doc = null;
			List list;
			Iterator iterator;
			String code=null;
			flowLog=new FlowLog();
			flowLog.setContext(xmlStr);
			flowLog.setOrder("apply");
			flowLog.setPhoneNumber(phoneNumber);
			flowLog.setType("response");
			HashMap<String,Object> result = new HashMap<String,Object>(); 
			String xpath;   
			try{
				doc = DocumentHelper.parseText(xmlStr);
				xpath="/root/header/param[@name='result_code']/@value"; 
				code=this.getAttribute(doc,xpath);  
				if(!code.equalsIgnoreCase("success")){
					xpath="/root/header/param[@name='result_msg']/@value";  
					//不成功的
					result.put("respCode",-10);
					result.put("respInfo",this.getAttribute(doc,xpath));
					flowLog.setCode(code);
					this.addFlowLog(flowLog);
					return new Gson().toJson(result);
				}
				xpath="/root/body/param[@name='order_no']/@value"; 
				result.put("respCode",0);
				result.put("respInfo", "");
				result.put("orderNo",this.getAttribute(doc,xpath)); 
			}
			catch(FlowException flowException){
				//表示xml格式不 对
				result.put("respCode",-11);
				result.put("respInfo","返回的XML格式不符合标准！");
			}
		    catch(Exception ex){
		    	//表示出错 
		    	log.error("apply()->插入返回信息时出错"+ex.toString());
		    	//log.error(ex.getMessage(), ex);  
		    	result.put("respCode",-12);
				result.put("respInfo","解析返回xml出错");
			} 
			flowLog.setCode(code);
			this.addFlowLog(flowLog);
			return new Gson().toJson(result);	*/
			HashMap<String,Object> result = new HashMap<String,Object>(); 
			result.put("respCode",-12);
			result.put("respInfo","该产品已下架");
			
			return new Gson().toJson(result);	
			
		}
		
		//流量包办理 
		@Override
		@SuppressWarnings("unchecked")
		public String handle(HashMap<String, String> map) {
			/*FlowLog flowLog;
			String url="http://";
			String phoneNumber=map.get("phoneNumber");
			String os=map.get("os");
			String clientIp=map.get("clientIp");
			Date date=new Date();
			String time=this.doDateForm(date); 
			String orderNo=map.get("orderNo");
			String verifyCode=map.get("verifyCode");
			String sign =MD5.encryptByMD5WithFlow(this.partnerCode+phoneNumber+os+clientIp+time+this.flowKey);
			sign = MD5.encryptByMD5WithFlow(sign);	 
			url+=this.flowIp;
			url+=this.flowHandlePrefix;
			url+="?";
			url+="partner_code="+this.partnerCode;
			url+="&account="+phoneNumber;
			url+="&os="+os;
			url+="&client_ip="+clientIp;
			url+="&time="+time;
			url+="&order_no="+orderNo;
			url+="&verify_code="+verifyCode;
			url+="&sign="+sign;
			flowLog=new FlowLog();
			flowLog.setCode("0");
			flowLog.setContext(url);
			flowLog.setOrder("handle");
			flowLog.setPhoneNumber(phoneNumber);
			flowLog.setType("request");
			this.addFlowLog(flowLog);
			String xmlStr=HttpUtil.submitForwardData(this.flowForwardUrl, url);
			Document doc = null;
			List list;
			Iterator iterator;
			String code=null;
			flowLog=new FlowLog();
			flowLog.setContext(xmlStr);
			flowLog.setOrder("handle");
			flowLog.setPhoneNumber(phoneNumber);
			flowLog.setType("response");
			HashMap<String,Object> result = new HashMap<String,Object>(); 
			String xpath; 
			
			try{
				doc = DocumentHelper.parseText(xmlStr);
				xpath="/root/header/param[@name='result_code']/@value"; 
				code=this.getAttribute(doc,xpath);  
				if(!code.equalsIgnoreCase("success")){
					xpath="/root/header/param[@name='result_msg']/@value";  
					//不成功的
					result.put("respCode",-10);
					result.put("respInfo",this.getAttribute(doc,xpath));
					flowLog.setCode(code);
					this.addFlowLog(flowLog);
					return new Gson().toJson(result);
				}  
				result.put("respCode",0);
				result.put("respInfo", "提交成功！！"); 
			}
			catch(FlowException flowException){
				//表示xml格式不 对
				result.put("respCode",-11);
				result.put("respInfo","返回的XML格式不符合标准！");
			}
		    catch(Exception ex){
		    	//表示出错 
		    	log.error("handle()->插入返回信息时出错"+ex.toString());
		    	//log.error(ex.getMessage(), ex);  
		    	result.put("respCode",-12);
				result.put("respInfo","解析返回xml出错");
			}   
			flowLog.setCode(code);
			this.addFlowLog(flowLog);
			//添加系统日志
			String userAgent = map.get("user-agent");
			String [] info = userAgent.split("_");
			OperateLog operate = new OperateLog();
			operate.setOperateTime(new Date());
			operate.setOperateType("TakeFlowOrder");
			operate.setPhoneNumber(phoneNumber);
			operate.setClient(info[2]);
			operate.setOs(info[0]);
			operate.setVersion(info[1]);
			operateLogMapper.addOperateLog(operate);
			String packageName=map.get("packageName");
			String packagePrice=map.get("packagePrice");
			String packageSize=map.get("packageSize");
			String packageCode=map.get("packageCode");  
			Product product=productMapper.findProductById(packageCode);
			if(product==null){
				product=new Product(); 
				product.setBusId(105);
				product.setCost(new Integer(packagePrice).intValue()); 
				product.setMerchantId("31000102");
				product.setProductDescribe(packageName);
				product.setProductId(packageCode);
				product.setProductName(packageName);
				product.setSPID("");
				product.setStatus("有效");
				product.setSendScore(product.getCost());
				productMapper.add(product);
				
			}
			OrderInfo order = new OrderInfo();
			order.setOrderId(generateOrderId());
			order.setProductId(packageCode);
			order.setBusId(product.getBusId());
			order.setTargetNumber(phoneNumber);
			order.setPayMoney(Long.parseLong(packagePrice));
			order.setPhoneNumber(phoneNumber);
			order.setOrderTime(new Date());
			order.setPayMethodId(1);
			order.setOrderStatus("成功");
			order.setPayStatus("未支付");
			order.setSPID("004");
			order.setSendScore(0);
			order.setHCOrderId(orderNo); 
			
			order.setOs(info[0]);
			order.setVersion(info[1]);
			order.setClient(info[2]);
			orderInfoMapper.add(order);
			return new Gson().toJson(result);*/	
			HashMap<String,Object> result = new HashMap<String,Object>(); 
			result.put("respCode",-12);
			result.put("respInfo","该产品已下架");
			
			return new Gson().toJson(result);	
			
		}
	//流量包查询 
	@Override
	public String orderQuery(HashMap<String, String> map) {
		FlowLog flowLog;
		String url="http://";
		String phoneNumber=map.get("phoneNumber"); 
		String orderNo=map.get("orderNo"); 
		Date date=new Date();
		String time=this.doDateForm(date);  
		 
		String sign =MD5.encryptByMD5WithFlow(this.partnerCode +phoneNumber+orderNo+time+this.flowKey);
		sign = MD5.encryptByMD5WithFlow(sign);	 
		url+=this.flowIp;
		url+=this.flowOrderQueryPrefix;
		url+="?";
		url+="partner_code="+this.partnerCode;
		url+="&account="+phoneNumber;  
		url+="&order_no="+orderNo;
		url+="&time="+time; 
		url+="&sign="+sign;
		flowLog=new FlowLog();
		flowLog.setCode("0");
		flowLog.setContext(url);
		flowLog.setOrder("orderQuery");
		flowLog.setPhoneNumber(phoneNumber);
		flowLog.setType("request");
		this.addFlowLog(flowLog);
		String xmlStr=HttpUtil.submitForwardData(this.flowForwardUrl, url);
		Document doc = null;
		List list;
		Iterator iterator;
		String code=null;
		flowLog=new FlowLog();
		flowLog.setContext(xmlStr);
		flowLog.setOrder("orderQuery");
		flowLog.setPhoneNumber(phoneNumber);
		flowLog.setType("response");
		HashMap<String,Object> result = new HashMap<String,Object>(); 
		String xpath;  
		
		try{
			doc = DocumentHelper.parseText(xmlStr);
			xpath="/root/header/param[@name='result_code']/@value"; 
			code=this.getAttribute(doc,xpath);  
			if(!code.equalsIgnoreCase("success")){
				xpath="/root/header/param[@name='result_msg']/@value";  
				//不成功的
				result.put("respCode",-10);
				result.put("respInfo",this.getAttribute(doc,xpath));
				flowLog.setCode(code);
				this.addFlowLog(flowLog);
				return new Gson().toJson(result);
			}  
			xpath="/root/body/param[@name='status']/@value"; 
			result.put("respCode",0);
			result.put("respInfo", "");
			result.put("status",this.getAttribute(doc,xpath));  
		}
		catch(FlowException flowException){
			//表示xml格式不 对
			result.put("respCode",-11);
			result.put("respInfo","返回的XML格式不符合标准！");
		}
	    catch(Exception ex){
	    	//表示出错 
	    	log.error("orderQuery()->插入返回信息时出错"+ex.toString());
	    	//log.error(ex.getMessage(), ex);  
	    	result.put("respCode",-12);
			result.put("respInfo","解析返回xml出错");
		} 
		flowLog.setCode(code);
		this.addFlowLog(flowLog);
		return new Gson().toJson(result);		 
		
	}  
	public void flowOrderInfoQuery(){ 
		OrderInfo order=new OrderInfo();
		order.setBusId(105);
		order.setPayStatus("未支付");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-20);
		order.setOrderTime(cal.getTime());
		cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE,-5);
		order.setPayTime(cal.getTime()); 
		
		ArrayList<OrderInfo> orderInfoList=orderInfoMapper.findFlowPerWeek(order);
		HashMap<String, String> map;
		Map<String,Object> resultMap;
		String json;
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		int respCode;
		String status;
		UserInfo userInfo;
		for(OrderInfo orderInfo:orderInfoList){
			map=new HashMap<String, String>();
			map.put("phoneNumber", orderInfo.getPhoneNumber());
			map.put("orderNo", orderInfo.getHCOrderId());
			json=orderQuery(map); 
			 
			resultMap=gson.fromJson(json, new TypeToken<Map<String,Object>>() {}.getType());
			//respCode=Integer.parseInt(resultMap.get("respCode").toString());
		 
			respCode=(int) Double.parseDouble(resultMap.get("respCode").toString()) ; 
			 
			orderInfo.setPayTime(new Date());
			orderInfo.setPayReturnCode(String.valueOf(respCode));
			Product product=productMapper.findProductById(orderInfo.getProductId());
			float sendCode= orderInfo.getPayMoney() ; 
			if(product!=null){
				sendCode=product.getSendScore();
			} 
			if(respCode==0 && resultMap.get("status").toString().equalsIgnoreCase("成功")){ 
				orderInfo.setPayStatus(resultMap.get("status").toString());
				orderInfo.setSendScore(sendCode); 
				userInfo=new UserInfo();
				userInfo.setPhoneNumber(orderInfo.getPhoneNumber());
				userInfo.setAllScore(sendCode);
				userInfoMapper.addScore(userInfo); 
				orderInfo.setPayReturnCode("0"); 
				orderInfoMapper.update(orderInfo);
				return;
			}
			if(respCode==0 && resultMap.get("status").toString().equalsIgnoreCase("失败")){ 
				orderInfo.setPayStatus(resultMap.get("status").toString()); 
				orderInfo.setSendScore(0); 
				orderInfo.setPayReturnCode("1");
				orderInfoMapper.update(orderInfo);
				return;
			}
			
		}
		
	}
    /*
	@Override
	public String record(HashMap<String, String> map) {
		FeedBackInfo info = new FeedBackInfo();
		info.setEmail(map.get("Email"));
		info.setFBTime(new Date());
		info.setPhoneNumber(map.get("PhoneNumber"));
		info.setContactNumber(map.get("ContactNumber"));
		try {
			info.setFBContent(URLDecoder.decode(map.get("FBContent"),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HashMap<String,Object> result = new HashMap<String,Object>();
		try {
			feedBackInfoMapper.add(info);
			result.put("respCode",0);
			result.put("respInfo", "");
		} catch (Exception e) {
			log.error("record()->插入返回信息时出错"+e.toString());
			result.put("respCode",-12);
			result.put("respInfo","反馈失败！");
		}
		return new Gson().toJson(result);
	}*/
	private String getFlowSize(String flowName){
		 Pattern pattern;
		 Matcher matcher;
		 pattern=Pattern.compile("^.*\\D(\\d+M).*$");
		 matcher=pattern.matcher(flowName);
		 if(matcher.find()){
				return matcher.group(1);
				 
		 }
		 return "包月";
	}
	private String getFlowSprice(RecommendItem item){
		 
		String size="";
		Pattern pattern;
		Matcher matcher;
		pattern=Pattern.compile("(\\d+)M$");
		matcher=pattern.matcher(item.getSize());
		if(matcher.find()){
			size= matcher.group(1); 
	    }
		if(size.equalsIgnoreCase(""))
			 return size;
		
		double dval = (new Double(size)).doubleValue()*36.86/120;
		DecimalFormat aDecimalFormat = (DecimalFormat) DecimalFormat.getInstance();
        aDecimalFormat.applyPattern("0.00");
        return aDecimalFormat.format(dval);
	}
	
	
	private String doDateForm(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date); 
    }
	private void addFlowLog(FlowLog flowLog){
		if(flowLog==null)
			 return;
		flowLog.setCode(flowLog.getCode()==null?"":flowLog.getCode());
		flowLog.setCode(flowLog.getCode().equalsIgnoreCase("success")?"0":flowLog.getCode());
		flowLog.setContext(flowLog.getContext()==null?"":flowLog.getContext());
		flowLog.setOrder(flowLog.getOrder()==null?"":flowLog.getOrder());
		flowLog.setPhoneNumber(flowLog.getPhoneNumber()==null?"":flowLog.getPhoneNumber());
		flowLog.setType(flowLog.getType()==null?"":flowLog.getType()); 
		flowLog.setDateTime(new Date());
		flowLogMapper.add(flowLog); 
	}
	private String  getAttribute(Document doc,String xpath) throws FlowException{
		//"/root/header/param[@name='result_code']/@value"
		List list = doc.selectNodes(xpath);
		if(list==null||list.size()==0){
			log.error("XML路径"+xpath+"解析出错");
			throw new FlowException("路径"+xpath+"解析出错"); 
		}  
		Attribute attribute = (Attribute)list.get(0);
		return attribute.getValue(); 
	}
 
	private class FlowException extends Exception  
	{  
	    public FlowException(String msg)  
	    {  
	        super(msg);  
	    }  
	}   
	private class QueryItem{
		private String name;
		private String used;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUsed() {
			return used;
		}
		public void setUsed(String used) {
			this.used = used;
		} 
	}
	private class RecommendItem{
		private String name;
		private String price;
		private String size;
		private String code;
		private String sprice;
		
		
		public String getSprice() {
			return sprice;
		}
		public void setSprice(String sprice) {
			this.sprice = sprice;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		
	}
	private String generateOrderId() {
		String Time = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Integer maxOrderId = orderIdHelperMapper.getMaxOrderId(Time);
		if (maxOrderId != null) {
			Integer nextId = orderIdHelperMapper.getNextId();
			String id = String.valueOf(nextId);
			for (int i = id.length(); i < 8; i++) {
				id = "0" + id;
			}
			id = Time + id;
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("time", Time);
			map.put("nextId", nextId);
			orderIdHelperMapper.update(map);
			return id;
		} else {
			HashMap<String, Object> map = new HashMap<String, Object>();
			orderIdHelperMapper.reNewSequence();
			Integer nextId = orderIdHelperMapper.getNextId();
			map.put("time", Time);
			map.put("id", nextId);
			orderIdHelperMapper.add(map);
			String id = String.valueOf(nextId);
			for (int i = id.length(); i < 8; i++) {
				id = "0" + id;
			}
			return Time + id;
		}
	}
	

}