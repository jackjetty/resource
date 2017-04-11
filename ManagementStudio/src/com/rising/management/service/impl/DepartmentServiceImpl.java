package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.Department;
import com.rising.management.dao.DepartmentDao;
import com.rising.management.dao.EmployeeDao;
import com.rising.management.service.DepartmentService;
import com.rising.management.vo.TreeDepartment;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
	
	Log log = LogFactory.getLog(DepartmentServiceImpl.class);
	
	@Autowired
	DepartmentDao departmentDao;
	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public HashMap<String, Object> getAllDepartment() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<Department> dt;
		try {
			dt=departmentDao.getAllDepartment();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取部门时出现异常！" + e.toString());
			resultMap.put("respInfo", "获取部门信息时出现异常！");
			return resultMap;
		}
		ArrayList<TreeDepartment> ATM = fillInInformation(dt);
		resultMap.put("TreeDepartment", ATM);
		return resultMap;
	}

	@Override
	public ArrayList<TreeDepartment> fillInInformation(ArrayList<Department> dt) {
		ArrayList<TreeDepartment> tdt=new ArrayList<TreeDepartment>();
		TreeDepartment treeRoot=new TreeDepartment();
		Department ment=findRoot(dt);
		if(ment==null){
			return null;
		}
		treeRoot.setId(ment.getDepartmentNo());
		treeRoot.setText(ment.getDepartmentName());
		treeRoot.setChildren(findNextChildren(dt,ment.getDepartmentNo()));
		tdt.add(treeRoot);
		return tdt;
	}

	@Override
	public Department findRoot(ArrayList<Department> dt) {
		for(Department d:dt){
			if("0".equals(d.getSuperId())){
				return d;
			}else{
				continue;
			}
		}
		return null;
	}


	@Override
	public ArrayList<TreeDepartment> findNextChildren(ArrayList<Department> dt,
			String departmentNo) {
		ArrayList<TreeDepartment> tdt=new ArrayList<TreeDepartment>();
		ArrayList<Department> dtt=dt;
		for(Department ment:dtt){
			if(departmentNo.equals(ment.getSuperId())){
				TreeDepartment treeRoot=new TreeDepartment();
				treeRoot.setId(ment.getDepartmentNo());
				treeRoot.setText(ment.getDepartmentName());
				treeRoot.setChildren(findNextChildren(dt,ment.getDepartmentNo()));
				tdt.add(treeRoot);
			}
		}
		
		return tdt;
	}

	@Override
	public HashMap<String, Object> getDepartmentNameAndNo() {
		HashMap<String, Object> map=new HashMap<String, Object>();
		ArrayList<Department> dt=departmentDao.getAllDepartment();
		StringBuilder departmentNo=new StringBuilder();
		StringBuilder departmentName=new StringBuilder();
		for(Department d:dt){
			departmentNo.append(d.getDepartmentNo()+",");
			departmentName.append(d.getDepartmentName()+",");
		}
		map.put("departmentNo", departmentNo.toString());
		map.put("departmentName", departmentName.toString());
		return map;
	}

	@Override
	public HashMap<String, Object> getDepartmentByName(String departmentName) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		Department ment=departmentDao.getDepartmentByName(departmentName);
		map.put("department", ment);
		return map;
	}

	@Override
	public HashMap<String, Object> addDepartment(Department dt) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			departmentDao.addDepartment(dt);
			result.put("respCode", 0);
			result.put("respInfo", "部门添加成功!");
		} catch (Exception e) {
			log.error("添加部门时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加部门时出现异常!");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> updateDepartment(Department dt) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			departmentDao.updateDepartment(dt);
			result.put("respCode", 0);
			result.put("respInfo", "部门信息修改成功!");
		} catch (Exception e) {
			log.error("修改部门时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "修改部门时出现异常!");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> removeDepartment(String departmentNo) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			Integer size=employeeDao.getEmoloyeeByDepartmentIdSize(departmentNo);
			if(size>0){
				result.put("respCode", -1);
				result.put("respInfo", "不能删除存在员工的部门!");
			}else{
				departmentDao.removeDepartment(departmentNo);
				result.put("respCode", 0);
				result.put("respInfo", "部门信息修改成功!");
			}
		} catch (Exception e) {
			log.error("修改部门时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "修改部门时出现异常!");
		}
		return result;
	}

}













