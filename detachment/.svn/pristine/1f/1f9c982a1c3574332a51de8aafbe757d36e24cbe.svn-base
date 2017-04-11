package com.detachment.dao.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.detachment.dao.DepartmentDao;
import com.detachment.pojo.TbDepartment;
@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<TbDepartment> implements  DepartmentDao{

	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbDepartment> getAllDepartment() {
		String hql="from TbDepartment";
		Query query = getSession().createQuery(hql);
		ArrayList<TbDepartment> td=(ArrayList<TbDepartment>) query.list();
		return td;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getDepartmentIdByRegion(String key) {
		String hql="from TbDepartment where region = :key";
		Query query = getSession().createQuery(hql).setParameter("key", key);
		ArrayList<TbDepartment> td=(ArrayList<TbDepartment>) query.list();
		if(td!=null && td.size()>0){
			return td.get(0).getDepartmentId();
		}else return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, String> getDepartmentMap() {
		String hql = "from TbDepartment";
		HashMap<String,String> map = new HashMap<String, String>();
		ArrayList<TbDepartment> atd = (ArrayList<TbDepartment>) getSession().createQuery(hql).list();
		for (TbDepartment tbDepartment : atd) {
			map.put(tbDepartment.getDepartmentId(), tbDepartment.getDepartmentName());
		}
		return map;
	}


	@Override
	public Integer getDepartmentSize() {
		String hql="select count(*) from TbDepartment";
		Query query = getSession().createQuery(hql);
		Number n = (Number) query.list().get(0);
		return n.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbDepartment> getAllDepartments(Integer start, Integer pageSize) {
		String hql="from TbDepartment";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbDepartment> td=(ArrayList<TbDepartment>) query.list();
		return td;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getDepartmentIdAndName() {
		HashMap<String, Object> result=new HashMap<String, Object>();
		String hql="from TbDepartment";
		Query query = getSession().createQuery(hql);
		ArrayList<TbDepartment> td=(ArrayList<TbDepartment>) query.list();
		for(TbDepartment t:td){
			result.put(t.getDepartmentId(), t.getDepartmentName());
		}
		return result;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbDepartment> getDepartmentByparentId(String parentId) {
		String hql="from TbDepartment where parentId = :parentId";
		Query query = getSession().createQuery(hql);
		query.setParameter("parentId", parentId);
		ArrayList<TbDepartment> tds=(ArrayList<TbDepartment>) query.list();
		return tds;
	}
	 
}