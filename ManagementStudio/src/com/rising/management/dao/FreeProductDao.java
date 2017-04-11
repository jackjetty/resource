package com.rising.management.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.FreeProduct;

public interface FreeProductDao {

	public Integer getFreeProductListSize(Integer busId, Integer cost);

	public ArrayList<FreeProduct> getFreeProduct(Integer busId, Integer cost,
			Integer start, Integer pageSize);

	public void addFreeProduct(FreeProduct fp);

	public void updateFreeProduct(FreeProduct fp);

	public void deleteById(String id);

	public ArrayList<FreeProduct> findAll();

	public HashMap<String, Object> getFreeProductMap();

}
