package com.rising.mobilepayment.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.Business;

@Component("businessMapper")
public interface BusinessMapper {

	public ArrayList<Business> getBussinessTypes();

}
