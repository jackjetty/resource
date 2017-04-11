package com.detachment.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.detachment.pojo.TbMenu;
import com.detachment.pojo.vo.TbMenuVo;


public interface MenuService {
	public ArrayList<TbMenuVo> getManagerMenu();
	public ArrayList<TbMenu> getManagerMenuTest(String roleId);
	public HashMap<String, Object> getAllMenu(String roleId);
}
