/**
 * UserInfoServiceImpl.java
 * com.rising.mobilepayment.service.impl
 * 工程：MobilePayMent
 * 功能： TODO 
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * chapterless     2013-5-30   上午11:23:28
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
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
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.OperateLog;
import com.rising.mobilepayment.bean.OrderInfo;
import com.rising.mobilepayment.bean.PhoneNumber;
import com.rising.mobilepayment.bean.QQPerMonthRecord;
import com.rising.mobilepayment.bean.SendMessageLog;
import com.rising.mobilepayment.bean.UserCheckIn;
import com.rising.mobilepayment.bean.UserInfo;
import com.rising.mobilepayment.bean.UserPublicInfoReadStatus;
import com.rising.mobilepayment.commom.Base64Utils;
import com.rising.mobilepayment.commom.CommonUtil;
import com.rising.mobilepayment.commom.Constant;
import com.rising.mobilepayment.mapper.AnswerFeedBackMapper;
import com.rising.mobilepayment.mapper.GetScoreRuleMapper;
import com.rising.mobilepayment.mapper.MessageMapper;
import com.rising.mobilepayment.mapper.OperateLogMapper;
import com.rising.mobilepayment.mapper.OrderInfoMapper;
import com.rising.mobilepayment.mapper.PhoneNumberMapper;
import com.rising.mobilepayment.mapper.PublicInfoMapper;
import com.rising.mobilepayment.mapper.QQPerMonthRecordMapper;
import com.rising.mobilepayment.mapper.SendMessageLogMapper;
import com.rising.mobilepayment.mapper.UserCheckInMapper;
import com.rising.mobilepayment.mapper.UserInfoMapper;
import com.rising.mobilepayment.mapper.UserPublicInfoReadStatusMapper;
import com.rising.mobilepayment.service.LotteryService;
import com.rising.mobilepayment.service.OrderInfoService;
import com.rising.mobilepayment.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	Log log = LogFactory.getLog(UserInfoServiceImpl.class);

	@Autowired
	UserInfoMapper userInfoMapper;

	@Autowired
	GetScoreRuleMapper getScoreRuleMapper;

	@Autowired
	MessageMapper messageMapper;

	@Autowired
	OperateLogMapper operateLogMapper;

	@Autowired
	OrderInfoService orderInfoService;

	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Autowired
	UserCheckInMapper userCheckInMapper;

	@Autowired
	PublicInfoMapper publicInfoMapper;

	@Autowired
	AnswerFeedBackMapper answerFeedBackMapper;

	@Autowired
	QQPerMonthRecordMapper qQPerMonthRecordMapper;
	
	@Autowired
	private SendMessageLogMapper sendMessageLogMapper;
	
	
	@Autowired
	private PhoneNumberMapper phoneNumberMapper;

	@Autowired
	LotteryService lotteryService;
	
	  

	private String getLeftMoneyForwardUrl;

	public String getGetLeftMoneyForwardUrl() {
		return getLeftMoneyForwardUrl;
	}

	@Value("#{propertiesReader[getLeftMoneyForwardUrl]}")
	public void setGetLeftMoneyForwardUrl(String getLeftMoneyForwardUrl) {
		this.getLeftMoneyForwardUrl = getLeftMoneyForwardUrl;
	}

	@Autowired
	UserPublicInfoReadStatusMapper userPublicInfoReadStatusMapper;

	
	
	
	@Override
	public String addUser(UserInfo user) {
		Map<String, Object> result = new HashMap<String, Object>();
		String password = user.getPassword(); 
		try {
			String pwdAfterBase64Decode = new String(
					Base64Utils.decode(password));
			user.setPassword(pwdAfterBase64Decode);
		} catch (Exception e1) {
			log.error("addUser()->验证用户身份时登录密码Base64解密失败！" + e1.toString());
			result.put("respCode", -201);
			result.put("respInfo", "用户登录密码Base64解密失败！");
			return new Gson().toJson(result);
		}
		try {
			/*Integer score = getScoreRuleMapper
					.getSendScoreByOperateType("UserRegister");*/
		 
		    user.setAllScore(0); 
			userInfoMapper.addUser(user);
		} catch (Exception e) {
			log.error("addUser()->向数据库中插入新注册用户信息时出现异常！" + e.toString());
			result.put("respCode", -204);
			result.put("respInfo", "插入数据异常！");
			return new Gson().toJson(result);
		}
		result.put("respCode", 0);
		result.put("respInfo", "");
		return new Gson().toJson(result);

	}

	@Override
	public String isUserValidate(UserInfo user, String userAgent) {
		Map<String, Object> result = new HashMap<String, Object>();
		UserInfo newUser = userInfoMapper.findUserByPhoneNumber(user
				.getPhoneNumber());
		if (newUser == null) {
			result.put("respCode", -1);
			result.put("respInfo", "该号码未注册！");
			return new Gson().toJson(result);
		}
		String password = user.getPassword();
		String pwdAfterBase64Decode = null;
		try {
			pwdAfterBase64Decode = new String(Base64Utils.decode(password));
		} catch (Exception e) {
			log.error("isUserValidate()->验证用户身份时登录密码Base64解密失败！" + e.toString());
			result.put("respCode", -201);
			result.put("respInfo", "用户登录密码Base64解密失败！");
			return new Gson().toJson(result);
		}
		if (pwdAfterBase64Decode.equals(newUser.getPassword())) {
			result.put("respCode", 0);
			result.put("respInfo", "");
			/*
			ArrayList<OperateLog> ao = findCheckInLog(user.getPhoneNumber());
			if (ao == null || ao.size() == 0) {
				OperateLog operate = new OperateLog();
				operate.setOperateTime(new Date());
				operate.setOperateType("CheckIn");
				operate.setPhoneNumber(user.getPhoneNumber());
				String[] info = userAgent.split("_");
				operate.setOs(info[0]);
				operate.setVersion(info[1]);
				operate.setClient(info[2]);
				operateLogMapper.addOperateLog(operate);
				 
				UserCheckIn checkIn = new UserCheckIn();
				checkIn.setPhoneNumber(user.getPhoneNumber());
				checkIn.setCheckInTime(new Date());
				checkIn.setSendScore(0);
				userCheckInMapper.addCheckInRecord(checkIn);
				userInfoMapper.update(newUser);
			}*/
			// 查询余额接口
			String leftMoney = "";
			result.put("PhoneNumber", newUser.getPhoneNumber());
			result.put("Score", newUser.getAllScore());
			result.put("LeftMoney", leftMoney);
			UserPublicInfoReadStatus status = userPublicInfoReadStatusMapper.getUserPublicInfoReadStatus(newUser.getPhoneNumber());
			// 未读公告数
			if (status == null) {
				status = new UserPublicInfoReadStatus();
				status.setMaxReadPublicInfoId(0);
			}
			Integer notReadPublicInfoNumber = publicInfoMapper
					.getNotReadPublicInfoNumber(status.getMaxReadPublicInfoId());
			// 未读私信数
			Integer notReadAnswerFeedBackNumber = answerFeedBackMapper
					.getNotReadAnswerFeedBackNumber(newUser.getPhoneNumber());
			Integer MessageNumber = notReadAnswerFeedBackNumber
					+ notReadPublicInfoNumber;
			result.put("MessageNumber", MessageNumber);
			result.put("DiscountNumber", 0);
			return new Gson().toJson(result);
		} else {
			result.put("respCode", -2);
			result.put("respInfo", "密码错误！如忘记密码，请重置密码！");
			return new Gson().toJson(result);
		}
	}

	public String changePassword(UserInfo user) {
		Map<String, Object> result = new HashMap<String, Object>();
		UserInfo newUser = userInfoMapper.findUserByPhoneNumber(user
				.getPhoneNumber());
		if (newUser == null) {
			result.put("respCode", -1);
			result.put("respInfo", "该号码未注册！");
			return new Gson().toJson(result);
		} else {
			try {
				String password = user.getPassword();
				String pwdAfterBase64Decode = new String(
						Base64Utils.decode(password));
				user.setPassword(pwdAfterBase64Decode);
				userInfoMapper.changeUserPassword(user);
			} catch (NoSuchAlgorithmException e) {
				log.error("changePassword()->验证用户身份时登录密码Base64解密失败！"
						+ e.toString());
				result.put("respCode", -201);
				result.put("respInfo", "用户登录密码Base64解密失败！");
				return new Gson().toJson(result);
			} catch (Exception e) {
				log.error("changePassword()->用户修改密码时更新数据库中数据失败！" + e.toString());
				result.put("respCode", -205);
				result.put("respInfo", "更新数据库数据失败！");
				return new Gson().toJson(result);
			}
			result.put("respCode", 0);
			result.put("respInfo", "");
			return new Gson().toJson(result);
		}
	}

	@Override
	public UserInfo findUserByPhoneNumber(String phoneNumber) {
		return userInfoMapper.findUserByPhoneNumber(phoneNumber);
	}

	@Override
	public String checkIn(String phoneNumber) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer score = getScoreRuleMapper
					.getSendScoreByOperateType("CheckIn");
			UserInfo u = userInfoMapper.findUserByPhoneNumber(phoneNumber);
			if (score != null) {
				u.setAllScore(u.getAllScore() + score);
			}
			try {
				UserCheckIn checkIn = new UserCheckIn();
				checkIn.setPhoneNumber(phoneNumber);
				checkIn.setCheckInTime(new Date());
				checkIn.setSendScore(score);
				userCheckInMapper.addCheckInRecord(checkIn);
			} catch (Exception e) {
				log.error(e.toString());
			}
			userInfoMapper.update(u);
			resultMap.put("respCode", 0);
			resultMap.put("userScore", u.getAllScore());
			String responseText = messageMapper
					.getMessageContentByUse("CheckInSuccess");
			resultMap.put("respInfo", responseText);
			resultMap.put("allowCheckIn", 0);
			return new Gson().toJson(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("respCode", -205);
			resultMap.put("respInfo", "签到更新数据时出现异常!");
			return new Gson().toJson(resultMap);
		}

	}

	private ArrayList<OperateLog> findCheckInLog(String phoneNumber) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("PhoneNumber", phoneNumber);
		String startTime = new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date()) + " 00:00:00";
		String endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date())
				+ " 23:59:59";
		ArrayList<OperateLog> log = null;
		try {
			Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime);
			Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(endTime);
			map.put("Start", startDate);
			map.put("End", endDate);
			log = operateLogMapper.findCheckInLog(map);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return log;
	}

	@Override
	public void updateUser(UserInfo user) {
		userInfoMapper.update(user);
	}

	
	
	
	@Override
	public String getLeftMoney(String phoneNumber) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		PhoneNumber phoneNumberObj=phoneNumberMapper.findId(phoneNumber);
		if(phoneNumberObj==null){
			map.put("respCode", 3);
			map.put("leftMoney", 0);
			map.put("respInfo", "未查询到用户信息！！");
			return new Gson().toJson(map);
		} 
		//?phoneNumber=13395837074&cityCode=573
		 
		String parameter = "phoneNumber=" + phoneNumberObj.getPhoneNumber() + "&cityCode="
				+ phoneNumberObj.getCityCode();
		String result = "";
		java.net.URL httpUrl;
		
		/*try {
		result = URLDecoder.decode(result, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}*/
		/*
		try {
			httpUrl = new URL(getLeftMoneyForwardUrl);
			URLConnection uc = (URLConnection) httpUrl.openConnection();
			uc.setReadTimeout(30000);
			uc.setDoOutput(true);
			uc.setDoInput(true);
			uc.setReadTimeout(30000);
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
			
			JSONObject object = JSONObject.fromObject(result);
			String retCode = object.getString("retCode");

			float leftMoney = 0;

			if ("0".equals(retCode)) {
				float balance = Float.parseFloat(object.getString("balance"));
				float zengjin = Float.parseFloat(object.getString("zengjin"));
				leftMoney = balance - zengjin;
				map.put("respCode", 0);
				map.put("leftMoney", leftMoney);
				map.put("phoneNumber", phoneNumberObj.getPhoneNumber());
				map.put("cityCode", phoneNumberObj.getCityCode());
				map.put("respInfo", "");
			} else {
				map.put("respCode", 2);
				map.put("leftMoney", 0);
				map.put("respInfo", object.getString("errMsg"));
			}
		} catch (Exception e1) {
			map.put("respCode", 2);
			map.put("leftMoney", 0);
			map.put("respInfo", "请求超过30秒未响应");
			log.error("getLeftMoney->查询可用余额请求失败！" + e1.toString());
		}*/
		map.put("respCode", 2);
		//map.put("leftMoney", 0);
		map.put("respInfo", "");
		return new Gson().toJson(map);
	}

	@Override
	public String updateUser2(UserInfo user) {
		Map<String, Object> result = new HashMap<String, Object>();
		String password = user.getPassword();
		try {
			String pwdAfterBase64Decode = new String(
					Base64Utils.decode(password));
			user.setPassword(pwdAfterBase64Decode);
		} catch (Exception e1) {
			log.error("addUser()->验证用户身份时登录密码Base64解密失败！" + e1.toString());
			result.put("respCode", -201);
			result.put("respInfo", "用户登录密码Base64解密失败！");
			return new Gson().toJson(result);
		}
		try {
			Integer score = getScoreRuleMapper
					.getSendScoreByOperateType("UserRegister");
			if (score == null) {
				user.setAllScore(0);
			} else {
				user.setAllScore(score);
			}
			userInfoMapper.updateUser(user);
		} catch (Exception e) {
			log.error("addUser()->向数据库中插入新注册用户信息时出现异常！" + e.toString());
			result.put("respCode", -204);
			result.put("respInfo", "插入数据异常！");
			return new Gson().toJson(result);
		}
		result.put("respCode", 0);
		result.put("respInfo", "");
		return new Gson().toJson(result);
	}

	 
	

	@Override
	public String findByOpenId(String openId) {
		UserInfo user = userInfoMapper.findUserByOpenId(openId);
		return new Gson().toJson(user);
	}

	@Override
	public HashMap<String, Object> bind(String phoneNumber, String openId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserInfo user = userInfoMapper.findUserByOpenId(openId);
		UserInfo user2 = userInfoMapper.findUserByPhoneNumber(phoneNumber);
		if (user != null) {
			map.put("respCode", -1);
			map.put("respInfo", "您的微信号已与其他手机号码绑定！");
		} else if (user2 != null) {
			userInfoMapper.updateOpenIdByPhoneNumber(openId, phoneNumber);
			map.put("respCode", 0);
			map.put("respInfo", "绑定成功！");
		} else if (user == null && user2 == null) {
			UserInfo newUser = new UserInfo();
			newUser.setOpenId(openId);
			newUser.setPhoneNumber(phoneNumber);
			newUser.setUserStatus("未激活");
			newUser.setAllScore(0);
			userInfoMapper.addUser(newUser);
			map.put("respCode", 0);
			map.put("respInfo", "绑定成功！");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> disBind(String phoneNumber, String openId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserInfo user = userInfoMapper.findUserByPhoneNumber(phoneNumber);
		if (user != null) {
			if (user.getOpenId() != null && user.getOpenId().equals(openId)) {
				userInfoMapper.updateOpenIdByPhoneNumber("default", phoneNumber);
				map.put("respCode", 0);
				map.put("respInfo", "绑定成功！");
			} else {
				map.put("respCode", 0);
				map.put("respInfo", "绑定成功！");
			}
		} else {
			map.put("respCode", 0);
			map.put("respInfo", "绑定成功！");
		}

		return map;

	}

	 
	
	
	private String giftUserQcion(String orderId,String phoneNumber,String qqNumber,String qqCount){
		String objId="1681";
		
		if(qqCount.equals("2"))
			  objId="1682";
		if(qqCount.equals("5"))
			  objId="1685";
		
		String parameter = "objid="+objId+"$paymoney="+qqCount+"$payid="+orderId+"$spid=003";
		parameter += "$username="+ qqNumber+"$caller=" + phoneNumber  ;
		
		
		//"objid=1681$paymoney=1$payid=2015052602100003$spid=003$username=121437479$caller=18158171080"
		String result = "";
		java.net.URL httpUrl;
		try {
			httpUrl = new URL(Constant.HBSERVERURL);
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
            e1.printStackTrace();
            return "99";
            
		}
		HashMap<String, Object>  resultMap=CommonUtil.stringToLowerMap(result); 
		return CommonUtil.trim(resultMap.get("result"));
		
	}


	 private String sendMessageNotice(String phoneNumber, String message){ 
			
			String returnString = "";
			try {
				String dataString = "objid=16670$username="
						+ URLEncoder.encode(message, "GB2312")
						+ "$paymoney=$payid=$caller="
						+ phoneNumber; 
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
					return "99";
				}
			 
			  
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "98";
			} 
			HashMap<String, Object>  resultMap=CommonUtil.stringToLowerMap(returnString); 
			return CommonUtil.trim(resultMap.get("result"));
			
		}


}
