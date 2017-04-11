package com.traffic.web.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.dao.ManagerDao;
import com.traffic.dao.ManagerRoleDao;
import com.traffic.dao.RoleDao;
import com.traffic.dao.UserDao;
import com.traffic.dto.UserDto;
import com.traffic.pojo.TbOperateRecord;
import com.traffic.pojo.TbUser;
import com.traffic.util.MD5;
import com.traffic.web.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	Log log = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	UserDao userDao;
	@Autowired
	ManagerDao managerDao;
	@Autowired
	ManagerRoleDao managerRoleDao;
	@Autowired
	RoleDao roleDao;
	

	@Override
	public HashMap<String, Object> getUser(String userId, Integer pageSize,
			Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if ("".equals(userId)) {
			userId = null;
		}
		Integer listSize = userDao.getUserSize(userId);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbUser> tu = userDao.getUser(userId, start, pageSize);
		HashMap<String, Object> pic =  roleDao.getRoleNameById();
		ArrayList<UserDto> tav = new ArrayList<UserDto>();
		for(int i = 0;tu != null && i < tu.size();i++){
			UserDto tuv = new UserDto(tu.get(i),pic);
			tav.add(tuv);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", tav);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addUser(TbUser tu) {
		ArrayList<String> gui = new ArrayList<String>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			ArrayList<TbUser> ui = userDao.getAll();
			for (int i = 0; i < ui.size(); i++) {
				gui.add(ui.get(i).getUserId());
			}
			if (gui.contains(tu.getUserId())) {
				resultMap.put("respInfo", "用户名已存在");
			} else {
				tu.setUserPassword(MD5.encryptByMD5With16Bit(tu.getUserPassword()));
				userDao.addUser(tu);
				resultMap.put("respCode", 0);
				resultMap.put("respInfo", "添加新用户成功");
				TbOperateRecord record = new TbOperateRecord();
				//record.setOperateId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
				record.setManager(tu.getUserId());
				record.setOperateType("添加新用户");
				record.setResult("成功");
				record.setOperateTime(new Timestamp(new Date().getTime()));
				record.setOperateContent("添加用户名为\""+tu.getUserId()+"\"的用户,添加成功");
				tbOperateLogDao.save(record);
			}

		} catch (Exception e) {
			log.error("添加新用户时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加新用户时出现异常");
			TbOperateRecord record = new TbOperateRecord();
			//record.setOperateId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			record.setManager(tu.getUserId());
			record.setOperateType("添加新用户");
			record.setResult("失败");
			record.setOperateTime(new Timestamp(new Date().getTime()));
			record.setOperateContent("添加用户名为\""+tu.getUserId()+"\"的用户,添加失败,原因：数据库操作出现异常");
			tbOperateLogDao.save(record);
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> updateUser(TbUser tu,String oldUserId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
				userDao.updateUser(tu,oldUserId);
				resultMap.put("respInfo", "更新用户时信息成功！");
				resultMap.put("respCode", 0);
		} catch (Exception e) {
			log.error("修改用户信息时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "修改用户信息时出现异常");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> deleteUser(String userIds) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = userIds.split(",");
		String lastId = "";
		try {
			for(String idObj : idArray){
				lastId = idObj;
				if(!"".equals(idObj)){
					managerRoleDao.deleteById(idObj);
					userDao.deleteById(idObj); 
					resultMap.put("respInfo", "删除用户信息成功!");
				}
			}
			
		} catch (Exception e) {
			log.error("删除id为"+lastId+"的用户信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "删除用户信息时出现异常!");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> getFirst(String userIds) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = userIds.split(",");
		String lastId = "";
		try {
			for(String idObj : idArray){
				lastId = idObj;
				if(!"".equals(idObj)){
					TbUser m = userDao.getUserById(idObj);
					m.setUserPassword(MD5.encryptByMD5With16Bit("123456"));
					managerDao.modifyPassword(m);
					resultMap.put("respCode", 0);
					resultMap.put("respInfo", "初始化用户密码成功!");
					TbOperateRecord record = new TbOperateRecord();
					//record.setOperateId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
					record.setManager(idObj);
					record.setOperateType("初始化密码");
					record.setResult("成功");
					record.setOperateTime(new Timestamp(new Date().getTime()));
					record.setOperateContent("初始化用户名为\""+idObj+"\"的用户密码成功");
					tbOperateLogDao.save(record);
				}
			}
			
		} catch (Exception e) {
			log.error("初始化用户名为"+lastId+"的用户信息时出现异常!"+e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "初始化用户密码时出现异常!");
			TbOperateRecord record = new TbOperateRecord();
			//record.setOperateId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			record.setManager(lastId);
			record.setOperateType("初始化密码");
			record.setResult("失败");
			record.setOperateTime(new Timestamp(new Date().getTime()));
			record.setOperateContent("初始化用户名为\""+lastId+"\"的用户密码失败,原因：数据库操作出现异常");
			tbOperateLogDao.save(record);
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> exchangeStatus(String userId) {
		TbUser a = userDao.getUserById(userId);
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(a ==null){
			map.put("respInfo","要操作的用户记录被删除了，请刷新后进行其他操作！");
			TbOperateRecord record = new TbOperateRecord();
			//record.setOperateId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			record.setManager(userId);
			record.setOperateType("操作用户状态");
			record.setResult("失败");
			record.setOperateTime(new Timestamp(new Date().getTime()));
			record.setOperateContent("操作用户名为\""+userId+"\"的用户失败,原因：要操作的用户记录被删除了");
			tbOperateLogDao.save(record);
			return map;
		}
		if("正常".equals(a.getStatus())){
			a.setStatus("冻结");
			userDao.updateUserStatus(a);
			map.put("respInfo","成功冻结用户!");
			TbOperateRecord record = new TbOperateRecord();
			//record.setOperateId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			record.setManager(userId);
			record.setOperateType("操作用户状态");
			record.setResult("成功");
			record.setOperateTime(new Timestamp(new Date().getTime()));
			record.setOperateContent("冻结用户名为\""+userId+"\"的用户成功");
			tbOperateLogDao.save(record);
		}else{
			a.setStatus("正常");
			userDao.updateUserStatus(a);
			map.put("respInfo","成功解冻用户!");
			TbOperateRecord record = new TbOperateRecord();
			//record.setOperateId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			record.setManager(userId);
			record.setOperateType("操作用户状态");
			record.setResult("成功");
			record.setOperateTime(new Timestamp(new Date().getTime()));
			record.setOperateContent("解冻用户名为\""+userId+"\"的用户成功");
			tbOperateLogDao.save(record);
		}
		return map;
	}
	public HashMap<String, Object> exchangeStatus1(String userId) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		TbUser a = userDao.getUserById(userId);
		if(a ==null){
			map.put("respInfo","要操作的用户记录被删除了，请刷新后进行其他操作！");
			TbOperateRecord record = new TbOperateRecord();
			//record.setOperateId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			record.setManager(userId);
			record.setOperateType("操作用户状态");
			record.setResult("失败");
			record.setOperateTime(new Timestamp(new Date().getTime()));
			record.setOperateContent("操作用户名为\""+userId+"\"的用户失败,原因：要操作的用户记录被删除了");
			tbOperateLogDao.save(record);
			return map;
		}
		a.setSimplifyProcess(a.getSimplifyProcess()==null?false:a.getSimplifyProcess());
		if(a.getSimplifyProcess()==true){
			a.setSimplifyProcess(false);
			userDao.updateUserStatus(a);
			map.put("respInfo","成功取消快速处理!");
			
		}else{
			a.setSimplifyProcess(true);
			userDao.updateUserStatus(a);
			map.put("respInfo","成功启用快速处理!");
		}
		return map;
		 
		
	}

	@Override
	public ArrayList<String> getAllUserId() {
		ArrayList<String> gproc = new ArrayList<String>();
		ArrayList<TbUser> userIds = userDao.getUserIds();
		for(int i = 0;i<userIds.size();i++){
			gproc.add(userIds.get(i).getUserId());
		}
		return gproc;
	}

	@Override
	public HashMap<String, Object> getPhoneNumberByOpenId(String openId) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		TbUser a = userDao.getUserByOpenId(openId);
		if(a==null){
			map.put("phoneNumber", "");
		}else{
			map.put("phoneNumber", a.getPhoneNumber());
		}
		return map;
	}

}
