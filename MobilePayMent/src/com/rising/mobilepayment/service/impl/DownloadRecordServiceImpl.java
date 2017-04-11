package com.rising.mobilepayment.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.mobilepayment.bean.DownloadRecord;
import com.rising.mobilepayment.mapper.DownloadRecordMapper;
import com.rising.mobilepayment.service.DownloadRecordService;


@Service("downloadRecordService")
public class DownloadRecordServiceImpl implements DownloadRecordService {

	@Autowired DownloadRecordMapper downloadRecordMapper;
	
	@Override
	public void record(String from,String Ip) {
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) -1);
		Date firstDate = c.getTime();
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) + 2);
		Date endDate = c.getTime();
		map.put("Start", firstDate);
		map.put("End", endDate);
		map.put("FromWay", from);
		map.put("Ip", Ip);
		ArrayList<DownloadRecord> adr = downloadRecordMapper.find(map);
		if(adr==null || adr.size()==0){
			DownloadRecord record = new DownloadRecord();
			record.setFromWay(from);
			record.setDownloadTime(new Date());
			record.setIpAddress(Ip);
			downloadRecordMapper.add(record);
		}
		
	}

}
