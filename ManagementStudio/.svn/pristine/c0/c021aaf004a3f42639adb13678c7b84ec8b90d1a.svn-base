package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.FreeProduct;
import com.rising.management.dao.FreeProductDao;

@Component("freeProductDao")
public class FreeProductDaoImpl extends BaseDaoImpl implements FreeProductDao {

	@Override
	public Integer getFreeProductListSize(Integer busId, Integer cost) {
		String hql = "select count(*) from FreeProduct";
		Query query = null;
		if (busId == null) {
			if (cost == null) {
				query = getSession().createQuery(hql);
			} else {
				hql = hql + " where cost = :cost";
				query = getSession().createQuery(hql);
				query.setParameter("cost", cost);
			}
		} else {
			if (cost == null) {
				hql = hql + "  where busId = :busId";
				query = getSession().createQuery(hql);
				query.setParameter("busId", busId);
			} else {
				hql = hql + "  where busId = :busId and cost = :cost";
				query = getSession().createQuery(hql);
				query.setParameter("busId", busId);
				query.setParameter("cost", cost);
			}
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<FreeProduct> getFreeProduct(Integer busId, Integer cost,
			Integer start, Integer pageSize) {
		String hql = "from FreeProduct";
		Query query = null;
		if (busId == null) {
			if (cost == null) {
				query = getSession().createQuery(hql);
			} else {
				hql = hql + " where cost = :cost";
				query = getSession().createQuery(hql);
				query.setParameter("cost", cost);
			}

		} else {
			if (cost == null) {
				hql = hql + " where busId = :busId";
				query = getSession().createQuery(hql);
				query.setParameter("busId", busId);
			} else {
				hql = hql + " where busId = :busId and cost = :cost";
				query = getSession().createQuery(hql);
				query.setParameter("busId", busId);
				query.setParameter("cost", cost);
			}

		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<FreeProduct> rs = (ArrayList<FreeProduct>) query.list();
		return rs;

	}

	@Override
	public void addFreeProduct(FreeProduct fp) {
		getSession().save(fp);
		getSession().flush();

	}

	@Override
	public void updateFreeProduct(FreeProduct fp) {
		getSession().update(fp);
		getSession().flush();
	}

	@Override
	public void deleteById(String id) {
		String hql = "delete from FreeProduct where freeProductId = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<FreeProduct> findAll() {
		String hql = " from FreeProduct  ";
		Query query = getSession().createQuery(hql);
		ArrayList<FreeProduct> proc = (ArrayList<FreeProduct>) query.list();
		return proc;
	}

	@Override
	public HashMap<String, Object> getFreeProductMap() {
		ArrayList<FreeProduct> afp = findAll();
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (FreeProduct freeProduct : afp) {
			map.put(freeProduct.getFreeProductId(),
					freeProduct.getProductName());
		}
		return map;
	}

}
