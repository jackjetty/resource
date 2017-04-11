package com.rising.management.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.vo.OrderInfoVo;
import com.rising.management.vo.RandomDraw;

public interface OrderInfoService {

	public HashMap<String, Object> getOrderInfo(Integer pageSize,
			Integer pageIndex, String phoneNumber, Integer busId,
			String productId, Boolean success, Boolean failed,
			String startTime, String endTime, String payReturnCode,
			String targetNumber,String os,String client);

	public Integer getOrderInfoSize(String phoneNumber, Integer busId,
			String productId, Boolean success, Boolean failed,
			String startTime, String endTime, String payReturnCode,
			String targetNumber,String os,String client);

	public ArrayList<RandomDraw> getOrderInfoByTen(Integer prizeId,
			String startTime, String endTime, Integer payMoneyMin,
			Integer payMoneyMax, Integer scoreMin, Integer scoreMax,
			String place);

	public HashMap<String, Object> getStatistics(String startTime,
			String endTime);

	public ArrayList<OrderInfoVo> getOrderInfoVo(Integer pageSize,
			Integer pageIndex, String phoneNumber, Integer busId,
			String productId, Boolean success, Boolean failed,
			String startTime, String endTime, String payReturnCode,
			String targetNumber,String os,String client);

	public ArrayList<HashMap<String, Object>> getStatisticsAllByWays(Integer busId,
			String startTime, String endTime);


	public ArrayList<String> getProductIds();

	public ArrayList<String> getProductIds(Integer busId);
	
	public HashMap<String, Object> getStatisticsAll(String startTime,
			String endTime, String place,Integer busId);

	public ArrayList<HashMap<String, Object>> getStatisticsAllByHighCharts(
			String productId, String ways, Integer busId, String startTime,
			String endTime);

	public ArrayList<HashMap<String, Object>> getStatisticsAll2(Integer busId,
			String startTime, String endTime);

	public ArrayList<HashMap<String, Object>> getStatisticsAll(Integer busId,
			String startTime, String endTime);

	public ArrayList<HashMap<String, Object>> getStatisticsInfoByPlace(
			String startTime, String endTime, String place);

	public HashMap<String, Object> getStatisticsByRecharge(String placeName,
			String startTime, String endTime, String toTime);

	public HashMap<String, Object> getUserActivity(String placeName,
			String startTime, String endTime, String toTime);

	public ArrayList<HashMap<String, String>> getStatisticsByCode(String startTime,String endTime,
			String returnCode);

	public HashMap<String, Object> getOrderInfoByPhoneNumber(Integer pageSize,
			Integer pageIndex, String phoneNumber);

	public ArrayList<HashMap<String, Object>> getStatisticsAllByWays2(
			Integer busId, String startTime, String endTime);

	public ArrayList<HashMap<String, Object>> getStatisticsInfoByPlace2(
			String startTime, String endTime, String place);

	public HashMap<String, Object> getStatisticsByRecharge2(String placeName,
			String startTime, String endTime, String toTime);

}
