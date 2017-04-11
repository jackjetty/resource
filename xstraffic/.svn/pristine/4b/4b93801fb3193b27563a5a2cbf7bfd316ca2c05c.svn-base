package com.traffic.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.TbOperateLogDao;
import com.traffic.pojo.TbOperateRecord;
import com.traffic.pojovo.TbOperateRecordVo;
import com.traffic.web.service.OperateRecordService;
@Service("operateRecordService")
public class OperateRecordServiceImpl implements OperateRecordService {
	@Autowired
	TbOperateLogDao operateRecordDao;
	@Override
	public HashMap<String, Object> getOperateRecord(String startTime,
			String endTime, Integer pageIndex, Integer pageSize) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Date startDate = null;
		Date endDate = null;
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
		Integer listSize = operateRecordDao.getOperateRecordSize(startDate, endDate);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbOperateRecord> ta = operateRecordDao.getOperateRecord(startDate,endDate,start, pageSize);
		ArrayList<TbOperateRecordVo> tav = new ArrayList<TbOperateRecordVo>();
		for (int i = 0; ta != null && i < ta.size(); i++) {
			TbOperateRecordVo order = new TbOperateRecordVo(ta.get(i));
			tav.add(order);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows",tav);
		resultMap.put("respCode", 0);
		return resultMap;
	}

}
