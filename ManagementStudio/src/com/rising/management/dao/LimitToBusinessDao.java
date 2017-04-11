package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.LimitToBusiness;

public interface LimitToBusinessDao {
	public Integer getLimitBusSize(Integer limitId,Integer busId);
	public ArrayList<LimitToBusiness> getLimitBuss(Integer limitId,Integer busId,
			Integer start,Integer pageSize);
	public void addLimitToBus(LimitToBusiness lb);
	public void updateLimitToBus(LimitToBusiness lb);
	public void removeLimitToBus(ArrayList<Integer> ids);
}	
