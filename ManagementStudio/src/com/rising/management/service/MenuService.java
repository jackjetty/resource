package com.rising.management.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.Menu;

public interface MenuService {
	
	public ArrayList<Menu> getManagerMenu();

	public HashMap<String, Object> getMenu(String id, String text, Integer pageSize, Integer pageIndex);

	public HashMap<String, Object> addMenu(Menu m);

	public HashMap<String, Object> updateMenu(Menu m);

	public HashMap<String, Object> removeMenu(String menuIds);

	public HashMap<String, Object> getAllMenu(Integer roleId);
	
	public ArrayList<Menu> getMenuById(String id);
	
	public HashMap<String,Object> getTextAndId();
	
	public ArrayList<Menu> getMenuByText(String text);
	
	public ArrayList<Menu> getMenuLikeId(String id);
}
