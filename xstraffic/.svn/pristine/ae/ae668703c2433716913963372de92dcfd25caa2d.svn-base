package com.traffic.web.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.pojo.TbMenu;
import com.traffic.pojovo.TreeMenu;
import com.traffic.web.service.MenuService;

@Controller("menuAction")
public class MenuAction {

	@Autowired
	MenuService menuService;

	private String roleId;
	private ArrayList<TreeMenu> tree;
	private ArrayList<TbMenu> result;
	private HashMap<String, Object> resultMap;

	public String getMenu() {
		result = menuService.getManagerMenu();
		return "success";
	}

	@SuppressWarnings("unchecked")
	public String getAllMenu() {
		resultMap = menuService.getAllMenu(roleId);
		tree = (ArrayList<TreeMenu>) resultMap.get("TreeMenu");
		if (tree == null) {
			return "failed";
		} else
			return "success";
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public ArrayList<TreeMenu> getTree() {
		return tree;
	}

	public void setTree(ArrayList<TreeMenu> tree) {
		this.tree = tree;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public ArrayList<TbMenu> getResult() {
		return result;
	}

	public void setResult(ArrayList<TbMenu> result) {
		this.result = result;
	}

}
