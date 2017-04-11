package com.rising.mobilepayment.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.LimitToBusiness;

@Component("limitToBusinessMapper")
public interface LimitToBusinessMapper {
	
	public ArrayList<LimitToBusiness> getLimitByBusId(Integer busId);

	public ArrayList<Integer> getBusIdsByLimitId(Integer limitId);

}
