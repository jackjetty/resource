package com.detachment.wei.service.impl;
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
import org.rising.wei.bean.req.ClickEventReqBean;
import org.rising.wei.bean.req.ImageReqBean;
import org.rising.wei.bean.req.LocationReqBean;
import org.rising.wei.bean.req.TextReqBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;  

import com.detachment.dao.ProcessDao;
import com.detachment.util.Constant;
 
import com.detachment.dao.CarMoveDao;
import com.detachment.dao.HandyPhotoDao;
import com.detachment.dao.InfoPicDao;
import com.detachment.dao.InfoTextDao;
import com.detachment.dao.WeiUserDao;
import com.detachment.pojo.TbHandyPhoto;
import com.detachment.pojo.TbInfoPic;
import com.detachment.pojo.TbInfoPicId;
import com.detachment.pojo.TbInfoText;
import com.detachment.pojo.TbInfoTextId;
import com.detachment.pojo.TbWeiUser;
import com.detachment.util.CommonUtil;
import com.detachment.wei.bean.store.HandyPhotoStoreBean;
import com.detachment.wei.bean.store.info.LocationStoreInfo;
import com.detachment.wei.bean.store.info.PictureStoreInfo;
 
import com.detachment.wei.process.HandyPhotoProcess;
import com.detachment.wei.thread.CopyThread;
  
@Service("handyPhotoProcessService")
public class HandyPhotoProcessServiceImpl   extends ProcessServiceImpl{ 
	@Autowired
	private ProcessDao processDao; 
	@Autowired
	private HandyPhotoDao handyPhotoDao; 
	@Autowired
	private InfoPicDao infoPicDao;  
	@Autowired
	private InfoTextDao infoTextDao; 
	@Autowired
	private WeiUserDao weiUserDao; 
	
	@Value("${wei.detachment.handyPhoto.url}") 
	private String  detachmentHandyPhoto_Url; 
	 
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true;
		sign=eventKey.equalsIgnoreCase(Constant.HANDYPHOTO)?sign:false;
		if(!sign) return false;  
		if(!processDao.isUsing(Constant.HANDYPHOTO)){
			this.hopeMessage();
			return true;
		} 
		//感谢您的支持！，请点击这里 举报注意事项，请点击这里 ，
		StringBuffer textBuffer=new StringBuffer("");  
		textBuffer.append("欢迎使用随手拍举报交通违法功能。 \n");
		textBuffer.append("举报人应仔细阅读举报范围说明、\n");
		textBuffer.append("举报注意事项，确保举报信息真实有效，\n");
		textBuffer.append("并承担相应法律责任。\n");  
		textBuffer.append("<a href='"+String.format(detachmentHandyPhoto_Url,this.SERVER_IP) +"'>举报范围说明，点击查看详细规定</a>\n\n"); 
		TbWeiUser tbWeiUser=weiUserDao.findById(this.openId); 
		String phoneNumber=CommonUtil.trim(tbWeiUser.getPhoneNumber()); 
        if(phoneNumber.equals("")){
        	textBuffer.append("为确保能及时与您联系，\n");
        	textBuffer.append("请输入您的手机号码 ：\n\n");
        	textBuffer.append("如不方便提供手机号，请回复 1");
        	this.session.setAttribute("lastOperationStage",HandyPhotoProcess.START);
        }
        else{
        	HandyPhotoStoreBean handyPhotoStoreBean=this.getSessionHandyPhotoStoreBean();
        	handyPhotoStoreBean.setReportPhoneNumber(phoneNumber);
    		textBuffer.append("如属现场举报，\n");
    		textBuffer.append("请点击“+”按钮然后点击“位置”，\n");
    		textBuffer.append("待定位成功后点击“发送”,\n");
    		textBuffer.append("上传举报现场的位置。\n\n" );
    		textBuffer.append("如非现场举报，请文字输入现场位置，如“家有超市门口”。");
    		this.session.setAttribute("handyPhotoStoreBean", handyPhotoStoreBean); 
    		this.session.setAttribute("lastOperationStage",HandyPhotoProcess.PHONENUMBER);
        } 
		this.tipMessage(textBuffer.toString()); 
		return true;  
		 
	}
	public boolean imageProcess(ImageReqBean imageReqBean,String imageFilePath){ 
		 super.imageProcess(imageReqBean,imageFilePath); 
		 if(imageProcess_Phote(imageReqBean)) return true; 
		 return false;
	}
	 
	private boolean imageProcess_Phote(ImageReqBean imageReqBean){  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  HandyPhotoProcess;  
		if(!sign) return false;
		sign=stage.equals(HandyPhotoProcess.LOCATION)||stage.equals(HandyPhotoProcess.PHOTE);
		if(!sign) return false;   
		HandyPhotoStoreBean handyPhotoStoreBean=this.getSessionHandyPhotoStoreBean(); 
		ArrayList<PictureStoreInfo> pictureInfoList= handyPhotoStoreBean.getPictureInfoList(); 
		if(stage.equals(HandyPhotoProcess.LOCATION))
			       pictureInfoList=null;
		
		if(pictureInfoList==null)
			pictureInfoList=new ArrayList<PictureStoreInfo>(); 
		int picIndex=pictureInfoList.size()+1;
		PictureStoreInfo pictureInfo= new PictureStoreInfo();
		pictureInfo.setType("附照");
		pictureInfo.setInfo("附照");  
		pictureInfo.setUrl(this.imageFilePath);
		pictureInfo.setName("附照_"+picIndex+".jpg");
		
		pictureInfoList.add(pictureInfo);  
		handyPhotoStoreBean.setPictureInfoList(pictureInfoList);  
		
	
	 
		StringBuffer txtBuffer=new StringBuffer("");
		txtBuffer.append("已收到"+picIndex+"张照片,\n");
		txtBuffer.append("如有需求请继续上传照片,\n"); 
		txtBuffer.append("如已图片上传完毕，\n");
		txtBuffer.append("请文字简要描述时间、位置、方向、原因等信息");
		 
		this.tipMessage(txtBuffer.toString());  
		this.session.setAttribute("handyPhotoStoreBean", handyPhotoStoreBean);
		this.session.setAttribute("lastOperationStage", HandyPhotoProcess.PHOTE); 
		return sign;  
		
	} 
	public boolean locationProcess(LocationReqBean locationReqBean){
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  HandyPhotoProcess;  
		if(!sign) return false;
		sign=stage.equals(HandyPhotoProcess.PHONENUMBER);
		if(!sign) return false; 
		if(locationReqBean.getLabel().trim().equals("")){
			 this.tipMessage("您提交的地理位置信息不明确，请重新提交！");
			 return true;
		}
		
		HandyPhotoStoreBean handyPhotoStoreBean=this.getSessionHandyPhotoStoreBean();
		LocationStoreInfo locationInfo=new  LocationStoreInfo();
		locationInfo.setAddress(locationReqBean.getLabel());
		locationInfo.setLocationX(locationReqBean.getLocation_X()); 
		locationInfo.setLocationY(locationReqBean.getLocation_Y()); 
		handyPhotoStoreBean.setLocationInfo(locationInfo); 
		StringBuffer txtBuffer=new StringBuffer("");
	    txtBuffer.append("您标注的地理位置是："+locationReqBean.getLabel()+"\n\n");   
		txtBuffer.append("上传违法车辆照片，\n");  
		txtBuffer.append("请点击“+”按钮，\n"); 
		txtBuffer.append("然后点击“拍摄”(或“照片”)按钮，\n" );
		txtBuffer.append("上传违法车辆照片。"); 
		this.tipMessage(txtBuffer.toString()); 
		this.session.setAttribute("handyPhotoStoreBean", handyPhotoStoreBean); 
		this.session.setAttribute("lastOperationStage",HandyPhotoProcess.LOCATION); 
		 
		return sign; 
	}
	public boolean textProcess(TextReqBean textReqBean){
		super.textProcess(textReqBean);
		if(textProcess_Phone(textReqBean)) return true;  
		if(textProcess_Location(textReqBean)) return true;  
		if(textProcess_Remark(textReqBean)) return true; 
		if(textProcess_Wait(textReqBean)) return true;
		return false;
	}
	private boolean  textProcess_Phone(TextReqBean textReqBean){
		String content=CommonUtil.trim(textReqBean.getContent());   
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  HandyPhotoProcess;  
		if(!sign) return false;
		sign=stage.equals(HandyPhotoProcess.START);
		if(!sign) return false; 
		if(content.equalsIgnoreCase("1")){
			content="";
		}
		else{
			sign=CommonUtil.isMobilePhone(content); 
			if(!sign){
				this.tipMessage("请输入正确的手机号码 ....../:8*");
				return true;
			} 
		}
		HandyPhotoStoreBean handyPhotoStoreBean=this.getSessionHandyPhotoStoreBean();
		handyPhotoStoreBean.setReportPhoneNumber(content);
		StringBuffer textBuffer=new StringBuffer("");
		textBuffer.append("如属现场举报，\n");
		textBuffer.append("请点击“+”按钮然后点击“位置”，\n");
		textBuffer.append("待定位成功后点击“发送”,\n");
		textBuffer.append("上传举报现场的位置。\n\n" );
		textBuffer.append("如非现场举报，请文字输入现场位置，如“家有超市门口”。");
		this.tipMessage(textBuffer.toString()); 
		this.session.setAttribute("handyPhotoStoreBean", handyPhotoStoreBean); 
		this.session.setAttribute("lastOperationStage",HandyPhotoProcess.PHONENUMBER);  
		return sign; 
	}
	private boolean textProcess_Location(TextReqBean textReqBean){
		String content=CommonUtil.trim(textReqBean.getContent());  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  HandyPhotoProcess;  
		if(!sign) return false;
		sign=stage.equals(HandyPhotoProcess.PHONENUMBER);
		if(!sign) return false;  
		 
		HandyPhotoStoreBean handyPhotoStoreBean=this.getSessionHandyPhotoStoreBean();
		LocationStoreInfo locationInfo=new  LocationStoreInfo(); 
		locationInfo.setAddress(content);
		locationInfo.setLocationX("");
		locationInfo.setLocationY(""); 
		handyPhotoStoreBean.setLocationInfo(locationInfo);
		StringBuffer txtBuffer=new StringBuffer(""); 
		txtBuffer.append("上传违法车辆照片，\n");  
		txtBuffer.append("请点击“+”按钮，\n"); 
		txtBuffer.append("然后点击“拍摄”(或“照片”)按钮，\n" );
		txtBuffer.append("上传违法车辆照片。"); 
		this.tipMessage(txtBuffer.toString()); 
		this.session.setAttribute("handyPhotoStoreBean", handyPhotoStoreBean); 
		this.session.setAttribute("lastOperationStage",HandyPhotoProcess.LOCATION);  
		return sign; 
	}
	
	private boolean textProcess_Remark(TextReqBean textReqBean){ 
		String content=CommonUtil.trim(textReqBean.getContent());  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  HandyPhotoProcess;  
		if(!sign) return false;
		sign=stage.equals(HandyPhotoProcess.PHOTE);
		if(!sign) return false;  
		HandyPhotoStoreBean handyPhotoStoreBean=this.getSessionHandyPhotoStoreBean();
	    LocationStoreInfo locationInfo=handyPhotoStoreBean.getLocationInfo();
		TbHandyPhoto tbHandyPhoto=new TbHandyPhoto(); 
		String handyPhotoId=handyPhotoDao.getNextHandyPhotoId(processDao.getPrefix(Constant.HANDYPHOTO));
		handyPhotoStoreBean.setHandyPhotoId(handyPhotoId);
		tbHandyPhoto.setHandyPhotoId(handyPhotoId);
		tbHandyPhoto.setHandyPhotoState("上报");
		tbHandyPhoto.setLocationX(locationInfo.getLocationX()) ;
		tbHandyPhoto.setLocationY(locationInfo.getLocationY());
		tbHandyPhoto.setAddress(locationInfo.getAddress()); 
		tbHandyPhoto.setReportOpenId(this.openId);
		tbHandyPhoto.setReportPhoneNumber(handyPhotoStoreBean.getReportPhoneNumber());
		Calendar cal = Calendar.getInstance(); 
        Date date=cal.getTime();   
        tbHandyPhoto.setReportTime(new Timestamp(date.getTime()));
        
        
        
        TbInfoText tbInfoText=new TbInfoText();
		TbInfoTextId tbInfoTextId=new TbInfoTextId();
		tbInfoTextId.setProcessId(Constant.HANDYPHOTO);
		tbInfoTextId.setRecordNo(handyPhotoId);
		tbInfoTextId.setTextIndex(infoTextDao.getNextTextIndex(tbInfoTextId.getProcessId(), tbInfoTextId.getRecordNo()));
		tbInfoText.setId(tbInfoTextId);
		tbInfoText.setTextMessage(content);
		tbInfoText.setTextTime(new Timestamp(date.getTime()));
		infoTextDao.save(tbInfoText); 
        
        StringBuffer txtBuffer=new StringBuffer("");
		txtBuffer.append("您的信息已收到，\n");
		txtBuffer.append("谢谢您的参与！\n");
		txtBuffer.append("我们会认真审核您的提交的信息,\n");
		txtBuffer.append("如有需要可继续输入文字。"); 
		this.tipMessage(txtBuffer.toString());  
		
		
		ArrayList<PictureStoreInfo> pictureInfoList= handyPhotoStoreBean.getPictureInfoList();
		int picIndex=0;
		TbInfoPic tbInfoPic;
		TbInfoPicId tbInfoPicId;
		String   toPathDir=this.ROOT_DIR+Constant.HANDYPHOTO+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=handyPhotoId+File.separator; 
		for(PictureStoreInfo pictureInfo:pictureInfoList) {
			picIndex++; 
			tbInfoPicId= new TbInfoPicId();
			tbInfoPicId.setProcessId(Constant.HANDYPHOTO);
			tbInfoPicId.setRecordNo(handyPhotoId); 
			tbInfoPicId.setPicIndex(picIndex);
			tbInfoPic=new TbInfoPic();
			tbInfoPic.setId(tbInfoPicId);
			tbInfoPic.setPicInfo(pictureInfo.getInfo());
			tbInfoPic.setPicType(pictureInfo.getType()); 
			tbInfoPic.setPicLocalStore(toPathDir+ "附照_"+picIndex+".jpg");
			new CopyThread(pictureInfo.getUrl(),tbInfoPic.getPicLocalStore()).start();   
			infoPicDao.save(tbInfoPic); 
		}   
		this.session.setAttribute("handyPhotoStoreBean", handyPhotoStoreBean);
		this.session.setAttribute("lastOperationStage", HandyPhotoProcess.REMARK); 
		handyPhotoDao.saveOrUpdate(tbHandyPhoto);
		return sign;  
		 
	}
	
	
	private boolean textProcess_Wait(TextReqBean textReqBean){ 
		String content=CommonUtil.trim(textReqBean.getContent());  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  HandyPhotoProcess;  
		if(!sign) return false;
		sign=stage.equals(HandyPhotoProcess.REMARK)||stage.equals(HandyPhotoProcess.WAIT);
		if(!sign) return false;  
		HandyPhotoStoreBean handyPhotoStoreBean=this.getSessionHandyPhotoStoreBean();
	      
		Calendar cal = Calendar.getInstance(); 
        Date date=cal.getTime(); 
        TbInfoText tbInfoText=new TbInfoText();
		TbInfoTextId tbInfoTextId=new TbInfoTextId();
		tbInfoTextId.setProcessId(Constant.HANDYPHOTO);
		tbInfoTextId.setRecordNo(handyPhotoStoreBean.getHandyPhotoId());
		tbInfoTextId.setTextIndex(infoTextDao.getNextTextIndex(tbInfoTextId.getProcessId(), tbInfoTextId.getRecordNo()));
		tbInfoText.setId(tbInfoTextId);
		tbInfoText.setTextMessage(content);
		tbInfoText.setTextTime(new Timestamp(date.getTime()));
		infoTextDao.save(tbInfoText);  
        StringBuffer txtBuffer=new StringBuffer("");
		txtBuffer.append("您的信息已收到，\n"); 
		txtBuffer.append("如有需要可继续输入文字。"); 
		this.tipMessage(txtBuffer.toString());   
		this.session.setAttribute("handyPhotoStoreBean", handyPhotoStoreBean);
		this.session.setAttribute("lastOperationStage", HandyPhotoProcess.WAIT);  
		return sign;  
		 
	}
}