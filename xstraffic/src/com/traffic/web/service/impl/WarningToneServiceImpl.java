package com.traffic.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.WarningToneDao;
import com.traffic.pojo.TbWarningTone;
import com.traffic.pojovo.TbWarningToneVo;
import com.traffic.web.service.WarningToneService;

@Service("warningToneService")
public class WarningToneServiceImpl implements WarningToneService {
	@Autowired
	WarningToneDao warningToneDao;
	@Override
	public HashMap<String, Object> getWarningTone(Integer pageSize,
			Integer pageIndex, String trafficIndex, String voiceStatus) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		if("".equals(trafficIndex)){
			trafficIndex = null;
		}
		if("".equals(voiceStatus)){
			voiceStatus = null;
		}
		Integer listSize = warningToneDao.getWarningToneListSize(trafficIndex,voiceStatus);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbWarningToneVo> ttv = new ArrayList<TbWarningToneVo>();
		ArrayList<TbWarningTone> tt = warningToneDao.getWarningTone(start,pageSize,trafficIndex,voiceStatus);
		for(int i=0;i<tt.size();i++){
			TbWarningToneVo tvo = new TbWarningToneVo(tt.get(i));
			ttv.add(tvo);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", ttv);
		resultMap.put("respCode", 0);
		return resultMap;
	}
	@Override
	public HashMap<String, Object> addWarningTone(TbWarningTone tt) {
		HashMap<String, Object> result = new HashMap<String, Object>();
			warningToneDao.addWarningTone(tt);
			result.put("respCode", 0);
			result.put("respInfo", "提示语音添加成功!");
		
		
		return result;
	}
	@Override
	public HashMap<String, Object> getVoicePath() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String RA = null;
		String CM = null;
		String voiceStatus = "启用";
		ArrayList<TbWarningTone> tt = warningToneDao.getWT(voiceStatus);
		for(int i=0;i<tt.size();i++){
			if("RA".equals(tt.get(i).getTrafficIndex())){
				RA = tt.get(i).getVoiceLocalStore();
			}
			if("CM".equals(tt.get(i).getTrafficIndex())){
				CM = tt.get(i).getVoiceLocalStore();
			}
		}
		result.put("RA", RA);
		result.put("CM", CM);
		return result;
	}
	@Override
	public HashMap<String, Object> changeStatus(Integer id,String trafficIndex,String voiceStatus) {
		TbWarningTone t = warningToneDao.getWTById(id);
		HashMap<String,Object> map = new HashMap<String, Object>();
		if("交通事故查询".equals(trafficIndex)){
			trafficIndex = "RA";
		}
		if("自助移车查询".equals(trafficIndex)){
			trafficIndex = "CM";
		}
		if("启用".equals(voiceStatus)){
			t.setVoiceStatus("禁用");
			warningToneDao.updateVoiceStatus(t);
			map.put("respInfo","成功禁用提示语音!");
		}
		if("禁用".equals(voiceStatus)){
			String status = "启用";
			ArrayList<TbWarningTone> tt = warningToneDao.getWT(status);
			for(int i=0;i<tt.size();i++){
				if(trafficIndex.equals(tt.get(i).getTrafficIndex())){
					Integer id2 = tt.get(i).getId();
					TbWarningTone t1 = warningToneDao.getWTById(id2);
					t1.setVoiceStatus("禁用");
					warningToneDao.updateVoiceStatus(t1);
				}
				
			}
			t.setVoiceStatus("启用");
			warningToneDao.updateVoiceStatus(t);
			map.put("respInfo","成功启用提示语音!");
		}
		
		return map;
	}
	@Override
	public HashMap<String, Object> removeWarningTone(String ids) {
		HashMap<String,Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> is = new ArrayList<Integer>();
			String[] iss = ids.split(",");
			for (String id : iss) {
				try {
					Integer i = Integer.parseInt(id);
					is.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			warningToneDao.removeWarningTone(is);
			result.put("respCode", 0);
			result.put("respInfo", "提示语音删除成功!");
		} catch (Exception e) {
			result.put("respCode", -3);
			result.put("respInfo", "删除语音时出现异常!");
		}
		return result;
	}
	@Override
	public HashMap<String, Object> updateWarningTone(TbWarningTone tt) {
		HashMap<String,Object> result = new HashMap<String, Object>();
		try {
			warningToneDao.updateWarningTone(tt);
			result.put("respCode", 0);
			result.put("respInfo", "提示语音修改成功!");
		} catch (Exception e) {
			result.put("respCode", -2);
			result.put("respInfo", "修改语音时出现异常!");
		}
		return result;
	}
	@Override
	public HashMap<String, Object> getAllLs() {
		HashMap<String,Object> result = new HashMap<String, Object>();
		ArrayList<String> ls = warningToneDao.getAllLs();
		result.put("vls", ls);
		return result;
	}

}
