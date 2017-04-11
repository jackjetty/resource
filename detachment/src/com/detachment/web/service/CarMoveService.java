package com.detachment.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.detachment.pojo.TbInfoPic;
import com.detachment.pojo.vo.TbCarMoveVo;


public interface CarMoveService {
	public HashMap<String, Object> getCarMove(String startTime, String endTime,
			String reportPhoneNumber, String carNumber, String carMoveState,
			Integer pageSize, Integer pageIndex);

	public ArrayList<TbInfoPic> getCarMovePic(String carMoveId);

	public HashMap<String, Object> getMoveCarAddress(String carMoveId);

	public HashMap<String, Object> exchangeStatus(String carMoveId,String managerName);

	public HashMap<String, Object> saveFailInfo(String carMoveId,
			String managerName, String remark);
	
	public TbCarMoveVo getCarMoveByCarMoveId(String carMoveId);
	
	public HashMap<String, Object> saveprocedureMessage1(String managerName,
			String carMoveId);
	
	public HashMap<String, Object> responseWei(String carMoveId);
}
