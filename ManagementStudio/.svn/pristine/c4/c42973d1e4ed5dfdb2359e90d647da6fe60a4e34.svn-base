package com.rising.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.OperateRecord;
import com.rising.management.dao.SysOperateLogDao;
import com.rising.management.service.BaseService;

@Service("baseService")
public class BaseServiceImpl implements BaseService {

	@Autowired
	SysOperateLogDao sysOperateLogDao;

	@Override
	public void saveOperateLogging(OperateRecord record) {
		sysOperateLogDao.save(record);
	}
}
