package com.traffic.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.traffic.pojo.TbElector;


public interface ElectorService {
	
	public HashMap<String,Object> getElectorByPage(String voteId,Integer pageIndex,Integer pageSize);
	
	public HashMap<String,Object> deleteElector(TbElector elector);
	
	public HashMap<String,Object> addElector(TbElector elector);
	
	public HashMap<String,Object> editElector(TbElector elector);
	
	public ArrayList<TbElector> getAllWeiElectorById(String voteId);
	
	public ArrayList<TbElector> getAllWeiElectorOrderByVotes(String voteId);
}
