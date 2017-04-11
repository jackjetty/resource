package com.rising.management.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.OperateRecord;
import com.rising.management.dao.SysOperateLogDao;
import com.rising.management.service.SysOperateLogService;
import com.rising.management.vo.OperateRecordVo;

@Service("sysOperateLogService")
public class SysOperateLogServiceImpl implements SysOperateLogService {

	@Autowired SysOperateLogDao sysOperateLogDao;
	
	@Override
	public HashMap<String, Object> getSysOperateLogByPage(String startTime,
			String endTime, Integer pageIndex, Integer pageSize) {
		Date startDate = null;
		Date endDate = null;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if ("".equals(startTime)) {
			startTime = null;
		}else{
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime + " 00:00:00");
			} catch (ParseException e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
		if ("".equals(endTime)) {
			endTime = null;
		}else{
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime + " 23:59:59");
			} catch (ParseException e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
		
		Integer listSize = sysOperateLogDao.getSysOperateLogListSize(startDate,endDate);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<OperateRecord> am = sysOperateLogDao
				.getSysOperateLog(startDate,endDate, start, pageSize);
		
		ArrayList<OperateRecordVo> aorv = new ArrayList<OperateRecordVo>();
		for (OperateRecord operateRecord : am) {
			aorv.add(new OperateRecordVo(operateRecord));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", aorv);
		return resultMap;
	}

}
