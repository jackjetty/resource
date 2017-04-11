package com.rising.management.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.rising.management.bean.Product;
import com.rising.management.dao.ProductDao;

@Component("productDao")
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> findProductByBusId(Integer busId) {
		String hql = "from Product where status = '有效'";
		Query query;
		if (busId != null) {
			hql = hql + " and busId = :busId";
			query = getSession().createQuery(hql);
			query.setParameter("busId", busId);
		} else {
			query = getSession().createQuery(hql);
		}
		ArrayList<Product> ap = (ArrayList<Product>) query.list();
		return ap;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getProductIds() {
		String hql = "from Product where status = '有效'";
		Query query = getSession().createQuery(hql);
		ArrayList<String> as = new ArrayList<String>();
		ArrayList<Product> ap = (ArrayList<Product>) query.list();
		for (Product product : ap) {
			as.add(product.getProductId());
		}
		return as;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getProductIds(Integer busId) {
		Query query = null;
		String hql = "from Product where status = '有效'";
		if (busId == null) {
			query = getSession().createQuery(hql);
		} else {
			hql = hql + " and busId = :busId";
			query = getSession().createQuery(hql);
			query.setInteger("busId", busId);
		}
		ArrayList<String> as = new ArrayList<String>();
		ArrayList<Product> ap = (ArrayList<Product>) query.list();
		for (Product product : ap) {
			as.add(product.getProductId());
		}
		return as;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> getProductByStatus() {
		String hql = "from Product where status='有效'";
		Query query = getSession().createQuery(hql);
		ArrayList<Product> ap = (ArrayList<Product>) query.list();
		return ap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getProductMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// String hql = "from Product where status = '有效'";
		String hql = "from Product";
		Query query = getSession().createQuery(hql);
		ArrayList<Product> ap = (ArrayList<Product>) query.list();
		for (Product product : ap) {
			map.put(product.getProductId(), product.getProductDescribe());
		}
		return map;
	}

	@Override
	public Integer getProductInfoListSize(Integer busId, Integer cost) {
		String hql = "select count(*) from Product ";
		Query query = null;
		if (busId != null) {
			if (cost != null) {
				hql = hql + " where busId = :busId and cost = :cost  ";
				query = getSession().createQuery(hql);
				query.setParameter("busId", busId);
				query.setParameter("cost", cost);
			} else {
				hql = hql + " where busId = :busId ";
				query = getSession().createQuery(hql);
				query.setParameter("busId", busId);
			}

		} else {
			if (cost != null) {
				hql = hql + " where cost = :cost ";
				query = getSession().createQuery(hql);
				query.setParameter("cost", cost);
			} else {
				query = getSession().createQuery(hql);
			}

		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> getProductInfo(Integer busId, Integer cost,
			Integer start, Integer pageSize) {
		String hql = " from Product ";
		Query query = null;
		if (busId != null) {
			if (cost != null) {
				hql = hql + " where busId = :busId and cost = :cost  ";
				query = getSession().createQuery(hql);
				query.setParameter("busId", busId);
				query.setParameter("cost", cost);
			} else {
				hql = hql + " where busId = :busId";
				query = getSession().createQuery(hql);
				query.setParameter("busId", busId);
			}
		} else {
			if (cost != null) {
				hql = hql + " where cost = :cost ";
				query = getSession().createQuery(hql);
				query.setParameter("cost", cost);

			} else {
				query = getSession().createQuery(hql);
			}

		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<Product> p = (ArrayList<Product>) query.list();
		return p;
	}

	@Override
	public void addProduct(Product p) {
		getSession().save(p);
		getSession().flush();
	}

	@Override
	public void deleteById(String productId) {
		String hql = " delete from Product where productId = :productId ";
		Query query = getSession().createQuery(hql);
		query.setParameter("productId", productId);
		query.executeUpdate();

	}

	@Override
	public void updateProduct(Product p) {
		getSession().update(p);
		getSession().flush();

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> findAll() {
		String hql = " from Product  ";
		Query query = getSession().createQuery(hql);
		ArrayList<Product> proc = (ArrayList<Product>) query.list();
		return proc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> findAllByStatus() {
		String hql = " from Product where status = '有效' ";
		Query query = getSession().createQuery(hql);
		ArrayList<Product> proc = (ArrayList<Product>) query.list();
		return proc;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getProductDescribe(String productId, Integer busId) {
		String hql = " from Product where status = '有效' and productId = :productId ";
		Query query = null;
		if (busId == null) {
			query = getSession().createQuery(hql);
			query.setParameter("productId", productId);

		} else {
			hql = hql + " and  busId = :busId ";
			query = getSession().createQuery(hql);
			query.setInteger("busId", busId);
			query.setParameter("productId", productId);

		}
		ArrayList<Product> p = (ArrayList<Product>) query.list();
		ArrayList<String> ap = new ArrayList<String>();
		for (int i = 0; i < p.size(); i++) {
			ap.add(p.get(i).getProductDescribe());
		}

		return ap;
	}

	@SuppressWarnings("unchecked")
	public String getProductDescribe2(String productId) {
		String hql = " select  distinct productDescribe from Product where productId = :productId";
		Query query = getSession().createQuery(hql);
		query.setParameter("productId", productId);
		ArrayList<String> p = (ArrayList<String>) query.list();
		String productDescribe = p.get(0);
		return productDescribe;
	}

	@Override
	public Integer getNumber(Integer busId) {
		String hql = "select count(*) from Product where status = '有效' ";
		Query query = null;
		if (busId == null) {
			query = getSession().createQuery(hql);
		} else {
			hql = hql + " and busId = :busId";
			query = getSession().createQuery(hql);
			query.setParameter("busId", busId);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getProductDescribe(String id) {
		String hql = "from Product where status = '有效' and productId = :productId order by productId ";
		Query query = getSession().createQuery(hql);
		query.setParameter("productId", id);
		ArrayList<Product> p = (ArrayList<Product>) query.list();
		ArrayList<String> pd = new ArrayList<String>();
		for (int i = 0; i < p.size(); i++) {
			pd.add(p.get(i).getProductDescribe());
		}

		return pd;
	}

	@Override
	public Integer getNumberBystatus() {
		String hql = "select count(*) from Product where status = '有效'";
		Query query = getSession().createQuery(hql);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getQQPermonthProductDescribe() {
		String hql = "select productDescribe from Product where busId = '103' and status = '有效'";
		Query query = getSession().createQuery(hql);
		ArrayList<String> pd = (ArrayList<String>) query.list();
		return pd;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getProductDescribeByBusId(Integer busId) {
		String hql = "select productDescribe from Product where status = '有效' ";
		Query query = null;
		if (busId == null) {
			query = getSession().createQuery(hql);
		} else {
			hql = hql + " and busId = :busId";
			query = getSession().createQuery(hql);
			query.setParameter("busId", busId);
		}
		ArrayList<String> pd = (ArrayList<String>) query.list();
		return pd;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<HashMap<String, Object>> getProductMap2() {
		ArrayList<HashMap<String, Object>> AHSO = new ArrayList<HashMap<String, Object>>();

		String hql = "from Product where status = '有效'";
		Query query = getSession().createQuery(hql);
		ArrayList<Product> ap = (ArrayList<Product>) query.list();
		for (Product product : ap) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("productId", product.getProductId());
			map.put("productDescribe", product.getProductDescribe());
			AHSO.add(map);
		}
		return AHSO;
	}

}
