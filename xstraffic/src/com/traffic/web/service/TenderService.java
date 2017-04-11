package com.traffic.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.traffic.pojo.TbTender;

public interface TenderService {
	
	public HashMap<String, Object> getTenderByPage(String voteId,String name,String openId,String nickName,String startDate,String endDate, Integer pageIndex,Integer pageSize);
}
