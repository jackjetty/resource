package com.traffic.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.traffic.dao.AccidentDao; 
import com.traffic.dao.InfoPicDao; 
import com.traffic.dao.InfoTextDao;
import com.traffic.dao.UserDao;
import com.traffic.pojo.TbAccident;
import com.traffic.pojo.TbInfoPic;
import com.traffic.pojo.TbInfoPicId;
import com.traffic.pojo.TbInfoText;
import com.traffic.pojovo.TbAccidentVo;
import com.traffic.pojovo.TbInfoTextVo; 
import com.traffic.rpt.FormalAccidentRpt;
import com.traffic.util.CommonUtil;
import com.traffic.util.Constant;
import com.traffic.web.service.ReportService;

@Service("reportService")
public class ReportServiceImpl implements ReportService {
	Log log = LogFactory.getLog(AccidentServiceImpl.class);
	@Autowired
	private AccidentDao accidentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private InfoPicDao infoPicDao;
	
	@Autowired
	private InfoTextDao infoTextDao;
	
	@Override
	public List<FormalAccidentRpt> generateFormalAccidentCollection(String accidentId,String[] arrPicIndex) {
		 
		List<FormalAccidentRpt> list = new ArrayList<FormalAccidentRpt>();
		String hql="select ta.accidentId,ta.accidentState,ta.address,ta.partyPhoneNumber,ta.reportPhoneNumber,ta.reportTime,tu.nickName,tu.userName from TbAccident as ta ,TbWeiUser as tu ";
		hql+=" where ta.accidentId=? and ta.reportOpenId=tu.openId ";
		List mResult=accidentDao.findByHQL(hql, accidentId);
		ListIterator iterator=mResult.listIterator();
		FormalAccidentRpt formalAccidentRpt=new FormalAccidentRpt();
		formalAccidentRpt.setTitle("轻微事故微信上报记录"); 
		if(!iterator.hasNext()){
			  return list;
		} 
		Object[] rows=(Object[]) iterator.next();
		formalAccidentRpt.setAccidentId(CommonUtil.trim(rows[0]));
		formalAccidentRpt.setAccidentState(CommonUtil.trim(rows[1]));
		formalAccidentRpt.setAddress(CommonUtil.trim(rows[2]));
		formalAccidentRpt.setPartyPhoneNumber(CommonUtil.trim(rows[3]));
		formalAccidentRpt.setReportPhoneNumber(CommonUtil.trim(rows[4]));
		formalAccidentRpt.setReportTime(CommonUtil.getDateForm(rows[5],"yyyy年MM月dd日 HH时mm分"));
		formalAccidentRpt.setNickName(CommonUtil.trim(rows[6]));
		formalAccidentRpt.setUserName(CommonUtil.trim(rows[7]));
		if( arrPicIndex.length==0 ){
			list.add(formalAccidentRpt);  
			return list;
		} 
		
		 
		hql="from TbInfoText where id.processId=? and id.recordNo=? order by id.textIndex";
		List<TbInfoText>  textProcessRecords=infoTextDao.findByHQL(hql,Constant.FORMALACCIDENT, accidentId);
		String textInfo="";
		for(TbInfoText tbInfoText:textProcessRecords){
			textInfo+=tbInfoText.getTextMessage()+";"; 
		}
		
		
		formalAccidentRpt.setTextInfo(textInfo);
		
		
		hql="from TbInfoPic where id.processId=? and id.recordNo=?   order by id.picIndex";
		
		 
		
		
		List sResult=infoPicDao.findByHQL(hql,Constant.FORMALACCIDENT, accidentId);
		iterator=sResult.listIterator();
		
		if( arrPicIndex.length==0||(!iterator.hasNext())){
			list.add(formalAccidentRpt);  
			return list;
		} 
		TbInfoPic tbInfoPic;
		FormalAccidentRpt aFormalAccidentRpt;
		TbInfoPicId tbInfoPicId;
		for(iterator=sResult.listIterator();iterator.hasNext();){
			tbInfoPic=(TbInfoPic)iterator.next(); 
			tbInfoPicId=tbInfoPic.getId();
			formalAccidentRpt.setPicPath(CommonUtil.trim(tbInfoPic.getPicLocalStore()));  
			for(String picIndex:arrPicIndex){
				if(picIndex.equalsIgnoreCase(tbInfoPicId.getPicIndex().toString())){
					list.add(formalAccidentRpt.clone());  
					break;
				}
			}
			
		}  
		return list;
	}
}