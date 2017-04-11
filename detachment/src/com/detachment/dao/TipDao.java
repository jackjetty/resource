package com.detachment.dao;
import com.detachment.pojo.TbTip;
public interface TipDao extends BaseDao<TbTip> { 
	public void updateRecordNum(String processId,String tipType,String departmentId);
}