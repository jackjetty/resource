package com.detachment.dao;
import java.util.ArrayList;

import com.detachment.pojo.TbUser;
public interface UserDao extends BaseDao<TbUser> { 
		 public void addUser(TbUser user);
		 public TbUser findUserByUserId(String userId);
		 
		 public Integer getUserSzie(String userId);
		 public ArrayList<TbUser> getUser(String userId,Integer start,
					Integer pageSize);
		 public  ArrayList<String> getRoleIdByUserId(String userId);
		 
		 public ArrayList<TbUser> getAllUser();
		 public void updateUser(TbUser tu, String oldUserId);
		 
		 public void FirstPassword(String userId,String newpassword);
		 
		 public ArrayList<TbUser> getUserByRoleId(String roleId);
		 
		 public ArrayList<TbUser> getUserByDepartmentId(String departmentId);
		 
	 
}