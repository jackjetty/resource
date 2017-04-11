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
  
 
import com.detachment.dao.FormalAccidentDao;
import com.detachment.dao.InfoPicDao;
import com.detachment.dao.ProcessDao;
import com.detachment.dao.UserDao;
import com.detachment.util.Constant;
import com.detachment.util.WeiUtil;
import com.detachment.wei.process.AccidentProcess;
import com.detachment.wei.process.FormalAccidentProcess;
import com.detachment.wei.process.TestAccidentProcess;
 
@Service("accidentProcessService")
public class AccidentProcessServiceImpl   extends ProcessServiceImpl{
	@Autowired
	private UserDao userDao;
	@Autowired
	private FormalAccidentDao formalAccidentDao;
	@Autowired
	private ProcessDao processDao;
	 
	
	@Value("${wei.detachment.scope.url}") 
	private String  detachementScope_Url; 
	
	@Value("${wei.accident.formal.record.url}") 
	private String accident_formal_recordUrl;
	
	@Value("${wei.picurl.accident.formal.record}") 
	private String pic_accident_formal_recordUrl;
	
	
	  
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true;
		sign=eventKey.equalsIgnoreCase(Constant.ACCIDENT)?sign:false;
		if(!sign) return false;
		if(!processDao.isUsing(Constant.ACCIDENT)){
			this.hopeMessage();
			return true;
		} 
		StringBuffer textBuffer=new StringBuffer("");  
		textBuffer.append("您已进入轻微事故处理平台: \n");
		textBuffer.append("【适用范围】\n");
		textBuffer.append("<a href='"+String.format(detachementScope_Url,this.SERVER_IP)+"'>点击查看详细规定</a>\n"); 
		textBuffer.append("适用于投保车辆在城区道路发生的人未伤，车能动，两车损失不过万的轻微物损事故。\n");  
		textBuffer.append("【适用时间】\n");
		textBuffer.append("每天6时至20时\n");
		textBuffer.append("【联系电话】\n");
		textBuffer.append(this.DETACHMENT_CONTACT+"\n"); 
		textBuffer.append("特别警示：伪造现场，虚假报案，将依法追究法律责任。\n");  
		textBuffer.append("请输入:\n");
		textBuffer.append("      [A]开始处理\n");
		textBuffer.append("      [B]测试体验\n");
		textBuffer.append("      [C]查询事故记录"); 
        this.tipMessage(textBuffer.toString());
		this.session.setAttribute("lastOperationStage", AccidentProcess.START); 
		this.session.setAttribute("accidentStoreBean",null);  
		return true; 
	} 
	public boolean textProcess(TextReqBean textReqBean){
		super.textProcess(textReqBean);
		if(textProcess_Order(textReqBean)) return true;  
		return false;
	}
	private boolean textProcess_Order(TextReqBean textReqBean){
		String content=textReqBean.getContent().trim();   
		boolean sign=true;
		sign=stage!=null?sign:false;
		if(!sign) return false; 
		sign=stage instanceof  AccidentProcess;  
		if(!sign) return false;
		sign=stage.equals(AccidentProcess.START);
		if(!sign) return false; 
	 
		if(content.equalsIgnoreCase("C")){
			textProcess_Order_Record(textReqBean);
			return sign;
		}
		if(content.equalsIgnoreCase("A")){
			textProcess_Order_Formal(textReqBean);
			return sign;
		}	
		if(content.equalsIgnoreCase("B")){
			textProcess_Order_Test(textReqBean);
			return sign;
		}	 
		this.tipMessage("请输入正确的指令 ....../:8*");
		return true;
	}
	private void textProcess_Order_Record(TextReqBean textReqBean){
	    //this.openId;
	    String hql="select count(*) from TbFormalAccident where reportOpenId=? ";
	    int recordNum=formalAccidentDao.findCount(hql, this.openId);
	    if(recordNum==0){
	    	this.tipMessage("不存在交通事故记录，请查证！！....../:8*");
	    	return;
	    }
	    ItemResObject itemResObject;
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>();
		itemResObject=new ItemResObject();
		itemResObject.setTitle("交通事故记录");
		itemResObject.setDescription("查询交通事故处理情况");
		itemResObject.setPicUrl( String.format(pic_accident_formal_recordUrl,this.SERVER_IP) );
		itemResObject.setUrl(String.format(accident_formal_recordUrl,this.SERVER_IP,this.openId));
		articles.add(itemResObject);
		this.outMsg=WeiUtil.getNewsMessage(textReqBean, articles) ;  
		//this.session.setAttribute("lastOperationStage",AccidentProcess.RECORD); 
	}
	private void textProcess_Order_Formal(TextReqBean textReqBean){
		StringBuffer textBuffer=new StringBuffer("");  
		textBuffer.append("轻微事故开始处理: \n");
		textBuffer.append("【第1步：标注位置】标注前请确保安全，放置警示标志，开启双闪，并注意过往车辆。\n操作方法：点击下方“+”图标→选择“位置”→确认当前[位置]后点击右上角“发送”。");
		this.tipMessage(textBuffer.toString());
		this.session.setAttribute("lastOperationStage",FormalAccidentProcess.START); 
    	return; 
	}
	
	private void textProcess_Order_Test(TextReqBean textReqBean){
		StringBuffer textBuffer=new StringBuffer("");  
		textBuffer.append("轻微事故测试体验: \n");
		textBuffer.append("【第1步：标注位置】标注前请确保安全，放置警示标志，开启双闪，并注意过往车辆。\n操作方法：点击下方“+”图标→选择“位置”→确认当前[位置]后点击右上角“发送”。");
		this.tipMessage(textBuffer.toString());
		this.session.setAttribute("lastOperationStage",TestAccidentProcess.START); 
    	return; 
	}
	
	
}