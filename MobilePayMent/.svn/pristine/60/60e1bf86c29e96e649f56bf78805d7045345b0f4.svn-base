package com.rising.mobilepayment.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rising.mobilepayment.bean.OperateLog;
import com.rising.mobilepayment.mapper.OperateLogMapper;
import com.rising.mobilepayment.service.OperateLogService;

@Transactional
@Service("operateLogService")
public class OperateLogServiceImpl implements OperateLogService {

	@Autowired
	OperateLogMapper operateLogMapper;

	@Override
	public void addOperateLog(String PhoneNumber, String OperateType,String userAgent) {
		OperateLog operate = new OperateLog();
		operate.setOperateTime(new Date());
		operate.setOperateType(OperateType);
		operate.setPhoneNumber(PhoneNumber);
		String [] info = userAgent.split("_");
		operate.setOs(info[0]);
		operate.setVersion(info[1]);
		operate.setClient(info[2]);
		operateLogMapper.addOperateLog(operate);
	}

	@Override
	public ArrayList<OperateLog> findCheckInLog(String phoneNumber) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("PhoneNumber", phoneNumber);
		String startTime = new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date()) + " 00:00:00";
		String endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date())
				+ " 23:59:59";
		ArrayList<OperateLog> log = null;
		try {
			Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime);
			Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(endTime);
			map.put("Start", startDate);
			map.put("End", endDate);
			log = operateLogMapper.findCheckInLog(map);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return log;
	}

}
