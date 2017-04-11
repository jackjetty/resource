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
import com.rising.management.bean.AppVersion;
import com.rising.management.bean.Manager;
import com.rising.management.bean.OperateRecord;
import com.rising.management.dao.AppVersionDao;
import com.rising.management.service.AppVersionService;
import com.rising.management.vo.AppVersionVo;

@Service("appVersionService")
public class AppVersionServiceImpl extends BaseServiceImpl implements AppVersionService {
	
	Log log = LogFactory.getLog(AppVersionServiceImpl.class);
	
	@Autowired  AppVersionDao appVersionDao;

	@Override
	public HashMap<String, Object> getAppVersionByPage(Integer pageIndex,
			Integer pageSize) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = appVersionDao.getAppVersionListSize();
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<AppVersionVo> aav = new ArrayList<AppVersionVo>();
		ArrayList<AppVersion> am = appVersionDao
				.getAppVersion(start, pageSize);
		for (AppVersion appVersion : am) {
			aav.add(new AppVersionVo(appVersion));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", aav);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addAppVersion(AppVersion a) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager operateManager = (Manager) session.get("Manager");
		try{
			appVersionDao.addAppVersion(a);
			resultMap.put("respInfo", "添加新App版本信息成功！");
			resultMap.put("respCode", 0);
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("app版本添加");
			record.setResult("成功");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",添加app版本"+a.getVersionName()+",添加成功");
			sysOperateLogDao.save(record);
		}catch(Exception e){
			log.error("添加新App版本信息时出现异常！"+e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加新App版本信息时出现异常！");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("app版本添加");
			record.setResult("失败");
			record.setCause("数据库操作出现异常");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",添加app版本"+a.getVersionName()+",添加失败,原因：数据库操作出现异常");
			sysOperateLogDao.save(record);
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> deleteByIds(String appVIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String[] ids = appVIds.split(",");
		String lastId = "";
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager operateManager = (Manager) session.get("Manager");
		try {
			for (String id : ids) {
				lastId = id;
				if (!"".equals(id)) {
					appVersionDao.deleteById(Integer.parseInt(id));
				}
			}
			result.put("respInfo", "删除app版本信息成功!");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("app版本删除");
			record.setResult("成功");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",删除app版本id=" + lastId + ",删除成功");
			sysOperateLogDao.save(record);
		} catch (Exception e) {
			log.error("删除id为" + lastId + "的app版本信息时出现异常!" + e.toString());
			e.printStackTrace();
			result.put("respInfo", "删除app版本信息时出现异常!");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("app版本删除");
			record.setResult("失败");
			record.setCause("数据库操作出现异常");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",删除app版本id=" + lastId + ",删除失败,原因：数据库操作出现异常");
			sysOperateLogDao.save(record);
		}
		return result;
	}

	@Override
	public HashMap<String, Object> updateAppVersion(AppVersion a) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager operateManager = (Manager) session.get("Manager");
		try{
			appVersionDao.updateAppVersion(a);
			resultMap.put("respInfo", "更新App版本信息成功！");
			resultMap.put("respCode", 0);
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("app版本信息更新");
			record.setResult("成功");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",更新app版本信息"+a.getVersionName()+",更新成功");
			sysOperateLogDao.save(record);
		}catch(Exception e){
			log.error("更新App版本信息时出现异常！"+e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "更新App版本信息时出现异常！");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("app版本信息更新");
			record.setResult("失败");
			record.setCause("数据库操作出现异常");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",更新app版本信息"+a.getVersionName()+",更新失败,原因：数据库操作出现异常");
			sysOperateLogDao.save(record);
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> exchangeStatusById(Integer appVId) {
		AppVersion a = appVersionDao.getAppVersionById(appVId);
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(a ==null){
			map.put("respInfo","要操作的App版本记录被删除了，请刷新后进行其他操作！");
			return map;
		}
		if("使用".equals(a.getRemark())){
			a.setRemark("禁用");
			appVersionDao.updateAppVersion(a);
			map.put("respInfo","修改App版本状态成功!");
		}else{
			a.setRemark("使用");
			appVersionDao.updateAppVersion(a);
			map.put("respInfo","修改App版本状态成功!");
		}
		return map;
	}

}
