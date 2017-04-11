package com.rising.mobilepayment.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.mobilepayment.bean.SecurityCode;
import com.rising.mobilepayment.commom.MD5;
import com.rising.mobilepayment.mapper.SecurityCodeMapper;
import com.rising.mobilepayment.service.SecurityCodeService;

@Service("securityCodeService")
public class SecurityCodeServiceImpl implements SecurityCodeService {

	Log log = LogFactory.getLog(SecurityCodeServiceImpl.class);

	@Autowired
	SecurityCodeMapper securityCodeMapper;

	@Override
	public boolean isSecurityCodeValidate(SecurityCode code,
			HashMap<String, Object> map) {
		SecurityCode newcode = securityCodeMapper.findCodeByPhoneNumber(code
				.getPhoneNumber());
		try {
			if (MD5.encryptByMD5With16Bit(code.getCode()).equals(newcode.getCode())) {
				if (!newcode.getInvalidTime().after(new Date())) {
					map.put("respCode", -8);
					map.put("respInfo", "验证码超时！");
					return false;
				} else
					return true;
			} else {
				map.put("respCode", -4);
				map.put("respInfo", "验证码错误！");
				return false;
			}
		} catch (NullPointerException e) {
			log.error("isSecurityCodeValidate()->该号码未获取短信验证码！");
			map.put("respCode", -9);
			map.put("respInfo", "请先获取短信验证码！");
			return false;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean isSecurityCodeValidate2(SecurityCode code,
			HashMap<String, Object> map) {
		SecurityCode newcode = securityCodeMapper.findCodeByPhoneNumber(code
				.getPhoneNumber());
		try {
			if (MD5.encryptByMD5With16Bit(code.getCode()).equals(newcode.getCode())) {
				if (!newcode.getInvalidTime().after(new Date())) {
					map.put("respCode", -8);
					map.put("respInfo", "验证码超时！");
					return false;
				} else
					return true;
			} else {
				map.put("respCode", -4);
				map.put("respInfo", "验证码错误！");
				return false;
			}
		} catch (NullPointerException e) {
			log.error("isSecurityCodeValidate()->该号码未获取短信验证码！");
			map.put("respCode", -9);
			map.put("respInfo", "请先获取短信验证码！");
			return false;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
	}
}
