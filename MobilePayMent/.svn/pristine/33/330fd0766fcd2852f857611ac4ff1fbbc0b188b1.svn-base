package com.rising.general.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.rising.general.bean.Order;


@Component("gOrderMapper")
public interface OrderMapper {

	public ArrayList<Order> queryPhoneNumberListForThisTargetNumber(
			HashMap<String, Object> param);

	public void add(Order order);
	
	
	public void place(Order order);

	public Order findByExample(Order order);

	public void update(Order neworder);

	public ArrayList<Order> findByHcOrderIdAndPayMethodId(String hcOrderId,
			String payMethodId);

	public void add2(Order order);
	
	
	public Order findByHcOrderId(HashMap<String, Object> map);
	
	
	public Integer getLastTimeSuccessOrderNumber(String  phoneNumber  ,String busId,String payStatus,String payTime);
    
	public ArrayList<Order> getDateOrderList( HashMap<String, Object> param);
}
