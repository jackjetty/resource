package com.traffic.wap.service;
import java.util.HashMap;

public interface QRWapService {

	
	public HashMap<String, Object> test(String eleId);
	public HashMap<String, Object> win();
	public HashMap<String, Object> refresh();
	public HashMap<String, Object> account(String eleId);
}