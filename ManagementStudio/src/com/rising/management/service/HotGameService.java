package com.rising.management.service;

import java.util.HashMap;

import com.rising.management.bean.HotGame;

public interface HotGameService {

	public HashMap<String, Object> getHotGameInfo(String packageName,
			String title, Integer pageSize, Integer pageIndex);

	public HashMap<String, Object> addHotGame(HotGame hg);

	public HashMap<String, Object> updateHotGame(HotGame hg);

	public HashMap<String, Object> removeHotGame(String ids);

	public HotGame getHotGameById(Integer id);
}
