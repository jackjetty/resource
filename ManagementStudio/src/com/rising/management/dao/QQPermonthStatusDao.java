package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.QQPerMonthStatus;

public interface QQPermonthStatusDao {

	public Integer getQQPermonthStatusListSize(String phoneNumber, String status);

	public ArrayList<QQPerMonthStatus> getQQPermonthStatus(String phoneNumber,
			String status, Integer pageSize, Integer start);

}
