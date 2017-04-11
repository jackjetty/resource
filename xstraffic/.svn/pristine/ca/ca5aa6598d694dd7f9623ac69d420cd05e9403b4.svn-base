package com.traffic.dao;
import java.util.ArrayList;
import java.util.Date;

import com.traffic.dao.BaseDao;
import com.traffic.pojo.TbBakPic;
public interface BakPicDao extends BaseDao<TbBakPic> { 
		 public TbBakPic findByUrl(String picUrl);
		 public Integer getBakPicSize(Date startDate, Date endDate);

			public ArrayList<TbBakPic> getBakPic(Date startDate, Date endDate,
					Integer start, Integer pageSize);

			public void deleteBakPic(ArrayList<Integer> is);
}