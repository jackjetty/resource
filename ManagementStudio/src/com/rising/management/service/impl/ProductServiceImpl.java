package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.Business;
import com.rising.management.bean.Merchant;
import com.rising.management.bean.Product;
import com.rising.management.dao.BusinessDao;
import com.rising.management.dao.MerchantDao;
import com.rising.management.dao.ProductDao;
import com.rising.management.service.ProductService;
import com.rising.management.vo.ProductVo;
@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	Log log = LogFactory.getLog(ProductServiceImpl.class);
	
	@Autowired
	BusinessDao businessDao;
	
	@Autowired 
	ProductDao productDao;
	
	@Autowired
	MerchantDao merchantDao;
	
	@Override
	public ArrayList<Product> getProductByBusId(Integer busId) {
		
		return productDao.findProductByBusId(busId);
	}
	
	@Override
	public HashMap<String,Object> getProductByStatus() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		StringBuilder productId=new StringBuilder();
		StringBuilder pn=new StringBuilder();
		ArrayList<Product> pd= productDao.getProductByStatus();
		for(Product p:pd){
			productId.append(p.getProductId()+",");
			pn.append(p.getProductDescribe()+",");
		}
		result.put("productId", productId.toString());
		result.put("pn", pn.toString());
		return result;
	}
	
	@Override
	public HashMap<String, Object> getProductInfo(Integer busId,Integer cost,
			Integer pageSize, Integer pageIndex) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		if("".equals(busId)){
			busId = null;
		}
		if("".equals(cost)){
			cost = null;
		}
		ArrayList<Business> ab = businessDao.getBusiness();
		HashMap<Integer,String> hashMap = transform(ab);
		ArrayList<Merchant> m = merchantDao.getMerchant();
		HashMap<String,String> map = transform2(m);
		Integer listSize = productDao.getProductInfoListSize(busId,cost);
		Integer start = (pageIndex-1)*pageSize;
		ArrayList<Product> p = productDao.getProductInfo(busId,cost,start,pageSize);
		ArrayList<ProductVo> arc = new ArrayList<ProductVo>();
		for(Product proc : p){
			arc.add(new ProductVo(proc,hashMap,map));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", arc);
		
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addProduct(Product p) {
		ArrayList<String> gproc = new ArrayList<String>();
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		try{
			ArrayList<Product> proc = productDao.findAll();
			for(int i = 0;i<proc.size();i++){
				gproc.add(proc.get(i).getProductId());
			}
			if(gproc.contains(p.getProductId())){
				resultMap.put("respInfo", "产品代码不能重复");
			}else{
				productDao.addProduct(p);
				resultMap.put("respInfo", "添加新产品信息成功！");
				resultMap.put("respCode", 0);
			}
		}catch(Exception e){
			log.error("添加新产品时出现异常！"+e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加新产品时出现异常！");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> deleteByIds(String productIds) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		String[] idArray = productIds.split(",");
		String lastId = "";
		try{
			for(String idObj : idArray){
				lastId = idObj;
				if(!"".equals(idObj)){
					productDao.deleteById(idObj); 
					resultMap.put("respInfo", "删除产品信息成功!");
				}
			}
		}catch(Exception e){
			log.error("删除id为"+lastId+"的产品信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "删除产品信息时出现异常!");
			
		}

		return resultMap;
	}

	@Override
	public HashMap<String, Object> updateProduct(Product p) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		try{
			productDao.updateProduct(p);
			resultMap.put("respInfo", "更新产品信息成功！");
			resultMap.put("respCode", 0);
		}catch(Exception e){
			log.error("更新产品信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "更新产品信息时出现异常!");
			resultMap.put("respCode", -1);
			
		}
		return resultMap;
	}
	
	private HashMap<Integer,String> transform(ArrayList<Business> ab){
		HashMap<Integer,String> ais = new HashMap<Integer,String>();
		for(Business business : ab){
			ais.put(business.getBusId(), business.getBtype());
		}
		return ais;
		
		
	}
	
	private HashMap<String,String> transform2(ArrayList<Merchant> m){
		HashMap<String,String> mc = new HashMap<String,String>();
		for(Merchant merchant:m){
			mc.put(merchant.getMerchantId(), merchant.getMerchantName());
		}
		return mc;
	}
	
	@Override
	public HashMap<String, Object> findAll() {
		HashMap<String,Object> p = new HashMap<String,Object>();
		ArrayList<Product> pp = productDao.findAllByStatus();
		p.put("rows", pp);
		return p;
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> getProductSimpleInfo() {
		ArrayList<HashMap<String, Object>> AHSO = productDao.getProductMap2();
		return AHSO;
	}
}





