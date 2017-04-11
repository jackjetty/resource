package com.detachment.web.service;

import java.util.HashMap;

import com.detachment.pojo.TbFullScode;
import com.detachment.pojo.vo.TbFullScodeVo;

public interface FullScodeService {
	public TbFullScodeVo getTbFullScodeByIdCard(String identityCard);
	
	public HashMap<String,Object> saveTbFulScode(TbFullScode tfs);
	
	public HashMap<String,Object> toFullScodeLogin(String identityCard,String fileNum,String openId,
			String userName,String phoneNumber);
	
	public HashMap<String,Object> saveFirstStudyTime(String identityCard,String openId);
	
	public HashMap<String,Object> getTotalHours(String identityCard);
	
	
	public  HashMap<String,Object>  getFullScode(String identityCard,String fileNum,String totalHour,String state, int page, int rows);
	public  HashMap<String,Object>  deductFullScode(Integer fullScodeId);
	
	public HashMap<String,Object> AutomaticLogin(String openId);
	
	public HashMap<String,Object> getPeopleInfoByIdentityCard(String identityCard);
	
	public HashMap<String,Object> updateFucScodeInfo(String userName,String phoneNumber,String identityCard);
	
	public  HashMap<String,Object>  updateFullScodeState(Integer fullScodeId);
	
	
}
