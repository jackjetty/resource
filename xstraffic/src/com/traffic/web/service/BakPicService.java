package com.traffic.web.service;

import java.util.HashMap;

public interface BakPicService {

	public HashMap<String, Object> getBakPic(String startTime, String endTime,
			Integer pageSize, Integer pageIndex);

	public HashMap<String, Object> deleteBakPic(String picIds);

}
