package com.detachment.dao;
import java.util.ArrayList;
import java.util.HashMap;

import com.detachment.pojo.TbDepartment;
public interface DepartmentDao extends BaseDao<TbDepartment> { 
	public ArrayList<TbDepartment> getAllDepartment();

	public String getDepartmentIdByRegion(String key);

	public HashMap<String, String> getDepartmentMap();
	
	public Integer getDepartmentSize();

	public ArrayList<TbDepartment> getAllDepartments(Integer start,
				Integer pageSize);
	public HashMap<String,Object> getDepartmentIdAndName();
	
	public ArrayList<TbDepartment> getDepartmentByparentId(String parentId);
	
}