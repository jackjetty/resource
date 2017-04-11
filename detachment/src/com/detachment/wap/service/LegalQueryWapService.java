package com.detachment.wap.service;
import java.util.ArrayList;
import java.util.HashMap;

 
public interface LegalQueryWapService {
	public byte[] verifyCode(); 
	public HashMap<String, Object> checkVerifyCode(String randValue);
	
	public HashMap<String, Object> driverIllegality(String carid,String carno,String cartype,String carTypeValue);
	
	public HashMap<String, Object> driverScore(String vsfzmhm,String vdabh);
}