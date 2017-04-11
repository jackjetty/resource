package com.detachment.wei.service.impl;
import java.io.File; 
import java.net.URLEncoder;
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
import org.rising.wei.bean.res.object.ItemResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;  

 
import com.detachment.dao.PersonalPromotionDao;
import com.detachment.dao.ProcessDao;
import com.detachment.dao.WeiUserDao;
import com.detachment.pojo.TbPersonalPromotion; 
 
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.HttpWeiUtil;
import com.detachment.util.WeiUtil;
import com.detachment.wei.thread.FileThread;
 
 
 
@Service("personalPromotionProcessService")
public class PersonalPromotionProcessServiceImpl   extends ProcessServiceImpl{ 
	@Autowired
	private ProcessDao processDao;
	
	@Autowired
	private PersonalPromotionDao personalPromotionDao;
	
	@Autowired
	private WeiUserDao weiUserDao;
	
	@Value("${wei.appid}") 
	private String  APPID; 
	
	@Value("${wei.appsecret}") 
	private String  APPSECRET; 
	
	
	@Value("${catalina.base.image.dir}") 
	private String  CATALINA_IMAGE; 
	
	
	 
	 
	@Value("${wei.picurl.personalpromotion.header}") 
	private String  pic_personalpromotion_headerUrl; 
	
	@Value("${wei.personalpromotion.promote.url}") 
	private String personalpromotion_promoteUrl;
	 
	public boolean clickEventProcess(ClickEventReqBean clickEventReqBean){ 
		super.clickEventProcess(clickEventReqBean);
		String eventKey=clickEventReqBean.getEventKey();
		boolean sign=true; 
		sign=eventKey.equalsIgnoreCase(Constant.PERSONALPROMOTION) ?sign:false;
		if(!sign) return false;  
		if(!processDao.isUsing(Constant.PERSONALPROMOTION)){
			this.hopeMessage();
			return true;
		}  
		 
		TbPersonalPromotion tbPersonalPromotion=personalPromotionDao.getTbPersonalPromotionByOpenId(this.openId);
		if(tbPersonalPromotion==null){
			tbPersonalPromotion=new TbPersonalPromotion();
			tbPersonalPromotion.setPromotionSum(0);
			tbPersonalPromotion.setTbWeiUser(weiUserDao.findById(this.openId));
			personalPromotionDao.saveOrUpdate(tbPersonalPromotion);
		}
		String picLoalStore=this.CATALINA_IMAGE+Constant.PERSONALPROMOTION.toLowerCase()+File.separator; 
		picLoalStore+=tbPersonalPromotion.getPersonalSceneId()+".jpg"; 
		String picUrl="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
		String ticket="";  
		if(!CommonUtil.isExist(picLoalStore)  ){
			try{
				ticket=HttpWeiUtil.getInstance(APPID, APPSECRET).getLimitTicket(tbPersonalPromotion.getPersonalSceneId());  
				new FileThread(picUrl.concat(URLEncoder.encode(ticket, "utf-8")),picLoalStore).start(); 
			}catch(Exception ex){
				ex.printStackTrace();
			} 
		}
		ItemResObject itemResObject; 
		ArrayList<ItemResObject> articles=new ArrayList<ItemResObject>(); 
		itemResObject=new ItemResObject();
		itemResObject.setTitle("我的推广"); 
		itemResObject.setDescription("推广绍兴微信公众号，赢取丰厚奖品!!"); 
		itemResObject.setPicUrl(String.format(pic_personalpromotion_headerUrl,this.SERVER_IP));  
		itemResObject.setUrl(String.format(personalpromotion_promoteUrl,this.SERVER_IP,this.openId));
		articles.add(itemResObject);   
		this.outMsg=WeiUtil.getNewsMessage(clickEventReqBean, articles) ;  
		return true;    
		 
	}
}