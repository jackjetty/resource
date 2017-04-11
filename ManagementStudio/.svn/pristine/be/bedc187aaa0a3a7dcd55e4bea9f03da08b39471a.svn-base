package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.PublicInfo;
import com.rising.management.dao.PublicInfoDao;
import com.rising.management.service.PublicInfoService;
import com.rising.management.vo.PublicInfoVo;

@Service("publicInfoService")
public class PublicInfoServiceImpl implements PublicInfoService {
	Log log = LogFactory.getLog(PublicInfoServiceImpl.class);
	@Autowired
	PublicInfoDao publicInfoDao;
	@Override
	public HashMap<String, Object> getPublicInfo(Integer pageSize,
			Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = publicInfoDao.getPublicInfoListSize();
		Integer start = (pageIndex-1)*pageSize;
		ArrayList<PublicInfoVo> pv = new ArrayList<PublicInfoVo>();
		ArrayList<PublicInfo> p =publicInfoDao.getPublicInfo(start,pageSize);
		for(PublicInfo s:p){
			pv.add(new PublicInfoVo(s));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows",pv);

	
	return resultMap;
	}

	@Override
	public HashMap<String, Object> addPublicInfo(PublicInfo p) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			publicInfoDao.addPublicInfo(p);
			result.put("respCode", 0);
			result.put("respInfo", "添加公告信息成功!");
		} catch (Exception e) {
			log.error("添加公告信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加公告信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> updatePublicInfo(PublicInfo p) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		try{
			publicInfoDao.updatePublicInfo(p);
			resultMap.put("respInfo", "更新公告信息成功！");
			resultMap.put("respCode", 0);
		}catch(Exception e){
			log.error("更新公告信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "更新公告信息时出现异常!");
			resultMap.put("respCode", -1);
			
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> removePublicInfo(String ids) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		String [] idArray =ids.split(",");
		String lastId = "";
		try{
			for(String idObj : idArray){
				lastId=idObj;
				if(!"".equals(idObj)){
					Integer id = Integer.parseInt(idObj);
					publicInfoDao.removePublicInfo(id); 
					resultMap.put("respInfo", "删除公告信息成功!");
				}
			}
		}catch(Exception e){
			log.error("删除id为"+lastId+"的公告信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "删除公告信息时出现异常!");
			
		}
		
		return resultMap;
	}
	

}
