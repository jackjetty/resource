package com.rising.mobilepayment.service;

import java.util.ArrayList;
import java.util.HashMap;




import com.rising.mobilepayment.bean.OrderInfo;
import com.rising.mobilepayment.bean.Product;
import com.rising.mobilepayment.bean.QQPerMonthReSubmit;
//import com.rising.mobilepayment.bean.OrderInfo;
//import com.rising.mobilepayment.bean.UserInfo;
import com.rising.mobilepayment.commom.PageObject;

public interface OrderInfoService {

	public String queryOrderInfoByPage(HashMap<String, String> map,
			PageObject page);

	public String findOrderInfoById(String orderId);
	
	public String takeCharge(HashMap<String, String> map)throws RuntimeException;

	public String takeOrder(HashMap<String, String> map);

	public String takeOrderQQPerMonth(HashMap<String, String> map);

	public String takeQQPerMonthCharge(HashMap<String, String> map);

	public String takeQQVIPOrder(HashMap<String, String> map);

	public String takeQQVIPCharge(HashMap<String, String> map);

	public String cancelQQPerMonthOrder(HashMap<String, String> map);
	
	public HashMap<String,Object> checkPerMonth(String phoneNumber);

	 

	public String recordTelephoneFee(HashMap<String, String> map);

	public ArrayList<OrderInfo> findQQPerMonthOrderByPhoneNumber(
			String phoneNumber);

	public String queryOrderInfoByPage2(HashMap<String, String> map,
			PageObject page);

	public String reSubmit(QQPerMonthReSubmit reSubmit, Product product);
 

}
