package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.PrizeShortMessage;
import com.rising.management.dao.PlaceDao;
import com.rising.management.dao.PrizeDao;
import com.rising.management.dao.PrizeShortMessageDao;
import com.rising.management.service.PrizeShortMessageService;
import com.rising.management.vo.PrizeShortMessageVo;


@Service("prizeShortMessageService")
public class PrizeShortMessageServiceImpl implements PrizeShortMessageService {
	
	Log log = LogFactory.getLog(PrizeShortMessageServiceImpl.class);
	
	@Autowired PrizeShortMessageDao prizeShortMessageDao;
	
	@Autowired PrizeDao prizeDao;
	
	@Autowired PlaceDao placeDao;
	
	@Override
	public HashMap<String, Object> getPrizeShortMessage(Integer pageIndex,
			Integer pageSize, Integer prizeId, String place) {
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		Integer listSize = prizeShortMessageDao.getPrizeShortMessageListSize(prizeId,place);
		Integer start = (pageIndex-1)*pageSize;
		ArrayList<PrizeShortMessage> pr = prizeShortMessageDao.getPrizeShortMessage(prizeId,place,start,pageSize);
		HashMap<String,Object> map = prizeDao.getPrizeIdAndName();
		HashMap<String,Object> placeMap = placeDao.getPlaceCodeAndName();
		ArrayList<PrizeShortMessageVo> apv = new ArrayList<PrizeShortMessageVo>();
		for (PrizeShortMessage prizeShortMessage : pr) {
			PrizeShortMessageVo prizeShortMessageVo = new PrizeShortMessageVo(prizeShortMessage,map,placeMap);
			apv.add(prizeShortMessageVo);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", apv);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> updatePrizeShortMessage(
			PrizeShortMessage prizeShortMessage) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		try{
			prizeShortMessageDao.update(prizeShortMessage);
			map.put("respCode", 0);
			map.put("respInfo", "短信模版已更新！");
		}catch(Exception e){
			map.put("respCode", -1);
			map.put("respInfo", "短信模版更新失败！");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> removePrizeShortMessage(String ids) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> ai = split(ids);
			prizeShortMessageDao.removePrizeShortMessage(ai);
			result.put("respCode", 0);
			result.put("respInfo", "短信模版删除成功!");
		} catch (Exception e) {
			log.error("删除验证码时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除短信模版时出现异常!");
		}
		return result;
	}

	private ArrayList<Integer> split(String ids) {
		ArrayList<Integer> ai = new ArrayList<Integer>();
		String [] id = ids.split(",");
		for (String string : id) {
			Integer x = null;
			try{
				x = Integer.parseInt(string);
				ai.add(x);
			}catch(Exception e){
				continue;
			}
		}
		return ai;
	}
	@Override
	public HashMap<String, Object> addPrizeShortMessage(
			PrizeShortMessage prizeShortMessage) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		try{
			prizeShortMessageDao.add(prizeShortMessage);
			map.put("respCode", 0);
			map.put("respInfo", "添加短信模版成功！");
		}catch(Exception e){
			map.put("respCode", -1);
			map.put("respInfo", "添加短信模版失败！");
		}
		return map;
	}

}
