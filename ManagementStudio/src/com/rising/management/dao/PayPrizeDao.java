package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.PayPrize;

public interface PayPrizeDao {

	public void addPayPrize(PayPrize payPrize);

	public void updatePayPrize(PayPrize payPrize);

	public void removePayPrize(ArrayList<Integer> ai);

	public Integer getPayPrizeListSize(String productId);

	public ArrayList<PayPrize> getPayPrize(String productId, Integer start,
			Integer pageSize);

	public PayPrize findById(Integer id);

	public ArrayList<PayPrize> findByProductId(String productId);

}
