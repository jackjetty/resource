package com.detachment.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.AwardDao;
import com.detachment.pojo.TbAward;
import com.detachment.web.service.AwardService;
@Service("awardService")
public class AwardServiceImpl implements AwardService {
	@Autowired
	AwardDao awardDao;

	@Override
	public void addTbAward(TbAward b) {
		awardDao.save(b);
	}

}
