package com.detachment.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.VoteResDao;
import com.detachment.web.service.VoteResService;
@Service
public class VoteResServiceImpl implements VoteResService {
	@Autowired
	private VoteResDao voteResDao;

}
