 
package com.rising.mobilepayment.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.OperateLog;
import com.rising.mobilepayment.bean.OrderInfo;
import com.rising.mobilepayment.bean.ProductRebate;
import com.rising.mobilepayment.bean.QQPerMonthRecord;
import com.rising.mobilepayment.bean.UserCheckIn;
import com.rising.mobilepayment.bean.UserInfo;
import com.rising.mobilepayment.bean.UserPublicInfoReadStatus;
import com.rising.mobilepayment.commom.Base64Utils;
import com.rising.mobilepayment.commom.CommonUtil;
import com.rising.mobilepayment.mapper.AnswerFeedBackMapper;
import com.rising.mobilepayment.mapper.GetScoreRuleMapper;
import com.rising.mobilepayment.mapper.MessageMapper;
import com.rising.mobilepayment.mapper.OperateLogMapper;
import com.rising.mobilepayment.mapper.OrderInfoMapper;
import com.rising.mobilepayment.mapper.ProductRebateMapper;
import com.rising.mobilepayment.mapper.PublicInfoMapper;
import com.rising.mobilepayment.mapper.QQPerMonthRecordMapper;
import com.rising.mobilepayment.mapper.UserCheckInMapper;
import com.rising.mobilepayment.mapper.UserInfoMapper;
import com.rising.mobilepayment.mapper.UserPublicInfoReadStatusMapper;
import com.rising.mobilepayment.service.LotteryService;
import com.rising.mobilepayment.service.OrderInfoService;
import com.rising.mobilepayment.service.UserInfoService;
import com.rising.mobilepayment.service.UserRebateService;

@Service("userRebateService")
public class UserRebateServiceImpl implements UserRebateService {
	Log log = LogFactory.getLog(UserRebateServiceImpl.class);

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	ProductRebateMapper productRebateMapper;

	 
	@Autowired
	OperateLogMapper operateLogMapper;

	 

	@Autowired
	private OrderInfoMapper orderInfoMapper;

	 

	@Autowired
	QQPerMonthRecordMapper qQPerMonthRecordMapper;

	 
 
	 

	@Override
	public HashMap<String, Object> getRebateList(String phoneNumber, String busId,String targetNumber) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		phoneNumber=CommonUtil.trim(phoneNumber);
		busId=CommonUtil.trim(busId);
		targetNumber=CommonUtil.trim(targetNumber); 
		List<ProductRebate> productRebateList=new ArrayList<ProductRebate>(); 
		String rebateType="NORMAL";
		
		/*
		int dealSum=0;
		Date curDate=new Date();
		Date beginDate=CommonUtil.getDateByForm("2015-12-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date endDate=CommonUtil.getDateByForm("2016-01-30 00:00:00", "yyyy-MM-dd HH:mm:ss");
		
		
		if( curDate.after(beginDate)&&curDate.before(endDate)&&(!phoneNumber.equals(""))&& busId.equalsIgnoreCase("100")){
			dealSum=orderInfoMapper.getSuccessOrderNumber(phoneNumber, "100", "1", CommonUtil.doDateForm( beginDate, "yyyy-MM-dd HH:mm:ss"), CommonUtil.doDateForm( endDate, "yyyy-MM-dd HH:mm:ss"));
			dealSum+=orderInfoMapper.getSuccessOrderNumber(phoneNumber, "100", "2", CommonUtil.doDateForm( beginDate, "yyyy-MM-dd HH:mm:ss"), CommonUtil.doDateForm( endDate, "yyyy-MM-dd HH:mm:ss"));
			dealSum+=orderInfoMapper.getSuccessOrderNumber(phoneNumber, "100", "9", CommonUtil.doDateForm( beginDate, "yyyy-MM-dd HH:mm:ss"), CommonUtil.doDateForm( endDate, "yyyy-MM-dd HH:mm:ss"));
			if(dealSum==0)
				rebateType="SALE9";
		}*/
		
		Calendar calendar = Calendar.getInstance();
		if(busId.equalsIgnoreCase("103") ){
			rebateType="SALE93";
		}
		
		
		try {
			
			productRebateList=productRebateMapper.getProductRebateList(rebateType, busId);
			
			 
			 
		} catch (Exception e) {
			log.error("addUser()->查询"+rebateType+"打折信息时出现异常！" + e.toString());
			result.put("respCode", -204);
			result.put("respInfo", "查询数据异常！");
			return  result; 
		}
		result.put("respCode", 0);
		result.put("respInfo", "");
		result.put("rebateType", rebateType);
		result.put("productRebateList", productRebateList);
		return  result;

	}

	 


}
