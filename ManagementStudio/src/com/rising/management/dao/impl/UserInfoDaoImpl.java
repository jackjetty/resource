package com.rising.management.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.rising.management.bean.OperateLog;
import com.rising.management.bean.UserInfo;
import com.rising.management.dao.UserInfoDao;
import com.rising.management.vo.UserInfoVo;

@Component("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl implements UserInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public Integer getAllUserNumber() {
		String hql = "from UserInfo";
		Query query = getSession().createQuery(hql);
		ArrayList<UserInfo> au = (ArrayList<UserInfo>) query.list();
		return au.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getNewRegisterNum(String placeName, Date startTime,
			Date endTime) {
		String hql = "from UserInfo";
		Query query = null;
		if (placeName == null) {
			if (startTime == null && endTime == null) {
				query = getSession().createQuery(hql);
			} else {
				hql = hql
						+ "  where  registerTime >= :startTime and registerTime <= :endTime";
				query = getSession().createQuery(hql);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}

		} else {
			if (startTime == null && endTime == null) {
				hql = hql
						+ " where address in (select code from Place where name = :name)";
				query = getSession().createQuery(hql);
				query.setParameter("name", placeName);
			} else {
				hql = hql
						+ "  where  registerTime >= :startTime and registerTime <= :endTime and address in (select code from Place where name = :name)";
				query = getSession().createQuery(hql);
				query.setParameter("name", placeName);
				query.setParameter("startTime", startTime);
				query.setParameter("endTime", endTime);
			}
		}
		ArrayList<UserInfo> au = (ArrayList<UserInfo>) query.list();
		return au.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public float getMaxScore() {
		String hql = "from UserInfo order by AllScore desc ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(1);
		ArrayList<UserInfo> au = (ArrayList<UserInfo>) query.list();
		return au.get(0).getAllScore();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getUserNumberByScoreRound(float min, float max) {
		Query query = null;
		if (min == 0) {
			String hql = "from UserInfo where AllScore >= :min and AllScore <= :max ";
			query = getSession().createQuery(hql);
			query.setFloat("min", min);
			query.setFloat("max", max);
		} else {
			String hql = "from UserInfo where AllScore > :min and AllScore <= :max ";
			query = getSession().createQuery(hql);
			query.setFloat("min", min);
			query.setFloat("max", max);
		}
		ArrayList<UserInfo> au = (ArrayList<UserInfo>) query.list();
		return au.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getAllUserNumber(String placeName) {
		String hql = "from UserInfo where address in (select code from Place where name = :name)";
		Query query = getSession().createQuery(hql);
		query.setParameter("name", placeName);
		ArrayList<OperateLog> ao = (ArrayList<OperateLog>) query.list();
		return ao.size();

	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getAllUserNumber2(String placeName) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Query query = null;
		if ("null".equals(placeName) || placeName == null) {
			String hql = "select p.name,count(u.phoneNumber) from UserInfo u,Place p where p.code = u.address group by p.name";
			query = getSession().createQuery(hql);
		} else {
			String hql = "select p.name,count(u.phoneNumber) from UserInfo u,Place p where p.code = u.address and p.name= :placeName group by p.name";
			query = getSession().createQuery(hql);
			query.setParameter("placeName", placeName);
		}
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		for (Object[] obj : ao) {
			map.put(obj[0].toString(), obj[1]);
		}
		return map;

	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getNewRegisterNum2(String placeName,
			Date startTime, Date endTime) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Query query = null;
		if ("null".equals(placeName) || placeName == null) {
			String hql = "select p.name,count(u.phoneNumber) from UserInfo u,Place p where p.code = u.address and u.registerTime >= :startTime and u.registerTime <= :endTime group by p.name";
			query = getSession().createQuery(hql);
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);
		} else {
			String hql = "select p.name,count(u.phoneNumber) from UserInfo u,Place p where p.code = u.address and u.registerTime >= :startTime and p.name = :placeName and u.registerTime <= :endTime group by p.name";
			query = getSession().createQuery(hql);
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);
			query.setParameter("placeName", placeName);
		}
		ArrayList<Object[]> au = (ArrayList<Object[]>) query.list();
		for (Object[] obj : au) {
			map.put(obj[0].toString(), obj[1]);
		}
		return map;

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserInfo> getUserByScoreRound(Integer scoreMin,
			Integer scoreMax) {
		String hql = "from UserInfo";
		Query query = null;
		if (scoreMin != null && scoreMax != null) {
			hql = hql
					+ " where AllScore >= :min and AllScore <= :max order by AllScore desc";
			query = getSession().createQuery(hql);
			query.setFloat("min", scoreMin);
			query.setFloat("max", scoreMax);
		} else if (scoreMin != null) {
			hql = hql + " where AllScore >= :min order by AllScore desc";
			query = getSession().createQuery(hql);
			query.setFloat("min", scoreMin);
		} else if (scoreMax != null) {
			hql = hql + " where AllScore <= :max order by AllScore desc";
			query = getSession().createQuery(hql);
			query.setFloat("max", scoreMax);
		}
		ArrayList<UserInfo> au = (ArrayList<UserInfo>) query.list();
		return au;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserInfo findUser(String phoneNumber, String place) {
		String hql = "from UserInfo where phoneNumber = :phoneNumber and address = :place";
		Query query = getSession().createQuery(hql);
		query.setParameter("phoneNumber", phoneNumber);
		query.setParameter("place", place);
		ArrayList<UserInfo> au = (ArrayList<UserInfo>) query.list();
		if (au == null || au.size() == 0) {
			return null;
		} else {
			return au.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getUserPlaceByNumber(String number) {
		String hql = "select b.name from UserInfo a,Place b where a.address = b.code and a.phoneNumber = :number";
		Query query = getSession().createQuery(hql);
		query.setParameter("number", number);
		ArrayList<String> as = (ArrayList<String>) query.list();
		if (as != null && as.size() > 0) {
			return as.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, String> getUserPlace() {
		String hql = "select a.phoneNumber as phoneNumber,b.name as place from UserInfo a,Place b where a.address = b.code";
		Query query = getSession().createQuery(hql);
		ArrayList<Object[]> as = (ArrayList<Object[]>) query.list();
		HashMap<String, String> map = new HashMap<String, String>();
		for (Object[] data : as) {
			map.put(String.valueOf(data[0]), String.valueOf(data[1]));
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getNewUserListSize(String phoneNum, String regDate,
			String lastTraDate, Date startTime, Date endTime, String p) {
		Query query = null;
		if (lastTraDate == null) {
			String hql = "select PHONENUMBER,REGISTERTIME from RS_USERINFO where REGISTERTIME >= :startTime and REGISTERTIME <= :endTime"
					+ "  and ADDRESS = :address";
			if (regDate == null) {
				if (phoneNum == null) {
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
				} else {
					hql = hql + " and PHONENUMBER = :phoneNumber";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("phoneNumber", phoneNum);
				}
			} else {
				if (phoneNum == null) {
					hql = hql
							+ " and to_char(REGISTERTIME,'yyyy-MM-dd') = :registerTime";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("registerTime", regDate);
				} else {
					hql = hql
							+ " and to_char(REGISTERTIME,'yyyy-MM-dd') = :registerTime and PHONENUMBER = :phoneNumber";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("phoneNumber", phoneNum);
					query.setParameter("registerTime", regDate);
				}
			}
		} else {
			String hql = "select u.PHONENUMBER,u.REGISTERTIME from (SELECT PHONENUMBER,max(ORDERTIME) as LASTTIME from　RS_ORDERINFO GROUP BY PHONENUMBER)　O,RS_USERINFO u where u.PHONENUMBER = O.PHONENUMBER"
					+ " and u.REGISTERTIME >= :startTime and u.REGISTERTIME <= :endTime and u.ADDRESS = :address and to_char(O.LASTTIME,'yyyy-MM-dd') = :lastTraDate";
			if (regDate == null) {
				if (phoneNum == null) {
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("lastTraDate", lastTraDate);
				} else {
					hql = hql + " and u.PHONENUMBER = :phoneNumber";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("phoneNumber", phoneNum);
					query.setParameter("lastTraDate", lastTraDate);
				}
			} else {
				if (phoneNum == null) {
					hql = hql
							+ " and to_char(u.REGISTERTIME,'yyyy-MM-dd') = :registerTime";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("registerTime", regDate);
					query.setParameter("lastTraDate", lastTraDate);
				} else {
					hql = hql
							+ " and to_char(u.REGISTERTIME,'yyyy-MM-dd') = :registerTime and u.PHONENUMBER = :phoneNumber";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("phoneNumber", phoneNum);
					query.setParameter("registerTime", regDate);
					query.setParameter("lastTraDate", lastTraDate);
				}
			}
		}
		ArrayList<Object[]> as = (ArrayList<Object[]>) query.list();
		return as.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object[]> getPhoneAndTime(String phoneNum, String regDate,
			String lastTraDate, Integer start, Integer pageSize,
			Date startTime, Date endTime, String p) {
		Query query = null;
		if (lastTraDate == null) {
			String hql = "select PHONENUMBER,REGISTERTIME from RS_USERINFO where REGISTERTIME >= :startTime and REGISTERTIME <= :endTime"
					+ "  and ADDRESS = :address";
			if (regDate == null) {
				if (phoneNum == null) {
					hql = hql + " order by registertime desc";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
				} else {
					hql = hql + " and PHONENUMBER = :phoneNumber";
					hql = hql + " order by registertime desc";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("phoneNumber", phoneNum);
				}
			} else {
				if (phoneNum == null) {
					hql = hql
							+ " and to_char(REGISTERTIME,'yyyy-MM-dd') = :registerTime";
					hql = hql + " order by registertime desc";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("registerTime", regDate);
				} else {
					hql = hql
							+ " and to_char(REGISTERTIME,'yyyy-MM-dd') = :registerTime and PHONENUMBER = :phoneNumber";
					hql = hql + " order by registertime desc";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("phoneNumber", phoneNum);
					query.setParameter("registerTime", regDate);
				}
			}
		} else {
			String hql = "select u.PHONENUMBER,u.REGISTERTIME from (SELECT PHONENUMBER,max(ORDERTIME) as LASTTIME FROM　RS_ORDERINFO GROUP BY PHONENUMBER)　O,RS_USERINFO u where u.PHONENUMBER = O.PHONENUMBER"
					+ " and u.REGISTERTIME >= :startTime and u.REGISTERTIME <= :endTime and u.ADDRESS = :address and to_char(O.LASTTIME,'yyyy-MM-dd') = :lastTraDate";
			if (regDate == null) {
				if (phoneNum == null) {
					hql = hql + " order by registertime desc";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("lastTraDate", lastTraDate);
				} else {
					hql = hql + " and u.PHONENUMBER = :phoneNumber";
					hql = hql + " order by registertime desc";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("phoneNumber", phoneNum);
					query.setParameter("lastTraDate", lastTraDate);
				}
			} else {
				if (phoneNum == null) {
					hql = hql
							+ " and to_char(u.REGISTERTIME,'yyyy-MM-dd') = :registerTime";
					hql = hql + " order by registertime desc";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("registerTime", regDate);
					query.setParameter("lastTraDate", lastTraDate);
				} else {
					hql = hql
							+ " and to_char(u.REGISTERTIME,'yyyy-MM-dd') = :registerTime and u.PHONENUMBER = :phoneNumber";
					hql = hql + " order by registertime desc";
					query = getSession().createSQLQuery(hql);
					query.setParameter("address", p);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("phoneNumber", phoneNum);
					query.setParameter("registerTime", regDate);
					query.setParameter("lastTraDate", lastTraDate);
				}
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<Object[]> as = (ArrayList<Object[]>) query.list();
		return as;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getNewResNum(Date startTime, Date endTime) {
		String hql = "from UserInfo where registerTime >= :startTime and registerTime <= :endTime ";
		Query query = getSession().createQuery(hql);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		ArrayList<UserInfo> as = (ArrayList<UserInfo>) query.list();
		return as.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getAll() {
		String hql = "from UserInfo ";
		Query query = getSession().createQuery(hql);
		ArrayList<OperateLog> ao = (ArrayList<OperateLog>) query.list();
		return ao.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getMonthByUserInfo(String placeName,
			Date startTime, Date endTime, String toTime) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (placeName == null) {
			if ("1".equals(toTime)) {
				String sql = "select TO_CHAR(REGISTERTIME,'YYYY') AA,count(registertime) from RS_USERINFO where "
						+ "registertime >= :starttime and registertime <= :endtime group by TO_CHAR(REGISTERTIME,'YYYY')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			} else if ("2".equals(toTime)) {
				String sql = "select TO_CHAR(REGISTERTIME,'YYYY-MM') AA,count(registertime) from RS_USERINFO where "
						+ "registertime >= :starttime and registertime <= :endtime group by TO_CHAR(REGISTERTIME,'YYYY-MM')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			} else if ("4".equals(toTime)) {
				String sql = "select TO_CHAR(REGISTERTIME,'yyyy-MM-dd') AA,count(registertime) from RS_USERINFO where "
						+ "registertime >= :starttime and registertime <= :endtime group by TO_CHAR(REGISTERTIME,'yyyy-MM-dd')";
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
				String sql = "select TO_CHAR(REGISTERTIME,'YYYY') AA,count(registertime) from RS_USERINFO where "
						+ "registertime >= :starttime and registertime <= :endtime and address =:address group by TO_CHAR(REGISTERTIME,'YYYY')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("address", placeName);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			} else if ("2".equals(toTime)) {
				String sql = "select TO_CHAR(REGISTERTIME,'YYYY-MM') AA,count(registertime) from RS_USERINFO where "
						+ "registertime >= :starttime and registertime <= :endtime and address =:address group by TO_CHAR(REGISTERTIME,'YYYY-MM')";
				SQLQuery query = getSession().createSQLQuery(sql);
				query.setParameter("address", placeName);
				query.setParameter("starttime", startTime);
				query.setParameter("endtime", endTime);
				ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();
				for (Object[] objs : list) {
					map.put(objs[0].toString(), objs[1]);
				}
			} else if ("4".equals(toTime)) {
				String sql = "select TO_CHAR(REGISTERTIME,'yyyy-MM-dd') AA,count(registertime) from RS_USERINFO where "
						+ "registertime >= :starttime and registertime <= :endtime and address =:address group by TO_CHAR(REGISTERTIME,'yyyy-MM-dd')";
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

	@SuppressWarnings("unchecked")
	@Override
	public Integer getSumUserInfo(String placeCode, Date startTime) {
		String hql = "from UserInfo where registerTime <= :starttime";
		Query query = null;
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		if (placeCode == null) {
			query = getSession().createQuery(hql);
			query.setParameter("starttime", startTime);
			list = (ArrayList<UserInfo>) query.list();
		} else {
			hql = hql + " and address = :address";
			query = getSession().createQuery(hql);
			query.setParameter("address", placeCode);
			query.setParameter("starttime", startTime);
			list = (ArrayList<UserInfo>) query.list();
		}
		return list.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getCreditsListSize(String phoneNum, String regDate,
			String lastTraDate, Integer min, Integer max) {
		Query query = null;
		if (min == 0) {
			if (lastTraDate == null) {
				String hql = "select PHONENUMBER,REGISTERTIME,ALLSCORE,ADDRESS from RS_USERINFO where ALLSCORE >= :min and ALLSCORE <= :max";
				if (regDate == null) {
					if (phoneNum == null) {
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
					} else {
						hql = hql + " and PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
					}
				} else {
					if (phoneNum == null) {
						hql = hql
								+ " and to_char(REGISTERTIME,'yyyy-MM-dd') = :registerTime";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("registerTime", regDate);
					} else {
						hql = hql
								+ " and to_char(REGISTERTIME,'yyyy-MM-dd') = :registerTime and PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
						query.setParameter("registerTime", regDate);
					}
				}
			} else {
				String hql = "select u.PHONENUMBER,u.REGISTERTIME,u.ALLSCORE,u.ADDRESS from (SELECT PHONENUMBER,max(ORDERTIME) as LASTTIME FROM　RS_ORDERINFO GROUP BY PHONENUMBER)　O,RS_USERINFO u where u.PHONENUMBER = O.PHONENUMBER"
						+ " and u.ALLSCORE >= :min and u.ALLSCORE <= :max and to_char(O.LASTTIME,'yyyy-MM-dd') = :lastTraDate";
				if (regDate == null) {
					if (phoneNum == null) {
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("lastTraDate", lastTraDate);
					} else {
						hql = hql + " and u.PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
						query.setParameter("lastTraDate", lastTraDate);
					}
				} else {
					if (phoneNum == null) {
						hql = hql
								+ " and to_char(u.REGISTERTIME,'yyyy-MM-dd') = :registerTime";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("registerTime", regDate);
						query.setParameter("lastTraDate", lastTraDate);
					} else {
						hql = hql
								+ " and to_char(u.REGISTERTIME,'yyyy-MM-dd') = :registerTime and u.PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
						query.setParameter("registerTime", regDate);
						query.setParameter("lastTraDate", lastTraDate);
					}
				}
			}
		} else {
			if (lastTraDate == null) {
				String hql = "select PHONENUMBER,REGISTERTIME,ALLSCORE,ADDRESS from RS_USERINFO where ALLSCORE > :min and ALLSCORE <= :max";
				if (regDate == null) {
					if (phoneNum == null) {
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
					} else {
						hql = hql + " and PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
					}
				} else {
					if (phoneNum == null) {
						hql = hql
								+ " and to_char(REGISTERTIME,'yyyy-MM-dd') = :registerTime";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("registerTime", regDate);
					} else {
						hql = hql
								+ " and to_char(REGISTERTIME,'yyyy-MM-dd') = :registerTime and PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
						query.setParameter("registerTime", regDate);
					}
				}
			} else {
				String hql = "select u.PHONENUMBER,u.REGISTERTIME,u.ALLSCORE,u.ADDRESS from (SELECT PHONENUMBER,max(ORDERTIME) as LASTTIME FROM　RS_ORDERINFO GROUP BY PHONENUMBER)　O,RS_USERINFO u where u.PHONENUMBER = O.PHONENUMBER"
						+ " and u.ALLSCORE > :min and u.ALLSCORE <= :max and to_char(O.LASTTIME,'yyyy-MM-dd') = :lastTraDate";
				if (regDate == null) {
					if (phoneNum == null) {
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("lastTraDate", lastTraDate);
					} else {
						hql = hql + " and u.PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
						query.setParameter("lastTraDate", lastTraDate);
					}
				} else {
					if (phoneNum == null) {
						hql = hql
								+ " and to_char(u.REGISTERTIME,'yyyy-MM-dd') = :registerTime";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("registerTime", regDate);
						query.setParameter("lastTraDate", lastTraDate);
					} else {
						hql = hql
								+ " and to_char(u.REGISTERTIME,'yyyy-MM-dd') = :registerTime and u.PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
						query.setParameter("registerTime", regDate);
						query.setParameter("lastTraDate", lastTraDate);
					}
				}
			}
		}
		ArrayList<Object[]> as = (ArrayList<Object[]>) query.list();
		return as.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object[]> getPhoneAndTime(String phoneNum, String regDate,
			String lastTraDate, Integer start, Integer pageSize, Integer min,
			Integer max) {
		Query query = null;
		if (min == 0) {
			if (lastTraDate == null) {
				String hql = "select PHONENUMBER,REGISTERTIME,ALLSCORE,ADDRESS from RS_USERINFO where ALLSCORE >= :min and ALLSCORE <= :max";
				if (regDate == null) {
					if (phoneNum == null) {
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
					} else {
						hql = hql + " and PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
					}
				} else {
					if (phoneNum == null) {
						hql = hql
								+ " and to_char(REGISTERTIME,'yyyy-MM-dd') = :registerTime";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("registerTime", regDate);
					} else {
						hql = hql
								+ " and to_char(REGISTERTIME,'yyyy-MM-dd') = :registerTime and PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
						query.setParameter("registerTime", regDate);
					}
				}
			} else {
				String hql = "select u.PHONENUMBER,u.REGISTERTIME,u.ALLSCORE,u.ADDRESS from (SELECT PHONENUMBER,max(ORDERTIME) as LASTTIME FROM　RS_ORDERINFO GROUP BY PHONENUMBER)　O,RS_USERINFO u where u.PHONENUMBER = O.PHONENUMBER"
						+ " and u.ALLSCORE >= :min and u.ALLSCORE <= :max and to_char(O.LASTTIME,'yyyy-MM-dd') = :lastTraDate";
				if (regDate == null) {
					if (phoneNum == null) {
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("lastTraDate", lastTraDate);
					} else {
						hql = hql + " and u.PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
						query.setParameter("lastTraDate", lastTraDate);
					}
				} else {
					if (phoneNum == null) {
						hql = hql
								+ " and to_char(u.REGISTERTIME,'yyyy-MM-dd') = :registerTime";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("registerTime", regDate);
						query.setParameter("lastTraDate", lastTraDate);
					} else {
						hql = hql
								+ " and to_char(u.REGISTERTIME,'yyyy-MM-dd') = :registerTime and u.PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
						query.setParameter("registerTime", regDate);
						query.setParameter("lastTraDate", lastTraDate);
					}
				}
			}
		} else {
			if (lastTraDate == null) {
				String hql = "select PHONENUMBER,REGISTERTIME,ALLSCORE,ADDRESS from RS_USERINFO where ALLSCORE > :min and ALLSCORE <= :max";
				if (regDate == null) {
					if (phoneNum == null) {
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
					} else {
						hql = hql + " and PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
					}
				} else {
					if (phoneNum == null) {
						hql = hql
								+ " and to_char(REGISTERTIME,'yyyy-MM-dd') = :registerTime";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("registerTime", regDate);
					} else {
						hql = hql
								+ " and to_char(REGISTERTIME,'yyyy-MM-dd') = :registerTime and PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
						query.setParameter("registerTime", regDate);
					}
				}
			} else {
				String hql = "select u.PHONENUMBER,u.REGISTERTIME,u.ALLSCORE,u.ADDRESS from (SELECT PHONENUMBER,max(ORDERTIME) as LASTTIME FROM　RS_ORDERINFO GROUP BY PHONENUMBER)　O,RS_USERINFO u where u.PHONENUMBER = O.PHONENUMBER"
						+ " and u.ALLSCORE > :min and u.ALLSCORE <= :max and to_char(O.LASTTIME,'yyyy-MM-dd') = :lastTraDate";
				if (regDate == null) {
					if (phoneNum == null) {
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("lastTraDate", lastTraDate);
					} else {
						hql = hql + " and u.PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
						query.setParameter("lastTraDate", lastTraDate);
					}
				} else {
					if (phoneNum == null) {
						hql = hql
								+ " and to_char(u.REGISTERTIME,'yyyy-MM-dd') = :registerTime";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("registerTime", regDate);
						query.setParameter("lastTraDate", lastTraDate);
					} else {
						hql = hql
								+ " and to_char(u.REGISTERTIME,'yyyy-MM-dd') = :registerTime and u.PHONENUMBER = :phoneNumber";
						query = getSession().createSQLQuery(hql);
						query.setParameter("min", min);
						query.setParameter("max", max);
						query.setParameter("phoneNumber", phoneNum);
						query.setParameter("registerTime", regDate);
						query.setParameter("lastTraDate", lastTraDate);
					}
				}
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<Object[]> as = (ArrayList<Object[]>) query.list();
		return as;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<HashMap<String, Object>> findLostUser(String phoneNumber) {
		String hql = "";
		SQLQuery query = null;
		ArrayList<HashMap<String, Object>> ahso = new ArrayList<HashMap<String, Object>>();
		hql = "select u.PhoneNumber,u.RegisterTime,u.AllScore,p.name from RS_UserInfo u,RS_Place p where u.Address = p.code and u.PhoneNumber = :phoneNumber";
		query = getSession().createSQLQuery(hql);
		query.setParameter("phoneNumber", phoneNumber);
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		if (ao != null && ao.size() > 0) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("phoneNumber", ao.get(0)[0]);
			Date d = (Date) ao.get(0)[1];
			if(d==null){
				map.put("registerTime", "");
			}else{
				map.put("registerTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
			}
			map.put("address", ao.get(0)[3]);
			map.put("score", ao.get(0)[2]);
			ahso.add(map);
			return ahso;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<HashMap<String, Object>> findLostUserInfo(
			ArrayList<String> phoneNumbers) {
		String hql = "";
		SQLQuery query = null;
		ArrayList<HashMap<String, Object>> ahso = new ArrayList<HashMap<String, Object>>();
		hql = "select u.PhoneNumber,u.RegisterTime,u.AllScore,p.name from RS_UserInfo u,RS_Place p where u.Address = p.code and u.PhoneNumber in :phoneNumbers";
		query = getSession().createSQLQuery(hql);
		query.setParameterList("phoneNumbers", phoneNumbers);
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		
		if (ao != null && ao.size() > 0) {
			for (Object[] objects : ao) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("phoneNumber", objects[0]);
				Date d = (Date) objects[1];
				if(d==null){
					map.put("registerTime", "");
				}else{
					map.put("registerTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
				}
				map.put("address", objects[3]);
				map.put("score", objects[2]);
				ahso.add(map);
			}
			return ahso;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<HashMap<String, Object>> findLostUser2() {
		String hql = "";
		SQLQuery query = null;
		Session session = getSession();
		ArrayList<HashMap<String, Object>> ahso = new ArrayList<HashMap<String, Object>>();
		hql = "select u.PhoneNumber,u.RegisterTime,u.AllScore,p.name from RS_UserInfo u,RS_Place p where u.Address = p.code and u.PhoneNumber in (select phoneNumber from RS_UserInfo where phoneNumber not in (select distinct phoneNumber from RS_OrderInfo))";
		query = session.createSQLQuery(hql);
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		if (ao != null && ao.size() > 0) {
			for (Object[] objects : ao) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("phoneNumber", objects[0]);
				Date d = (Date) objects[1];
				if(d==null){
					map.put("registerTime", "");
				}else{
					map.put("registerTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
				}
				map.put("address", objects[3]);
				map.put("score", objects[2]);
				ahso.add(map);
			}
			return ahso;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserInfoVo findByPhoneNumber(String phoneNumber) {
		String hql = "select user.phoneNumber,user.registerTime,user.allScore,p.name from UserInfo user,Place p where user.address = p.code and user.phoneNumber = :phoneNumber";
		Query query = getSession().createQuery(hql);
		query.setParameter("phoneNumber",phoneNumber);
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		UserInfoVo user = new UserInfoVo();
		if(ao!=null && ao.size()>0){
			user.setPhoneNumber(String.valueOf(ao.get(0)[0]));
			Date d = (Date) ao.get(0)[1];
			user.setRegisterTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
			user.setAllScore((Float)ao.get(0)[2]);
			user.setAddress(String.valueOf(ao.get(0)[3]));
			return user;
		}else{
			return null;
		}
	}
}
