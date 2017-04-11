package com.detachment.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.detachment.pojo.TbUser;

public interface UserService {
	public HashMap<String,Object> addUser(TbUser user,String departmentId,String roleId);
	public HashMap<String, Object> isManagerValidate(String userId,
			String password, String captcha)throws Exception;
	public HashMap<String, Object> modifyPassword(String password,
			String newPassword)throws Exception;
	public HashMap<String,Object> getUser(String userId,Integer pageSize,
			Integer pageIndex);
	public HashMap<String, Object> changeRoleIdByUserId(String userId,String roleId);
	
	public ArrayList<String> getAllUserId();
	
	public HashMap<String, Object> updateUser(TbUser user,String departmentId,String roleId,String oldUserId);

	public TbUser getUserByUserId(String userId);
	
	public HashMap<String,Object> deleteUser(String userIds);
	
	public HashMap<String, Object> getFirst(String userIds);
	
	public Integer getUserSizeTest(String userId); 
	
}
