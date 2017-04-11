package com.traffic.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap; 
import com.traffic.pojo.TbHandyPhoto; 
public interface HandyPhotoDao extends BaseDao<TbHandyPhoto> {
	public String  getNextHandyPhotoId (String prefix); 
	 
}