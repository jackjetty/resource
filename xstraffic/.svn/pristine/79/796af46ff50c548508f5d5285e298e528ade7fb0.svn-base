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
import org.rising.wei.bean.BaseBean;
import org.rising.wei.bean.req.ClickEventReqBean;
import org.rising.wei.bean.req.ImageReqBean;
import org.rising.wei.bean.req.LocationReqBean;
import org.rising.wei.bean.req.TextReqBean;
import org.rising.wei.bean.res.NewsResBean;
import org.rising.wei.bean.res.TextResBean;
import org.rising.wei.bean.res.object.ItemResObject;
import com.traffic.wei.store.AccidentStoreBean;
import com.traffic.wei.store.CarMoveStoreBean;
import com.traffic.wei.store.UserStoreBean;
import com.traffic.wei.store.info.LocationStoreInfo;
 
import com.traffic.dao.AccidentDao;
import com.traffic.dao.CarMoveDao;
import com.traffic.dao.InfoPicDao;
import com.traffic.dao.OrationRecordDao;
import com.traffic.dao.UserDao; 
import com.traffic.dao.ProcessDao; 
import com.traffic.wei.service.ProcessService;
import com.traffic.pojo.TbAccident;
import com.traffic.pojo.TbCarMove;
import com.traffic.pojo.TbInfoPic;
import com.traffic.pojo.TbInfoPicId;
import com.traffic.pojo.TbUser;
import com.traffic.pojo.TbOrationRecord;  
import com.traffic.wei.process.CarMoveProcess; 
import com.traffic.wei.thread.FileThread;
import com.traffic.util.Constant;  
import com.traffic.util.CommonUtil;
@Service("carMoveProcessService")
public class CarMoveProcessServiceImpl   extends ProcessServiceImpl{
	@Autowired
	private UserDao userDao; 
	@Autowired
	private ProcessDao processDao;
	@Autowired
	private OrationRecordDao orationRecordDao;
	@Autowired
	private InfoPicDao infoPicDao; 
	@Autowired
	private CarMoveDao carMoveDao;
	
	@Value("${wei.carmove.limittime}") 
	private int   carmove_limittime; 
	
	@Value("${traffic.selfmovecar.contact}") 
	private String   selfmovecar_contact; 
 
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true;
		sign=eventKey.equalsIgnoreCase(Constant.CARMOVE)?sign:false;
		if(!sign) return false;  
		if(!processDao.isUsing(Constant.CARMOVE)){
			this.hopeMessage();
			return true;
		}
		StringBuffer textBuffer=new StringBuffer("");  
		textBuffer.append("欢迎使用自助移车服务， \n");
		textBuffer.append("【适用范围】\n");
		textBuffer.append("仅用于停放的车辆影响交通，车主不在现场，也没留联系方式的情况。\n"); 
		textBuffer.append("【适用时间】\n");
		textBuffer.append("每天的8时至21时,\n其余时间有紧急情况请拨110联系。\n\n");
		//String phoneNumber=this.userDao.getUserPhoneNumber(this.openId);
		String phoneNumber="";   
        if(phoneNumber.equals("")){
        	textBuffer.append("为确保能及时与您联系，\n");
        	textBuffer.append("请输入您的手机号码 ："); 
        	this.session.setAttribute("lastOperationStage",CarMoveProcess.START); 
        }
        else{
        	
        	CarMoveStoreBean carMoveStoreBean=this.getSessionCarMoveStoreBean();
        	carMoveStoreBean.setReportPhoneNumber(phoneNumber);
        	textBuffer.append("请输入对方车牌号码,格式如“浙B35678”"); 
    		this.session.setAttribute("carMoveStoreBean", carMoveStoreBean); 
    		this.session.setAttribute("lastOperationStage",CarMoveProcess.PHONENUMBER); 
        	 
        } 
		this.tipMessage(textBuffer.toString()); 
		return true;  
		 
	}
	public boolean imageProcess(ImageReqBean imageReqBean,String imageFilePath){ 
		 super.imageProcess(imageReqBean,imageFilePath);
		 
		 if(imageProcess_CarMovePhote(imageReqBean)) return true;
		 if(imageProcess_CarMoveWait(imageReqBean)) return true;
		 return false;
	}
	private boolean imageProcess_CarMovePhote(ImageReqBean imageReqBean){  
		 boolean sign=true;
		 sign=stage!=null?sign:false;
		 if(!sign) return false; 
		 sign=stage instanceof  CarMoveProcess;  
		 if(!sign) return false;
		 sign=stage.equals(CarMoveProcess.LOCATION);
		 if(!sign) return false;   
		 CarMoveStoreBean carMoveStoreBean=this.getSessionCarMoveStoreBean() ; 
		 LocationStoreInfo locationInfo=carMoveStoreBean.getLocationInfo();
		 TbCarMove tbCarMove=new TbCarMove(); 
		 String carMoveId=carMoveDao.getNextCarMoveId("CM");
		 carMoveStoreBean.setCarMoveId(carMoveId);
		 tbCarMove.setCarMoveId(carMoveId);
		 tbCarMove.setCarMoveState("上报");
		 tbCarMove.setCarNumber(carMoveStoreBean.getCarNumber());
		 tbCarMove.setLocationX(locationInfo.getLocationX()) ;
		 tbCarMove.setLocationY(locationInfo.getLocationY());
		 tbCarMove.setAddress(locationInfo.getAddress());
		 tbCarMove.setReportOpenId(this.openId);
		 tbCarMove.setReportPhoneNumber(carMoveStoreBean.getReportPhoneNumber());
		  
		 Calendar cal = Calendar.getInstance(); 
        Date date=cal.getTime();   
        tbCarMove.setReportTime(new Timestamp(date.getTime()));  
		 StringBuffer txtBuffer=new StringBuffer("");
		 txtBuffer.append("您的信息已收到，\n");
		 txtBuffer.append("受理编号是:\n");
		 txtBuffer.append(carMoveId+"\n");
		 txtBuffer.append("我们将尽快通知车主，\n");
		 txtBuffer.append("如超过10分钟车主未到现场处理,"); 
		 txtBuffer.append("可拨"+selfmovecar_contact+"人工联系。\n");
		 txtBuffer.append("照片如需修改或补充，请继续上传照片");
		 this.tipMessage(txtBuffer.toString());  
		 TbInfoPicId tbInfoPicId; 
		 tbInfoPicId= new TbInfoPicId();  
		 tbInfoPicId.setProcessId(Constant.CARMOVE);
		 tbInfoPicId.setRecordNo(carMoveId); 
		 int picIndex=infoPicDao.getNextPicIndex(tbInfoPicId.getProcessId(), tbInfoPicId.getRecordNo()) ;
		 tbInfoPicId.setPicIndex(picIndex); 
		 String filePath= this.dir_root+Constant.CARMOVE+File.separator; 
		 filePath+=CommonUtil.getDateForm()+File.separator+carMoveStoreBean.getCarMoveId(); 
		 filePath+=File.separator+"信息.jpg";
		 new FileThread(imageReqBean.getPicUrl(),filePath).start(); 
		 TbInfoPic tbInfoPic;
		 tbInfoPic=new TbInfoPic();
		 tbInfoPic.setId(tbInfoPicId); 
		 tbInfoPic.setPicInfo("信息照");
		 tbInfoPic.setPicType("信息");
		 tbInfoPic.setPicLocalStore(filePath); 
		 infoPicDao.save(tbInfoPic);  
		 this.session.setAttribute("carMoveStoreBean", carMoveStoreBean);
		 this.session.setAttribute("lastOperationStage", CarMoveProcess.PHOTE); 
		 carMoveDao.save(tbCarMove); 
		 return sign; 
	}
	private boolean imageProcess_CarMoveWait(ImageReqBean imageReqBean){  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  CarMoveProcess;  
		if(!sign) return false;
		sign=stage.equals(CarMoveProcess.PHOTE)||stage.equals(CarMoveProcess.WAIT);
		if(!sign) return false;   
		CarMoveStoreBean carMoveStoreBean=this.getSessionCarMoveStoreBean() ;  
		String carMoveId=carMoveStoreBean.getCarMoveId();
		if(carMoveId==null){
			 this.tipMessage("受理编号未确定，请重新上报！");
			 return true;
		} 
		TbInfoPicId tbInfoPicId; 
		tbInfoPicId= new TbInfoPicId();  
		tbInfoPicId.setProcessId(Constant.CARMOVE);
		tbInfoPicId.setRecordNo(carMoveId);   
		int picIndex=infoPicDao.getNextPicIndex(tbInfoPicId.getProcessId(), tbInfoPicId.getRecordNo()) ;
		tbInfoPicId.setPicIndex(picIndex);  
		String filePath=this.dir_root+Constant.CARMOVE+File.separator; 
		filePath+=CommonUtil.getDateForm()+File.separator+carMoveId; 
		filePath+=File.separator+"附图_"+picIndex+".jpg";
		new FileThread(imageReqBean.getPicUrl(),filePath).start();
		this.tipMessage("已收到"+picIndex+"张照片");  
		TbInfoPic tbInfoPic;
		tbInfoPic=new TbInfoPic();
		tbInfoPic.setId(tbInfoPicId);  
		tbInfoPic.setPicInfo("附图照"+picIndex);
		tbInfoPic.setPicType("附图"+picIndex);
		tbInfoPic.setPicLocalStore(filePath); 
		infoPicDao.save(tbInfoPic);     
		this.session.setAttribute("carMoveStoreBean", carMoveStoreBean);
		this.session.setAttribute("lastOperationStage", CarMoveProcess.WAIT); 
		return sign;  
		
	} 
	public boolean locationProcess(LocationReqBean locationReqBean){
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  CarMoveProcess;  
		if(!sign) return false;
		 
		sign=stage.equals(CarMoveProcess.CARNUMBER);
		if(!sign) return false; 
		if(locationReqBean.getLabel().trim().equals("")){
			 this.tipMessage("您提交的地理位置信息不明确，请重新提交！");
			 return true;
		}
		CarMoveStoreBean carMoveStoreBean=this.getSessionCarMoveStoreBean(); 
		LocationStoreInfo locationInfo=new  LocationStoreInfo();
		locationInfo.setAddress(locationReqBean.getLabel());
		locationInfo.setLocationX(locationReqBean.getLocation_X()); 
		locationInfo.setLocationY(locationReqBean.getLocation_Y()); 
		carMoveStoreBean.setLocationInfo(locationInfo); 
		StringBuffer txtBuffer=new StringBuffer("");
	    txtBuffer.append("您标注的地理位置是："+locationReqBean.getLabel()+"\n\n");  
	     
	    txtBuffer.append("请上传对方车辆照片,\n"); 
	    txtBuffer.append("（近景，照片需含整车且能看清车牌）"); 
	    this.tipMessage(txtBuffer.toString()); 
		this.session.setAttribute("carMoveStoreBean", carMoveStoreBean); 
		this.session.setAttribute("lastOperationStage",CarMoveProcess.LOCATION);  
		return sign; 
	}
	public boolean textProcess(TextReqBean textReqBean){
		super.textProcess(textReqBean);
		if(textProcess_Phone(textReqBean)) return true;   
		if(textProcess_TextLocation(textReqBean)) return true; 
		if(textProcess_CarNum(textReqBean)) return true;  
		return false;
	}
	private boolean textProcess_CarNum(TextReqBean textReqBean){
		String content=CommonUtil.trim(textReqBean.getContent());  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  CarMoveProcess;  
		if(!sign) return false;
		sign=stage.equals(CarMoveProcess.PHONENUMBER);
		if(!sign) return false;  
		sign=CommonUtil.isCarNumber(content); 
		if(!sign){
			this.tipMessage("请输入正确的车牌号码..../:8*");
			return true;
		} 
		CarMoveStoreBean carMoveStoreBean=this.getSessionCarMoveStoreBean();
		carMoveStoreBean.setCarNumber(content);
		if(carMoveDao.isLimitReport(carmove_limittime,carMoveStoreBean.getCarNumber())){
			this.tipMessage("车牌号为:"+carMoveStoreBean.getCarNumber()+"\n已经受理请耐心等候!");
			return true;
		}   
		StringBuffer txtBuffer=new StringBuffer(""); 
		txtBuffer.append("请点击“+”按钮然后点击“位置”，\n"); 
		txtBuffer.append("待定位成功后点击“发送”,上传现场位置,\n" );
		txtBuffer.append("或通过文字描述移车现场位置，如“家有超市门口”。"); 
		this.tipMessage(txtBuffer.toString()); 
		this.session.setAttribute("carMoveStoreBean", carMoveStoreBean); 
		this.session.setAttribute("lastOperationStage",CarMoveProcess.CARNUMBER);  
		return sign; 
	}
	private boolean  textProcess_Phone(TextReqBean textReqBean){
		String content=CommonUtil.trim(textReqBean.getContent());   
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  CarMoveProcess;  
		if(!sign) return false;
		sign=stage.equals(CarMoveProcess.START);
		if(!sign) return false; 
		sign=CommonUtil.isMobilePhone(content); 
		if(!sign){
			this.tipMessage("请输入正确的手机号码 ....../:8*");
			return true;
		}
		//saveUserInfo(content); 
		
		CarMoveStoreBean carMoveStoreBean=this.getSessionCarMoveStoreBean();
		carMoveStoreBean.setReportPhoneNumber(content);
		StringBuffer txtBuffer=new StringBuffer("");  
		txtBuffer.append("请输入对方车牌号码,格式如“浙B35678”"); 
		
		
		this.tipMessage(txtBuffer.toString()); 
		this.session.setAttribute("carMoveStoreBean", carMoveStoreBean); 
		this.session.setAttribute("lastOperationStage",CarMoveProcess.PHONENUMBER);
		return sign; 
	}
	 
	
	private boolean textProcess_TextLocation(TextReqBean textReqBean){ 
		String content=CommonUtil.trim(textReqBean.getContent());  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  CarMoveProcess;  
		if(!sign) return false;
		sign=stage.equals(CarMoveProcess.CARNUMBER);
		if(!sign) return false;  
		CarMoveStoreBean carMoveStoreBean=this.getSessionCarMoveStoreBean(); 
		LocationStoreInfo locationInfo=new  LocationStoreInfo();
		locationInfo.setAddress(content);  
		carMoveStoreBean.setLocationInfo(locationInfo); 
		StringBuffer txtBuffer=new StringBuffer(""); 
	    txtBuffer.append("请上传对方车辆照片,\n"); 
	    txtBuffer.append("（近景，照片需含整车且能看清车牌）"); 
	    this.tipMessage(txtBuffer.toString()); 
		this.session.setAttribute("carMoveStoreBean", carMoveStoreBean); 
		this.session.setAttribute("lastOperationStage",CarMoveProcess.LOCATION);  
		return sign; 
	}
}