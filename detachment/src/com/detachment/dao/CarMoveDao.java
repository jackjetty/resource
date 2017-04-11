package com.detachment.dao;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.detachment.pojo.TbCarMove; 
import com.detachment.pojo.TbInfoPic;
public interface CarMoveDao extends BaseDao<TbCarMove> {
	public String  getNextCarMoveId (String prefix); 
	public boolean isLimitReport(int interval,String carType ,String carNumber);
	public int  getDayReportCount(String openId);
	
	 

 
 

	public ArrayList<TbInfoPic> getCarMovePic(String carMoveId);

	public ArrayList<Object[]> getMoveCarAddress(String carMoveId);

	public TbCarMove getMoveCarById(String carMoveId);

	public void exchangeStatus(TbCarMove tc);

	public Integer getMcNumber();
	
	public void updateMoveCarStateSuccess(String managerName, String carMoveId);
	
	public void updateMoveCarStateFail(String carMoveId);
}