package com.detachment.wei.service.impl; 
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date; 

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
import org.rising.wei.bean.res.TextResBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; 

import cn.rising.model.SessionModel;

import com.detachment.dao.UserDao;
import com.detachment.util.CommonUtil;
import com.detachment.wei.bean.store.AccidentStoreBean;
import com.detachment.wei.bean.store.CarMoveStoreBean;
import com.detachment.wei.bean.store.HandyPhotoStoreBean;
import com.detachment.wei.bean.store.UserStoreBean;
import com.detachment.wei.bean.store.info.PictureStoreInfo;
import com.detachment.wei.service.ProcessService;
 
  
 
public class  ProcessServiceImpl implements ProcessService{
	protected BaseBean inMsg;
	protected BaseBean outMsg;
	protected SessionModel session;
	protected Object stage; 
	protected String openId;
	protected String imageFilePath; 
	
	@Value("${server.ip}") 
	protected String  SERVER_IP; 
	
	
	@Value("${detachment.contact}") 
	protected String  DETACHMENT_CONTACT; 
	@Value("${detachment.name}") 
	protected String  DETACHMENT_NAME;
	 
	
	@Value("${root.dir}") 
	protected String  ROOT_DIR; 
	
	@Autowired
	private UserDao userDao;
	
	protected int auditIndex;
	protected ArrayList<String> procedureList;
	
	protected String auditId;
	
	
	protected String AUDITPROCESSID;
	 
	
	//通用的处理
	public void initProcess(BaseBean inMsg,SessionModel session){
		this.inMsg=inMsg; 
		this.session=session;
		this.stage=this.session.getAttribute("lastOperationStage");
		this.auditIndex=Integer.parseInt(this.session.getAttribute("auditIndex")==null?"-1":this.session.getAttribute("auditIndex").toString());
		 
		if(this.session.getAttribute("procedureList")!=null){
			procedureList=(ArrayList<String>)this.session.getAttribute("procedureList");
		}
		if(this.session.getAttribute("auditId")==null){
			this.auditId=null;
		}
		else{
			this.auditId=this.session.getAttribute("auditId").toString();
		}
		if(this.session.getAttribute("AUDITPROCESSID")==null){
			this.AUDITPROCESSID=null;
		}
		else{
			this.AUDITPROCESSID=this.session.getAttribute("AUDITPROCESSID").toString();
		}
		 
		this.openId=this.inMsg.getFromUserName(); 
	}
	public boolean clickEventProcess(ClickEventReqBean inMsg){ 
		return false;
	}
	public boolean textProcess(TextReqBean inMsg){
		return false;
	}
	public boolean imageProcess(ImageReqBean inMsg,String imageFilePath){
		
		this.imageFilePath=imageFilePath;
		return false;
	}
	public boolean linkProcess(LinkReqBean inMsg){
		return false;
	}
	public boolean locationProcess(LocationReqBean inMsg){
		return false;
	}
	public boolean videoProcess(VideoReqBean inMsg){
		return false;
	}
	public boolean voiceProcess(VoiceReqBean inMsg){
		return false;
	}
	public boolean scribeEventProcess(ScribeEventReqBean inMsg){
		 
		return false;
	}
	public boolean scanEventProcess(ScanEventReqBean inMsg){
		return false;
	}  
	public BaseBean getOutMessage(){
		return outMsg;
	}
	
	
	protected UserStoreBean getSessionUserStoreBean(){ 
		UserStoreBean userStoreBean;
		if(this.session.getAttribute("userStoreBean")==null){
			userStoreBean=new UserStoreBean(); 
			this.session.setAttribute("userStoreBean",userStoreBean); 
		}else{
			userStoreBean=(UserStoreBean)this.session.getAttribute("userStoreBean");
		} 
		return userStoreBean;   
	}
	
	protected AccidentStoreBean getSessionAccidentStoreBean(){ 
		AccidentStoreBean accidentStoreBean;
		if(this.session.getAttribute("accidentStoreBean")==null){
			accidentStoreBean=new AccidentStoreBean(); 
			this.session.setAttribute("accidentStoreBean",accidentStoreBean); 
		}else{
			accidentStoreBean=(AccidentStoreBean)this.session.getAttribute("accidentStoreBean");
		} 
		return accidentStoreBean;   
	}
	  protected CarMoveStoreBean getSessionCarMoveStoreBean(){
		CarMoveStoreBean carMoveStoreBean;
		if(this.session.getAttribute("carMoveStoreBean")==null){
			carMoveStoreBean=new CarMoveStoreBean(); 
			this.session.setAttribute("carMoveStoreBean",carMoveStoreBean); 
		}else{
			carMoveStoreBean=(CarMoveStoreBean)this.session.getAttribute("carMoveStoreBean");
		} 
		return carMoveStoreBean; 
	}
	/*
	protected CongestStoreBean getSessionCongestStoreBean(){
		CongestStoreBean congestStoreBean;
		if(this.session.getAttribute("congestStoreBean")==null){
			congestStoreBean=new CongestStoreBean(); 
			this.session.setAttribute("congestStoreBean",congestStoreBean); 
		}else{
			congestStoreBean=(CongestStoreBean)this.session.getAttribute("congestStoreBean");
		} 
		return congestStoreBean; 
	} */
	
	protected HandyPhotoStoreBean getSessionHandyPhotoStoreBean(){
		HandyPhotoStoreBean handyPhotoStoreBean;
		if(this.session.getAttribute("handyPhotoStoreBean")==null){
			handyPhotoStoreBean=new HandyPhotoStoreBean(); 
			this.session.setAttribute("handyPhotoStoreBean",handyPhotoStoreBean); 
		}else{
			handyPhotoStoreBean=(HandyPhotoStoreBean)this.session.getAttribute("handyPhotoStoreBean");
		} 
		return handyPhotoStoreBean; 
	} 
	
	
	 
	protected void hopeMessage(){
		 
		tipMessage("建设中，敬请期待....../:8*");
	}
	protected void tipMessage(){
		 
		tipMessage("请输入正确的指令....../:8*");
	} 
	protected void tipMessage(String message){ 
		TextResBean localResBean=new TextResBean();
		localResBean.setCreateTime(this.inMsg.getCreateTime());
		localResBean.setFromUserName(this.inMsg.getToUserName());
		localResBean.setToUserName(this.inMsg.getFromUserName());
		localResBean.setMsgType(ProcessService.MESSAGE_TYPE_TEXT);
		localResBean.setContent(message);  
		this.outMsg=localResBean; 
	} 
	 
	protected void addPicture(String type,String info,String picName){
		AccidentStoreBean accidentStoreBean=this.getSessionAccidentStoreBean(); 
		PictureStoreInfo pictureInfo= new PictureStoreInfo();
		pictureInfo.setType(type);
		pictureInfo.setInfo(info);  
		pictureInfo.setUrl(this.imageFilePath);
		pictureInfo.setName(picName);
		ArrayList<PictureStoreInfo> pictureInfoList= accidentStoreBean.getPictureInfoList(); 
		if(pictureInfoList==null)
			pictureInfoList=new ArrayList<PictureStoreInfo>();
		pictureInfoList.add(pictureInfo);  
		accidentStoreBean.setPictureInfoList(pictureInfoList); 
		this.session.setAttribute("accidentStoreBean",accidentStoreBean); 
	}   
}