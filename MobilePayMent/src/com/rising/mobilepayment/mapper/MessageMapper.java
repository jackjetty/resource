package com.rising.mobilepayment.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.stereotype.Component;


@Component("messageMapper")
public interface MessageMapper {
	
	public String getMessageContentByUse(String use);
	
	public ArrayList<HashMap<String, String>> getMessageContentByUse2(String[] useString);

}
