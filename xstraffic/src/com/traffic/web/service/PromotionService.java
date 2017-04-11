package com.traffic.web.service;

import java.util.HashMap;

public interface PromotionService {
	public HashMap<String,Object> getPromotion(Integer pageSize, Integer pageIndex);
}
