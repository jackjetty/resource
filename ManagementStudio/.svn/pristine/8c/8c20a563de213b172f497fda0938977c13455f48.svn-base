package com.rising.management.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.Product;

public interface ProductDao {

	public ArrayList<Product> findProductByBusId(Integer busId);
	
	public ArrayList<String> getProductIds();

	public ArrayList<String> getProductIds(Integer busId);

	public HashMap<String, Object> getProductMap();
	
	public ArrayList<Product> getProductByStatus();

	public  ArrayList<Product> findAll();
	
	public Integer getProductInfoListSize(Integer busId,Integer cost);
	
	public ArrayList<Product> getProductInfo(Integer busId,Integer cost,Integer start,Integer pageSize);
	
	public void addProduct(Product p);
	
	public void deleteById(String productId);
	
	public void updateProduct(Product p);

	public ArrayList<Product> findAllByStatus();
	
	public ArrayList<String> getProductDescribe(String productId,Integer busId);
	
	public Integer getNumber(Integer busId);

	public ArrayList<String> getProductDescribe(String id);

	public Integer getNumberBystatus();

	public String getProductDescribe2(String productId);
	
	public ArrayList<String> getQQPermonthProductDescribe();

	public ArrayList<String> getProductDescribeByBusId(Integer busId);

	public ArrayList<HashMap<String, Object>> getProductMap2();

	

}
