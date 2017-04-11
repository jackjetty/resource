package com.traffic.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.traffic.pojo.TbVote;


public interface VoteService {
public HashMap<String,Object> getVoteByPage(Integer pageIndex,Integer pageSize,String title,String status);
	
	public HashMap<String,Object> deleteVotesByIds(String ids);
	
	public HashMap<String,Object> addVote(TbVote vote);
	
	public HashMap<String,Object> editVote(TbVote vote);
	
	public ArrayList<TbVote> getAllWeiVotes();
	
	public TbVote findVoteById(String voteId);
}
