package com.rising.mobilepayment.mapper;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.UserPublicInfoReadStatus;


@Component
public interface UserPublicInfoReadStatusMapper {

	public UserPublicInfoReadStatus getUserPublicInfoReadStatus(String PhoneNumber);
	public void addUserPublicInfoReadStatus(UserPublicInfoReadStatus status);
	public void updateUserPublicInfoReadStatus(UserPublicInfoReadStatus status);
}
