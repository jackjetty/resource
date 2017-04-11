package com.rising.management.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.rising.management.dao.BaseDao;

@Component
public class BaseDaoImpl implements BaseDao {

	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sessionFactory;

	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
