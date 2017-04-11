package com.rising.general.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.general.bean.RSAKey;
import com.rising.general.mapper.RSAKeyMapper;
import com.rising.general.service.RSAKeyService;


@Service("rSAKeyService")
public class RSAKeyServiceImpl implements RSAKeyService {
	
	@Autowired RSAKeyMapper gRSAKeyMapper;

	@Override
	public RSAKey findByMerchantId(String merchantId) {
		ArrayList<RSAKey> ar = gRSAKeyMapper.findByMerchantId(merchantId);
		if(ar!=null && ar.size()>0){
			return ar.get(0);
		}else{
			return null;
		}
	}

}
