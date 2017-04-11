package com.rising.mobilepayment.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.UserBindInfo;
import com.rising.mobilepayment.mapper.UserBindInfoMapper;
import com.rising.mobilepayment.service.UserBindInfoService;

@Service
public class UserBindInfoServiceImpl implements UserBindInfoService {

	@Autowired
	UserBindInfoMapper userBindInfoMapper;

	@Override
	public String bindOperate(HashMap<String, String> map) {
		String checkResult = map.get("checkResult");
		if ("pass".equals(checkResult.toLowerCase())) {
			ArrayList<UserBindInfo> aub = userBindInfoMapper.findValid(map);
			if (aub != null && aub.size() > 0) {
				UserBindInfo ubi = new UserBindInfo();
				ubi.setRegisterPhoneNumber(map.get("registerPhoneNumber"));
				ubi.setCheckResult(checkResult);
				ubi.setBindNumber(map.get("bindNumber"));
				ubi.setBindTime(new Date());
				ubi.setBindResult("失败");
				ubi.setValid("无效");
				try {
					ubi.setCheckTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(map.get("checkTime")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				ubi.setRemark("号码进行二次绑定");
				userBindInfoMapper.addRecord(ubi);
			} else {
				UserBindInfo ubi = new UserBindInfo();
				ubi.setRegisterPhoneNumber(map.get("registerPhoneNumber"));
				ubi.setCheckResult(checkResult);
				ubi.setBindNumber(map.get("bindNumber"));
				ubi.setBindTime(new Date());
				ubi.setBindResult("成功");
				ubi.setValid("有效");
				ubi.setRemark("绑定成功");
				try {
					ubi.setCheckTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(map.get("checkTime")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				userBindInfoMapper.addRecord(ubi);
			}
		} else {
			UserBindInfo ubi = new UserBindInfo();
			ubi.setRegisterPhoneNumber(map.get("registerPhoneNumber"));
			ubi.setCheckResult(checkResult);
			ubi.setBindNumber(map.get("bindNumber"));
			ubi.setBindTime(new Date());
			ubi.setBindResult("失败");
			ubi.setValid("无效");
			try {
				ubi.setCheckTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(map.get("checkTime")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ubi.setRemark("身份核对失败");
			userBindInfoMapper.addRecord(ubi);
		}
		
		return "{ok}";
	}

}
