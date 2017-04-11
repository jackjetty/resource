package com.rising.mobilepayment.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.Merchant;

@Component("merchantMapper")
public interface MerchantMapper {

	public ArrayList<Merchant> findByPartnerCode(Integer partner_code);

}
