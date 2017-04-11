package com.detachment.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.detachment.json.AccidentBean;
import com.detachment.json.RespObject;
import com.detachment.pojo.TbInfoPic;
import com.detachment.pojo.TbInfoText;
import com.detachment.pojo.vo.TbAccidentJsp;
import com.detachment.pojo.vo.TbAccidentStatisticsVo;
import com.detachment.pojo.vo.TbFormalAccidentVo;


public interface AccidentService {

	public HashMap<String, Object> getAccident(String startTime, String endTime,String accidentState,
			String reportPhoneNumber, String nickName, Integer pageSize,
			Integer pageIndex, String emergencyCall, String departmentId);

	public ArrayList<TbInfoText> getTbAccidentText(String accidentId);
	
	public ArrayList<TbAccidentJsp> getAccidentJsp(String openId,Integer pageSize, Integer pageIndex);

	public HashMap<String, Object> getTbAddress(String accidentId);

	public HashMap<String, Object> saveprocedureMessage(String managerName,
			String policeOpnContent);

	public ArrayList<TbInfoPic> getTbAccidentPic(String accidentId);

	public ArrayList<TbFormalAccidentVo> getAccident(String nickName,
			String reportPhoneNumber, String emergencyCall, String startTime,
			String endTime, String departmentId,String accidentState);

	public HashMap<String, Object> reDevelopDepartment(String departmentId,
			String formlAccidentIds);

	public HashMap<String, Object> responseWei(String accidentId,String state);

	public HashMap<String, Object> getAccidentNumber();
	
	public TbFormalAccidentVo getAccidentByAccidentId(String accidentId);
	
	public HashMap<String, Object> updateAccidentStateMalicious(String managerName, String accidentId);
	
	public HashMap<String, Object> updateAccidentStateOther(String accidentState,String accidentId);
	
	public ArrayList<TbAccidentStatisticsVo> getAccidentStatistics(String startTime, String endTime);
	
	public RespObject saveTbFormalAccident(AccidentBean tbFormalAccident);

	public RespObject updateTbFormalAccident(AccidentBean bean);

}
