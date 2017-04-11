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
@Service("dMVProcessService")
public class DMVProcessServiceImpl   extends ProcessServiceImpl{
	 
	 
	@Autowired
	private ProcessDao processDao;
 
  
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true;
		sign=eventKey.equalsIgnoreCase(Constant.DMV)?sign:false;
		if(!sign) return false;  
		if(!processDao.isUsing(Constant.DMV)){
			this.hopeMessage();
			return true;
		}
		StringBuffer textBuffer=new StringBuffer("");  
		textBuffer.append("建设中，敬请期待！！"); 
		this.tipMessage(textBuffer.toString()); 
		 
		    
		return true;  
	}
}