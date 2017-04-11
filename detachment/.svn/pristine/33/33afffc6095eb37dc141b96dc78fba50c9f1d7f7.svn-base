package com.detachment.web.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbMenu;
import com.detachment.pojo.vo.TbMenuVo;
import com.detachment.pojo.vo.TreeMenu;
import com.detachment.web.service.MenuService;


@Controller("menuAction")
public class MenuAction {
	
	private ArrayList<TbMenuVo> result;
	
	private String roleId;
	private ArrayList<TreeMenu> tree;
	private HashMap<String, Object> resultMap;
	
	@Autowired
	MenuService menuService;
	
	public String getMenu(){
		 
		result = menuService.getManagerMenu();
		
		return "success";
	}
	
	public String getAllMenu() {
		resultMap = menuService.getAllMenu(roleId);
		tree = (ArrayList<TreeMenu>) resultMap.get("TreeMenu");
		if (tree == null) {
			return "failed";
		} else
			return "success";
	}
	
	
	

	public ArrayList<TbMenuVo> getResult() {
		return result;
	}

	public void setResult(ArrayList<TbMenuVo> result) {
		this.result = result;
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

	
	
	
	
	
	
}
