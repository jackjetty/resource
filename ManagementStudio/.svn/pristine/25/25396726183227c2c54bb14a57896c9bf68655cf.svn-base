package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.Department;
import com.rising.management.dao.DepartmentDao;

@Component("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl implements DepartmentDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Department> getAllDepartment() {
		String hql = "from Department";
		Query query  = getSession().createQuery(hql);
		ArrayList<Department> dt= (ArrayList<Department>) query.list();
		return dt;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getDepartmentNameNo() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String hql = "from Department";
		Query query  = getSession().createQuery(hql);
		ArrayList<Department> dt= (ArrayList<Department>) query.list();
		for(Department d:dt){
			map.put(d.getDepartmentNo(), d.getDepartmentName());
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Department getDepartmentByName(String departmentName) {
		String hql="from Department where departmentName = :departmentName";
		Query query  = getSession().createQuery(hql);
		query.setParameter("departmentName", departmentName);
		ArrayList<Department> dt= (ArrayList<Department>) query.list();
		if(dt.size()>0){
			return dt.get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public void addDepartment(Department dt) {
		getSession().save(dt);
		getSession().flush();
		
	}

	@Override
	public void updateDepartment(Department dt) {
		getSession().update(dt);
		getSession().flush();
		
	}

	@Override
	public void removeDepartment(String departmentNo) {
		String hql="delete from Department where departmentNo = :departmentNo";
		Query query  = getSession().createQuery(hql);
		query.setParameter("departmentNo", departmentNo);
		query.executeUpdate();
	}

}
