package com.traffic.web.service;

import java.util.HashMap;

public interface TbTipService {
	public HashMap<String, Object> getTbTipRecordNum(String processId,
			String tipType);
}
