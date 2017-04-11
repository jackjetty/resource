package com.rising.mobilepayment.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.KindyReminder;

@Component()
public interface KindyReminderMapper {
		
	public ArrayList<KindyReminder> getAll();
	
}
