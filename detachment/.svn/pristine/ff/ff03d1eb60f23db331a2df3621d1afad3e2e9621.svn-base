package com.detachment.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.detachment.pojo.TbFeedBack;
import com.detachment.pojo.vo.TbFeedBackVo;

public interface FeedBackDao extends BaseDao<TbFeedBack> {
	public Integer getFeedBackListSize(Date startDate, Date endDate,String recordType);

	public List<Object[]> getFeedBack(Date startDate, Date endDate,String recordType,
			Integer start, Integer pageSize);

	public TbFeedBackVo getFeedBackById(Integer feedBackId);

	public ArrayList<Object[]> getUserFbInfo(Integer feedBackId);
	
	public ArrayList<TbFeedBack> getFeedBackByOpenIdType(String feedOpenId);
}