package com.detachment.dao.impl;
import org.springframework.stereotype.Repository;   
import com.detachment.dao.ProcessDao; 
import com.detachment.pojo.TbProcess; 
import com.detachment.util.CommonUtil; 
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;  
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