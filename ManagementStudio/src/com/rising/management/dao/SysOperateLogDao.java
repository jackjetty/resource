package com.rising.management.dao;

import java.util.ArrayList;
import java.util.Date;

import com.rising.management.bean.OperateRecord;

public interface SysOperateLogDao {

	public void save(OperateRecord record);

	public Integer getSysOperateLogListSize(Date startTime, Date endTime);

	public ArrayList<OperateRecord> getSysOperateLog(Date startTime,
			Date endTime, Integer start, Integer pageSize);
	
}
