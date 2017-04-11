package com.detachment.web.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.CarMoveDao;
import com.detachment.dao.FormalAccidentDao;
import com.detachment.dao.HandyPhotoDao;
import com.detachment.dao.InfoPicDao;
import com.detachment.dao.InfoTextDao;
import com.detachment.dao.WeiUserDao;
import com.detachment.pojo.TbHandyPhoto;
import com.detachment.pojo.TbWeiUser;
import com.detachment.pojo.vo.TbCarMoveVo;
import com.detachment.pojo.vo.TbHandyPhotoVo;
import com.detachment.util.CommonUtil;
import com.detachment.util.Constant;
import com.detachment.util.GpsToBaiDu;
import com.detachment.web.service.HandyPhotoService;


@Service("HandyPhotoService")
public class HandyPhotoServiceImpl implements HandyPhotoService {
	@Autowired
	private WeiUserDao weiUserDao;
	@Autowired
	private HandyPhotoDao handyPhotoDao;
	@Autowired
	private CarMoveDao carMoveDao;
	@Autowired
	private InfoTextDao infoTextDao;
	
	@Autowired
	private InfoPicDao infoPicDao;
	
	@Autowired
	private FormalAccidentDao formalAccidentDao;
	@Override
	public HashMap<String, Object> getHandyPhoto(String startTime, String endTime,
			String reportPhoneNumber, String nickName,Integer pageSize, Integer pageIndex,String handyPhotoState) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if(CommonUtil.trim(startTime).equalsIgnoreCase("")){
			startTime=CommonUtil.getBeforeWeekDateStrForm();
		}
		if(CommonUtil.trim(endTime).equalsIgnoreCase("")){
			endTime=CommonUtil.getCurrentDateStrForm();
		} 
		reportPhoneNumber=CommonUtil.trim(reportPhoneNumber);
		nickName=CommonUtil.trim(nickName);
		handyPhotoState=CommonUtil.trim(handyPhotoState);
	    
        String hql=" select count(ta.handyPhotoId) from TbHandyPhoto as ta,TbWeiUser as tu    ";
		
		String StrOrder="";  
      
        
         //
        String StrCdt=""; 
        ArrayList arraylist=new ArrayList();
        StrCdt=" where   (ta.reportOpenId=tu.openId) ";
        
        StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
        StrCdt+="  (  CONVERT(varchar(100), ta.reportTime, 23) between ? and ? ) ";
        arraylist.add(startTime); 
        arraylist.add(endTime); 
        
        if( reportPhoneNumber.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( ta.reportPhoneNumber like ? ) ";
            arraylist.add("%"+reportPhoneNumber+"%");  
        }
        
        if( handyPhotoState.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( ta.handyPhotoState like ? ) ";
            arraylist.add("%"+handyPhotoState+"%");  
        }
	    
        
        if( nickName.length()>0){
        	StrCdt=StrCdt.length()==0?" where  ":StrCdt+" and ";
            StrCdt+=" ( tu.nickname like ? ) ";
            arraylist.add("%"+nickName+"%");  
        }
		 
		
        Integer listSize=handyPhotoDao.findCount(hql+StrCdt, arraylist);
        
        
        
        StrOrder=" order by ta.reportTime desc"; 
        
         
        hql="select ta.handyPhotoId,ta.reportOpenId,ta.reportTime,ta.reportPhoneNumber " +
        		",ta.address,ta.locationX,ta.locationY,ta.handyPhotoState,ta.accepter,ta.acceptTime,ta.remark,ta.dealResult," +
        		"tu.nickname,tu.realName from TbHandyPhoto as ta ,TbWeiUser as tu ";
        
        List mResult=handyPhotoDao.findPage(hql+StrCdt+StrOrder, (pageIndex - 1) * pageSize, pageSize, arraylist);
        
        Object[] rows;
		String handyPhotoId;
		TbHandyPhotoVo tbHandyPhotoVo;
		List<TbHandyPhotoVo> handyPhotoVoList=new ArrayList<TbHandyPhotoVo>();
		for (Iterator iter = mResult.iterator(); iter.hasNext();) {
			  rows=(Object[]) iter.next();
			  tbHandyPhotoVo=new TbHandyPhotoVo();
			  handyPhotoId=CommonUtil.trim(rows[0]); 
			  tbHandyPhotoVo.setHandyPhotoId(handyPhotoId);
			  tbHandyPhotoVo.setReportOpenId(CommonUtil.trim(rows[1]));
			  tbHandyPhotoVo.setReportTime(CommonUtil.getDateForm(rows[2],"yyyy-MM-dd HH:mm:ss"));
			  tbHandyPhotoVo.setReportPhoneNumber(CommonUtil.trim(rows[3]));
			  tbHandyPhotoVo.setAddress(CommonUtil.trim(rows[4]));
			  tbHandyPhotoVo.setLocationX(CommonUtil.trim(rows[5]));
			  tbHandyPhotoVo.setLocationY(CommonUtil.trim(rows[6]));
			  tbHandyPhotoVo.setHandyPhotoState(CommonUtil.trim(rows[7]));
			  tbHandyPhotoVo.setAccepter(CommonUtil.trim(rows[8]));
			  tbHandyPhotoVo.setAcceptTime(CommonUtil.getDateForm(rows[9],"yyyy-MM-dd HH:mm:ss"));
			  tbHandyPhotoVo.setRemark(CommonUtil.trim(rows[10]));
			  tbHandyPhotoVo.setDealResult(CommonUtil.trim(rows[11]));
			  
			  tbHandyPhotoVo.setNickName(CommonUtil.trim(rows[12]));
			  tbHandyPhotoVo.setUserName(CommonUtil.trim(rows[13]));
			  hql="select count(*) from TbInfoPic where processId =? and recordNo = ?"; 
			  tbHandyPhotoVo.setPicInfo(new Integer(infoPicDao.findCount(hql, Constant.HANDYPHOTO,handyPhotoId)).toString());
			  String textContent=infoTextDao.getTextInfoByHandyPhotoId( handyPhotoId);
			  tbHandyPhotoVo.setTextInfo(textContent);
			  handyPhotoVoList.add(tbHandyPhotoVo) ;  
		} 
		resultMap.put("listSize", listSize);
		resultMap.put("rows",handyPhotoVoList);
		resultMap.put("respCode", 0);
		
		return resultMap;
	}
	@Override
	public HashMap<String, Object> getTbAddress(String handyPhotoId) {
		ArrayList<Object[]> addr = handyPhotoDao.getTbAddress(handyPhotoId);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		double locationY=Double.parseDouble(addr.get(0)[0].toString());
		double locationX=Double.parseDouble(addr.get(0)[1].toString());
		String[] xy=GpsToBaiDu.ToBaiduAddress(locationX, locationY).split("==");
		resultMap.put("locationX", xy[0]);
		resultMap.put("locationY", xy[1]);
		resultMap.put("address", addr.get(0)[2]);
		return resultMap;
	}
	@Override
	public HashMap<String, Object> updateState(String handyPhotoId,
			String managerName) {
		TbHandyPhoto tc = handyPhotoDao.findById(handyPhotoId);
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(tc==null){
			map.put("respInfo","要操作的用户记录被删除了，请刷新后进行其他操作！");
		}
		if(!"受理".equals(tc.getHandyPhotoState())){
			tc.setHandyPhotoState("受理");
			tc.setAcceptTime(new Timestamp(new Date().getTime()));
			tc.setAccepter(managerName);
			handyPhotoDao.updateState(tc);
			map.put("respInfo","成功处理随手拍!");
		}else{
			map.put("respInfo","该随手拍已处理!");
		}
		return map;
	
	}
	@Override
	public TbHandyPhotoVo getHandyPhotoById(String handyPhotoId) {
		TbHandyPhoto tbHandyPhone=handyPhotoDao.findById(handyPhotoId);
		String reportOpenId=CommonUtil.trim(tbHandyPhone.getReportOpenId()); 
		TbWeiUser tbWeiUser=weiUserDao.findById(reportOpenId);
		String nickname="";
		String username="";
		if(tbWeiUser!=null){
			nickname=CommonUtil.trim(tbWeiUser.getNickname());
			username=CommonUtil.trim(tbWeiUser.getRealName()); 		
		}  
		 
		TbHandyPhotoVo tbHandyPhotoVo=new TbHandyPhotoVo(tbHandyPhone,username,nickname);
		return tbHandyPhotoVo;
	}
	@Override
	public HashMap<String, Object> saveProcedureMessageHandyPhoto(
			String managerName, String handyPhotoId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			handyPhotoDao.saveprocedureMessage1(managerName, handyPhotoId);
			resultMap.put("respCode", 0);
			resultMap.put("respInfo", "保存审核结果成功！");
		} catch (Exception e) {
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "保存审核结果时发生异常！");
		}
		return resultMap;
	}
	@Override
	public HashMap<String, Object> responseWeiHandyPhoto(String handyPhotoId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			handyPhotoDao.updateAccidentStateFailed(handyPhotoId);
			resultMap.put("respCode", 0);
			resultMap.put("respInfo", "处理结果发送成功！");
		} catch (Exception e) {
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "处理结果发送失败！");
		}
		return resultMap;
	}
}
