package com.rising.management.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.rising.management.bean.Manager;
import com.rising.management.bean.OperateRecord;
import com.rising.management.bean.Role;
import com.rising.management.common.MD5;
import com.rising.management.dao.ManagerDao;
import com.rising.management.dao.ManagerRoleDao;
import com.rising.management.service.ManagerService;

@Service("managerService")
public class ManagerServiceImpl extends BaseServiceImpl implements ManagerService {

	Log log = LogFactory.getLog(ManagerServiceImpl.class);

	@Autowired
	ManagerDao managerDao;
	
	@Autowired ManagerRoleDao managerRoleDao;

	@Override
	public HashMap<String, Object> isManagerValidate(String username,
			String password,String IP) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Manager m = managerDao.findManagerByName(username);
		if (m == null) {
			result.put("respCode", -2);
			result.put("respInfo", "该用户不存在！");
			OperateRecord record = new OperateRecord();
			record.setManager(username);
			record.setOperateType("用户登录");
			record.setResult("失败");
			record.setCause("该用户不存在");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+username +",IP:"+IP+",登录失败,原因：用户不存在");
			sysOperateLogDao.save(record);
		} else {
			try {
				if (m.getPassword().equals(MD5.encryptByMD5With16Bit(password))) {
					result.put("respCode", 0);
					result.put("respInfo", "登录成功！");
					Map<String, Object> session = ActionContext.getContext()
							.getSession();
					HashMap<String,Object> map = new HashMap<String, Object>();
					Role role = managerRoleDao.getRoleManagerId(m.getManagerId());
					if(role != null){
						map.put("role", role.getName());
					}
					session.put("Manager", m);
					session.put("ManagerRole", map);
					ServletActionContext.getRequest().getSession().setAttribute("managerUser", m.getName());
					OperateRecord record = new OperateRecord();
					record.setManager(username);
					record.setOperateType("用户登录");
					record.setResult("成功");
					record.setOperateTime(new Date());
					record.setOperateContent("用户："+username +",IP:"+IP+",登录成功");
					sysOperateLogDao.save(record);
				} else {
					result.put("respCode", -3);
					result.put("respInfo", "用户密码错误！");
					OperateRecord record = new OperateRecord();
					record.setManager(username);
					record.setOperateType("用户登录");
					record.setResult("失败");
					record.setCause("用户密码错误");
					record.setOperateTime(new Date());
					record.setOperateContent("用户："+username +",IP:"+IP+",登录失败,原因：用户密码错误");
					sysOperateLogDao.save(record);
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public HashMap<String, Object> modifyPassword(Manager m) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			managerDao.modifyPassword(m);
			resultMap.put("respCode", 0);
			resultMap.put("respInfo", "修改密码成功！");
		} catch (Exception e) {
			log.error("修改密码时发生异常！" + e.toString());
			resultMap.put("respInfo", "修改密码时发生异常！");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> deleteById(String managerIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String[] ids = managerIds.split(",");
		String lastId = "";
		try {
			for (String id : ids) {
				lastId = id;
				if (!"".equals(id)) {
					managerDao.deleteById(Integer.parseInt(id));
				}
			}
			result.put("respInfo", "删除用户成功!");
		} catch (Exception e) {
			log.error("删除用户id为" + lastId + "的用户时出现异常!" + e.toString());
			e.printStackTrace();
			result.put("respInfo", "删除用户时出现异常!");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> getManagerByPage(String username,
			Integer pageSize, Integer pageIndex) {
		if ("".equals(username)) {
			username = null;
		}
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = managerDao.getManagerListSize(username);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<Manager> am = managerDao
				.getManager(username, start, pageSize);
		resultMap.put("listSize", listSize);
		resultMap.put("rows", am);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addUser(Manager m) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager operateManager = (Manager) session.get("Manager");
		try {
			m.setPassword(MD5.encryptByMD5With16Bit(m.getPassword()));
			managerDao.addManager(m);
			resultMap.put("respInfo", "添加用户成功");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("添加用户");
			record.setResult("成功");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",添加用户名为\""+m.getName()+"\"的用户,添加成功");
			sysOperateLogDao.save(record);
		} catch (Exception e) {
			log.error("添加用户时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "添加用户时出现异常！");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("添加用户");
			record.setResult("失败");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",添加用户名为\""+m.getName()+"\"的用户,添加失败,原因：数据库操作出现异常");
			sysOperateLogDao.save(record);
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> updateUser(Manager m) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			managerDao.updateManager(m);
			map.put("respInfo", "修改用户信息成功！");
		} catch (Exception e) {
			log.error("修改用户信息时出现异常！" + e.toString());
			e.printStackTrace();
			map.put("respInfo", "修改用户信息时出现异常！");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> isManagerValidate(String username,
			String password, String captcha,String IP) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		String captchaCode = (String) session.get("captchaCode");
		if (captcha != null && captcha.equals(captchaCode)) {
			result = isManagerValidate(username,password,IP);
		} else {
			result.put("respCode", -1);
			result.put("respInfo", "验证码错误！");
			OperateRecord record = new OperateRecord();
			record.setManager(username);
			record.setOperateType("用户登录");
			record.setResult("失败");
			record.setCause("验证码错误");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+username +",IP:"+IP+",登录失败,原因：验证码错误");
			sysOperateLogDao.save(record);
		}
		return result;
	}

	@Override
	public HashMap<String, Object> modifyPassword(String password,
			String newPassword) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager m = (Manager) session.get("Manager");
		HashMap<String,Object> result = new HashMap<String, Object>();
		try {
			if (MD5.encryptByMD5With16Bit(password).equals(m.getPassword())) {
				try {
					m.setPassword(MD5.encryptByMD5With16Bit(newPassword));
					managerDao.modifyPassword(m);
					result.put("respCode", 0);
					result.put("respInfo", "修改密码成功！");
					OperateRecord record = new OperateRecord();
					record.setManager(m.getName());
					record.setOperateType("修改登录密码");
					record.setResult("成功");
					record.setOperateTime(new Date());
					record.setOperateContent("用户："+m.getName() +",修改密码,成功");
					sysOperateLogDao.save(record);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}catch(Exception e){
					OperateRecord record = new OperateRecord();
					record.setManager(m.getName());
					record.setOperateType("修改登录密码");
					record.setResult("失败");
					record.setCause("数据库操作出现异常");
					record.setOperateTime(new Date());
					record.setOperateContent("用户："+m.getName() +",修改密码失败,原因:数据库操作出现异常");
					sysOperateLogDao.save(record);
					e.printStackTrace();
				}
				
			} else {
				result.put("respCode", -1);
				result.put("respInfo", "原密码错误！");
				OperateRecord record = new OperateRecord();
				record.setManager(m.getName());
				record.setOperateType("修改登录密码");
				record.setResult("失败");
				record.setCause("原密码错误");
				record.setOperateTime(new Date());
				record.setOperateContent("用户："+m.getName() +",修改密码失败,原因：原密码错误");
				sysOperateLogDao.save(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
