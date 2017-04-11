package com.detachment.wei.service.impl; 
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import java.util.regex.Pattern; 
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod; 
import org.rising.wei.bean.BaseBean;
import org.rising.wei.bean.UserBean;
import org.rising.wei.bean.req.ClickEventReqBean;
import org.rising.wei.bean.req.ImageReqBean;
import org.rising.wei.bean.req.LinkReqBean;
import org.rising.wei.bean.req.LocationReqBean;
import org.rising.wei.bean.req.ScanEventReqBean;
import org.rising.wei.bean.req.ScribeEventReqBean;
import org.rising.wei.bean.req.TextReqBean;
import org.rising.wei.bean.req.VideoReqBean;
import org.rising.wei.bean.req.VoiceReqBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service; 

import com.alibaba.fastjson.JSON; 
import com.detachment.dao.PersonalPromotionDao;
import com.detachment.dao.UserDao;
import com.detachment.dao.WeiUserDao;
import com.detachment.pojo.TbPersonalPromotion;
import com.detachment.pojo.TbWeiUser;
import com.detachment.util.CommonUtil;
import com.detachment.util.HttpWeiUtil;
import com.detachment.util.WeiUtil;
import com.detachment.wei.service.HandlerService; 
import com.detachment.wei.thread.FileThread;
 
@Service("handlerService")  
public class HandlerServiceImpl implements HandlerService{ 
	
	private String imageFilePath;  
	@Value("${detachment.name}") 
	protected String  DETACHMENT_NAME;
	
	@Value("${root.dir}") 
	private String  ROOT_DIR; 
	
	
	@Value("${wei.appid}") 
	private String  APPID; 
	
	@Value("${wei.appsecret}") 
	private String  APPSECRET; 
	
	@Autowired
	private UserDao userDao; 
	  
	@Autowired
	private WeiUserDao weiUserDao; 
	
	 
	
	@Override
	public String getImageFilePath(){
		return CommonUtil.trim(imageFilePath);
	}
	@Override
	public BaseBean allType(BaseBean inMsg){  
		String openId=inMsg.getFromUserName().trim();  
		TbWeiUser tbWeiUser=weiUserDao.findById(openId);
		if(tbWeiUser==null){ 
			tbWeiUser=new TbWeiUser();
			tbWeiUser.setOpenId(openId); 
		}
		if(CommonUtil.trim(tbWeiUser.getNickname()).equalsIgnoreCase("")){
			UserBean userBean=null; 
			try {
				userBean=HttpWeiUtil.getInstance(APPID, APPSECRET).getUserBean(openId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(userBean!=null){ 
				tbWeiUser.setCity(userBean.getCity());
				tbWeiUser.setCountry(userBean.getCountry());
				tbWeiUser.setHeadimgurl(userBean.getHeadimgurl());
				tbWeiUser.setLanguage(userBean.getLanguage());
				tbWeiUser.setNickname(userBean.getNickname());
				tbWeiUser.setProvince(userBean.getProvince());
				tbWeiUser.setSex(userBean.getSex());
				tbWeiUser.setSubscribe(userBean.getSubscribe()); 
				weiUserDao.saveOrUpdate(tbWeiUser);
				//tbWeiUser.setSubscribeTime(Long.toString(userBean.getSubscribe_time())); 
			}  
		}  
		
		return WeiUtil.getTextMessage(inMsg, "您好，感谢您的关注！！/::P");  
	} 
	@Override
	public BaseBean textTypeMsg(TextReqBean inMsg) { 
		return WeiUtil.getTextMessage(inMsg, "您好，感谢您的关注！！/::P"); 
	} 
	@Override
	public BaseBean locationTypeMsg(LocationReqBean inMsg) { 
		return WeiUtil.getTextMessage(inMsg, "您好，感谢您的关注！！/::P");
	} 
	@Override
	public BaseBean imageTypeMsg(ImageReqBean inMsg) {
		
		 
		String picName=System.currentTimeMillis()+".jpg"; 
		this.imageFilePath=""; 
		this.imageFilePath=ROOT_DIR+"imageBak"+File.separator;
		this.imageFilePath+=CommonUtil.getDateForm()+File.separator;
		this.imageFilePath+=picName; 
	 
		new FileThread(inMsg.getPicUrl(),this.imageFilePath).start();    
		 
    	 
		return WeiUtil.getTextMessage(inMsg, "您好，感谢您的关注！！/::P");
	} 
	@Override
	public BaseBean videoTypeMsg(VideoReqBean inMsg) { 
		return WeiUtil.getTextMessage(inMsg, "您好，感谢您的关注！！/::P");
	} 
	@Override
	public BaseBean linkTypeMsg(LinkReqBean inMsg) { 
		return WeiUtil.getTextMessage(inMsg, "您好，感谢您的关注！！/::P");
	}

	@Override
	public BaseBean voiceTypeMsg(VoiceReqBean inMsg) {
		 
		return WeiUtil.getTextMessage(inMsg, "您好，感谢您的关注！！/::P");
	}

	@Override
	public BaseBean clickEventTypeMsg(ClickEventReqBean inMsg) {  
		return WeiUtil.getTextMessage(inMsg, "您好，感谢您的关注！！/::P");
	}
    //关注的时候所做的事情 
	@Override
	public BaseBean scribeEventTypeMsg(ScribeEventReqBean inMsg) {  
		StringBuffer textBuffer=new StringBuffer("");
		textBuffer.append("您好！\n");
		textBuffer.append("欢迎进入"+this.DETACHMENT_NAME+"微信平台,\n");
		textBuffer.append("我们为您提供轻微事故快速处理等服务，您的满意是我们最大的追求！");  
		if(CommonUtil.trim(inMsg.getTicket()).equalsIgnoreCase("")){
			return WeiUtil.getTextMessage(inMsg, textBuffer.toString()) ; 
		} 
		String openId=inMsg.getFromUserName().trim();  
		TbWeiUser tbWeiUser=weiUserDao.findById(openId);
		 
		if(tbWeiUser==null){
			tbWeiUser=new TbWeiUser();
			tbWeiUser.setOpenId(openId); 
			 
		} 
		String strSceneId=inMsg.getEventKey().replace("qrscene_", "");
		strSceneId=CommonUtil.trim(strSceneId);
		int sceneId;
		if(tbWeiUser.getSceneId()==null ){
			tbWeiUser.setSceneId(0); 
		}
		
		if(tbWeiUser.getSceneId()==0&& CommonUtil.isInteger(strSceneId) ){
			sceneId=new Integer(strSceneId).intValue();
			tbWeiUser.setSceneId(sceneId); 
			 
		}
		weiUserDao.saveOrUpdate(tbWeiUser); 
		return WeiUtil.getTextMessage(inMsg, textBuffer.toString()) ;   
	}
	
	
	public BaseBean unScribeEventTypeMsg(ScribeEventReqBean inMsg){
		String openId=inMsg.getFromUserName().trim();  
		TbWeiUser tbWeiUser=weiUserDao.findById(openId);
		if(tbWeiUser==null){
			tbWeiUser=new TbWeiUser();
			tbWeiUser.setOpenId(openId); 
		}  
		int sceneId=0 ;
		if(tbWeiUser.getSceneId()!=null)
			sceneId=tbWeiUser.getSceneId();
		 
		 
		tbWeiUser.setSceneId(0);
		tbWeiUser.setSubscribe(0);  
		weiUserDao.saveOrUpdate(tbWeiUser);  
		return WeiUtil.getTextMessage(inMsg, "");
	}

	@Override
	public BaseBean scanEventTypeMsg(ScanEventReqBean inMsg) {
		String openId=inMsg.getFromUserName().trim();  
		TbWeiUser tbWeiUser=weiUserDao.findById(openId);
		if(tbWeiUser==null){
			tbWeiUser=new TbWeiUser();
			tbWeiUser.setOpenId(openId); 
		}
		String strSceneId=CommonUtil.trim(inMsg.getEventKey());
		
		int sceneId ;
		if(tbWeiUser.getSceneId()==null ){
			tbWeiUser.setSceneId(0); 
		}
		
		if(tbWeiUser.getSceneId()==0&& CommonUtil.isInteger(strSceneId) ){
			sceneId=new Integer(strSceneId).intValue();
			tbWeiUser.setSceneId(sceneId); 
			 
		}
		weiUserDao.saveOrUpdate(tbWeiUser);
		
		return WeiUtil.getTextMessage(inMsg, "您好，感谢您的关注！！/::P");
	}  
}
