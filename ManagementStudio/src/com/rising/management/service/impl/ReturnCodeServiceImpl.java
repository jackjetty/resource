package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.rising.management.bean.Business;
import com.rising.management.bean.Manager;
import com.rising.management.bean.OperateRecord;
import com.rising.management.bean.ReturnCode;
import com.rising.management.dao.BusinessDao;
import com.rising.management.dao.ReturnCodeDao;
import com.rising.management.service.ReturnCodeService;
import com.rising.management.vo.ReturnCodeVo;

@Service("returnCodeService")
public class ReturnCodeServiceImpl extends BaseServiceImpl implements ReturnCodeService {
	Log log = LogFactory.getLog(ReturnCodeServiceImpl.class);

	@Autowired
	BusinessDao businessDao;

	@Autowired
	ReturnCodeDao returnCodeDao;

	@Override
	public HashMap<String, Object> getReturnCode(Integer pageSize,
			Integer pageIndex, Integer busId, String returnCode, Boolean hasShow) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if ("".equals(busId)) {
			busId = null;
		}
		if ("".equals(returnCode)) {
			returnCode = null;
		}
		ArrayList<Business> ab = businessDao.getBusiness();
		HashMap<Integer, String> map = transform(ab);
		Integer listSize = returnCodeDao.getReturnCodeListSize(busId,
				returnCode, hasShow);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<ReturnCode> rc = returnCodeDao.getReturnCode(returnCode,
				busId, hasShow, start, pageSize);
		ArrayList<ReturnCodeVo> arc = new ArrayList<ReturnCodeVo>();
		for (ReturnCode returnCode2 : rc) {
			arc.add(new ReturnCodeVo(returnCode2, map));
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", arc);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addReturnCode(ReturnCode r) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager operateManager = (Manager) session.get("Manager");
		try {
			returnCodeDao.addReturnCode(r);
			resultMap.put("respInfo", "添加新返回码信息成功！");
			resultMap.put("respCode", 0);
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("返回码信息添加");
			record.setResult("成功");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",添加返回码"+r.getReturnCode()+",添加成功");
			sysOperateLogDao.save(record);
		} catch (Exception e) {
			log.error("添加新返回码时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "添加新返回码信息时出现异常！");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("返回码信息添加");
			record.setResult("失败");
			record.setCause("数据库操作出现异常");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",添加返回码"+r.getReturnCode()+",添加失败,原因：数据库操作出现异常");
			sysOperateLogDao.save(record);
		}
		return resultMap;
	}

	@Override
	public HashMap<String, Object> deleteByIds(String ids) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String[] idArray = ids.split(",");
		String lastId = "";
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager operateManager = (Manager) session.get("Manager");
		try {
			for (String idObj : idArray) {
				lastId = idObj;
				if (!"".equals(idObj)) {
					returnCodeDao.deleteById(Integer.parseInt(idObj));
				}
			}
			result.put("respInfo", "删除返回码信息成功!");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("返回码信息删除");
			record.setResult("成功");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",删除返回码信息id="+lastId+",删除成功");
			sysOperateLogDao.save(record);
		} catch (Exception e) {
			log.error("删除id为" + lastId + "的返回码信息时出现异常!" + e.toString());
			e.printStackTrace();
			result.put("respInfo", "删除返回码信息时出现异常!");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("返回码信息删除");
			record.setResult("失败");
			record.setCause("数据库操作出现异常");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",删除返回码信息id="+lastId+",删除失败,原因：数据库操作出现异常");
			sysOperateLogDao.save(record);
		}
		return result;
	}

	@Override
	public HashMap<String, Object> updateReturnCode(ReturnCode r) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Manager operateManager = (Manager) session.get("Manager");
		try {
			returnCodeDao.updateReturnCode(r);
			resultMap.put("respInfo", "更新返回码信息成功！");
			resultMap.put("respCode", 0);
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("返回码信息更新");
			record.setResult("成功");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",更新返回码信息"+r.getReturnCode()+",更新成功");
			sysOperateLogDao.save(record);
		} catch (Exception e) {
			log.error("更新返回码信息时出现异常！" + e.toString());
			e.printStackTrace();
			resultMap.put("respCode", -1);
			resultMap.put("respInfo", "更新返回码信息时出现异常！");
			OperateRecord record = new OperateRecord();
			record.setManager(operateManager.getName());
			record.setOperateType("返回码信息更新");
			record.setResult("失败");
			record.setCause("数据库操作出现异常");
			record.setOperateTime(new Date());
			record.setOperateContent("用户："+operateManager.getName() +",更新返回码信息"+r.getReturnCode()+",更新失败,原因：数据库操作出现异常");
			sysOperateLogDao.save(record);
		}
		return resultMap;
	}

	private HashMap<Integer, String> transform(ArrayList<Business> ab) {
		HashMap<Integer, String> ais = new HashMap<Integer, String>();
		for (Business business : ab) {
			ais.put(business.getBusId(), business.getBtype());
		}
		return ais;
	}

}
