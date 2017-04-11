package com.rising.management.service;

import java.util.HashMap;

public interface DownloadRecordService {
	public HashMap<String, Object> getDownloadRecord(String fromWay,
			String downloadTime, Integer pageSize, Integer pageIndex);

}
