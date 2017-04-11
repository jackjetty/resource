package com.traffic.dao;

import java.util.ArrayList;
import com.traffic.pojo.TbOrganizationInfo;


public interface OrganizationInfoDao {
	public Integer getOrganizationInfoSize(String organizationName);

	public ArrayList<TbOrganizationInfo> getOrganizationInfo(String organizationName,
			Integer start, Integer pageSize);
	public void addOrganizationInfo(TbOrganizationInfo oi);
	public void updateOrganizationInfo(TbOrganizationInfo oi);
	public void deleteById(int id);
	public TbOrganizationInfo getOrganizationInfoById(int id);
	public ArrayList<TbOrganizationInfo> getMechanismInfo();
	
}
