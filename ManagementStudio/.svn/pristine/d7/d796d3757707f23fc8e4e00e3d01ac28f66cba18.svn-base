package com.rising.management.service;

import java.util.HashMap;

import com.rising.management.bean.Manager;

public interface ManagerService {
	
	public HashMap<String,Object> isManagerValidate(String username,String password,String IP);

	public HashMap<String, Object> modifyPassword(Manager m);
	
	public HashMap<String, Object> modifyPassword(String password,String newPassword);

	public HashMap<String, Object> deleteById(String managerIds);

	public HashMap<String, Object> getManagerByPage(String username,
			Integer pageSize, Integer pageIndex);

	public HashMap<String, Object> addUser(Manager m);

	public HashMap<String, Object> updateUser(Manager m);

	public HashMap<String, Object> isManagerValidate(String username,
			String password, String captcha,String IP);

}
