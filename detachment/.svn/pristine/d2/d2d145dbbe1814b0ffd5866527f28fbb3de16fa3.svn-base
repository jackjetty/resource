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

import com.detachment.dao.ProcessDao;
import com.detachment.util.Constant;
import com.detachment.util.WeiUtil;
 
 
  
@Service("microClassProcessService")
public class MicroClassProcessServiceImpl   extends ProcessServiceImpl{
	 
	 
	@Autowired
	private ProcessDao processDao;
	
	@Value("${wei.microclass.fullscode.url}") 
	private String  detachmentFullScode_Url; 
	@Value("${wei.microclass.newstudent.url}") 
	private String  detachmentNewStudent_Url; 
	
	
	@Value("${wei.picurl.microclass.header}") 
	private String  pic_microclass_headerUrl; 
	
	@Value("${wei.picurl.microclass.fullscode}") 
	private String  pic_microclass_fullscodeUrl; 
	
	@Value("${wei.picurl.microclass.newstudent}") 
	private String  pic_microclass_newstudentUrl; 
	
	@Value("${wei.picurl.yuyue}") 
	private String  yuyue; 
	
	@Value("${wei.picurl.moni}") 
	private String  moni;  
	
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true;
		sign=eventKey.equalsIgnoreCase(Constant.MICROCLASS)?sign:false;
		if(!sign) return false;  
		if(!processDao.isUsing(Constant.MICROCLASS)){
			this.hopeMessage();
			return true;
		}
		 
	 
		ItemResObject itemResObject;
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>(); 
		
		
		itemResObject=new ItemResObject();
		itemResObject.setTitle(""); 
		itemResObject.setDescription(""); 
		 
		itemResObject.setPicUrl(String.format(pic_microclass_headerUrl,this.SERVER_IP));  
		 
		itemResObject.setUrl("");
		articles.add(itemResObject); 
		
		itemResObject=new ItemResObject();
		itemResObject.setTitle("新学员学习"); 
		itemResObject.setDescription("新学员学习"); 
		itemResObject.setPicUrl(String.format(pic_microclass_newstudentUrl,this.SERVER_IP));  
		itemResObject.setUrl(String.format(detachmentNewStudent_Url ,this.SERVER_IP));
		articles.add(itemResObject); 
		/*
		itemResObject=new ItemResObject();
		itemResObject.setTitle("满12分学习"); 
		itemResObject.setDescription("满12分学习"); 
		itemResObject.setPicUrl(String.format(pic_microclass_fullscodeUrl,this.SERVER_IP));  
		itemResObject.setUrl(String.format(detachmentFullScode_Url,this.SERVER_IP,clickEventReqBean.getFromUserName()) );
		articles.add(itemResObject);   */
		
		itemResObject=new ItemResObject();
		itemResObject.setTitle("驾考模拟训练"); 
		itemResObject.setDescription("驾考模拟训练"); 
		itemResObject.setPicUrl(String.format(moni,this.SERVER_IP));  
		itemResObject.setUrl(String.format("http://www.weiximen.com/hzxstraffic/wei/microwebsite/peopleService/cotent2.html" ,this.SERVER_IP));
		articles.add(itemResObject); 
		
		itemResObject=new ItemResObject();
		itemResObject.setTitle("驾驶人考试预约"); 
		itemResObject.setDescription("驾驶人考试预约"); 
		itemResObject.setPicUrl(String.format(yuyue,this.SERVER_IP));  
		itemResObject.setUrl(String.format("http://www.sxga.gov.cn:88/drv_web/info.do",this.SERVER_IP,clickEventReqBean.getFromUserName()) );
		articles.add(itemResObject);   
		
		this.outMsg=WeiUtil.getNewsMessage(clickEventReqBean, articles) ; 
		
		return true;
		 
		 
	}
}