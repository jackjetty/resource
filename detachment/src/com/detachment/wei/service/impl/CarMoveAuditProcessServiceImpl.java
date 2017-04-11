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
import org.rising.wei.bean.ErrCodeBean;
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
  
 
import com.detachment.dao.CarMoveDao;
import com.detachment.dao.DepartmentDao;
import com.detachment.dao.FormalAccidentDao;
import com.detachment.dao.InfoPicDao;
import com.detachment.dao.ProcessDao;
import com.detachment.dao.TipDao;
import com.detachment.dao.UserDao;
import com.detachment.dao.WeiUserDao;
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
import com.detachment.wei.bean.store.CarMoveStoreBean;
import com.detachment.wei.bean.store.info.LocationStoreInfo;
import com.detachment.wei.bean.store.info.PictureStoreInfo;
import com.detachment.wei.process.CarMoveProcess;
import com.detachment.wei.process.FormalAccidentProcess;
import com.detachment.wei.service.ProcessService;
import com.detachment.wei.thread.CopyThread;
import com.detachment.wei.thread.FileThread;
import com.detachment.wei.wsdd.ClientWSDD;
import com.detachment.dao.ProcedureDao;
import com.detachment.pojo.TbCarMove;

@Service("carMoveAuditProcessService")
public class  CarMoveAuditProcessServiceImpl   extends ProcessServiceImpl{
	@Autowired
	private UserDao userDao; 
	@Autowired
	private ProcessDao processDao;
    
	@Autowired
	private WeiUserDao weiUserDao;
	@Autowired
	private InfoPicDao infoPicDao; 
	@Autowired
	private CarMoveDao carMoveDao;
	@Autowired
	private ProcedureDao procedureDao;
	
	
	public boolean imageProcess(ImageReqBean imageReqBean,String imageFilePath){ 
		 super.imageProcess(imageReqBean,imageFilePath);
		 
		 if(imageProcess_CarMovePhote(imageReqBean)) return true;
		 if(imageProcess_CarMoveWait(imageReqBean)) return true;
		 return false;
	}
	private boolean imageProcess_CarMovePhote(ImageReqBean imageReqBean){  
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false;
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("PHOTE");
		if(!sign) return false; 
		TbCarMove tbCarMove=carMoveDao.findById(auditId);
		String filePath= this.ROOT_DIR+Constant.CARMOVE+File.separator; 
		filePath+=CommonUtil.getDateForm()+File.separator+auditId; 
		filePath+=File.separator+"信息.jpg";
		new FileThread(imageReqBean.getPicUrl(),filePath).start(); 
		TbInfoPicId tbInfoPicId; 
		tbInfoPicId= new TbInfoPicId();  
		tbInfoPicId.setProcessId(Constant.CARMOVE);
		tbInfoPicId.setRecordNo(auditId); 
		tbInfoPicId.setPicIndex(1);   
		TbInfoPic tbInfoPic=infoPicDao.findById(tbInfoPicId);
		if(tbInfoPic==null){
			tbInfoPic=new TbInfoPic();
			tbInfoPic.setId(tbInfoPicId); 
		}
		
		tbInfoPic.setPicInfo("信息照");
		tbInfoPic.setPicType("信息");
		tbInfoPic.setPicLocalStore(filePath); 
		infoPicDao.saveOrUpdate(tbInfoPic);  
		finish(tbCarMove); 
		return sign;  
	}
	private boolean imageProcess_CarMoveWait(ImageReqBean imageReqBean){  
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false;
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("WAIT");
		if(!sign) return false; 
		TbCarMove tbCarMove=carMoveDao.findById(auditId);
		 
		  
		int picIndex=infoPicDao.getNextPicIndex(Constant.CARMOVE, auditId) ;
		 
		String filePath=this.ROOT_DIR+Constant.CARMOVE+File.separator; 
		filePath+=CommonUtil.getDateForm()+File.separator+auditId; 
		filePath+=File.separator+"附图_"+picIndex+".jpg";
		new FileThread(imageReqBean.getPicUrl(),filePath).start();
		 
		TbInfoPicId tbInfoPicId; 
		tbInfoPicId= new TbInfoPicId();  
		tbInfoPicId.setProcessId(Constant.CARMOVE);
		tbInfoPicId.setRecordNo(auditId); 
		tbInfoPicId.setPicIndex(picIndex); 
		TbInfoPic tbInfoPic=infoPicDao.findById(tbInfoPicId);
		if(tbInfoPic==null){
			tbInfoPic=new TbInfoPic();
			tbInfoPic.setId(tbInfoPicId);
		}
		  
		tbInfoPic.setPicInfo("附图照"+picIndex);
		tbInfoPic.setPicType("附图"+picIndex);
		tbInfoPic.setPicLocalStore(filePath); 
		infoPicDao.saveOrUpdate(tbInfoPic);  
		finish(  tbCarMove); 
		return sign; 
		
	} 
	
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
		TbCarMove tbCarMove=carMoveDao.findById(auditId);
		tbCarMove.setAddress(locationReqBean.getLabel());
		tbCarMove.setLocationX(locationReqBean.getLocation_X());
		tbCarMove.setLocationY(locationReqBean.getLocation_Y());
		carMoveDao.saveOrUpdate(tbCarMove) ;
		finish(tbCarMove);
		return sign;
	}
	public boolean textProcess(TextReqBean textReqBean){
		super.textProcess(textReqBean);
		if(textProcess_Phone(textReqBean)) return true;   
		if(textProcess_CarNum(textReqBean)) return true; 
		if(textProcess_CarType(textReqBean))return true; 
		return false;
	}
	
    private boolean textProcess_Phone(TextReqBean textReqBean){
		
		String content=textReqBean.getContent().trim();  
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false;
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("PHONENUMBER");
		if(!sign) return false; 
		sign=CommonUtil.isMobilePhone(content);
		if(!sign){
			this.tipMessage("请输入正确的手机号码 ....../:8*");
			return true;
		} 
		TbCarMove tbCarMove=carMoveDao.findById(auditId);
		tbCarMove.setReportPhoneNumber(content);
		carMoveDao.saveOrUpdate(tbCarMove) ; 
		finish(  tbCarMove);
		return sign; 
	}
    private boolean textProcess_CarNum(TextReqBean textReqBean){
		
		String content=textReqBean.getContent().trim();  
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false;
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("CARNUMBER");
		if(!sign) return false; 
		sign=CommonUtil.isCarNumber(content); 
		if(!sign){
			this.tipMessage("请输入正确的车牌号码..../:8*");
			return true;
		} 
		TbCarMove tbCarMove=carMoveDao.findById(auditId);
		tbCarMove.setCarNumber(content);
		carMoveDao.saveOrUpdate(tbCarMove) ; 
		finish(  tbCarMove);
		return sign; 
	}
    
    private boolean textProcess_CarType(TextReqBean textReqBean){ 
    	String content=textReqBean.getContent().trim();  
		boolean sign=true;
		sign=this.procedureList==null?false:sign; 
		if(!sign) return false; 
		sign= this.auditIndex==-1?false:sign;
		if(!sign) return false;
		String procedureId=this.procedureList.get(auditIndex);
		sign=procedureId.equals("CARTYPE");
		if(!sign) return false; 
		
		TbCarMove tbCarMove=carMoveDao.findById(auditId);
		if(content.equalsIgnoreCase("1")){
			tbCarMove.setCarType("02");
			carMoveDao.saveOrUpdate(tbCarMove) ;
			finish(  tbCarMove);
			return sign; 
		}
		if(content.equalsIgnoreCase("2")){
			tbCarMove.setCarType("01"); 
			carMoveDao.saveOrUpdate(tbCarMove) ; 
			finish(  tbCarMove);
			return sign; 
		}	     
		this.tipMessage("请输入正确车牌类型!!");
		return sign; 
	} 
    private void finish(TbCarMove tbCarMove){
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
			tbCarMove.setCarMoveState("上报");
			carMoveDao.saveOrUpdate(tbCarMove);
			return;
		} 
		this.session.setAttribute("auditIndex",auditIndex);  
		this.tipMessage(procedureDao.getProcedureTip(this.AUDITPROCESSID, this.procedureList.get(auditIndex)));
	} 
    
	
}