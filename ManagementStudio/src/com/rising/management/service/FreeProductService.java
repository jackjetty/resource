package com.rising.management.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.FreeProduct;

public interface FreeProductService {

	public HashMap<String, Object> getFreeProduct(Integer busId, Integer cost,
			Integer pageSize, Integer pageIndex);

	public HashMap<String, Object> addFreeProduct(FreeProduct fp);

	public HashMap<String, Object> updateFreeProduct(FreeProduct fp);

	public HashMap<String, Object> removeFreeProduct(String productIds);

	public ArrayList<HashMap<String, Object>> getFreeProductMap();

}
