package com.traffic.dao;

import com.traffic.pojo.TbUser;

public interface ManagerDao {
	public TbUser findManagerByName(String username);

	public void modifyPassword(TbUser m);
	

}
