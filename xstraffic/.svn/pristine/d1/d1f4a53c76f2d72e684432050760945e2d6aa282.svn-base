package com.traffic.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.traffic.pojo.TbInfoPic;
import com.traffic.pojovo.TbAccidentVo;
import com.traffic.pojovo.TbInfoTextVo;

public interface AccidentService {

	public HashMap<String, Object> getAccident(String startTime,
			String endTime, String reportPhoneNumber, String nickName,
			Integer pageSize, Integer pageIndexString, String reporterType,String accidentId);

	public ArrayList<TbInfoPic> getTbAccidentPic(String accidentId);

	public HashMap<String, Object> getTbAddress(String accidentId);

	 

	public HashMap<String, Object> saveprocedureMessage(String managerName,
			String policeOpnContent,String claimOpnContent, String accidentId);

	public HashMap<String, Object> getAccidentNumber();

	public ArrayList<TbInfoTextVo> getTbAccidentText(String accidentId);

	public HashMap<String, Object> getTbNumber();
	
	public HashMap<String, Object> saveprocedureMessage1(  String accidentId);
	
	public HashMap<String, Object> responseWei(String accidentId);
	
	public HashMap<String, Object> getAccidentByAccidentId(String accidentId);
	
	public HashMap<String, Object> accidentDetail(String accidentId);
	
	
}
