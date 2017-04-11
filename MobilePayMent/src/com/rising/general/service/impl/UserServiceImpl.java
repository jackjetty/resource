package com.rising.general.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.general.bean.UserInfo;
import com.rising.general.mapper.UserInfoMapper;
import com.rising.general.service.UserService;

@Service("gUserService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserInfoMapper gUserInfoMapper;

	@Override
	public HashMap<String, Object> registerUser(String phonenumber) {
		UserInfo  userInfo = gUserInfoMapper.findUserByPhoneNumber(phonenumber);
		HashMap<String,Object> result = new HashMap<String, Object>();
		if(userInfo!=null  ){
			result.put("respCode",-4);
			result.put("respInfo", "该用户已注册！");
		}else{
			UserInfo user = new UserInfo();
			user.setAddress("");
			user.setPhoneNumber(phonenumber);
			user.setAllScore(0);
			user.setRegisterTime(new Date());
			gUserInfoMapper.addUser(user);
			result.put("respCode",0);
			result.put("respInfo", "用户注册成功！");
		}
		return result;
	}
}
