package com.rising.management.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.Product;

public interface ProductService {

	public ArrayList<Product> getProductByBusId(Integer busId);

	public HashMap<String,Object> getProductByStatus();

	public HashMap<String,Object> getProductInfo(Integer busId,Integer cost,Integer pageSize,Integer pageIndex);
	
	public HashMap<String,Object> addProduct(Product p);
	
	public HashMap<String,Object> deleteByIds(String productIds);
	
	public HashMap<String,Object> updateProduct(Product p);
	
	public HashMap<String,Object> findAll();

	public ArrayList<HashMap<String, Object>> getProductSimpleInfo();

}
