package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.AdvertisingWay;
import com.rising.management.dao.AdvertisingWayDao;
import com.rising.management.service.AdvertisingWayService;
@Service("advertisingWayService")
public class AdvertisingWayServiceImpl implements AdvertisingWayService {
	Log log = LogFactory.getLog(AdvertisingWayServiceImpl.class);
	@Autowired
	AdvertisingWayDao advertisingWayDao;
	@Override
	public HashMap<String, Object> getAdvertisingWay(String path,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("null".equals(path)){
			path=null;
		}
			Integer listSize = advertisingWayDao.getAdvertisingWayListSize(path);
			Integer start = (pageIndex-1)*pageSize;
			ArrayList<AdvertisingWay> sr =advertisingWayDao.getAdvertisingWay(path,start,pageSize);
			resultMap.put("listSize", listSize);
			resultMap.put("rows",sr);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addAdvertisingWay(AdvertisingWay aw) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			advertisingWayDao.addAdvertisingWay(aw);
			result.put("respCode", 0);
			result.put("respInfo", "添加宣传渠道成功!");
		} catch (Exception e) {
			log.error("添加宣传渠道时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加宣传渠道时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> updateAdvertisingWay(AdvertisingWay aw) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			advertisingWayDao.updateAdvertisingWay(aw);
			result.put("respCode", 0);
			result.put("respInfo", "宣传渠道修改成功!");
		} catch (Exception e) {
			log.error("修改宣传渠道时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "修改宣传渠道时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> removeAdvertisingWay(String ids) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String[] idArr = ids.split(",");
		String lastId = "";
		try {
			for (String id : idArr) {
				lastId = id;
				if (!"".equals(id)) {
					advertisingWayDao.deleteById(Integer.parseInt(id));
				}
			}
			result.put("respInfo", "删除宣传渠道成功!");
		} catch (Exception e) {
			log.error("删除id为" + lastId + "的宣传渠道时出现异常!" + e.toString());
			e.printStackTrace();
			result.put("respInfo", "删除宣传渠道时出现异常!");
		}
		return result;
		}

	@Override
	public ArrayList<AdvertisingWay> getAdvertisingDetail() {
		return advertisingWayDao.getAdvertisingDetail();
		
	}

}
