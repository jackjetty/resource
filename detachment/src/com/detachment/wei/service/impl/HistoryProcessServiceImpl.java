package com.detachment.wei.service.impl;
import java.io.File; 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rising.wei.bean.req.ClickEventReqBean;
import org.rising.wei.bean.res.object.ItemResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;  

import com.detachment.dao.HistoryDao;
import com.detachment.dao.HistoryTypeDao;
import com.detachment.dao.ProcessDao;
import com.detachment.pojo.TbHistory;
import com.detachment.pojo.TbHistoryType;
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.WeiUtil;
 
 
 
@Service("historyProcessService")
public class HistoryProcessServiceImpl   extends ProcessServiceImpl{
	 
	 
	@Autowired
	private ProcessDao processDao;
	
	@Autowired
	private HistoryTypeDao historyTypeDao;
	 
	@Value("${wei.picurl.history.header}") 
	private String  pic_history_headerUrl; 
	
	 
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true;
		 
		 
		sign=eventKey.equalsIgnoreCase(Constant.HISTORY)||eventKey.equalsIgnoreCase("CONTACTUS")?sign:false;
		
		if(!sign) return false;  
		String hql="from TbHistoryType where valid=?  order by showOrder "; 
		List<TbHistoryType> list=historyTypeDao.findByHQL(hql, true);
		
		if(list==null||list.size()==0){ 
			this.tipMessage("没有交管资讯！！");
			return true;
		}  
		ItemResObject itemResObject;
		
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>(); 
		itemResObject=new ItemResObject();
		itemResObject.setTitle(""); 
		itemResObject.setDescription(""); 
		itemResObject.setPicUrl(String.format(pic_history_headerUrl,this.SERVER_IP));  
		itemResObject.setUrl( "");
		articles.add(itemResObject); 
		
		
		for(TbHistoryType tbHistoryType:list){ 
			itemResObject=new ItemResObject();
			itemResObject.setTitle(tbHistoryType.getHistoryTypeName()); 
			itemResObject.setDescription(tbHistoryType.getHistoryTypeDes());
			itemResObject.setPicUrl(String.format(CommonUtil.trim(tbHistoryType.getHistoryTypePic()),this.SERVER_IP));  
			itemResObject.setUrl(String.format(CommonUtil.trim(tbHistoryType.getHistoryTypeUrl()),this.SERVER_IP));
			articles.add(itemResObject);  
		} 
		this.outMsg=WeiUtil.getNewsMessage(clickEventReqBean, articles) ;  
		return true;    
		 
	}
}