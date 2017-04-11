package com.traffic.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.traffic.pojo.TbAccident;
import com.traffic.pojo.TbInfoPic;
import com.traffic.pojo.TbInfoText;
public interface AccidentDao extends BaseDao<TbAccident> {
	public String  getNextAccidentId (String prefix);  
	public Integer getAccidentListSize(Date startTime,Date endTime, String reportPhoneNumber,String nickName,String reporterType);

	 
	public ArrayList<TbInfoPic> getTbAccidentPic(String accidentId);
	public ArrayList<Object[]> getTbAddress(String accidentId);

	public HashMap<String,Object> getPic();

	public HashMap<String,Object> getVoice();

	public void saveprocedureMessage(String managerName,String policeOpnContent,String claimOpnContent, String accidentId);

	public Integer getDoNumber(Date startDate,Date endDate);

	public Integer getSingleNumber(Date startDate,Date endDate);

	public HashMap<String, Object> getPoliceOpnContent();

	public HashMap<String, Object> getClaimOpnContent();

	public ArrayList<TbInfoText> getTbAccidentText(String accidentId);

	public HashMap<String, Object> getTbAccidentText();

	public Integer getTbNumber();

	public void saveprocedureMessage1(String managerName, String accidentId);
	
	public void updateAccidentStateFailed(String accidentId);
	
	public TbAccident getAccidentByAccidentId(String accidentId);
	
	/*public Integer getAccidentListSize(String startTime, String endTime,
			String reportPhoneNumber, String nickName, String reporterType);*/
	
	 
}