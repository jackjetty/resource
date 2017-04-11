package com.traffic.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.traffic.pojo.TbPublicInfo;


public interface PublicInfoService {
	public HashMap<String, Object> getPublicInfo(String startTime, String endTime, String publicType,
			Integer pageSize, Integer pageIndex);

	public Integer getPublicInfoSize(String startTime, String endTime, String publicType);
	public HashMap<String, Object> addPublicInfo(TbPublicInfo pb);

	public HashMap<String, Object> updatePublicInfo(TbPublicInfo pb);

	public HashMap<String, Object> removePublicInfo(String ids);
	
	public TbPublicInfo getPublicInfoById(int id);
	
	public HashMap<String, Object> getCodeInfo(int id);
	
	public HashMap<String, Object> publicInfoStatus(int id);
	
	public HashMap<String, Object> publicInfoUpOrDown(int thisId,int thisIndex,int nextId,int nextIndex);
	
	public void toSetPublicIndex(TbPublicInfo pb);
	
	public ArrayList<TbPublicInfo> getAllCodeJsp(String publicType,
			Integer pageSize, Integer pageIndex);

	public Integer getAllCodeJspSize(String publicType);
	
	public HashMap<String, Object> getPublicInfoWB(Integer pageSize, Integer pageIndex);
}
