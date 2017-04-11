package com.rising.general.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.general.bean.PayMethod;
import com.rising.general.mapper.PayMethodMapper;
import com.rising.general.service.PayMethodService;

@Service("gPayMethodService")
public class PayMethodServiceImpl implements PayMethodService {

	
	@Autowired PayMethodMapper gPayMethodMapper;
	@Override
	public PayMethod findByMerchantId(String merchantId) {
		ArrayList<PayMethod> ap = gPayMethodMapper.getByMerchantId(merchantId);
		if(ap!=null && ap.size()>0)
			return ap.get(0);
		else return null;
	}

}
