package com.rising.management.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.SweepStakes;
import com.rising.management.dao.SweepStakesDao;
import com.rising.management.service.SweepStakesService;
import com.rising.management.vo.SweepStakesVo;

@Service("sweepStakesService")
public class SweepStakesServiceImpl implements SweepStakesService{
	Log log = LogFactory.getLog(SweepStakesServiceImpl.class);
	
	@Autowired
	SweepStakesDao sweepStakesDao;
	
	@Override
	public HashMap<String, Object> getSweepStakes(String startTime, String endTime,
			String eventName, Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Date startTime1=null;
		Date endTime1=null;
		if("".equals(startTime)){
			startTime1=null;
		}else{
			try {
				startTime1=new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if("".equals(endTime)){
			endTime1=null;
		}else{
			try {
				endTime1=new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if("".equals(eventName)){
			eventName=null;
		}
		Integer listSize =sweepStakesDao.getSweepStakesSize(startTime1, endTime1, eventName);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<SweepStakes> am=sweepStakesDao.getSweepStakes(startTime1, endTime1, eventName, start, pageSize);
		ArrayList<SweepStakesVo> spv=new ArrayList<SweepStakesVo>();
		for(int i=0;am!=null &&i<am.size();i++){
			SweepStakesVo ssv=new SweepStakesVo(am.get(i));
			spv.add(ssv);
		}
		
		resultMap.put("listSize", listSize);
		resultMap.put("rows", spv);
		return resultMap;
	}

	@Override
	public Integer getSweepStakesSize(String startTime, String endTime,
			String eventName) {
		Date startTime1=null;
		Date endTime1=null;
		if("".equals(startTime)){
			startTime1=null;
		}else{
			try {
				startTime1=new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if("".equals(endTime)){
			endTime1=null;
		}else{
			try {
				endTime1=new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if("".equals(eventName)){
			eventName=null;
		}
		Integer listSize =sweepStakesDao.getSweepStakesSize(startTime1, endTime1, eventName);
		return listSize;
	}

	@Override
	public HashMap<String, Object> addSweepStakes(SweepStakes sp) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			sweepStakesDao.addSweepStakes(sp);
			result.put("respCode", 0);
			result.put("respInfo", "信息添加成功!");
		} catch (Exception e) {
			log.error("添加SweepStakes时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> updateSweepStakes(SweepStakes sp) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			sweepStakesDao.updateSweepStakes(sp);
			result.put("respCode", 0);
			result.put("respInfo", "信息修改成功!");
		} catch (Exception e) {
			log.error("修改SweepStakes时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "修改信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> removeSweepStakes(String ids) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> sps = new ArrayList<Integer>();
			String[] ids1 = ids.split(",");
			for (String id : ids1) {
				try {
					Integer i = Integer.parseInt(id);
					sps.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			sweepStakesDao.removeSweepStakes(sps);
			result.put("respCode", 0);
			result.put("respInfo", "信息删除成功!");
		} catch (Exception e) {
			log.error("删除SweepStakes时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除信息时出现异常!");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> changeStatus(Integer id) {
		SweepStakes ss=sweepStakesDao.getSweepStakesById(id);
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(ss==null){
			map.put("respInfo","要操作活动已被删除了，请刷新后进行其他操作！");
			return map;
		}
		if("启用".equals(ss.getStatus())){
			ss.setStatus("结束");
			ss.setEndTime(new Date());
			sweepStakesDao.updateSweepStakes(ss);
			map.put("respInfo","修改状态成功!");
		}else{
			ArrayList<SweepStakes> sst=sweepStakesDao.getSweepStakesInfo();
			for(SweepStakes s:sst){
				if(s.getStartTime()!=null&&s.getEndTime()==null){
					map.put("respInfo","已有一个活动在进行当中");
					return map;
				}
			}
			ss.setStatus("启用");
			try {
				ss.setStartTime(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
				ss.setEndTime(null);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sweepStakesDao.updateSweepStakes(ss);
			map.put("respInfo","修改状态成功!");
		}
		
		return map;
	}

}
