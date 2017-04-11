package com.traffic.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.TbOperateLogDao;
import com.traffic.pojo.TbOperateRecord;
import com.traffic.web.service.BaseService;
@Service("baseService")
public class BaseServiceImpl implements BaseService {
	@Autowired
	TbOperateLogDao tbOperateLogDao;
	@Override
	public void saveOperateLogging(TbOperateRecord record) {
		tbOperateLogDao.save(record);
	}

}
