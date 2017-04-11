package com.traffic.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.BakPicDao;
import com.traffic.pojo.TbBakPic;
import com.traffic.pojovo.TbBakPicVo;
import com.traffic.web.service.BakPicService;
@Service("bakPicService")
public class BakPicServiceImpl implements BakPicService {
	@Autowired
	BakPicDao bakPicDao;
	@Override
	public HashMap<String, Object> getBakPic(String startTime, String endTime,
			Integer pageSize, Integer pageIndex) {
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
		Integer listSize = bakPicDao.getBakPicSize(startDate, endDate);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbBakPic> bp = bakPicDao.getBakPic(startDate,endDate,start, pageSize);
		ArrayList<TbBakPicVo> bpv = new ArrayList<TbBakPicVo>();
		for (int i = 0; bp != null && i < bp.size(); i++) {
			TbBakPicVo pv = new TbBakPicVo(bp.get(i));
			bpv.add(pv);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows",bpv);
		resultMap.put("respCode", 0);
		return resultMap;
	}
	@Override
	public HashMap<String, Object> deleteBakPic(String picIds) {
		HashMap<String,Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> is = new ArrayList<Integer>();
			String[] iss = picIds.split(",");
			for (String id : iss) {
				try {
					Integer i = Integer.parseInt(id);
					is.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			bakPicDao.deleteBakPic(is);
			result.put("respCode", 0);
			result.put("respInfo", "图片信息删除成功!");
		} catch (Exception e) {
			result.put("respCode", -3);
			result.put("respInfo", "删除图片信息时出现异常!");
		}
		return result;
	}

}
