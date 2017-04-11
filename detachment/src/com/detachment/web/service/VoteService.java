package com.detachment.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.detachment.pojo.TbCandidate;
import com.detachment.pojo.TbVote;
import com.detachment.wap.json.VoteRes;

public interface VoteService {
	void insertVote(TbVote vote);
	
	TbVote getVoteById(int id);

	Set<TbCandidate> getAllCandidate(int topID);

	Map<String,String> submitVote(String[] hobby,String openId);

	List<VoteRes> getVoteRes();

	TbCandidate getCandidateById(int id);
}
