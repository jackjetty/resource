package com.detachment.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.DepartmentDao;
import com.detachment.dao.UserDao;
import com.detachment.pojo.TbDepartment;
import com.detachment.pojo.TbRole;
import com.detachment.pojo.TbUser;
import com.detachment.pojo.vo.TbDepartmentVo;
import com.detachment.web.service.DepartmentService;


@Service
public class DepartmentServiceImpl  implements DepartmentService{

	
	@Autowired UserDao userDao;
	
	@Autowired DepartmentDao departmentDao;
	
	@Override
	public HashMap<String, Object> getDepartmentIDName() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<TbDepartment> td=departmentDao.getAllDepartment();
		String departmentids="";
		String departmentNames="";
		for(TbDepartment d:td){
			departmentids+=d.getDepartmentId()+",";
			departmentNames+=d.getDepartmentName()+",";
		}
		map.put("departmentId", departmentids);
		map.put("departmentName", departmentNames);
		return map;
	}

	@Override
	public ArrayList<TbDepartmentVo> getAllDepartment() {
		TbUser u = (TbUser) ServletActionContext.getRequest().getSession()
				.getAttribute("managerUser");
		u = userDao.findById(u.getUserId());
		ArrayList<TbDepartment> atd = null;
		ArrayList<TbDepartmentVo> atdv = new ArrayList<TbDepartmentVo>();
		if("系统管理员".equals(u.getTbRole().getRoleName()) ||"支队管理员".equals(u.getTbRole().getRoleName()) || "R_Insurance".equals(u.getTbRole().getRoleId())){
			atd = departmentDao.getAllDepartment();
		}else{
			atd = new ArrayList<TbDepartment>();
			atd.add(u.getTbDepartment());
		}
		for (TbDepartment tbDepartment : atd) {
			atdv.add(new TbDepartmentVo(tbDepartment));
		}
		return atdv;
	}

	@Override
	public HashMap<String, Object> getAllDepartments(Integer pageSize,
			Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize =departmentDao.getDepartmentSize();
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbDepartment> tdp=departmentDao.getAllDepartments(start, pageSize);
		ArrayList<TbDepartmentVo> tdpVo=new ArrayList<TbDepartmentVo>();
		HashMap<String,Object> result=departmentDao.getDepartmentIdAndName();
		for(TbDepartment td:tdp){
			TbDepartmentVo tbvo=new TbDepartmentVo(td,result);
			tdpVo.add(tbvo);
		}
		
		resultMap.put("listSize", listSize);
		resultMap.put("rows", tdpVo);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addDepartment(TbDepartment td) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		departmentDao.save(td);
		resultMap.put("respInfo", "添加部门成功");
		return resultMap;
	}

	@Override
	public TbDepartment getTbDepartmentById(String departmentId) {
		TbDepartment td=departmentDao.findById(departmentId);
		return td;
	}

	@Override
	public HashMap<String, Object> updateDepartment(TbDepartment td) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		TbDepartment tdp=departmentDao.findById(td.getParentId());
		if(tdp!=null&&tdp.getParentId().equals(td.getDepartmentId())){
			resultMap.put("respCode", "1");
			resultMap.put("respInfo", "不能选择自身的下级部门为上级");
		}else{
			departmentDao.update(td);
			resultMap.put("respCode", "0");
			resultMap.put("respInfo", "部门信息修改成功");
		}
		
		return resultMap;
	}

	@Override
	public HashMap<String, Object> removeDepartment(String departmentIds) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = departmentIds.split(",");
		try {
			for(String idObj : idArray){
				if(!"".equals(idObj)){
					ArrayList<TbUser> tu=userDao.getUserByDepartmentId(idObj);
					ArrayList<TbDepartment> tds=departmentDao.getDepartmentByparentId(idObj);
					if(tu.size()!=0){
						resultMap.put("respInfo", "此部门信息被用户使用中,请先删除用户");
						resultMap.put("respCode", "1");
					}else if(tds.size()!=0){
						resultMap.put("respInfo", "此部门为其他部门的上级部门,请先删除其他部门");
						resultMap.put("respCode", "2");
					}else{
						TbDepartment td=departmentDao.findById(idObj);
						departmentDao.delete(td);
						resultMap.put("respInfo", "删除部门信息成功!");
						resultMap.put("respCode", "0");
					} 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("respInfo", "删除部门信息时出现异常!");
		}
		
		return resultMap;
	}

}
