package com.traffic.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.traffic.dao.AccidentDao;
import com.traffic.dao.InfoPicDao;
import com.traffic.dao.InfoTextDao;
import com.traffic.dao.MoveCarDao;
import com.traffic.dao.UserDao;
import com.traffic.pojo.TbAccident;
import com.traffic.pojo.TbInfoPic;
import com.traffic.pojo.TbInfoText;
import com.traffic.pojo.TbUser;
import com.traffic.pojovo.TbAccidentVo;
import com.traffic.pojovo.TbInfoTextVo;
import com.traffic.util.CommonUtil;
import com.traffic.util.Constant;
import com.traffic.util.GpsToBaiDu;
import com.traffic.web.service.AccidentService;

@Service("accidentService")
public class AccidentServiceImpl implements AccidentService {
	Log log = LogFactory.getLog(AccidentServiceImpl.class);
	@Autowired
	AccidentDao accidentDao;
	@Autowired
	UserDao userDao;
	@Autowired
	MoveCarDao moveCarDao;
	@Autowired
	private InfoPicDao infoPicDao;
	@Autowired
	private InfoTextDao infoTextDao;

	@Override
	public HashMap<String, Object> getAccident(String startTime,
			String endTime, String reportPhoneNumber, String nickName,
			Integer pageSize, Integer pageIndex, String reporterType,
			String accidentId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		reportPhoneNumber = CommonUtil.trim(reportPhoneNumber);
		nickName = CommonUtil.trim(nickName);
		reporterType = CommonUtil.trim(reporterType);
		accidentId = CommonUtil.trim(accidentId);
		startTime = CommonUtil.trim(startTime);
		endTime = CommonUtil.trim(endTime);
		if (CommonUtil.trim(startTime).equalsIgnoreCase("")) {
			startTime = CommonUtil.getBeforeWeekDateStrForm();
		}
		if (CommonUtil.trim(endTime).equalsIgnoreCase("")) {
			endTime = CommonUtil.getCurrentDateStrForm();
		}

		String hql = " select count(*) from TbAccident a,TbWeiUser b     ";
		String StrOrder = "";
		String StrCdt = "";
		ArrayList arraylist = new ArrayList();
		StrCdt = " where   (a.reportOpenId=b.openId) ";
		StrCdt = StrCdt.length() == 0 ? " where  " : StrCdt + " and ";
		StrCdt += "    (convert(varchar(100),a.reportTime,23) between ? and ?) ";
		arraylist.add(startTime);
		arraylist.add(endTime);
		if (reportPhoneNumber.length() > 0) {
			StrCdt = StrCdt.length() == 0 ? " where  " : StrCdt + " and ";
			StrCdt += " ( a.reportPhoneNumber  like ? ) ";
			arraylist.add("%" + reportPhoneNumber + "%");
		}
		if (nickName.length() > 0) {
			StrCdt = StrCdt.length() == 0 ? " where  " : StrCdt + " and ";
			StrCdt += " (  b.nickName  like ? ) ";
			arraylist.add("%" + nickName + "%");
		}
		if (reporterType.length() > 0) {
			StrCdt = StrCdt.length() == 0 ? " where  " : StrCdt + " and ";
			StrCdt += " (  a.reporterType=   ? ) ";
			arraylist.add(reporterType);
		}

		Integer listSize = accidentDao.findCount(hql + StrCdt, arraylist);

		StrOrder = " order by a.reportTime desc";

		hql = "select a.accidentId,a.reportOpenId,a.reportTime,a.reportPhoneNumber "
				+ ",a.partyPhoneNumber,a.reporterType,a.address,a.locationX,a.locationY,a.accidentState,a.accepter,a.remark,a.claimUserId,a.claimTime,a.policeUserId,a.policeTime,"
				+ "b.nickname,b.realName from   TbAccident a,TbWeiUser b  ";

		List mResult = accidentDao.findPage(hql + StrCdt + StrOrder,
				(pageIndex - 1) * pageSize, pageSize, arraylist);

		Object[] rows;
		TbAccidentVo tbAccidentVo;
		List<TbAccidentVo> accidentVoList = new ArrayList<TbAccidentVo>();

		for (Iterator iter = mResult.iterator(); iter.hasNext();) {
			rows = (Object[]) iter.next();
			tbAccidentVo = new TbAccidentVo();
			accidentId = CommonUtil.trim(rows[0]);
			tbAccidentVo.setAccidentId(accidentId);
			tbAccidentVo.setReportOpenId(CommonUtil.trim(rows[1]));
			tbAccidentVo.setReportTime(CommonUtil.getDateForm(rows[2],
					"yyyy-MM-dd HH:mm:ss"));
			tbAccidentVo.setReportPhoneNumber(CommonUtil.trim(rows[3]));
			tbAccidentVo.setPartyPhoneNumber(CommonUtil.trim(rows[4]));
			tbAccidentVo.setReporterType(CommonUtil.trim(rows[5]));
			tbAccidentVo.setAddress(CommonUtil.trim(rows[6]));
			tbAccidentVo.setLocationX(CommonUtil.trim(rows[7]));
			tbAccidentVo.setLocationY(CommonUtil.trim(rows[8]));
			tbAccidentVo.setAccidentState(CommonUtil.trim(rows[9]));
			tbAccidentVo.setAccepter(CommonUtil.trim(rows[10]));
			tbAccidentVo.setRemark(CommonUtil.trim(rows[11]));
			tbAccidentVo.setNickName(CommonUtil.trim(rows[16]));

			accidentVoList.add(tbAccidentVo);

			// hql="select count(*) from TbInfoPic where processId =? and recordNo = ?";
			// tbCarMoveVo.setPicInfo(new Integer(infoPicDao.findCount(hql,
			// Constant.CARMOVE,carMoveId)).toString());
			// carMoveVoList.add(tbCarMoveVo);

		}

		resultMap.put("listSize", listSize);
		resultMap.put("rows", accidentVoList);
		resultMap.put("respCode", 0);
		return resultMap;
	}

	@Override
	public ArrayList<TbInfoPic> getTbAccidentPic(String accidentId) {
		return accidentDao.getTbAccidentPic(accidentId);
	}

	@Override
	public HashMap<String, Object> getTbAddress(String accidentId) {
		ArrayList<Object[]> addr = accidentDao.getTbAddress(accidentId);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		double locationY = Double.parseDouble(addr.get(0)[0].toString());
		double locationX = Double.parseDouble(addr.get(0)[1].toString());
		String[] xy = GpsToBaiDu.ToBaiduAddress(locationX, locationY).split(
				"==");
		resultMap.put("locationX", xy[0]);
		resultMap.put("locationY", xy[1]);
		resultMap.put("address", addr.get(0)[2]);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> saveprocedureMessage(String managerName,
			String policeOpnContent, String claimOpnContent, String accidentId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			accidentDao.saveprocedureMessage(managerName, policeOpnContent,
					claimOpnContent, accidentId);
			resultMap.put("respCode", 0);
			resultMap.put("respInfo", "保存意见成功！");
		} catch (Exception e) {
			log.error("保存意见时发生异常！" + e.toString());
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "保存意见时发生异常！");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> getAccidentNumber() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str
					.toString() + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str
					.toString() + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Integer singleData = accidentDao.getSingleNumber(startDate, endDate);
		Integer doData = accidentDao.getDoNumber(startDate, endDate);
		resultMap.put("singleData", singleData);
		resultMap.put("doData", doData);
		return resultMap;

	}

	@Override
	public ArrayList<TbInfoTextVo> getTbAccidentText(String accidentId) {
		ArrayList<TbInfoText> ta = accidentDao.getTbAccidentText(accidentId);
		ArrayList<TbInfoTextVo> tav = new ArrayList<TbInfoTextVo>();
		for (int i = 0; ta != null && i < ta.size(); i++) {
			TbInfoTextVo order = new TbInfoTextVo(ta.get(i));
			tav.add(order);
		}
		return tav;
	}

	@Override
	public HashMap<String, Object> getTbNumber() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer TbNumber = accidentDao.getTbNumber();
		Integer McNumber = moveCarDao.getMcNumber();
		resultMap.put("TbNumber", TbNumber);
		resultMap.put("McNumber", McNumber);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> saveprocedureMessage1(String accidentId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		TbAccident tbAccident = accidentDao.findById(accidentId);
		if (tbAccident == null) {
			resultMap.put("respCode", 1);
			resultMap.put("respInfo", "记录不存在！");
			return resultMap;
		}
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TbUser tbUser = (TbUser) session.get("Manager");
		if (tbUser == null) {
			resultMap.put("respCode", 1);
			resultMap.put("respInfo", "请重新登录！");
			return resultMap;
		}
		tbAccident.setAccepter(tbUser.getUserId());
		tbAccident.setAccidentState("审核通过");
		accidentDao.saveOrUpdate(tbAccident);
		resultMap.put("respCode", 0);
		resultMap.put("respInfo", "审核成功！");
		return resultMap;
	}

	@Override
	public HashMap<String, Object> responseWei(String accidentId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			accidentDao.updateAccidentStateFailed(accidentId);
			map.put("respCode", 0);
			map.put("respInfo", "处理结果发送成功！");
		} catch (Exception e) {
			map.put("respCode", -1);
			map.put("respInfo", "处理结果发送失败！");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getAccidentByAccidentId(String accidentId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		TbAccident ta = accidentDao.getAccidentByAccidentId(accidentId);

		HashMap<String, Object> userName = userDao.getUserName();
		TbAccidentVo order = new TbAccidentVo(ta, userName);
		ArrayList<TbInfoText> taa = accidentDao.getTbAccidentText(accidentId);
		map.put("tbAccidentVo", order);
		map.put("infoText", taa);
		return map;
	}

	@Override
	public HashMap<String, Object> accidentDetail(String accidentId) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String hql = "select ta.accidentId,ta.accidentState,ta.address,ta.partyPhoneNumber,ta.reportPhoneNumber,ta.reportTime,ta.locationX,ta.locationY,tw.nickname,tw.realName from TbAccident as ta ,TbWeiUser as tw ";
		hql += " where ta.accidentId=? and ta.reportOpenId=tw.openId ";
		List mResult = accidentDao.findByHQL(hql, accidentId);
		ListIterator iterator = mResult.listIterator();
		if (!iterator.hasNext()) {
			return result;
		}
		Object[] rows = (Object[]) iterator.next();
		result.put("accidentId", CommonUtil.trim(rows[0]));
		result.put("accidentState", CommonUtil.trim(rows[1]));
		result.put("address", CommonUtil.trim(rows[2]));
		result.put("partyPhoneNumber", CommonUtil.trim(rows[3]));
		result.put("reportPhoneNumber", CommonUtil.trim(rows[4]));
		result.put("reportTime",
				CommonUtil.getDateForm(rows[5], "yyyy年MM月dd日 HH时mm分"));
		result.put("locationX", CommonUtil.trim(rows[6]));
		result.put("locationY", CommonUtil.trim(rows[7]));
		result.put("nickName", CommonUtil.trim(rows[8]));
		result.put("userName", CommonUtil.trim(rows[9]));
		hql = "from TbInfoPic where id.processId=? and id.recordNo=? order by id.picIndex";
		List<TbInfoPic> picProcessRecords = infoPicDao.findByHQL(hql,
				Constant.FORMALACCIDENT, accidentId);
		result.put("picProcessRecords", picProcessRecords);

		hql = "from TbInfoText where id.processId=? and id.recordNo=? order by id.textIndex";
		List<TbInfoText> textProcessRecords = infoTextDao.findByHQL(hql,
				Constant.FORMALACCIDENT, accidentId);
		result.put("textProcessRecords", textProcessRecords);
		return result;
	}

}
