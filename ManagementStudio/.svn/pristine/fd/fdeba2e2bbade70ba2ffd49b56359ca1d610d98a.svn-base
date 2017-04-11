package com.rising.management.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.PayLimit;

public interface PayLimitDao {


	public Integer getPayLimitListSize(String limitType);

	public ArrayList<PayLimit> getPayLimit(String limitType, Integer start,
			Integer pageSize);


	public void deleteById(Integer limitId);

	public void updatePayLimit(PayLimit p);

	public void addpayLimit(PayLimit p);

	public HashMap<String,Object> getLimitIdandLimitName();
	public ArrayList<PayLimit> getPayLimit();
}
