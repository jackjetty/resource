package com.detachment.dao;
import java.util.ArrayList;
import java.util.Date;

import com.detachment.pojo.TbFormalAccident;
import com.detachment.pojo.TbInfoPic;
public interface FormalAccidentDao extends BaseDao<TbFormalAccident> {

	public Integer getAccidentListSize(Date startDate, Date endDate,String accidentState,
			String reportPhoneNumber, String nickName, String emergencyCall, String departmentId);

	public ArrayList<TbFormalAccident> getAccident(
			Date startDate, Date endDate, String accidentState,String reportPhoneNumber,
			String nickName, Integer start, Integer pageSize,
			String emergencyCall,String departmentId);

	public ArrayList<Object[]> getTbAddress(String accidentId);

	public void saveprocedureMessage(String managerName,
			String policeOpnContent);

	public ArrayList<TbInfoPic> getTbAccidentPic(String accidentId);

	public ArrayList<TbFormalAccident> getAccident(Date startDate,
			Date endDate, String reportPhoneNumber, String nickName,
			String emergencyCall, String departmentId,String accidentState); 
	
	public ArrayList<TbFormalAccident> getAccidentJsp(String openId, Integer start, Integer pageSize);

	public void updateDepartment(String departmentId, ArrayList<String> as);

	public String getNextFormalAccidentId(String prefix );

	public void updateAccidentStateFailed(String accidentId,String state);

	public Integer getListSizeByDepartmentId(String departmentId);
	
	public TbFormalAccident getAccidentByAccidentId(String accidentId);
	
	public Integer getListSizeChuJingByDepartmentId(String departmentId);
	
	public void updateAccidentStateMalicious(String managerName, String accidentId);
}