package com.rising.management.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.management.bean.Prize;

public interface PrizeDao {
	
	public ArrayList<Prize> getPrize(Boolean hasLeft, Integer start,Integer pageSize);

	public Integer getPrizeListSize(Boolean hasLeft);

	public ArrayList<Prize> getPrizeInfo();


	public Prize getPrizeById(Integer prizeId);

	public void leftAmountDownOne(Integer prizeId);
	
	public void savePrize(Prize p);
	
	public void updatePrize(Prize p);
	
	public void deleteProze(ArrayList<Integer> prizeIds);
	
	public HashMap<String,Object> getPrizeIdAndName();

}
