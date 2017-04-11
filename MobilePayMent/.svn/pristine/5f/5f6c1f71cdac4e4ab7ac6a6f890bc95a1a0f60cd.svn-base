/**
 * UserService.java
 * com.rising.mobilepayment.service
 * 工程：MobilePayMent
 * 功能： 管理用户的service层接口
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * Administrator     2013-5-24   上午9:49:37
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.rising.mobilepayment.service;

import java.util.HashMap;

import com.rising.mobilepayment.bean.UserInfo;

public interface UserInfoService {

	public String addUser(UserInfo user);

	public String isUserValidate(UserInfo user, String userAgent);

	public String changePassword(UserInfo user);

	public UserInfo findUserByPhoneNumber(String phoneNumber);

	public String checkIn(String phoneNumber);

	public void updateUser(UserInfo user);

	public String getLeftMoney(String phoneNumber);

	public String updateUser2(UserInfo user);

  

	public String findByOpenId(String openId);

	public HashMap<String, Object> bind(String phoneNumber, String openId);

	public HashMap<String, Object> disBind(String phoneNumber, String openId);
 

}
