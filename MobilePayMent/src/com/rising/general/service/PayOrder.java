package com.rising.general.service;

import java.util.HashMap;

public interface PayOrder {

	public HashMap<String, String> getParameterMap(HashMap<String, String> map);

	public HashMap<String, Object> payOrder(HashMap<String, String> paraMap, String url ) throws Exception;

}
