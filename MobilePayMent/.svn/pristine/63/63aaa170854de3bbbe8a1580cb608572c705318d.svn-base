package com.rising.mobilepayment.mapper;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component("orderIdHelperMapper")
public interface OrderIdHelperMapper {
	public Integer getMaxOrderId(String time);

	public void update(HashMap<String,Object> map);

	public void add(HashMap<String,Object> map);

	public Integer getNextId();

	public void reNewSequence();
}
