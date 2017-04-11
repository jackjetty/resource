package com.traffic.dao;

import java.util.ArrayList;
import java.util.Date;

import com.traffic.pojo.TbPublicInfo;

public interface PublicInfoDao extends BaseDao<TbPublicInfo>{
	public Integer getPublicInfoSize(Date startTime, Date endTime,String publicType);

	public ArrayList<TbPublicInfo> getPublicInfo(Date startTime, Date endTime,String publicType,
			Integer start, Integer pageSize);
	public void addPublicInfo(TbPublicInfo pb);
	public void updatePublicInfo(TbPublicInfo pb);
	public void deleteById(int id);
	public TbPublicInfo getPublicInfoById(int id);
	public Integer getAllCodeJspSize(String publicType);
	public ArrayList<TbPublicInfo> getAllCodeJsp(String publicType,Integer start, Integer pageSize);
	
}
