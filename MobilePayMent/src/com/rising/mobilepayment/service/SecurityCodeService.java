package com.rising.mobilepayment.service;



import java.util.HashMap;

import com.rising.mobilepayment.bean.SecurityCode;

public interface SecurityCodeService {
	
	public boolean isSecurityCodeValidate(SecurityCode code,HashMap<String,Object> map);

	

	public boolean isSecurityCodeValidate2(SecurityCode code,
			HashMap<String, Object> resultMap);
}
