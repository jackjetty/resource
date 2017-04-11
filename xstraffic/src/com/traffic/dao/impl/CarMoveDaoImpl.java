package com.traffic.dao.impl;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List; 
import org.springframework.stereotype.Repository;  

 
import com.traffic.dao.CarMoveDao;
import com.traffic.pojo.TbCarMove;
import com.traffic.util.CommonUtil;  
@Repository("carMoveDao")
public class CarMoveDaoImpl    extends BaseDaoImpl<TbCarMove> implements  CarMoveDao{
	@SuppressWarnings("unchecked")
	@Override
	public String  getNextCarMoveId (String prefix) { 
		String nextCarMoveId=prefix+CommonUtil.getDateForm("yyMMdd");
		int currentNum=0; 
		String hql = "select max(carMoveId) from TbCarMove where carMoveId like ? ";
		List list=this.findByHQL(hql, nextCarMoveId+"%"); 
		if(list.size()>0&&list.get(0)!=null){
			String maxCarMoveId= list.get(0).toString();
			currentNum=CommonUtil.getCurrentNum(maxCarMoveId,nextCarMoveId);
		}
		currentNum++;
		DecimalFormat aDecimalFormat = (DecimalFormat) DecimalFormat.getInstance();
        aDecimalFormat.applyPattern("0000");
        return nextCarMoveId+aDecimalFormat.format(currentNum);  
	} 
	public boolean isLimitReport(int interval,String carNumber){ 
		//int interval=Integer.parseInt(ProcessService.CARMOVE_LIMITTIME); 
		String hql="select count(*) from TbCarMove where carNumber=? and reportTime>=?";
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.MINUTE, -1*interval);
		Date date=cal.getTime();  
		List list=this.findByHQL(hql, carNumber,new Timestamp(date.getTime())); 
		if(list==null||list.size()==0)
			 return false;
		if(list.get(0)==null)
			 return false; 
		if(((Number) list.get(0)).intValue()>0)
			 return true;
		return false;   
	}

	 
}