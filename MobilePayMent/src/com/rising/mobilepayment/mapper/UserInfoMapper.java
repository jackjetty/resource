/**
 * UserInfoMapper.java
 * com.rising.mobilepayment.mapper
 * 工程：MobilePayMent
 * 功能： TODO 
 *
 * author    date          time      
 * ─────────────────────────────────────────────
 * chapterless     2013-5-30   上午11:23:54
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.rising.mobilepayment.mapper;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.UserInfo;

@Component("userInfoMapper")
public interface UserInfoMapper {

	public UserInfo findUserByPhoneNumber(String PhoneNumber);

	public void addUser(UserInfo user);

	public void changeUserPassword(UserInfo user);

	public void update(UserInfo user);
	
	public void addScore(UserInfo user);

	public void updateUser(UserInfo user);

	 

	public UserInfo findUserByOpenId(String OpenId);

	public void updateOpenIdByPhoneNumber(String OpenId, String PhoneNumber);

}
