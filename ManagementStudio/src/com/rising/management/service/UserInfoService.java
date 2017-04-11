package com.rising.management.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.rising.management.vo.UserInfoVo;

public interface UserInfoService {

	public ArrayList<HashMap<String, Object>> doUserScoreStatistics();

	public HashMap<String, Object> getNewUser(String phoneNumber,
			String registerTime, String lastTradeTime, Integer pageSize,
			Integer pageIndex, String startTime, String endTime, String p);

	public HashMap<String, Object> getMonthByUserInfo(String placeName,
			Date startTime, Date endTime, String toTime);

	public Integer getSumUserInfo(String placeCode, Date startTime);

	public HashMap<String, Object> getCreditsNumbers(String phoneNumber,
			String registerTime, String lastTradeTime, Integer pageSize,
			Integer pageIndex, Integer min, Integer max);

	public UserInfoVo getUserInfo(String phoneNumber);
}
