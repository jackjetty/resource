package com.traffic.wei.service.impl;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;   

import com.traffic.dao.DoAnswerDao;
import com.traffic.dao.ProcessDao;
import com.traffic.dao.UserDao;
import com.traffic.pojo.TbAccident;
import com.traffic.pojo.TbDoAnswer;
import com.traffic.pojo.TbUser;
import com.traffic.pojo.TbOrationRecord;  
import com.traffic.util.Constant;  
import com.traffic.util.CommonUtil;
import com.traffic.util.WeiUtil;
import org.rising.wei.bean.req.ClickEventReqBean;
import org.rising.wei.bean.req.TextReqBean;
import com.traffic.wei.process.DoAnswerProcess;
import com.traffic.wei.process.FeedBackProcess;
@Service("doAnswerProcessService")
public class DoAnswerProcessServiceImpl   extends ProcessServiceImpl{
	@Autowired
	private UserDao userDao;
	 
	@Autowired
	private ProcessDao processDao;
	@Autowired
	private DoAnswerDao doAnswerDao; 
  
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true;
		sign=eventKey.equalsIgnoreCase(Constant.DOANSWER)?sign:false;
		if(!sign) return false; 
		if(!processDao.isUsing(Constant.DOANSWER)){
			this.hopeMessage();
			return true;
		}
		StringBuffer textBuffer=new StringBuffer("");  
		textBuffer.append("欢迎您提出需要解答的问题或建议，请输入："); 
		this.tipMessage(textBuffer.toString()); 
		 
		this.session.setAttribute("lastOperationStage",DoAnswerProcess.START);    
		return true;  
	}
	public boolean textProcess(TextReqBean textReqBean){
		super.textProcess(textReqBean);
		String content=textReqBean.getContent().trim();  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false;  
		sign=stage instanceof  DoAnswerProcess;  
		if(!sign) return false;
		sign=stage.equals(DoAnswerProcess.START);
		if(!sign) return false; 
		this.tipMessage("您的问题我们已经收到，我们将尽快回复您！");  
		TbDoAnswer tbDoAnswer=new TbDoAnswer();
		tbDoAnswer.setAnswerOpenId(this.openId);
		tbDoAnswer.setAnswerText(content); 
		Calendar cal = Calendar.getInstance(); 
        Date date=cal.getTime();  
        tbDoAnswer.setAnswerTime(new Timestamp(date.getTime()));
        tbDoAnswer.setDoAnswerState("未回复"); 
        doAnswerDao.saveOrUpdate(tbDoAnswer); 
		this.session.setAttribute("lastOperationStage",DoAnswerProcess.CONTEXT); 
		return sign;  
		 
	} 
	
}