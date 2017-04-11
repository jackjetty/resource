package com.traffic.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.TipDao;
import com.traffic.pojo.TbTip;
import com.traffic.web.service.TbTipService;

@Service
public class TbTipServiceImpl implements TbTipService{
	
	@Autowired
	TipDao tipDao;

	@Override
	public HashMap<String, Object> getTbTipRecordNum(String processId,
			String tipType) {
		HashMap<String,Object> result = new HashMap<String, Object>();
		ArrayList<TbTip> tt=(ArrayList<TbTip>) tipDao.findByHQL("from TbTip where processId =? and tipType =? and haveTip =false", processId,tipType);
		String recordNo="";
		if(tt.size()>0){
			for(TbTip t:tt){
				recordNo+=","+t.getRecordNo();
				t.setHaveTip(true);
				tipDao.update(t);
				
			}
			result.put("code", 0);
			result.put("recordNo", recordNo.substring(1, recordNo.length()));
		}else{
			result.put("code", 1);
		}
		return result;
	}

}
