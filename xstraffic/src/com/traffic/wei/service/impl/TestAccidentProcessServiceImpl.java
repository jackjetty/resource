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

 
import com.traffic.dao.OrationRecordDao;
import com.traffic.dao.ProcessDao;
import com.traffic.dao.TestAccidentDao;
import com.traffic.dao.UserDao; 
import com.traffic.pojo.TbTestAccident;
import com.traffic.util.CommonUtil;
import com.traffic.util.Constant;
import org.rising.wei.bean.req.ClickEventReqBean;
import org.rising.wei.bean.req.ImageReqBean;
import org.rising.wei.bean.req.LocationReqBean;
import org.rising.wei.bean.req.TextReqBean;
import org.rising.wei.bean.res.NewsResBean;
import org.rising.wei.bean.res.TextResBean;
import org.rising.wei.bean.res.object.ItemResObject;
import com.traffic.wei.process.TestAccidentProcess;
import com.traffic.wei.service.ProcessService;
import com.traffic.wei.store.AccidentStoreBean;
import com.traffic.wei.store.info.LocationStoreInfo; 
@Service("testAccidentProcessService")
public class TestAccidentProcessServiceImpl   extends ProcessServiceImpl{
	 
	@Autowired
	private TestAccidentDao testAccidentDao;
	@Autowired
	private ProcessDao processDao;
	@Autowired
	private OrationRecordDao orationRecordDao;
	
	@Value("${wei.traffic.scope.url}") 
	private String  trafficScope_Url; 
	
	@Value("${wei.picurl.accident.center}") 
	private String  pic_accident_centerUrl; 
	
	@Value("${wei.picurl.accident.details}") 
	private String  pic_accident_detailsUrl; 
	
	@Value("${wei.picurl.accident.overview}") 
	private String  pic_accident_overviewUrl; 
	
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true;
		sign=eventKey.equalsIgnoreCase(Constant.TESTACCIDENT)?sign:false;
		if(!sign) return false; 
		if(!processDao.isUsing(Constant.TESTACCIDENT)){
			this.hopeMessage();
			return true;
		}
		StringBuffer textBuffer=new StringBuffer(""); 
		textBuffer.append("您已进入轻微事故学习平台: \n");
		textBuffer.append("【适用范围】\n");
		textBuffer.append("<a href='"+String.format(trafficScope_Url,this.SERVER_IP)+"'>点击查看详细规定</a>\n"); 
		textBuffer.append("微信平台仅适用于无人员伤亡，车辆可移动的轻微物损事故。适用于所有投保车辆。\n");  
		textBuffer.append("【适用时间】\n");
		textBuffer.append("每天的8时至17时30分\n");
		textBuffer.append("【联系电话】\n");
		textBuffer.append(this.TRAFFIC_CONTACT+"\n"); 
		textBuffer.append("特别警示：伪造现场，虚假报案，将依法追究法律责任。\n");  
		 
		textBuffer.append("处理开始：\n【第1步：标注位置】标注前请确保安全，放置警示标志，开启双闪，并注意过往车辆。\n操作方法：点击下方“+”图标，选择“位置”，确认当前[位置]后点击右上角“发送”。");   
		this.tipMessage(textBuffer.toString()); 
		this.session.setAttribute("lastOperationStage", TestAccidentProcess.START); 
		this.session.setAttribute("accidentStoreBean",null);   
		return true; 
	}
	
	public boolean imageProcess(ImageReqBean imageReqBean,String imageFilePath){ 
		 super.imageProcess(imageReqBean,  imageFilePath);
		 if(imageProcess_Overview(imageReqBean)) return true;
		 if(imageProcess_Center(imageReqBean)) return true;
		 if(imageProcess_Detail(imageReqBean)) return true;
		 if(imageProcess_Wait(imageReqBean)) return true;
		 return false;
	}
	
	private boolean imageProcess_Overview(ImageReqBean imageReqBean){
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  TestAccidentProcess;  
		if(!sign) return false;
		sign=stage.equals(TestAccidentProcess.PHONE);
		if(!sign) return false;  
		NewsResBean localResBean=new NewsResBean();
		localResBean.setCreateTime(imageReqBean.getCreateTime());
		localResBean.setFromUserName(imageReqBean.getToUserName());
		localResBean.setToUserName(imageReqBean.getFromUserName()); 
		localResBean.setMsgType(ProcessService.MESSAGE_TYPE_NEWS);
		ItemResObject itemResObject;
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>();
		itemResObject=new ItemResObject();
		itemResObject.setTitle("【第3-2步：现场拍照——后方照】"); 
		itemResObject.setDescription("已经收到第一张照片。请继续在事故车辆后方约8米处进行拍照上传，照片要求能看清楚车辆停车位置。");
		itemResObject.setPicUrl(String.format(pic_accident_centerUrl,this.SERVER_IP));
		itemResObject.setUrl("");
		articles.add(itemResObject);
		localResBean.setArticles(articles) ;
		localResBean.setArticleCount(new Integer(articles.size()).toString()); 
		this.outMsg=localResBean;  
		this.session.setAttribute("lastOperationStage", TestAccidentProcess.OVERVIEW);   
		this.addPicture("概览","概览照",null); 
		return sign; 
	}
	
	
	private boolean imageProcess_Center(ImageReqBean imageReqBean)  {  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  TestAccidentProcess;  
		if(!sign) return false;
		sign=stage.equals(TestAccidentProcess.OVERVIEW);
		if(!sign) return false; 
	 
		NewsResBean localResBean=new NewsResBean();
		localResBean.setCreateTime(this.inMsg.getCreateTime());
		localResBean.setFromUserName(this.inMsg.getToUserName());
		localResBean.setToUserName(this.inMsg.getFromUserName()); 
		localResBean.setMsgType(ProcessService.MESSAGE_TYPE_NEWS);
		ItemResObject itemResObject;
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>();
		itemResObject=new ItemResObject();
		itemResObject.setTitle("【第3-3步：现场拍照——细目照】"); 
		itemResObject.setDescription("已经收到第二张照片。请继续按图示要求拍照，照片要求能看清楚车辆碰撞部位，损坏情况。");
		itemResObject.setPicUrl(String.format(pic_accident_detailsUrl,this.SERVER_IP));
		itemResObject.setUrl("");
		articles.add(itemResObject);
		localResBean.setArticles(articles) ;
		localResBean.setArticleCount(new Integer(articles.size()).toString());
		this.outMsg=localResBean; 
		this.session.setAttribute("lastOperationStage", TestAccidentProcess.CENTER);  
		this.addPicture("中心","中心照",null);
		
		 
		return sign;
		
	}
	
	
	private boolean imageProcess_Detail(ImageReqBean imageReqBean)  {  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  TestAccidentProcess;  
		if(!sign) return false;
		sign=stage.equals(TestAccidentProcess.CENTER);
		if(!sign) return false; 
		 
		TextResBean localResBean=new TextResBean();
		localResBean.setCreateTime(this.inMsg.getCreateTime());
		localResBean.setFromUserName(this.inMsg.getToUserName());
		localResBean.setToUserName(this.inMsg.getFromUserName());
		localResBean.setMsgType(ProcessService.MESSAGE_TYPE_TEXT); 
		localResBean.setContent("已接收到第三张照片 请将双方驾驶证、行驶证、交强险保单（或交强险粘贴标志）一同拍照上传。"); 
		this.outMsg=localResBean;  
		this.session.setAttribute("lastOperationStage", TestAccidentProcess.DETAIL);    
		this.addPicture("细目","细目照",null); 
		return sign;
		
	} 
	
	private boolean imageProcess_Wait(ImageReqBean imageReqBean)  {  
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  TestAccidentProcess;  
		if(!sign) return false;
		sign=stage.equals(TestAccidentProcess.DETAIL);
		if(!sign) return false;  
		 
		this.addPicture("信息","信息照",null);
		AccidentStoreBean accidentStoreBean=this.getSessionAccidentStoreBean(); 
		TbTestAccident tbTestAccident=new TbTestAccident();
		
		
		 
		tbTestAccident.setAccidentState("上报");
		LocationStoreInfo locationInfo=accidentStoreBean.getLocationInfo();
		if(locationInfo!=null){
			tbTestAccident.setAddress(locationInfo.getAddress());
			tbTestAccident.setLocationX(locationInfo.getLocationX());
			tbTestAccident.setLocationY(locationInfo.getLocationY());
		}
		tbTestAccident.setReporterType("公众");
		tbTestAccident.setReportOpenId(this.openId);
		tbTestAccident.setReportPhoneNumber(accidentStoreBean.getReportPhoneNumber()); 
		Calendar cal = Calendar.getInstance(); 
        Date date=cal.getTime();   
        tbTestAccident.setReportTime(new Timestamp(date.getTime())); 
		
        //accidentStoreBean.setAccidentId(accidentId);
		this.session.setAttribute("lastOperationStage", TestAccidentProcess.WAIT);  
		this.session.setAttribute("accidentStoreBean", null);
		testAccidentDao.saveOrUpdate(tbTestAccident); 
		StringBuffer textBuffer=new StringBuffer("");
		textBuffer.append("已接收到第四张照片,");
		textBuffer.append("学习版轻微事故处理程序完成，谢谢您的关注！");
		this.tipMessage(textBuffer.toString());  
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
		TextResBean localResBean=new TextResBean();
		localResBean.setCreateTime(this.inMsg.getCreateTime());
		localResBean.setFromUserName(this.inMsg.getToUserName());
		localResBean.setToUserName(this.inMsg.getFromUserName());
		localResBean.setMsgType(ProcessService.MESSAGE_TYPE_TEXT);
		 //"经度:"+locationReqBean.getLocation_X()+"纬度:"+locationReqBean.getLocation_Y() 
		localResBean.setContent("您标注的地理位置是："+locationReqBean.getLabel()+"\n【第2步：提供电话】请输入您的手机号，并确保通讯畅通，以便民警与您取得联系。"); 
		this.outMsg=localResBean; 
		this.session.setAttribute("lastOperationStage",TestAccidentProcess.LOCATION); 
		AccidentStoreBean accidentStoreBean=this.getSessionAccidentStoreBean();
		LocationStoreInfo locationInfo=accidentStoreBean.getLocationInfo();
		if(locationInfo==null)
			  locationInfo= new LocationStoreInfo(); 
		locationInfo.setAddress(locationReqBean.getLabel());
		locationInfo.setLocationX(locationReqBean.getLocation_X()); 
		locationInfo.setLocationY(locationReqBean.getLocation_Y());
		accidentStoreBean.setLocationInfo(locationInfo);
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
		
		NewsResBean localResBean=new NewsResBean();
		localResBean.setCreateTime(textReqBean.getCreateTime());
		localResBean.setFromUserName(textReqBean.getToUserName());
		localResBean.setToUserName(textReqBean.getFromUserName()); 
		localResBean.setMsgType(ProcessService.MESSAGE_TYPE_NEWS);
		ItemResObject itemResObject;
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>();
		itemResObject=new ItemResObject();
		itemResObject.setTitle("【第3-1步：现场拍照——前方照】");
		itemResObject.setDescription("站在道路中间拍摄，标志、标线拍清楚，在事故车前方约8米处进行拍照。点击下方“+”图标，选择“拍照”。");
		itemResObject.setPicUrl(String.format(pic_accident_overviewUrl,this.SERVER_IP));
		itemResObject.setUrl("");
		articles.add(itemResObject);
		localResBean.setArticles(articles) ;
		localResBean.setArticleCount(new Integer(articles.size()).toString());
		this.outMsg=localResBean;  
		this.session.setAttribute("lastOperationStage",TestAccidentProcess.PHONE);  
		AccidentStoreBean accidentStoreBean=this.getSessionAccidentStoreBean();
		accidentStoreBean.setReportPhoneNumber(content);  
		this.session.setAttribute("accidentStoreBean", accidentStoreBean);
		return sign;
	}
	
	
	
}