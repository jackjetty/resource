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
package com.rising.general.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.rising.general.bean.UserInfo;

@Component("gUserInfoMapper")
public interface UserInfoMapper {

	public  UserInfo  findUserByPhoneNumber(String PhoneNumber);

	public void addUser(UserInfo user);

}
