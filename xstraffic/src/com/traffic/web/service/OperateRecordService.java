package com.traffic.web.service;

import java.util.HashMap;

public interface OperateRecordService {

	public HashMap<String, Object> getOperateRecord(String startTime, String endTime,
			Integer pageIndex, Integer pageSize);

}
