package com.traffic.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.traffic.dao.MenuDao;
import com.traffic.dao.RoleRightDao;
import com.traffic.pojo.TbMenu;
import com.traffic.pojo.TbRoleRight;
import com.traffic.pojo.TbUser;
import com.traffic.pojovo.TreeMenu;
import com.traffic.web.service.MenuService;
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	Log log = LogFactory.getLog(MenuServiceImpl.class);
	@Autowired
	MenuDao menuDao;
	@Autowired
	RoleRightDao roleRightDao;
	@Override
	public HashMap<String, Object> getAllMenu(String roleId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<TbMenu> am;
		ArrayList<TbRoleRight> ar;
		try {
			am = menuDao.getAllMenu();
			ar = roleRightDao.getByRoleId(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取菜单时出现异常！" + e.toString());
			resultMap.put("respInfo", "获取菜单时出现异常！");
			return resultMap;
		}
		ArrayList<TreeMenu> ATM = fillInInformation(am, ar);
		resultMap.put("TreeMenu", ATM);
		return resultMap;
	}
	private ArrayList<TreeMenu> fillInInformation(ArrayList<TbMenu> am,
			ArrayList<TbRoleRight> ar) {
		ArrayList<TreeMenu> atm = new ArrayList<TreeMenu>();
		TreeMenu treeRoot = new TreeMenu();
		ArrayList<Integer> ai = getRoleMenuIds(ar);
		TbMenu root = findRoot(am);
		if (root == null) {
			return null;
		}
		treeRoot.setId(root.getId());
		treeRoot.setText("系统功能菜单");
		treeRoot.setChildren(findNextChildren(am, root.getMenuId(), ai));
		atm.add(treeRoot);
		return atm;
	}
	private ArrayList<Integer> getRoleMenuIds(ArrayList<TbRoleRight> ar) {
		ArrayList<Integer> ai = new ArrayList<Integer>();
		for (TbRoleRight roleRight : ar) {
			ai.add(roleRight.getMenuId());
		}
		return ai;
	}
	private TbMenu findRoot(ArrayList<TbMenu> am) {
		for (TbMenu menu : am) {
			if ("0".equals(menu.getMenuId())) {
				return menu;
			} else {
				continue;
			}
		}
		return null;
	}
	private ArrayList<TreeMenu> findNextChildren(ArrayList<TbMenu> am, String id,
			ArrayList<Integer> ai) {
		ArrayList<TbMenu> amn = am;
		ArrayList<TreeMenu> atm = new ArrayList<TreeMenu>();
		for (TbMenu menu : amn) {
			Integer idlength = id.length();
			if(id.length() == 1){
				if(menu.getMenuId().length()==2){
					TreeMenu treeMenu = new TreeMenu();
					treeMenu.setId(menu.getId());
					treeMenu.setText(menu.getText());
					treeMenu.setChildren(findNextChildren(am, menu.getMenuId(), ai));
					if(treeMenu.getChildren().size()==0){
						treeMenu.setChecked(ai.contains(menu.getId()));
					}
					atm.add(treeMenu);
				}
			}
			else if ((menu.getMenuId().length() - idlength) <= 2
					&& (menu.getMenuId().length() - idlength) > 0
					&& id.equals(menu.getMenuId().substring(0, idlength))) {
				TreeMenu treeMenu = new TreeMenu();
				treeMenu.setId(menu.getId());
				treeMenu.setText(menu.getText());
				treeMenu.setChildren(findNextChildren(am, menu.getMenuId(), ai));
				if(treeMenu.getChildren().size()==0){
					treeMenu.setChecked(ai.contains(menu.getId()));
				}
				atm.add(treeMenu);
			}
		}
		return atm;
	}
	@Override
	public ArrayList<TbMenu> getManagerMenu() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		TbUser tbUser = (TbUser) session.get("Manager");
		String userId = tbUser.getUserId();
		ArrayList<TbMenu> am = menuDao.getMenuByUserId(userId);
		return am;
	}


}
