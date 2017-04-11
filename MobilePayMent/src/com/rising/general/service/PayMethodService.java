package com.rising.general.service;

import com.rising.general.bean.PayMethod;

public interface PayMethodService {

	public PayMethod findByMerchantId(String merchantId);

}
