package com.traffic.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.traffic.pojo.TbUser;

public interface UserService {

	public HashMap<String, Object> getUser(String userId, Integer pageSize,
			Integer pageIndex);

	public HashMap<String, Object> addUser(TbUser tu);

	public HashMap<String, Object> updateUser(TbUser tu,String oldUserId);

	public HashMap<String, Object> deleteUser(String userIds);

	public HashMap<String, Object> getFirst(String userIds);

	public HashMap<String, Object> exchangeStatus(String userId);
	public HashMap<String, Object> exchangeStatus1(String userId);

	public ArrayList<String> getAllUserId();
	
	public HashMap<String, Object> getPhoneNumberByOpenId(String openId);

}
