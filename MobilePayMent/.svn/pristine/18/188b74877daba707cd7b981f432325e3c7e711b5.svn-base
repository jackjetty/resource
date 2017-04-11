package com.rising.general.action;

import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.general.bean.Merchant;
import com.rising.general.bean.RSAKey;
import com.rising.general.commom.Base64Utils;
import com.rising.general.commom.BaseAction;
import com.rising.general.commom.RSAUtils;
import com.rising.general.service.MerchantService;
import com.rising.general.service.RSAKeyService;
import com.rising.general.service.UserService;

@Controller
@RequestMapping("/general/user")
public class UserAction extends BaseAction {

	@Autowired
	UserService gUserService;

	@Autowired
	MerchantService gMerchantService;

	@Autowired
	RSAKeyService gRSAKeyService;

	@RequestMapping("/register")
	public void register(HttpServletRequest request,
			HttpServletResponse response) {
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
					String value = map.get("value");
					String middleString = URLDecoder.decode(value, "UTF-8");
					byte[] middleByte = Base64Utils.decode(middleString);
					String plaintXMLString = new String(
							RSAUtils.decryptByPublicKey(middleByte,
									key.getPublicKey()));
					map.remove("value");
					String[] parameters = plaintXMLString.split("&");
					String mapKey = "";
					String mapValue = "";
					for (String parameter : parameters) {
						mapKey = parameter.split("=")[0];
						try {
							mapValue = parameter.split("=")[1];
						} catch (ArrayIndexOutOfBoundsException e) {
							mapValue = "";
						}
						map.put(mapKey, mapValue);
					}
					resultMap = gUserService
							.registerUser(map.get("phonenumber"));
				}
			}

		} catch (Exception e) {
			resultMap.put("respCode", -2);
			resultMap.put("respInfo", "请求报文格式错误！");
		}
		writeJSONString(resultMap,response,key);
	}

}
