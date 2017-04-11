package com.rising.mobilepayment.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.rising.mobilepayment.bean.WinnerList;

public interface WinnerListMapper {
	
	public String getLastWeekWinnerPhoneNumber(HashMap<String,Date> map);

	public ArrayList<WinnerList> getWinnerList();

	public ArrayList<WinnerList> find(HashMap<String, Object> paraMap);

}
