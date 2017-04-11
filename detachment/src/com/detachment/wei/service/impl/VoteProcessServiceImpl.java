package com.detachment.wei.service.impl;
import java.util.ArrayList;

import org.rising.wei.bean.req.ClickEventReqBean;
import org.rising.wei.bean.res.object.ItemResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.detachment.dao.BaseInfoDao;
import com.detachment.dao.ProcessDao; 
import com.detachment.util.Constant;
import com.detachment.util.WeiUtil;
 
 
 
@Service("voteProcessService")
public class VoteProcessServiceImpl   extends ProcessServiceImpl{ 
	@Autowired
	private ProcessDao processDao;
	
	@Autowired
	private BaseInfoDao baseInfoDao;
	
 
	 
	 
	@Value("${wei.picurl.vote.header}") 
	private String  pic_investigate_headerUrl; 
	
	@Value("${wei.vote.url}") 
	private String investigate_eduUrl;
	 
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true; 
		sign=eventKey.equalsIgnoreCase(Constant.VOTE) ?sign:false;
		if(!sign) return false;  
		if(!processDao.isUsing(Constant.VOTE)){
			this.hopeMessage();
			return true;
		}  
		 
/*		TbBaseInfo tbBaseInfo=baseInfoDao.findById(this.openId);
		if(tbBaseInfo!=null&& CommonUtil.trim(tbBaseInfo.getState()).equalsIgnoreCase("finish")){
			this.outMsg=WeiUtil.getTextMessage(clickEventReqBean, "您好，您的微调查已经完成，感谢您的参与和支持！！");
			return true;
		} */
		ItemResObject itemResObject; 
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>(); 
		itemResObject=new ItemResObject();
		itemResObject.setTitle("绍兴市“三有三好”十佳驾驶人评选活动"); 
		itemResObject.setDescription("绍兴市公安局、交通运输局联合开展绍兴市“三有三好”十佳驾驶人评选活动，网友可通过绍兴交警官方微信，投票选出您心目中的绍兴市“三有三好”十佳驾驶人"); 
		itemResObject.setPicUrl(String.format(pic_investigate_headerUrl,this.SERVER_IP));  
		itemResObject.setUrl(String.format(investigate_eduUrl,this.SERVER_IP,this.openId));
		articles.add(itemResObject);   
		this.outMsg=WeiUtil.getNewsMessage(clickEventReqBean, articles) ;  
		return true;    
		 
	}
}