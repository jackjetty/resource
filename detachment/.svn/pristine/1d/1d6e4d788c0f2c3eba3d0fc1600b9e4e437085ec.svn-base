package com.detachment.wei.service;
import org.rising.wei.bean.BaseBean;
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

import cn.rising.model.SessionModel;

public interface ProcessService {
	public final static String   MESSAGE_TYPE_TEXT="text";
	public final static String   MESSAGE_TYPE_IMAGE="image";
	public final static String   MESSAGE_TYPE_LOCATION="location";
	public final static String   MESSAGE_TYPE_LINK="link";
	public final static String   MESSAGE_TYPE_VOICE="voice";
	public final static String   MESSAGE_TYPE_EVENT="event";
	public final static String   MESSAGE_TYPE_VIDEO="video";
	public final static String   MESSAGE_TYPE_NEWS="news";
	
	public final static String  EVENT_TYPE_SUBSCRIBE="subscribe";
	public final static String  EVENT_TYPE_UNSUBSCRIBE="unsubscribe";
	public final static String  EVENT_TYPE_CLICK="CLICK"; 
	
	
	  
	public void initProcess(BaseBean inMsg,SessionModel session );
	public boolean clickEventProcess(ClickEventReqBean inMsg);
	public boolean textProcess(TextReqBean inMsg);
	public boolean imageProcess(ImageReqBean inMsg,String imageFilePath);
	public boolean linkProcess(LinkReqBean inMsg);
	public boolean locationProcess(LocationReqBean inMsg);
	public boolean videoProcess(VideoReqBean inMsg);
	public boolean voiceProcess(VoiceReqBean inMsg);
	public boolean scribeEventProcess(ScribeEventReqBean inMsg);
	public boolean scanEventProcess(ScanEventReqBean inMsg);  
	public BaseBean getOutMessage();
	
}