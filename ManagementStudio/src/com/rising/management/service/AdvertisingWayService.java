package com.rising.management.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.AdvertisingWay;

public interface AdvertisingWayService {

	public HashMap<String, Object> getAdvertisingWay(String path,
			Integer pageSize, Integer pageIndex);

	public HashMap<String, Object> addAdvertisingWay(AdvertisingWay aw);

	public HashMap<String, Object> updateAdvertisingWay(AdvertisingWay aw);

	public HashMap<String, Object> removeAdvertisingWay(String ids);

	public ArrayList<AdvertisingWay> getAdvertisingDetail();

}
