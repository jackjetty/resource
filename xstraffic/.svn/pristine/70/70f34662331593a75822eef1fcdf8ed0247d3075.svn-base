package com.traffic.dao.impl;
import org.springframework.stereotype.Repository;   
import java.util.List;   

import com.traffic.dao.InfoTextDao;
import com.traffic.pojo.TbInfoText;
import com.traffic.util.CommonUtil;
@Repository("infoTextDao")
public class InfoTextDaoImpl    extends BaseDaoImpl<TbInfoText> implements  InfoTextDao{
 
	public int getNextTextIndex(String processId,String recordNo){
		//processId,recordNo,textIndex,textTime,textMessage
		String hql="select max(id.textIndex) from TbInfoText where id.processId=? and id.recordNo=?";
		List list=this.findByHQL(hql, processId,recordNo);
		if(list==null||list.size()==0)
			 return 1;
		if(list.get(0)==null)
			 return 1; 
		return ((Number) list.get(0)).intValue()+1;
	}
}