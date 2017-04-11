package com.traffic.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.UserDao;
import com.traffic.pojo.TbUser;
import com.traffic.util.CommonUtil;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<TbUser> implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public Integer getUserSize(String userId) {
		String hql = "from TbUser where (roleId is null or roleId <> 'R10') and (userId <> 'wch') ";
		Query query = null;
		if (userId != null) {
			hql = hql + " and (userId = :userId) ";
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
	public ArrayList<TbUser> getUser(String userId, Integer start,
			Integer pageSize) {
		String hql = "from TbUser  where (roleId is null or roleId <> 'R10') and (userId <> 'wch')";
		Query query = null;
		if (userId != null) {
			hql = hql + " and (userId = :userId) ";
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
	public ArrayList<TbUser> getAll() {
		String hql = "from TbUser";
		Query query = getSession().createQuery(hql);
		ArrayList<TbUser> tu = (ArrayList<TbUser>) query.list();
		return tu;
	}

	@Override
	public void addUser(TbUser tu) {
		getSession().save(tu);
		getSession().flush();
	}

	@Override
	public void updateUser(TbUser tu, String oldUserId) {
		String hql = "update TbUser set userId = :userId, remark = :remark,userName = :userName,phoneNumber = :phoneNumber  where userId='" + oldUserId + "'";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", tu.getUserId()); 
		query.setParameter("remark", tu.getRemark());
		query.setParameter("userName", tu.getUserName());
		query.setParameter("phoneNumber", tu.getPhoneNumber());
		
		query.executeUpdate();

	}

	@Override
	public void deleteById(String userId) {
		String hql = "delete from TbUser where userId = :userId";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbUser getUserById(String userId) {
		String hql = "from TbUser where userId = :userId";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", userId);
		ArrayList<TbUser> tu = (ArrayList<TbUser>) query.list();
		if (tu != null) {
			return tu.get(0);
		} else {
			return null;
		}

	}

	 

	@Override
	public void updateUserStatus(TbUser a) {
		getSession().update(a);
		getSession().flush();
	}

	@Override
	public void updateUserName(String userId, String roleId, String userName) {
		String hql = "update TbUser set roleId=:roleId where userId=:userId";
		Query query = getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		query.setParameter("userId", userId);
		query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getUserName() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String hql = "from TbUser";
		Query query = getSession().createQuery(hql);
		ArrayList<TbUser> m = (ArrayList<TbUser>) query.list();
		for (TbUser ta : m) {
			map.put(ta.getOpenId(), ta.getUserName());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbUser> getUserIds() {
		String hql = "from TbUser";
		Query query = getSession().createQuery(hql);
		ArrayList<TbUser> tu = (ArrayList<TbUser>) query.list();
		return tu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPhoneNumber() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String hql = "from TbUser";
		Query query = getSession().createQuery(hql);
		ArrayList<TbUser> m = (ArrayList<TbUser>) query.list();
		for (TbUser ta : m) {
			map.put(ta.getOpenId(), ta.getPhoneNumber());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbUser getUserByOpenId(String openId) {
		String hql = "from TbUser where openId = :openId";
		Query query = getSession().createQuery(hql);
		query.setParameter("openId", openId);
		ArrayList<TbUser> tu = (ArrayList<TbUser>) query.list();
		if (tu != null && tu.size()>0) {
			return tu.get(0);
		} else {
			return null;
		}
	}


	public TbUser findUserByOpenId(String openId){
		if(openId==null)
			 return null;
		String hql = "from TbUser where openId =?";
		List list=this.findByHQL(hql, openId);
		if(list==null||list.size()==0)
			 return null;
		if(list.get(0)==null)
			 return null; 
		return  (TbUser) list.get(0);
	}
	public boolean IsSimplifyProcess(String openId){
		TbUser tbUser =findUserByOpenId(openId);
		if(tbUser==null)
			 return false;
		if(CommonUtil.trim(tbUser.getStatus()).equalsIgnoreCase("冻结"))
			 return false;
		if(tbUser.getSimplifyProcess()!=null )
			 return tbUser.getSimplifyProcess();
		return false; 
	}
	@Override
	public String  getUserPhoneNumber(String openId) {
		TbUser tbUser=findUserByOpenId(openId);
		if(tbUser==null)
			 return "";
		return CommonUtil.trim(tbUser.getPhoneNumber()); 		
	} 
	

	@Override
	public List<TbUser> getScribeWeUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
