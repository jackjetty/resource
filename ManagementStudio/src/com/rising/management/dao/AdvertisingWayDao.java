package com.rising.management.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.AdvertisingWay;

public interface AdvertisingWayDao {

	public Integer getAdvertisingWayListSize(String path);

	public ArrayList<AdvertisingWay> getAdvertisingWay(String path,
			Integer start, Integer pageSize);

	public void addAdvertisingWay(AdvertisingWay aw);

	public void updateAdvertisingWay(AdvertisingWay aw);

	public void deleteById(Integer id);

	public ArrayList<AdvertisingWay> getAdvertisingDetail();

	public HashMap<String, Object> getAdvertisingMap();

}
