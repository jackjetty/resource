package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.Menu;

public interface MenuDao {
	public ArrayList<Menu> getMenuByManagerId(Integer managerId);

	public Integer getMenuListSize(String id, String text);

	public ArrayList<Menu> getMenu(String id, String text, Integer start,
			Integer pageSize);

	public void addMenu(Menu m);

	public void updateMenu(Menu m);

	public void removeMenu(ArrayList<Integer> ai);

	public ArrayList<Menu> getAllMenu();

	public ArrayList<Menu> getMenuById(String id);

	public ArrayList<Menu> getIdAndText();

	public ArrayList<Menu> getMenuByText(String text);
	
	public ArrayList<Menu> getMenuLikeId(String id);
}
