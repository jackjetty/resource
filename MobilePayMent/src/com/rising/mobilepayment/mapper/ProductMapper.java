package com.rising.mobilepayment.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.Product;

@Component("productMapper")
public interface ProductMapper {

	public ArrayList<Product> findProductsByBusId(String BusId);

	public Product findProductById(String ProductId);
	
	public void add(Product product);
	
	public ArrayList<Product> findByBusIdAndCost(HashMap<String, Object> paraMap);
}
