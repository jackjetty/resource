package com.traffic.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap; 
import com.traffic.pojo.TbCongest; 
public interface CongestDao extends BaseDao<TbCongest> { 
	public String  getNextCongestId (String prefix); 
	public Integer getCongestList(Date startDate, Date endDate,
			String reportPhoneNumber, String congestState);

	public ArrayList<TbCongest> getCongest(Date startDate, Date endDate,
			String reportPhoneNumber, Integer start, Integer pageSize,
			String congestState);

	public TbCongest getCongestById(String congestId);

	public void changeCgState(TbCongest tc);
}