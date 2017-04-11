package com.traffic.dao.impl;

import java.util.List; 
import org.springframework.stereotype.Repository;  
 
import com.traffic.dao.ProcessDao;
import com.traffic.pojo.TbProcess;
import com.traffic.util.CommonUtil;  
@Repository("processDao")
public class ProcessDaoImpl    extends BaseDaoImpl<TbProcess> implements  ProcessDao{
	@SuppressWarnings("unchecked")
	@Override
	public boolean isUsing(String processId){
		 TbProcess tbProcess=this.findById(processId);
		 if(tbProcess==null)
			 return false;
		 if(tbProcess.getUsing()==null)
			 return false;
		 return tbProcess.getUsing();
	}
	public String getPrefix(String processId){
		 TbProcess tbProcess=this.findById(processId);
		 if(tbProcess==null)
			 return "";
		 
		 return CommonUtil.trim(tbProcess.getPrefix());
	}
}