package com.detachment.web.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.TbHtmlDao;
import com.detachment.dao.TbHtmlTypeDao;
import com.detachment.pojo.TbHtml;
import com.detachment.pojo.TbHtmlType;
import com.detachment.pojo.vo.TbHtmlVo;
import com.detachment.web.service.TbHtmlService;

@Service
public class TbHtmlServiceImpl implements TbHtmlService{
	
	@Autowired
	TbHtmlDao tbHtmlDao;
	@Autowired
	TbHtmlTypeDao tbHtmlTypeDao;
	
	
	@Override
	public HashMap<String, Object> getTbHtml(String startTime, String endTime,
			String htmlType, Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Timestamp startDate = null;
		Timestamp endDate = null;
		if ("".equals(startTime)) {
			startDate = null;
		} else {
			try {
				startDate = Timestamp.valueOf(startTime.toString()+ " 00:00:00");
			} catch (Exception e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
		if ("".equals(endTime)) {
			endDate = null;
		} else {
			try {
				endDate = Timestamp.valueOf(endTime.toString() + " 23:59:59");
			} catch (Exception e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
		if("null".equals(htmlType) || "".equals(htmlType)){
			htmlType=null;
		}
		Integer listSize =tbHtmlDao.getTbHtmlSize(startDate, endDate, htmlType);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbHtml> ths=tbHtmlDao.getTbHtml(startDate, endDate, htmlType, start, pageSize);
		ArrayList<TbHtmlType> tht=(ArrayList<TbHtmlType>) tbHtmlTypeDao.findByHQL("from TbHtmlType");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<TbHtmlVo> thvo=new ArrayList<TbHtmlVo>();
		for(TbHtmlType t:tht){
			map.put(t.getHtmlTypeId(), t.getHtmlTypeName());
		}
		for(TbHtml th:ths){
			TbHtmlVo thv=new TbHtmlVo(th,map);
			thvo.add(thv);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows",thvo);
		return resultMap;
	}


	@Override
	public HashMap<String, Object> addTbHtml(TbHtml th) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		tbHtmlDao.save(th);
		resultMap.put("respCode", 0);
		resultMap.put("respInfo", "保存成功!");
		return resultMap;
	}


	@Override
	public TbHtmlVo getTbHtmlByHtmlId(Integer htmlId) {
		// TODO Auto-generated method stub
		ArrayList<TbHtmlType> tht=(ArrayList<TbHtmlType>) tbHtmlTypeDao.findByHQL("from TbHtmlType");
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(TbHtmlType t:tht){
			map.put(t.getHtmlTypeId(), t.getHtmlTypeName());
		}
		TbHtml th=tbHtmlDao.findById(htmlId);
		TbHtmlVo thv=new TbHtmlVo(th,map);
		return thv;
	}


	@Override
	public HashMap<String, Object> updateHtml(TbHtml th) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		tbHtmlDao.update(th);
		resultMap.put("respCode", 0);
		resultMap.put("respInfo", "修改成功!");
		return resultMap;
	}


	@Override
	public TbHtml getTbHtmlById(Integer htmlId) {
		// TODO Auto-generated method stub
		return tbHtmlDao.findById(htmlId);
	}


	@Override
	public HashMap<String, Object> removeHtml(String ids) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = ids.split(",");
		int lastId=0;
		try {
			for(String id : idArray){
				if(!"".equals(id)){
					lastId = Integer.parseInt(id);
					TbHtml th=tbHtmlDao.findById(lastId);
					tbHtmlDao.delete(th);
					resultMap.put("respInfo", "删除信息成功!");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("respInfo", "删除信息时出现异常!");
		}
		return resultMap;
	}


	@Override
	public ArrayList<TbHtmlVo> getTbHtmlJsp(String htmlTypeId,Integer pageSize, Integer pageIndex) {
		ArrayList<TbHtmlVo> thvo=new ArrayList<TbHtmlVo>();
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbHtml> ths=tbHtmlDao.getTbHtmlJsp(htmlTypeId, start, pageSize);
		ArrayList<TbHtmlType> tht=(ArrayList<TbHtmlType>) tbHtmlTypeDao.findByHQL("from TbHtmlType");
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(TbHtmlType t:tht){
			map.put(t.getHtmlTypeId(), t.getHtmlTypeName());
		}
		for(TbHtml th:ths){
			TbHtmlVo thv=new TbHtmlVo(th,map);
			thvo.add(thv);
		}
		return thvo;
	}

}













