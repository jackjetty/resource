package com.rising.mobilepayment.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.mobilepayment.bean.Prize;
import com.rising.mobilepayment.bean.SweepStakes;
import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.LotteryService;
import com.rising.mobilepayment.service.OperateLogService;

@Controller
@RequestMapping("/lottery")
public class LotteryAction extends BaseAction {

	Log log = LogFactory.getLog(LotteryAction.class);

	@Autowired
	OperateLogService operateLogService;

	@Autowired
	LotteryService lotteryService;

	@RequestMapping("/getInfo")
	public void getInfo(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		Date d = new Date();
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog(PhoneNumber, "GetLotteryInfo",userAgent);
			log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d)+"->用户："+PhoneNumber+"获取抽奖奖品信息");
		} catch (Exception e) {
			log.error("getInfo()->用户获取抽奖信息时插入操作记录失败！" + e.toString());
		}
		String resultJson = lotteryService.getInfo(PhoneNumber);
		log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d)+"->用户："+PhoneNumber+"获取抽奖奖品信息为："+resultJson);
		writeJSONString(resultJson, response);
	}
/*
	@RequestMapping("/winning")
	public void winning(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		Date d = new Date();
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog(PhoneNumber, "AddWinningInfo",userAgent);
			log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d)+"->用户："+PhoneNumber+"抽奖奖品信息为："+mapToString(map));
		} catch (Exception e) {
			log.error("winning()->用户抽奖时插入操作记录失败！" + e.toString());
		}
		String resultJson = lotteryService.addWinningInfo(map);
		log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d)+"->用户："+PhoneNumber+"实际中奖奖品信息为："+resultJson);
		writeJSONString(resultJson, response);
	}*/

	@RequestMapping("/check")
	public void check(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> map = getParameter(request);
		String PhoneNumber = map.get("PhoneNumber");
		String userAgent = request.getHeader("user-agent");
		try {
			operateLogService.addOperateLog(PhoneNumber, "CheckWinning",userAgent);
		} catch (Exception e) {
			log.error("check()->用户查询抽奖信息时插入操作记录失败！" + e.toString());
		}
		String resultJson = lotteryService.checkWinning(map);
		writeJSONString(resultJson, response);
	}

	@RequestMapping("/getImage")
	public void getImage(HttpServletRequest request,
			HttpServletResponse response) {
		byte[] image = null;
		HashMap<String, String> map = getParameter(request);
		Prize prize = lotteryService.getPrizeByImageName(map.get("ImgName").split("_")[1]);
		if (map.get("ImgName").startsWith("Small")) {
			image = prize.getImg1();
		} else {
			image = prize.getImg2();
		}
		try {
			ServletOutputStream out = response.getOutputStream();
			out.write(image);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error("getImage()->图片输出时出错!" + e.toString());
		}
	}

	@RequestMapping("/getActiveImage")
	public void getActiveImage(HttpServletRequest request,
			HttpServletResponse response) {
		byte[] image = null;
		HashMap<String, String> map = getParameter(request);
		SweepStakes stakes = lotteryService.getSweepStakesByImageName(map
				.get("ImgName"));
		if (map.get("ImgName").startsWith("before")) {
			image = stakes.getLotteryImgBefore();
		} else if (map.get("ImgName").startsWith("after")) {
			image = stakes.getLotteryImgAfter();
		}
		try {
			ServletOutputStream out = response.getOutputStream();
			out.write(image);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error("getActiveImage()->图片输出时出错!" + e.toString());
		}
	}
}
