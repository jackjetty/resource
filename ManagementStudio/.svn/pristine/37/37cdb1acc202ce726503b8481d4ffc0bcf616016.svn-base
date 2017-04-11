package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.Merchant;
import com.rising.management.dao.MerchantDao;
import com.rising.management.service.MerchantService;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService{
	Log log = LogFactory.getLog(MerchantServiceImpl.class);
	@Autowired MerchantDao merchantDao;
	
	@Override
	public HashMap<String, Object> getMerchant() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<Merchant> mc = merchantDao.getMerchant();
		StringBuilder merchantId=new StringBuilder();
		StringBuilder merchantName=new StringBuilder();
		for(Merchant m:mc){
			merchantId.append(m.getMerchantId()+",");
			merchantName.append(m.getMerchantName()+",");
		}
		result.put("merchantId", merchantId.toString());
		result.put("merchantName", merchantName.toString());
		
		return result;
	}

	@Override
	public HashMap<String, Object> getMerchantInfo(Integer pageSize,
			Integer pageIndex) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		Integer listSize = merchantDao.getMerchantInfoListSize();
		Integer start = (pageIndex - 1)*pageSize;
		ArrayList<Merchant> m = merchantDao.getMerchantInfo(start,pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", m);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addMerchant(Merchant m) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		try{
			ArrayList<Merchant> mm = merchantDao.findAll();
			ArrayList<String> gmm = new ArrayList<String>();
			for(int i = 0;i<mm.size();i++){
				gmm.add(mm.get(i).getMerchantId());
			}
			if(gmm.contains(m.getMerchantId())){
				resultMap.put("respInfo", "商户编号不能重复");
			}else{
				merchantDao.addMerchant(m);
				resultMap.put("respInfo", "添加商户信息成功！");
				resultMap.put("respCode", 0);
			}
			
			
		}catch(Exception e){
			log.error("添加商户信息时出现异常！"+e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加新商户信息时出现异常！");
			
		}
		return resultMap;

	}

	@Override
	public HashMap<String, Object> updateMerchant(Merchant m) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		try{
			merchantDao.updateMerchant(m);
			resultMap.put("respInfo", "更新商户信息成功！");
			resultMap.put("respCode", 0);
		}catch(Exception e){
			log.error("更新产品信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "更新商户信息时出现异常!");
			resultMap.put("respCode", -1);
			
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> deleteByIds(String merchantIds) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		String[] idArray = merchantIds.split(",");
		String lastId = "";
		try{
			for(String idObj : idArray){
				lastId = idObj;
				if(!"".equals(idObj)){
					merchantDao.deleteById(idObj); 
					resultMap.put("respInfo", "删除商户信息成功!");
				}
			}
		}catch(Exception e){
			log.error("删除id为"+lastId+"的商户信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "删除商户信息时出现异常!");
			
		}

		return resultMap;
	}

	@Override
	public ArrayList<Merchant> getMerchantType() {
		return merchantDao.getMerchantType();
	}

}









