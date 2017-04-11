package com.detachment.wei.service.impl;
import java.io.File; 
import java.net.URLEncoder;
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
import com.detachment.dao.BaseInfoDao;
import com.detachment.dao.ProcessDao; 
import com.detachment.pojo.TbBaseInfo;
import com.detachment.pojo.TbPersonalPromotion; 
 
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.HttpWeiUtil;
import com.detachment.util.WeiUtil;
import com.detachment.wei.thread.FileThread;
 
 
 
@Service("investigateProcessService")
public class InvestigateProcessServiceImpl   extends ProcessServiceImpl{ 
	@Autowired
	private ProcessDao processDao;
	
	@Autowired
	private BaseInfoDao baseInfoDao;
	
	 
	 
	 
	@Value("${wei.picurl.investigate.header}") 
	private String  pic_investigate_headerUrl; 
	
	@Value("${wei.investigate.edu.url}") 
	private String investigate_eduUrl;
	 
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true; 
		sign=eventKey.equalsIgnoreCase(Constant.INVESTIGATE) ?sign:false;
		if(!sign) return false;  
		if(!processDao.isUsing(Constant.INVESTIGATE)){
			this.hopeMessage();
			return true;
		}  
		 
		TbBaseInfo tbBaseInfo=baseInfoDao.findById(this.openId);
		if(tbBaseInfo!=null&& CommonUtil.trim(tbBaseInfo.getState()).equalsIgnoreCase("finish")){
			this.outMsg=WeiUtil.getTextMessage(clickEventReqBean, "您好，您的微调查已经完成，感谢您的参与和支持！！");
			return true;
		} 
		ItemResObject itemResObject; 
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>(); 
		itemResObject=new ItemResObject();
		itemResObject.setTitle("微调查"); 
		itemResObject.setDescription("绍兴市公安局、绍兴市教育局联合开展的安全文明出行微调查！！"); 
		itemResObject.setPicUrl(String.format(pic_investigate_headerUrl,this.SERVER_IP));  
		itemResObject.setUrl(String.format(investigate_eduUrl,this.SERVER_IP,this.openId));
		articles.add(itemResObject);   
		this.outMsg=WeiUtil.getNewsMessage(clickEventReqBean, articles) ;  
		return true;    
		 
	}
}