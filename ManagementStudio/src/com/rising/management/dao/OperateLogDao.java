package com.rising.management.dao;

import java.util.Date;
import java.util.HashMap;

public interface OperateLogDao {

	public Integer getNewRegisterNumber(Date startTime, Date endTime);
	public Integer getNewRegisterNumber(String placeName,Date startTime, Date endTime);
	public String getOs(String operateType,String phoneNumber);
	public String getClient(String operateType,String phoneNumber);
}
