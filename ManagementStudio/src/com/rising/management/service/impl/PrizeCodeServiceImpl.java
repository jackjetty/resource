package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.PrizeCode;
import com.rising.management.dao.PrizeCodeDao;
import com.rising.management.dao.PrizeDao;
import com.rising.management.service.PrizeCodeService;
import com.rising.management.vo.PrizeCodeVo;


@Service("prizeCodeService")
public class PrizeCodeServiceImpl implements PrizeCodeService {
	
	Log log = LogFactory.getLog(PrizeCodeServiceImpl.class);
	
	@Autowired PrizeCodeDao prizeCodeDao;
	
	@Autowired PrizeDao prizeDao;

	@Override
	public HashMap<String, Object> getPrizeCode(Integer prizeId, String status,
			Integer pageIndex, Integer pageSize) {
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		Integer listSize = prizeCodeDao.getPrizeCodeListSize(prizeId,status);
		Integer start = (pageIndex-1)*pageSize;
		ArrayList<PrizeCode> pr = prizeCodeDao.getPrizeCode(prizeId,status,start,pageSize);
		HashMap<String,Object> map = prizeDao.getPrizeIdAndName();
		ArrayList<PrizeCodeVo> apv = new ArrayList<PrizeCodeVo>();
		for (PrizeCode prizeCode : pr) {
			PrizeCodeVo prizeCodeVo = new PrizeCodeVo(prizeCode,map);
			apv.add(prizeCodeVo);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", apv);
		return resultMap;
		
	}

	@Override
	public HashMap<String, Object> removePrizeCode(String ids) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> ai = split(ids);
			prizeCodeDao.removePrizeCode(ai);
			result.put("respCode", 0);
			result.put("respInfo", "验证码删除成功!");
		} catch (Exception e) {
			log.error("删除验证码时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除验证码时出现异常!");
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
	public HashMap<String, Object> add(ArrayList<PrizeCode> ap) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		try{
			prizeCodeDao.add(ap);
			map.put("respCode", 0);
			map.put("respInfo", "数据导入成功！");
		}catch(Exception e){
			map.put("respCode", -2);
			map.put("respInfo", "数据导入失败");
		}
		return map;
	}

}
