package com.rising.mobilepayment.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.rising.mobilepayment.bean.SalesInformation;

public interface SalesInformationService {

	public String findSalesInformationById(ArrayList<Integer> inforIds);

	public SalesInformation getSalesInforImageById(HashMap<String, Object> param);

	public String getValiSalesInfo2(Integer pageIndex, Integer pageSize);

}
