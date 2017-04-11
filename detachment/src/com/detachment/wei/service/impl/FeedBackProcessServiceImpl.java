package com.detachment.wei.service.impl;
import java.io.File; 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import org.rising.wei.bean.req.ClickEventReqBean;
import org.rising.wei.bean.req.TextReqBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;   
import com.detachment.dao.FeedBackDao; 
import com.detachment.dao.ProcessDao;
import com.detachment.dao.UserDao; 
import com.detachment.pojo.TbFeedBack;
import com.detachment.pojo.TbUser; 
import com.detachment.util.Constant;  
import com.detachment.util.CommonUtil;
import com.detachment.wei.process.FeedBackProcess;
@Service("feedBackProcessService")
public class FeedBackProcessServiceImpl   extends ProcessServiceImpl{
	@Autowired
	private UserDao userDao; 
	@Autowired
	private ProcessDao processDao;
	 
	@Autowired
	private FeedBackDao feedBackDao; 
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true;
 
		sign= eventKey.equalsIgnoreCase(Constant.FEEDBACK)?sign:false;
		if(!sign) return false;   
		StringBuffer textBuffer=new StringBuffer("");  
		textBuffer.append("欢迎您提出宝贵的意见和建议，请输入:"); 
		this.tipMessage(textBuffer.toString()); 
		this.session.setAttribute("lastOperationStage",FeedBackProcess.START);    
		return true;  
		 
	}
	public boolean textProcess(TextReqBean textReqBean){
		super.textProcess(textReqBean);
		String content=textReqBean.getContent().trim();  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false;  
		sign=stage instanceof  FeedBackProcess;  
		if(!sign) return false;
		sign=stage.equals(FeedBackProcess.START);
		if(!sign) return false; 
		this.tipMessage("感谢您的意见与建议！共建绍兴美好交通！");  
		TbFeedBack tbFeedBack=new TbFeedBack();
		tbFeedBack.setFeedOpenId(this.openId);
		tbFeedBack.setFeedText(content);
		Calendar cal = Calendar.getInstance(); 
        Date date=cal.getTime();      
        tbFeedBack.setFeedTime(new Timestamp(date.getTime()));
        tbFeedBack.setRecordType("REPLY");
        tbFeedBack.setFeedBackState("未回复");
        feedBackDao.saveOrUpdate(tbFeedBack); 
		this.session.setAttribute("lastOperationStage",FeedBackProcess.CONTEXT); 
		return sign;  
		 
	} 
	
}