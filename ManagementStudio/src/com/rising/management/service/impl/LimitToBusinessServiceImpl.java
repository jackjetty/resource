package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.Business;
import com.rising.management.bean.LimitToBusiness;
import com.rising.management.bean.PayLimit;
import com.rising.management.dao.BusinessDao;
import com.rising.management.dao.LimitToBusinessDao;
import com.rising.management.dao.PayLimitDao;
import com.rising.management.service.LimitToBusinessService;
import com.rising.management.vo.LimitToBusinessVo;

@Service("limitToBusinessService")
public class LimitToBusinessServiceImpl implements LimitToBusinessService{
	Log log = LogFactory.getLog(LimitToBusinessServiceImpl.class);
	
	@Autowired
	LimitToBusinessDao limitToBusinessDao;
	@Autowired
	BusinessDao businessDao;
	@Autowired
	PayLimitDao payLimitDao;
	@Override
	public HashMap<String, Object> getLimitBuss(String limitId, String busId,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer limitid=null;
		Integer busid=null;
		if(!"null".equals(limitId)){
			limitid=Integer.parseInt(limitId);
		}
		if(!"null".equals(busId)){
			busid=Integer.parseInt(busId);
		}
		Integer listSize =limitToBusinessDao.getLimitBusSize(limitid, busid);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<LimitToBusiness> lb=limitToBusinessDao.getLimitBuss(limitid, busid, start, pageSize);
		ArrayList<LimitToBusinessVo> lbv=new ArrayList<LimitToBusinessVo>();
		HashMap<String,Object> bus=businessDao.getBusIdAndBusName();
		HashMap<String,Object> plt=payLimitDao.getLimitIdandLimitName();
		for(int i=0;lb!=null &&i<lb.size();i++){
			LimitToBusinessVo ltbvo=new LimitToBusinessVo(lb.get(i),plt,bus);
			lbv.add(ltbvo);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", lbv);
		return resultMap;
	}

	@Override
	public Integer getLimitBusSize(String limitId, String busId) {
		Integer limitid;
		Integer busid;
		if(limitId.length()>=1){
			limitid=Integer.parseInt(limitId);
		}else{
			limitid=null;
		}
		if(busId.length()>=1){
			busid=Integer.parseInt(busId);
		}else{
			busid=null;
		}
		Integer in=limitToBusinessDao.getLimitBusSize(limitid, busid);
		return in;
	}

	@Override
	public HashMap<String, Object> getLimitIdNameAndBusIdName() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<Business> bs=businessDao.getBusiness();
		ArrayList<PayLimit> pl=payLimitDao.getPayLimit();
		StringBuilder busId=new StringBuilder();
		StringBuilder Btype=new StringBuilder();
		StringBuilder limitId=new StringBuilder();
		StringBuilder limitName=new StringBuilder();
		for(Business b:bs){
			busId.append(b.getBusId()+",");
			Btype.append(b.getBtype()+",");
		}
		result.put("busId", busId.toString());
		result.put("Btype", Btype.toString());
		for(PayLimit p:pl){
			limitId.append(p.getLimitId()+",");
			limitName.append(p.getLimitName()+",");
		}
		result.put("limitId", limitId.toString());
		result.put("limitName", limitName.toString());
		return result;
	}

	@Override
	public HashMap<String, Object> addLimitToBus(LimitToBusiness lb) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			limitToBusinessDao.addLimitToBus(lb);
			result.put("respCode", 0);
			result.put("respInfo", "信息添加成功!");
		} catch (Exception e) {
			log.error("添加信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> updateLimitToBus(LimitToBusiness lb) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			limitToBusinessDao.updateLimitToBus(lb);
			result.put("respCode", 0);
			result.put("respInfo", "信息修改成功!");
		} catch (Exception e) {
			log.error("修改信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "修改信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> removteLimitToBus(String ids) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> ai = new ArrayList<Integer>();
			String[] idss = ids.split(",");
			for (String id : idss) {
				try {
					Integer i = Integer.parseInt(id);
					ai.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			limitToBusinessDao.removeLimitToBus(ai);
			result.put("respCode", 0);
			result.put("respInfo", "信息删除成功!");
		} catch (Exception e) {
			log.error("删除信息时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除信息时出现异常!");
		}
		return result;
	}

}
