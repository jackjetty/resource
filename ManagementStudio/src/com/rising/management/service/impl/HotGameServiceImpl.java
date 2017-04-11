package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.HotGame;
import com.rising.management.dao.HotGameDao;
import com.rising.management.service.HotGameService;

@Service("hotGameService")
public class HotGameServiceImpl implements HotGameService{
	Log log = LogFactory.getLog(HotGameServiceImpl.class);
	
	@Autowired
	HotGameDao hotGameDao;

	@Override
	public HashMap<String, Object> getHotGameInfo(String packageName,
			String title, Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("".equals(packageName)){
			packageName=null;
		}
		if("".equals(title)){
			title=null;
		}
		Integer listSize=hotGameDao.getHotGameInfoSize(packageName, title);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<HotGame> hgs=hotGameDao.getHotGameInfo(packageName, title, start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", hgs);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addHotGame(HotGame hg) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			hotGameDao.addHotGame(hg);
			result.put("respCode", 0);
			result.put("respInfo", "热门游戏添加成功!");
		} catch (Exception e) {
			log.error("添加信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> updateHotGame(HotGame hg) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			hotGameDao.updateHotGame(hg);
			result.put("respCode", 0);
			result.put("respInfo", "热门游戏修改成功!");
		} catch (Exception e) {
			log.error("修改信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "修改信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> removeHotGame(String ids) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> is = new ArrayList<Integer>();
			String[] iss = ids.split(",");
			for (String id : iss) {
				try {
					Integer i = Integer.parseInt(id);
					is.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			hotGameDao.removeHotGame(is);
			result.put("respCode", 0);
			result.put("respInfo", "游戏信息删除成功!");
		} catch (Exception e) {
			log.error("删除信息时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除信息时出现异常!");
		}
		return result;
	}

	@Override
	public HotGame getHotGameById(Integer id) {
		HotGame hg=hotGameDao.getHotGameById(id);
		return hg;
	}
	
}
