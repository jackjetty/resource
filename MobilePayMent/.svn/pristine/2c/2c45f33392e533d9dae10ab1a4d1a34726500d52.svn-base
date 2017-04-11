package com.rising.general.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rising.general.bean.Merchant;
import com.rising.general.bean.PayMethod;
import com.rising.general.bean.RSAKey;
import com.rising.general.commom.Base64Utils;
import com.rising.general.commom.BaseAction;
import com.rising.general.commom.RSAUtils;
import com.rising.general.service.MerchantService;
import com.rising.general.service.OrderService;
import com.rising.general.service.PayMethodService;
import com.rising.general.service.RSAKeyService;
import com.rising.mobilepayment.bean.SecurityCode;
import com.rising.mobilepayment.commom.CommonUtil;

@Controller
@RequestMapping("/general/order")
public class OrderAction extends BaseAction {

	@Autowired
	MerchantService gMerchantService;

	@Autowired
	OrderService gOrderService;

	@Autowired
	RSAKeyService gRSAKeyService;

	@Autowired
	PayMethodService gPayMethodService;
	
	
	
	@RequestMapping("/unSmsPay")
	public void unSmsPay(HttpServletRequest request, HttpServletResponse response) {  
		
		HashMap<String, String> map = getParameter(request,"web"); 
		String resultJson=gOrderService.unSmsPay(map);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter(); 
			resultJson = URLEncoder.encode(resultJson, "UTF-8");
			out.write(resultJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 
	}
	
	
 
	
	@RequestMapping(value="/dateOrderRecord", produces = "application/json; charset=utf-8")
	public @ResponseBody String   dateOrderRecord(HttpServletRequest request, HttpServletResponse response) {  
		
		HashMap<String, String> map = getParameter(request,"web"); 
		String resultJson=gOrderService.dateOrderRecord(map);
		return resultJson;  
	}
	
	
	//通用接口 产品支付接口
		@RequestMapping("/pay")
		public void pay(HttpServletRequest request, HttpServletResponse response) { 
			String userAgent = CommonUtil.trim(request.getHeader("user-agent"));
			
			HashMap<String, String> map = getParameter(request,"web"); 
			String resultJson=gOrderService.pay(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				resultJson = URLEncoder.encode(resultJson, "UTF-8");
				out.write(resultJson);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			 
		}
		
		
		@RequestMapping("/pminfo")
		public void pminfo(HttpServletRequest request, HttpServletResponse response) {
		 
			String userAgent = CommonUtil.trim(request.getHeader("user-agent")); 
			HashMap<String, String> map = getParameter(request,"web"); 
			String resultJson=gOrderService.pminfo(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				resultJson = URLEncoder.encode(resultJson, "UTF-8");
				out.write(resultJson);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}

		@RequestMapping("/pmcancel")
		public void pmcancel(HttpServletRequest request, HttpServletResponse response) {
		 
			String userAgent = CommonUtil.trim(request.getHeader("user-agent")); 
			HashMap<String, String> map = getParameter(request,"web"); 
			String resultJson=gOrderService.pmcancel(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				resultJson = URLEncoder.encode(resultJson, "UTF-8");
				out.write(resultJson);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
		
		@RequestMapping("/msgnotice")
		public void msgnotice(HttpServletRequest request, HttpServletResponse response) {
		 
			String userAgent = CommonUtil.trim(request.getHeader("user-agent")); 
			HashMap<String, String> map = getParameter(request,"web"); 
			String resultJson=gOrderService.msgnotice(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				resultJson = URLEncoder.encode(resultJson, "UTF-8");
				out.write(resultJson);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
		
	

	
	//通用接口 产品支付接口
	@RequestMapping("/apply")
	public void apply(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, String> map = null;
		RSAKey key = null;
		PayMethod method = null;
		try {
			map = getParameter(request);
			Integer partner_code = Integer.parseInt(map.get("partner_code"));
			String ipAddress = map.get("requestIp");
			Merchant m = gMerchantService.findByPartnerCode(partner_code);
			if (m == null) {
				resultMap.put("respCode", -1);
				resultMap.put("respInfo", "合作伙伴编码错误！");
			} else {
				method = gPayMethodService.findByMerchantId(m.getMerchantId());
				
				if (method != null) {
					String ipBind = method.getIpBind();
					if (ipBind != null && "YES".equals(ipBind)) {
						String bindIp = method.getBindIp();
						if (bindIp != null && bindIp.equals(ipAddress)) {
							key = gRSAKeyService.findByMerchantId(m
									.getMerchantId());
							if (key == null) {
								resultMap.put("respCode", -2);
								resultMap
										.put("respInfo", "请确认是否已经交换RSA加解密的公钥！");
							} else {
								String parameter = map.get("value");
								byte[] middlebytes = Base64Utils
										.decode(parameter);
								middlebytes = RSAUtils.decryptByPublicKey(
										middlebytes, key.getPublicKey());
								parameter = new String(middlebytes);
								map.remove("value");
								map.put("payMethodId",
										String.valueOf(method.getPayMethodId()));
								String methodDetail = method.getPayMethodDetail();
								String remark = method.getRemark();
								map.put("methodDetail",methodDetail );
								map.put("remark", remark);
								String[] parameters = parameter.split("&");
								String mapKey = "";
								String mapValue = "";
								for (String parameter1 : parameters) {
									mapKey = parameter1.split("=")[0];
									try {
										mapValue = parameter1.split("=")[1];
									} catch (ArrayIndexOutOfBoundsException e) {
										mapValue = "";
									}
									map.put(mapKey, mapValue);
								}
								resultMap = gOrderService.apply(map);
							}
						} else {
							resultMap.put("respCode", -3);
							resultMap.put("respInfo", "发送请求ip与绑定ip不匹配！");
							key = null;
						}
					} else {
						key = gRSAKeyService
								.findByMerchantId(m.getMerchantId());
						if (key == null) {
							resultMap.put("respCode", -2);
							resultMap.put("respInfo", "请确认是否已经交换RSA加解密的公钥！");
						} else {
							String parameter = map.get("value");
							byte[] middlebytes = Base64Utils.decode(parameter);
							middlebytes = RSAUtils.decryptByPublicKey(
									middlebytes, key.getPublicKey());
							parameter = new String(middlebytes);
							map.remove("value");
							map.put("payMethodId",
									String.valueOf(method.getPayMethodId()));
							map.put("perOrder", method.getPerOrder());
							String[] parameters = parameter.split("&");
							String mapKey = "";
							String mapValue = "";
							for (String parameter1 : parameters) {
								mapKey = parameter1.split("=")[0];
								try {
									mapValue = parameter1.split("=")[1];
								} catch (ArrayIndexOutOfBoundsException e) {
									mapValue = "";
								}
								map.put(mapKey, mapValue);
							}
							resultMap = gOrderService.apply(map);
						}
					}

				} else {
					resultMap.put("respCode", -5);
					resultMap.put("respInfo", "配置出错");
					key = null;
				}

			}
		} catch (SocketException e) {
			resultMap.put("respCode", -4);
			resultMap.put("respInfo", "支付网关连接失败！");
			key = null;
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("respCode", -3);
			resultMap.put("respInfo", "请求报文格式错误！");
			key = null;
		}
		writeJSONString(resultMap, response, key);
	} 
}
