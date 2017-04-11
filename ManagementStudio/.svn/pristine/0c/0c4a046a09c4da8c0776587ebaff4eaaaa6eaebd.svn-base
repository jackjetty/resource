package com.rising.management.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import com.rising.management.bean.UserCheckin;
import com.rising.management.dao.UserCheckinDao;

@Component("userCheckinDao")
public class UserCheckinDaoImpl extends BaseDaoImpl implements UserCheckinDao {

	@Override
	public Integer getUserCheckinSize(String phoneNumber, String checkinTime) {
		String hql = "select count(*) from UserCheckin";
		Query query = null;
		if (phoneNumber == null) {
			if (checkinTime == null) {
				query = getSession().createQuery(hql);
			} else {
				hql = hql
						+ " where to_char(checkinTime,'yyyy-MM-dd') = :checkinTime";
				query = getSession().createQuery(hql);
				query.setParameter("checkinTime", checkinTime);
			}
		} else {
			if (checkinTime == null) {
				hql = hql + " where phoneNumber = :phoneNumber";
				query = getSession().createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
			} else {
				hql = hql
						+ " where phoneNumber = :phoneNumber and to_char(checkinTime,'yyyy-MM-dd') = :checkinTime ";
				query = getSession().createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("checkinTime", checkinTime);
			}
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserCheckin> getUserCheckin(String phoneNumber,
			String checkinTime, Integer pageSize, Integer start) {
		String hql = "from UserCheckin";
		Query query = null;
		if (phoneNumber == null) {
			if (checkinTime == null) {
				hql = hql + " order by checkinTime desc";
				query = getSession().createQuery(hql);
			} else {
				hql = hql
						+ " where to_char(checkinTime,'yyyy-MM-dd') = :checkinTime order by checkinTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("checkinTime", checkinTime);
			}
		} else {
			if (checkinTime == null) {
				hql = hql
						+ " where phoneNumber = :phoneNumber order by checkinTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
			} else {
				hql = hql
						+ " where phoneNumber = :phoneNumber and to_char(checkinTime,'yyyy-MM-dd') = :checkinTime order by checkinTime desc";
				query = getSession().createQuery(hql);
				query.setParameter("phoneNumber", phoneNumber);
				query.setParameter("checkinTime", checkinTime);
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<UserCheckin> rs = (ArrayList<UserCheckin>) query.list();
		return rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getUserCheckinNumbers(String placeName,
			Date startTime, Date endTime, String toTime) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (placeName == null) {
			if ("1".equals(toTime)) {
				String sql = "select TO_CHAR(CHECKINTIME,'yyyy-MM-dd') AA,count(distinct PHONENUMBER) from RS_USERCHECKIN where "
						+ "CHECKINTIME >= :starttime and CHECKINTIME <= :endtime  group by TO_CHAR(CHECKINTIME,'yyyy-MM-dd')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			} else if ("3".equals(toTime)) {
				String sql = "select TO_CHAR(CHECKINTIME,'YYYY-MM') AA,count(distinct PHONENUMBER) from RS_USERCHECKIN where "
						+ "CHECKINTIME >= :starttime and CHECKINTIME <= :endtime  group by TO_CHAR(CHECKINTIME,'YYYY-MM')";
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
				String sql = "select TO_CHAR(c.CHECKINTIME,'yyyy-MM-dd') AA,count(distinct c.PHONENUMBER) from RS_USERCHECKIN c,RS_USERINFO u where "
						+ "c.CHECKINTIME >= :starttime and c.CHECKINTIME <= :endtime and u.ADDRESS =:address and  c.PHONENUMBER = u.PHONENUMBER group by TO_CHAR(c.CHECKINTIME,'yyyy-MM-dd')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("address", placeName);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			} else if ("3".equals(toTime)) {
				String sql = "select TO_CHAR(c.CHECKINTIME,'yyyy-MM') AA,count(distinct c.PHONENUMBER) from RS_USERCHECKIN c,RS_USERINFO u where "
						+ "c.CHECKINTIME >= :starttime and c.CHECKINTIME <= :endtime and u.ADDRESS =:address and  c.PHONENUMBER = u.PHONENUMBER group by TO_CHAR(c.CHECKINTIME,'yyyy-MM')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("address", placeName);
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
	public Integer getUserCheckinNumbers(String placeName, Date startDate,
			Date endDate) {
		Query query = null;
		if (!"".equals(placeName)) {
			String hql = "select count(distinct c.phoneNumber) from UserCheckin c,Place p,UserInfo u where p.code = u.address and u.phoneNumber = c.phoneNumber "
					+ "and c.checkinTime >= :startDate and c.checkinTime <= :endDate and p.name = :placeName";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("placeName", placeName);
		} else {
			String hql = "select count(distinct phoneNumber) from UserCheckin where checkinTime >= :startDate and checkinTime <= :endDate";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<HashMap<String, Object>> getUserCheckinInfo(
			ArrayList<String> phoneNumbers) {
		String sql = "select phoneNumber,Max(checkInTime) as lastTime from RS_UserCheckIn where phoneNumber in :phoneNumbers group by phoneNumber";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameterList("phoneNumbers", phoneNumbers);
		ArrayList<HashMap<String, Object>> ahso = new ArrayList<HashMap<String, Object>>();
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		for (Object[] objects : ao) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("phoneNumber", objects[0]);
			Date d = (Date) objects[1];
			if (d == null) {
				map.put("lastTime", "");
			} else {
				map.put("lastTime",
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
			}
			ahso.add(map);
		}
		ArrayList<String> newNumbers = getPhoneNumbers(ahso);
		for (String phoneNumber : phoneNumbers) {
			if (!newNumbers.contains(phoneNumber)) {
				HashMap<String, Object> map2 = new HashMap<String, Object>();
				map2.put("phoneNumber", phoneNumber);
				map2.put("lastTime", "");
				ahso.add(map2);
			}
		}
		return ahso;
	}

	private ArrayList<String> getPhoneNumbers(
			ArrayList<HashMap<String, Object>> ahm) {
		ArrayList<String> phoneNumbers = new ArrayList<String>();
		for (HashMap<String, Object> hashMap : ahm) {
			phoneNumbers.add(String.valueOf(hashMap.get("phoneNumber")));
		}
		return phoneNumbers;
	}

	@Override
	public Integer getUserCheckinSize2(String phoneNumber) {
		String hql = "select count(*) from UserCheckin where phoneNumber =:phoneNumber";
		Query query = getSession().createQuery(hql);
		query.setParameter("phoneNumber", phoneNumber);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserCheckin> getUserCheckin2(String phoneNumber,
			Integer pageSize, Integer start) {
		String hql = "from UserCheckin where phoneNumber =:phoneNumber order by checkinTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("phoneNumber", phoneNumber);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<UserCheckin> rs = (ArrayList<UserCheckin>) query.list();
		return rs;
	}

}
