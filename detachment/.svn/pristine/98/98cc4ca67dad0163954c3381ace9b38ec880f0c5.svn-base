package com.detachment.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.detachment.dao.InfoPicDao;
import com.detachment.pojo.TbInfoPic;
@Repository("infoPicDao")
public class InfoPicDaoImpl    extends BaseDaoImpl<TbInfoPic> implements  InfoPicDao{
	public int getNextPicIndex(String processId,String recordNo){
		String hql="select max(id.picIndex) from TbInfoPic where id.processId=? and id.recordNo=?";
		List  list=this.findByHQL(hql, processId,recordNo);
		if(list==null||list.size()==0)
			 return 1;
		if(list.get(0)==null)
			 return 1; 
		return ((Number) list.get(0)).intValue()+1;
	}
}