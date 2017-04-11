package com.rising.mobilepayment.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.PrizeCode;


@Component("prizeCodeMapper")
public interface PrizeCodeMapper {

	public ArrayList<PrizeCode> getPrizeCode(Integer prizeId, Integer number);

	public void update(Integer id);
	
}
