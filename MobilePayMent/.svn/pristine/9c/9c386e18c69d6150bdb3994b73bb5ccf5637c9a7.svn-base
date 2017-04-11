package com.rising.general.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.general.bean.Merchant;
import com.rising.general.mapper.MerchantMapper;
import com.rising.general.service.MerchantService;

@Service("gMerchantService")
public class MerchantServiceImpl implements MerchantService {
	
	@Autowired MerchantMapper gMerchantMapper;

	@Override
	public Merchant findByPartnerCode(Integer partner_code) {
		ArrayList<Merchant> am = gMerchantMapper.findByPartnerCode(partner_code);
		if(am!=null && am.size()>0){
			return am.get(0);
		}else{
			return null;
		}
	}

}
