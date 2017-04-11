package com.rising.mobilepayment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.Product;
import com.rising.mobilepayment.mapper.ProductMapper;
import com.rising.mobilepayment.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	Log log = LogFactory.getLog(ProductServiceImpl.class);
	@Autowired
	ProductMapper productMapper;

	@Override
	public String findProductsByBusId(String BusId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<Product> ap;
		try {
			ap = productMapper.findProductsByBusId(BusId);
			map.put("respCode", 0);
			map.put("respInfo", "");
			map.put("Products", ap);
		} catch (Exception e) {
			log.error("findProductsByBusId()->查询详细产品时出错！" + e.toString());
			map.put("respCode", -206);
			map.put("respInfo", "数据库查询出现异常！");
		}
		return new Gson().toJson(map);

	}

	@Override
	public Product findByBusIdAndCost(String busId, float cost) {
		HashMap<String,Object> paraMap = new HashMap<String, Object>();
		paraMap.put("busId", Integer.parseInt(busId));
		paraMap.put("cost", cost);
		ArrayList<Product> ap = productMapper.findByBusIdAndCost(paraMap);
		if(ap != null && ap.size()>0 ){
			return ap.get(0);
		}else{
			return null;
		}
	}

}
