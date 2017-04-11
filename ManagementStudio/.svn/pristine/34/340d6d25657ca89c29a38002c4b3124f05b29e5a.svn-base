package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.ReturnCode;

public interface ReturnCodeDao {
	public Integer getReturnCodeListSize(Integer busId, String returnCode,
			Boolean hasShow);

	public ArrayList<ReturnCode> getReturnCode(String returnCode,
			Integer busId, Boolean hasShow, Integer start, Integer pageSize);

	public void addReturnCode(ReturnCode r);

	public void deleteById(int rcId);

	public void updateReturnCode(ReturnCode r);

	public ReturnCode getReturnCodeById(Integer id);

	public String getMeannByCode(String returnCode);
	public String getShowByCode(String returnCode);

}
