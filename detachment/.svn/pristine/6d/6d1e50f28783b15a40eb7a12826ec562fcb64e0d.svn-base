package com.detachment.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.MonitorDao;
import com.detachment.pojo.TbMonitor;
import com.detachment.web.service.MonitorService;

@Service
public class MonitorServiceImpl implements MonitorService {

	//dao
	@Autowired
	private MonitorDao monitorDao;
	
	@Override
	public HashMap<String, Object> getMonitorList(String monitorType, String position, float bigLat, float smallLat, float bigLng, float smallLng) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			String hql = "from TbMonitor where cast(latitude as float) > ? and cast(latitude as float) < ? and cast(longitude as float) > ? and cast(longitude as float) < ?";
			ArrayList<Object> param = new ArrayList<Object>();
			param.add(smallLat);
			param.add(bigLat);
			param.add(smallLng);
			param.add(bigLng);
			
			if (monitorType != null && !monitorType.equals("")) {
				hql += " and monitorTypeName=?";
				param.add(monitorType);
			}
			if (position != null && !position.equals("")) {
				hql += " and position like ?";
				param.add("%" + position + "%");
			}
			List<TbMonitor> monitorList = monitorDao.findByHQL(hql, param);
			result.put("respCode", 0);
			result.put("monitorList", monitorList);
		} catch (Exception e) {
			result.put("respCode", -1);
		}
		return result;
	}

}
