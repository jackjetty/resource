package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.rising.management.bean.Manager;
import com.rising.management.bean.OperateRecord;
import com.rising.management.bean.PhoneRound;
import com.rising.management.dao.PhoneRoundDao;
import com.rising.management.service.PhoneRoundService;

@Service("phoneRoundService")
public class PhoneRoundServiceImpl extends BaseServiceImpl implements PhoneRoundService {
	Log log = LogFactory.getLog(PhoneRoundServiceImpl.class);
	@Autowired PhoneRoundDao phoneRoundDao;
	@Override
	public HashMap<String, Object> getPhoneRoundService(Integer pageSize,Integer pageIndex) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		Integer listSize = phoneRoundDao.getPhoneRoundListSize();
		Integer start = (pageIndex-1)*pageSize;
		ArrayList<PhoneRound> pr = phoneRoundDao.getPhoneRound(start,pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", pr);
		return resultMap;
	}
	@Override
	public HashMap<String, Object> updatePhoneRound(String status, Integer id) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager operateManager = (Manager) session.get("Manager");
		try {
			phoneRoundDao.updatePhoneRound(status,id);
			resultMap.put("success", true);
			resultMap.put("respInfo", "修改状态信息成功！");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("电信手机号段修改");
			record.setResult("成功");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",修改电信手机号段id="+id+",修改成功");
			sysOperateLogDao.save(record);
		} catch (Exception e) {
			log.error("修改状态信息时发生异常!"+e.toString());
			resultMap.put("respInfo", "修改状态信息时发生异常！");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("电信手机号段修改");
			record.setResult("失败");
			record.setCause("数据库操作出现异常");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",修改电信手机号段id="+id+",修改失败,原因：数据库操作出现异常");
			sysOperateLogDao.save(record);
		}
		return resultMap;
	}
	@Override
	public HashMap<String, Object> addPhoneRound(PhoneRound pr) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager operateManager = (Manager) session.get("Manager");
		try{
			ArrayList<PhoneRound> prr = phoneRoundDao.findAll();
			ArrayList<String> gpr = new ArrayList<String>();
			for(int i = 0;i<prr.size();i++){
				gpr.add(prr.get(i).getPhoneRound());
			}
			if(gpr.contains(pr.getPhoneRound())){
				resultMap.put("respInfo", "手机号段不能重复");
				OperateRecord record = new OperateRecord();
				record.setManager(operateManager.getName());
				record.setOperateType("电信手机号段添加");
				record.setResult("失败");
				record.setCause("手机号段重复");
				record.setOperateTime(new Date());
				record.setOperateContent("用户："+operateManager.getName() +",添加电信手机号段"+pr.getPhoneRound()+",添加失败，原因：手机号段重复");
				sysOperateLogDao.save(record);
			}else{
				phoneRoundDao.addPhoneRound(pr);
				resultMap.put("respInfo", "添加新手机号段信息成功！");
				resultMap.put("respCode", 0);
				OperateRecord record = new OperateRecord();
				record.setManager(operateManager.getName());
				record.setOperateType("电信手机号段添加");
				record.setResult("成功");
				record.setOperateTime(new Date());
				record.setOperateContent("用户："+operateManager.getName() +",添加电信手机号段"+pr.getPhoneRound()+",添加成功");
				sysOperateLogDao.save(record);
			}
			
			
		}catch(Exception e){
			log.error("添加新手机号段时出现异常！"+e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加新手机号段时出现异常！");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("电信手机号段添加");
			record.setResult("失败");
			record.setCause("数据库操作出现异常");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",添加电信手机号段"+pr.getPhoneRound()+",添加失败，原因：数据库操作异常");
			sysOperateLogDao.save(record);
		}
		return resultMap;

}
	@Override
	public HashMap<String, Object> deleteByIds(String ids) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		String[] idArray = ids.split(",");
		String lastId = "";
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager operateManager = (Manager) session.get("Manager");
		try{
			for(String idObj : idArray){
				lastId = idObj;
				if(!"".equals(idObj)){
					Integer idObj2 = Integer.parseInt(idObj);
					phoneRoundDao.deleteById(idObj2); 
					resultMap.put("respInfo", "删除产品信息成功!");
					OperateRecord record = new OperateRecord();
					record.setManager(operateManager.getName());
					record.setOperateType("电信手机号段删除");
					record.setResult("成功");
					record.setOperateTime(new Date());
					record.setOperateContent("用户："+operateManager.getName() +",删除电信手机号段id="+lastId+",删除成功");
					sysOperateLogDao.save(record);
				}
			}
		}catch(Exception e){
			log.error("删除id为"+lastId+"的产品信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "删除产品信息时出现异常!");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("电信手机号段删除");
			record.setResult("失败");
			record.setCause("数据库操作出现异常");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",删除电信手机号段id="+lastId+",删除失败,原因：数据库操作出现异常");
			sysOperateLogDao.save(record);
			
		}

		return resultMap;
	}
}
