package com.traffic.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.OrganizationInfoDao;
import com.traffic.pojo.TbOrganizationInfo;
import com.traffic.web.service.OrganizationInfoService;


@Service("organizationInfoService")
public class OrganizationInfoServiceImpl implements OrganizationInfoService{
	Log log = LogFactory.getLog(OrganizationInfoServiceImpl.class);
	
	@Autowired
	OrganizationInfoDao organizationInfoDao;
	
	@Override
	public HashMap<String, Object> getOrganizationInfo(String organizationName,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("".equals(organizationName)){
			organizationName=null;
		}
		Integer listSize =organizationInfoDao.getOrganizationInfoSize(organizationName);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbOrganizationInfo> ta=organizationInfoDao.getOrganizationInfo(organizationName, start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows",ta);
		return resultMap;
	}

	@Override
	public Integer getOrganizationInfoSize(String organizationName) {
		//HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("".equals(organizationName)){
			organizationName=null;
		}
		Integer listSize =organizationInfoDao.getOrganizationInfoSize(organizationName);
		return listSize;
	}

	@Override
	public HashMap<String, Object> addOrganizationInfo(TbOrganizationInfo oi) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			organizationInfoDao.addOrganizationInfo(oi);
			resultMap.put("respInfo", "机构信息添加成功！");
			resultMap.put("respCode", 0);
		} catch (Exception e) {
			log.error("添加机构信息时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加机构信息时出现异常");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> updateOrganizationInfo(TbOrganizationInfo oi) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			organizationInfoDao.updateOrganizationInfo(oi);
			resultMap.put("respInfo", "机构信息修改成功！");
			resultMap.put("respCode", 0);
		} catch (Exception e) {
			log.error("修改机构信息时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "修改机构信息时出现异常");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> removeOrganizationInfo(String ids) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = ids.split(",");
		int lastId=0;
		try {
			for(String id : idArray){
				if(!"".equals(id)){
					lastId = Integer.parseInt(id);
					organizationInfoDao.deleteById(lastId);
					resultMap.put("respInfo", "删除信息成功!");
				}
			}
			
		} catch (Exception e) {
			log.error("删除id为"+lastId+"的机构信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "删除机构信息时出现异常!");
		}
		return resultMap;
	}

	@Override
	public TbOrganizationInfo getOrganizationInfoById(int id) {
		return organizationInfoDao.getOrganizationInfoById(id);
	}

	@Override
	public ArrayList<TbOrganizationInfo> getMechanismInfo() {
		ArrayList<TbOrganizationInfo> tu=organizationInfoDao.getMechanismInfo();
		return tu;
	}

}
