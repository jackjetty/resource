package com.rising.management.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.rising.management.bean.Business;
import com.rising.management.bean.OrderInfo;

public interface OrderInfoDao {

	public Integer getOrderInfoListSize(String phoneNumber, Integer busId,
			String productId, Boolean success, Boolean failed, Date startDate,
			Date endDate, String payReturnCode, String targetNumber,String os,String client);

	public ArrayList<OrderInfo> getOrderInfo(String phoneNumber, Integer busId,
			String productId, Boolean success, Boolean failed, Integer start,
			Integer pageSize, Date startDate, Date endDate,
			String payReturnCode, String targetNumber,String os,String client);

	public ArrayList<Object[]> getOrderInfoByTen(Date startDate, Date endDate,
			Integer payMoneyMin, Integer payMoneyMax);

	public Integer getPaySuccess(String id, Date startDate, Date endDate);

	public Integer getPayFailed(String id, Date startDate, Date endDate);

	public double getAllPayMoney(String id, Date startDate, Date endDate);

	public Integer getAllPayPhoneNumbers(String id, Date startDate, Date endDate);

	public Integer getPaySuccessPhoneNumbers(String id, Date startDate,
			Date endDate);

	public Integer getPayFailedPhoneNumbers(String id, Date startDate,
			Date endDate);

	public HashMap<String, Object> getAllPayMoneyByBusiness(
			ArrayList<Business> ab, Date startDate, Date endDate);

	public Integer getPaySuccess(String id, Date startDate, Date endDate,
			String place);

	public Integer getPayFailed(String id, Date startDate, Date endDate,
			String place);

	public double getAllPayMoney(String id, Date startDate, Date endDate,
			String place);

	public Integer getAllPayPhoneNumbers(String id, Date startDate,
			Date endDate, String place);

	public Integer getPaySuccessPhoneNumbers(String id, Date startDate,
			Date endDate, String place);

	public Integer getPayFailedPhoneNumbers(String id, Date startDate,
			Date endDate, String place);

	public HashMap<String, Object> getAllPayMoneyByBusiness(
			ArrayList<Business> ab, Date startDate, Date endDate, String place);

	public Integer getPaySuccess(String id, Integer busId, Date startDate,
			Date endDate);

	public Integer getPayFailed(String id, Integer busId, Date startDate,
			Date endDate);

	public double getAllPayMoney(String id, Integer busId, Date startDate,
			Date endDate);

	public Integer getAllPayPhoneNumbers(String id, Integer busId,
			Date startDate, Date endDate);

	public Integer getPaySuccessPhoneNumbers(String id, Integer busId,
			Date startDate, Date endDate);

	public Integer getPayFailedPhoneNumbers(String id, Integer busId,
			Date startDate, Date endDate);

	public HashMap<String, Object> getAllPayMoneyByBusinessAll(
			ArrayList<Business> ab, Date startDate, Date endDate);

	public Double getAllPayMoneyByBusId(Integer busId, Date startDate,
			Date endDate);

	public Integer getSuccessTimes(String phoneNumber);

	public Integer getFailTimes(String phoneNumber);

	public double getAll(String productId, Date startDate, Date endDate,
			String place);

	public ArrayList<Date> getPayTime(Date startDate, Date endDate);

	public Integer getPaySuccess2(String productId, Integer busId,
			Date startDate, Date endDate);

	public Integer getPayFailed2(String productId, Integer busId,
			Date startDate, Date endDate);

	public double getAllPayMoney2(String productId, Integer busId,
			Date startDate, Date endDate);

	public Integer getAllPayPhoneNumbers2(String productId, Integer busId,
			Date startDate, Date endDate);

	public Integer getPaySuccessPhoneNumbers2(String productId, Integer busId,
			Date startDate, Date endDate);

	public Integer getPayFailedPhoneNumbers2(String productId, Integer busId,
			Date startDate, Date endDate);

	public HashMap<String, Object> getPaySuccessByYear(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getPayFailedByYear(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getAllPayMoneyByYear(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getAllPayPhoneNumbersByYear(
			String productId, Integer busId, String start, String end);

	public HashMap<String, Object> getPaySuccessPhoneNumbersByYear(
			String productId, Integer busId, String start, String end);

	public HashMap<String, Object> getPayFailedPhoneNumbersByYear(
			String productId, Integer busId, String start, String end);

	public HashMap<String, Object> getPayAllByYear(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getPaySuccessByMonth(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getPayFailedByMonth(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getAllPayMoneyByMonth(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getAllPayPhoneNumbersByMonth(
			String productId, Integer busId, String start, String end);

	public HashMap<String, Object> getPaySuccessPhoneNumbersByMonth(
			String productId, Integer busId, String start, String end);

	public HashMap<String, Object> getPayFailedPhoneNumbersByMonth(
			String productId, Integer busId, String start, String end);

	public HashMap<String, Object> getPayAllByMonth(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getPayFailedPhoneNumbersByDay(
			String productId, Integer busId, String start, String end);

	public HashMap<String, Object> getPaySuccessPhoneNumbersByDay(
			String productId, Integer busId, String start, String end);

	public HashMap<String, Object> getAllPayPhoneNumbersByDay(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getAllPayMoneyByDay(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getPayAllByDay(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getPayFailedByDay(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getPaySuccessByDay(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getPayFailedPhoneNumbersByWeek(
			String productId, Integer busId, String start, String end);

	public HashMap<String, Object> getPaySuccessPhoneNumbersByweek(
			String productId, Integer busId, String start, String end);

	public HashMap<String, Object> getAllPayPhoneNumbersByWeek(
			String productId, Integer busId, String start, String end);

	public HashMap<String, Object> getAllPayMoneyByWeek(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getPayAllByWeek(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getPayFailedByWeek(String productId,
			Integer busId, String start, String end);

	public HashMap<String, Object> getPaySuccessByWeek(String productId,
			Integer busId, String start, String end);

	public String getLastTradeTime(String phoneNumber);

	public HashMap<String, Object> getPaySuccess(Integer busId, Date startDate,
			Date endDate);

	public HashMap<String, Object> getPayFailed(Integer busId, Date startDate,
			Date endDate);

	public HashMap<String, Object> getAllPay(Integer busId, Date startDate,
			Date endDate);

	public HashMap<String, Object> getAllPayMoney(Integer busId,
			Date startDate, Date endDate);

	public HashMap<String, Object> getAllPayPhoneNumbers(Integer busId,
			Date startDate, Date endDate);

	public HashMap<String, Object> getPaySuccessPhoneNumbers(Integer busId,
			Date startDate, Date endDate);

	public HashMap<String, Object> getPayFailedPhoneNumbers(Integer busId,
			Date startDate, Date endDate);

	public HashMap<String, Object> getPaySuccess2(Integer busId,
			Date startDate, Date endDate);

	public Integer getAllPaySuccess2(Date startDate, Date endDate);

	public HashMap<String, Object> getPayFailed2(Integer busId, Date startDate,
			Date endDate);

	public Integer getPayFailed2(Date startDate, Date endDate);

	public HashMap<String, Object> getAllPay2(Integer busId, Date startDate,
			Date endDate);

	public Integer getAllPay2(Date startDate, Date endDate);

	public HashMap<String, Object> getAllPayMoney2(Integer busId,
			Date startDate, Date endDate);

	public double getAllPayMoney2(Date startDate, Date endDate);

	public HashMap<String, Object> getAllPayPhoneNumbers2(Integer busId,
			Date startDate, Date endDate);

	public Integer getAllPhoneNumbers2(Date startDate, Date endDate);

	public HashMap<String, Object> getPaySuccessPhoneNumbers2(Integer busId,
			Date startDate, Date endDate);

	public Integer getAllPaySuccessPhoneNumbers2(Date startDate, Date endDate);

	public HashMap<String, Object> getPayFailedPhoneNumbers2(Integer busId,
			Date startDate, Date endDate);

	public Integer getAllPayFailedPhoneNumbers2(Date startDate, Date endDate);

	public Integer getAppUserInfoSize2(String phoneNumber, String address,
			String startTime, String endTime);

	public HashMap<String, Object> getPaySuccessByPlace(Date startDate,
			Date endDate, String place);

	public ArrayList<Object[]> getAppUserInfo2(String phoneNumber,
			String address, String startTime, String endTime, Integer start,
			Integer pageSize);

	public Integer getPaySuccessAllByPlace(Date startDate, Date endDate,
			String place);

	public HashMap<String, Object> getPayFailedByPlace(Date startDate,
			Date endDate, String place);

	public Integer getPayFailedAllByPlace(Date startDate, Date endDate,
			String place);

	public HashMap<String, Object> getAllPayByPlace(Date startDate,
			Date endDate, String place);

	public Integer getPayAllByPlace(Date startDate, Date endDate, String place);

	public HashMap<String, Object> getAllPayMoneyByPlace(Date startDate,
			Date endDate, String place);

	public double getPayMoneyAllByPlace(Date startDate, Date endDate,
			String place);

	public HashMap<String, Object> getPaySuccessPhoneNumbersByPlace(
			Date startDate, Date endDate, String place);

	public Integer getPaySuccessPhoneNumbersAllByPlace(Date startDate,
			Date endDate, String place);

	public HashMap<String, Object> getPayFailedPhoneNumbersByPlace(
			Date startDate, Date endDate, String place);

	public Integer getPayFailedPhoneNumbersAllByPlace(Date startDate,
			Date endDate, String place);

	public HashMap<String, Object> getAllPayPhoneNumbersByPlace(Date startDate,
			Date endDate, String place);

	public Integer getPhoneNumbersAllByPlace(Date startDate, Date endDate,
			String place);

	public HashMap<String, Object> getPayMoneyByTime(String placeName,
			Date startTime, Date endTime, String toTime);

	public double getPayMoneyByPlaceCode(String placeName, Date startDate,
			Date endDate);

	public double getPayMoneyByPlaceName(String placeName, Date startDate,
			Date endDate);
	
	public double getPayMoneyByPlaceName2(String placeName, Date startDate,
			Date endDate);

	public Integer getSuccessOrderNumbers(String placeName, Date startDate,
			Date endDate);

	public Integer getFailOrderNumbers(String placeName, Date startDate,
			Date endDate);

	public Integer getAllOrderNumbers(String placeName, Date startDate,
			Date endDate);

	public HashMap<String, Object> getSuccessOrderNumberByTime(
			String placeCode, Date startDate, Date endDate, String toTime);

	public HashMap<String, Object> getFailOrderNumberByTime(String placeCode,
			Date startDate, Date endDate, String toTime);

	public HashMap<String, Object> getAllOrderNumberByTime(String placeCode,
			Date startDate, Date endDate, String toTime);

	public ArrayList<Object[]> getfailNumbers(Date startDate, Date endDate,
			String returnCode);

	public Integer getAllfailNumbers(Date startDate, Date endDate);

	public double getAllloseMoney(Date startDate, Date endDate);

	public double getPayMoneyByPhone(String phoneNumber);

	public ArrayList<HashMap<String, Object>> getDetailOrderInfo(
			ArrayList<String> phoneNumbers);

	public ArrayList<HashMap<String, Object>> getDetailOrderInfo2(Date lastDay);

	public Integer getOrderInfoListSize2(String phoneNumber);

	public ArrayList<OrderInfo> getOrderInfo2(String phoneNumber,
			Integer start, Integer pageSize);

	public HashMap<String, Object> getUserOrderInfo(String phoneNumber);

	public Integer getSuccessOrderNumberByTime2(String placeCode,
			Date startDate, Date endDate);

	public Integer getFailOrderNumberByTime2(String placeCode, Date startDate,
			Date endDate);

	public Integer getAllOrderNumberByTime2(String placeCode, Date startDate,
			Date endDate);

	public Integer getSuccessOrderNumberByTime3(Date startDate, Date endDate);

	public Integer getFailOrderNumberByTime3(Date startDate, Date endDate);

	public Integer getAllOrderNumberByTime3(Date startDate, Date endDate);

	public HashMap<String, Object> getPaySuccess3(Integer busId,
			Date startDate, Date endDate);

	public Integer getAllPaySuccess3(Date startDate, Date endDate);

	public HashMap<String, Object> getPayFailed3(Integer busId, Date startDate,
			Date endDate);

	public Integer getPayFailed3(Date startDate, Date endDate);

	public HashMap<String, Object> getAllPay3(Integer busId, Date startDate,
			Date endDate);

	public Integer getAllPay3(Date startDate, Date endDate);

	public HashMap<String, Object> getAllPayMoney3(Integer busId,
			Date startDate, Date endDate);

	public double getAllPayMoney3(Date startDate, Date endDate);

	public HashMap<String, Object> getPaySuccessPhoneNumbers3(Integer busId,
			Date startDate, Date endDate);

	public Integer getAllPaySuccessPhoneNumbers3(Date startDate, Date endDate);

	public HashMap<String, Object> getPayFailedPhoneNumbers3(Integer busId,
			Date startDate, Date endDate);

	public HashMap<String, Object> getAllPayPhoneNumbers3(Integer busId,
			Date startDate, Date endDate);

	public Integer getAllPhoneNumbers3(Date startDate, Date endDate);

	public HashMap<String, Object> getPaySuccessByPlace2(Date startDate,
			Date endDate, String place);

	public Integer getPaySuccessAllByPlace2(Date startDate, Date endDate,
			String place);

	public HashMap<String, Object> getPayFailedByPlace2(Date startDate,
			Date endDate, String place);

	public Integer getPayFailedAllByPlace2(Date startDate, Date endDate,
			String place);

	public HashMap<String, Object> getAllPayByPlace2(Date startDate,
			Date endDate, String place);

	public Integer getPayAllByPlace2(Date startDate, Date endDate, String place);

	public double getPayMoneyAllByPlace2(Date startDate, Date endDate,
			String place);

	public HashMap<String, Object> getPaySuccessPhoneNumbersByPlace2(
			Date startDate, Date endDate, String place);

	public Integer getPaySuccessPhoneNumbersAllByPlace2(Date startDate,
			Date endDate, String place);

	public HashMap<String, Object> getPayFailedPhoneNumbersByPlace2(
			Date startDate, Date endDate, String place);

	public Integer getPayFailedPhoneNumbersAllByPlace2(Date startDate,
			Date endDate, String place);

	public HashMap<String, Object> getAllPayPhoneNumbersByPlace2(
			Date startDate, Date endDate, String place);

	public Integer getPhoneNumbersAllByPlace2(Date startDate, Date endDate,
			String place);

	public HashMap<String, Object> getPayMoneyByTime2(String placeCode,
			Date startDate, Date endDate, String toTime);

	public double getPayMoneyByPlaceCode2(String placeCode, Date startDate,
			Date endDate);

}
