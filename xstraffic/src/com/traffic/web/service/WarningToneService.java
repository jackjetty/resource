package com.traffic.web.service;

import java.util.HashMap;

import com.traffic.pojo.TbWarningTone;

public interface WarningToneService {

	public HashMap<String, Object> getWarningTone(Integer pageSize, Integer pageIndex,
			String trafficIndex, String voiceStatus);

	public HashMap<String, Object> addWarningTone(TbWarningTone tt);

	public HashMap<String, Object> getVoicePath();

	public HashMap<String, Object> changeStatus(Integer id,String trafficIndex,String voiceStatus);

	public HashMap<String, Object> removeWarningTone(String ids);

	public HashMap<String, Object> updateWarningTone(TbWarningTone tt);

	public HashMap<String, Object> getAllLs();

}
