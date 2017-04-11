package com.rising.management.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.UserInfo;
import com.rising.management.vo.UserInfoVo;

import java.util.Date;

public interface UserInfoDao {

	public Integer getAllUserNumber();

	public Integer getNewRegisterNum(String placeName, Date startTime,
			Date endTime);

	public HashMap<String, Object> getNewRegisterNum2(String placeName,
			Date startTime, Date endTime);

	public Integer getNewResNum(Date startTime, Date endTime);

	public float getMaxScore();

	public Integer getUserNumberByScoreRound(float min, float max);

	public Integer getAllUserNumber(String placeName);

	public HashMap<String, Object> getAllUserNumber2(String placeName);

	public Integer getAll();

	public ArrayList<UserInfo> getUserByScoreRound(Integer scoreMin,
			Integer scoreMax);

	public UserInfo findUser(String phoneNumber, String place);

	public String getUserPlaceByNumber(String number);

	public Integer getNewUserListSize(String phoneNum, String regDate,
			String lastTraDate, Date startTime, Date endTime, String p);

	public ArrayList<Object[]> getPhoneAndTime(String phoneNum, String regDate,
			String lastTraDate, Integer start, Integer pageSize,
			Date startDate, Date endDate, String p);

	public HashMap<String, Object> getMonthByUserInfo(String placeName,
			Date startTime, Date endTime, String toTime);

	public Integer getSumUserInfo(String placeCode, Date startTime);

	public HashMap<String, String> getUserPlace();

	public Integer getCreditsListSize(String phoneNum, String regTime,
			String lastTraTime, Integer min, Integer max);

	public ArrayList<Object[]> getPhoneAndTime(String phoneNum, String regTime,
			String lastTraTime, Integer start, Integer pageSize, Integer min,
			Integer max);

	public ArrayList<HashMap<String, Object>> findLostUser(String phoneNumber);

	public ArrayList<HashMap<String, Object>> findLostUserInfo(
			ArrayList<String> phoneNumbers);

	public ArrayList<HashMap<String, Object>> findLostUser2();

	public UserInfoVo findByPhoneNumber(String phoneNumber);

}
