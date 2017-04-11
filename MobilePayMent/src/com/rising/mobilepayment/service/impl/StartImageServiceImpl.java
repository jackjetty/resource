package com.rising.mobilepayment.service.impl;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.mobilepayment.bean.AppStartPicture;
import com.rising.mobilepayment.mapper.AppStartPictureMapper;
import com.rising.mobilepayment.service.StartImageService;

@Service("startImageService")
public class StartImageServiceImpl implements StartImageService {
	
	Log log = LogFactory.getLog(StartImageServiceImpl.class);

	@Autowired
	AppStartPictureMapper appStartPictureMapper;

	@Override
	public AppStartPicture getNowStartPicture(HashMap<String, String> map) {
		String pictureName = map.get("PictureName");
		AppStartPicture asp = appStartPictureMapper.find(pictureName);
		return asp;
	}

}
