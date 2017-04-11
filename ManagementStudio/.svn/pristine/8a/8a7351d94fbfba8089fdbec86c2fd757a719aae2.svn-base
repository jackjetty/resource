package com.rising.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rising.management.bean.FreeProduct;
import com.rising.management.dao.BusinessDao;
import com.rising.management.dao.FreeProductDao;
import com.rising.management.dao.MerchantDao;
import com.rising.management.service.FreeProductService;
import com.rising.management.vo.FreeProductVo;

@Service("freeProductService")
public class FreeProductServiceImpl implements FreeProductService {
	Log log = LogFactory.getLog(FreeProductServiceImpl.class);

	@Autowired
	FreeProductDao freeProductDao;

	@Autowired
	BusinessDao businessDao;

	@Autowired
	MerchantDao merchantDao;

	@Override
	public HashMap<String, Object> getFreeProduct(Integer busId, Integer cost,
			Integer pageSize, Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer listSize = freeProductDao.getFreeProductListSize(busId, cost);
		Integer start = (pageIndex - 1) * pageSize;
		HashMap<String,Object> businessMap = businessDao.getBusIdAndBusName();
		HashMap<String,Object> merchantMap = merchantDao.getMerchantInfo();
		ArrayList<FreeProduct> sr = freeProductDao.getFreeProduct(busId, cost,
				start, pageSize);
		ArrayList<FreeProductVo> afp = new ArrayList<FreeProductVo>();
		for (FreeProduct freeProduct : sr) {
			FreeProductVo vo = new FreeProductVo(freeProduct, businessMap, merchantMap);
			afp.add(vo);
		}
		resultMap.put("listSize", listSize);
		resultMap.put("rows", afp);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> addFreeProduct(FreeProduct fp) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			ArrayList<FreeProduct> proc = freeProductDao.findAll();
			ArrayList<String> gproc = new ArrayList<String>();
			for (int i = 0; i < proc.size(); i++) {
				gproc.add(proc.get(i).getFreeProductId());
			}
			if (gproc.contains(fp.getFreeProductId())) {
				result.put("respInfo", "产品代码不能重复");
			} else {
				freeProductDao.addFreeProduct(fp);
				result.put("respCode", 0);
				result.put("respInfo", "添加免费产品成功!");
			}
		} catch (Exception e) {
			log.error("添加免费产品时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "添加免费产品时出现异常!");
		}

		return result;
	}

	@Override
	public HashMap<String, Object> updateFreeProduct(FreeProduct fp) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			freeProductDao.updateFreeProduct(fp);
			result.put("respCode", 0);
			result.put("respInfo", "修改免费产品成功!");
		} catch (Exception e) {
			log.error("修改免费产品时出现异常!" + e.toString());
			result.put("respCode", -2);
			result.put("respInfo", "修改免费产品时出现异常!");
		}

		return result;
	}

	@Override
	public HashMap<String, Object> removeFreeProduct(String productIds) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String[] idArr = productIds.split(",");
		String lastId = "";
		try {
			for (String id : idArr) {
				lastId = id;
				if (!"".equals(id)) {
					freeProductDao.deleteById(id);
				}
			}
			result.put("respInfo", "删除免费产品成功!");
		} catch (Exception e) {
			log.error("删除id为" + lastId + "的免费产品时出现异常!" + e.toString());
			e.printStackTrace();
			result.put("respInfo", "删除免费产品时出现异常!");
		}
		return result;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getFreeProductMap() {
		ArrayList<HashMap<String, Object>> ahso = new ArrayList<HashMap<String,Object>>();
		ArrayList<FreeProduct> afp = freeProductDao.findAll();
		for (FreeProduct freeProduct : afp) {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("freeProductId", freeProduct.getFreeProductId());
			map.put("productName", freeProduct.getProductName());
			ahso.add(map);
		}
		return ahso;
	}

}
