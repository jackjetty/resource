package com.rising.management.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.rising.management.bean.QQPermonthRecode;

public interface QQPermonthRecodeDao {

	public Integer getQQPermonthRecodeListSize(String phoneNumber, Date startDate,
			Date endDate);

	public ArrayList<QQPermonthRecode> getQQPermonthRecode(String phoneNumber,
			Date startDate, Date endDate, Integer pageSize, Integer start);

	public HashMap<String, Object> getAllPayPhoneNumbers(Date startDate,
			Date endDate);

	public HashMap<String, Object> getQQNumbers(Date startDate, Date endDate);

	public HashMap<String, Object> getAllSendScore(Date startDate, Date endDate);

	public HashMap<String, Object> getAllPayMoney(Date startDate, Date endDate);

	public Integer getPayPhoneNumbersAll(Date startDate, Date endDate);

	public Integer getQQNumbersAll(Date startDate, Date endDate);

	public Long getSendScoreAll(Date startDate, Date endDate);

	public double getPayMoneyAll(Date startDate, Date endDate);

	public Integer getAllFailNumbers(Date startDate, Date endDate);

	public Integer getAllSuccessNumbers(Date startDate, Date endDate);


	public HashMap<String, Object> getAllPay(Date startDate, Date endDate);

	public Integer getAllPaySuccess(Date startDate, Date endDate);

	public Integer getAllPayFail(Date startDate, Date endDate);

	public Integer getPaySuccess(Date startDate, Date endDate, String place);

	public Integer getPayFail(Date startDate, Date endDate, String place);

	public Integer getAllPay(Date startDate, Date endDate, String place);

	public double getPayMoneyByPalce(Date startDate, Date endDate, String place);

	public Integer getSuccessNumbers(Date startDate, Date endDate, String place);

	public Integer getFailNumbers(Date startDate, Date endDate, String place);

	public Integer getAllNumbers(Date startDate, Date endDate, String place);

	public double getPayMoneyByTime(Date startDate, Date endDate,
			String placeName);

	public HashMap<String, Object> getPayMoneyByTimeRound(String placeCode,
			Date startDate, Date endDate, String toTime);

	public Integer getUserQQPermonthRecodeListSize(String phoneNumber);

	public ArrayList<QQPermonthRecode> getUserQQPermonthRecode(
			String phoneNumber, Integer pageSize, Integer start);

	public HashMap<String, Integer> getPayPhoneNumber(Date startDate,
			Date endDate);

	public HashMap<String, Integer> getAllPayNumbers(Date startDate,
			Date endDate);

	public HashMap<String, Object> getPaySuccess(Date startDate, Date endDate);

	public HashMap<String, Object> getPayFailed(Date startDate, Date endDate);

	public HashMap<String, Object> getPaySuccessPhoneNumbers(Date startDate,
			Date endDate);

	public HashMap<String, Object> getPayFailedPhoneNumbers(Date startDate,
			Date endDate);

	public double getAllPayMoneyByBusId(Date startDate, Date endDate);

	public double getPayMoneyAll2(Date startDate, Date endDate);

	public HashMap<String, Integer> getPayPhoneNumber2(Date startDate, Date endDate);

	public HashMap<String, Integer> getAllPayNumbers2(Date startDate, Date endDate);

}
