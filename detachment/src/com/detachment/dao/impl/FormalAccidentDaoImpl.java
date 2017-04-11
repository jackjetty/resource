package com.detachment.dao.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.detachment.dao.FormalAccidentDao;
import com.detachment.pojo.TbFormalAccident;
import com.detachment.pojo.TbInfoPic;
import com.detachment.util.CommonUtil;

@Repository("formalAccidentDao")
public class FormalAccidentDaoImpl extends BaseDaoImpl<TbFormalAccident>
		implements FormalAccidentDao {

	@Override
	public Integer getAccidentListSize(Date startDate, Date endDate,String accidentState,
			String reportPhoneNumber, String nickName, String emergencyCall,
			String departmentId) {
		String hql = "select count(*) from TbFormalAccident where";
		Query query = null;
		if(accidentState==null){
			if (departmentId == null) {
				if (nickName == null) {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
						} else {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);

						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and emergencyCall = :emergencyCall";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
						} else {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and emergencyCall = :emergencyCall";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);

						}
					}
				} else {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and reportOpenId in (select openId from TbWeiUser where nickName = :nickName )";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
						} else {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and reportOpenId in (select openId from TbWeiUser where nickName = :nickName )";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);

						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and emergencyCall = :emergencyCall and reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) ";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
						} else {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and emergencyCall = :emergencyCall and reportOpenId in (select openId from TbWeiUser where nickName = :nickName )";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);

						}
					}

				}
			} else {
				hql = hql + " departmentId = :departmentId";
				if (nickName == null) {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
						} else {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("departmentId", departmentId);

						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime and emergencyCall = :emergencyCall";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("departmentId", departmentId);
						} else {
							hql = hql
									+ "and reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and emergencyCall = :emergencyCall";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("departmentId", departmentId);
						}
					}
				} else {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime and reportOpenId in (select openId from TbWeiUser where nickName = :nickName )";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("departmentId", departmentId);
						} else {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and reportOpenId in (select openId from TbWeiUser where nickName = :nickName )";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("departmentId", departmentId);

						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime and emergencyCall = :emergencyCall and reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) ";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("departmentId", departmentId);
						} else {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and emergencyCall = :emergencyCall and reportOpenId in (select openId from TbWeiUser where nickName = :nickName )";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("departmentId", departmentId);

						}
					}

				}
			}
			
		}else{//========================
			hql = hql
					+ " accidentState = :accidentState and  ";
			if (departmentId == null) {
				if (nickName == null) {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("accidentState", accidentState);

						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and emergencyCall = :emergencyCall";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and emergencyCall = :emergencyCall";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("accidentState", accidentState);
						}
					}
				} else {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and reportOpenId in (select openId from TbWeiUser where nickName = :nickName )";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and reportOpenId in (select openId from TbWeiUser where nickName = :nickName )";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("accidentState", accidentState);

						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and emergencyCall = :emergencyCall and reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) ";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and emergencyCall = :emergencyCall and reportOpenId in (select openId from TbWeiUser where nickName = :nickName )";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);

						}
					}

				}
			} else {
				hql = hql + " departmentId = :departmentId";
				if (nickName == null) {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("departmentId", departmentId);
							query.setParameter("accidentState", accidentState);

						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime and emergencyCall = :emergencyCall";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("departmentId", departmentId);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ "and reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and emergencyCall = :emergencyCall";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("departmentId", departmentId);
							query.setParameter("accidentState", accidentState);
						}
					}
				} else {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime and reportOpenId in (select openId from TbWeiUser where nickName = :nickName )";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("departmentId", departmentId);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and reportOpenId in (select openId from TbWeiUser where nickName = :nickName )";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("departmentId", departmentId);
							query.setParameter("accidentState", accidentState);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime and emergencyCall = :emergencyCall and reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) ";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("departmentId", departmentId);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and reportTime >= :startTime and reportTime <= :endTime and reportPhoneNumber = :reportPhoneNumber and emergencyCall = :emergencyCall and reportOpenId in (select openId from TbWeiUser where nickName = :nickName )";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("departmentId", departmentId);
							query.setParameter("accidentState", accidentState);
						}
					}

				}
			}
		}

		Number n = (Number) query.list().get(0);
		return n.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbFormalAccident> getAccident(Date startDate,
			Date endDate, String accidentState,String reportPhoneNumber, String nickName,
			Integer start, Integer pageSize, String emergencyCall,
			String departmentId) {
		String hql = "select new TbFormalAccident(a.formlAccidentId,a.reportTime,a.reportPhoneNumber,a.reporterType,a.address,a.locationX,a.locationY,a.accidentState,a.departmentId,b.realName,b.nickname,a.accepter,a.emergencyCall) from TbFormalAccident a,TbWeiUser b where a.reportOpenId = b.openId ";
		Query query = null;
		
		if(accidentState==null){
			if (departmentId != null) {
				hql = hql + "and a.departmentId = :departmentId";
				if (nickName == null) {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
						}
					}
				} else {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and  a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("nickName", nickName);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
						}
					}
				}
			}else{
				if (nickName == null) {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
						}
					}
				} else {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and  a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
						}
					}
				}
			}
		}else{//========================
			hql = hql + "and a.accidentState = :accidentState  ";
			if (departmentId != null) {
				hql = hql + "and a.departmentId = :departmentId";
				if (nickName == null) {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("accidentState", accidentState);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("accidentState", accidentState);
						}
					}
				} else {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and  a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("accidentState", accidentState);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						}
					}
				}
			}else{
				if (nickName == null) {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("accidentState", accidentState);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("accidentState", accidentState);
						}
					}
				} else {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and  a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("accidentState", accidentState);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						}
					}
				}
			}
		}

		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbFormalAccident> ata = (ArrayList<TbFormalAccident>) query
				.list();
		return ata;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbFormalAccident> getAccidentJsp(String openId,
			Integer start, Integer pageSize) {
		String hql = "from TbFormalAccident where reportOpenId=:openId order by reportTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("openId", openId);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbFormalAccident> ta = (ArrayList<TbFormalAccident>) query
				.list();
		return ta;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> getTbAddress(String accidentId) {
		String hql = "select locationX,locationY,address from TbFormalAccident where formlAccidentId = :accidentId";
		Query query = getSession().createQuery(hql);
		query.setParameter("accidentId", accidentId);
		ArrayList<Object[]> addr = (ArrayList<Object[]>) query.list();
		return addr;
	}

	@Override
	public void saveprocedureMessage(String managerName, String accidentId) {
		String hql = "update TbFormalAccident set acceptTime = :acceptTime,accepter = :managerName,accidentState = '审核通过' where formlAccidentId = :accidentId";
		getSession().createQuery(hql).setParameter("acceptTime", new Date())
				.setParameter("managerName", managerName)
				.setParameter("accidentId", accidentId).executeUpdate();
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbInfoPic> getTbAccidentPic(String accidentId) {
		Query query = getSession().createQuery(
				"from TbInfoPic where recordNo = :recordNo");
		query.setParameter("recordNo", accidentId);
		ArrayList<TbInfoPic> tap = (ArrayList<TbInfoPic>) query.list();
		return tap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbFormalAccident> getAccident(Date startDate,
			Date endDate, String reportPhoneNumber, String nickName,
			String emergencyCall, String departmentId,String accidentState) {
		String hql = "select new TbFormalAccident(a.formlAccidentId,a.reportTime,a.reportPhoneNumber,a.reporterType,a.address,a.locationX,a.locationY,a.accidentState,a.departmentId,b.realName,b.nickname,a.accepter,a.emergencyCall) from TbFormalAccident a,TbWeiUser b where a.reportOpenId = b.openId ";
		Query query = null;
		
		
		
		if(accidentState==null){
			
			if (departmentId != null) {
				hql = hql + "and a.departmentId = :departmentId";
				if (nickName == null) {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
						}
					}
				} else {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and  a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("nickName", nickName);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
						}
					}
				}
			}else{
				if (nickName == null) {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
						}
					}
				} else {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and  a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
						}
					}
				}
			}
			
			
			
			
		}else{//==========================
			hql = hql + " and a.accidentState = :accidentState ";
			
			if (departmentId != null) {
				hql = hql + "and a.departmentId = :departmentId";
				if (nickName == null) {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("accidentState", accidentState);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("accidentState", accidentState);
						}
					}
				} else {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and  a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("accidentState", accidentState);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("departmentId", departmentId);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						}
					}
				}
			}else{
				if (nickName == null) {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("accidentState", accidentState);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("accidentState", accidentState);
						}
					}
				} else {
					if (emergencyCall == null) {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and  a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("nickName", nickName);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("accidentState", accidentState);
						}
					} else {
						if (reportPhoneNumber == null) {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						} else {
							hql = hql
									+ " and a.reportTime >= :startTime and a.reportTime <= :endTime and a.reportPhoneNumber = :reportPhoneNumber and a.emergencyCall = :emergencyCall and a.reportOpenId in (select openId from TbWeiUser where nickName = :nickName ) order by a.reportTime desc";
							query = getSession().createQuery(hql);
							query.setParameter("startTime", startDate);
							query.setParameter("endTime", endDate);
							query.setParameter("reportPhoneNumber",
									reportPhoneNumber);
							query.setParameter("emergencyCall", Integer.parseInt(emergencyCall)==0?false:true);
							query.setParameter("nickName", nickName);
							query.setParameter("accidentState", accidentState);
						}
					}
				}
			}
		}
		
		ArrayList<TbFormalAccident> ata = (ArrayList<TbFormalAccident>) query
				.list();
		return ata;
	}

	@Override
	public String getNextFormalAccidentId(String prefix) {
		Session session = getSession();
		String nextAccidentId = prefix + CommonUtil.getDateForm("yyMMdd");
		int currentNum = 0;
		String hql = "select max(formlAccidentId) from TbFormalAccident where formlAccidentId like :accidentname";
		Query query = session.createQuery(hql);
		query.setParameter("accidentname", nextAccidentId + "%");
		List list = query.list();
		if (list.size() > 0 && list.get(0) != null) {
			String maxAccidentId = list.get(0).toString();
			currentNum = CommonUtil
					.getCurrentNum(maxAccidentId, nextAccidentId);
		}
		currentNum++;
		DecimalFormat aDecimalFormat = (DecimalFormat) DecimalFormat
				.getInstance();
		aDecimalFormat.applyPattern("0000");
		return nextAccidentId + aDecimalFormat.format(currentNum);
	}

	@Override
	public void updateDepartment(String departmentId, ArrayList<String> as) {
		String hql = "update TbFormalAccident set departmentId = :departmentId where formlAccidentId in :as";
		getSession().createQuery(hql)
				.setParameter("departmentId", departmentId)
				.setParameterList("as", as).executeUpdate();
		getSession().flush();
	}

	@Override
	public void updateAccidentStateFailed(String accidentId,String state) {
		String hql = "update TbFormalAccident set accidentState = :state where formlAccidentId =:accidentId";
		getSession().createQuery(hql).setParameter("accidentId", accidentId).setParameter("state", state)
				.executeUpdate();
		getSession().flush();

	}

	@Override
	public Integer getListSizeByDepartmentId(String departmentId) {
		String hql = "select count(*) from TbFormalAccident";
		Number n = null;
		if(departmentId==null){
			n = (Number) getSession().createQuery(hql).list().get(0);
		}else{
			hql = hql + " where departmentId = :departmentId";
			n = (Number) getSession().createQuery(hql).setParameter("departmentId", departmentId).list().get(0);
		}
		return n.intValue();
	}


	@SuppressWarnings("unchecked")
	@Override
	public TbFormalAccident getAccidentByAccidentId(String accidentId) {
		String hql = "select new TbFormalAccident(a.formlAccidentId,a.reportTime,a.reportPhoneNumber,a.reporterType,a.address,a.locationX,a.locationY,a.accidentState,a.departmentId,b.realName,b.nickname,a.accepter,a.emergencyCall) from TbFormalAccident a,TbWeiUser b where a.reportOpenId = b.openId and a.formlAccidentId= :accidentId";
		Query query = null;
		query = getSession().createQuery(hql);
		query.setParameter("accidentId", accidentId);
		ArrayList<TbFormalAccident> ata = (ArrayList<TbFormalAccident>) query
				.list();
		if(ata.size()>0){
			return ata.get(0);
		}else{
			return null;
		}
		
	}



	@Override
	public Integer getListSizeChuJingByDepartmentId(String departmentId) {
		String hql = "select count(*) from TbFormalAccident where emergencyCall = true ";
		Number n = null;
		if(departmentId==null){
			n = (Number) getSession().createQuery(hql).list().get(0);
		}else{
			hql = hql + " and departmentId = :departmentId";
			n = (Number) getSession().createQuery(hql).setParameter("departmentId", departmentId).list().get(0);
		}
		return n.intValue();
	}


	@Override
	public void updateAccidentStateMalicious(String managerName,
			String accidentId) {
		String hql = "update TbFormalAccident set acceptTime = :acceptTime,accepter = :managerName,accidentState = '恶意上报' where formlAccidentId = :accidentId";
		getSession().createQuery(hql).setParameter("acceptTime", new Date())
				.setParameter("managerName", managerName)
				.setParameter("accidentId", accidentId).executeUpdate();
		getSession().flush();
		
	}

}