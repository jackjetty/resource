package com.rising.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.management.dao.OrderInfoDao;
import com.rising.management.dao.PlaceDao;
import com.rising.management.dao.UserInfoDao;
import com.rising.management.service.UserInfoService;
import com.rising.management.vo.CreditsVo;
import com.rising.management.vo.NewUserVo;
import com.rising.management.vo.UserInfoVo;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	private Integer baseScore;

	private Integer scoreRound;

	public Integer getBaseScore() {
		return baseScore;
	}

	@Value("#{propertiesReader[BaseScore]}")
	public void setBaseScore(Integer baseScore) {
		this.baseScore = baseScore;
	}

	public Integer getScoreRound() {
		return scoreRound;
	}

	@Value("#{propertiesReader[ScoreRound]}")
	public void setScoreRound(Integer scoreRound) {
		this.scoreRound = scoreRound;
	}

	@Autowired
	UserInfoDao userInfoDao;

	@Autowired
	OrderInfoDao orderInfoDao;

	@Autowired
	PlaceDao placeDao;

	@Override
	public ArrayList<HashMap<String, Object>> doUserScoreStatistics() {
		ArrayList<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
		float maxScore = userInfoDao.getMaxScore();
		Integer x = (int) ((maxScore - baseScore) % scoreRound);
		double maxScoreLimit = scoreRound + (int) maxScore - 0.1;
		Integer max = baseScore + scoreRound;
		Integer min = baseScore;
		if (max <= 400 && max <= maxScoreLimit) {
			HashMap<String, Object> map = new LinkedHashMap<String, Object>();
			max = 400;
			min = 0;
			Integer number = userInfoDao.getUserNumberByScoreRound(min, max);
			map.put("min", min);
			map.put("max", max);
			map.put("IntegralPart", min + " <= 用户积分 <=" + max);
			map.put("Credits", number);
			listMap.add(map);
		}
		if (max <= 1000 && max <= maxScoreLimit) {
			HashMap<String, Object> map = new LinkedHashMap<String, Object>();
			max = 1000;
			min = 400;
			Integer number = userInfoDao.getUserNumberByScoreRound(min, max);
			map.put("min", min);
			map.put("max", max);
			map.put("IntegralPart", min + " < 用户积分 <=" + max);
			map.put("Credits", number);
			listMap.add(map);
		}
		if (max <= 1200 && max <= maxScoreLimit) {
			HashMap<String, Object> map = new LinkedHashMap<String, Object>();
			max = 1200;
			min = 1000;
			Integer number = userInfoDao.getUserNumberByScoreRound(min, max);
			map.put("min", min);
			map.put("max", max);
			map.put("IntegralPart", min + " < 用户积分 <=" + max);
			map.put("Credits", number);
			listMap.add(map);
		}
		if (max <= 1500 && max <= maxScoreLimit) {
			HashMap<String, Object> map = new LinkedHashMap<String, Object>();
			max = 1500;
			min = 1200;
			Integer number = userInfoDao.getUserNumberByScoreRound(min, max);
			map.put("min", min);
			map.put("max", max);
			map.put("IntegralPart", min + " < 用户积分 <=" + max);
			map.put("Credits", number);
			listMap.add(map);
		}
		if (max <= 2000 && max <= maxScoreLimit) {
			HashMap<String, Object> map = new LinkedHashMap<String, Object>();
			max = 2000;
			min = 1500;
			Integer number = userInfoDao.getUserNumberByScoreRound(min, max);
			map.put("min", min);
			map.put("max", max);
			map.put("IntegralPart", min + " < 用户积分 <=" + max);
			map.put("Credits", number);
			listMap.add(map);
		}
		min = max;
		max = max + scoreRound;
		while (max > 2000 && max <= maxScoreLimit) {
			HashMap<String, Object> map = new LinkedHashMap<String, Object>();
			Integer number = userInfoDao.getUserNumberByScoreRound(min, max);
			map.put("min", min);
			map.put("max", max);
			map.put("IntegralPart", min + " < 用户积分 <=" + max);
			map.put("Credits", number);
			listMap.add(map);
			max = max + scoreRound;
			min = min + scoreRound;
		}
		return listMap;
	}

	@Override
	public HashMap<String, Object> getNewUser(String phoneNum, String regTime,
			String lastTraTime, Integer pageSize, Integer pageIndex,
			String startTime, String endTime, String p) {
		Date startDate = null;
		Date endDate = null;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if ("".equals(phoneNum)) {
				phoneNum = null;
			}
			if ("".equals(regTime)) {
				regTime = null;
			}

			if ("".equals(lastTraTime)) {
				lastTraTime = null;
			}

			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime + " 00:00:00");
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime
					+ " 23:59:59");
			Integer listSize = userInfoDao.getNewUserListSize(phoneNum,
					regTime, lastTraTime, startDate, endDate, p);
			Integer start = (pageIndex - 1) * pageSize;
			ArrayList<Object[]> pt = userInfoDao.getPhoneAndTime(phoneNum,
					regTime, lastTraTime, start, pageSize, startDate, endDate,
					p);
			ArrayList<NewUserVo> nuv = new ArrayList<NewUserVo>();
			for (Object[] obj : pt) {
				String phoneNumber = null;
				if (null != obj[0]) {
					phoneNumber = obj[0].toString();
				}
				String registerTime = null;
				if (null != obj[1]) {
					registerTime = obj[1].toString();
				}
				Date reTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(registerTime);
				Integer successTimes = orderInfoDao
						.getSuccessTimes(phoneNumber);
				Integer failTimes = orderInfoDao.getFailTimes(phoneNumber);
				String lastTradeTime = orderInfoDao
						.getLastTradeTime(phoneNumber);
				NewUserVo newUserVo = new NewUserVo(phoneNumber, reTime,
						successTimes, failTimes, lastTradeTime);
				nuv.add(newUserVo);
			}
			resultMap.put("listSize", listSize);
			resultMap.put("rows", nuv);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	@Override
	public HashMap<String, Object> getMonthByUserInfo(String placeName,
			Date startTime, Date endTime, String toTime) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = userInfoDao.getMonthByUserInfo(placeName, startTime,
				endTime, toTime);
		return resultMap;
	}

	@Override
	public Integer getSumUserInfo(String placeCode, Date startTime) {
		Integer size = userInfoDao.getSumUserInfo(placeCode, startTime);
		return size;
	}

	@Override
	public HashMap<String, Object> getCreditsNumbers(String phoneNum,
			String regTime, String lastTraTime, Integer pageSize,
			Integer pageIndex, Integer min, Integer max) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if ("".equals(phoneNum)) {
				phoneNum = null;
			}
			if ("".equals(regTime)) {
				regTime = null;
			}

			if ("".equals(lastTraTime)) {
				lastTraTime = null;
			}
			Integer listSize = userInfoDao.getCreditsListSize(phoneNum,
					regTime, lastTraTime, min, max);
			Integer start = (pageIndex - 1) * pageSize;
			ArrayList<Object[]> pt = userInfoDao.getPhoneAndTime(phoneNum,
					regTime, lastTraTime, start, pageSize, min, max);
			ArrayList<CreditsVo> cv = new ArrayList<CreditsVo>();
			for (Object[] obj : pt) {
				String phoneNumber = null;
				if (null != obj[0]) {
					phoneNumber = obj[0].toString();
				}
				String registerTime = null;
				if (null != obj[1]) {
					registerTime = obj[1].toString();
				}
				Integer allScore = null;
				if (null != obj[2]) {
					allScore = Integer.parseInt(obj[2].toString());
				}
				String address = null;
				if (null != obj[3]) {
					address = obj[3].toString();
				}
				Date reTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(registerTime);
				String placeName = placeDao.getPlaceNameByCode(address);
				double payMoney = orderInfoDao.getPayMoneyByPhone(phoneNumber);
				String lastTradeTime = orderInfoDao
						.getLastTradeTime(phoneNumber);
				CreditsVo creditsVo = new CreditsVo(phoneNumber, reTime,
						allScore, placeName, payMoney, lastTradeTime);
				cv.add(creditsVo);
			}
			resultMap.put("listSize", listSize);
			resultMap.put("rows", cv);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	@Override
	public UserInfoVo getUserInfo(String phoneNumber) {
		UserInfoVo user = userInfoDao.findByPhoneNumber(phoneNumber);
		HashMap<String,Object> map = orderInfoDao.getUserOrderInfo(phoneNumber);
		user.setAllOrder((Integer)map.get("allOrder"));
		user.setFailedOrder((Integer)map.get("failedOrder"));
		user.setSuccessOrder((Integer)map.get("successOrder"));
		user.setPayMoney((Float)map.get("payMoney"));
		return user;
	}
}
