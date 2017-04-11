package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.PayPrize;
import com.rising.management.dao.FreeProductDao;
import com.rising.management.dao.PayPrizeDao;
import com.rising.management.dao.ProductDao;
import com.rising.management.service.PayPrizeService;
import com.rising.management.vo.PayPrizeVo;

@Service("payPrizeService")
public class PayPrizeServiceImpl implements PayPrizeService {

	Log log = LogFactory.getLog(PayPrizeServiceImpl.class);

	@Autowired
	PayPrizeDao payPrizeDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	FreeProductDao freeProductDao;

	@Override
	public HashMap<String, Object> addPayPrize(PayPrize payPrize) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			payPrizeDao.addPayPrize(payPrize);
			map.put("respCode", 0);
			map.put("respInfo", "添加充值赠送规则成功！");
		} catch (Exception e) {
			log.error(e);
			map.put("respCode", -1);
			map.put("respInfo", "添加充值赠送规则失败！");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> updatePayPrize(PayPrize payPrize) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			payPrizeDao.updatePayPrize(payPrize);
			map.put("respCode", 0);
			map.put("respInfo", "更新充值赠送规则成功！");
		} catch (Exception e) {
			log.error(e);
			map.put("respCode", -1);
			map.put("respInfo", "更新充值赠送规则失败！");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> removePayPrize(String ids) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<Integer> ai = split(ids);
			payPrizeDao.removePayPrize(ai);
			result.put("respCode", 0);
			result.put("respInfo", "充值赠送规则删除成功!");
		} catch (Exception e) {
			log.error("删除充值赠送规则时出现异常!" + e.toString());
			result.put("respCode", -3);
			result.put("respInfo", "删除验证码时出现异常!");
		}
		return result;
	}

	private ArrayList<Integer> split(String ids) {
		ArrayList<Integer> ai = new ArrayList<Integer>();
		String[] id = ids.split(",");
		for (String string : id) {
			Integer x = null;
			try {
				x = Integer.parseInt(string);
				ai.add(x);
			} catch (Exception e) {
				continue;
			}
		}
		return ai;
	}

	@Override
	public HashMap<String, Object> getPayPrize(String productId,
			Integer pageSize, Integer pageIndex) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = payPrizeDao.getPayPrizeListSize(productId);
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<PayPrize> sr = payPrizeDao.getPayPrize(productId, start,
				pageSize);
		HashMap<String, Object> productMap = productDao.getProductMap();
		HashMap<String, Object> freeProductMap = freeProductDao
				.getFreeProductMap();
		ArrayList<PayPrizeVo> afp = new ArrayList<PayPrizeVo>();
		for (PayPrize payPrize : sr) {
			PayPrizeVo vo = new PayPrizeVo(payPrize, freeProductMap, productMap);
			afp.add(vo);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", afp);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> changeStatus(Integer id) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		PayPrize p = payPrizeDao.findById(id);
		if ("启用".equals(p.getStatus())) {
			p.setStatus("结束");
			payPrizeDao.updatePayPrize(p);
			resultMap.put("respInfo", "成功停用充值赠送规则！");
			resultMap.put("respCode", 0);
		} else {
			ArrayList<PayPrize> app = payPrizeDao.findByProductId(p
					.getProductId());
			if (app != null && app.size() > 0) {
				resultMap.put("respInfo", "启用充值赠送规则失败！同一时间一种充值产品只能启用一条充值赠送规则！");
				resultMap.put("respCode", -1);
			} else {
				p.setStatus("启用");
				payPrizeDao.updatePayPrize(p);
				resultMap.put("respInfo", "成功启用充值赠送规则！");
				resultMap.put("respCode", 0);
			}
		}

		return resultMap;
	}

}
