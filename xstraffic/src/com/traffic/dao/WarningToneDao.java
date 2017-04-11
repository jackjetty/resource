package com.traffic.dao;

import java.util.ArrayList;

import com.traffic.pojo.TbWarningTone;

public interface WarningToneDao {

	public Integer getWarningToneListSize(String trafficIndex, String voiceStatus);

	public ArrayList<TbWarningTone> getWarningTone(Integer start,
			Integer pageSize, String trafficIndex, String voiceStatus);

	public void addWarningTone(TbWarningTone tt);

	public ArrayList<TbWarningTone> getWT(String voiceStatus);

	public TbWarningTone getWTById(Integer id);

	public void updateVoiceStatus(TbWarningTone t);

	public void removeWarningTone(ArrayList<Integer> is);

	public void updateWarningTone(TbWarningTone tt);

	public ArrayList<String> getAllLs();



}
