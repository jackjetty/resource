package com.rising.general.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.general.bean.Product;
import com.rising.general.mapper.ProductMapper;
import com.rising.general.service.ProductService;

@Service("gProductService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired ProductMapper gProductMapper;

	@Override
	public HashMap<String, Object> getProductInfo(String merchantId) {
		ArrayList<Product> ap = gProductMapper.findProductsByMerchantId(merchantId);
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("respCode", 0);
		map.put("productList", ap);
		return map;
	}

}
