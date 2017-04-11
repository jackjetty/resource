package com.traffic.dao;

import com.traffic.pojo.TbUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public interface UserDao extends BaseDao<TbUser> { 
	public TbUser findUserByOpenId(String openId);
	public boolean IsSimplifyProcess(String openId);  
	 
	public ArrayList<TbUser> getUser(String userId, Integer start,
			Integer pageSize);

	public List<TbUser> getScribeWeUser(); 
	
	public Integer getUserSize(String userId);

	public ArrayList<TbUser> getAll();

	public void addUser(TbUser tu);

	public void updateUser(TbUser tu,String oldUserId);

	public void deleteById(String idObj);

	public TbUser getUserById(String idObj);

	//public HashMap<String, Object> getNickName();

	public void updateUserStatus(TbUser a);

	public void updateUserName(String userId, String id, String roleName);

	public HashMap<String, Object> getUserName();

	public ArrayList<TbUser> getUserIds();

	public HashMap<String, Object> getPhoneNumber();

	public TbUser getUserByOpenId(String openId);
	public String  getUserPhoneNumber(String openId);

}