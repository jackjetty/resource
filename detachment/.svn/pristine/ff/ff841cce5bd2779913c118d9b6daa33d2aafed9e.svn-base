package com.detachment.wei.service.impl; 
import java.text.DecimalFormat;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 

import com.detachment.dao.MonitorDao;
import com.detachment.dao.PlaceAreaDao;
import com.detachment.dao.PlaceDao;
import com.detachment.dao.UserDao;
import com.detachment.math.PointMath;
import com.detachment.pojo.TbMonitor;
import com.detachment.pojo.TbPlace;
import com.detachment.util.AttributionUtil;
import com.detachment.util.CommonUtil;
import com.detachment.util.GpsToBaiDu;
import com.detachment.wei.service.AttributionService;
@Service("attributionService")
public class  AttributionServiceImpl implements AttributionService{ 
	@Autowired
	private PlaceDao placeDao; 
	@Autowired
	private MonitorDao monitorDao; 
	@Autowired
	private PlaceAreaDao placeAreaDao; 
	
	public String getAttributionDepartment(String locationX,String locationY) {
		float  destLatitude=Float.parseFloat(locationX);
		float  destLongitude=Float.parseFloat(locationY);
		//120.60402713049==30.132707134092
				
		 //String mapResult = GpsToBaiDu.ToBaiduAddress(destLongitude, destLatitude);
		 String mapResult =destLongitude+"=="+destLatitude;
		if(CommonUtil.trim(mapResult).equalsIgnoreCase(""))
			 return null;
		 
		destLongitude=Float.parseFloat(mapResult.split("==")[0]);
		destLatitude=Float.parseFloat (mapResult.split("==")[1]);  
		PointMath destPoint=new PointMath();
		destPoint.setPointX(destLatitude);
		destPoint.setPointY(destLongitude) ;
		String hql="from TbPlace order by placeId ";
		List <TbPlace> placeList=placeDao.findByHQL(hql); 
		List<PointMath> pointLine;
		int placeId;
		 
		for(TbPlace tbPlace:placeList){
			placeId=tbPlace.getPlaceId();
			pointLine=placeAreaDao.getPointLine(placeId); 
			if(AttributionUtil.InPolygon(pointLine, destPoint)==0)  
			              continue;
			switch(placeId){
			     case 1:
			    	  return "DP002";
			     case 2:
			    	  return "DP006"; 	
			     case 3:
			    	  return "DP005";
			     case 4:
			    	  return "DP003"; 
			     case 6:
			    	  return "DP004"; 	  
			}
			break;    
		} 
		return null;
	}
	public String  tranPoiLocation(){
		String hql="from TbMonitor ";
		List<TbMonitor> monitorList=monitorDao.findByHQL(hql  );
		
		String longitude;
		String latitude;
		double lng,ggLng;
		double lat,ggLat;
		double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
		double x,y,z,theta;
		for(TbMonitor tbMonitor:monitorList){
			longitude=tbMonitor.getLongitude();
			latitude=tbMonitor.getLatitude();
			lng=Double.parseDouble(longitude);
			lat=Double.parseDouble(latitude);
			 
			 x = lng - 0.0065;
			 y = lat - 0.006;  
			 z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);  
			 theta =  Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);  
			 ggLng = z * Math.cos(theta);  
			 ggLat = z * Math.sin(theta);
			 DecimalFormat aDecimalFormat = (DecimalFormat) DecimalFormat .getInstance();
			 aDecimalFormat.applyPattern("#0.00000000000");
			 tbMonitor.setLatitude(aDecimalFormat.format(ggLat));
			 tbMonitor.setLongitude(aDecimalFormat.format(ggLng));
			 monitorDao.saveOrUpdate(tbMonitor);
			 
			
		}
		return "success";
		
		
		
	}
}
