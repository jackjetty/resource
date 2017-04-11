package com.rising.general.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.rising.general.service.TakeOrder;

@Service("gTakeOrderFactoryBean")
public class TakeOrderFactoryBean implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public TakeOrder getBean(String interfaceName) {
		TakeOrder takeOrder =  (TakeOrder) applicationContext.getBean(interfaceName);
		return takeOrder;
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
	}

}
