package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.Prize;
import com.rising.management.bean.SweepStakes;
import com.rising.management.bean.SweepStakesPrize;
import com.rising.management.dao.BusinessDao;
import com.rising.management.dao.PlaceDao;
import com.rising.management.dao.PrizeDao;
import com.rising.management.dao.SweepStakesDao;
import com.rising.management.dao.SweepStakesPrizeDao;
import com.rising.management.service.SweepStakesPrizeService;
import com.rising.management.vo.SweepStakesPrizeVo;

@Service("sweepStakesPrizeService")
public class SweepStakesPrizeServiceImpl implements SweepStakesPrizeService{
	Log log = LogFactory.getLog(SweepStakesPrizeServiceImpl.class);
	
	@Autowired
	SweepStakesPrizeDao sweepStakesPrizeDao;
	@Autowired
	PrizeDao prizeDao;
	@Autowired
	SweepStakesDao sweepStakesDao;
	@Autowired
	PlaceDao placeDao;
	@Autowired
	BusinessDao businessDao;
	
	@Override
	public HashMap<String, Object> getSweepStakesPrize(String activityId,
			String prizeId, Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer activityId1=null;
		Integer prizeId1=null;
		if(!"null".equals(activityId)){
			activityId1=Integer.parseInt(activityId);
		}
		if(!"null".equals(prizeId)){
			prizeId1=Integer.parseInt(prizeId);
		}
		Integer listSize =sweepStakesPrizeDao.getSweepStakesPrizeSize(activityId1, prizeId1);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<SweepStakesPrize> sp=sweepStakesPrizeDao.getSweepStakesPrize(activityId1, prizeId1, start, pageSize);
		HashMap<String,Object> prizeMap=prizeDao.getPrizeIdAndName();
		HashMap<String,Object> sweepMap=sweepStakesDao.getEventNameAndId();
		HashMap<String,Object> placeMap=placeDao.getPlaceCodeAndName();
		HashMap<String,Object> busIdName=businessDao.getBusIdAndBusName();
		ArrayList<SweepStakesPrizeVo> sspv=new ArrayList<SweepStakesPrizeVo>();
		for(int i=0;sp!=null&&i<sp.size();i++){
			SweepStakesPrizeVo spv=new SweepStakesPrizeVo(sp.get(i),sweepMap,prizeMap,placeMap,busIdName);
			sspv.add(spv);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", sspv);
		return resultMap;
	}

	@Override
	public Integer getSalesListSize(String activityId, String prizeId) {
		Integer activityId1=null;
		Integer prizeId1=null;
		if(!"".equals(activityId)){
			activityId1=Integer.parseInt(activityId);
		}
		if(!"".equals(prizeId)){
			prizeId1=Integer.parseInt(prizeId);
		}
		Integer listSize =sweepStakesPrizeDao.getSweepStakesPrizeSize(activityId1, prizeId1);
		return listSize;
	}

	@Override
	public HashMap<String, Object> getPrizeInfoAndSweepInfo() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<Prize> pz=prizeDao.getPrizeInfo();
		ArrayList<SweepStakes> ss=sweepStakesDao.getSweepStakesInfo();
		StringBuilder prizeId=new StringBuilder();
		StringBuilder prizeName=new StringBuilder();
		StringBuilder sweepId=new StringBuilder();
		StringBuilder sweepName=new StringBuilder();
		for(Prize p:pz){
			prizeId.append(p.getPrizeId()+",");
			prizeName.append(p.getName()+",");
		}
		for(SweepStakes s:ss){
			sweepId.append(s.getId()+",");
			sweepName.append(s.getEventName()+",");
		}
		result.put("prizeId", prizeId.toString());
		result.put("prizeName", prizeName.toString());
		result.put("sweepId", sweepId.toString());
		result.put("sweepName", sweepName.toString());
		return result;
	}

	@Override
	public HashMap<String, Object> addSweepStakesPrize(SweepStakesPrize sp) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			Integer sums=sweepStakesPrizeDao.SumToPrizeNumber(sp.getPrizeId());
			Prize p=prizeDao.getPrizeById(sp.getPrizeId());
			Integer aa=sums+sp.getPrizeNumber();
			if(aa>p.getLeftAmount()){
				result.put("respCode", 1);
				result.put("respInfo", "奖品数量超过奖品剩余数量，请重新填写");
			}else{
				sweepStakesPrizeDao.addSweepStakesPrize(sp);
				result.put("respCode", 0);
				result.put("respInfo", "信息添加成功!");
			}
		} catch (Exception e) {
			log.error("添加SweepStakes时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> updateSweepStakesPrize(SweepStakesPrize sp) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			SweepStakesPrize ssp=sweepStakesPrizeDao.getSweepStakesPrizeById(sp.getId());
			Integer sums=sweepStakesPrizeDao.SumToPrizeNumber(ssp.getPrizeId());
			Prize p=prizeDao.getPrizeById(sp.getPrizeId());
			Integer a=p.getLeftAmount()-sums;
			Integer aa=sp.getPrizeNumber()-ssp.getPrizeNumber();
			if(aa>a){
				result.put("respCode", 1);
				result.put("respInfo", "奖品数量超过奖品剩余数量，请重新填写");
			}else{
				sweepStakesPrizeDao.updateSweepStakesPrize(sp);
				result.put("respCode", 0);
				result.put("respInfo", "信息修改成功!");
			}
		} catch (Exception e) {
			log.error("添加SweepStakes时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加信息时出现异常!");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> removeSweepStakesPrize(String ids) {
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
			sweepStakesPrizeDao.removeSweepStakesPrize(sps);
			result.put("respCode", 0);
			result.put("respInfo", "信息删除成功!");
		} catch (Exception e) {
			log.error("删除SweepStakes时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除信息时出现异常!");
		}
		return result;
	}
	
}
