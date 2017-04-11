package com.detachment.dao.impl;
import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.Role2menuDao;
import com.detachment.pojo.TbRole2menu;
import com.detachment.pojo.vo.TbRole2MenuVo;
@Repository("role2menuDao")
public class Role2menuDaoImpl    extends BaseDaoImpl<TbRole2menu> implements  Role2menuDao{


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbRole2MenuVo> getByRoleId(String roleId) {
		String hql="from TbRole2menu where id.tbRole.roleId = :roleId and valid=true";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		ArrayList<TbRole2menu> rm=(ArrayList<TbRole2menu>) query.list();
		ArrayList<TbRole2MenuVo> rmv=new ArrayList<TbRole2MenuVo>();
		for (TbRole2menu r:rm) {
			TbRole2MenuVo rmvo=new TbRole2MenuVo(r);
			rmv.add(rmvo);
		}
		return rmv;
	}


	@Override
	public void deleteTbRole2Menu(String roleId, String menuId) {
		String hql="delete from TbRole2menu where id.tbRole.roleId = :roleId and id.tbMenu.menuId = :menuId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		query.setParameter("menuId", menuId);
		query.executeUpdate();
		
	}


	@Override
	public void deleteTbRole2MenuByRoleId(String roleId) {
		String hql="delete from TbRole2menu where id.tbRole.roleId = :roleId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		query.executeUpdate();
	}
	 
}