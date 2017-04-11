package com.detachment.dao;

import java.util.ArrayList;
import java.util.Date;

import com.detachment.pojo.TbHtml;

public interface TbHtmlDao extends BaseDao<TbHtml>{
	public Integer getTbHtmlSize(Date startTime, Date endTime,String htmlType);

	public ArrayList<TbHtml> getTbHtml(Date startTime, Date endTime,String htmlType,
			Integer start, Integer pageSize); 
	public ArrayList<TbHtml> getTbHtmlJsp(String htmlType,
			Integer start, Integer pageSize);
}
