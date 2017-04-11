package com.detachment.web.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.AppointmentDao;
import com.detachment.dao.AppointmentRecordDao;
import com.detachment.dao.AppointmentTypeDao;
import com.detachment.dao.FullScodeDao;
import com.detachment.dao.WeiUserDao;
import com.detachment.pojo.TbAppointment;
import com.detachment.pojo.TbAppointmentRecord;
import com.detachment.pojo.TbAppointmentType;
import com.detachment.pojo.TbFullScode;
import com.detachment.pojo.TbWeiUser;
import com.detachment.pojo.vo.TbAppointmentVo;
import com.detachment.web.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	AppointmentDao appointmentDao;
	@Autowired
	AppointmentTypeDao appointmentTypeDao;
	@Autowired
	WeiUserDao weiUserDao;
	@Autowired
	FullScodeDao fullScodeDao;
	@Autowired
	AppointmentRecordDao appointmentRecordDao;

	@Override
	public ArrayList<TbAppointmentVo> getAppointmentJsp(Integer pageSize,
			Integer pageIndex) {
		String hql="from TbAppointment where appointmentState =?";
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbAppointment> tat=(ArrayList<TbAppointment>) appointmentDao.findPage(hql, start, pageSize, "开放");
		ArrayList<TbAppointmentVo> tatvo=new ArrayList<TbAppointmentVo>();
		HashMap<String,Object> map=getappointmentTypeIdName();
		for(TbAppointment t:tat){
			TbAppointmentVo tvo=new TbAppointmentVo(t,map);
			tatvo.add(tvo);
		}
		return tatvo;
	}
	
	@Override
	public HashMap<String, Object> saveAppointmentRecord(Integer appointmentId,String openId) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		try {
			TbAppointment ta=appointmentDao.findById(appointmentId);
			TbWeiUser tw=weiUserDao.findById(openId);
			TbFullScode tf=fullScodeDao.findByOpenIdLast(openId);
			if(tw!=null && tf!=null){
				if("预约中".equals(tf.getState())){
					TbAppointmentRecord tar=new TbAppointmentRecord();
					tar.setTbAppointment(ta);
					tar.setTbWeiUser(tw);
					tar.setIdentityCard(tf.getIdentityCard());
					tar.setFileNum(tf.getFileNum());
					tar.setUserName(tf.getUserName());
					tar.setPhoneNumber(tf.getPhoneNumber());
					tar.setAppointmentTime(new Timestamp(new Date().getTime()));
					tar.setAppointmentRecordState("已预约");
					appointmentRecordDao.save(tar);
					Integer count=ta.getAppointmentCount()+1;
					ta.setAppointmentCount(count);
					appointmentDao.update(ta);
					tf.setState("已预约");
					fullScodeDao.update(tf);
					HashMap<String,Object> map=getappointmentTypeIdName();
					TbAppointmentVo ta1=new TbAppointmentVo(ta,map);
					resultMap.put("codeInfo", 1);
					resultMap.put("codeResp", "预约成功");
					resultMap.put("codeText", ta1);
				}else{
					resultMap.put("codeInfo", 0);
					resultMap.put("codeResp", "您当前状态不能预约");
				}
				
			}else{
				resultMap.put("codeInfo", 2);
				resultMap.put("codeResp", "预约不成功！请稍后重新预约");
			}
		} catch (Exception e) {
			//e.printStackTrace();
			resultMap.put("codeInfo", 2);
			resultMap.put("codeResp", "预约不成功！请稍后重新预约");
		}
		return resultMap;
	}

	@Override
	public ArrayList<TbAppointmentVo> findAppointmentRecordJsp(String openId) {
		ArrayList<TbAppointmentVo> tatvo=new ArrayList<TbAppointmentVo>();
		try {
			String hql=" from TbAppointment where appointmentState='开放' and appointmentId in (select tbAppointment.appointmentId from TbAppointmentRecord where openId=?)";
			ArrayList<TbAppointment> tat=(ArrayList<TbAppointment>) appointmentDao.findByHQL(hql, openId);
			HashMap<String,Object> map=getappointmentTypeIdName();
			for(TbAppointment t:tat){
				TbAppointmentVo tvo=new TbAppointmentVo(t,map);
				tatvo.add(tvo);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return tatvo;
	}

	@Override
	public HashMap<String,Object> getTbAppointmentTypeIdAndName() {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		ArrayList<TbAppointmentType> tatype=(ArrayList<TbAppointmentType>) appointmentTypeDao.findByHQL("from TbAppointmentType");
		String ids="";
		String names="";
		for(TbAppointmentType t:tatype){
			ids+=t.getAppointmentTypeId()+"=";
			names+=t.getAppointmentTypeName()+"=";
		}
		resultMap.put("ids", ids);
		resultMap.put("names", names);
		return resultMap;
	}

	@Override
	public HashMap<String,Object> getAppointment(Integer pageSize,
			Integer pageIndex, String appointmentTypeId, String appointmentState) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		ArrayList<TbAppointment> tat=new ArrayList<TbAppointment>();
		Integer start = (pageIndex - 1) * pageSize;
		Integer listSize = null;
		if("".equals(appointmentTypeId)){
			if("".equals(appointmentState)){
				listSize=appointmentDao.findCount("select count(*) from TbAppointment");
				tat=(ArrayList<TbAppointment>) appointmentDao.findPage("from TbAppointment", start, pageSize);
			}else{
				String hql="from TbAppointment where appointmentState=?";
				String hql1="select count(*) from TbAppointment where appointmentState=?";
				listSize=appointmentDao.findCount(hql1,appointmentState);
				tat=(ArrayList<TbAppointment>) appointmentDao.findPage(hql, start, pageSize,appointmentState);
			}
		}else{
			if("".equals(appointmentState)){
				String hql="from TbAppointment where appointmentTypeId=?";
				String hql1="select count(*) from TbAppointment where appointmentTypeId=?";
				listSize=appointmentDao.findCount(hql1,appointmentTypeId);
				tat=(ArrayList<TbAppointment>) appointmentDao.findPage(hql, start, pageSize,appointmentTypeId);
			}else{
				String hql="from TbAppointment where appointmentTypeId=? and appointmentState=?";
				String hql1="select count(*) from TbAppointment where appointmentTypeId=? and appointmentState=?";
				listSize=appointmentDao.findCount(hql1,appointmentTypeId,appointmentState);
				tat=(ArrayList<TbAppointment>) appointmentDao.findPage(hql, start, pageSize,appointmentTypeId,appointmentState);
			}
		}
		ArrayList<TbAppointmentVo> tatvo=new ArrayList<TbAppointmentVo>();
		HashMap<String,Object> map=getappointmentTypeIdName();
		for(TbAppointment t:tat){
			TbAppointmentVo tvo=new TbAppointmentVo(t,map);
			tatvo.add(tvo);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", tatvo);
		return resultMap;
	}

	@Override
	public TbAppointmentVo getTbAppointmentById(Integer appointmentId) {
		TbAppointment ta=appointmentDao.findById(appointmentId);
		HashMap<String,Object> map=getappointmentTypeIdName();
		TbAppointmentVo tatvo=new TbAppointmentVo(ta,map);
		return tatvo;
	}

	@Override
	public HashMap<String, Object> addAppointment(String appointmentTypeId,
			Integer appointmentSum, String appointmentStartTime,
			String appointmentEndTime, String appointmentTheme,
			String appointmentDesc, String appointmentAddress,
			String appointmentRemark) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		try {
			Timestamp startTime = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse(appointmentStartTime.toString() + " 00:00:00").getTime());
			Timestamp endTime = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse(appointmentEndTime.toString() + " 23:59:59").getTime());
			TbAppointmentType tatype=appointmentTypeDao.findById(appointmentTypeId);
			TbAppointment tat=new TbAppointment();
			tat.setTbAppointmentType(tatype);
			tat.setAppointmentStartTime(startTime);
			tat.setAppointmentEndTime(endTime);
			tat.setAppointmentTheme(appointmentTheme);
			tat.setAppointmentDesc(appointmentDesc);
			tat.setAppointmentAddress(appointmentAddress);
			tat.setAppointmentRemark(appointmentRemark);
			tat.setAppointmentSum(appointmentSum);
			tat.setAppointmentCount(0);
			tat.setAppointmentState("开放");
			appointmentDao.save(tat);
			map.put("respCode", 0);
			map.put("respInfo", "添加成功!");
		} catch (ParseException e) {
			map.put("respCode", -1);
			map.put("respInfo", "添加失败!");
			e.printStackTrace();
		}
		return map;
	}
	
	private HashMap<String,Object> getappointmentTypeIdName(){
		ArrayList<TbAppointmentType> tatype=(ArrayList<TbAppointmentType>) appointmentTypeDao.findByHQL("from TbAppointmentType");
		HashMap<String,Object> map=new HashMap<String,Object>();
		for(TbAppointmentType ty:tatype){
			map.put(ty.getAppointmentTypeId(), ty.getAppointmentTypeName());
		}
		return map;
		
	}

	@Override
	public HashMap<String, Object> updateAppointment(Integer appointmentId,
			String appointmentTypeId, Integer appointmentSum,
			String appointmentStartTime, String appointmentEndTime,
			String appointmentTheme, String appointmentDesc,
			String appointmentAddress, String appointmentRemark) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		try {
			Timestamp startTime = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse(appointmentStartTime.toString() + " 00:00:00").getTime());
			Timestamp endTime = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse(appointmentEndTime.toString() + " 23:59:59").getTime());
			TbAppointmentType tatype=appointmentTypeDao.findById(appointmentTypeId);
			TbAppointment tat=appointmentDao.findById(appointmentId);
			tat.setTbAppointmentType(tatype);
			tat.setAppointmentStartTime(startTime);
			tat.setAppointmentEndTime(endTime);
			tat.setAppointmentTheme(appointmentTheme);
			tat.setAppointmentDesc(appointmentDesc);
			tat.setAppointmentAddress(appointmentAddress);
			tat.setAppointmentRemark(appointmentRemark);
			tat.setAppointmentSum(appointmentSum);
			appointmentDao.update(tat);
			map.put("respCode", 0);
			map.put("respInfo", "修改成功!");
		} catch (ParseException e) {
			map.put("respCode", -1);
			map.put("respInfo", "修改失败!");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public HashMap<String, Object> removeAppointment(String appointmentIds) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = appointmentIds.split(",");
		int lastId=0;
		try {
			for(String appointmentId : idArray){
				if(!"".equals(appointmentId)){
					lastId = Integer.parseInt(appointmentId);
					TbAppointment tat=appointmentDao.findById(lastId);
					appointmentDao.delete(tat);
					resultMap.put("respInfo", "删除信息成功!");
				}
			}
		} catch (Exception e) {
			resultMap.put("respInfo", "删除信息失败!");
		}
		
		return resultMap;
	}

	@Override
	public HashMap<String, Object> updateAppointmentState(String appointmentIds) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = appointmentIds.split(",");
		int lastId=0;
		try {
			for(String appointmentId : idArray){
				if(!"".equals(appointmentId)){
					lastId = Integer.parseInt(appointmentId);
					TbAppointment tat=appointmentDao.findById(lastId);
					tat.setAppointmentState("关闭");
					appointmentDao.update(tat);
					resultMap.put("respInfo", "关闭活动成功!");
				}
			}
		} catch (Exception e) {
			resultMap.put("respInfo", "关闭活动失败!");
		}
		return resultMap;
	}

	// tinker 2014-09-12
	@Override
	public HashMap<String, Object> getTbAppointmentTheme(String appointmentTypeId) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		List<TbAppointment> tbAppointmentList = appointmentDao.findByHQL("from TbAppointment where tbAppointmentType.appointmentTypeId=?", appointmentTypeId);
		List<String> themeList = new ArrayList<String>(); 
		for (TbAppointment tbAppointment : tbAppointmentList) {
			themeList.add(tbAppointment.getAppointmentTheme());
		}
		result.put("themeList", themeList);
		return result;
	}

}







