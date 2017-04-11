package com.rising.mobilepayment.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.PhoneNumber;

@Component("phoneNumberMapper")
public interface PhoneNumberMapper {
	
	public  PhoneNumber  findId(String phoneNumber);
	
	public void add(PhoneNumber phoneNumber);
	
	public void update(PhoneNumber phoneNumber);

}
