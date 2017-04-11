package com.rising.general.service;

import com.rising.general.bean.Merchant;

public interface MerchantService {

	public Merchant findByPartnerCode(Integer partner_code);

}
