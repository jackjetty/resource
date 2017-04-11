package com.rising.management.service;

import java.util.HashMap;

import com.rising.management.bean.SweepStakes;

public interface SweepStakesService {
	public HashMap<String, Object> getSweepStakes(String startTime, String endTime,
			String eventName,Integer pageSize, Integer pageIndex);
	public Integer getSweepStakesSize(String startTime, String endTime,String eventName);
	public HashMap<String, Object> addSweepStakes(SweepStakes sp);
	public HashMap<String, Object> updateSweepStakes(SweepStakes sp);
	public HashMap<String, Object> removeSweepStakes(String ids);
	public HashMap<String, Object> changeStatus(Integer id);
}
