package com.traffic.dao;
import java.util.ArrayList;
import java.util.Date;

import com.traffic.dao.BaseDao;
import com.traffic.pojo.TbFeedBack;

public interface FeedBackDao extends BaseDao<TbFeedBack> {
	public Integer getFeedBackListSize(Date startDate, Date endDate,String recordType);

	public ArrayList<TbFeedBack> getFeedBack(Date startDate, Date endDate,String recordType,
			Integer start, Integer pageSize);

	public TbFeedBack getFeedBackById(Integer feedBackId);

	public ArrayList<TbFeedBack> getUserFbInfo(String feedOpenId);
	
	public ArrayList<TbFeedBack> getFeedBackByOpenIdType(String feedOpenId);
}