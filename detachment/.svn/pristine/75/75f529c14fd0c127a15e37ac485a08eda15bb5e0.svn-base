package com.detachment.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.UserDao;
import com.detachment.pojo.TbUser;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<TbUser> implements  UserDao{



	@Override
	public void addUser(TbUser user) {
		save(user);
	}


	@Override
	public TbUser findUserByUserId(String userId) {
		TbUser user=findById(userId);
		return user;
	}





	@SuppressWarnings("unchecked")
	@Override
	public Integer getUserSzie(String userId) {
		String hql="from TbUser ";
		Query query = null;
		if (userId != null) {
			hql+=" where userId = :userId ";
			query = getSession().createQuery(hql);
			query.setParameter("userId", userId);
		} else {
			query = getSession().createQuery(hql);
		}
		ArrayList<TbUser> tu = (ArrayList<TbUser>) query.list();
		return tu.size();
	}


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbUser> getUser(String userId,Integer start,
			Integer pageSize) {
		String hql="from TbUser ";
		Query query = null;
		if (userId != null) {
			hql+=" where userId = :userId ";
			query = getSession().createQuery(hql);
			query.setParameter("userId", userId);
		} else {
			query = getSession().createQuery(hql);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbUser> tu = (ArrayList<TbUser>) query.list();
		return tu;
	}



	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getRoleIdByUserId(String userId) {
		String hql = "from TbUser where userId = :userId";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", userId);
		ArrayList<TbUser> amr = (ArrayList<TbUser>) query.list();
		ArrayList<String> ai = new ArrayList<String>();
		for (TbUser managerRole : amr) {
			ai.add(managerRole.getTbRole().getRoleId());
		}
		return ai;
	}


	

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbUser> getAllUser() {
		String hql="from TbUser";
		Query query = getSession().createQuery(hql);
		ArrayList<TbUser> amr = (ArrayList<TbUser>) query.list();
		return amr;
	}


	


	@Override
	public void updateUser(TbUser tu, String oldUserId) {
		String hql = "update TbUser set userId = :userId,userName = :userName,departmentId = :departmentId,phoneNumber = :phoneNumber,roleId = :roleId  where userId='" + oldUserId + "'";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", tu.getUserId());
		query.setParameter("userName", tu.getUserName());
		query.setParameter("phoneNumber", tu.getPhoneNumber());
		query.setParameter("departmentId", tu.getTbDepartment().getDepartmentId());
		query.setParameter("roleId", tu.getTbRole().getRoleId());
		query.executeUpdate();
		
	}



	@Override
	public void FirstPassword(String userId, String newpassword) {
		String hql = "update TbUser set passWord = :newpassword  where userId= :userId";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("newpassword", newpassword);
		query.executeUpdate();
		
	}



	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbUser> getUserByRoleId(String roleId) {
		String hql="from TbUser where roleId = :roleId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		ArrayList<TbUser> tu=(ArrayList<TbUser>) query.list();
		return tu;
	}




	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbUser> getUserByDepartmentId(String departmentId) {
		String hql="from TbUser where departmentId = :departmentId";
		Query query = getSession().createQuery(hql);
		query.setParameter("departmentId", departmentId);
		ArrayList<TbUser> tu=(ArrayList<TbUser>) query.list();
		return tu;
	}


	 
}