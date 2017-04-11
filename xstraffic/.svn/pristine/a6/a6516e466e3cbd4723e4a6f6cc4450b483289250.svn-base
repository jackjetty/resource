package com.traffic.dao;

import java.util.ArrayList;
import java.util.Date;

import com.traffic.pojo.TbOperateRecord;


public interface TbOperateLogDao {
	public void save(TbOperateRecord record);

	public Integer getOperateRecordSize(Date startDate, Date endDate);

	public ArrayList<TbOperateRecord> getOperateRecord(Date startDate,
			Date endDate, Integer start, Integer pageSize);
}
