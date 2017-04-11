package com.detachment.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.CandidateDao;
import com.detachment.pojo.TbCandidate;
import com.detachment.web.service.CandidateService;
@Service("candidateService")
public class CandidateServiceImpl implements CandidateService {
	@Autowired
	CandidateDao candidateDao;
	@Override
	public void testAdd(TbCandidate c) {
		candidateDao.save(c);
	}

}
