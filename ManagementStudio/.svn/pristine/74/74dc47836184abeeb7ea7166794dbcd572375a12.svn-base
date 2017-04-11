package com.rising.management.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import com.rising.management.bean.QQPermonthRecode;
import com.rising.management.dao.QQPermonthRecodeDao;

@Component("qqPermonthRecodeDao")
public class QQPermonthRecodeDaoImpl extends BaseDaoImpl implements
		QQPermonthRecodeDao {

	@Override
	public Integer getQQPermonthRecodeListSize(String phoneNumber,
			Date startDate, Date endDate) {
		String hql = "select count(*) from QQPermonthRecode ";
		Query query = null;
		if (phoneNumber == null) {
			hql = hql
					+ " where checkTime >= :startDate and checkTime <= :endDate ";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		} else {
			hql = hql
					+ " where checkTime >= :startDate and checkTime <= :endDate and  phoneNumber = :phoneNumber";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("phoneNumber", phoneNumber);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<QQPermonthRecode> getQQPermonthRecode(String phoneNumber,
			Date startDate, Date endDate, Integer pageSize, Integer start) {
		String hql = "from QQPermonthRecode ";
		Query query = null;
		if (phoneNumber == null) {
			hql = hql
					+ " where checkTime >= :startDate and checkTime <= :endDate ";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		} else {
			hql = hql
					+ " where checkTime >= :startDate and checkTime <= :endDate and  phoneNumber = :phoneNumber";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("phoneNumber", phoneNumber);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<QQPermonthRecode> rs = (ArrayList<QQPermonthRecode>) query
				.list();
		return rs;

	}
////-------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getAllPayPhoneNumbers(Date startDate,
			Date endDate) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select p.PRODUCTDESCRIBE ,count(distinct q.PHONENUMBER) from RS_PRODUCT p,RS_QQPERMONTHRECORD q,RS_BUSINESS b where p.BUSID=b.BUSID and q.COST = p.COST "
				+ " and p.status = '有效' and b.FEEMETHOD ='不定时' and q.CHECKTIME >= :startDate and q.CHECKTIME <= :endDate group by p.PRODUCTDESCRIBE";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> qq = (ArrayList<Object[]>) query.list();
		for (Object[] obj : qq) {
			map.put(obj[0].toString(), obj[1]);
		}
		return map;
	}
////-------------------------------------------
	@Override
	public Integer getPayPhoneNumbersAll(Date startDate, Date endDate) {
		String hql = "select count(distinct phoneNumber) from QQPermonthRecode where checkTime >= :startDate and checkTime <= :endDate";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}
////-------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getQQNumbers(Date startDate, Date endDate) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select p.PRODUCTDESCRIBE ,count(distinct q.QQNUMBER) from RS_PRODUCT p,RS_QQPERMONTHRECORD q,RS_BUSINESS b where p.BUSID=b.BUSID and q.COST = p.COST "
				+ " and p.status = '有效' and b.FEEMETHOD ='不定时' and q.CHECKTIME >= :startDate and q.CHECKTIME <= :endDate group by p.PRODUCTDESCRIBE";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> qq = (ArrayList<Object[]>) query.list();
		for (Object[] obj : qq) {
			map.put(obj[0].toString(), obj[1]);
		}
		return map;
	}

	@Override
	public Integer getQQNumbersAll(Date startDate, Date endDate) {
		String hql = "select count(distinct QQNumber) from QQPermonthRecode where checkTime >= :startDate and checkTime <= :endDate";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getAllSendScore(Date startDate, Date endDate) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select p.PRODUCTDESCRIBE , sum(q.SENDSCORE) from RS_PRODUCT p,RS_QQPERMONTHRECORD q,RS_BUSINESS b where p.BUSID=b.BUSID and q.COST = p.COST "
				+ " and p.status = '有效' and b.FEEMETHOD ='不定时' and q.CHECKTIME >= :startDate and q.CHECKTIME <= :endDate group by p.PRODUCTDESCRIBE";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> qq = (ArrayList<Object[]>) query.list();
		for (Object[] obj : qq) {
			map.put(obj[0].toString(), obj[1]);
		}
		return map;
	}
////-------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public Long getSendScoreAll(Date startDate, Date endDate) {
		String hql = "select sum(sendScore) from QQPermonthRecode where checkTime >= :startDate and checkTime <= :endDate";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Long> pn = (ArrayList<Long>) query.list();
		Long sendScore = pn.get(0);
		return sendScore;
	}
////-------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getAllPayMoney(Date startDate, Date endDate) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select p.PRODUCTDESCRIBE , sum(q.PAYMONEY) from RS_PRODUCT p,RS_QQPERMONTHRECORD q,RS_BUSINESS b where p.BUSID=b.BUSID and q.COST = p.COST "
				+ " and p.status = '有效' and b.FEEMETHOD ='不定时' and q.CHECKTIME >= :startDate and q.CHECKTIME <= :endDate group by p.PRODUCTDESCRIBE";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> qq = (ArrayList<Object[]>) query.list();
		for (Object[] obj : qq) {
			map.put(obj[0].toString(), obj[1]);
		}
		return map;
	}
	
////-------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public double getPayMoneyAll(Date startDate, Date endDate) {
		String hql = "select sum(payMoney) from QQPermonthRecode where status = '扣费成功' and checkTime >= :startDate and checkTime <= :endDate";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Double> ad = (ArrayList<Double>) query.list();
		Double allPayMoney = ad.get(0);
		if (allPayMoney == null) {
			return 0.0;
		}
		return allPayMoney;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public double getPayMoneyAll2(Date startDate, Date endDate) {
		String hql = "select sum(payMoney) from QQPermonthRecode2 where status = '扣费成功' and checkTime >= :startDate and checkTime <= :endDate";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Double> ad = (ArrayList<Double>) query.list();
		Double allPayMoney = ad.get(0);
		if (allPayMoney == null) {
			return 0.0;
		}
		return allPayMoney;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getAllFailNumbers(Date startDate, Date endDate) {
		String hql = "select status,count(distinct phoneNumber) from QQPermonthRecode where checkTime >= :startDate and checkTime <= :endDate group by status";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> pn = (ArrayList<Object[]>) query.list();
		HashMap<String,Object> map = new HashMap<String, Object>();
		for (Object[] strings : pn) {
			map.put(strings[0].toString(), Integer.parseInt(strings[1].toString()));
		}
		Integer x = (Integer) map.get("扣费失败");
		if(x==null){
			return 0;
		}else{
			return x;
		}
	}

	@Override
	public Integer getAllSuccessNumbers(Date startDate, Date endDate) {
		String hql = "select count(distinct phoneNumber) from QQPermonthRecode where checkTime >= :startDate and checkTime <= :endDate and status = '扣费成功'";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getAllPay(Date startDate, Date endDate) {
		String sql = "select p.PRODUCTDESCRIBE,COUNT(*) FROM RS_QQPERMONTHRECORD q,RS_PRODUCT p WHERE p.STATUS = '有效' and q.COST = p.COST and p.BUSID = 103 and q.CHECKTIME >= :startDate and q.CHECKTIME <= :endDate group by p.PRODUCTDESCRIBE";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < ao.size(); i++) {
			map.put(ao.get(i)[0].toString(), Integer.parseInt(ao.get(i)[1].toString()));
		}
		return map;
	}

	@Override
	public Integer getAllPaySuccess(Date startDate, Date endDate) {
		String hql = "select count(*) from QQPermonthRecode where checkTime >= :startDate and checkTime <= :endDate and status = '扣费成功'";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@Override
	public Integer getAllPayFail(Date startDate, Date endDate) {
		String hql = "select count(*) from QQPermonthRecode where checkTime >= :startDate and checkTime <= :endDate and status = '扣费失败'";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@Override
	public Integer getPaySuccess(Date startDate, Date endDate, String place) {
		Query query = null;
		if (place == null) {
			String hql = "select count(*) from QQPermonthRecode where status = '扣费成功' and checkTime >= :startDate and checkTime <= :endDate";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);

		} else {
			String hql = "select count(*) from QQPermonthRecode q,UserInfo u where q.status = '扣费成功' and q.phoneNumber = u.phoneNumber and q.checkTime >= :startDate and q.checkTime <= :endDate  and u.address = :place";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("place", place);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@Override
	public Integer getPayFail(Date startDate, Date endDate, String place) {
		Query query = null;
		if (place == null) {
			String hql = "select count(*) from QQPermonthRecode where status = '扣费失败' and checkTime >= :startDate and checkTime <= :endDate";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);

		} else {
			String hql = "select count(*) from QQPermonthRecode q,UserInfo u where q.status = '扣费失败' and q.phoneNumber = u.phoneNumber and q.checkTime >= :startDate and q.checkTime <= :endDate  and u.address = :place";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("place", place);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@Override
	public Integer getAllPay(Date startDate, Date endDate, String place) {
		Query query = null;
		if (place == null) {
			String hql = "select count(*) from QQPermonthRecode where  checkTime >= :startDate and checkTime <= :endDate";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);

		} else {
			String hql = "select count(*) from QQPermonthRecode q,UserInfo u where  q.phoneNumber = u.phoneNumber and q.checkTime >= :startDate and q.checkTime <= :endDate  and u.address = :place";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("place", place);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getPayMoneyByPalce(Date startDate, Date endDate, String place) {
		Query query = null;
		if (place == null) {
			String hql = " select sum(payMoney) from QQPermonthRecode where status = '扣费成功' and checkTime >= :startDate and checkTime <= :endDate";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);

		} else {
			String hql = "select sum(q.payMoney)  from QQPermonthRecode q,UserInfo u where q.status = '扣费成功' and q.phoneNumber = u.phoneNumber and q.checkTime >= :startDate and q.checkTime <= :endDate  and u.address = :place";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("place", place);
		}
		ArrayList<Double> pn = (ArrayList<Double>) query.list();
		Double allPayMoney = pn.get(0);
		if (allPayMoney == null) {
			return 0.0;
		}
		return allPayMoney;
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getPayMoneyByTime(Date startDate, Date endDate,
			String placeName) {
		Query query = null;
		if (placeName == null) {
			String hql = " select sum(payMoney) from QQPermonthRecode where status = '扣费成功' and checkTime >= :startDate and checkTime <= :endDate";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);

		} else {
			String hql = "select sum(q.payMoney)  from QQPermonthRecode q,UserInfo u,Place p where q.status = '扣费成功' and q.phoneNumber = u.phoneNumber and p.code = u.address and q.checkTime >= :startDate and q.checkTime <= :endDate  and p.name = :placeName";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("placeName", placeName);
		}
		ArrayList<Double> pn = (ArrayList<Double>) query.list();
		Double allPayMoney = pn.get(0);
		if (allPayMoney == null) {
			return 0.0;
		}
		return allPayMoney;
	}

	
	@Override
	public Integer getSuccessNumbers(Date startDate, Date endDate, String place) {
		Query query = null;
		if (place == null) {
			String hql = " select count(distinct q.phoneNumber) from QQPermonthRecode where status = '扣费成功' and checkTime >= :startDate and checkTime <= :endDate";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);

		} else {
			String hql = "select count(distinct q.phoneNumber)  from QQPermonthRecode q,UserInfo u where q.status = '扣费成功' and q.phoneNumber = u.phoneNumber and q.checkTime >= :startDate and q.checkTime <= :endDate  and u.address = :place";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("place", place);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@Override
	public Integer getFailNumbers(Date startDate, Date endDate, String place) {
		Query query = null;
		if (place == null) {
			String hql = " select count(distinct q.phoneNumber) from QQPermonthRecode where status = '扣费失败' and checkTime >= :startDate and checkTime <= :endDate";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);

		} else {
			String hql = "select count(distinct q.phoneNumber)  from QQPermonthRecode q,UserInfo u where q.status = '扣费失败' and q.phoneNumber = u.phoneNumber and q.checkTime >= :startDate and q.checkTime <= :endDate  and u.address = :place";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("place", place);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@Override
	public Integer getAllNumbers(Date startDate, Date endDate, String place) {
		Query query = null;
		if (place == null) {
			String hql = " select count(distinct q.phoneNumber) from QQPermonthRecode where  checkTime >= :startDate and checkTime <= :endDate";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);

		} else {
			String hql = "select count(distinct q.phoneNumber)  from QQPermonthRecode q,UserInfo u where  q.phoneNumber = u.phoneNumber and q.checkTime >= :startDate and q.checkTime <= :endDate  and u.address = :place";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("place", place);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPayMoneyByTimeRound(String placeName,
			Date startTime, Date endTime, String toTime) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (placeName == null) {
			if ("1".equals(toTime)) {
				String sql = "select TO_CHAR(CHECKTIME,'YYYY') AA,sum(PAYMONEY) from RS_QQPERMONTHRECORD  where "
						+ "CHECKTIME >= :starttime and CHECKTIME <= :endtime and STATUS = '扣费成功' group by TO_CHAR(CHECKTIME,'YYYY')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			} else if ("2".equals(toTime)) {
				String sql = "select TO_CHAR(CHECKTIME,'yyyy-MM') AA,sum(PAYMONEY) from RS_QQPERMONTHRECORD  where "
						+ "CHECKTIME >= :starttime and CHECKTIME <= :endtime and STATUS = '扣费成功' group by TO_CHAR(CHECKTIME,'yyyy-MM')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			} else if ("4".equals(toTime)) {
				String sql = "select TO_CHAR(CHECKTIME,'yyyy-MM-dd') AA,sum(PAYMONEY) from RS_QQPERMONTHRECORD  where "
						+ "CHECKTIME >= :starttime and CHECKTIME <= :endtime and STATUS = '扣费成功' group by TO_CHAR(CHECKTIME,'yyyy-MM-dd')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			} else if ("5".equals(toTime)) {
				String sql = "select TO_CHAR(CHECKTIME,'hh24') AA,sum(PAYMONEY) from RS_QQPERMONTHRECORD  where "
						+ "CHECKTIME >= :starttime and CHECKTIME <= :endtime and STATUS = '扣费成功' group by TO_CHAR(CHECKTIME,'hh24')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			}
		} else {
			if ("1".equals(toTime)) {
				String sql = "select TO_CHAR(q.CHECKTIME,'YYYY') AA,sum(q.PAYMONEY) from RS_QQPERMONTHRECORD q,RS_USERINFO u where "
						+ "q.CHECKTIME >= :starttime and q.CHECKTIME <= :endtime and q.STATUS = '扣费成功' and q.PHONENUMBER = u.PHONENUMBER and u.ADDRESS = :placeName group by TO_CHAR(q.CHECKTIME,'YYYY')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("placeName", placeName);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			} else if ("2".equals(toTime)) {
				String sql = "select TO_CHAR(q.CHECKTIME,'YYYY-MM') AA,sum(q.PAYMONEY) from RS_QQPERMONTHRECORD q,RS_USERINFO u where "
						+ "q.CHECKTIME >= :starttime and q.CHECKTIME <= :endtime and q.STATUS = '扣费成功' and q.PHONENUMBER = u.PHONENUMBER and u.ADDRESS = :placeName group by TO_CHAR(q.CHECKTIME,'YYYY-MM')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("placeName", placeName);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			} else if ("4".equals(toTime)) {
				String sql = "select TO_CHAR(q.CHECKTIME,'yyyy-MM-dd') AA,sum(q.PAYMONEY) from RS_QQPERMONTHRECORD q,RS_USERINFO u where "
						+ "q.CHECKTIME >= :starttime and q.CHECKTIME <= :endtime and q.STATUS = '扣费成功' and q.PHONENUMBER = u.PHONENUMBER and u.ADDRESS = :placeName group by TO_CHAR(q.CHECKTIME,'yyyy-MM-dd')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("placeName", placeName);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			} else if ("5".equals(toTime)) {
				String sql = "select TO_CHAR(q.CHECKTIME,'hh24') AA,sum(q.PAYMONEY) from RS_QQPERMONTHRECORD q,RS_USERINFO u where "
						+ "q.CHECKTIME >= :starttime and q.CHECKTIME <= :endtime and q.STATUS = '扣费成功' and q.PHONENUMBER = u.PHONENUMBER and u.ADDRESS = :placeName group by TO_CHAR(q.CHECKTIME,'hh24')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("placeName", placeName);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			}
		}

		return map;
	}

	@Override
	public Integer getUserQQPermonthRecodeListSize(String phoneNumber) {
		String hql = "select count(*) from QQPermonthRecode where phoneNumber = :phoneNumber";
		Query query = getSession().createQuery(hql);
		query.setParameter("phoneNumber", phoneNumber);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<QQPermonthRecode> getUserQQPermonthRecode(
			String phoneNumber, Integer pageSize, Integer start) {
		String hql = "from QQPermonthRecode where phoneNumber = :phoneNumber order by checkTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("phoneNumber", phoneNumber);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<QQPermonthRecode> aqq = (ArrayList<QQPermonthRecode>) query.list();
		return aqq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Integer> getPayPhoneNumber(Date startDate,
			Date endDate) {
		String hql = "select status,count(distinct phoneNumber) from QQPermonthRecode where checkTime >= :startDate and checkTime <= :endDate group by status";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> pn = (ArrayList<Object[]>) query.list();
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		Integer all = 0;
		for (Object[] strings : pn) {
			map.put(strings[0].toString(), Integer.parseInt(strings[1].toString()));
			all = all + Integer.parseInt(strings[1].toString());
		}
		map.put("All", all);
		return map;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Integer> getPayPhoneNumber2(Date startDate,
			Date endDate) {
		String hql = "select status,count(distinct phoneNumber) from QQPermonthRecode2 where checkTime >= :startDate and checkTime <= :endDate group by status";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> pn = (ArrayList<Object[]>) query.list();
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		Integer all = 0;
		for (Object[] strings : pn) {
			map.put(strings[0].toString(), Integer.parseInt(strings[1].toString()));
			all = all + Integer.parseInt(strings[1].toString());
		}
		map.put("All", all);
		return map;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Integer> getAllPayNumbers(Date startDate,
			Date endDate) {
		String hql = "select status, count(*) from QQPermonthRecode where checkTime >= :startDate and checkTime <= :endDate group by status";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Integer All = 0;
		for (int i = 0; i < ao.size(); i++) {
			map.put(ao.get(i)[0].toString(), Integer.parseInt(ao.get(i)[1].toString()));
			All = All + Integer.parseInt(ao.get(i)[1].toString());
		}
		map.put("All", All);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Integer> getAllPayNumbers2(Date startDate,
			Date endDate) {
		String hql = "select status, count(*) from QQPermonthRecode2 where checkTime >= :startDate and checkTime <= :endDate group by status";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Integer All = 0;
		for (int i = 0; i < ao.size(); i++) {
			map.put(ao.get(i)[0].toString(), Integer.parseInt(ao.get(i)[1].toString()));
			All = All + Integer.parseInt(ao.get(i)[1].toString());
		}
		map.put("All", All);
		return map;
	}


	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPaySuccess(Date startDate, Date endDate) {
		String sql = "select p.PRODUCTDESCRIBE,COUNT(*) FROM RS_QQPERMONTHRECORD q,RS_PRODUCT p WHERE p.STATUS = '有效' and q.COST = p.COST and p.BUSID = 103 and q.STATUS ='扣费成功' and q.CHECKTIME >= :startDate and q.CHECKTIME <= :endDate group by p.PRODUCTDESCRIBE";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < ao.size(); i++) {
			map.put(ao.get(i)[0].toString(), Integer.parseInt(ao.get(i)[1].toString()));
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPayFailed(Date startDate, Date endDate) {
		String sql = "select p.PRODUCTDESCRIBE,COUNT(*) FROM RS_QQPERMONTHRECORD q,RS_PRODUCT p WHERE p.STATUS = '有效' and q.COST = p.COST and p.BUSID = 103 and q.STATUS ='扣费失败' and q.CHECKTIME >= :startDate and q.CHECKTIME <= :endDate group by p.PRODUCTDESCRIBE";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < ao.size(); i++) {
			map.put(ao.get(i)[0].toString(), Integer.parseInt(ao.get(i)[1].toString()));
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPaySuccessPhoneNumbers(Date startDate,
			Date endDate) {
		String sql = "select p.PRODUCTDESCRIBE,count(distinct phoneNumber) from RS_QQPERMONTHRECORD　q,RS_PRODUCT p where p.STATUS = '有效' and p.COST = q.COST and p.BUSID = 103 and q.STATUS ='扣费成功' and q.CHECKTIME >= :startDate and q.CHECKTIME <= :endDate group by p.PRODUCTDESCRIBE";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < ao.size(); i++) {
			map.put(ao.get(i)[0].toString(), Integer.parseInt(ao.get(i)[1].toString()));
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPayFailedPhoneNumbers(Date startDate,
			Date endDate) {
		String sql = "select p.PRODUCTDESCRIBE,count(distinct phoneNumber) from RS_QQPERMONTHRECORD　q,RS_PRODUCT p where p.STATUS = '有效' and p.COST = q.COST and p.BUSID = 103 and q.STATUS ='扣费失败' and q.CHECKTIME >= :startDate and q.CHECKTIME <= :endDate group by p.PRODUCTDESCRIBE";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < ao.size(); i++) {
			map.put(ao.get(i)[0].toString(), Integer.parseInt(ao.get(i)[1].toString()));
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getAllPayMoneyByBusId(Date startDate, Date endDate) {
		String sql = "select sum(PAYMONEY) FROM RS_QQPERMONTHRECORD where STATUS = '扣费成功' and CHECKTIME >= :startDate and CHECKTIME <= :endDate";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		ArrayList<BigDecimal> pn = (ArrayList<BigDecimal>) query.list();
		Double allPayMoney = pn.get(0).doubleValue();
		if (allPayMoney == null) {
			return 0.0;
		}
		return allPayMoney;
	}
	
}
