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
import org.rising.wei.bean.res.TextResBean;
import org.rising.wei.bean.res.object.ItemResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;  

import com.detachment.dao.DepartmentDao;
import com.detachment.dao.TestAccidentDao;
import com.detachment.dao.ProcessDao;
import com.detachment.dao.UserDao;
import com.detachment.pojo.TbTestAccident;
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.WeiUtil;
import com.detachment.wei.bean.store.AccidentStoreBean;
import com.detachment.wei.bean.store.info.LocationStoreInfo;
import com.detachment.wei.process.TestAccidentProcess;
import com.detachment.wei.process.TestAccidentProcess;
  
@Service("testAccidentProcessService")
public class TestAccidentProcessServiceImpl   extends ProcessServiceImpl{
	@Autowired
	private UserDao userDao;
	@Autowired
	private TestAccidentDao testAccidentDao;
	@Autowired
	private ProcessDao processDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	
	 
	
	@Value("${wei.detachment.scope.url}") 
	private String  detachementScope_Url; 
	
	@Value("${wei.picurl.accident.middle}") 
	private String  pic_accident_middleUrl; 
	
	@Value("${wei.picurl.accident.rear}") 
	private String  pic_accident_rearUrl; 
	
	@Value("${wei.picurl.accident.front}") 
	private String  pic_accident_frontUrl; 
	
	 
	
	public boolean imageProcess(ImageReqBean imageReqBean,String imageFilePath){ 
		 super.imageProcess(imageReqBean,  imageFilePath);
		 if(imageProcess_Front(imageReqBean)) return true;
		 if(imageProcess_Rear(imageReqBean)) return true;
		 if(imageProcess_Middle(imageReqBean)) return true;
		 if(imageProcess_Paper(imageReqBean)) return true;
		 return false;
	}
	
	private boolean imageProcess_Front(ImageReqBean imageReqBean){
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  TestAccidentProcess;  
		if(!sign) return false;
		sign=stage.equals(TestAccidentProcess.PHONE);
		if(!sign) return false;  
		ItemResObject itemResObject;
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>();
		itemResObject=new ItemResObject();
		itemResObject.setTitle("【第3-2步：现场拍照——后方照】"); 
		itemResObject.setDescription("已经收到第一张照片。请继续在事故车后方约5米处进行拍照上传，照片要求车号、道路标志、标线清晰。点击下方“+”图标，选择“图片”。");
		itemResObject.setPicUrl(String.format(pic_accident_rearUrl,this.SERVER_IP));
		itemResObject.setUrl("");
		articles.add(itemResObject); 
		this.outMsg=WeiUtil.getNewsMessage(imageReqBean, articles) ;  
		this.session.setAttribute("lastOperationStage", TestAccidentProcess.FRONT); 
		this.addPicture("前方","前方照",null);   
		return sign; 
	}
	
	
	private boolean imageProcess_Rear(ImageReqBean imageReqBean)  {  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  TestAccidentProcess;  
		if(!sign) return false;
		sign=stage.equals(TestAccidentProcess.FRONT);
		if(!sign) return false; 
	 
		ItemResObject itemResObject;
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>();
		itemResObject=new ItemResObject();
		itemResObject.setTitle("【第3-3步：现场拍照——细目照】");
		itemResObject.setDescription("已经收到第二张照片。请继续按图示要求拍照，照片要求能看清楚车辆碰撞部位，损坏情况。点击下方“+”图标，选择“图片”。");
		itemResObject.setPicUrl( String.format(pic_accident_middleUrl,this.SERVER_IP) );
		itemResObject.setUrl("");
		articles.add(itemResObject);  
		this.outMsg=WeiUtil.getNewsMessage(imageReqBean, articles) ;  
		this.session.setAttribute("lastOperationStage", TestAccidentProcess.REAR);  
		this.addPicture("后方","后方照",null);  
		return sign;
		
	}
	
	
	private boolean imageProcess_Middle(ImageReqBean imageReqBean)  {  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  TestAccidentProcess;  
		if(!sign) return false;
		sign=stage.equals(TestAccidentProcess.REAR);
		if(!sign) return false; 
		
		StringBuffer textBuffer=new StringBuffer("");
		textBuffer.append("已接收到第三张照片 请将双方驾驶证、行驶证、交强险保单（或交强险粘贴标志）一同拍照上传。");
		this.tipMessage(textBuffer.toString()); 
		this.session.setAttribute("lastOperationStage", TestAccidentProcess.MIDDLE);   
		 
		this.addPicture("中心","中心照",null); 
		return sign;
		
	} 
	
	private boolean imageProcess_Paper(ImageReqBean imageReqBean)  {  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  TestAccidentProcess;  
		if(!sign) return false;
		sign=stage.equals(TestAccidentProcess.MIDDLE);
		if(!sign) return false;  
		this.addPicture("信息","信息照",null);
		
		AccidentStoreBean accidentStoreBean=this.getSessionAccidentStoreBean(); 
		LocationStoreInfo locationInfo=accidentStoreBean.getLocationInfo();
		String departmentId="DP002";
		 
		TbTestAccident tbTestAccident=new TbTestAccident();
		tbTestAccident.setAccidentState("上报");
		tbTestAccident.setAddress(locationInfo.getAddress());
		tbTestAccident.setLocationX(locationInfo.getLocationX());
		tbTestAccident.setLocationY(locationInfo.getLocationY());
		tbTestAccident.setReporterType("公众");
		tbTestAccident.setReportOpenId(this.openId);
		tbTestAccident.setReportPhoneNumber(accidentStoreBean.getReportPhoneNumber()); 
		 
		Calendar cal = Calendar.getInstance(); 
        Date date=cal.getTime();   
        tbTestAccident.setReportTime(new Timestamp(date.getTime()));
        tbTestAccident.setDepartmentId(departmentId);
     
        testAccidentDao.saveOrUpdate(tbTestAccident);
        String testAccidentId=Integer.toString(tbTestAccident.getTestAccidentId());
        accidentStoreBean.setAccidentId(testAccidentId );
        StringBuffer textBuffer=new StringBuffer("");
		textBuffer.append("已接收到第四张照片,\n");
		textBuffer.append(this.DETACHMENT_NAME+"联系电话:\n");
		textBuffer.append(this.DETACHMENT_CONTACT+",\n");
		textBuffer.append("事故编号：");
		textBuffer.append(testAccidentId);
		textBuffer.append("\n测试体验完成!!/:oY。"); 
		this.tipMessage(textBuffer.toString()); 
		this.session.setAttribute("lastOperationStage", TestAccidentProcess.WAIT);  
		this.session.setAttribute("accidentStoreBean", null); 
		return sign; 
	} 
	public boolean locationProcess(LocationReqBean locationReqBean){
		super.locationProcess(locationReqBean);
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  TestAccidentProcess;  
		if(!sign) return false;
		sign=stage.equals(TestAccidentProcess.START);
		if(!sign) return false; 
		if(locationReqBean.getLabel().trim().equals("")){
			 this.tipMessage("您提交的地理位置信息不明确，请重新提交！");
			 return true;
		} 
		
		StringBuffer textBuffer=new StringBuffer("");
		textBuffer.append("您标注的地理位置是："+locationReqBean.getLabel()+"\n");
		textBuffer.append("【第2步：提供电话】请输入您的手机号，并确保通讯畅通，以便民警与您取得联系。");
		this.tipMessage(textBuffer.toString());  
		
		AccidentStoreBean accidentStoreBean=this.getSessionAccidentStoreBean();
		LocationStoreInfo locationInfo=accidentStoreBean.getLocationInfo();
		if(locationInfo==null)
			  locationInfo= new LocationStoreInfo(); 
		locationInfo.setAddress(locationReqBean.getLabel());
		locationInfo.setLocationX(locationReqBean.getLocation_X()); 
		locationInfo.setLocationY(locationReqBean.getLocation_Y());
		accidentStoreBean.setLocationInfo(locationInfo);
		this.session.setAttribute("lastOperationStage",TestAccidentProcess.LOCATION); 
		this.session.setAttribute("accidentStoreBean",accidentStoreBean);
		return sign; 
	}
	public boolean textProcess(TextReqBean textReqBean){
		super.textProcess(textReqBean);
		if(textProcess_Phone(textReqBean)) return true;  
		return false;
	}
	private boolean textProcess_Phone(TextReqBean textReqBean){
		String content=textReqBean.getContent().trim();  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  TestAccidentProcess;  
		if(!sign) return false;
		sign=stage.equals(TestAccidentProcess.LOCATION);
		if(!sign) return false;
		sign=CommonUtil.isMobilePhone(content);
		if(!sign){
			this.tipMessage("请输入正确的手机号码!!");
			return true;
		}
		ItemResObject itemResObject;
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>();
		itemResObject=new ItemResObject();
		itemResObject.setTitle("【第3-1步：现场拍照——前方照】");
		itemResObject.setDescription("站在道路中间拍摄，在事故车前方5米处进行拍照，照片要求车号、道路标志、标线清晰。点击下方“+”图标，选择“图片”。");
		itemResObject.setPicUrl( String.format(pic_accident_frontUrl,this.SERVER_IP) );
		itemResObject.setUrl("");
		articles.add(itemResObject);
		this.outMsg=WeiUtil.getNewsMessage(textReqBean, articles) ;   
		AccidentStoreBean accidentStoreBean=this.getSessionAccidentStoreBean();
		accidentStoreBean.setReportPhoneNumber(content);  
		this.session.setAttribute("lastOperationStage",TestAccidentProcess.PHONE);  
		this.session.setAttribute("accidentStoreBean", accidentStoreBean);
		return sign;
	}
	
	
	
}