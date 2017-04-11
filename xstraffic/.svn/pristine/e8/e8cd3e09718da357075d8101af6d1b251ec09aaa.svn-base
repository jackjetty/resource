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
 
import cn.rising.model.SessionModel;

import com.traffic.pojo.TbTip;
import com.traffic.dao.AccidentDao;
import com.traffic.dao.InfoTextDao;
import com.traffic.dao.TipDao;
import com.traffic.dao.InfoPicDao;
import com.traffic.dao.ProcedureDao;
import com.traffic.pojo.TbAccident;
import com.traffic.pojo.TbInfoPic;
import com.traffic.pojo.TbInfoPicId; 
import com.traffic.pojo.TbInfoText;
import com.traffic.pojo.TbInfoTextId;
import com.traffic.util.CommonUtil;
import com.traffic.util.Constant;
import org.rising.wei.bean.req.ImageReqBean;
import org.rising.wei.bean.req.LocationReqBean;
import org.rising.wei.bean.req.TextReqBean;
import com.traffic.wei.thread.FileThread; 
@Service("accidentAuditProcessService")
public class  AccidentAuditProcessServiceImpl   extends ProcessServiceImpl{
	@Autowired
	private  AccidentDao accidentDao; 
	@Autowired
	private InfoPicDao infoPicDao;  
	
	@Autowired
	private ProcedureDao procedureDao; 
	
	@Autowired
	private TipDao tipDao; 
	@Autowired
	private InfoTextDao infoTextDao; 
	
	
	 
	
	public boolean locationProcess(LocationReqBean locationReqBean){
		super.locationProcess(locationReqBean);
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false;
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("LOCATION");
		if(!sign) return false;
		TbAccident tbAccident=accidentDao.findById(auditId);
		tbAccident.setAddress(locationReqBean.getLabel());
		tbAccident.setLocationX(locationReqBean.getLocation_X());
		tbAccident.setLocationY(locationReqBean.getLocation_Y());
		accidentDao.saveOrUpdate(tbAccident); 
		finish(tbAccident);
		return sign;
	}
	public boolean textProcess(TextReqBean textReqBean){
		super.textProcess(textReqBean);
		if(textProcess_Phone(textReqBean)) return true; 
		if(textProcess_Wait(textReqBean)) return true; 
		if(textProcess_PartyPhone(textReqBean)) return true; 
		return false;

	}
	
	
	
	private boolean textProcess_PartyPhone(TextReqBean textReqBean){
		String content=textReqBean.getContent().trim();  
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false;
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("PARTYPHONE");
		if(!sign) return false; 
		sign=CommonUtil.isMobilePhone(content);
		if(!sign){
			this.tipMessage("请输入正确的手机号码 ....../:8*");
			return true;
		} 
		TbAccident tbAccident=accidentDao.findById(auditId);
		tbAccident.setPartyPhoneNumber(content);
		accidentDao.saveOrUpdate(tbAccident);
		finish(  tbAccident);
		return sign; 
	}
	 
	private boolean textProcess_Phone(TextReqBean textReqBean){
		String content=textReqBean.getContent().trim();  
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false;
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("PHONE");
		if(!sign) return false; 
		sign=CommonUtil.isMobilePhone(content);
		if(!sign){
			this.tipMessage("请输入正确的手机号码 ....../:8*");
			return true;
		} 
		TbAccident tbAccident=accidentDao.findById(auditId);
		tbAccident.setReportPhoneNumber(content);
		accidentDao.saveOrUpdate(tbAccident);
		finish(  tbAccident);
		return sign; 
	}
	
	
	private boolean textProcess_Wait(TextReqBean textReqBean)  {  
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false; 
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("WAIT");
		if(!sign) return false;  
		TbAccident tbAccident=accidentDao.findById(auditId);
		 
		
		int textIndex=infoTextDao.getNextTextIndex(Constant.FORMALACCIDENT, this.auditId);
		String content=textReqBean.getContent().trim();   
		
		
		TbInfoText tbInfoText=new TbInfoText();
		TbInfoTextId tbInfoTextId=new TbInfoTextId();
		
		
		tbInfoTextId.setTextIndex(textIndex);
		tbInfoTextId.setProcessId(Constant.FORMALACCIDENT);
		tbInfoTextId.setRecordNo(auditId);
		tbInfoText.setId(tbInfoTextId);
        tbInfoText.setTextMessage(content); 
		Calendar cal = Calendar.getInstance(); 
        Date date=cal.getTime();   
        tbInfoText.setTextTime(new Timestamp(date.getTime()));    
		infoTextDao.saveOrUpdate(tbInfoText);
		finish(tbAccident); 
		return sign; 
	}
	
	public boolean imageProcess(ImageReqBean imageReqBean,String imageFilePath){ 
		 super.imageProcess(imageReqBean, imageFilePath);
		 if(imageProcess_Front(imageReqBean)) return true;
		 if(imageProcess_Rear(imageReqBean)) return true;
		 if(imageProcess_Middle(imageReqBean)) return true;
		 if(imageProcess_Paper(imageReqBean)) return true;
		 
		 if(imageProcess_Wait(imageReqBean)) return true; 
		 return false;
	}
	
	private boolean imageProcess_Front(ImageReqBean imageReqBean)  { 
		
	 
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false;
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("FRONT");
		if(!sign) return false;  
		TbAccident tbAccident=accidentDao.findById(auditId);
		
		String   toPathDir=this.dir_root+"accident"+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=this.auditId+File.separator; 
		toPathDir+="前方照.jpg";
		new FileThread(imageReqBean.getPicUrl(),toPathDir).start(); 
		TbInfoPic tbInfoPic=new TbInfoPic();
		TbInfoPicId tbInfoPicId=new TbInfoPicId();
		tbInfoPicId.setPicIndex(1);
		tbInfoPicId.setProcessId(Constant.FORMALACCIDENT);
		tbInfoPicId.setRecordNo(auditId);
		tbInfoPic.setId(tbInfoPicId);
		tbInfoPic.setPicType("前方") ;
		tbInfoPic.setPicInfo("前方照");
		tbInfoPic.setPicLocalStore(toPathDir);
		infoPicDao.saveOrUpdate(tbInfoPic);
		
		finish(tbAccident);
		return sign; 
	}
	private boolean imageProcess_Rear(ImageReqBean imageReqBean)  { 
		
		 
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false;
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("REAR");
		if(!sign) return false;  
		TbAccident tbAccident=accidentDao.findById(auditId);
		 
		
		String   toPathDir=this.dir_root+"accident"+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=this.auditId+File.separator; 
		toPathDir+="后方照.jpg";
		new FileThread(imageReqBean.getPicUrl(),toPathDir).start(); 
		TbInfoPic tbInfoPic=new TbInfoPic();
		TbInfoPicId tbInfoPicId=new TbInfoPicId();
		tbInfoPicId.setPicIndex(2);
		tbInfoPicId.setProcessId(Constant.FORMALACCIDENT);
		tbInfoPicId.setRecordNo(auditId);
		tbInfoPic.setId(tbInfoPicId);
		tbInfoPic.setPicType("后方") ;
		tbInfoPic.setPicInfo("后方照");
		tbInfoPic.setPicLocalStore(toPathDir);
		infoPicDao.saveOrUpdate(tbInfoPic);
		
		finish(  tbAccident);
		return sign; 
	}
	private boolean imageProcess_Middle(ImageReqBean imageReqBean)  {  
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false;
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("MIDDLE");
		if(!sign) return false;  
		TbAccident tbAccident=accidentDao.findById(auditId);
		 
		
		String   toPathDir=this.dir_root+"accident"+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=this.auditId+File.separator; 
		toPathDir+="细目照.jpg";
		new FileThread(imageReqBean.getPicUrl(),toPathDir).start(); 
		TbInfoPic tbInfoPic=new TbInfoPic();
		TbInfoPicId tbInfoPicId=new TbInfoPicId();
		tbInfoPicId.setPicIndex(3);
		tbInfoPicId.setProcessId(Constant.FORMALACCIDENT);
		tbInfoPicId.setRecordNo(auditId);
		tbInfoPic.setId(tbInfoPicId);
		tbInfoPic.setPicType("细目") ;
		tbInfoPic.setPicInfo("细目照");
		tbInfoPic.setPicLocalStore(toPathDir);
		infoPicDao.saveOrUpdate(tbInfoPic);
		
		finish(  tbAccident);
		return sign; 
	}
	
	private boolean imageProcess_Paper(ImageReqBean imageReqBean)  {  
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false;
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("PAPER");
		if(!sign) return false;  
		TbAccident tbAccident=accidentDao.findById(auditId);
		 
		
		String   toPathDir=this.dir_root+"accident"+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=this.auditId+File.separator; 
		toPathDir+="信息照.jpg";
		new FileThread(imageReqBean.getPicUrl(),toPathDir).start(); 
		TbInfoPic tbInfoPic=new TbInfoPic();
		TbInfoPicId tbInfoPicId=new TbInfoPicId();
		tbInfoPicId.setPicIndex(4);
		tbInfoPicId.setProcessId(Constant.FORMALACCIDENT);
		tbInfoPicId.setRecordNo(auditId);
		tbInfoPic.setId(tbInfoPicId);
		tbInfoPic.setPicType("信息") ;
		tbInfoPic.setPicInfo("信息照");
		tbInfoPic.setPicLocalStore(toPathDir);
		infoPicDao.saveOrUpdate(tbInfoPic);
		
		finish(tbAccident);
		return sign; 
	}
	private boolean imageProcess_Wait(ImageReqBean imageReqBean)  {  
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false; 
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("WAIT");
		if(!sign) return false;  
		TbAccident tbAccident=accidentDao.findById(auditId);
		 
		
		int picIndex=infoPicDao.getNextPicIndex(Constant.FORMALACCIDENT, this.auditId);
		String   toPathDir=this.dir_root+"accident"+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=this.auditId+File.separator; 
		toPathDir+="附图_"+picIndex+".jpg";
		new FileThread(imageReqBean.getPicUrl(),toPathDir).start(); 
		TbInfoPic tbInfoPic=new TbInfoPic();
		TbInfoPicId tbInfoPicId=new TbInfoPicId();
		tbInfoPicId.setPicIndex(picIndex);
		tbInfoPicId.setProcessId(Constant.FORMALACCIDENT);
		tbInfoPicId.setRecordNo(auditId);
		tbInfoPic.setId(tbInfoPicId);
		tbInfoPic.setPicType("附图") ;
		tbInfoPic.setPicInfo("附图照"+picIndex);
		tbInfoPic.setPicLocalStore(toPathDir);
		infoPicDao.saveOrUpdate(tbInfoPic);
		finish(  tbAccident);
		
		 
		return sign; 
	}
	
	private void finish(TbAccident tbAccident){
		int listSize=procedureList.size();
		auditIndex++;
		if(auditIndex==listSize){
			this.session.setAttribute("auditIndex",null); 
			session.setAttribute("AUDIT", null);
			session.setAttribute("AUDITPROCESSID", null);
			session.setAttribute("auditIndex", null); 
			session.setAttribute("auditId", null); 
			session.setAttribute("procedureList", null); 
			this.tipMessage("您的信息正在审核中,请耐心等候!!");
			tbAccident.setAccidentState("上报");
			accidentDao.saveOrUpdate(tbAccident); 
			TbTip tbTip=new TbTip();  
			tbTip.setProcessId(Constant.FORMALACCIDENT); 
			tbTip.setTipType("RESUBMIT");
			tbTip.setRecordNo(tbAccident.getAccidentId());
			tbTip.setHaveTip(false); 
			Calendar cal = Calendar.getInstance(); 
	        Date date=cal.getTime();   
	        tbTip.setLastTime(new Timestamp(date.getTime())); 
			tipDao.saveOrUpdate(tbTip); 
			return;
		} 
		this.session.setAttribute("auditIndex",auditIndex);  
		//CommonUtil.getProcedureGuid()
		this.tipMessage(procedureDao.getProcedureTip(this.AUDITPROCESSID, this.procedureList.get(auditIndex)));
	}
	
}