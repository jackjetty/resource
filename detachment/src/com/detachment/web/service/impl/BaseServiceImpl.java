package com.detachment.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.detachment.dao.OperateRecordDao;
import com.detachment.pojo.TbOperateRecord;
import com.detachment.web.service.BaseService;

@Service("baseService")
public class BaseServiceImpl implements BaseService {

	@Autowired
	OperateRecordDao operateRecordDao;

	@Override
	public void saveOperateLogging(TbOperateRecord record) {
		operateRecordDao.save(record);
	}
}
