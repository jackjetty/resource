package com.rising.management.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.rising.management.bean.UserCheckin;

public interface UserCheckinDao {
	public Integer getUserCheckinSize(String phoneNumber, String checkinTime);

	public ArrayList<UserCheckin> getUserCheckin(String phoneNumber,
			String checkinTime, Integer pageSize, Integer start);

	public HashMap<String, Object> getUserCheckinNumbers(String placeCode,
			Date startDate, Date endDate, String toTime);

	public Integer getUserCheckinNumbers(String placeName, Date startDate,
			Date endDate);

	public ArrayList<HashMap<String, Object>> getUserCheckinInfo(
			ArrayList<String> phoneNumbers);

	public Integer getUserCheckinSize2(String phoneNumber);

	public ArrayList<UserCheckin> getUserCheckin2(String phoneNumber,
			Integer pageSize, Integer start);

}
