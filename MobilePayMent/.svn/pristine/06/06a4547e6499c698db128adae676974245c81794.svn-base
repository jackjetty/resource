package com.rising.mobilepayment.commom;

import java.net.URLEncoder;



public class Testing {

	public static void main(String[] args) {
		String leftInfo = "浙江电信掌上营业厅Q币充值";
		String LeftInfo = MD5.encryptByMD5With32Bit(leftInfo);
		String code = "PartnerCode=20880005&PayMethodId=10&LeftInfo="+LeftInfo+"&PhoneNumber=13336022050&UserId=13336022050";
		try {
			code = Base64Utils.encode(code.getBytes());
			code = URLEncoder.encode(code, "UTF-8");
			System.out.println(code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
