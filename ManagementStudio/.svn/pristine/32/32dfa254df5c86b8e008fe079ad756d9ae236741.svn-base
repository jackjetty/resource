package com.rising.management.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.rising.management.bean.SweepStakes;

public interface SweepStakesDao {
	public Integer getSweepStakesSize(Date startTime, Date endTime, String eventName);
	
	public ArrayList<SweepStakes> getSweepStakes(Date startTime, Date endTime, String eventName,Integer start,
			Integer pageSize);
	public void addSweepStakes(SweepStakes sp);
	public void updateSweepStakes(SweepStakes sp);
	public void removeSweepStakes(ArrayList<Integer> ids);
	public HashMap<String, Object> getEventNameAndId();
	public ArrayList<SweepStakes> getSweepStakesInfo();
	public SweepStakes getSweepStakesById(Integer id);
}
