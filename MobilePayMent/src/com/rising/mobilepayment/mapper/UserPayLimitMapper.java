package com.rising.mobilepayment.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.UserPayLimit;

@Component("userPayLimitMapper")
public interface UserPayLimitMapper {

	public ArrayList<UserPayLimit> getUserPayLimitByPhoneNumber(String phoneNumber);

	public void updateMoneyAndTime(UserPayLimit userPayLimit);

	public void addUserPayLimit(UserPayLimit userPayLimit);

}
