package com.rising.mobilepayment.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.OrderInfo;

@Component("orderInfoMapper")
public interface OrderInfoMapper {

	public ArrayList<OrderInfo> queryOrderInfoList(HashMap<String, Object> map);

	public ArrayList<HashMap<String,Object>> queryOrderInfoList2(HashMap<String, Object> map);
	
	public OrderInfo findById(String OrderId);

	public void add(OrderInfo order);
	
	public void addTelephoneFee(OrderInfo order);

	public void update(OrderInfo order);

	public OrderInfo findByExample(OrderInfo order);

	public Integer getAllSize(HashMap<String, Object> map);

	public ArrayList<OrderInfo> queryPhoneNumberListForThisTargetNumber(HashMap<String,Object> map);

	public Integer getTodayQBPayMoney(HashMap<String, Object> param);

	public Float getThisMonthPayMoney(HashMap<String, Object> param);

	public Integer getThisMonthQBPayMoney(HashMap<String, Object> param);

	public ArrayList<OrderInfo> findQQPerMonth(HashMap<String, Object> param);
	
	public ArrayList<OrderInfo> findFlowPerWeek(OrderInfo order);

	public ArrayList<String> getHashQQPerMonthPhoneNumber();

	public Float getPayMoneyByBusIds(HashMap<String, Object> paraMap);

	public ArrayList<OrderInfo> findLastQBOrder(String string);
	
	public Float getThisMonthPayMoney2(HashMap<String, Object> param);

	public ArrayList<OrderInfo> findQQPerMonthOrderByPhoneNumber(String phoneNumber);

	public ArrayList<OrderInfo> getOrderInfoByPhoneNumber(String string);

	public ArrayList<OrderInfo> getSuccessOrderInfoListByBusId(String busId,String beginPayTime,String endPayTime );

	public ArrayList<OrderInfo> findLastQBOrderByPhoneNumberAndPayMethodId(String phoneNumber,
			String payMethodId);
	
	
	public Integer getLastTimeSuccessOrderNumber(String  phoneNumber  ,String busId,String payStatus,String payTime);
	
	
	public Integer getSuccessOrderNumber(String  phoneNumber  ,String busId,String payMethodId ,String beginPayTime,String endPayTime);
	
	
	public String  getSuccessMaxProductId(String phoneNumber,String beginPayTime,String endPayTime);
	
	
	public OrderInfo getLastSuccessOrderInfoByProductId(String phoneNumber,String productId,String beginPayTime,String endPayTime);
	
	public void updateClient(String monthForm,String payMethodId,String client);
	
	
	
}
