package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.Business;
import com.rising.management.bean.Merchant;
import com.rising.management.dao.BusinessDao;
import com.rising.management.dao.MerchantDao;
import com.rising.management.service.BusinessService;
import com.rising.management.vo.BusinessVo;

@Service("businessService")
public class BusinessServiceImpl implements BusinessService {
	Log log = LogFactory.getLog(ProductServiceImpl.class);
	@Autowired BusinessDao businessDao;
	@Autowired MerchantDao merchantDao;

	@Override
	public ArrayList<Business> getBusiness() {
		return businessDao.getBusiness();
	}

	@Override
	public HashMap<String, Object> getBusAndBtype() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<Business> bs=businessDao.getBusiness();
		StringBuilder busId=new StringBuilder();
		StringBuilder Btype=new StringBuilder();
		for(Business b:bs){
			busId.append(b.getBusId()+",");
			Btype.append(b.getBtype()+",");
		}
		result.put("busId", busId.toString());
		result.put("Btype", Btype.toString());
		return result;
	}

	@Override
	public HashMap<String, Object> getBusinessInfo(Integer busId,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<Merchant> m = merchantDao.getMerchant();
		HashMap<String,String> map = transform(m);
		Integer listSize = businessDao.getBusinessInfoListSize(busId);
		Integer start = (pageIndex-1)*pageSize;
		ArrayList<Business> bs=businessDao.getBusinessInfo(busId,start,pageSize);
		ArrayList<BusinessVo>bv=new ArrayList<BusinessVo>();
		for(Business b:bs){
			bv.add(new BusinessVo(b,map));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", bv);
		
		return resultMap;
	}

	@Override
	public HashMap<String, Object> updateBusiness(Business b) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		try{
			businessDao.updateProduct(b);
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

	@Override
	public HashMap<String, Object> removeBusiness(String busIds) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		String [] busIdArray =busIds.split(",");
		String lastId = "";
		try{
			for(String idObj : busIdArray){
				lastId=idObj;
				if(!"".equals(idObj)){
					Integer busId = Integer.parseInt(idObj);
					businessDao.deleteById(busId); 
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
	public HashMap<String, Object> addBusiness(Business b) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		try {
		ArrayList<Business> bs =businessDao.findAll();
		ArrayList<Integer> bId = new ArrayList<Integer>();
		for(int i=0;i<bs.size();i++){
			bId.add(bs.get(i).getBusId());
		}
		if(bId.contains(b.getBusId())){
			resultMap.put("respInfo", "产品编号不能重复");
		}else{
			businessDao.addBusiness(b);
			resultMap.put("respInfo", "添加新产品信息成功！");
			resultMap.put("respCode", 0);
		}
		} catch (Exception e) {
			log.error("添加新产品时出现异常！"+e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加新产品时出现异常！");
		}
		return resultMap;
	}
	private HashMap<String,String> transform(ArrayList<Merchant> m){
		HashMap<String,String> mc = new HashMap<String,String>();
		for(Merchant merchant:m){
			mc.put(merchant.getMerchantId(), merchant.getMerchantName());
		}
		return mc;
	}
	

	

}
