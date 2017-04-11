package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.Manager;

public interface ManagerDao {
	
	public Manager findManagerByName(String username);

	public void modifyPassword(Manager m);

	public void deleteById(Integer managerId);

	public Integer getManagerListSize(String username);

	public ArrayList<Manager> getManager(String username, Integer start,
			Integer pageSize);

	public void addManager(Manager m);

	public void updateManager(Manager m);

}
