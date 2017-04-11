package com.rising.general.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.general.bean.Merchant;
import com.rising.general.bean.RSAKey;
import com.rising.general.commom.BaseAction;
import com.rising.general.service.MerchantService;
import com.rising.general.service.ProductService;
import com.rising.general.service.RSAKeyService;

@Controller("gProductAction")
@RequestMapping("/general/product")
public class ProductAction extends BaseAction {

	@Autowired
	MerchantService gMerchantService;

	@Autowired
	ProductService gdProductService;

	@Autowired
	RSAKeyService gRSAKeyService;

	@RequestMapping("/getInfo")
	public void getInfo(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, String> map = null;
		RSAKey key = null;
		try {
			map = getParameter(request);
			Integer partner_code = Integer.parseInt(map.get("partner_code"));
			Merchant m = gMerchantService.findByPartnerCode(partner_code);
			if (m == null) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "合作伙伴编码错误！");
			} else {
				key = gRSAKeyService.findByMerchantId(m.getMerchantId());
				if (key == null) {
					resultMap.put("respCode", -2);
					resultMap.put("respInfo", "请确认是否已经交换RSA加解密的公钥！");
				} else {
					resultMap = gdProductService.getProductInfo(m
							.getMerchantId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("respCode", -3);
			resultMap.put("respInfo", "请求报文格式错误！");
			key = null;
		}
		writeJSONString(resultMap, response, key);
	}

}
