package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.Prize;
import com.rising.management.dao.PrizeDao;
import com.rising.management.service.PrizeService;
import com.rising.management.vo.PrizeVo;

@Service("prizeService")
public class PrizeServiceImpl implements PrizeService {
	Log log = LogFactory.getLog(PrizeServiceImpl.class);
	
	@Autowired PrizeDao prizeDao;

	@Override
	public HashMap<String, Object> getPrizeByPage(Boolean hasLeft,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = prizeDao.getPrizeListSize(hasLeft);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<Prize> am = prizeDao.getPrize(hasLeft, start, pageSize);
		
		ArrayList<PrizeVo> apv = new ArrayList<PrizeVo>();
		for (Prize prize : am) {
			apv.add(new PrizeVo(prize));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", apv);
		return resultMap;
	}

	@Override
	public ArrayList<Prize> getPrizeInfo() {
		ArrayList<Prize> ap = prizeDao.getPrizeInfo();
		return ap;
	}

	@Override
	public HashMap<String,Object> getPrizeById(Integer prizeId) {
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		Prize prize = prizeDao.getPrizeById(prizeId);
		resultMap.put("name", prize.getName());
		resultMap.put("amount", prize.getAmount());
		resultMap.put("leftAmount", prize.getLeftAmount());
		return resultMap;
	}
	
	@Override
	public HashMap<String, Object> savePrize(Prize p) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			prizeDao.savePrize(p);
			result.put("respCode", 0);
			result.put("respInfo", "奖品信息添加成功!");
		} catch (Exception e) {
			log.error("添加奖品信息时出现异常!" + e.toString());
			result.put("respCode", -1);
			result.put("respInfo", "添加奖品信息时出现异常!");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> updatePrize(Prize p) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		//Prize prize = prizeDao.getPrizeById(p.getPrizeId());
		try {
			prizeDao.updatePrize(p);
			result.put("respCode", 0);
			result.put("respInfo", "奖品信息修改成功!");
		} catch (Exception e) {
			log.error("修改奖品信息时出现异常!" + e.toString());
			result.put("respCode", -1);
			result.put("respInfo", "修改奖品信息时出现异常!");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> deletePrize(String prizeIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> ai = new ArrayList<Integer>();
			String[] ids = prizeIds.split(",");
			for (String id : ids) {
				try {
					Integer i = Integer.parseInt(id);
					ai.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			prizeDao.deleteProze(ai);
			result.put("respCode", 0);
			result.put("respInfo", "奖品信息删除成功!");
		} catch (Exception e) {
			log.error("删除奖品信息时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除奖品信息时出现异常!");
		}
		return result;
	}


}
