package com.detachment.dao.impl;
import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.MenuDao;
import com.detachment.pojo.TbMenu;
import com.detachment.pojo.vo.TbMenuVo;
@Repository("menuDao")
public class MenuDaoImpl    extends BaseDaoImpl<TbMenu> implements  MenuDao{

	@Override
	public ArrayList<TbMenu> getMenuByRoleId(String roleId) {
		String hql = "from TbMenu where menuId in (select id.tbMenu.menuId from TbRole2menu where id.tbRole.roleId = ? and valid=true)";
		ArrayList<TbMenu> am = (ArrayList<TbMenu>) findByHQL(hql,roleId);
		return am;
	}



	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbMenuVo> getAllMenu() {
		String hql="from TbMenu";
		Query query  = getSession().createQuery(hql);
		ArrayList<TbMenu> am = (ArrayList<TbMenu>) query.list();
		ArrayList<TbMenuVo> tmv=new ArrayList<TbMenuVo>();
		for(TbMenu a:am){
			TbMenuVo tm=new TbMenuVo(a);
			tmv.add(tm);
		}
		return tmv;
	}
	 
}