package com.detachment.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.detachment.dao.TipDao;
import com.detachment.dao.UserDao;
import com.detachment.pojo.TbTip;
import com.detachment.pojo.TbUser;
import com.detachment.web.service.TbTipService;
@Service
public class TbTipServiceImpl implements TbTipService{
	
	@Autowired
	TipDao tipDao;
//	@Autowired
//	UserDao userDao;
//	
//	@Override
//	public HashMap<String, Object> getTbTipRecordNum(String processId,
//			String tipType) {
//		HashMap<String,Object> result = new HashMap<String, Object>();
//		TbUser u = (TbUser) ServletActionContext.getRequest().getSession()
//				.getAttribute("managerUser");
//		ArrayList<TbTip> tt=(ArrayList<TbTip>) tipDao.findByHQL("from TbTip where processId =? and tipType =? and departmentId =?", processId,tipType,u.getTbDepartment().getDepartmentId());
//		if(tt.size()==0){
//			result.put("num", 0);
//		}else{
//			result.put("num", tt.get(0).getRecordNum());
//		}
//		return result;
//	}
//
//	@Override
//	public HashMap<String, Object> updateRecordNum(String processId,
//			String tipType) {
//		HashMap<String,Object> result = new HashMap<String, Object>();
//		TbUser u = (TbUser) ServletActionContext.getRequest().getSession()
//				.getAttribute("managerUser");
//		tipDao.updateRecordNum(processId, tipType, u.getTbDepartment().getDepartmentId());
//		result.put("code", 1);
//		return result;
//	}

	@Override
	public HashMap<String, Object> getTbTipRecordNum(String processId,
			String tipType) {
		HashMap<String,Object> result = new HashMap<String, Object>();
		TbUser u = (TbUser) ServletActionContext.getRequest().getSession()
				.getAttribute("managerUser");
		ArrayList<TbTip> tt=(ArrayList<TbTip>) tipDao.findByHQL("from TbTip where processId =? and tipType =? and departmentId =? and haveTip =false", processId,tipType,u.getTbDepartment().getDepartmentId());
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
