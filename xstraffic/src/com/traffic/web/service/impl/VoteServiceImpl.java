package com.traffic.web.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.VoteDao;
import com.traffic.pojo.TbVote;
import com.traffic.util.CommonUtil;
import com.traffic.util.UUIDGenerator;
import com.traffic.web.service.VoteService;



@Service("voteService")
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	VoteDao voteDao;
	 
	@Override
	public HashMap<String, Object> getVoteByPage(Integer pageIndex,
			Integer pageSize, String title, String status) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(title.equals("")){
			title = null;
		}
		if(status.equals("")){
			status = null;
		}
		paramMap.put("title", title);
		paramMap.put("status", status);
		
		
		title=CommonUtil.trim(title);
		status=CommonUtil.trim(status);
        String hql=" select count(*) from TbVote      ";
		
		String StrOrder="";  
		String StrCdt=""; 
		ArrayList arraylist=new ArrayList();
         
        
        if( title.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( title like ? ) ";
            arraylist.add("%"+title+"%");  
        }
        if( status.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( status = ? ) ";
            arraylist.add(status);  
        }
        if(status.length()==0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( status in (?,?) ) ";
            arraylist.add("0");
            arraylist.add("1");
        }
        
        Integer listSize=voteDao.findCount(hql+StrCdt, arraylist);    
        
		 
		
        
        
        StrOrder=" order by createTime desc ";
       
        hql="   from  TbVote    "+StrCdt   + StrOrder;  
        
    	 
    	List<TbVote> votes=  voteDao.findPage(hql,(pageIndex - 1) * pageSize, pageSize,arraylist); 
    	 
          
		
        
        
        
        
         
         
		resultMap.put("listSize", listSize);
		resultMap.put("rows", votes);
		return resultMap;
	}
	 
	@Override
	public HashMap<String, Object> deleteVotesByIds(String ids) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] arrId = ids.split(",");
		ArrayList list = new ArrayList();
		TbVote tbVote;
		for(String id : arrId){
			tbVote=voteDao.findById(id);
			if(tbVote!=null){
				tbVote.setStatus("80");
				voteDao.update(tbVote);
			}
				 
		} 
	 
		resultMap.put("success", true);
		resultMap.put("msg", "删除成功");
		 
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addVote(TbVote vote) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		vote.setId(UUIDGenerator.getUUID());
		 
		vote.setCreateTime(new Timestamp(new Date().getTime()));
		try{
			voteDao.save(vote); 
			resultMap.put("success", true);
			resultMap.put("msg", "添加成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("msg", "添加失败");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> editVote(TbVote vote) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		TbVote oldVote = voteDao.findById(vote.getId());
		if(!vote.getImage().equals("")){
			oldVote.setImage(vote.getImage());
		}		
		oldVote.setRule(vote.getRule());
		oldVote.setStartTime(vote.getStartTime());
		oldVote.setEndTime(vote.getEndTime());
		oldVote.setTitle(vote.getTitle());
		oldVote.setStatus(vote.getStatus());
		oldVote.setType(vote.getType());
		oldVote.setContent(vote.getContent());
		try{
			voteDao.update(oldVote);
			resultMap.put("success", true);
			resultMap.put("msg", "编辑成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("msg", "编辑失败");
		}
		return resultMap;
	}

	@Override
	public ArrayList<TbVote> getAllWeiVotes() {
		
		
		String StrOrder="";  
		String StrCdt=""; 
		String hql = "";
		
		StrOrder=" order by startTime desc ";
		 
		ArrayList arraylist=new ArrayList();
		Timestamp timestamp =new Timestamp(new Date().getTime());
        
		StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
	    StrCdt+=" ( status = ? ) ";
	    arraylist.add( "0");
        StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
        StrCdt+=" ( startTime <= ? ) ";
        arraylist.add( timestamp);
        StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
        StrCdt+=" ( endTime >= ? ) ";
        arraylist.add( timestamp);
        
        hql="   from  TbVote    "+StrCdt   + StrOrder;

        return (ArrayList<TbVote>) voteDao.findByHQL(hql, arraylist);
        
		 
	}

	@Override
	public TbVote findVoteById(String voteId) {
		return voteDao.findById(voteId);
	} 
	 
	
}
