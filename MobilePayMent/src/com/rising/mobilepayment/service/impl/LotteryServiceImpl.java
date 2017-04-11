package com.rising.mobilepayment.service.impl;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.OrderInfo;
import com.rising.mobilepayment.bean.Prize;
import com.rising.mobilepayment.bean.PrizeCode;
import com.rising.mobilepayment.bean.PrizeShortMessage;
import com.rising.mobilepayment.bean.SweepStakes;
import com.rising.mobilepayment.bean.SweepStakesPrize;
import com.rising.mobilepayment.bean.UserInfo;
import com.rising.mobilepayment.bean.UserLotteryTimes;
import com.rising.mobilepayment.bean.WinnerList;
import com.rising.mobilepayment.bean.WinningInfo;
import com.rising.mobilepayment.commom.DateUtil;
import com.rising.mobilepayment.commom.SendMessageThread;
import com.rising.mobilepayment.mapper.MessageMapper;
import com.rising.mobilepayment.mapper.OrderInfoMapper;
import com.rising.mobilepayment.mapper.PrizeCodeMapper;
import com.rising.mobilepayment.mapper.PrizeMapper;
import com.rising.mobilepayment.mapper.PrizeShortMessageMapper;
import com.rising.mobilepayment.mapper.QQPerMonthRecordMapper;
import com.rising.mobilepayment.mapper.SweepStakesMapper;
import com.rising.mobilepayment.mapper.SweepStakesPrizeMapper;
import com.rising.mobilepayment.mapper.UserInfoMapper;
import com.rising.mobilepayment.mapper.UserLotteryTimesMapper;
import com.rising.mobilepayment.mapper.WinnerListMapper;
import com.rising.mobilepayment.mapper.WinningInfoMapper;
import com.rising.mobilepayment.service.LotteryService;

@Service("lotteryService")
public class LotteryServiceImpl implements LotteryService {

	Log log = LogFactory.getLog(LotteryServiceImpl.class);

	private static String SendByInterface = "INTERFACE";

	private static String SendByScore = "SENDSCORE";

	private Integer listSize;

	public Integer getListSize() {
		return listSize;
	}

	@Value("#{propertiesReader[listSize]}")
	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	private String payMoneyIp;

	private String sendFilmMessageParameter;

	@Autowired
	OrderInfoMapper orderInfoMapper;

	@Autowired
	UserInfoMapper userInfoMapper;

	@Autowired
	QQPerMonthRecordMapper qQPerMonthRecordMapper;

	@Autowired
	PrizeMapper prizeMapper;

	@Autowired
	SweepStakesMapper sweepStakesMapper;

	@Autowired
	SweepStakesPrizeMapper sweepStakesPrizeMapper;

	@Autowired
	WinningInfoMapper winningInfoMapper;

	@Autowired
	MessageMapper messageMapper;

	@Autowired
	UserLotteryTimesMapper userLotteryTimesMapper;

	@Autowired
	WinnerListMapper winnerListMapper;

	@Autowired
	PrizeCodeMapper prizeCodeMapper;

	@Autowired
	PrizeShortMessageMapper prizeShortMessageMapper;

	public String getPayMoneyIp() {
		return payMoneyIp;
	}

	@Value("#{propertiesReader[shortMessageIp]}")
	public void setPayMoneyIp(String payMoneyIp) {
		this.payMoneyIp = payMoneyIp;
	}

	public String getSendFilmMessageParameter() {
		return sendFilmMessageParameter;
	}

	@Value("#{propertiesReader[sendFilmMessageParameter]}")
	public void setSendFilmMessageParameter(String sendFilmMessageParameter) {
		this.sendFilmMessageParameter = sendFilmMessageParameter;
	}

	@Override
	public String getInfo(String phoneNumber) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 查询当前是否有抽奖活动
		SweepStakes sweepStakes = sweepStakesMapper.getSweepStakes();
		if (sweepStakes == null) {
			result.put("respCode", -20);
			result.put("respInfo", "当前没有抽奖活动！");
			return new Gson().toJson(result);
		}
		UserInfo user = userInfoMapper.findUserByPhoneNumber(phoneNumber);
		if (user.getAllScore() < sweepStakes.getDeductScore()) {
			result.put("respCode", -26);
			result.put("respInfo", "您当前的积分不足,不能进行抽奖！");
			return new Gson().toJson(result);
		}
		// 查询用户当天是否还有抽奖机会o
		UserLotteryTimes lotteryTimes = userLotteryTimesMapper
				.findByPhoneNumber(phoneNumber);
		if (lotteryTimes == null) {
			lotteryTimes = new UserLotteryTimes();
			lotteryTimes.setLastLotteryTime(new Date());
			lotteryTimes.setLotteryTimes(0);
			lotteryTimes.setPhoneNumber(phoneNumber);
			userLotteryTimesMapper.addLotteryTimes(lotteryTimes);
			result.put("lotteryLave", sweepStakes.getTimes());
		} else {
			DateUtil d = new DateUtil();
			HashMap<String, Date> thisDay = d.getTodayTime();
			if (lotteryTimes.getLastLotteryTime().before(thisDay.get("Start"))) {
				lotteryTimes.setLotteryTimes(0);
				lotteryTimes.setLastLotteryTime(new Date());
				userLotteryTimesMapper.updateLotteryTimes(lotteryTimes);
				result.put("lotteryLave", sweepStakes.getTimes());
			} else {
				result.put("lotteryLave",
						sweepStakes.getTimes() - lotteryTimes.getLotteryTimes());
			}
		}
		if (lotteryTimes != null
				&& sweepStakes.getTimes() == lotteryTimes.getLotteryTimes()) {
			result.put("respCode", -24);
			result.put("lotteryLave", 0);
			result.put("respInfo", "您当日抽奖次数已用完!");
			return new Gson().toJson(result);
		}
		ArrayList<SweepStakesPrize> ass = sweepStakesPrizeMapper
				.findBySweepStakes(sweepStakes.getId());
		ArrayList<SweepStakesPrize> ass2 = new ArrayList<SweepStakesPrize>();
		ArrayList<Prize> ap;
		ArrayList<Integer> ai = getPrizeId(ass);
		if (ai != null && ai.size() > 0) {
			ap = prizeMapper.findByPrizeIds(ai);
		} else {
			ap = new ArrayList<Prize>();
		}
		if (ass == null || ass.size() == 0) {
			result.put("respCode", -25);
			result.put("lotteryLave", 0);
			result.put("respInfo", "奖品已抽完！");
			return new Gson().toJson(result);
		}
		for (int i = 0; ass != null && i < ass.size(); i++) {
			Prize p = findPrizeById(ap, ass.get(i).getPrizeId());
			if (checkQualifications(ass.get(i), phoneNumber, p)) {
				ass2.add(ass.get(i));
			}
		}
		ArrayList<HashMap<String, Object>> listLottery = getUserPrizeInfo(ass2,
				ap);
		String lotteryInfo = messageMapper
				.getMessageContentByUse("lotteryInfo");
		String Prompt = messageMapper.getMessageContentByUse("LotteryPrompt");
		result.put("respCode", 0);
		result.put("lotteryType", sweepStakes.getEventName());
		result.put("lotteryInfo", lotteryInfo);
		result.put("prompt", Prompt);
		result.put("lotteryImgBeforeName",
				sweepStakes.getLotteryImgBeforeName());
		result.put("lotteryImgAfterName", sweepStakes.getLotteryImgAfterName());
		result.put("lotteryInfo", lotteryInfo);
		result.put("listLottery", listLottery);
		return new Gson().toJson(result);
	}

	private ArrayList<HashMap<String, Object>> getUserPrizeInfo(
			ArrayList<SweepStakesPrize> ass, ArrayList<Prize> ap) {
		ArrayList<HashMap<String, Object>> listLottery = new ArrayList<HashMap<String, Object>>();
		HashMap<Integer, Integer> hi = getPrizeInfo(ap);
		ArrayList<SweepStakesPrize> assp = new ArrayList<SweepStakesPrize>();
		for (SweepStakesPrize sweepStakesPrize : ass) {
			Prize p;
			try {
				p = ap.get(hi.get(sweepStakesPrize.getPrizeId()));
			} catch (Exception e) {
				p = null;
			}
			if (p != null) {
				if (!"SENDSCORE".equals(p.getSendWay())) {
					String frequency = sweepStakesPrize.getFrequency();
					if (frequency != null) {
						DateUtil d = new DateUtil();
						HashMap<String, Date> dateMap = null;
						if (frequency.equals("PerWeek")) {
							dateMap = d.getThisWeekTime();
						} else if (frequency.equals("PerMonth")) {
							dateMap = d.getThisMonthTime();
						} else if (frequency.equals("PerYear")) {
							dateMap = d.getThisYearTime();
						} else if (frequency.equals("PerDay")) {
							dateMap = d.getTodayTime();
						}
						HashMap<String, Object> paraMap = new HashMap<String, Object>();
						paraMap.put("lotteryId", String.valueOf(p.getPrizeId()));
						paraMap.put("StartTime", dateMap.get("Start"));
						paraMap.put("EndTime", dateMap.get("End"));
						ArrayList<WinningInfo> aw = winningInfoMapper
								.getWinningInfo(paraMap);
						Integer frequencyNumber = sweepStakesPrize
								.getFrequencyNumber();
						if (frequencyNumber != null) {
							if (aw == null || aw.size() < frequencyNumber) {
								// 可以根据中奖概率放入奖品中
								// 奖品有中奖频率，有中奖概率
								Float rate = sweepStakesPrize.getRate();
								Integer x = null;
								if (rate != null) {
									x = (int) (rate * 100);
									ArrayList<Integer> ai = getLuckNumbers(x);
									Integer luckNumber = getLuckNumber();
									if (ai.contains(luckNumber)) {
										HashMap<String, Object> map = new HashMap<String, Object>();
										map.put("LotteryID",
												String.valueOf(p.getPrizeId()));
										map.put("Name", p.getName());
										map.put("Number", "1");
										map.put("ImgName",
												"Big_" + p.getImgName());
										listLottery.add(map);
									}
								} else {
									// 奖品有中奖频率无中奖概率
								}
							}
						}

					} else {
						Float rate = sweepStakesPrize.getRate();
						Integer x = null;
						if (rate != null) {
							// 奖品无中奖频率有中奖概率
							x = (int) (rate * 100);
							ArrayList<Integer> ai = getLuckNumbers(x);
							Integer luckNumber = getLuckNumber();
							if (ai.contains(luckNumber)) {
								HashMap<String, Object> map = new HashMap<String, Object>();
								map.put("LotteryID",
										String.valueOf(p.getPrizeId()));
								map.put("Name", p.getName());
								map.put("Number", "1");
								map.put("ImgName", "Big_" + p.getImgName());
								listLottery.add(map);
							}
						} else {
							// 奖品无中奖频率无中奖概率
						}
					}
				} else {
					assp.add(sweepStakesPrize);
				}
			}
		}
		if (assp.size() == 0) {
			if (listLottery.size() < 9) {
				Integer size = listLottery.size();
				for (int i = 0; i < 9 - size; i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("LotteryID", "");
					map.put("Name", "");
					map.put("Number", "");
					map.put("ImgName", "");
					listLottery.add(map);
				}
			}
		} else {
			if (listLottery.size() < 9) {
				Integer size = listLottery.size();
				for (int i = 0; i < 9 - size; i++) {
					Integer x = new Random().nextInt(assp.size());
					SweepStakesPrize prize = assp.get(x);
					Prize p1;
					try {
						p1 = ap.get(hi.get(prize.getPrizeId()));
					} catch (Exception e) {
						p1 = null;
					}
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("LotteryID", String.valueOf(p1.getPrizeId()));
					map.put("Name", "积分");
					map.put("Number", String.valueOf(p1.getSendScore()));
					map.put("ImgName", "Big_" + p1.getImgName());
					listLottery.add(map);
				}
			}
		}
		return sort(listLottery);
	}

	private ArrayList<HashMap<String, Object>> sort(
			ArrayList<HashMap<String, Object>> ahso) {
		Integer size = ahso.size();
		ArrayList<HashMap<String, Object>> ahso2 = new ArrayList<HashMap<String, Object>>();

		ArrayList<Integer> ai = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			Integer index = new Random().nextInt(size);
			while (ai.contains(index)) {
				index = new Random().nextInt(size);
			}
			HashMap<String, Object> aso = ahso.get(index);
			ai.add(index);
			aso.put("No", i + 1 + "");
			ahso2.add(aso);
		}
		ArrayList<HashMap<String, Object>> ahso3 = new ArrayList<HashMap<String, Object>>();
		for (HashMap<String, Object> hashMap : ahso2) {
			if (!"".equals(hashMap.get("Name"))) {
				ahso3.add(hashMap);
			}
		}
		return ahso3;
	}

	private ArrayList<Integer> getLuckNumbers(Integer number) {
		ArrayList<Integer> ai = new ArrayList<Integer>();
		Random r = new Random();
		while (ai.size() < number) {
			Integer x = r.nextInt(100);
			if (ai.contains(x)) {
				continue;
			} else {
				ai.add(x);
			}
		}
		return ai;
	}

	private Integer getLuckNumber() {
		return new Random().nextInt(100);
	}

	private Boolean checkQualifications(SweepStakesPrize sweepStakesPrize,
			String phoneNumber, Prize p) {
		UserInfo user = userInfoMapper.findUserByPhoneNumber(phoneNumber);
		DateUtil d = new DateUtil();
		if (user != null) {
			String place = sweepStakesPrize.getUserLimit();
			if (place != null) {
				if (!user.getAddress().equals(place)) {
					return false;
				}
			}
		}
		String userFrequency = sweepStakesPrize.getUserFrequency();
		if (userFrequency != null) {
			HashMap<String, Date> dateMap = null;
			if ("PerYear".equals(userFrequency)) {
				dateMap = d.getThisYearTime();
			} else if ("PerDay".equals(userFrequency)) {
				dateMap = d.getTodayTime();
			} else if ("PerMonth".equals(userFrequency)) {
				dateMap = d.getThisMonthTime();
			} else if ("PerWeek".equals(userFrequency)) {
				dateMap = d.getThisWeekTime();
			}
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("phoneNumber", phoneNumber);
			paraMap.put("StartTime", dateMap.get("Start"));
			paraMap.put("EndTime", dateMap.get("End"));
			paraMap.put("prizeName", p.getName());
			paraMap.put("prizeId", p.getPrizeId());
			ArrayList<WinnerList> aw = winnerListMapper.find(paraMap);
			Integer userFrequencyNumber = sweepStakesPrize.getFrequencyNumber();
			if (userFrequencyNumber != null) {
				if (aw != null && aw.size() >= userFrequencyNumber) {
					return false;
				} else {
					ArrayList<WinningInfo> awi = winningInfoMapper
							.getWinningInfo2(paraMap);
					if (awi != null && awi.size() > 0) {
						return false;
					}
				}
			} else
				return false;

		}
		Integer payMoneyLimit = sweepStakesPrize.getPayMoneyLimit();
		String payMoneyType = sweepStakesPrize.getPayMoneyType();
		if (payMoneyLimit != null && payMoneyLimit > 0) {
			ArrayList<String> types = new ArrayList<String>();
			if (payMoneyType != null) {
				String[] payMoneyTypes = payMoneyType.split("&");
				for (String string : payMoneyTypes) {
					types.add(string);
				}
			}
			String payMoneyTime = sweepStakesPrize.getPayMoneyTime();
			HashMap<String, Date> dateMap = null;
			if ("ThisMonth".equals(payMoneyTime)) {
				dateMap = d.getThisMonthTime();
			} else if ("ThisYear".equals(payMoneyTime)) {
				dateMap = d.getThisYearTime();
			} else if ("ThisWeek".equals(payMoneyTime)) {
				dateMap = d.getThisWeekTime();
			} else if ("ThisDay".equals(payMoneyTime)) {
				dateMap = d.getTodayTime();
			}
			if ("AllTime".equals(payMoneyTime)) {
				dateMap = new HashMap<String, Date>();
				try {
					Date d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse("2013-06-01 00:00:00");
					dateMap.put("Start", d1);
					dateMap.put("End", new Date());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("phoneNumber", phoneNumber);
			paraMap.put("types", types);
			paraMap.put("StartTime", dateMap.get("Start"));
			paraMap.put("EndTime", dateMap.get("End"));
			Float payMoney = orderInfoMapper.getThisMonthPayMoney2(paraMap);
			if (payMoney == null) {
				payMoney = (float) 0.0;
			}
			if (types.contains("103")) {
				Float money = qQPerMonthRecordMapper.getThisMonthPay(paraMap);
				if (money == null) {
					money = (float) 0.0;
				}
				payMoney = payMoney + money;
			}

			if (payMoney < payMoneyLimit) {
				return false;
			}
		}
		return true;
	}

	private ArrayList<Integer> getPrizeId(ArrayList<SweepStakesPrize> ass) {
		ArrayList<Integer> ai = new ArrayList<Integer>();
		for (SweepStakesPrize sweepStakesPrize : ass) {
			ai.add(sweepStakesPrize.getPrizeId());
		}
		return ai;
	}

	private HashMap<Integer, Integer> getPrizeInfo(ArrayList<Prize> ap) {
		HashMap<Integer, Integer> hi = new HashMap<Integer, Integer>();
		for (int i = 0; ap != null && i < ap.size(); i++) {
			hi.put(ap.get(i).getPrizeId(), i);
		}
		return hi;
	}

	private Prize findPrizeById(ArrayList<Prize> ap, Integer prizeId) {
		for (Prize prize : ap) {
			if (prize.getPrizeId() == prizeId) {
				return prize;
			}
		}
		return null;
	}
/*
	@Override
	public String addWinningInfo(HashMap<String, String> map) {
		// 查询抽奖活动每次扣积分数目，每日抽奖次数
		// 查看奖品是否还有
		// 奖品发送
		// 抽奖次数减一
		// 扣除积分
		// 赠送积分的返回最后的积分数目
		HashMap<String, Object> result = new HashMap<String, Object>();
		WinningInfo info = new WinningInfo();
		log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
				+ "->用户：" + map.get("PhoneNumber") + "->查询抽奖活动！");
		SweepStakes sweepStakes = sweepStakesMapper.getSweepStakes();
		SweepStakesPrize sweepStakesPrize = null;
		try {
			log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date())
					+ "->用户："
					+ map.get("PhoneNumber")
					+ "->查询抽奖活动奖品！");
			sweepStakesPrize = sweepStakesPrizeMapper
					.findPrizeByLotteryId(Integer.parseInt(map.get("LotteryID")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
				+ "->用户：" + map.get("PhoneNumber") + "->查询用户信息！");
		UserInfo user = userInfoMapper.findUserByPhoneNumber(map
				.get("PhoneNumber"));
		UserLotteryTimes lotteryTimes = userLotteryTimesMapper
				.findByPhoneNumber(map.get("PhoneNumber"));
		// 判断用户积分是否足够
		// 判断用户抽奖次数是否已满
		if (sweepStakes != null) {
			log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date())
					+ "->用户："
					+ map.get("PhoneNumber")
					+ "->判断抽奖活动还在进行!");
			log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date())
					+ "->用户："
					+ map.get("PhoneNumber")
					+ "->当前积分为" + user.getAllScore());
			log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date())
					+ "->用户："
					+ map.get("PhoneNumber")
					+ "->当前抽奖活动每次扣除积分为" + sweepStakes.getDeductScore());
			if (user.getAllScore() >= sweepStakes.getDeductScore()) {
				DateUtil d = new DateUtil();
				HashMap<String, Date> thisDay = d.getTodayTime();
				if (lotteryTimes != null
						&& !lotteryTimes.getLastLotteryTime().before(
								thisDay.get("Start"))
						&& lotteryTimes.getLotteryTimes() >= sweepStakes
								.getTimes()) {
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->警告！用户当天抽奖次数数据异常！");
					result.put("respCode", -21);
					result.put("respInfo", "当日抽奖次数已用完！");
					return new Gson().toJson(result);
				}

			} else {
				log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date())
						+ "->用户："
						+ map.get("PhoneNumber")
						+ "->警告！用户积分数据异常！");
				result.put("respCode", -21);
				result.put("respInfo", "您的积分不足！");
				return new Gson().toJson(result);
			}

			if (sweepStakesPrize != null
					&& sweepStakesPrize.getPrizeNumber() != null) {
				if (sweepStakesPrize.getPrizeNumber() > 0
						&& hasNotChouzhong(sweepStakesPrize)) {
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->判断抽奖活动奖品未抽完，且按照奖品中奖频率允许该用户中奖!");
					Prize prize = prizeMapper.findPrizeById(Integer
							.parseInt(map.get("LotteryID")));
					if (checkQualifications(sweepStakesPrize,
							map.get("PhoneNumber"), prize)) {
						if (SendByScore.equals(prize.getSendWay())) {
							log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.format(new Date())
									+ "->用户："
									+ map.get("PhoneNumber")
									+ "->抽到奖品为"
									+ prize.getSendScore() + "积分");
							if (user != null) {
								if (prize.getSendScore() != null) {
									log.info(new SimpleDateFormat(
											"yyyy-MM-dd HH:mm:ss")
											.format(new Date())
											+ "->用户："
											+ map.get("PhoneNumber")
											+ "->原来有积分" + user.getAllScore());
									user.setAllScore(user.getAllScore()
											+ prize.getSendScore());
									log.info(new SimpleDateFormat(
											"yyyy-MM-dd HH:mm:ss")
											.format(new Date())
											+ "->用户："
											+ map.get("PhoneNumber")
											+ "->抽到奖品为"
											+ prize.getSendScore()
											+ "积分，累计到用户积分中，此时用户积分为"
											+ user.getAllScore());
								}
							}
							result.put("Name", "积分");
							result.put("Number",
									String.valueOf(prize.getSendScore()));
							result.put("ImgName", "Big_" + prize.getImgName());
						} else if (SendByInterface.equals(prize.getSendWay())) {
							log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.format(new Date())
									+ "->用户："
									+ map.get("PhoneNumber")
									+ "->抽到奖品为"
									+ prize.getName());
							if (user != null) {
								ArrayList<OrderInfo> order = orderInfoMapper
										.findLastQBOrder(map.get("PhoneNumber"));
								if (order != null && order.size() > 0) {
									String QQNumber = order.get(0)
											.getTargetNumber();
									sendOneQB(map.get("PhoneNumber"), QQNumber);
									log.info(new SimpleDateFormat(
											"yyyy-MM-dd HH:mm:ss")
											.format(new Date())
											+ "->用户："
											+ map.get("PhoneNumber")
											+ "->调用接口进行奖品赠送！");
								}
							}
							result.put("Name", "Q币");
							result.put("Number", "1");
							result.put("ImgName", "Big_" + prize.getImgName());

						} else {
							log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.format(new Date())
									+ "->用户："
									+ map.get("PhoneNumber")
									+ "->抽到奖品为"
									+ prize.getName());
							result.put("Name", prize.getName());
							result.put("Number", "1");
							result.put("ImgName", "Big_" + prize.getImgName());
						}
						user.setAllScore(user.getAllScore()
								- sweepStakes.getDeductScore());
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())
								+ "->用户："
								+ map.get("PhoneNumber")
								+ "->扣除抽奖所需的"
								+ sweepStakes.getDeductScore()
								+ "积分,剩余"
								+ user.getAllScore());
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())
								+ "->用户："
								+ map.get("PhoneNumber") + "->更新用户在数据库中的信息");
						userInfoMapper.update(user);
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())
								+ "->用户："
								+ map.get("PhoneNumber") + "->用户在数据库中的信息更新成功");
						result.put("userScore", user.getAllScore());
						info.setLotteryId(prize.getPrizeId());
						info.setPhoneNumber(map.get("PhoneNumber"));
						info.setHasSendMessage("no");
						info.setWinTime(new Date());
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())
								+ "->用户："
								+ map.get("PhoneNumber") + "->更新数据库中抽奖奖品数量-1");
						sweepStakesPrize.setPrizeNumber(sweepStakesPrize
								.getPrizeNumber() - 1);
						sweepStakesPrizeMapper.updateStakesPrize(sweepStakesPrize);
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())
								+ "->用户："
								+ map.get("PhoneNumber") + "->数据库中抽奖活动奖品数量-1成功");
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())
								+ "->用户："
								+ map.get("PhoneNumber") + "->更新数据库中奖品数量-1");
						prize.setLeftAmount(prize.getLeftAmount() - 1);
						prizeMapper.updatePrzie(prize);
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())
								+ "->用户："
								+ map.get("PhoneNumber") + "->数据库中奖品数量-1成功");
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())
								+ "->用户："
								+ map.get("PhoneNumber") + "->添加用户中奖信息到数据库中");
						winningInfoMapper.addWinningInfo(info);
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())
								+ "->用户："
								+ map.get("PhoneNumber") + "->添加用户中奖信息到数据库中成功");
						sendMessage(user, sweepStakesPrize, info);
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())
								+ "->用户："
								+ map.get("PhoneNumber") + "->更新数据库中户当日抽奖次数+1");
						if (lotteryTimes == null) {
							lotteryTimes = new UserLotteryTimes();
							lotteryTimes.setLastLotteryTime(new Date());
							lotteryTimes.setLotteryTimes(1);
							lotteryTimes.setPhoneNumber(map.get("PhoneNumber"));
							userLotteryTimesMapper.addLotteryTimes(lotteryTimes);
							result.put("lotteryLave", sweepStakes.getTimes() - 1);
						} else {
							DateUtil d = new DateUtil();
							HashMap<String, Date> thisDay = d.getTodayTime();
							if (lotteryTimes.getLastLotteryTime().before(
									thisDay.get("Start"))) {
								lotteryTimes.setLotteryTimes(1);
								lotteryTimes.setLastLotteryTime(new Date());
								userLotteryTimesMapper
										.updateLotteryTimes(lotteryTimes);
								result.put("lotteryLave",
										sweepStakes.getTimes() - 1);
							} else {
								lotteryTimes.setLotteryTimes(lotteryTimes
										.getLotteryTimes() + 1);
								lotteryTimes.setLastLotteryTime(new Date());
								userLotteryTimesMapper
										.updateLotteryTimes(lotteryTimes);
								result.put("lotteryLave", sweepStakes.getTimes()
										- lotteryTimes.getLotteryTimes());
							}
						}
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())
								+ "->用户："
								+ map.get("PhoneNumber") + "->数据库中户当日抽奖次数+1成功");
						if (user.getAllScore() < sweepStakes.getDeductScore()) {
							result.put("lotteryLave", 0);
						}
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())
								+ "->用户："
								+ map.get("PhoneNumber")
								+ "->根据用户当日已抽奖次数以及剩余积分数判断用户当日还可抽奖次数为"
								+ result.get("lotteryLave"));
						result.put("respCode", 0);
						result.put("respInfo", sweepStakesPrize.getShow());
						return new Gson().toJson(result);
					}else{
						result.put("respCode", 0);
						result.put("respInfo", "");
						return new Gson().toJson(result);
					}
					
				} else {
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->判断抽奖活动奖品已抽完或按照奖品中奖频率不允许该用户中奖!");
					Prize p = prizeMapper.findSendMinScore();
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->查询当前赠送积分最少的奖品为"
							+ p.getName());
					SweepStakesPrize stakesPrize = sweepStakesPrizeMapper
							.findPrizeByLotteryId(p.getPrizeId());
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->抽到奖品为"
							+ p.getSendScore() + "积分");
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->原来有积分"
							+ user.getAllScore());
					user.setAllScore(user.getAllScore()
							- sweepStakes.getDeductScore() + p.getSendScore());
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->抽到奖品为"
							+ p.getSendScore()
							+ "积分，累计到用户积分中，同时扣除抽奖所需的"
							+ sweepStakes.getDeductScore()
							+ "积分，此时用户积分为"
							+ user.getAllScore());
					userInfoMapper.update(user);
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->用户在数据库中的信息更新成功");
					result.put("userScore", user.getAllScore());
					result.put("Name", "积分");
					result.put("Number", String.valueOf(p.getSendScore()));
					result.put("ImgName", "Big_" + p.getImgName());

					info.setLotteryId(p.getPrizeId());
					info.setPhoneNumber(map.get("PhoneNumber"));
					info.setWinTime(new Date());
					info.setHasSendMessage("no");
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->更新数据库中抽奖奖品数量-1");
					stakesPrize
							.setPrizeNumber(stakesPrize.getPrizeNumber() - 1);
					sweepStakesPrizeMapper.updateStakesPrize(stakesPrize);
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->数据库中抽奖活动奖品数量-1成功");
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->更新数据库中奖品数量-1");
					p.setLeftAmount(p.getLeftAmount() - 1);
					prizeMapper.updatePrzie(p);
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->数据库中奖品数量-1成功");
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->添加用户中奖信息到数据库中");
					winningInfoMapper.addWinningInfo(info);
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->添加用户中奖信息到数据库中成功");
					sendMessage(user, sweepStakesPrize, info);
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->更新数据库中户当日抽奖次数+1");
					if (lotteryTimes == null) {
						lotteryTimes = new UserLotteryTimes();
						lotteryTimes.setLastLotteryTime(new Date());
						lotteryTimes.setLotteryTimes(1);
						lotteryTimes.setPhoneNumber(map.get("PhoneNumber"));
						userLotteryTimesMapper.addLotteryTimes(lotteryTimes);
						result.put("lotteryLave", sweepStakes.getTimes() - 1);
					} else {
						DateUtil d = new DateUtil();
						HashMap<String, Date> thisDay = d.getTodayTime();
						if (lotteryTimes.getLastLotteryTime().before(
								thisDay.get("Start"))) {
							lotteryTimes.setLotteryTimes(1);
							lotteryTimes.setLastLotteryTime(new Date());
							userLotteryTimesMapper
									.updateLotteryTimes(lotteryTimes);
							result.put("lotteryLave",
									sweepStakes.getTimes() - 1);
						} else {
							lotteryTimes.setLotteryTimes(lotteryTimes
									.getLotteryTimes() + 1);
							lotteryTimes.setLastLotteryTime(new Date());
							userLotteryTimesMapper
									.updateLotteryTimes(lotteryTimes);
							result.put("lotteryLave", sweepStakes.getTimes()
									- lotteryTimes.getLotteryTimes());
						}
					}
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->数据库中户当日抽奖次数+1成功");
					if (user.getAllScore() < sweepStakes.getDeductScore()) {
						result.put("lotteryLave", 0);
					}
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->根据用户当日已抽奖次数以及剩余积分数判断用户当日还可抽奖次数为"
							+ result.get("lotteryLave"));
					result.put("respCode", 0);
					result.put("respInfo", stakesPrize.getShow());
					return new Gson().toJson(result);
				}
			} else {
				if (Integer.parseInt(map.get("LotteryID")) == 0) {
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->判断为没有中奖");
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->原来有积分"
							+ user.getAllScore());
					user.setAllScore(user.getAllScore()
							- sweepStakes.getDeductScore());
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->扣除抽奖所需的"
							+ sweepStakes.getDeductScore()
							+ "积分，此时用户积分为"
							+ user.getAllScore());
					userInfoMapper.update(user);
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->用户在数据库中的信息更新成功");
					result.put("userScore", user.getAllScore());
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->更新数据库中户当日抽奖次数+1");
					if (lotteryTimes == null) {
						lotteryTimes = new UserLotteryTimes();
						lotteryTimes.setLastLotteryTime(new Date());
						lotteryTimes.setLotteryTimes(1);
						lotteryTimes.setPhoneNumber(map.get("PhoneNumber"));
						userLotteryTimesMapper.addLotteryTimes(lotteryTimes);
						result.put("lotteryLave", sweepStakes.getTimes() - 1);
					} else {
						DateUtil d = new DateUtil();
						HashMap<String, Date> thisDay = d.getTodayTime();
						if (lotteryTimes.getLastLotteryTime().before(
								thisDay.get("Start"))) {
							lotteryTimes.setLotteryTimes(1);
							lotteryTimes.setLastLotteryTime(new Date());
							userLotteryTimesMapper
									.updateLotteryTimes(lotteryTimes);
							result.put("lotteryLave",
									sweepStakes.getTimes() - 1);
						} else {
							lotteryTimes.setLotteryTimes(lotteryTimes
									.getLotteryTimes() + 1);
							lotteryTimes.setLastLotteryTime(new Date());
							userLotteryTimesMapper
									.updateLotteryTimes(lotteryTimes);
							result.put("lotteryLave", sweepStakes.getTimes()
									- lotteryTimes.getLotteryTimes());
						}
					}
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->数据库中户当日抽奖次数+1成功");
					if (user.getAllScore() < sweepStakes.getDeductScore()) {
						result.put("lotteryLave", 0);
					}
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->根据用户当日已抽奖次数以及剩余积分数判断用户当日还可抽奖次数为"
							+ result.get("lotteryLave"));
					result.put("respCode", 0);
					String meizhongjiang = messageMapper
							.getMessageContentByUse("meizhongjiang");
					result.put("respInfo", meizhongjiang);
					return new Gson().toJson(result);
				} else {
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->判断抽奖活动奖品已抽完或按照奖品中奖频率不允许该用户中奖!");
					Prize p = prizeMapper.findSendMinScore();
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->查询当前赠送积分最少的奖品为"
							+ p.getName());
					SweepStakesPrize stakesPrize = sweepStakesPrizeMapper
							.findPrizeByLotteryId(p.getPrizeId());
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->抽到奖品为"
							+ p.getSendScore() + "积分");
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->原来有积分"
							+ user.getAllScore());
					user.setAllScore(user.getAllScore()
							- sweepStakes.getDeductScore() + p.getSendScore());
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->抽到奖品为"
							+ p.getSendScore()
							+ "积分，累计到用户积分中，同时扣除抽奖所需的"
							+ sweepStakes.getDeductScore()
							+ "积分，此时用户积分为"
							+ user.getAllScore());
					userInfoMapper.update(user);
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->用户在数据库中的信息更新成功");
					result.put("userScore", user.getAllScore());
					result.put("Name", "积分");
					result.put("Number", String.valueOf(p.getSendScore()));
					result.put("ImgName", "Big_" + p.getImgName());

					info.setLotteryId(p.getPrizeId());
					info.setPhoneNumber(map.get("PhoneNumber"));
					info.setWinTime(new Date());
					info.setHasSendMessage("no");
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->更新数据库中抽奖奖品数量-1");
					stakesPrize
							.setPrizeNumber(stakesPrize.getPrizeNumber() - 1);
					sweepStakesPrizeMapper.updateStakesPrize(stakesPrize);
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->数据库中抽奖活动奖品数量-1成功");
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->更新数据库中奖品数量-1");
					p.setLeftAmount(p.getLeftAmount() - 1);
					prizeMapper.updatePrzie(p);
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->数据库中奖品数量-1成功");
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->添加用户中奖信息到数据库中");
					winningInfoMapper.addWinningInfo(info);
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->添加用户中奖信息到数据库中成功");
					sendMessage(user, sweepStakesPrize, info);
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->更新数据库中户当日抽奖次数+1");
					if (lotteryTimes == null) {
						lotteryTimes = new UserLotteryTimes();
						lotteryTimes.setLastLotteryTime(new Date());
						lotteryTimes.setLotteryTimes(1);
						lotteryTimes.setPhoneNumber(map.get("PhoneNumber"));
						userLotteryTimesMapper.addLotteryTimes(lotteryTimes);
						result.put("lotteryLave", sweepStakes.getTimes() - 1);
					} else {
						DateUtil d = new DateUtil();
						HashMap<String, Date> thisDay = d.getTodayTime();
						if (lotteryTimes.getLastLotteryTime().before(
								thisDay.get("Start"))) {
							lotteryTimes.setLotteryTimes(1);
							lotteryTimes.setLastLotteryTime(new Date());
							userLotteryTimesMapper
									.updateLotteryTimes(lotteryTimes);
							result.put("lotteryLave",
									sweepStakes.getTimes() - 1);
						} else {
							lotteryTimes.setLotteryTimes(lotteryTimes
									.getLotteryTimes() + 1);
							lotteryTimes.setLastLotteryTime(new Date());
							userLotteryTimesMapper
									.updateLotteryTimes(lotteryTimes);
							result.put("lotteryLave", sweepStakes.getTimes()
									- lotteryTimes.getLotteryTimes());
						}
					}
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber") + "->数据库中户当日抽奖次数+1成功");
					if (user.getAllScore() < sweepStakes.getDeductScore()) {
						result.put("lotteryLave", 0);
					}
					log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date())
							+ "->用户："
							+ map.get("PhoneNumber")
							+ "->根据用户当日已抽奖次数以及剩余积分数判断用户当日还可抽奖次数为"
							+ result.get("lotteryLave"));
					result.put("respCode", 0);
					String zhongjiang = messageMapper
							.getMessageContentByUse("zhongjiang");
					result.put("respInfo", zhongjiang);
					return new Gson().toJson(result);
				}

			}
		} else {
			result.put("respCode", -21);
			result.put("respInfo", "当前无抽奖活动!");
			return new Gson().toJson(result);
		}
	}*/

	private Boolean hasNotChouzhong(SweepStakesPrize sweepStakesPrize) {
		Prize p = prizeMapper.findPrizeById(sweepStakesPrize.getPrizeId());
		if (!"SENDSCORE".equals(p.getSendWay())) {
			String frequency = sweepStakesPrize.getFrequency();
			if (frequency != null) {
				DateUtil d = new DateUtil();
				HashMap<String, Date> dateMap = null;
				if (frequency.equals("PerWeek")) {
					dateMap = d.getThisWeekTime();
				} else if (frequency.equals("PerMonth")) {
					dateMap = d.getThisMonthTime();
				} else if (frequency.equals("PerYear")) {
					dateMap = d.getThisYearTime();
				} else if (frequency.equals("PerDay")) {
					dateMap = d.getTodayTime();
				}
				HashMap<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("lotteryId", String.valueOf(p.getPrizeId()));
				paraMap.put("StartTime", dateMap.get("Start"));
				paraMap.put("EndTime", dateMap.get("End"));
				ArrayList<WinningInfo> aw = winningInfoMapper
						.getWinningInfo(paraMap);
				if (aw == null || aw.size() == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else {
			return true;
		}

	}
/*
	@Override
	public HashMap<String, String> sendOneQB(String PhoneNumber, String QQNumber) {
		String parameter = "objid=16023$paymoney=1$payid=$";
		parameter = parameter + "caller=" + PhoneNumber + "$username="
				+ QQNumber;
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "";
		java.net.URL httpUrl;
		try {
			httpUrl = new URL(payMoneyIp);
			URLConnection uc = (URLConnection) httpUrl.openConnection();
			uc.setDoOutput(true);
			uc.setDoInput(true);
			PrintWriter out = new PrintWriter(uc.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();
		} catch (Exception e1) {

		}
		result = result.replace("$", ",");
		String[] params = result.split(",");
		String key = "";
		String value = "";
		for (String a : params) {
			key = a.split("=")[0];
			try {
				value = a.split("=")[1];
				value = value.trim();
			} catch (ArrayIndexOutOfBoundsException e) {
				value = "";
			}
			map.put(key, value);
		}
		return map;

	}*/

	@Override
	public String checkWinning(HashMap<String, String> map) {
		ArrayList<WinningInfo> aw = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> ahm = new ArrayList<HashMap<String, Object>>();
		Integer pageIndex = Integer.parseInt(map.get("PageIndex"));
		Integer allSize = winningInfoMapper.getAllSize(map.get("PhoneNumber"));
		int start = (pageIndex - 1) * listSize + 1;
		int end = start + listSize - 1;
		paraMap.put("start", start);
		paraMap.put("end", end);
		paraMap.put("phoneNumber", map.get("PhoneNumber"));
		try {
			aw = winningInfoMapper.findByPhoneNumber(paraMap);
			ArrayList<Integer> ai = getPrizeID(aw);
			ArrayList<Prize> ap;
			if (ai != null && ai.size() > 0) {
				ap = prizeMapper.findByPrizeIds(ai);
			} else {
				ap = new ArrayList<Prize>();
			}

			for (WinningInfo winningInfo : aw) {
				for (Prize prize : ap) {
					if (winningInfo.getLotteryId() == prize.getPrizeId()) {
						HashMap<String, Object> tempMap = getWinningInfo(
								winningInfo, prize);
						ahm.add(tempMap);
					}
				}
			}
			Integer pageSize = allSize % listSize == 0 ? allSize / listSize
					: allSize / listSize + 1;
			result.put("pageSize", pageSize);
			result.put("respCode", 0);
			result.put("listWin", ahm);
		} catch (Exception e) {
			result.put("respCode", -23);
			result.put("respInfo", "查询数据失败!");
			e.printStackTrace();
		}
		return new Gson().toJson(result);
	}

	private ArrayList<Integer> getPrizeID(ArrayList<WinningInfo> aw) {
		ArrayList<Integer> ai = new ArrayList<Integer>();
		for (WinningInfo winningInfo : aw) {
			ai.add(winningInfo.getLotteryId());
		}
		return ai;
	}

	private HashMap<String, Object> getWinningInfo(WinningInfo winfo,
			Prize prize) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if ("SENDSCORE".equals(prize.getSendWay())) {
			map.put("Number", String.valueOf(prize.getSendScore()));
			map.put("Name", "积分");
		} else if ("INTERFACE".equals(prize.getSendWay())) {
			map.put("Number", "1");
			map.put("Name", "Q币");
		} else {
			map.put("Number", "1");
			map.put("Name", prize.getName());
		}
		map.put("Time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(winfo.getWinTime()));
		map.put("ImgName", "Small_" + prize.getImgName());

		return map;
	}

	@Override
	public Prize getPrizeByImageName(String ImgName) {
		Prize p = prizeMapper.findByImgName(ImgName);
		return p;
	}

	@Override
	public SweepStakes getSweepStakesByImageName(String ImgName) {
		SweepStakes stakes = sweepStakesMapper.getSweepStakesByImgName(ImgName);
		return stakes;
	}

	private void sendMessage(UserInfo user, SweepStakesPrize sweepStakesPrize,
			WinningInfo info) {
		String needMessage = sweepStakesPrize.getNeedMessage();
		String messageContent = "";
		if ("no".equals(needMessage)) {
			info.setHasSendMessage("无需发送短信");
			winningInfoMapper.updateWinningInfo(info.getId(),
					info.getHasSendMessage());
		} else {
			Integer prizeId = sweepStakesPrize.getPrizeId();
			String place = user.getAddress();
			ArrayList<PrizeShortMessage> ap = prizeShortMessageMapper
					.findPrizeShortMessage(prizeId, place);
			if (ap == null || ap.size() == 0) {
				info.setHasSendMessage("未找到对应短信内容");
				winningInfoMapper.updateWinningInfo(info.getId(),
						info.getHasSendMessage());
			} else {
				ArrayList<PrizeCode> apc = null;
				String content = ap.get(0).getContent();
				if ("yes".equals(sweepStakesPrize.getNeedCode())) {
					Integer x = sweepStakesPrize.getOneToMany();
					apc = prizeCodeMapper.getPrizeCode(prizeId, x);
					String code = "";
					if (apc == null || apc.size() < x) {
						info.setHasSendMessage("短信验证码不足");
						winningInfoMapper.updateWinningInfo(info.getId(),
								info.getHasSendMessage());
					} else {
						for (PrizeCode prizeCode : apc) {
							code = code + prizeCode.getCode() + "，";
						}
						Integer endIndex = code.lastIndexOf("，");
						code = code.substring(0, endIndex);
						content = content.replace("CODE", code);
						content = content.replace("TIME", apc.get(0)
								.getEffectiveTime());
						messageContent = content;
					}
				} else {
					messageContent = content;
				}
				if ("".equals(messageContent)) {
					info.setHasSendMessage("未找到对应短信内容");
					winningInfoMapper.updateWinningInfo(info.getId(),
							info.getHasSendMessage());
				} else {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("payMoneyIp", payMoneyIp);
					map.put("phoneNumber", user.getPhoneNumber());
					map.put("sendFilmMessageParameter",
							sendFilmMessageParameter);
					SendMessageThread thread = new SendMessageThread(
							messageContent, map);
					ArrayList<Integer> ai = getCodeId(apc);
					thread.setPrizCodeIds(ai);
					thread.setInfo(info);
					thread.setWinningInfoMapper(winningInfoMapper);
					thread.setPrizeCodeMapper(prizeCodeMapper);
					thread.start();
				}
			}

		}
	}
	
	private ArrayList<Integer> getCodeId(ArrayList<PrizeCode> apc) {
		ArrayList<Integer> ai = new ArrayList<Integer>();
		if (apc == null) {
			return null;
		}
		for (PrizeCode prizeCode : apc) {
			ai.add(prizeCode.getId());
		}
		return ai;
	}

}
