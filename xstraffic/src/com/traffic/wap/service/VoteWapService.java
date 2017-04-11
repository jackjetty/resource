package com.traffic.wap.service;

import java.util.HashMap;

public interface VoteWapService {

	
	public HashMap<String, Object> voteWapPage(String code,String voteId,String url);
	
	public HashMap<String, Object> voteWapElectors(String voteId,String url);
	
	public HashMap<String, Object> voteWapTopElectors(String voteId,String url);
	
	public HashMap<String, Object> voteWapDoVote(String voteId,String electorStr);
	
	public HashMap<String, Object> voteWapCheckTender(String voteId);
	
	public HashMap<String, Object> voteWapElectoDetail(String id,String url);
}


 