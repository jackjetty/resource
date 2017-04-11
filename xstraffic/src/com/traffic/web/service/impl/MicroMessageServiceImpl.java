package com.traffic.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.MicroMessageDao;
import com.traffic.pojo.TbMicroMessage;
import com.traffic.web.service.MicroMessageService;

@Service("microMessageService")
public class MicroMessageServiceImpl implements MicroMessageService {
	@Autowired
	MicroMessageDao microMessageDao;
	@Override
	public HashMap<String, Object> getMicroMessage(Integer pageSize,
			Integer pageIndex, String microId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("".equals(microId)){
			microId = null;
		}
		Integer listSize = microMessageDao.getMicroMessageListSize(microId);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbMicroMessage> ta = microMessageDao.getMicroMessage(start, pageSize, microId);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", ta);
		return resultMap;
	}

}
