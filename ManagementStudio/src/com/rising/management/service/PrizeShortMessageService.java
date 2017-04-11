package com.rising.management.service;

import java.util.HashMap;

import com.rising.management.bean.PrizeShortMessage;

public interface PrizeShortMessageService {

	public HashMap<String, Object> getPrizeShortMessage(Integer pageIndex,
			Integer pageSize, Integer prizeId, String place);

	public HashMap<String, Object> updatePrizeShortMessage(
			PrizeShortMessage prizeShortMessage);

	public HashMap<String, Object> removePrizeShortMessage(String ids);

	public HashMap<String, Object> addPrizeShortMessage(
			PrizeShortMessage prizeShortMessage);

}
