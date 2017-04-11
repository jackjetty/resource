package com.rising.mobilepayment.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.PublicInfo;


@Component("publicInfoMapper")
public interface PublicInfoMapper {

	public Integer getAllSize();

	public ArrayList<PublicInfo> getPublicInfo(HashMap<String, Object> map);

	public Integer getNotReadPublicInfoNumber(Integer maxReadPublicInfoId);

	public ArrayList<HashMap<String, Object>> getTotalMessage(
			HashMap<String, Object> map);

	public Integer getMaxId();

}
