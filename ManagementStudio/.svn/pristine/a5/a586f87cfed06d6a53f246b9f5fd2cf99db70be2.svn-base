package com.rising.management.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.Department;
import com.rising.management.vo.TreeDepartment;

public interface DepartmentService {
	public HashMap<String,Object> getAllDepartment();
	
	public ArrayList<TreeDepartment> fillInInformation(ArrayList<Department> dt);
	
	public Department findRoot(ArrayList<Department> dt);
	
	public ArrayList<TreeDepartment> findNextChildren(ArrayList<Department> dt,String departmentNo);
	
	public HashMap<String, Object> getDepartmentNameAndNo();
	
	public HashMap<String, Object> getDepartmentByName(String departmentName);
	
	public HashMap<String, Object> addDepartment(Department dt);
	
	public HashMap<String, Object> updateDepartment(Department dt);
	
	public HashMap<String, Object> removeDepartment(String departmentNo);
}
