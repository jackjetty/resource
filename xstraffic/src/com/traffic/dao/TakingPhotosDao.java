package com.traffic.dao;

import java.util.ArrayList;
import java.util.Date;

import com.traffic.pojo.TbTakingPhotos;

public interface TakingPhotosDao {

	public Integer getTakingPhotosSize(Date startDate, Date endDate,
			String takingPhoneNumber, String nickName,String handyPhotoState);

	public ArrayList<TbTakingPhotos> getMoveCar(Date startDate, Date endDate,
			String takingPhoneNumber, Integer start, Integer pageSize, String nickName,String handyPhotoState);

	public ArrayList<Object[]> getTbAddress(String takingPhotosId);

	public TbTakingPhotos getTpById(String takingPhotosId);

	public void updateState(TbTakingPhotos tc);

}
