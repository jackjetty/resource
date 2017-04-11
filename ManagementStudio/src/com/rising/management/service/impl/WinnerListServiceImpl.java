package com.rising.management.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.rising.management.bean.Manager;
import com.rising.management.bean.Prize;
import com.rising.management.bean.WinnerList;
import com.rising.management.dao.PrizeDao;
import com.rising.management.dao.UserInfoDao;
import com.rising.management.dao.WinnerListDao;
import com.rising.management.service.WinnerListService;
import com.rising.management.vo.WinnerListVo;
import com.rising.management.vo.WinningInfoVo;

@Service("winnerListService")

public class WinnerListServiceImpl implements WinnerListService {

	Log log = LogFactory.getLog(WinnerListServiceImpl.class);
	@Autowired
	WinnerListDao winnerListDao;


	@Autowired
	PrizeDao prizeDao;

	@Autowired
	UserInfoDao userInfoDao;

	@Override
	public HashMap<String, Object> getWinnerListByPage(Integer pageIndex,
			Integer pageSize, String place) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = winnerListDao.getWinnerListListSize(place);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<WinnerList> aw = winnerListDao.getWinnerList(place, start,
				pageSize);
		ArrayList<WinnerListVo>  awv = new ArrayList<WinnerListVo>();
		for (WinnerList winnerList : aw) {
			WinnerListVo wlv = new WinnerListVo(winnerList);
			awv.add(wlv);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", awv);
		resultMap.put("respCode", 0);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> deleteWinnerById(Integer id) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			winnerListDao.deleteById(id);
			resultMap.put("respInfo", "删除中奖人员信息成功！");
			resultMap.put("respCode", 0);
		} catch (Exception e) {
			log.error("删除中奖人员信息时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "删除中奖人员信息失败！");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addWinner(Integer prizeId, Integer sortId,
			String numbers) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager m = (Manager) session.get("Manager");
		HashMap<String, Object> result = new HashMap<String, Object>();
		String[] phoneNumber = numbers.split(",");
		Prize prize = prizeDao.getPrizeById(prizeId);
		String prizeName = prize.getName();
		if (prize.getLeftAmount() < phoneNumber.length) {
			result.put("respInfo", "奖品剩余的数量不足,请重新抽奖！");
			return result;
		} else {
			for (String number : phoneNumber) {
				String place = userInfoDao.getUserPlaceByNumber(number);
				WinnerList w = new WinnerList();
				w.setPlace(place);
				w.setPhoneNumber(number);
				w.setPrize(prizeName);
				w.setSortId(String.valueOf(sortId));
				w.setSortDetail("第" + sortId + "期");
				w.setManager(m.getName());
				w.setWinTime(new Date());
				winnerListDao.addWinner(w);
				prizeDao.leftAmountDownOne(prizeId);
			}
			result.put("respInfo", "抽奖信息保存成功！");
			return result;
		}

	}

	@Override
	public HashMap<String, Object> getWinningInfo(Integer pageSize,
			Integer pageIndex, Integer prizeId, String phoneNumber, String winTime) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("".equals(phoneNumber)){
			phoneNumber=null;
		}
		if("".equals(winTime)){
			winTime = null;
		}
		Integer listSize = winnerListDao.getWinningInfoList(phoneNumber,winTime,prizeId);
		Integer start = (pageIndex-1)*pageSize;
		ArrayList<Object[]> w = winnerListDao.getWinningInfo(pageSize,start,phoneNumber,winTime,prizeId);
		ArrayList<WinningInfoVo> wv = new ArrayList<WinningInfoVo>();
		for(Object[] obj:w){
			wv.add(new WinningInfoVo(obj));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", wv);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> getWinningInfoByPhoneNumber(
			Integer pageSize, Integer pageIndex, String phoneNumber) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = winnerListDao.getWinningInfoList2(phoneNumber);
		Integer start = (pageIndex-1)*pageSize;
		ArrayList<Object[]> w = winnerListDao.getWinningInfoList2(pageSize,start,phoneNumber);
		ArrayList<WinningInfoVo> wv = new ArrayList<WinningInfoVo>();
		for(Object[] obj:w){
			wv.add(new WinningInfoVo(obj));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", wv);
		return resultMap;
	}

}
