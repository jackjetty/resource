package com.rising.mobilepayment.service.impl;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
//import com.rising.mobilepayment.bean.LimitToBusiness;
import com.rising.mobilepayment.bean.OrderInfo;
import com.rising.mobilepayment.bean.OrderResult;
//import com.rising.mobilepayment.bean.PayLimit;
import com.rising.mobilepayment.bean.Product;
import com.rising.mobilepayment.bean.ProductMessageTemplate;
import com.rising.mobilepayment.bean.QQMonthLog;
import com.rising.mobilepayment.bean.QQPerMonthReSubmit;
import com.rising.mobilepayment.bean.ReturnCode;
import com.rising.mobilepayment.bean.SendMessageLog;
import com.rising.mobilepayment.bean.UserInfo;
import com.rising.mobilepayment.commom.CommonUtil;
import com.rising.mobilepayment.commom.Constant;
import com.rising.mobilepayment.commom.HttpUtil;
//import com.rising.mobilepayment.bean.UserPayLimit;
//import com.rising.mobilepayment.bean.UserThisMonthPayMoney;
import com.rising.mobilepayment.commom.DateUtil;
import com.rising.mobilepayment.commom.PageObject;
import com.rising.mobilepayment.job.QQAgentPayTask;
import com.rising.mobilepayment.mapper.FreeProductMapper;
//import com.rising.mobilepayment.mapper.LimitToBusinessMapper;
import com.rising.mobilepayment.mapper.OrderIdHelperMapper;
import com.rising.mobilepayment.mapper.OrderInfoMapper;
import com.rising.mobilepayment.mapper.PayPrizeMapper;
//import com.rising.mobilepayment.mapper.PayLimitMapper;
import com.rising.mobilepayment.mapper.ProductMapper;
import com.rising.mobilepayment.mapper.ProductMessageTemplateMapper;
import com.rising.mobilepayment.mapper.QQMonthLogMapper;
import com.rising.mobilepayment.mapper.QQPoolMapper;
import com.rising.mobilepayment.mapper.ReturnCodeMapper;
import com.rising.mobilepayment.mapper.SendMessageLogMapper;
import com.rising.mobilepayment.mapper.UserInfoMapper;
//import com.rising.mobilepayment.mapper.UserPayLimitMapper;
//import com.rising.mobilepayment.mapper.UserThisMonthPayMoneyMapper;
import com.rising.mobilepayment.service.OrderInfoService;

@Transactional
@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

	Log log = LogFactory.getLog(OrderInfoServiceImpl.class);
	@Autowired
	OrderInfoMapper orderInfoMapper;
	
	@Autowired
	private QQPoolMapper qqPoolMapper; 

	@Autowired
	OrderIdHelperMapper orderIdHelperMapper;

	@Autowired
	ProductMapper productMapper;

	@Autowired
	UserInfoMapper userInfoMapper;

	@Autowired
	PayPrizeMapper payPrizeMapper;

	@Autowired
	FreeProductMapper freeProductMapper;

	@Autowired
	SendMessageLogMapper sendMessageLogMapper;

	@Autowired
	ProductMessageTemplateMapper productMessageTemplateMapper;

	@Autowired
	ReturnCodeMapper returnCodeMapper;
	
	@Autowired
	private QQMonthLogMapper qqMonthLogMapper;
	
	
	

	private String payMoneyIp;

	private String checkQQPerMonthStatusIp;

	private String cancelQQPerMonthIp;

	public String getPayMoneyIp() {
		return payMoneyIp;
	}

	@Value("#{propertiesReader[shortMessageIp]}")
	public void setPayMoneyIp(String payMoneyIp) {
		this.payMoneyIp = payMoneyIp;
	}

	public String getCheckQQPerMonthStatusIp() {
		return checkQQPerMonthStatusIp;
	}

	@Value("#{propertiesReader[checkQQPerMonthStatusIp]}")
	public void setCheckQQPerMonthStatusIp(String checkQQPerMonthStatusIp) {
		this.checkQQPerMonthStatusIp = checkQQPerMonthStatusIp;
	}

	public String getCancelQQPerMonthIp() {
		return cancelQQPerMonthIp;
	}

	@Value("#{propertiesReader[cancelQQPerMonthIp]}")
	public void setCancelQQPerMonthIp(String cancelQQPerMonthIp) {
		this.cancelQQPerMonthIp = cancelQQPerMonthIp;
	}

	@Override
	public String queryOrderInfoByPage(HashMap<String, String> map,
			PageObject page) {
		HashMap<String, Object> map2 = mapChange(map, page);
		HashMap<String, Object> result = new HashMap<String, Object>();
		DateUtil d = new DateUtil();
		HashMap<String, Date> dateMap = d.getLastThreeMonthTime();
		map2.put("StartTime", dateMap.get("Start"));
		map2.put("EndTime", dateMap.get("End"));
		Integer allSize = orderInfoMapper.getAllSize(map2);
		ArrayList<OrderInfo> ao = null;
		try {
			ao = orderInfoMapper.queryOrderInfoList(map2);
			ArrayList<OrderResult> aor = new ArrayList<OrderResult>();
			for (OrderInfo orderInfo : ao) {
				aor.add(changeOrderInfoToOrderResult(orderInfo));
			}
			result.put("respCode", 0);
			result.put("respInfo", "");
			result.put("pageIndex", page.getPageIndex());
			Integer pageSize = allSize % page.getListSize() == 0 ? allSize
					/ page.getListSize() : allSize / page.getListSize() + 1;
			result.put("pageSize", pageSize);
			result.put("listRecord", aor);
		} catch (Exception e) {
			log.error("queryOrderInfoByPage()->数据库查询失败！" + e.toString());
			result.put("respCode", -206);
			result.put("respInfo", "数据库查询出现异常！");
		}
		return new Gson().toJson(result);
	}

	@Override
	public String queryOrderInfoByPage2(HashMap<String, String> map,
			PageObject page) {
		HashMap<String, Object> map2 = mapChange(map, page);
		HashMap<String, Object> result = new HashMap<String, Object>();
		DateUtil d = new DateUtil();
		HashMap<String, Date> dateMap = d.getLastThreeMonthTime();
		map2.put("StartTime", dateMap.get("Start"));
		map2.put("EndTime", dateMap.get("End"));
		Integer allSize = orderInfoMapper.getAllSize(map2);
		ArrayList<HashMap<String, Object>> ao = null;
		ArrayList<HashMap<String, Object>> ao2 = new ArrayList<HashMap<String, Object>>();
		try {
			ao = orderInfoMapper.queryOrderInfoList2(map2);
			for (HashMap<String, Object> hashMap : ao) {
				HashMap<String, Object> newMap = new HashMap<String, Object>();
				Date d1 = (Date) hashMap.get("RECHARGETIME");
				newMap.put("rechargeTime",
						new SimpleDateFormat("MM月dd日HH:mm").format(d1));
				java.math.BigDecimal big = (BigDecimal) hashMap
						.get("BUSINESSTYPE");
				String businessType = big.toString();
				if ("1031".equals(businessType)) {
					newMap.put("businessType", "103");
					String rechargeResult = (String) hashMap
							.get("RECHARGERESULT");
					if ("扣费成功".equals(rechargeResult)) {
						newMap.put("rechargeResult", "0");
					} else {
						newMap.put("rechargeResult", "1");
					}
					newMap.put("parvalue", hashMap.get("PARVALUE"));
					newMap.put("vipType", "");
				} else {
					newMap.put("businessType", businessType);
					newMap.put("rechargeResult", hashMap.get("RECHARGERESULT"));
					Product product = productMapper.findProductById(String
							.valueOf(hashMap.get("PRODUCTID")));
					newMap.put("parvalue", product.getCost());
					newMap.put("vipType", product.getProductName());
				}
				newMap.put("rechargeType",
						getType(Integer.parseInt(businessType)));
				newMap.put("rechargeNumber", hashMap.get("RECHARGENUMBER"));
				newMap.put("orderAmount", hashMap.get("ORDERAMOUNT"));
				ao2.add(newMap);
			}
			result.put("respCode", 0);
			result.put("respInfo", "");
			result.put("pageIndex", page.getPageIndex());
			Integer pageSize = allSize % page.getListSize() == 0 ? allSize
					/ page.getListSize() : allSize / page.getListSize() + 1;
			result.put("pageSize", pageSize);
			result.put("listRecord", ao2);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("queryOrderInfoByPage()->数据库查询失败！" + e.toString());
			result.put("respCode", -206);
			result.put("respInfo", "数据库查询出现异常！");
		}
		return new Gson().toJson(result);
	}

	private HashMap<String, Object> mapChange(HashMap<String, String> map,
			PageObject page) {
		HashMap<String, Object> last = new HashMap<String, Object>();
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		String key = "";
		String value = "";
		while (it.hasNext()) {
			key = it.next();
			value = map.get(key);
			if (!"".equals(value)) {
				last.put(key, value);
			} else
				continue;
		}
		int pageIndex = page.getPageIndex();
		int listSize = page.getListSize();
		int start = (pageIndex - 1) * listSize + 1;
		int end = start + listSize - 1;
		last.put("start", start);
		last.put("end", end);
		return last;
	}

	@Override
	public String findOrderInfoById(String orderId) {
		OrderInfo order = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			order = orderInfoMapper.findById(orderId);
			result.put("respCode", 0);
			result.put("respInfo", "");
			result.put("OrderDetail", order);
		} catch (Exception e) {
			log.error("findOrderInfoById()->查询交易记录详细信息时出错！" + e.toString());
			result.put("respCode", -206);
			result.put("respInfo", "数据库查询出现异常！");
		}
		return new Gson().toJson(result);
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

	private String mapToString(HashMap<String, Object> map) {
		String parameter = "";
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = String.valueOf(map.get(key));
			parameter = parameter + key + "=" + value + "$";
		}
		return parameter;
	}

	private boolean payMoney(String url, String parameter,
			HashMap<String, String> map) {
		String result = "";
		java.net.URL httpUrl;
		try {
			httpUrl = new URL(url);
			URLConnection uc = (URLConnection) httpUrl.openConnection();
			uc.setDoOutput(true);
			uc.setDoInput(true);
			PrintWriter out = new PrintWriter(uc.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();
		} catch (Exception e1) {
			log.error("payMoney()->发送支付请求失败！" + e1.toString());
			return false;
		}
		
		Pattern responsePtn = Pattern.compile("\\$+"); 
		String[] strs = responsePtn.split(result); 
		Pattern cellPtn = Pattern.compile("^(.+)=(.*?)$"); 
		for (String cellRst:strs) { 
			Matcher matcher = cellPtn.matcher(cellRst ); 
			if(matcher.find()){
				
				
				map.put(CommonUtil.trim(matcher.group(1)), CommonUtil.trim(matcher.group(2)));
			       
			} 
		        
		
		}  
	 
		if ("0".equals(map.get("result"))) {
			return true;
		} else {
			return false;
		}
	}

	private String payMoney(String url, String parameter) {
		String result = "";
		java.net.URL httpUrl;
		try {
			httpUrl = new URL(url);
			URLConnection uc = (URLConnection) httpUrl.openConnection();
			uc.setDoOutput(true);
			uc.setDoInput(true);
			PrintWriter out = new PrintWriter(uc.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();
		} catch (Exception e1) {
			log.error("payMoney()->发送支付请求失败！" + e1.toString());
		}
		return result;
	}

	@Override
	public String takeCharge(HashMap<String, String> map)
			throws RuntimeException {
		HashMap<String, Object> param = new HashMap<String, Object>();
		HashMap<String, Object> result = new HashMap<String, Object>();
		OrderInfo order = new OrderInfo();
		order.setOrderId(map.get("OrderId"));
		order.setProductId(map.get("ProductId"));
		order.setPhoneNumber(map.get("PhoneNumber"));
		order.setSPID(map.get("SPID"));
		order.setTargetNumber(map.get("TargetNumber"));
		order.setPayMoney(Float.parseFloat(map.get("PayMoney")));
		order.setPayMethodId(Integer.parseInt(map.get("PayMethodId")));
		OrderInfo neworder = orderInfoMapper.findByExample(order);
		
		
		if (neworder == null) {
			result.put("respCode", -7);
			result.put("respInfo", "充值失败");
			return new Gson().toJson(result);
		}
		String client=CommonUtil.trim(neworder.getClient());
		Random random = new Random();  
		/**/
		 
		
		String phoneNumber = map.get("PhoneNumber");
		String payMethodId = map.get("PayMethodId");
		
		
		
		ArrayList<String> productIds = new ArrayList<String>();
		productIds.add("16019");
		productIds.add("16020");
		productIds.add("16021");
		productIds.add("16022");
		String productId = map.get("ProductId");
		
		   
			
		 
		Product product = productMapper.findProductById(map.get("ProductId"));
		param.put("paymoney", product.getCost());
		
		/*
		if (productIds.contains(productId)) { 
				if (client.equalsIgnoreCase("limia900")
						||checkFirst(phoneNumber, payMethodId) )  
					 map.put("ProductId", productId + "90"); 
			 
		}*/
		param.put("caller", map.get("PhoneNumber"));
		param.put("objid", map.get("ProductId"));
		param.put("payid", map.get("OrderId"));
		param.put("spid", map.get("SPID"));
		param.put("username", map.get("TargetNumber"));
		
		/*
		if(payMethodId.equals("1")
        		&&phoneNumber.equals("18072749082")
        		     &&productId.length()==5){ 
				result.put("respCode", -9);
				result.put("respInfo", "充值失败，\n系统繁忙！\n支付宝支付测试期间惊喜优惠！！" );
				return new Gson().toJson(result);
				
        }*/
		
		String parameter = mapToString(param);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		boolean sign=payMoney(payMoneyIp, parameter, resultMap);
		try {
			if (sign) {
				neworder.setPayReturnCode(resultMap.get("result"));
				neworder.setPayStatus("成功");
				neworder.setPayTime(new Date());
				neworder.setSendScore(product.getSendScore());
				orderInfoMapper.update(neworder);
				UserInfo user = userInfoMapper.findUserByPhoneNumber(map
						.get("PhoneNumber"));
				 
				user.setAllScore(0f);
				userInfoMapper.update(user); 

				if (product.getBusId() == 100) {
					HashMap<String, Object> map2 = new HashMap<String, Object>();
					map2.put("id", "100");
					map2.put("type", "CHARGE");
					ProductMessageTemplate template = productMessageTemplateMapper
							.getTemplateByIdAndType(map2);
					String messageContent = template.getMessageTemplate();
					messageContent = messageContent.replaceAll("NUMBER",
							product.getCost().toString());
					messageContent = messageContent.replaceAll("MONEY",
							String.valueOf(neworder.getPayMoney()));
					messageContent = messageContent.replaceAll("TIME",
							new SimpleDateFormat("yyyy年MM月dd日")
									.format(new Date()));
					SendMessageLog log1 = new SendMessageLog();
					log1.setLogTime(new Date());
					log1.setLogType("request");
					log1.setMessageContent(messageContent);
					log1.setMessageType("Q币直充");
					log1.setSendPhoneNumber(String.valueOf(map
							.get("PhoneNumber")));
					try {
						String dataString = "objid=16670$username="
								+ URLEncoder.encode(messageContent, "GB2312")
								+ "$paymoney=$payid=$caller="
								+ map.get("PhoneNumber");
						log1.setDataString(dataString);
						sendMessageLogMapper.add(log1);
						String returnString = sendMessage(
								"http://61.130.6.68:8090", dataString);
						SendMessageLog log2 = new SendMessageLog();
						log2.setLogTime(new Date());
						log2.setLogType("response");
						log2.setMessageContent(messageContent);
						log2.setMessageType("Q币直充");
						log2.setSendPhoneNumber(String.valueOf(map
								.get("PhoneNumber")));
						log2.setDataString(returnString);
						sendMessageLogMapper.add(log2);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				result = new HashMap<String, Object>();
				result.put("respCode", 0);
				result.put("respInfo", product.getProductName() + "充值成功");
				result.put("userScore", 0f);
				return new Gson().toJson(result);
			} else {
				neworder.setPayStatus("失败");
				neworder.setPayTime(new Date());
				
                String resultCode = String.valueOf(resultMap.get("result"));
				
				resultCode=checkReturnCode(phoneNumber,String.valueOf(neworder.getBusId()),resultCode);
				
				
				neworder.setPayReturnCode(resultCode);
				neworder.setSendScore((float) 0);
				orderInfoMapper.update(neworder);
				HashMap<String, Object> queryMap = new HashMap<String, Object>();
				queryMap.put("BusId", neworder.getBusId());
				queryMap.put("ReturnCode",resultCode);
				ReturnCode returnCode = returnCodeMapper.findByBusIdAndReturnCode(queryMap);
				result = new HashMap<String, Object>();
				result.put("respCode", -7);
				if (returnCode != null) {
					result.put("respInfo", "充值失败！" + returnCode.getShow());
				} else {
					result.put("respInfo", "亲，系统维护中，给您带来的不便敬请谅解！");
				}
				HashMap<String, Object>  paraMap;
				paraMap =new HashMap<String, Object> ();
				/* if(payMethodId.equals("1")&&qqPoolMapper.getQBRestQuota(paraMap)>150){
					 result.put("respInfo", result.get("respInfo") +"\n英雄联盟支付宝支付惊喜优惠！！");
				 }*/ 
				return new Gson().toJson(result);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String takeOrder(HashMap<String, String> map)
			throws RuntimeException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String TargetNumber = map.get("TargetNumber");
		String PhoneNumber = map.get("PhoneNumber");
		DateUtil d = new DateUtil();
		HashMap<String, Date> dateMap = d.getThisMonthTime();
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("TargetNumber", TargetNumber);
		param.put("PhoneNumber", PhoneNumber);
		param.put("StartTime", dateMap.get("Start"));
		param.put("EndTime", dateMap.get("End"));
		ArrayList<OrderInfo> ao = orderInfoMapper
				.queryPhoneNumberListForThisTargetNumber(param);
		if (ao.size() > 2) {
			result.put("respCode", -13);
			result.put("respInfo", "充值的手机号码已达限量！");
			return new Gson().toJson(result);
		}
		Product product = productMapper.findProductById(map.get("ProductId"));
		if (product == null) {
			result.put("respCode", -6);
			result.put("respInfo", "下单失败!");
			return new Gson().toJson(result);
		}
		if (product.getBusId() == 101 || product.getBusId() == 102) {
			result.put("respCode", -12);
			result.put("respInfo", "该产品已下架");
			return new Gson().toJson(result);
		}
		OrderInfo order = new OrderInfo();
		order.setOrderId(generateOrderId());
		order.setProductId(map.get("ProductId"));
		order.setBusId(product.getBusId());
		order.setTargetNumber(map.get("TargetNumber"));
		order.setPayMoney(Float.parseFloat(map.get("PayMoney")));
		order.setPhoneNumber(map.get("PhoneNumber"));
		order.setOrderTime(new Date());
		order.setPayMethodId(Integer.parseInt(map.get("PayMethodId")));
		order.setOrderStatus("成功");
		order.setPayStatus("未支付");
		order.setSPID(product.getSPID());
		order.setSendScore(0);
		String userAgent = map.get("user-agent");
		String[] info = userAgent.split("_");
		order.setOs(info[0]);
		order.setVersion(info[1]);
		order.setClient(info[2]);
		try {
			orderInfoMapper.add(order);
			result.put("respCode", 0);
			result.put("respInfo", "");
			result.put("OrderId", order.getOrderId());
			result.put("PhoneNumber", order.getPhoneNumber());
		} catch (Exception e) {
			log.error("takeOrder()->" + e.toString());
			throw new RuntimeException(e.getMessage());
		}
		return new Gson().toJson(result);
	}

	public OrderResult changeOrderInfoToOrderResult(OrderInfo order) {
		OrderResult result = new OrderResult();
		Date payTime = order.getPayTime();
		String RechargeTime = new SimpleDateFormat("MM月dd日HH:mm")
				.format(payTime);
		Product product = productMapper.findProductById(order.getProductId());
		result.setBusinessType(String.valueOf(order.getBusId()));
		result.setRechargeType(getType(order.getBusId()));
		result.setVipType(product.getProductName());
		result.setOrderAmount(order.getPayMoney());
		result.setParvalue(product.getCost());
		result.setRechargeNumber(order.getTargetNumber());
		result.setRechargeResult(order.getPayReturnCode());
		result.setRechargeTime(RechargeTime);
		result.setRecordId(order.getOrderId());
		return result;
	}

	private String getType(Integer busId) {
		switch (busId) {
		case 100:
			return "Q币充值";
		case 101:
			return "V币充值";
		case 102:
			return "米币充值";
		case 103:
			return "Q币包月提交";
		case 1031:
			return "Q币包月扣费";
		case 104:
			return "会员包月";
		case 105:
			return "流量包充值";
		case 106:
			return "话费充值";
		}
		return null;
	}

	@Override
	public String takeOrderQQPerMonth(HashMap<String, String> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		Product product = productMapper.findProductById(map.get("ProductId"));
		if (product == null) {
			result.put("respCode", -7);
			result.put("respInfo", "Q币包月申请失败!");
			return new Gson().toJson(result);
		}

		OrderInfo order = new OrderInfo();
		order.setOrderId(generateOrderId());
		order.setProductId(map.get("ProductId"));
		order.setBusId(product.getBusId());
		order.setPayMethodId(Integer.parseInt(map.get("PayMethodId")));
		order.setTargetNumber(map.get("TargetNumber"));
		order.setPayMoney(Float.parseFloat(map.get("PayMoney")));
		order.setPhoneNumber(map.get("PhoneNumber"));
		order.setOrderTime(new Date());
		order.setOrderStatus("成功");
		order.setPayStatus("Q币包月申请未确认");
		order.setSPID(product.getSPID());
		order.setSendScore(0);
		String userAgent = map.get("user-agent");
		String[] info = userAgent.split("_");
		order.setOs(info[0]);
		order.setVersion(info[1]);
		order.setClient(info[2]);
		try {
			orderInfoMapper.add(order);
			result.put("respCode", 0);
			result.put("respInfo", "");
			result.put("OrderId", order.getOrderId());
			result.put("PhoneNumber", order.getPhoneNumber());
		} catch (Exception e) {
			log.error("takeOrderQQPerMonth()->" + e.toString());
			throw new RuntimeException(e.getMessage());
		}
		return new Gson().toJson(result);
	}

	@Override
	public String takeQQPerMonthCharge(HashMap<String, String> map)
			throws RuntimeException {
		HashMap<String, Object> param = new HashMap<String, Object>();
		HashMap<String, Object> result = new HashMap<String, Object>();
		OrderInfo order = new OrderInfo();
		order.setOrderId(map.get("OrderId"));
		order.setProductId(map.get("ProductId"));
		order.setPhoneNumber(map.get("PhoneNumber"));
		order.setSPID(map.get("SPID"));
		order.setTargetNumber(map.get("TargetNumber"));
		order.setPayMoney(Float.parseFloat(map.get("PayMoney")));
		order.setPayMethodId(Integer.parseInt(map.get("PayMethodId")));
		OrderInfo neworder = orderInfoMapper.findByExample(order);
		if (neworder == null) {
			result.put("respCode", -7);
			result.put("respInfo", "Q币包月信息提交失败");
			return new Gson().toJson(result);
		}
		
		
		if(CommonUtil.hasCurPM(  map.get("PhoneNumber"))){
			result.put("respCode", -2);
			result.put("respInfo", "该手机已有包月信息，请先取消后再办理！");
			return  new Gson().toJson(result);
		}
	 
		String productId = map.get("ProductId");
		Product product = productMapper.findProductById(productId);
		if (product != null) {
			if (product.getProductId() != null) {
				param.put("objid", product.getProductId().substring(0, 5));
			} else {
				result.put("respCode", -7);
				result.put("respInfo", "Q币包月信息提交失败");
				return new Gson().toJson(result);
			}
		} else {
			result.put("respCode", -7);
			result.put("respInfo", "Q币包月信息提交失败");
			return new Gson().toJson(result);
		}
		param.put("username", "z" + map.get("TargetNumber"));
		param.put("caller", map.get("PhoneNumber"));
		param.put("paymoney", product.getCost());
		param.put("payid", "");
		String parameter = mapToString(param);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		boolean sign=payMoney(payMoneyIp, parameter, resultMap);
		//插入 QQMonthLog 操作
		String phoneNumber=CommonUtil.trim(map.get("PhoneNumber"));
		String qqNumber=CommonUtil.trim(map.get("TargetNumber"));
		String fee=product.getCost().toString();
		Date curDate=new Date();
		String curMonthForm=CommonUtil.doDateForm(curDate,"yyyyMM"); 
		QQMonthLog qqMonthLog;
		if(sign){
			qqMonthLog=qqMonthLogMapper.findByPQM(phoneNumber, qqNumber, curMonthForm);
			if(qqMonthLog==null){
				qqMonthLog =new QQMonthLog(); 
				qqMonthLog.setApplyDate(curDate);
				qqMonthLog.setCancelDate(null);
				qqMonthLog.setCreateDate(curDate);
				qqMonthLog.setFee(fee);
				qqMonthLog.setFromSource(Constant.SOURCE168);
				qqMonthLog.setLastCheckDate(CommonUtil.firstMonthDate());
				qqMonthLog.setMsgDate (null);
				qqMonthLog.setMsgSign("1");
				qqMonthLog.setOrderStatus("1");
				qqMonthLog.setPaymethodId(CommonUtil.trim(order.getPayMethodId()) );
				qqMonthLog.setPayMoney(CommonUtil.trim(order.getPayMoney()));
				qqMonthLog.setPayStatus("未扣费");
				qqMonthLog.setPhoneNumber(phoneNumber);
				qqMonthLog.setQqNumber(qqNumber);
				qqMonthLog.setRegDate(curDate);
				qqMonthLog.setTargetMonth(curMonthForm);
				qqMonthLogMapper.add(qqMonthLog);
			}
			else{
				qqMonthLog.setApplyDate(curDate);
				qqMonthLog.setCancelDate(null); 
				qqMonthLog.setFee(fee);
				qqMonthLog.setFromSource(Constant.SOURCE168);
				qqMonthLog.setLastCheckDate(CommonUtil.firstMonthDate());
				qqMonthLog.setMsgDate (null);
				//qqMonthLog.setMsgSign("1");
				qqMonthLog.setOrderStatus("1");
				qqMonthLog.setPaymethodId(CommonUtil.trim(order.getPayMethodId()) );
				qqMonthLog.setPayMoney(CommonUtil.trim(order.getPayMoney()));
				//qqMonthLog.setPayStatus("未扣费");
				qqMonthLog.setPhoneNumber(phoneNumber); 
				qqMonthLogMapper.update(qqMonthLog);
			}
		}
		
		
		
		try {
			if (sign) {
				neworder.setPayReturnCode(resultMap.get("result"));
				neworder.setPayStatus("Q币包月信息提交成功");
				neworder.setPayTime(new Date());
				neworder.setSendScore(0);
				orderInfoMapper.update(neworder);
				result = new HashMap<String, Object>();
				result.put("respCode", 0);
				result.put("respInfo", "Q币包月订单提交成功");
				return new Gson().toJson(result);
			} else {
				neworder.setPayStatus("Q币包月订单提交失败");
				neworder.setPayTime(new Date());
				neworder.setPayReturnCode(resultMap.get("result"));
				neworder.setSendScore((float) 0);
				orderInfoMapper.update(neworder);
				HashMap<String, Object> queryMap = new HashMap<String, Object>();
				queryMap.put("BusId", neworder.getBusId());
				queryMap.put("ReturnCode", resultMap.get("result"));
				ReturnCode returnCode = returnCodeMapper
						.findByBusIdAndReturnCode(queryMap);
				result = new HashMap<String, Object>();
				result.put("respCode", -7);
				if (returnCode != null) {
					result.put("respInfo", "Q币包月订单提交失败" + returnCode.getShow());
				} else {
					result.put("respInfo", "亲，系统维护中，给您带来的不便敬请谅解！");
				}
				return new Gson().toJson(result);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
   

	@Override
	public String reSubmit(QQPerMonthReSubmit reSubmit, Product product) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("objid", reSubmit.getProductId().substring(0, 5));
		param.put("username", "z" + reSubmit.getQqNumber());
		param.put("caller", reSubmit.getPhoneNumber());
		param.put("paymoney", product.getCost());
		param.put("payid", "");
		String parameter = mapToString(param);
		String result = payMoney(payMoneyIp, parameter);
		return result;
	}

	@Override
	public String takeQQVIPOrder(HashMap<String, String> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Product product = productMapper.findProductById(map.get("ProductId"));
		if (product == null) {
			result.put("respCode", -7);
			result.put("respInfo", "QQ会员申请失败!");
			return new Gson().toJson(result);
		}
		OrderInfo order = new OrderInfo();
		order.setOrderId(generateOrderId());
		order.setProductId(map.get("ProductId"));
		order.setBusId(product.getBusId());
		 //Integer.parseInt()
		order.setPayMethodId(CommonUtil.castInt(CommonUtil.trim(map.get("PayMethodId"))));
		order.setTargetNumber(map.get("TargetNumber"));
		order.setPayMoney(Float.parseFloat(map.get("PayMoney")));
		order.setPhoneNumber(map.get("PhoneNumber"));
		order.setOrderTime(new Date());
		order.setOrderStatus("成功");
		order.setPayStatus("QQ会员申请未确认!");
		order.setSPID(product.getSPID());
		order.setSendScore(0);
		String userAgent = map.get("user-agent");
		String[] info = userAgent.split("_");
		order.setOs(info[0]);
		order.setVersion(info[1]);
		order.setClient(info[2]);
		try {
			orderInfoMapper.add(order);
			result.put("respCode", 0);
			result.put("respInfo", "");
			result.put("OrderId", order.getOrderId());
			result.put("PhoneNumber", order.getPhoneNumber());
		} catch (Exception e) {
			log.error("takeOrderQQPerMonth()->" + e.toString());
			throw new RuntimeException(e.getMessage());
		}
		return new Gson().toJson(result);
	}

	@Override
	public String takeQQVIPCharge(HashMap<String, String> map)
			throws RuntimeException {
		HashMap<String, Object> param = new HashMap<String, Object>();
		HashMap<String, Object> result = new HashMap<String, Object>();
		OrderInfo order = new OrderInfo();
		order.setOrderId(map.get("OrderId"));
		order.setProductId(map.get("ProductId"));
		order.setPhoneNumber(map.get("PhoneNumber"));
		order.setSPID(map.get("SPID"));
		order.setTargetNumber(map.get("TargetNumber"));
		order.setPayMoney(Float.parseFloat(map.get("PayMoney")));
		order.setPayMethodId(Integer.parseInt(map.get("PayMethodId")));
		OrderInfo neworder = orderInfoMapper.findByExample(order);
		String productId = map.get("ProductId");
		Product product = productMapper.findProductById(productId);
		
		String phoneNumber=CommonUtil.trim(map.get("PhoneNumber"));
		String payMethodId=CommonUtil.trim(map.get("PayMethodId"));
				
				 
		//160600402
		 /* if(payMethodId.equals("1")
        		&&phoneNumber.equals("18072749082")
        		     &&productId.length()>7){ 
				result.put("respCode", -9);
				result.put("respInfo", "充值失败，\n系统繁忙！\n支付宝支付测试期间惊喜优惠！！" );
				return new Gson().toJson(result);
				
        }*/
        /*
       Random random = new Random();  
        if(payMethodId.equals("1")&&random.nextInt(10)>=2){ 
			result.put("respCode", -9);
			result.put("respInfo", "充值失败，\n系统繁忙！\n支付宝支付测试期间惊喜！！" );
			return new Gson().toJson(result);
	      }*/
		if (product != null) {
			if (product.getProductId() != null) {
				param.put("objid", product.getProductId().substring(0, 5));
			} else {
				result.put("respCode", -7);
				result.put("respInfo", product.getProductName() + "包月失败");
				return new Gson().toJson(result);
			}
		} else {
			result.put("respCode", -7);
			result.put("respInfo", "QQ会员包月失败!");
			return new Gson().toJson(result);
		}
		if (neworder == null) {
			result.put("respCode", -7);
			result.put("respInfo", product.getProductName() + "包月失败");
			return new Gson().toJson(result);
		}
		 
		String payid = productId.substring(6, 7);
		param.put("username", map.get("TargetNumber"));
		param.put("caller", map.get("PhoneNumber"));
		param.put("paymoney", product.getCost());
		param.put("payid", payid);
		String parameter = mapToString(param);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		try {
			if (payMoney(payMoneyIp, parameter, resultMap)) {
				neworder.setPayReturnCode(resultMap.get("result"));
				neworder.setPayStatus("成功");
				neworder.setPayTime(new Date());
				neworder.setSendScore(product.getSendScore());
				orderInfoMapper.update(neworder);
				UserInfo user = userInfoMapper.findUserByPhoneNumber(map
						.get("PhoneNumber"));
				float score = user.getAllScore() + product.getSendScore();
				user.setAllScore(0f);
				userInfoMapper.update(user);
				// recordPayMoney(neworder, user);
				result = new HashMap<String, Object>();
				result.put("respCode", 0);
				result.put("respInfo", product.getProductName() + "包月成功");
				result.put("userScore", score);
				return new Gson().toJson(result);
			} else {
				neworder.setPayStatus("失败");
				neworder.setPayTime(new Date());
				neworder.setPayReturnCode(resultMap.get("result"));
				neworder.setSendScore((float) 0);
				orderInfoMapper.update(neworder);
				HashMap<String, Object> queryMap = new HashMap<String, Object>();
				queryMap.put("BusId", neworder.getBusId());
				queryMap.put("ReturnCode", resultMap.get("result"));
				ReturnCode returnCode = returnCodeMapper
						.findByBusIdAndReturnCode(queryMap);
				result = new HashMap<String, Object>();
				result.put("respCode", -7);
				if (returnCode != null) {
					result.put("respInfo", product.getProductName() + "包月失败,\n"
							+ returnCode.getShow()  );
				} else {
					result.put("respInfo",   "亲，系统维护中，给您带来的不便敬请谅解！" );
				}
				HashMap<String, Object>  paraMap;
				paraMap =new HashMap<String, Object> ();
				
				 
				 /*if(payMethodId.equals("1")&&qqPoolMapper.getQBRestQuota(paraMap)>150){
					 result.put("respInfo", result.get("respInfo") +"\n支付宝支付惊喜优惠！！");
				 } */
				
				return new Gson().toJson(result);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private HashMap<String, Object> checkPerMonthStatus(String url,
			String parameter) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String result = "";
		java.net.URL httpUrl;
		try {
			httpUrl = new URL(url);
			URLConnection uc = (URLConnection) httpUrl.openConnection();
			uc.setDoOutput(true);
			uc.setDoInput(true);
			PrintWriter out = new PrintWriter(uc.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();
		} catch (Exception e1) {
			log.error("checkPerMonthStatus()->发送包月情况查询请求失败！" + e1.toString());

		}
		Pattern responsePtn = Pattern.compile("\\$+");
		 
		String[] strs = responsePtn.split(result); 
		Pattern cellPtn = Pattern.compile("^(.+)=(.*?)$"); 
		for (String cellRst:strs) { 
			Matcher matcher = cellPtn.matcher(cellRst ); 
			if(matcher.find()){ 
				map.put(CommonUtil.trim(matcher.group(1)).toLowerCase(), CommonUtil.trim(matcher.group(2)));
			       
			} 
		        
		
		} 
		
		 
		return map;
	}

	@Override
	public String cancelQQPerMonthOrder(HashMap<String, String> paraMap) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String PhoneNumber = paraMap.get("PhoneNumber");
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("PhoneNumber", PhoneNumber);
		map1.put("ProductId", "16050");
		HashMap<String, Object> map = new HashMap<String, Object>();
		String parameter = "objid=16051$caller=" + PhoneNumber + "$username="
				+ "z" + paraMap.get("TargetNumber") + "$paymoney="
				+ paraMap.get("Cost") + "payid=";
		String resultline = "";
		java.net.URL httpUrl;
		try {
			httpUrl = new URL(cancelQQPerMonthIp);
			URLConnection uc = (URLConnection) httpUrl.openConnection();
			uc.setDoOutput(true);
			uc.setDoInput(true);
			PrintWriter out = new PrintWriter(uc.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				resultline += line;
			}
			in.close();
		} catch (Exception e1) {
			log.error("cancelQQPerMonthOrder()->取消包月请求失败" + e1.toString());

		}
		
		Pattern responsePtn = Pattern.compile("\\$+");
		 
		String[] strs = responsePtn.split(resultline); 
		Pattern cellPtn = Pattern.compile("^(.+)=(.*?)$"); 
		for (String cellRst:strs) { 
			Matcher matcher = cellPtn.matcher(cellRst ); 
			if(matcher.find()){
				
				
				map.put(CommonUtil.trim(matcher.group(1)).toLowerCase(), CommonUtil.trim(matcher.group(2)));
			       
			} 
		        
		
		} 
		 

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", "103-C");
		map2.put("type", "CANCEL");
		ProductMessageTemplate template = productMessageTemplateMapper
				.getTemplateByIdAndType(map2);
		String messageContent = template.getMessageTemplate();
		messageContent = messageContent.replaceAll("TIME",
				new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		SendMessageLog log1 = new SendMessageLog();
		log1.setLogTime(new Date());
		log1.setLogType("request");
		log1.setMessageContent(messageContent);
		log1.setMessageType("Q币包月退订");
		log1.setSendPhoneNumber(PhoneNumber);
		try {
			String dataString = "objid=16670$username="
					+ URLEncoder.encode(messageContent, "GB2312")
					+ "$paymoney=$payid=$caller=" + PhoneNumber;
			log1.setDataString(dataString);
			sendMessageLogMapper.add(log1);
			String returnString = sendMessage("http://61.130.6.68:8090",
					dataString);
			SendMessageLog log2 = new SendMessageLog();
			log2.setLogTime(new Date());
			log2.setLogType("response");
			log2.setMessageContent(messageContent);
			log2.setMessageType("Q币包月退订");
			log2.setSendPhoneNumber(PhoneNumber);
			log2.setDataString(returnString);
			sendMessageLogMapper.add(log2);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (map.get("result").equals("0")) {
			result.put("respCode", 0);
			result.put("respInfo", "包月取消成功");
		} else {
			result.put("respCode", -19);
			result.put("respInfo", "包月取消失败");
		}
		return new Gson().toJson(result);
	}

	@Override
	public HashMap<String, Object> checkPerMonth(String phoneNumber) {
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("objid", "16052");
		paraMap.put("caller", phoneNumber);
		paraMap.put("username", "1165945426");
		paraMap.put("paymoney", 30);
		String parameter = mapToString(paraMap);
		return checkPerMonthStatus(checkQQPerMonthStatusIp, parameter);
	}

	 

	@Override
	public String recordTelephoneFee(HashMap<String, String> map) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String dt_order = map.get("dt_order");
		String mb_cust = map.get("mb_cust");
		String money_order = map.get("money_order");
		String no_order = map.get("no_order");
		String price_goods = map.get("price_goods");
		String sta_order = map.get("sta_order");
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("busId", "106");
		paraMap.put("cost", Float.parseFloat(price_goods));
		ArrayList<Product> ap = productMapper.findByBusIdAndCost(paraMap);
		if (ap == null || ap.size() == 0) {
			resultMap.put("ret_code", "1111");
			resultMap.put("ret_msg", "交易失败");
		} else {
			Product p = ap.get(0);
			OrderInfo order = new OrderInfo();
			order.setOrderId(generateOrderId());
			order.setProductId(p.getProductId());
			order.setHCOrderId(no_order);
			order.setBusId(p.getBusId());
			order.setPayMethodId(1);
			order.setTargetNumber(mb_cust);
			order.setPayMoney(Float.parseFloat(money_order));
			order.setPhoneNumber(mb_cust);
			order.setSPID(p.getSPID());
			try {
				Date d = new SimpleDateFormat("yyyyMMddHHmmss").parse(dt_order);
				order.setOrderTime(d);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			order.setPayTime(new Date());
			order.setOrderStatus("成功");
			UserInfo user = userInfoMapper.findUserByPhoneNumber(mb_cust);
			try {
				if ("SUCCESS".equals(sta_order)) {
					order.setPayStatus("成功");
					order.setPayReturnCode("0");
					if (user != null) {
						float score = user.getAllScore() + p.getSendScore();
						user.setAllScore(0f);
						userInfoMapper.update(user);
						order.setSendScore(p.getSendScore());
					} else {
						order.setSendScore(0);
					}
				} else {
					order.setPayStatus("失败");
					order.setPayReturnCode("-1");
					order.setSendScore(0);
				}

				orderInfoMapper.addTelephoneFee(order);
				resultMap.put("ret_code", "0000");
				resultMap.put("ret_msg", "交易成功");
			} catch (Exception e) {
				log.error("recordTelephoneFee()->" + e.toString());
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return new Gson().toJson(resultMap);

	}

	@Override
	public ArrayList<OrderInfo> findQQPerMonthOrderByPhoneNumber(
			String phoneNumber) {
		ArrayList<OrderInfo> aoi = orderInfoMapper
				.findQQPerMonthOrderByPhoneNumber(phoneNumber);
		return aoi;
	}

	
	
	
 

	private String sendMessage(String url, String parameter) {
		String result = "";
		java.net.URL httpUrl;
		try {
			httpUrl = new URL(url);
			URLConnection uc = (URLConnection) httpUrl.openConnection();
			uc.setDoOutput(true);
			uc.setDoInput(true);
			PrintWriter out = new PrintWriter(uc.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream(), "gb2312"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	 

	public Boolean checkFirst(String phoneNumber, String payMethodId) {
		ArrayList<String> permitPayMethodId = new ArrayList<String>();
		permitPayMethodId.add("1");
		permitPayMethodId.add("2");
		permitPayMethodId.add("9");
		int dealSum=0;
		Date curDate=new Date();
		Date beginDate=CommonUtil.getDateByForm("2015-12-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date endDate=CommonUtil.getDateByForm("2016-01-30 00:00:00", "yyyy-MM-dd HH:mm:ss");
		if( curDate.after(beginDate)&&curDate.before(endDate)&&(!phoneNumber.equals("")) ){
			dealSum=orderInfoMapper.getSuccessOrderNumber(phoneNumber, "100", "1", CommonUtil.doDateForm( beginDate, "yyyy-MM-dd HH:mm:ss"), CommonUtil.doDateForm( endDate, "yyyy-MM-dd HH:mm:ss"));
			dealSum+=orderInfoMapper.getSuccessOrderNumber(phoneNumber, "100", "2", CommonUtil.doDateForm( beginDate, "yyyy-MM-dd HH:mm:ss"), CommonUtil.doDateForm( endDate, "yyyy-MM-dd HH:mm:ss"));
			dealSum+=orderInfoMapper.getSuccessOrderNumber(phoneNumber, "100", "9", CommonUtil.doDateForm( beginDate, "yyyy-MM-dd HH:mm:ss"), CommonUtil.doDateForm( endDate, "yyyy-MM-dd HH:mm:ss"));
			if(dealSum==0)
				 return true;
		}
		return false;
	}
	
	
	public String  checkReturnCode(String phoneNumber,String busId,String resultCode){
		resultCode=CommonUtil.trim(resultCode);
		busId=CommonUtil.trim(busId);
		Calendar calendar = Calendar.getInstance();
		if(resultCode.equalsIgnoreCase("9999")){
			calendar.add(Calendar.MINUTE, -35);
			int lastTimeSuccessOrderNumber=0;
			lastTimeSuccessOrderNumber=orderInfoMapper.getLastTimeSuccessOrderNumber(phoneNumber, busId,"成功",CommonUtil.doDateForm(calendar.getTime(),"yyyy-MM-dd HH:mm:ss"));
			if(lastTimeSuccessOrderNumber>0)
				resultCode="99991";
			return resultCode;
		}
		return resultCode;
		
	}

}
