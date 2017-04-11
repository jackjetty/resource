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

 
public interface HandlerService { 
	
	/**
	 * 统一处理器
	 * @param msg
	 * @return
	 */
	public BaseBean allType(BaseBean inMsg);
	
	/**
	 * 文字内容的消息处理
	 * @param msg
	 * @return
	 */
	public BaseBean textTypeMsg(TextReqBean inMsg);
	
	/**
	 * 地理位置类型的消息处理
	 * @param msg
	 * @return
	 */
	public BaseBean locationTypeMsg(LocationReqBean inMsg);
	
	/**
	 * 图片类型的消息处理
	 * @param msg
	 * @return
	 */
	public BaseBean imageTypeMsg(ImageReqBean inMsg);
	
	/**
	 * 视频类型的消息处理
	 * @param msg
	 * @return
	 */
	public BaseBean videoTypeMsg(VideoReqBean inMsg);
	
	/**
	 * 链接类型的消息处理
	 * @param msg
	 * @return
	 */
	public BaseBean linkTypeMsg(LinkReqBean inMsg);
	/**
	 * 语音类型的消息处理
	 * @param msg
	 * @return
	 */
	public BaseBean voiceTypeMsg(VoiceReqBean inMsg);
	/**
	 * 事件类型的消息处理。<br/>
	 * 在用户首次关注公众账号时，系统将会推送一条subscribe的事件
	 * @param msg
	 * @return
	 */
	public BaseBean clickEventTypeMsg(ClickEventReqBean inMsg);

	public BaseBean scribeEventTypeMsg(ScribeEventReqBean inMsg); 
	
	public BaseBean unScribeEventTypeMsg(ScribeEventReqBean inMsg); 
	
	public BaseBean scanEventTypeMsg(ScanEventReqBean inMsg); 
	
	public String getImageFilePath();
	 
	
}