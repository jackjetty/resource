package com.traffic.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.traffic.pojo.TbCarMove;
import com.traffic.pojo.TbInfoPic;

public interface MoveCarDao { 

	public Integer getMoveCar(Date startDate, Date endDate, String reportPhoneNumber,
			String carNumber, String carMoveState);

	public ArrayList<TbCarMove> getMoveCar(Date startDate, Date endDate,
			String reportPhoneNumber, String carNumber, Integer start,
			Integer pageSize, String carMoveState);

	public HashMap<String, Object> getMovePic();

	public ArrayList<TbInfoPic> getCarMovePic(String carMoveId);

	public ArrayList<Object[]> getMoveCarAddress(String carMoveId);

	public TbCarMove getMoveCarById(String carMoveId);

	public void exchangeStatus(TbCarMove tc);

	public Integer getMcNumber();
	
	public void updateMoveCarStateSuccess(String managerName, String carMoveId);
	
	public void updateMoveCarStateFail(String carMoveId);

}
