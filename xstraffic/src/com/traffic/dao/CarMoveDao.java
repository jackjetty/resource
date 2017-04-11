package com.traffic.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap; 
import com.traffic.pojo.TbCarMove; 
public interface CarMoveDao extends BaseDao<TbCarMove> {
	public String  getNextCarMoveId (String prefix); 
	public boolean isLimitReport(int interval,String carNumber);
}