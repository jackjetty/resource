package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.Employee;
import com.rising.management.dao.DepartmentDao;
import com.rising.management.dao.EmployeeDao;
import com.rising.management.service.EmployeeService;
import com.rising.management.vo.EmployeeVo;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
	Log log = LogFactory.getLog(EmployeeServiceImpl.class);
	
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	DepartmentDao departmentDao;
	
	@Override
	public HashMap<String, Object> getEmployeeByDepartmentNo(
			String departmentNo, Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize =employeeDao.getEmoloyeeByDepartmentIdSize(departmentNo);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<Employee> em=employeeDao.getEmployeeByDepartmentId(departmentNo, start, pageSize);
		ArrayList<EmployeeVo> empvo=new ArrayList<EmployeeVo>();
		HashMap<String, Object> dtMap=departmentDao.getDepartmentNameNo();
		for(int i=0;em!=null &&i<em.size();i++){
			EmployeeVo emv=new EmployeeVo(em.get(i),dtMap);
			empvo.add(emv);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", empvo);
		return resultMap;
	}

	@Override
	public Integer getEmoloyeeByDepartmentNoSize(String departmentNo) {
		Integer in =employeeDao.getEmoloyeeByDepartmentIdSize(departmentNo);
		return in;
	}

	@Override
	public HashMap<String, Object> addEmployee(Employee emp) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			employeeDao.addEmployee(emp);
			result.put("respCode", 0);
			result.put("respInfo", "员工信息添加成功!");
		} catch (Exception e) {
			log.error("添加员工信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加员工信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> updateEmoloyee(Employee emp) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			employeeDao.updateEmployee(emp);
			result.put("respCode", 0);
			result.put("respInfo", "员工信息修改成功!");
		} catch (Exception e) {
			log.error("修改员工信息时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "修改员工信息时出现异常!");
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> removeEmployee(String employeeIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> ai = new ArrayList<Integer>();
			String[] ids = employeeIds.split(",");
			for (String id : ids) {
				try {
					Integer i = Integer.parseInt(id);
					ai.add(i);
				} catch (Exception e) {
					continue;
				}
			}
			employeeDao.removeEmployee(ai);
			result.put("respCode", 0);
			result.put("respInfo", "员工信息删除成功!");
		} catch (Exception e) {
			log.error("删除员工信息时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除员工信息时出现异常!");
		}
		return result;
	}

}
