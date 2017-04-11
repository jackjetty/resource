package com.traffic.wei.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map; 
 

import javax.servlet.ServletContext; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller; 


import cn.rising.model.SessionModel;
import cn.rising.redis.dao.SessionRedisDao;

import com.opensymphony.xwork2.ActionContext;  
import com.thoughtworks.xstream.XStream;
 
import com.traffic.util.CommonUtil;
import com.traffic.util.XmlUtil;
import org.rising.wei.bean.BaseBean;
import org.rising.wei.bean.req.ClickEventReqBean;
import org.rising.wei.bean.req.ImageReqBean;
import  org.rising.wei.bean.req.LinkReqBean;
import  org.rising.wei.bean.req.LocationReqBean;
import  org.rising.wei.bean.req.ScanEventReqBean;
import  org.rising.wei.bean.req.ScribeEventReqBean;
import  org.rising.wei.bean.req.TextReqBean;
import  org.rising.wei.bean.req.VideoReqBean;
import org.rising.wei.bean.req.VoiceReqBean;
import org.rising.wei.bean.res.NewsResBean;
import org.rising.wei.bean.res.TextResBean;
import org.rising.wei.bean.res.object.ItemResObject;
 
import com.traffic.wei.service.HandlerService;
import com.traffic.wei.service.ProcessService;
import org.json.simple.*;
@Scope("prototype")
@Controller("platFormAction")
public class PlatFormAction  implements ServletRequestAware, ServletResponseAware, ServletContextAware,ApplicationAware{
	Log log = LogFactory.getLog(PlatFormAction.class); 
	 
    private HttpServletRequest request; 
    private ServletContext servletContext; 
    private HttpServletResponse response; 
    private PrintWriter pw = null;
    private Map <String, Object>application;
    
    
    @Value("${wei.token}") 
	private   String Token ; 
    
    @Autowired
	private SessionRedisDao sessionRedisDao;
    
    @Override 
    public void setApplication(Map<String, Object> application) { 
        this.application= application; 
    }
    @Override 
    public void setServletRequest(HttpServletRequest req) { 
        this.request=req; 
    } 
    @Override 
    public void setServletResponse(HttpServletResponse res) { 
        this.response=res; 
    } 
    @Override 
    public void setServletContext(ServletContext ser) { 
        this.servletContext=ser; 
    } 
    
	public void chat() {  
		 
	 
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8"); 
			pw=response.getWriter();
		} catch (IOException e) { 
			e.printStackTrace();
			log.error("getChat()->" + e); 
			return;
		}  
		if(request.getMethod().equalsIgnoreCase("get")){ 
			this.getChat(); 
			return;
		}
		this.postChat(); 
		//session.put("captchaCode", captchaCode);  
	}
	private void getChat() { 
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		if(signature==null||timestamp==null||nonce==null)
			 return;
		String[] ArrTmp = { Token, timestamp, nonce };
		Arrays.sort(ArrTmp);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ArrTmp.length; i++) {
			sb.append(ArrTmp[i]);
		}
		String pwd = CommonUtil.Encrypt(sb.toString());
		String echostr = request.getParameter("echostr"); 
		if(pwd.equals(signature)){
			if(!"".equals(echostr) && echostr != null){
				 
				pw.print(echostr); 
				return;
			}
		} 
	}
	
	@Autowired
	private HandlerService handlerService;
	 
 
 
	@Autowired
	private ProcessService formalAccidentProcessService; 
	
	@Autowired
	private ProcessService doAnswerProcessService;
	@Autowired
	private ProcessService feedBackProcessService;
	@Autowired
	private ProcessService studyPolicyProcessService;
	 
	@Autowired
	private ProcessService testAccidentProcessService; 
	
	@Autowired
	private ProcessService accidentAuditProcessService; 
	
	@Autowired
	private ProcessService workGuideProcessService;
	
	@Autowired
	private ProcessService dMVProcessService;
	
	
	@Autowired
	private ProcessService carMoveProcessService;
	
	@Autowired
	private ProcessService carMoveAuditProcessService;
	
	private String imageFilePath="";
 
	private void postChat() {  
		BaseBean inMsg;
		BaseBean outMsg,handlerOutMsg,processOutMsg=null;   
		StringBuffer sb = new StringBuffer();
		String inMsgXml="";
		String line;
		Map<String, String> map = null;   
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			} 
			inMsgXml=sb.toString();
			map = XmlUtil.xml2Map(inMsgXml); 
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} 
		String openId=map.get("xml.FromUserName");
		SessionModel session;
		
		session=sessionRedisDao.get(openId);
		
		
		if( session ==null){
			session=new SessionModel();
			session.setOpenId(openId);
			session.setLastAccessedTime(System.currentTimeMillis());
			sessionRedisDao.add(session);
		}    
		 
		inMsg=new BaseBean(); 
		inMsg.setCreateTime(map.get("xml.CreateTime")); 
		inMsg.setFromUserName(openId);
		inMsg.setMsgType(map.get("xml.MsgType"));
		inMsg.setToUserName(map.get("xml.ToUserName"));  
		/*XStream xstream = new XStream();     
		xstream.autodetectAnnotations(true);*/
		XStream xstream=XmlUtil.init(true); 
		handlerOutMsg=handlerService.allType(inMsg);
		
		
		
	    if(inMsg.getMsgType().equalsIgnoreCase("image")){  
			xstream.processAnnotations(ImageReqBean.class);
			xstream.alias("xml", ImageReqBean.class); 
			inMsg= (ImageReqBean) xstream.fromXML(inMsgXml); 
			 
			handlerOutMsg=handlerService.imageTypeMsg((ImageReqBean)inMsg); 
			imageFilePath=handlerService.getImageFilePath();
			processOutMsg=processMessage(session,inMsg,"image");  
			 
		}
		if(inMsg.getMsgType().equalsIgnoreCase("link")){ 
			xstream.processAnnotations(LinkReqBean.class);
			xstream.alias("xml", LinkReqBean.class); 
			inMsg= (LinkReqBean) xstream.fromXML(inMsgXml); 
			handlerOutMsg=handlerService.linkTypeMsg((LinkReqBean)inMsg);
			processOutMsg=processMessage(session,inMsg,"link"); 
		}
		if(inMsg.getMsgType().equalsIgnoreCase("location")){ 
			 
			xstream.processAnnotations(LocationReqBean.class);
			xstream.alias("xml", LocationReqBean.class); 
			inMsg= (LocationReqBean) xstream.fromXML(inMsgXml);
			handlerOutMsg=handlerService.locationTypeMsg((LocationReqBean)inMsg);
			processOutMsg=processMessage(session,inMsg, "location"); 
		}
		if(inMsg.getMsgType().equalsIgnoreCase("text")){
			 
			//processService=new TextProcessServiceImpl();
			//xstream.processAnnotations(TextReqBean.class);
			xstream.alias("xml", TextReqBean.class);  
			inMsg= (TextReqBean) xstream.fromXML(inMsgXml);
			handlerOutMsg=handlerService.textTypeMsg((TextReqBean)inMsg);
			processOutMsg=processMessage(session,inMsg, "text"); 
		}
		if(inMsg.getMsgType().equalsIgnoreCase("video")){  
			xstream.processAnnotations(VideoReqBean.class);
			xstream.alias("xml", VideoReqBean.class); 
			inMsg= (VideoReqBean) xstream.fromXML(inMsgXml);
			handlerOutMsg=handlerService.videoTypeMsg((VideoReqBean)inMsg);
			processOutMsg=processMessage(session,inMsg, "video");
		}
		if(inMsg.getMsgType().equalsIgnoreCase("voice")){ 
		  
			xstream.processAnnotations(VoiceReqBean.class);
			xstream.alias("xml", VoiceReqBean.class); 
			inMsg= (VoiceReqBean) xstream.fromXML(inMsgXml);
			handlerOutMsg=handlerService.voiceTypeMsg((VoiceReqBean)inMsg);
			processOutMsg=processMessage(session,inMsg, "voice");
		} 
		
		if(inMsg.getMsgType().equalsIgnoreCase("event")){
			String event=map.get("xml.Event")==null?"":map.get("xml.Event").trim();
			if(event.equalsIgnoreCase("subscribe")){
				  
				xstream.processAnnotations(ScribeEventReqBean.class);
				xstream.alias("xml", ScribeEventReqBean.class);  
				inMsg= (ScribeEventReqBean) xstream.fromXML(inMsgXml);
			 
				
				handlerOutMsg=handlerService.scribeEventTypeMsg((ScribeEventReqBean)inMsg); 
				processOutMsg=processMessage(session,inMsg, "SUBSCRIBE");
			}
			if(event.equalsIgnoreCase("unsubscribe")){ 
				xstream.processAnnotations(ScribeEventReqBean.class);
				xstream.alias("xml", ScribeEventReqBean.class);  
				inMsg= (ScribeEventReqBean) xstream.fromXML(inMsgXml); 
				handlerOutMsg=handlerService.unScribeEventTypeMsg((ScribeEventReqBean)inMsg); 
				//processOutMsg=processMessage(session,inMsg, "SUBSCRIBE");
				pw.print("");
				return;
			}
			
			
			if(event.equalsIgnoreCase("SCAN")){ 
				xstream.processAnnotations(ScanEventReqBean.class);
				xstream.alias("xml", ScanEventReqBean.class);
				inMsg= (ScanEventReqBean) xstream.fromXML(inMsgXml);
				handlerOutMsg=handlerService.scanEventTypeMsg((ScanEventReqBean)inMsg); 
				processOutMsg=processMessage(session,inMsg, "SCAN"); 
			}
            if(event.equalsIgnoreCase("LOCATION")){
            	 
			} 
            
            if(event.equalsIgnoreCase("CLICK")){
            	 
            	xstream.processAnnotations(ClickEventReqBean.class);
            	xstream.alias("xml", ClickEventReqBean.class);
            	inMsg= (ClickEventReqBean) xstream.fromXML(inMsgXml);
            	handlerOutMsg=handlerService.clickEventTypeMsg((ClickEventReqBean)inMsg);
            	//预处理
            	session.setAttribute("AUDIT", null);
        		session.setAttribute("AUDITPROCESSID", null);
            	
            	
            	processOutMsg=processMessage(session,inMsg, "CLICK");   
				
				
			}
			
		} 
		 
        session.setLastAccessedTime(System.currentTimeMillis());
		
        
		
		sessionRedisDao.update(session);  
		outMsg=new BaseBean();
		if(processOutMsg!=null)
			outMsg=processOutMsg;
		else
			outMsg=handlerOutMsg; 
		if(outMsg instanceof TextResBean){
			xstream.alias("xml", TextResBean.class); 
			if(((TextResBean )outMsg).getContent().equalsIgnoreCase("")){
				
				pw.print("");
				return;
			}
			
			
			
		}
		if(outMsg instanceof NewsResBean){
			xstream.alias("xml", NewsResBean.class); 
			xstream.alias("item", ItemResObject.class); 
		}   
		String xml = xstream.toXML(outMsg);  
		pw.print(xml);
		
	}
 
	private BaseBean processMessage(SessionModel session,BaseBean inMsg,String msgType){
		ArrayList<ProcessService> arrayList=new ArrayList<ProcessService>();
		boolean sign=false;
		if(session.getAttribute("AUDIT")!=null
				&&session.getAttribute("AUDIT").toString().equalsIgnoreCase("true")
				&&session.getAttribute("AUDITPROCESSID")!=null
				&&session.getAttribute("AUDITPROCESSID").toString().equalsIgnoreCase("ACCIDENT")){
			arrayList.add(accidentAuditProcessService);
			sign=true;
			
		}
		if(session.getAttribute("AUDIT")!=null
				&&session.getAttribute("AUDIT").toString().equalsIgnoreCase("true")
				&&session.getAttribute("AUDITPROCESSID")!=null
				&&session.getAttribute("AUDITPROCESSID").toString().equalsIgnoreCase("CARMOVE")){
			arrayList.add(carMoveAuditProcessService);
			sign=true;
			
		}
		if(!sign){  
			arrayList.add(formalAccidentProcessService); 
			arrayList.add(testAccidentProcessService);
			arrayList.add(carMoveProcessService); 
			arrayList.add(doAnswerProcessService);
			arrayList.add(feedBackProcessService);
			arrayList.add(studyPolicyProcessService);
			arrayList.add(workGuideProcessService);
			arrayList.add(dMVProcessService); 
		}
		
		
		for(ProcessService processService:arrayList){ 
	    	processService.initProcess(inMsg, session);
	    	if(msgType.equalsIgnoreCase("CLICK")&&processService.clickEventProcess((ClickEventReqBean) inMsg)){ 
		    		return processService.getOutMessage();   
	    	}
	    	if(msgType.equalsIgnoreCase("SCAN")&&processService.scanEventProcess((ScanEventReqBean) inMsg)){
	    		return processService.getOutMessage();   
	    	}
	    	if(msgType.equalsIgnoreCase("SUBSCRIBE")&&processService.scribeEventProcess ((ScribeEventReqBean) inMsg)){
	    		return processService.getOutMessage();   
	    	}
	    	
	    	 
	    	if(msgType.equalsIgnoreCase("image")&&processService.imageProcess((ImageReqBean) inMsg,imageFilePath)){ 
	    		return processService.getOutMessage();   
	    	}
	    	if(msgType.equalsIgnoreCase("link")&&processService.linkProcess   ((LinkReqBean) inMsg)){
	    		return processService.getOutMessage();   
	    	}
	    	if(msgType.equalsIgnoreCase("location")&&processService.locationProcess((LocationReqBean) inMsg)){
	    		return processService.getOutMessage();   
	    	}
	    	if(msgType.equalsIgnoreCase("text")&&processService.textProcess((TextReqBean) inMsg)){
	    		return processService.getOutMessage();   
	    	}
	    	if(msgType.equalsIgnoreCase("video")&&processService.videoProcess((VideoReqBean) inMsg)){
	    		return processService.getOutMessage();   
	    	}
	    	if(msgType.equalsIgnoreCase("voice")&&processService.voiceProcess((VoiceReqBean) inMsg)){
	    		return processService.getOutMessage();   
	    	} 
			
	    } 
		return null;   
	} 
	
	
}
