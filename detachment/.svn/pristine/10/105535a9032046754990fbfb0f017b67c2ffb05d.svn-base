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
import org.rising.wei.bean.res.NewsResBean;
import org.rising.wei.bean.res.TextResBean;
import org.rising.wei.bean.res.object.ItemResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;  
  
 
import com.detachment.dao.DepartmentDao;
import com.detachment.dao.FormalAccidentDao;
import com.detachment.dao.InfoPicDao;
import com.detachment.dao.ProcessDao;
import com.detachment.dao.TipDao;
import com.detachment.dao.UserDao;
import com.detachment.pojo.TbDepartment;
import com.detachment.pojo.TbFormalAccident;
import com.detachment.pojo.TbInfoPic;
import com.detachment.pojo.TbInfoPicId;
import com.detachment.pojo.TbTip; 
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.WeiUtil;
import com.detachment.web.service.PlaceService;
import com.detachment.wei.bean.store.AccidentStoreBean;
import com.detachment.wei.bean.store.info.LocationStoreInfo;
import com.detachment.wei.bean.store.info.PictureStoreInfo;
import com.detachment.wei.process.FormalAccidentProcess;
import com.detachment.wei.service.ProcessService;
import com.detachment.wei.thread.CopyThread;
import com.detachment.wei.thread.FileThread;
import com.detachment.dao.ProcedureDao;

@Service("accidentAuditProcessService")
public class  AccidentAuditProcessServiceImpl   extends ProcessServiceImpl{
	@Autowired
	private FormalAccidentDao formalAccidentDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private InfoPicDao infoPicDao; 
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private TipDao tipDao;
	
	@Autowired
	private ProcedureDao procedureDao;  
	
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
		TbFormalAccident tbFormalAccident=formalAccidentDao.findById(auditId);
		tbFormalAccident.setAddress(locationReqBean.getLabel());
		tbFormalAccident.setLocationX(locationReqBean.getLocation_X());
		tbFormalAccident.setLocationY(locationReqBean.getLocation_Y());
		//String departmentId=placeService.checkTheAccidentPlace(Double.parseDouble(locationReqBean.getLocation_Y()), Double.parseDouble(locationReqBean.getLocation_X())); 
		formalAccidentDao.saveOrUpdate(tbFormalAccident);
		finish(tbFormalAccident);
		return sign;
	}
	public boolean textProcess(TextReqBean textReqBean){
		super.textProcess(textReqBean);
		if(textProcess_Phone(textReqBean)) return true;
		if(textProcess_EmergencyCall(textReqBean)) return true;
		return false;
		
	}
	private boolean textProcess_EmergencyCall(TextReqBean textReqBean){
		String content=textReqBean.getContent().trim(); 
		boolean sign=true;
		sign=content.equalsIgnoreCase("D");
		if(!sign) return false; 
		this.session.setAttribute("auditIndex",null); 
		session.setAttribute("AUDIT", null);
		session.setAttribute("auditIndex", null); 
		session.setAttribute("formalAccidentId", null); 
		session.setAttribute("procedureList", null); 
		TbFormalAccident tbFormalAccident=formalAccidentDao.findById(auditId);
		tbFormalAccident.setEmergencyCall(true); 
		formalAccidentDao.saveOrUpdate(tbFormalAccident);
		TbDepartment tbDePartment=departmentDao.findById(tbFormalAccident.getDepartmentId());
		StringBuffer textBuffer=new StringBuffer("");
		textBuffer.append(tbDePartment.getDepartmentName()+"将迅速与您取得联系并派人员赶赴现场处理，\n");
		textBuffer.append(tbDePartment.getDepartmentName()+"地址:\n");
		textBuffer.append(tbDePartment.getDepartmentAddress()); 
		textBuffer.append("\n电话:"+tbDePartment.getDepartmentPhone()); 
		this.tipMessage(textBuffer.toString());
		return true;
		
		
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
		TbFormalAccident tbFormalAccident=formalAccidentDao.findById(auditId);
		tbFormalAccident.setReportPhoneNumber(content);
		formalAccidentDao.saveOrUpdate(tbFormalAccident);
		finish(  tbFormalAccident);
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
		TbFormalAccident tbFormalAccident=formalAccidentDao.findById(auditId);
		
		String   toPathDir=this.ROOT_DIR+"accident"+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=this.auditId+File.separator; 
		toPathDir+="前方照.jpg";
		new FileThread(imageReqBean.getPicUrl(),toPathDir).start(); 
		
		TbInfoPicId tbInfoPicId=new TbInfoPicId();
		tbInfoPicId.setPicIndex(1);
		tbInfoPicId.setProcessId(Constant.ACCIDENT);
		tbInfoPicId.setRecordNo(auditId);
		
		TbInfoPic tbInfoPic=infoPicDao.findById(tbInfoPicId);
		if(tbInfoPic==null){
			tbInfoPic=new TbInfoPic();
			tbInfoPic.setId(tbInfoPicId);
		} 
		
		
		tbInfoPic.setPicType("前方") ;
		tbInfoPic.setPicInfo("前方照");
		tbInfoPic.setPicLocalStore(toPathDir);
		infoPicDao.saveOrUpdate(tbInfoPic);
		
		finish(  tbFormalAccident);
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
		TbFormalAccident tbFormalAccident=formalAccidentDao.findById(auditId);
		 
		
		String   toPathDir=this.ROOT_DIR+"accident"+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=this.auditId+File.separator; 
		toPathDir+="后方照.jpg";
		new FileThread(imageReqBean.getPicUrl(),toPathDir).start(); 
		
		TbInfoPicId tbInfoPicId=new TbInfoPicId();
		tbInfoPicId.setPicIndex(2);
		tbInfoPicId.setProcessId(Constant.ACCIDENT);
		tbInfoPicId.setRecordNo(auditId);
		TbInfoPic tbInfoPic=infoPicDao.findById(tbInfoPicId);
		if(tbInfoPic==null){
			tbInfoPic=new TbInfoPic();
			tbInfoPic.setId(tbInfoPicId);
		}
				
		
		tbInfoPic.setPicType("后方") ;
		tbInfoPic.setPicInfo("后方照");
		tbInfoPic.setPicLocalStore(toPathDir);
		infoPicDao.saveOrUpdate(tbInfoPic);
		
		finish(  tbFormalAccident);
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
		TbFormalAccident tbFormalAccident=formalAccidentDao.findById(auditId);
		 
		
		String   toPathDir=this.ROOT_DIR+"accident"+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=this.auditId+File.separator; 
		toPathDir+="中心照.jpg";
		new FileThread(imageReqBean.getPicUrl(),toPathDir).start(); 
		 
		TbInfoPicId tbInfoPicId=new TbInfoPicId();
		tbInfoPicId.setPicIndex(3);
		tbInfoPicId.setProcessId(Constant.ACCIDENT);
		tbInfoPicId.setRecordNo(auditId);
		TbInfoPic tbInfoPic=infoPicDao.findById(tbInfoPicId);
		if(tbInfoPic==null){
			tbInfoPic=new TbInfoPic();
			tbInfoPic.setId(tbInfoPicId);
		} 
		tbInfoPic.setPicType("中心") ;
		tbInfoPic.setPicInfo("中心照");
		tbInfoPic.setPicLocalStore(toPathDir);
		infoPicDao.saveOrUpdate(tbInfoPic);
		
		finish(  tbFormalAccident);
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
		TbFormalAccident tbFormalAccident=formalAccidentDao.findById(auditId);
		 
		
		String   toPathDir=this.ROOT_DIR+"accident"+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=this.auditId+File.separator; 
		toPathDir+="信息照.jpg";
		new FileThread(imageReqBean.getPicUrl(),toPathDir).start(); 
		 
		TbInfoPicId tbInfoPicId=new TbInfoPicId();
		tbInfoPicId.setPicIndex(4);
		tbInfoPicId.setProcessId(Constant.ACCIDENT);
		tbInfoPicId.setRecordNo(auditId);
		TbInfoPic tbInfoPic=infoPicDao.findById(tbInfoPicId);
		if(tbInfoPic==null){
			tbInfoPic=new TbInfoPic();
			tbInfoPic.setId(tbInfoPicId);
		}
		tbInfoPic.setPicType("信息") ;
		tbInfoPic.setPicInfo("信息照");
		tbInfoPic.setPicLocalStore(toPathDir);
		infoPicDao.saveOrUpdate(tbInfoPic);
		
		finish(tbFormalAccident);
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
		TbFormalAccident tbFormalAccident=formalAccidentDao.findById(auditId);
		 
		
		int picIndex=infoPicDao.getNextPicIndex(Constant.ACCIDENT, this.auditId);
		String   toPathDir=this.ROOT_DIR+"accident"+File.separator;
		toPathDir+=CommonUtil.getDateForm()+File.separator;
		toPathDir+=this.auditId+File.separator; 
		toPathDir+="附图_"+picIndex+".jpg";
		new FileThread(imageReqBean.getPicUrl(),toPathDir).start(); 
		 
		TbInfoPicId tbInfoPicId=new TbInfoPicId();
		tbInfoPicId.setPicIndex(picIndex);
		tbInfoPicId.setProcessId(Constant.ACCIDENT);
		tbInfoPicId.setRecordNo(auditId);
		TbInfoPic tbInfoPic=infoPicDao.findById(tbInfoPicId);
		if(tbInfoPic==null){
			tbInfoPic=new TbInfoPic();
			tbInfoPic.setId(tbInfoPicId);
		}
		tbInfoPic.setPicType("附图") ;
		tbInfoPic.setPicInfo("附图照"+picIndex);
		tbInfoPic.setPicLocalStore(toPathDir);
		infoPicDao.saveOrUpdate(tbInfoPic);
		finish(  tbFormalAccident);
		
		 
		return sign; 
	}
	
	private void finish(TbFormalAccident tbFormalAccident){
		int listSize=procedureList.size();
		auditIndex++;
		if(auditIndex==listSize){
			this.session.setAttribute("auditIndex",null); 
			session.setAttribute("AUDIT", null);
			session.setAttribute("auditIndex", null); 
			session.setAttribute("formalAccidentId", null); 
			session.setAttribute("procedureList", null); 
			this.tipMessage("您的信息正在审核中,请耐心等候!!");
			
			
			
			tbFormalAccident.setAccidentState("上报");
			
			TbTip tbTip=new TbTip();  
			tbTip.setProcessId(Constant.ACCIDENT);
			tbTip.setDepartmentId(tbFormalAccident.getDepartmentId());
			tbTip.setTipType("RESUBMIT");
			tbTip.setRecordNo(tbFormalAccident.getFormlAccidentId());
			tbTip.setHaveTip(false); 
			Calendar cal = Calendar.getInstance(); 
	        Date date=cal.getTime();   
	        tbTip.setLastTime(new Timestamp(date.getTime())); 
			tipDao.saveOrUpdate(tbTip); 
			return;
		} 
		this.session.setAttribute("auditIndex",auditIndex); 
		this.tipMessage(procedureDao.getProcedureTip(this.AUDITPROCESSID, this.procedureList.get(auditIndex)));
	}
	
}