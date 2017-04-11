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
 
 
import com.traffic.dao.OrationRecordDao;
import com.traffic.dao.ProcessDao;
import com.traffic.dao.UserDao;
import com.traffic.pojo.TbAccident;
import com.traffic.pojo.TbUser;
import com.traffic.pojo.TbOrationRecord; 
 
 
import com.traffic.util.Constant;  
import com.traffic.util.CommonUtil;
import com.traffic.util.WeiUtil;
import org.rising.wei.bean.req.ClickEventReqBean;
import org.rising.wei.bean.res.object.ItemResObject;
@Service("studyPolicyProcessService")
public class StudyPolicyProcessServiceImpl   extends ProcessServiceImpl{
	@Autowired
	private UserDao userDao;
	 
	@Autowired
	private ProcessDao processDao; 
	
	
	@Autowired
	private OrationRecordDao orationRecordDao;
	
	 
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true;
		sign=eventKey.equalsIgnoreCase(Constant.STUDYPOLICY)?sign:false;
		if(!sign) return false; 
		if(!processDao.isUsing(Constant.STUDYPOLICY)){
			this.hopeMessage();
			return true;
		}
		 
	 
		ItemResObject itemResObject;
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>(); 
		
		
		itemResObject=new ItemResObject();
		itemResObject.setTitle("每月推送信息"); 
		itemResObject.setDescription("每月推送信息"); 
		itemResObject.setPicUrl(String.format("http://%1$s/nhtraffic/image/convenient/policiesregulations.jpg",this.SERVER_IP));  
		itemResObject.setUrl( "http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzA5Nzc1ODkyMA==#wechat_webview_type=1&wechat_redirect");
		articles.add(itemResObject); 
		
		itemResObject=new ItemResObject();
		itemResObject.setTitle("法律法规"); 
		itemResObject.setDescription("法律法规"); 
		itemResObject.setPicUrl(String.format("http://%1$s/nhtraffic/image/convenient/policiesregulation.jpg",this.SERVER_IP));  
		itemResObject.setUrl(String.format("http://%1$s/nhtraffic/about/lawsRegulations.html" ,this.SERVER_IP));
		articles.add(itemResObject); 
		
		itemResObject=new ItemResObject();
		itemResObject.setTitle("交通宣传"); 
		itemResObject.setDescription("交通宣传"); 
		itemResObject.setPicUrl(String.format("http://%1$s/nhtraffic/image/linestudy/linestudy.jpg",this.SERVER_IP));  
		itemResObject.setUrl(String.format("http://%1$s/nhtraffic/getCodeJsp.action?publicType=Notice",this.SERVER_IP) );
		articles.add(itemResObject); 
		
		itemResObject=new ItemResObject();
		itemResObject.setTitle("便民服务"); 
		itemResObject.setDescription("便民服务");
		itemResObject.setPicUrl(String.format("http://%1$s/nhtraffic/image/convenient/dynamicnews.jpg",this.SERVER_IP));  
		 
		itemResObject.setUrl(String.format("http://%1$s/nhtraffic/police/convenience.html",this.SERVER_IP) );
	 
		articles.add(itemResObject); 
		 
		this.outMsg=WeiUtil.getNewsMessage(clickEventReqBean, articles) ;   
		return true;   
	}
}