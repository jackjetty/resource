package com.rising.management.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.WinnerList;

public interface WinnerListDao {
	public void addWinner(WinnerList w);

	public ArrayList<WinnerList> getWinnerList(String place, Integer start,
			Integer pageSize);

	public Integer getWinnerListListSize(String place);

	public void deleteById(Integer id);

	public ArrayList<String> getWinnerPhoneNumberByPrize(Integer prizeId);

	public Integer getWinningInfoList(String phoneNumber, String winTime, Integer prizeId);

	public ArrayList<Object[]> getWinningInfo(Integer pageSize, Integer start, String phoneNumber, String winTime, Integer prizeId);

	public ArrayList<HashMap<String, Object>> getUserWinningInfo(
			ArrayList<String> phoneNumbers);

	public Integer getWinningInfoList2(String phoneNumber);

	public ArrayList<Object[]> getWinningInfoList2(Integer pageSize,
			Integer start, String phoneNumber);
}
