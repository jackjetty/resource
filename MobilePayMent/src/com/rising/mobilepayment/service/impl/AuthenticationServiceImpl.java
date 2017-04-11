package com.rising.mobilepayment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rising.mobilepayment.bean.Merchant;
import com.rising.mobilepayment.bean.PayMethod;
import com.rising.mobilepayment.commom.MD5;
import com.rising.mobilepayment.mapper.MerchantMapper;
import com.rising.mobilepayment.mapper.PayMethodMapper;
import com.rising.mobilepayment.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	MerchantMapper merchantMapper;

	@Autowired
	PayMethodMapper payMethodMapper;

	@Override
	public String checkAuthe(String partnerCode, String payMethodId,
			String leftInfo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<Merchant> am = merchantMapper.findByPartnerCode(Integer
				.parseInt(partnerCode));
		PayMethod method = null;
		if (am != null && am.size() == 1) {
			ArrayList<PayMethod> ap = payMethodMapper
					.getByMerchantIdAndPayMethodId(am.get(0).getMerchantId(),
							Integer.parseInt(payMethodId));
			if (ap != null && ap.size() == 1) {
				method = ap.get(0);
				String tempCode = MD5.encryptByMD5With32Bit(method
						.getLeftInfo());
				
				
				
				return new Gson().toJson(method);
				/*
				if (tempCode.equals(leftInfo)) {
					return new Gson().toJson(method);
				} else {
					map.put("respCode", -3);
					map.put("respInfo", "预留信息不正确");
					return new Gson().toJson(map);
				}*/
			} else {
				map.put("respCode", -1);
				if (ap == null || ap.size() == 0) {
					map.put("respInfo", "该商户无该渠道");
				} else {
					map.put("respInfo", "获取商户渠道信息时出现错误");
				}
				return new Gson().toJson(map);
			}
		} else {
			map.put("respCode", -2);
			if (am == null || am.size() == 0) {
				map.put("respInfo", "无该商户信息");
			} else {
				map.put("respInfo", "获取商户信息时出现错误");
			}
			return new Gson().toJson(map);
		}
	}
}
