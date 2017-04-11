package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.rising.management.bean.Manager;
import com.rising.management.bean.Menu;
import com.rising.management.bean.RoleRight;
import com.rising.management.dao.MenuDao;
import com.rising.management.dao.RoleRightDao;
import com.rising.management.service.MenuService;
import com.rising.management.vo.TreeMenu;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	Log log = LogFactory.getLog(MenuServiceImpl.class);

	@Autowired
	MenuDao menuDao;

	@Autowired
	RoleRightDao roleRightDao;

	@Override
	public ArrayList<Menu> getManagerMenu() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager manager = (Manager) session.get("Manager");
		Integer managerId = manager.getManagerId();
		ArrayList<Menu> am = menuDao.getMenuByManagerId(managerId);
		return am;
	}
	

	@Override
	public HashMap<String, Object> getMenu(String id, String text,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if ("".equals(id)) {
			id = null;
		}
		if ("".equals(text)) {
			text = null;
		}
		Integer listSize = menuDao.getMenuListSize(id, text);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<Menu> am = menuDao.getMenu(id, text, start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", am);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addMenu(Menu m) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Menu> am=menuDao.getMenuById(m.getId());
			if(am.size()!=0){
				result.put("respCode", -1);
				result.put("respInfo", "菜单ID已存在!");
			}else{
				menuDao.addMenu(m);
				result.put("respCode", 0);
				result.put("respInfo", "功能菜单添加成功!");
			}
			
			
		} catch (Exception e) {
			log.error("添加Menu时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加Menu时出现异常!");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> updateMenu(Menu m) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try {
			ArrayList<Menu> am=menuDao.getMenuById(m.getId());
			Map<String, Object> session = ActionContext.getContext().getSession();
			String menuId=(String) session.get("MenuId");
			String MenuGrade=(String) session.get("MenuGrade");
			ArrayList<Menu> listMenu=getMenuLikeId(menuId);
			int length=listMenu.size();
			if(am.size()!=0&&(!am.get(0).getMenuId().equals(m.getMenuId()))){
				result.put("respCode", -1);
				result.put("respInfo", "菜单ID已存在!");
			}else if(length>1&&!MenuGrade.equals(m.getGrade())){
				result.put("respCode", -5);
				result.put("respInfo", "不能修改等级!此菜单存在子菜单!");
			}else{
				for(Menu mm:listMenu){
					if(mm.getId().length()==4){
						String aa=m.getId()+mm.getId().substring(2, 4);
						mm.setId(aa);
						menuDao.updateMenu(mm);
					}
				}
				menuDao.updateMenu(m);
				if(!menuId.equals(m.getId())){
					for(Menu mm:listMenu){
						roleRightDao.deleteRoleRightByMenuId(mm.getMenuId());
					}
				}
				result.put("respCode", 0);
				result.put("respInfo", "功能菜单修改成功!");
			}
			session.remove("MenuId");
			session.remove("MenuGrade");
		} catch (Exception e) {
			log.error("修改Menu时出现异常!" + e.toString());
			e.printStackTrace();
			result.put("respCode", -3);
			result.put("respInfo", "修改Menu时出现异常!");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> removeMenu(String menuIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> ai = new ArrayList<Integer>();
			String[] ids = menuIds.split(",");
			for (String id : ids) {
				try {
					Integer i = Integer.parseInt(id);
					ai.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			menuDao.removeMenu(ai);
			result.put("respCode", 0);
			result.put("respInfo", "功能菜单删除成功!");
		} catch (Exception e) {
			log.error("删除Menu时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除Menu时出现异常!");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> getAllMenu(Integer roleId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<Menu> am;
		ArrayList<RoleRight> ar;
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

	private ArrayList<TreeMenu> fillInInformation(ArrayList<Menu> am,
			ArrayList<RoleRight> ar) {
		ArrayList<TreeMenu> atm = new ArrayList<TreeMenu>();
		TreeMenu treeRoot = new TreeMenu();
		ArrayList<Integer> ai = getRoleMenuIds(ar);
		Menu root = findRoot(am);
		if (root == null) {
			return null;
		}
		treeRoot.setId(root.getMenuId());
		treeRoot.setText("系统功能菜单");
		treeRoot.setChildren(findNextChildren(am, root.getId(), ai));
		atm.add(treeRoot);
		return atm;
	}

	private Menu findRoot(ArrayList<Menu> am) {
		for (Menu menu : am) {
			if ("0".equals(menu.getId())) {
				return menu;
			} else {
				continue;
			}
		}
		return null;
	}

	private ArrayList<TreeMenu> findNextChildren(ArrayList<Menu> am, String id,
			ArrayList<Integer> ai) {
		ArrayList<Menu> amn = am;
		ArrayList<TreeMenu> atm = new ArrayList<TreeMenu>();
		for (Menu menu : amn) {
			Integer idlength = id.length();
			if(id.length() == 1){
				if(menu.getId().length()==2){
					TreeMenu treeMenu = new TreeMenu();
					treeMenu.setId(menu.getMenuId());
					treeMenu.setText(menu.getText());
					treeMenu.setChildren(findNextChildren(am, menu.getId(), ai));
					if(treeMenu.getChildren().size()==0){
						treeMenu.setChecked(ai.contains(menu.getMenuId()));
					}
					atm.add(treeMenu);
				}
			}
			else if ((menu.getId().length() - idlength) <= 2
					&& (menu.getId().length() - idlength) > 0
					&& id.equals(menu.getId().substring(0, idlength))) {
				TreeMenu treeMenu = new TreeMenu();
				treeMenu.setId(menu.getMenuId());
				treeMenu.setText(menu.getText());
				treeMenu.setChildren(findNextChildren(am, menu.getId(), ai));
				if(treeMenu.getChildren().size()==0){
					treeMenu.setChecked(ai.contains(menu.getMenuId()));
				}
				atm.add(treeMenu);
			}
		}
		return atm;
	}

	private ArrayList<Integer> getRoleMenuIds(ArrayList<RoleRight> ar) {
		ArrayList<Integer> ai = new ArrayList<Integer>();
		for (RoleRight roleRight : ar) {
			ai.add(roleRight.getMenuId());
		}
		return ai;
	}


	@Override
	public ArrayList<Menu> getMenuById(String id) {
		ArrayList<Menu> am=menuDao.getMenuById(id);
		return am;
	}
	
	@Override
	public ArrayList<Menu> getMenuByText(String text) {
		ArrayList<Menu> am=menuDao.getMenuByText(text);
		return am;
	}

	@Override
	public HashMap<String, Object> getTextAndId() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<Menu> am=menuDao.getIdAndText();
		HashMap<String,String> idText=new HashMap<String, String>();
		for(Menu mu:am){
			idText.put(mu.getId(),mu.getText());
		}
		Set<String> aa=idText.keySet();
		StringBuilder text=new StringBuilder();
		StringBuilder idCode=new StringBuilder();
		for(String a:aa){
			if(a.length()==2){
				text.append(idText.get(a)+",");
				idCode.append(a+",");
			}
		}
		
		result.put("id", idCode.toString());
		result.put("text", text.toString());
		return result;
	}


	@Override
	public ArrayList<Menu> getMenuLikeId(String id) {
		ArrayList<Menu> am=menuDao.getMenuLikeId(id);
		return am;
	}


	

	
}
