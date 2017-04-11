package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.DownloadRecord;
import com.rising.management.dao.AdvertisingWayDao;
import com.rising.management.dao.DownloadRecordDao;
import com.rising.management.service.DownloadRecordService;
import com.rising.management.vo.DownloadRecordVo;
@Service("downloadRecordService")
public class DownloadRecordServiceImpl implements DownloadRecordService {
	Log log = LogFactory.getLog(DownloadRecordServiceImpl.class);
	
	@Autowired
	DownloadRecordDao downloadRecordDao;
	
	
	@Autowired AdvertisingWayDao advertisingWayDao;
	
	@Override
	public HashMap<String, Object> getDownloadRecord(String fromWay,
			String downloadTime, Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("null".equals(fromWay)){
			fromWay = null;
		}
		if("".equals(downloadTime)){
			downloadTime = null;
		}
			Integer listSize = downloadRecordDao.getDownloadRecordListSize(fromWay, downloadTime);
			Integer start = (pageIndex-1)*pageSize;
			ArrayList<DownloadRecordVo> srv = new ArrayList<DownloadRecordVo>();
			ArrayList<DownloadRecord> sr =downloadRecordDao.getDownloadRecord(fromWay, downloadTime,start,pageSize);
			HashMap<String,Object> map = advertisingWayDao.getAdvertisingMap();
			for(DownloadRecord s:sr){
				srv.add(new DownloadRecordVo(s,map));
			}
			resultMap.put("listSize", listSize);
			resultMap.put("rows",srv);
	
		
		return resultMap;
	}

}
