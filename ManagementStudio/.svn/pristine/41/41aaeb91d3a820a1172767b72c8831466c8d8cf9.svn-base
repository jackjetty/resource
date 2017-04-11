package com.rising.management.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.UserCheckin;
import com.rising.management.dao.PlaceDao;
import com.rising.management.dao.UserCheckinDao;
import com.rising.management.service.PlaceService;
import com.rising.management.service.UserCheckinService;
import com.rising.management.vo.UserCheckinVo;

@Service("userCheckinService")
public class UserCheckinServiceImpl implements UserCheckinService{
	
	Log log = LogFactory.getLog(UserCheckinServiceImpl.class);
	@Autowired
	UserCheckinDao userCheckinDao;
	@Autowired
	PlaceDao placeDao;
	@Autowired
	PlaceService placeService;

	@Override
	public HashMap<String, Object> getUserCheckin(String phoneNumber,
			String checkinTime, Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("".equals(phoneNumber)){
			phoneNumber=null;
		}
		if("".equals(checkinTime)){
			checkinTime = null;
		}
		Integer listSize =userCheckinDao.getUserCheckinSize(phoneNumber, checkinTime);
		Integer start = (pageIndex-1)*pageSize;
		ArrayList<UserCheckin> uc=userCheckinDao.getUserCheckin(phoneNumber, checkinTime, pageSize, start);
		ArrayList<UserCheckinVo> ucv=new ArrayList<UserCheckinVo>();
		for(UserCheckin u:uc){
			ucv.add(new UserCheckinVo(u));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows",ucv);
		return resultMap;
	}

	@Override
	public Integer getUserCheckinSize(String phoneNumber,
			String checkinTime) {
		if("".equals(checkinTime)){
			checkinTime = null;
		}
		if("".equals(phoneNumber)){
			phoneNumber=null;
		}
		Integer listSize =userCheckinDao.getUserCheckinSize(phoneNumber, checkinTime);
		return listSize;
	}

	@Override
	public HashMap<String, Object> getUserCheckinByImage(String placeName,
			String startTime, String endTime, String toTime) {

		Date startDate = null;
		Date endDate = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String,Object> resultMap= new HashMap<String, Object>();
		//获得地区名
		ArrayList<String> pc = placeDao.getPName();
		//获得地区名和地区号
		HashMap<String,Object> codeName=placeService.getPlaceCodeForName();
		String placeCode=null;
		if(codeName.get(placeName)!=null){
			placeCode=(String) codeName.get(placeName);
		}
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(startTime + " 00:00:00");
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime
					+ " 23:59:59");
			String xAxis="";
			String newshuju="";
			Integer startYear=Integer.parseInt(startTime.split("-")[0]);
			Integer endYear=Integer.parseInt(endTime.split("-")[0]);
			if("".equals(placeName)&&"0".equals(toTime)){
				for (String pName:pc) {
					if(null!=placeName && !"".equals(placeName) && !"null".equals(placeName)){
						Integer userCheckinNumbers = userCheckinDao.getUserCheckinNumbers(placeName,startDate,endDate);
						//Integer newRegister = operateLogDao.getNewRegisterNumber(placeName,startDate,endDate);
						xAxis=xAxis+pName+",";
						newshuju=newshuju+userCheckinNumbers+",";
					}else{
						Integer userCheckinNumbers = userCheckinDao.getUserCheckinNumbers(pName,startDate,endDate);
						xAxis=xAxis+pName+",";
						newshuju=newshuju+userCheckinNumbers+",";
						
					}
					
				}	
				String xAxis1=xAxis.substring(0, xAxis.length()-1);
				String newshuju1="["+newshuju.substring(0, newshuju.length()-1)+"]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			}else if("".equals(placeName)&&"1".equals(toTime)){
				resultMap=userCheckinDao.getUserCheckinNumbers(placeCode, startDate, endDate, toTime);
				Date start=null;
				Date end=null;
				try {
					 start=new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
					 end=new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				long quto=(end.getTime()-start.getTime())/1000 / 60 / 60 / 24;
				Calendar c=Calendar.getInstance();
				for(int i=0;i<=quto;i++){
					c.setTime(start);
					c.add(Calendar.DAY_OF_MONTH, i);
					String day=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
					String day1=day.split("-")[1]+"-"+day.split("-")[2];
					Integer newrt = 0;
					if(resultMap.get(day.toString())==null){
						newrt = 0;
					}else{
						String newrtString= resultMap.get(day.toString()).toString();
						newrt = Integer.parseInt(newrtString);
					}
					xAxis=xAxis+day1+",";
					newshuju=newshuju+newrt+",";
				}
				String xAxis1=xAxis;
				String newshuju1="["+newshuju.substring(0, newshuju.length()-1)+"]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
				
				
				
			}else if("".equals(placeName)&&"2".equals(toTime)){
				Date start=null;
				Date end=null;
				try {
					 start=new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
					 end=new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Calendar c = Calendar.getInstance();
				c.setTime(end);
				c.add(Calendar.DAY_OF_MONTH, 1);
				end = c.getTime();
				long quto=(end.getTime()-start.getTime())/1000 / 60 / 60 / 24;
				long size=0;
				if(quto%7==0){
					size=quto/7;
				}else{
					size=(quto/7)+1;
				}
				for(int i=1;i<=size;i++){
					String day1="第"+i+"周";
					Integer sumZhou=0;
					Date startD =null;
					Date endD = null;
					if (i == size) {
						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, (i-1)*7);
						startD= c.getTime();
						int mod = (int) (quto % 7);
						c.add(Calendar.DAY_OF_MONTH, mod);
						endD = c.getTime();
					} else {
						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, (i-1)*7);
						startD = c.getTime();
						c.add(Calendar.DAY_OF_MONTH, i*7);
						endD = c.getTime();
					}
					sumZhou = userCheckinDao.getUserCheckinNumbers("",startD, endD);	
					xAxis=xAxis+day1+",";
					newshuju=newshuju+sumZhou+",";
				}
				String xAxis1=xAxis;
				String newshuju1="["+newshuju.substring(0, newshuju.length()-1)+"]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			}else if("".equals(placeName)&&"3".equals(toTime)){
				resultMap=userCheckinDao.getUserCheckinNumbers(placeCode, startDate, endDate, toTime);
				Integer startMonth=Integer.parseInt(startTime.split("-")[1]);
				Integer endMonth=Integer.parseInt(endTime.split("-")[1]);
				if(startYear.equals(endYear)){
					Integer months=endMonth-startMonth;
					for(int i=0;i<=months;i++){
						Integer month=startMonth+i;
						String month1="";
						if(month<10){
							month1=startYear+"-0"+month;
						}else{
							month1=startYear+"-"+month;
						}
						Integer newrt = 0;
						if(resultMap.get(month1.toString())==null){
							newrt = 0;
						}else{
							String newrtString= resultMap.get(month1.toString()).toString();
							newrt = Integer.parseInt(newrtString);
						}
						xAxis=xAxis+month1+",";
						newshuju=newshuju+newrt+",";
						
					}
				}else {
					Integer months=12+endMonth-startMonth;
					for(int i=0;i<=months;i++){
						int month=startMonth+i;
						String month1="";
						if(month<12&&month<10){
							month1=startYear+"-0"+month;
						}else if(month<=12&&month>=10){
							month1=startYear+"-"+month;
						}else if(month>12){
							int cha=month-12;
							if(cha<10){
								month1=endYear+"-0"+cha;
							}else{
								month1=endYear+"-"+cha;
							}
						}
						Integer newrt = 0;
						if(resultMap.get(month1.toString())==null){
							newrt = 0;
						}else{
							String newrtString= resultMap.get(month1.toString()).toString();
							newrt = Integer.parseInt(newrtString);
						}
						xAxis=xAxis+month1+",";
						newshuju=newshuju+newrt+",";
					}
					
				}
				String xAxis1=xAxis;
				String newshuju1="["+newshuju.substring(0, newshuju.length()-1)+"]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			}else if(placeName.length()>1&&"0".equals(toTime)){
				//Integer newRegister = operateLogDao.getNewRegisterNumber(placeName,startDate,endDate);
				Integer userCheckinNumbers = userCheckinDao.getUserCheckinNumbers(placeName,startDate,endDate);
				xAxis=placeName+",";
				newshuju="["+userCheckinNumbers+"]";
				map.put("xAxis", xAxis);
				map.put("newshuju", newshuju);

				
			}else if(placeName.length()>1&&"1".equals(toTime)){
				resultMap=userCheckinDao.getUserCheckinNumbers(placeCode, startDate, endDate, toTime);
				Date start=null;
				Date end=null;
				try {
					 start=new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
					 end=new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				long quto=(end.getTime()-start.getTime())/1000 / 60 / 60 / 24;
				Calendar c=Calendar.getInstance();
				for(int i=0;i<=quto;i++){
					c.setTime(start);
					c.add(Calendar.DAY_OF_MONTH, i);
					String day=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
					String day1=day.split("-")[1]+"-"+day.split("-")[2];
					Integer newrt = 0;
					if(resultMap.get(day.toString())==null){
						newrt = 0;
					}else{
						String newrtString= resultMap.get(day.toString()).toString();
						newrt = Integer.parseInt(newrtString);
					}
					xAxis=xAxis+day1+",";
					newshuju=newshuju+newrt+",";
				}
				String xAxis1=xAxis;
				String newshuju1="["+newshuju.substring(0, newshuju.length()-1)+"]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			}else if(placeName.length()>1&&"2".equals(toTime)){
				Date start=null;
				Date end=null;
				try {
					 start=new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
					 end=new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Calendar c = Calendar.getInstance();
				c.setTime(end);
				c.add(Calendar.DAY_OF_MONTH, 1);
				end = c.getTime();
				long quto=(end.getTime()-start.getTime())/1000 / 60 / 60 / 24;
				long size=0;
				if(quto%7==0){
					size=quto/7;
				}else{
					size=(quto/7)+1;
				}
				for(int i=1;i<=size;i++){
					String day1="第"+i+"周";
					Integer sumZhou = 0;
					Date startD =null;
					Date endD = null;
					if (i == size) {
						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, (i-1)*7);
						startD= c.getTime();
						int mod = (int) (quto % 7);
						c.add(Calendar.DAY_OF_MONTH, mod);
						endD = c.getTime();
					} else {
						c.setTime(start);
						c.add(Calendar.DAY_OF_MONTH, (i-1)*7);
						startD = c.getTime();
						c.add(Calendar.DAY_OF_MONTH, i*7);
						endD = c.getTime();
					}
					sumZhou = userCheckinDao.getUserCheckinNumbers(placeName,startD, endD);
					xAxis=xAxis+day1+",";
					newshuju=newshuju+sumZhou+",";
				}
				String xAxis1=xAxis;
				String newshuju1="["+newshuju.substring(0, newshuju.length()-1)+"]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			}else if(placeName.length()>1&&"3".equals(toTime)){
				resultMap=userCheckinDao.getUserCheckinNumbers(placeCode, startDate, endDate, toTime);
				Integer startMonth=Integer.parseInt(startTime.split("-")[1]);
				Integer endMonth=Integer.parseInt(endTime.split("-")[1]);
				if(startYear.equals(endYear)){
					Integer months=endMonth-startMonth;
					for(int i=0;i<=months;i++){
						Integer month=startMonth+i;
						String month1="";
						if(month<10){
							month1=startYear+"-0"+month;
						}else{
							month1=startYear+"-"+month;
						}
						Integer newrt = 0;
						if(resultMap.get(month1.toString())==null){
							newrt = 0;
						}else{
							String newrtString= resultMap.get(month1.toString()).toString();
							newrt = Integer.parseInt(newrtString);
						}
						xAxis=xAxis+month1+",";
						newshuju=newshuju+newrt+",";
						
					}
				}else {
					Integer months=12+endMonth-startMonth;
					for(int i=0;i<=months;i++){
						int month=startMonth+i;
						String month1="";
						if(month<12&&month<10){
							month1=startYear+"-0"+month;
						}else if(month<=12&&month>=10){
							month1=startYear+"-"+month;
						}else if(month>12){
							int cha=month-12;
							if(cha<10){
								month1=endYear+"-0"+cha;
							}else{
								month1=endYear+"-"+cha;
							}
						}
						Integer newrt = 0;
						if(resultMap.get(month1.toString())==null){
							newrt = 0;
						}else{
							String newrtString= resultMap.get(month1.toString()).toString();
							newrt = Integer.parseInt(newrtString);
						}
						xAxis=xAxis+month1+",";
						newshuju=newshuju+newrt+",";
					}
					
				}
				String xAxis1=xAxis;
				String newshuju1="["+newshuju.substring(0, newshuju.length()-1)+"]";
				map.put("xAxis", xAxis1);
				map.put("newshuju", newshuju1);
			}
		}catch(ParseException e1){
			map.put("respCode", -1);
			map.put("respInfo","请确认日期格式是否为yyyy-MM-dd,去除中间多余的空格！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getCheckInInfoByPhoneNumber(
			Integer pageSize, Integer pageIndex, String phoneNumber) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = userCheckinDao.getUserCheckinSize2(phoneNumber);
				Integer start = (pageIndex-1)*pageSize;
		ArrayList<UserCheckin> uc=userCheckinDao.getUserCheckin2(phoneNumber,pageSize, start);
		ArrayList<UserCheckinVo> ucv=new ArrayList<UserCheckinVo>();
		for(UserCheckin u:uc){
			ucv.add(new UserCheckinVo(u));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows",ucv);
		return resultMap;
	}

}
