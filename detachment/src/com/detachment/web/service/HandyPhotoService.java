package com.detachment.web.service;

import java.util.HashMap;

import com.detachment.pojo.vo.TbHandyPhotoVo;


public interface HandyPhotoService {
	
	
	public HashMap<String, Object> getHandyPhoto(String startTime, String endTime,
			String reportPhoneNumber, String nickName, Integer pageSize, Integer pageIndex,String handyPhotoState);

	public HashMap<String, Object> getTbAddress(String handyPhotoId);

	public HashMap<String, Object> updateState(String handyPhotoId,
			String managerName);
	
	public TbHandyPhotoVo getHandyPhotoById(String handyPhotoId);
	
	public HashMap<String, Object> saveProcedureMessageHandyPhoto(String managerName,
			String handyPhotoId);
	
	public HashMap<String, Object> responseWeiHandyPhoto(String handyPhotoId);
}
