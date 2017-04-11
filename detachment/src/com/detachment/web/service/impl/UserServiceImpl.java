package com.detachment.web.service.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.BaseDao;
import com.detachment.dao.DepartmentDao;
import com.detachment.dao.OperateRecordDao;
import com.detachment.dao.RoleDao;
import com.detachment.dao.UserDao;
import com.detachment.pojo.TbDepartment;
import com.detachment.pojo.TbRole;
import com.detachment.pojo.TbUser;
import com.detachment.pojo.vo.TbUserVo;
import com.detachment.util.MD5;
import com.detachment.web.service.UserService;
import com.opensymphony.xwork2.ActionContext;



@Service("userService")
public class UserServiceImpl implements UserService{
	Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	DepartmentDao departmentDao;
	@Autowired
	OperateRecordDao operateRecordDao; 
	@Override
	public HashMap<String, Object> addUser(TbUser user,String departmentId,String roleId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			user.setPassword(MD5.encryptByMD5With16Bit(user.getPassword()));
			user.setTbRole(roleDao.findById(roleId));
			user.setTbDepartment(departmentDao.findById(departmentId));
			userDao.addUser(user);
			resultMap.put("respInfo", "添加用户成功");
		} catch (Exception e) {
			log.error("添加用户时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "添加用户时出现异常！");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> isManagerValidate(String userId,
			String password, String captcha) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		String captchaCode = (String) session.get("captchaCode");
		if (captcha != null && captcha.equals(captchaCode)) {
			result = isManagerValidate(userId, password);
		} else {
			result.put("respCode", -1);
			result.put("respInfo", "验证码错误！");
			
		}
		return result;
	}
	
	
	public HashMap<String, Object> isManagerValidate(String userId,
			String password) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		TbUser u = userDao.findUserByUserId(userId);
		if (u == null) {
			result.put("respCode", -2);
			result.put("respInfo", "该用户不存在！");
		} else {
				if (u.getPassword().equals(MD5.encryptByMD5With16Bit(password))) {
					result.put("respCode", 0);
					result.put("respInfo", "登录成功！");
					Map<String, Object> session = ActionContext.getContext()
							.getSession();
					HashMap<String, Object> map = new HashMap<String, Object>();
					TbRole role = roleDao.findRoleById(u.getTbRole().getRoleId());
					if (role != null) {
						map.put("role", role.getRoleName());
						map.put("roleId", role.getRoleId());
					}
					session.put("User", u);
					session.put("UserRole", map);
					ServletActionContext.getRequest().getSession()
							.setAttribute("managerUser", u);
				
				} else {
					result.put("respCode", -3);
					result.put("respInfo", "用户密码错误！");
					
				}
		}
		return result;
	}

	@Override
	public HashMap<String, Object> modifyPassword(String password,
			String newPassword) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		TbUser u =  (TbUser) session.get("User");
		HashMap<String,Object> result = new HashMap<String, Object>();
			if (u.getPassword().equals(MD5.encryptByMD5With16Bit(password))) {
					u.setPassword(MD5.encryptByMD5With16Bit(newPassword));
					userDao.update(u);
					result.put("respCode", 0);
					result.put("respInfo", "修改密码成功！");
			} else {
				result.put("respCode", -1);
				result.put("respInfo", "原密码错误！");
			}
		return result;
	}

	@Override
	public HashMap<String, Object> getUser(String userId, Integer pageSize,
			Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if("".equals(userId)){
			userId=null;
		}
		Integer listSize=userDao.getUserSzie(userId);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbUser> tus=(ArrayList<TbUser>) userDao.getUser(userId, start, pageSize);
		ArrayList<TbUserVo> tusVo=new ArrayList<TbUserVo>();
		for(TbUser t:tus){
			TbDepartment td=departmentDao.findById(t.getTbDepartment().getDepartmentId());
			TbRole tr=roleDao.findById(t.getTbRole().getRoleId());
			TbUserVo tuvo=new TbUserVo(t,td,tr);
			tusVo.add(tuvo);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", tusVo);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> changeRoleIdByUserId(String userId,
			String roleId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			TbUser u=userDao.findById(userId);
			TbRole r=roleDao.findById(roleId);
			u.setTbRole(r);
			userDao.update(u);
			resultMap.put("respInfo", "修改角色成功");
		} catch (Exception e) {
			resultMap.put("respInfo", "修改角色失败");
		}
		
		return resultMap;
	}

	@Override
	public ArrayList<String> getAllUserId() {
		ArrayList<String> gproc = new ArrayList<String>();
		ArrayList<TbUser> userIds = userDao.getAllUser();
		for(int i = 0;i<userIds.size();i++){
			gproc.add(userIds.get(i).getUserId());
		}
		return gproc;
	}

	@Override
	public HashMap<String, Object> updateUser(TbUser user, String departmentId,
			String roleId, String oldUserId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			user.setTbRole(roleDao.findById(roleId));
			user.setTbDepartment(departmentDao.findById(departmentId));
			userDao.updateUser(user, oldUserId);
			resultMap.put("respInfo", "修改用户成功");
		} catch (Exception e) {
			log.error("添加用户时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respInfo", "修改用户时出现异常！");
		}
		return resultMap;
	}

	@Override
	public TbUser getUserByUserId(String userId) {
		TbUser user=userDao.findById(userId);
		return user;
	}

	@Override
	public HashMap<String, Object> deleteUser(String userIds) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = userIds.split(",");
		try {
			for(String idObj : idArray){
				if(!"".equals(idObj)){
					userDao.delete(userDao.findById(idObj)); 
					resultMap.put("respInfo", "删除用户信息成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("respInfo", "删除用户信息时出现异常!");
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> getFirst(String userIds) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String[] idArray = userIds.split(",");
		try {
			for(String idObj : idArray){
				if(!"".equals(idObj)){
					userDao.FirstPassword(idObj, MD5.encryptByMD5With16Bit("123456"));
					resultMap.put("respCode", 0);
					resultMap.put("respInfo", "初始化用户密码成功!");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("respInfo", "初始化用户密码时出现异常!");
		}
		return resultMap;
	}

	@Override
	public Integer getUserSizeTest(String userId) {
		String hql="";
		Integer listSize=0;
		if("".equals(userId)){
//			System.out.println("是否进入到判断是否为null");
			hql="select count(*) from TbUser";
//			userId=null;
//			
//			System.out.println((userId==null)+"在serviceImpl中判断userId是否为空");
			listSize=userDao.findCount(hql);
		}else{
			hql="select count(*) from TbUser where userId=?";
			listSize=userDao.findCount(hql,userId);
		}
		
		return listSize;
	}

}
