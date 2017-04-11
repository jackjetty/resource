package com.rising.management.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.Employee;
import com.rising.management.dao.EmployeeDao;

@Component("employeeDao")
public class EmployeeDaoImpl extends BaseDaoImpl implements EmployeeDao{

	@Override
	public Integer getEmoloyeeByDepartmentIdSize(String departmentNo) {
		Query query = null;
		if("NO.000".equals(departmentNo)){
			String hql="select count(*) from Employee";
			query = getSession().createQuery(hql);
		}else {
			String hql="select count(*) from Employee where departmentId= :departmentNo";
			query = getSession().createQuery(hql);
			query.setParameter("departmentNo", departmentNo);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Employee> getEmployeeByDepartmentId(String departmentNo,
			Integer start, Integer pageSize) {
		ArrayList<Employee> em=new ArrayList<Employee>();
		if("NO.000".equals(departmentNo)){
			String hql="from Employee";
			Query query = getSession().createQuery(hql);
			query.setFirstResult(start);
			query.setMaxResults(pageSize);
			em=(ArrayList<Employee>) query.list();
		}else{
			String hql="from Employee where departmentId= :departmentNo";
			Query query = getSession().createQuery(hql);
			query.setParameter("departmentNo", departmentNo);
			query.setFirstResult(start);
			query.setMaxResults(pageSize);
			em=(ArrayList<Employee>) query.list();
		}
		return em;
	}

	@Override
	public void addEmployee(Employee emp) {
		getSession().save(emp);
		getSession().flush();
	}

	@Override
	public void updateEmployee(Employee emp) {
		getSession().update(emp);
		getSession().flush();
	}

	@Override
	public void removeEmployee(ArrayList<Integer> aus) {
		String hql = "delete from Employee where id in (:aus)";
		Query query  = getSession().createQuery(hql);
		query.setParameterList("aus",aus);
		query.executeUpdate();
		
	}

}
