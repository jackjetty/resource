package com.detachment.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.detachment.pojo.TbDepartment;
import com.detachment.pojo.vo.TbDepartmentVo;

public interface DepartmentService {
	public HashMap<String,Object> getDepartmentIDName();

	public ArrayList<TbDepartmentVo> getAllDepartment();
	
	public HashMap<String,Object> getAllDepartments(Integer pageSize,
			Integer pageIndex);
	
	public HashMap<String,Object> addDepartment(TbDepartment td);
	
	public TbDepartment getTbDepartmentById(String departmentId);
	
	public HashMap<String,Object> updateDepartment(TbDepartment td);
	
	public HashMap<String,Object> removeDepartment(String departmentIds);
}
