package com.detachment.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.MenuDao;
import com.detachment.dao.Role2menuDao;
import com.detachment.pojo.TbMenu;
import com.detachment.pojo.TbUser;
import com.detachment.pojo.vo.TbMenuVo;
import com.detachment.pojo.vo.TbRole2MenuVo;
import com.detachment.pojo.vo.TreeMenu;
import com.detachment.web.service.MenuService;
import com.opensymphony.xwork2.ActionContext;


@Service("menuService")
public class MenuServiceImpl implements MenuService{
	Log log = LogFactory.getLog(MenuServiceImpl.class);
	
	@Autowired
	MenuDao menuDao;
	@Autowired
	Role2menuDao role2menuDao;

	@Override
	public ArrayList<TbMenuVo> getManagerMenu() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		TbUser u =  (TbUser) session.get("User");
		String roleId = u.getTbRole().getRoleId();
		ArrayList<TbMenu> am = menuDao.getMenuByRoleId(roleId);
		ArrayList<TbMenuVo> ams=new ArrayList<TbMenuVo>();
		for(TbMenu t:am){
			TbMenuVo tb=new TbMenuVo(t);
			ams.add(tb);
		}
		return ams;
	}

	@Override
	public ArrayList<TbMenu> getManagerMenuTest(String roleId) {
		ArrayList<TbMenu> am = menuDao.getMenuByRoleId(roleId);
		return am;
	}

	@Override
	public HashMap<String, Object> getAllMenu(String roleId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<TbMenuVo> am;
		ArrayList<TbRole2MenuVo> ar;
		try {
			am = menuDao.getAllMenu();
			ar = role2menuDao.getByRoleId(roleId);
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
	
	
	private ArrayList<TreeMenu> fillInInformation(ArrayList<TbMenuVo> am,
			ArrayList<TbRole2MenuVo> ar) {
		ArrayList<TreeMenu> atm = new ArrayList<TreeMenu>();
		TreeMenu treeRoot = new TreeMenu();
		ArrayList<String> ai = getRoleMenuIds(ar);
		TbMenuVo root = findRoot(am);
		if (root == null) {
			return null;
		}
		treeRoot.setId(root.getMenuId());
		treeRoot.setText("系统功能菜单");
		treeRoot.setChildren(findNextChildren(am, root.getMenuId(), ai));
		atm.add(treeRoot);
		return atm;
	}
	private ArrayList<String> getRoleMenuIds(ArrayList<TbRole2MenuVo> ar) {
		ArrayList<String> ai = new ArrayList<String>();
		for (TbRole2MenuVo roleRight : ar) {
			ai.add(roleRight.getMenuId());
		}
		return ai;
	}
	private TbMenuVo findRoot(ArrayList<TbMenuVo> am) {
		for (TbMenuVo menu : am) {
			if ("0".equals(menu.getMenuId())) {
				return menu;
			} else {
				continue;
			}
		}
		return null;
	}
	private ArrayList<TreeMenu> findNextChildren(ArrayList<TbMenuVo> am, String id,
			ArrayList<String> ai) {
		ArrayList<TbMenuVo> amn = am;
		ArrayList<TreeMenu> atm = new ArrayList<TreeMenu>();
		for (TbMenuVo menu : amn) {
			Integer idlength = id.length();
			if(id.length() == 1){
				if(menu.getMenuId().length()==2){
					TreeMenu treeMenu = new TreeMenu();
					treeMenu.setId(menu.getMenuId());
					treeMenu.setText(menu.getText());
					treeMenu.setChildren(findNextChildren(am, menu.getMenuId(), ai));
					if(treeMenu.getChildren().size()==0){
						treeMenu.setChecked(ai.contains(menu.getMenuId()));
					}
					atm.add(treeMenu);
				}
			}
			else if ((menu.getMenuId().length() - idlength) <= 2
					&& (menu.getMenuId().length() - idlength) > 0
					&& id.equals(menu.getMenuId().substring(0, idlength))) {
				TreeMenu treeMenu = new TreeMenu();
				treeMenu.setId(menu.getMenuId());
				treeMenu.setText(menu.getText());
				treeMenu.setChildren(findNextChildren(am, menu.getMenuId(), ai));
				if(treeMenu.getChildren().size()==0){
					treeMenu.setChecked(ai.contains(menu.getMenuId()));
				}
				atm.add(treeMenu);
			}
		}
		return atm;
	}
	
	
	
}
