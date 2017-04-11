package com.rising.mobilepayment.service;

import com.rising.mobilepayment.bean.Product;

public interface ProductService {

	public String findProductsByBusId(String BusId);

	public Product findByBusIdAndCost(String string, float parseFloat);

}
