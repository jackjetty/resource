package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.HotGame;

public interface HotGameDao {
	public Integer getHotGameInfoSize(String packageName,String title);
	public ArrayList<HotGame> getHotGameInfo(String packageName,String title,
			Integer start,Integer pageSize);
	public void addHotGame(HotGame hg);
	public void updateHotGame(HotGame hg);
	public void removeHotGame(ArrayList<Integer> ids);
	public HotGame getHotGameById(Integer id);
}
