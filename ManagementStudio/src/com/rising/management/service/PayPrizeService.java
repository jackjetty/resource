package com.rising.management.service;

import java.util.HashMap;

import com.rising.management.bean.PayPrize;

public interface PayPrizeService {

	public HashMap<String, Object> addPayPrize(PayPrize payPrize);

	public HashMap<String, Object> updatePayPrize(PayPrize payPrize);

	public HashMap<String, Object> removePayPrize(String ids);

	public HashMap<String, Object> getPayPrize(String productId,
			Integer pageSize, Integer pageIndex);

	public HashMap<String, Object> changeStatus(Integer id);

}
