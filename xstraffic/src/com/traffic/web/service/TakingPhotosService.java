package com.traffic.web.service;

import java.util.HashMap;

public interface TakingPhotosService {
	
	
	public HashMap<String, Object> getTakingPhotos(String startTime, String endTime,
			String takingPhoneNumber, String nickName, Integer pageSize, Integer pageIndex,String handyPhotoState);

	public HashMap<String, Object> getTbAddress(String takingPhotosId);

	public HashMap<String, Object> updateState(String takingPhotosId,
			String managerName);

}
