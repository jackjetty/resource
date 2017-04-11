package com.traffic.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.ElectorDao;
import com.traffic.pojo.TbElector;
import com.traffic.pojo.TbVote;
import com.traffic.util.UUIDGenerator;
import com.traffic.web.service.ElectorService;



@Service("electorService")
public class ElectorServiceImpl implements ElectorService {
	
	@Autowired
	ElectorDao electorDao;

	@Override
	public HashMap<String, Object> getElectorByPage(String voteId,
			Integer pageIndex, Integer pageSize) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String hql=" select count(*) from TbElector      ";
		String StrOrder="";  
		String StrCdt=""; 
		ArrayList arraylist=new ArrayList();
		if( voteId.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( voteId = ? ) ";
            arraylist.add(voteId);  
        }
		Integer listSize = electorDao.findCount(hql + StrCdt + StrOrder, arraylist);
		
		StrOrder=" order by number asc ";
	       
        hql="   from  TbElector    "+StrCdt   + StrOrder;  
    	 
    	List<TbElector> electors=  electorDao.findPage(hql,(pageIndex - 1) * pageSize, pageSize,arraylist); 
    	
		resultMap.put("listSize", listSize);
		resultMap.put("rows", electors);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> deleteElector(TbElector elector) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try{
			TbElector oldElector = electorDao.findById(elector.getId());
			if(oldElector.getVotes()>0){
				resultMap.put("success", false);
				resultMap.put("msg", "已经被投票，不能删除");
			}else{
				electorDao.delete(oldElector);
				resultMap.put("success", true);
				resultMap.put("msg", "删除成功");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("msg", "删除失败");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addElector(TbElector elector) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		elector.setId(UUIDGenerator.getUUID());
		try{
			electorDao.save(elector);
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
	public HashMap<String, Object> editElector(TbElector elector) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		TbElector oldElector = electorDao.findById(elector.getId());
		try{
			if(!elector.getImg().equals("")){
				oldElector.setImg(elector.getImg());
			}
			oldElector.setIntroduce(elector.getIntroduce());
			oldElector.setName(elector.getName());
			oldElector.setNumber(elector.getNumber());
			oldElector.setWorkUnit(elector.getWorkUnit());
			oldElector.setAchievement(elector.getAchievement());
			oldElector.setArea(elector.getArea());
			electorDao.update(oldElector);
			resultMap.put("success", true);
			resultMap.put("msg", "编辑成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("msg", "添加失败");
		}
		return resultMap;
	}

	@Override
	public ArrayList<TbElector> getAllWeiElectorById(String voteId) {
		String StrOrder="";  
		String StrCdt=""; 
		String hql = "   from  TbElector    ";
		
		StrOrder=" order by number asc ";
		
		ArrayList arraylist=new ArrayList();
		
		StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
	    StrCdt+=" ( voteId = ? ) ";
	    arraylist.add(voteId);
		return (ArrayList<TbElector>) electorDao.findByHQL(hql, arraylist);
	}
	
	@Override
	public ArrayList<TbElector> getAllWeiElectorOrderByVotes(String voteId) {
		String StrOrder="";  
		String StrCdt=""; 
		String hql = "";
		
		StrOrder=" order by votes desc ";
		
		ArrayList arraylist=new ArrayList();
		
		StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
	    StrCdt+=" ( voteId = ? ) ";
	    arraylist.add(voteId);
		return (ArrayList<TbElector>) electorDao.findByHQL(hql, arraylist);
	}

}
