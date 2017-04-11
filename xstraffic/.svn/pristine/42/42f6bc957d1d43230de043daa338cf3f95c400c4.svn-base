package com.traffic.web.service.impl;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.opensymphony.xwork2.ActionContext;
 
 
import com.traffic.dao.InfoPicDao;
 
import com.traffic.json.JsInfoPic; 
import com.traffic.pojo.TbInfoPic;
import com.traffic.pojo.TbInfoPicId;
 
 
 
import com.traffic.util.CommonUtil;
import com.traffic.util.Constant;
import com.traffic.util.ImageUtil;
 
import com.traffic.web.service.InfoPicService;
 
 
import com.alibaba.fastjson.JSON;
@Service("infoPicService")
public class InfoPicServiceImpl implements InfoPicService {
	private Log log = LogFactory.getLog(InfoPicServiceImpl.class);
	@Autowired
	private InfoPicDao infoPicDao;
	
	@Override
	public HashMap<String, Object> rotatePic(String jsArrInfoPic) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		jsArrInfoPic = jsArrInfoPic == null || jsArrInfoPic.equalsIgnoreCase("") ? "[]" : jsArrInfoPic;
		List<JsInfoPic> jsInfoPicList = JSON.parseArray(jsArrInfoPic, JsInfoPic.class);
		TbInfoPic tbInfoPic;
		TbInfoPicId tbInfoPicId;
		String picLocalStore;
		for(JsInfoPic jsInfoPic:jsInfoPicList){
			tbInfoPicId=new TbInfoPicId();
			tbInfoPicId.setProcessId(jsInfoPic.getProcessId());
			tbInfoPicId.setRecordNo(jsInfoPic.getRecordNo());
			tbInfoPicId.setPicIndex(jsInfoPic.getPicIndex());
			tbInfoPic=infoPicDao.findById(tbInfoPicId);
			if(tbInfoPic==null)
				 continue;
			picLocalStore=tbInfoPic.getPicLocalStore();
			
			BufferedImage src;
			try {
				 src = ImageIO.read(new File(picLocalStore));
				 BufferedImage des = ImageUtil.Rotate(src, jsInfoPic.getRotateDegree());
				 ImageIO.write(des, "jpg", new File(picLocalStore)) ; 
			} catch (IOException e) {
				 
				e.printStackTrace();
				continue;
			}  
		   
			 
			
		}
		result.put("respCode", 0);
		result.put("respInfo", "操作成功！");
		return result;
		
	}
	
}