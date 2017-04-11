package com.rising.management.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.rising.management.bean.Menu;
import com.rising.management.service.MenuService;
import com.rising.management.vo.TreeMenu;

@Controller("menuAction")
public class MenuAction {
	Log log = LogFactory.getLog(MenuAction.class);

	private Integer pageSize;

	private Integer pageIndex;

	private Integer menuId;

	private String menuIds;

	private String id;

	private String text;

	private String grade;

	private String link;

	private Integer roleId;

	private String iconCls;
	@Autowired
	MenuService menuService;

	private ArrayList<Menu> result;

	private ArrayList<TreeMenu> tree;

	private HashMap<String, Object> resultMap;
	
	public String getMenuLikeId(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("MenuId",id);
		session.put("MenuGrade",grade);
		return "success";
	}
	
	public String getTextAndId(){
		resultMap=menuService.getTextAndId();
		return "success";
	}

	public String getMenu() {
		result = menuService.getManagerMenu();
		return "success";
	}

	public String getMenuByPage() {
		resultMap = menuService.getMenu(id, text, pageSize, pageIndex);
		return "success";
	}

	public String doMenu() {
		return "success";
	}

	public String addMenu() {
		Menu m = new Menu();
		m.setId(id);
		m.setGrade(grade);
		m.setLink(link);
		m.setText(text);
		m.setIconCls(iconCls);
		resultMap = menuService.addMenu(m);
		return "success";
	}

	public String updateMenu() {
		Menu m = new Menu();
		m.setMenuId(menuId);
		m.setId(id);
		m.setGrade(grade);
		m.setLink(link);
		m.setText(text);
		m.setIconCls(iconCls);
		resultMap = menuService.updateMenu(m);
		return "success";
	}

	public String removeMenu() {
		resultMap = menuService.removeMenu(menuIds);
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

	public ArrayList<Menu> getResult() {
		return result;
	}

	public void setResult(ArrayList<Menu> result) {
		this.result = result;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public ArrayList<TreeMenu> getTree() {
		return tree;
	}

	public void setTree(ArrayList<TreeMenu> tree) {
		this.tree = tree;
	}

}
