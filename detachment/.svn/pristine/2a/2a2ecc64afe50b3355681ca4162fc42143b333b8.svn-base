package com.detachment.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.HistoryDao;
import com.detachment.dao.HistoryTypeDao;
import com.detachment.pojo.TbHistory;
import com.detachment.pojo.TbHistoryType;
import com.detachment.pojo.TbHtmlType;
import com.detachment.pojo.vo.TbHistoryVo;
import com.detachment.web.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService{
	
	@Autowired
	HistoryDao historyDao;
	@Autowired
	HistoryTypeDao historyTypeDao;
	
	@Override
	public HashMap<String, Object> getTbHistory(String historyTypeId,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("null".equals(historyTypeId) || "".equals(historyTypeId)){
			historyTypeId=null;
		}
		Integer listSize =historyDao.getHistorySize(historyTypeId);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbHistory> ths=historyDao.getTbHistory(historyTypeId, start, pageSize);
		ArrayList<TbHistoryType> tht=(ArrayList<TbHistoryType>) historyTypeDao.findByHQL("from TbHistoryType");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<TbHistoryVo> thvo= new ArrayList<TbHistoryVo>();
		for(TbHistoryType t:tht){
			map.put(t.getHistoryTypeId(), t.getHistoryTypeName());
		}
		for(TbHistory th:ths){
			TbHistoryVo thv=new TbHistoryVo(th,map);
			thvo.add(thv);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows",thvo);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> changeHistoryTypeById(String ids,
			String historyTypeId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = ids.split(",");
		try {
			for(String idObj : idArray){
				if(!"".equals(idObj)){
					historyDao.changeHistoryTypeById(idObj, historyTypeId);
					resultMap.put("respInfo", "设置成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("respInfo", "设置信息类型时出现异常!");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> changeValid(String ids, boolean valid) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = ids.split(",");
		try {
			for(String idObj : idArray){
				if(!"".equals(idObj)){
					historyDao.changeValid(idObj, valid);
					if(valid){
						resultMap.put("respInfo", "启用成功!");
					}else{
						resultMap.put("respInfo", "禁用成功!");
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("respInfo", "启用信息类型时出现异常!");
		}
		return resultMap;
	}

	@Override
	public ArrayList<TbHistoryVo> getHistoryToJsp(String historyTypeId,
			Integer pageSize, Integer pageIndex) {
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbHistory> ths=historyDao.getTbHistory(historyTypeId, start, pageSize);
		ArrayList<TbHistoryType> tht=(ArrayList<TbHistoryType>) historyTypeDao.findByHQL("from TbHistoryType");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<TbHistoryVo> thvo= new ArrayList<TbHistoryVo>();
		for(TbHistoryType t:tht){
			map.put(t.getHistoryTypeId(), t.getHistoryTypeName());
		}
		for(TbHistory th:ths){
			TbHistoryVo thv=new TbHistoryVo(th,map);
			thvo.add(thv);
		}
		return thvo;
	}

}
