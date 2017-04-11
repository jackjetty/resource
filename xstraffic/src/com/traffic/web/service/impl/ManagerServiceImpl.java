package com.traffic.web.service.impl;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opensymphony.xwork2.ActionContext;
import com.traffic.dao.ManagerDao;
import com.traffic.dao.ManagerRoleDao;
import com.traffic.pojo.TbOperateRecord;
import com.traffic.pojo.TbRole;
import com.traffic.pojo.TbUser;
import com.traffic.util.MD5;
import com.traffic.web.service.ManagerService;
@Service("managerService")
public class ManagerServiceImpl extends BaseServiceImpl implements ManagerService  {
	Log log = LogFactory.getLog(ManagerServiceImpl.class);
	@Autowired
	ManagerDao managerDao;
	@Autowired
	ManagerRoleDao managerRoleDao;
	@Override
	public HashMap<String, Object> isManagerValidate(String username,
			String password,String IP) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		TbUser m = managerDao.findManagerByName(username);
		if (m == null) {
			result.put("respCode", -2);
			result.put("respInfo", "该用户不存在！");
			TbOperateRecord record = new TbOperateRecord();
//			String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//			record.setOperateId(str);
			record.setManager(username);
			record.setOperateType("用户登录");
			record.setResult("失败");
			record.setCause("该用户不存在");
			record.setOperateTime(new Timestamp(new Date().getTime()));
			record.setOperateContent("用户："+username +",IP:"+IP+",登录失败,原因：用户不存在");
			tbOperateLogDao.save(record);
		} else {
			try {
				if("正常".equals(m.getStatus())){
				if (m.getUserPassword().equals(MD5.encryptByMD5With16Bit(password))) {
					result.put("respCode", 0);
					result.put("respInfo", "登录成功！");
					Map<String, Object> session = ActionContext.getContext()
							.getSession();
					HashMap<String,Object> map = new HashMap<String, Object>();
					TbRole role = managerRoleDao.getRoleRoleId(username);
					if(role != null){
						map.put("role", role.getRoleName());
					}
					session.put("Manager", m);
					session.put("ManagerRole", map);
					ServletActionContext.getRequest().getSession().setAttribute("managerUser", m.getUserId());
					//ServletActionContext.getServletContext().setAttribute("managerUser", m.getUserId());
					//ServletActionContext.getSession().setAttribute("managerUser", m.getUserId());
					TbOperateRecord record = new TbOperateRecord();
//					String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//					record.setOperateId(str);
					record.setManager(username);
					record.setOperateType("用户登录");
					record.setResult("成功");
					record.setOperateTime(new Timestamp(new Date().getTime()));
					record.setOperateContent("用户："+username +",IP:"+IP+",登录成功");
					tbOperateLogDao.save(record);
				} else {
					result.put("respCode", -3);
					result.put("respInfo", "用户密码错误！");
					TbOperateRecord record = new TbOperateRecord();
//					String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//					record.setOperateId(str);
					record.setManager(username);
					record.setOperateType("用户登录");
					record.setResult("失败");
					record.setCause("用户密码错误");
					record.setOperateTime(new Timestamp(new Date().getTime()));
					record.setOperateContent("用户："+username +",IP:"+IP+",登录失败,原因：用户密码错误");
					tbOperateLogDao.save(record);
				}
				}else{
					result.put("respInfo", "用户已被冻结,请联系管理员解冻！");
					TbOperateRecord record = new TbOperateRecord();
//					String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//					record.setOperateId(str);
					record.setManager(username);
					record.setOperateType("用户登录");
					record.setResult("失败");
					record.setCause("用户已被冻结");
					record.setOperateTime(new Timestamp(new Date().getTime()));
					record.setOperateContent("用户："+username +",IP:"+IP+",登录失败,原因：用户已被冻结");
					tbOperateLogDao.save(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public HashMap<String, Object> isManagerValidate(String username,
			String password,String captcha,String IP) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		String captchaCode = (String) session.get("captchaCode");
		if (captcha != null && captcha.equals(captchaCode)) {
			result = isManagerValidate(username,password,IP);
		} else {
			result.put("respCode", -1);
			result.put("respInfo", "验证码错误！");
			TbOperateRecord record = new TbOperateRecord();
//			String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//			record.setOperateId(str);
			record.setManager(username);
			record.setOperateType("用户登录");
			record.setResult("失败");
			record.setCause("验证码错误");
			record.setOperateTime(new Timestamp(new Date().getTime()));
			record.setOperateContent("用户："+username +",IP:"+IP+",登录失败,原因：验证码错误");
			tbOperateLogDao.save(record);
		}
		return result;
	}
	@Override
	public HashMap<String, Object> modifyPassword(String password,
			String newPassword) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		TbUser m = (TbUser) session.get("Manager");
		HashMap<String,Object> result = new HashMap<String, Object>();
		try {
			if (m.getUserPassword().equals(MD5.encryptByMD5With16Bit(password))) {
				try {
					m.setUserPassword(MD5.encryptByMD5With16Bit(newPassword));
					managerDao.modifyPassword(m);
					result.put("respCode", 0);
					result.put("respInfo", "修改密码成功！");
					TbOperateRecord record = new TbOperateRecord();
//					String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//					record.setOperateId(str);
					record.setManager(m.getUserId());
					record.setOperateType("修改登录密码");
					record.setResult("成功");
					record.setOperateTime(new Timestamp(new Date().getTime()));
					record.setOperateContent("用户："+m.getUserId() +",修改密码,成功");
					tbOperateLogDao.save(record);
				}catch(NoSuchAlgorithmException e){
					e.printStackTrace();
				}catch(Exception e){
					TbOperateRecord record = new TbOperateRecord();
//					String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//					record.setOperateId(str);
					record.setManager(m.getUserId());
					record.setOperateType("修改登录密码");
					record.setResult("失败");
					record.setCause("数据库操作出现异常");
					record.setOperateTime(new Timestamp(new Date().getTime()));
					record.setOperateContent("用户："+m.getUserId() +",修改密码失败,原因:数据库操作出现异常");
					tbOperateLogDao.save(record);
					e.printStackTrace();
				}
			} else {
				result.put("respCode", -1);
				result.put("respInfo", "原密码错误！");
				TbOperateRecord record = new TbOperateRecord();
//				String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//				record.setOperateId(str);
				record.setManager(m.getUserId());
				record.setOperateType("修改登录密码");
				record.setResult("失败");
				record.setCause("原密码错误");
				record.setOperateTime(new Timestamp(new Date().getTime()));
				record.setOperateContent("用户："+m.getUserId() +",修改密码失败,原因：原密码错误");
				tbOperateLogDao.save(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
