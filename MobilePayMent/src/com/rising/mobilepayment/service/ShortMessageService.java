package com.rising.mobilepayment.service;

public interface ShortMessageService {
	
	public String registerShortMessage(String phoneNumbe, String checkCode);

	public String payShortMessage(String phoneNumber, String checkCode);

	public String findPasswordShortMessage(String phoneNumber, String checkCode);

	public String cancelQQPerMonthShortMessage(String phoneNumber,
			String securityCode);

	public String sendBindShortMessage(String phoneNumber, String securityCode);
	
}
