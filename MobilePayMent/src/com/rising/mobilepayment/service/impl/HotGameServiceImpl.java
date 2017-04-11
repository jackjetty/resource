package com.rising.mobilepayment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.HotGame;
import com.rising.mobilepayment.mapper.HotGameMapper;
import com.rising.mobilepayment.service.HotGameService;

@Service("hotGameService")
public class HotGameServiceImpl implements HotGameService {

	@Autowired HotGameMapper hotGameMapper;
	
	private Integer listSize;

	public Integer getListSize() {
		return listSize;
	}

	@Value("#{propertiesReader[listSize]}")
	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}
	
	@Override
	public HotGame getHotGameByImageName(String ImgName) {
		HotGame hotGame = hotGameMapper.findByImgName(ImgName);
		return hotGame;
	}

	@Override
	public String getInfo(Integer pageIndex) {
		Integer allSize = hotGameMapper.getAllSize();
		HashMap<String,Object> map = new HashMap<String, Object>();
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		int start = (pageIndex - 1) * listSize + 1;
		int end = start + listSize - 1;
		map.put("start",start );
		map.put("end",end );
		ArrayList<HotGame> ahg = null;
		try {
			ahg = hotGameMapper.getHotGame(map);
			ArrayList<HashMap<String,Object>> ahso = getHotGameInfo(ahg);
			resultMap.put("respCode", 0);
			resultMap.put("respInfo", "");
			resultMap.put("pageIndex", pageIndex);
			Integer pageSize = allSize % listSize == 0 ? allSize
					/ listSize : allSize /listSize + 1;
			resultMap.put("pageSize", pageSize);
			resultMap.put("listGame", ahso);
		}catch(Exception e){
			resultMap.put("respCode",-206);
			resultMap.put("respInfo","数据库查询出现异常！");
		}
		String resultGson = new Gson().toJson(resultMap);
		return resultGson;
	}
	
	private ArrayList<HashMap<String,Object>> getHotGameInfo(ArrayList<HotGame> ahg){
		ArrayList<HashMap<String,Object>> ahso = new ArrayList<HashMap<String,Object>>();
		for (HotGame hotGame : ahg) {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("ImgName", "Small_"+hotGame.getIcoName());
			map.put("Name", hotGame.getTitle());
			map.put("DownloadAddr", hotGame.getApkUrl());
			map.put("Size", hotGame.getFileSize());
			map.put("Star", String.valueOf(hotGame.getStar()));
			map.put("AppName", hotGame.getPackageName());
			ahso.add(map);
		}
		return ahso;
	}

}
