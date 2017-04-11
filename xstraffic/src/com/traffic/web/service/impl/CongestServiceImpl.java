package com.traffic.web.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.AccidentDao;
import com.traffic.dao.CongestDao;
import com.traffic.pojo.TbCongest;
import com.traffic.pojovo.TbCongestVo;
import com.traffic.web.service.CongestService;

@Service("congestService")
public class CongestServiceImpl implements CongestService {
	@Autowired
	CongestDao congestDao;
	@Autowired
	AccidentDao accidentDao;

	@Override
	public HashMap<String, Object> getCongest(String startTime, String endTime,
			String reportPhoneNumber, String congestState, Integer pageSize,
			Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Date startDate = null;
		Date endDate = null;
		if ("".equals(congestState)) {
			congestState = null;
		}
		if ("".equals(reportPhoneNumber)) {
			reportPhoneNumber = null;
		}
		if ("".equals(startTime)) {
			startDate = null;
		} else {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(startTime.toString() + " 00:00:00");
			} catch (ParseException e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
		if ("".equals(endTime)) {
			endDate = null;
		} else {
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(endTime.toString() + " 23:59:59");
			} catch (ParseException e) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
				e.printStackTrace();
				return resultMap;
			}
		}
		Integer listSize = congestDao.getCongestList(startDate, endDate,
				reportPhoneNumber, congestState);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbCongestVo> tav = new ArrayList<TbCongestVo>();
		HashMap<String, Object> pic = accidentDao.getPic();
		HashMap<String, Object> textContent = accidentDao.getTbAccidentText();
		ArrayList<TbCongest> ta = congestDao.getCongest(startDate, endDate,
				reportPhoneNumber, start, pageSize, congestState);
		for (int i = 0; ta != null && i < ta.size(); i++) {
			TbCongestVo tcv = new TbCongestVo(ta.get(i), pic, textContent);
			tav.add(tcv);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", tav);
		resultMap.put("respCode", 0);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> changeCgState(String congestId,
			String managerName) {
		TbCongest tc = congestDao.getCongestById(congestId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (tc == null) {
			map.put("respInfo", "要操作的用户记录被删除了，请刷新后进行其他操作！");
		}
		if (!"处理".equals(tc.getCongestState())) {
			tc.setCongestState("处理");
			tc.setAcceptTime(new Timestamp(new Date().getTime()));
			tc.setAccepter(managerName);
			congestDao.changeCgState(tc);
			map.put("respInfo", "成功处理拥堵事件!");
		} else {
			map.put("respInfo", "该拥堵事件已处理!");
		}
		return map;
	}

}
