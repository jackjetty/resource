package com.traffic.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.traffic.dao.MoveCarDao;
import com.traffic.pojo.TbCarMove;
import com.traffic.pojo.TbInfoPic;
@Repository("moveCarDao")
public class MoveCarDaoImpl extends BaseDaoImpl<TbCarMove> implements MoveCarDao {

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMoveCar(Date startTime, Date endTime,
			String reportPhoneNumber, String carNumber, String carMoveState) {
		String hql = "from TbCarMove";
		Query query = null;
		if(reportPhoneNumber==null){
			if(carNumber==null){
				if(carMoveState==null){
					hql = hql +" where reportTime >=:startTime and reportTime <=:endTime order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
				}else{
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and carMoveState = :carMoveState order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("carMoveState", carMoveState);
				}
			}else{
				if(carMoveState==null){
					hql = hql +" where reportTime >=:startTime and reportTime <=:endTime and carNumber = :carNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("carNumber", carNumber);
				}else{
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and carMoveState = :carMoveState and carNumber = :carNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("carMoveState", carMoveState);
					query.setParameter("carNumber", carNumber);
				}
			}
		}else{
			if(carNumber==null){
				if(carMoveState==null){
					hql = hql +" where reportTime >=:startTime and reportTime <=:endTime and reportPhoneNumber = :reportPhoneNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
				}else{
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and carMoveState = :carMoveState and reportPhoneNumber = :reportPhoneNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("carMoveState", carMoveState);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
				}
			}else{
				if(carMoveState==null){
					hql = hql +" where reportTime >=:startTime and reportTime <=:endTime and carNumber = :carNumber and reportPhoneNumber = :reportPhoneNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("carNumber", carNumber);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
				}else{
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and carMoveState = :carMoveState and carNumber = :carNumber and reportPhoneNumber = :reportPhoneNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("carMoveState", carMoveState);
					query.setParameter("carNumber", carNumber);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
				}
			}
			
		}
		ArrayList<TbCarMove> ta = (ArrayList<TbCarMove>) query.list();
		return ta.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbCarMove> getMoveCar(Date startTime, Date endTime,
			String reportPhoneNumber, String carNumber, Integer start,
			Integer pageSize, String carMoveState) {
		String hql = "from TbCarMove";
		Query query = null;
		if(reportPhoneNumber==null){
			if(carNumber==null){
				if(carMoveState==null){
					hql = hql +" where reportTime >=:startTime and reportTime <=:endTime order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
				}else{
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and carMoveState = :carMoveState order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("carMoveState", carMoveState);
				}
			}else{
				if(carMoveState==null){
					hql = hql +" where reportTime >=:startTime and reportTime <=:endTime and carNumber = :carNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("carNumber", carNumber);
				}else{
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and carMoveState = :carMoveState and carNumber = :carNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("carMoveState", carMoveState);
					query.setParameter("carNumber", carNumber);
				}
			}
		}else{
			if(carNumber==null){
				if(carMoveState==null){
					hql = hql +" where reportTime >=:startTime and reportTime <=:endTime and reportPhoneNumber = :reportPhoneNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
				}else{
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and carMoveState = :carMoveState and reportPhoneNumber = :reportPhoneNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("carMoveState", carMoveState);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
				}
			}else{
				if(carMoveState==null){
					hql = hql +" where reportTime >=:startTime and reportTime <=:endTime and carNumber = :carNumber and reportPhoneNumber = :reportPhoneNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("carNumber", carNumber);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
				}else{
					hql = hql
							+ " where reportTime >= :startTime and reportTime <= :endTime and carMoveState = :carMoveState and carNumber = :carNumber and reportPhoneNumber = :reportPhoneNumber order by reportTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("startTime", startTime);
					query.setParameter("endTime", endTime);
					query.setParameter("carMoveState", carMoveState);
					query.setParameter("carNumber", carNumber);
					query.setParameter("reportPhoneNumber", reportPhoneNumber);
				}
			}
			
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbCarMove> ta = (ArrayList<TbCarMove>) query.list();
		return ta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getMovePic() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String hql = "from TbInfoPic";
		Query query = getSession().createQuery(hql);
		ArrayList<TbInfoPic> m = (ArrayList<TbInfoPic>) query.list();
		for (TbInfoPic ta : m) {
			map.put(ta.getId().getRecordNo(), ta.getPicLocalStore());
		}
		return map;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbInfoPic> getCarMovePic(String carMoveId) {
		Query query = getSession().createQuery(
				"from TbInfoPic where recordNo = :recordNo");
		query.setParameter("recordNo", carMoveId);
		ArrayList<TbInfoPic> tap = (ArrayList<TbInfoPic>) query
				.list();
		return tap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object[]> getMoveCarAddress(String carMoveId) {
		String hql = "select locationX,locationY,address from TbCarMove where carMoveId = :carMoveId";
		Query query = getSession().createQuery(hql);
		query.setParameter("carMoveId", carMoveId);
		ArrayList<Object[]> addr = (ArrayList<Object[]>) query.list();
		return addr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbCarMove getMoveCarById(String carMoveId) {
		String hql = "from TbCarMove where carMoveId = :carMoveId";
		Query query = getSession().createQuery(hql);
		query.setParameter("carMoveId", carMoveId);
		ArrayList<TbCarMove> addr = (ArrayList<TbCarMove>) query.list();
		return addr.get(0);
	}

	@Override
	public void exchangeStatus(TbCarMove tc) {
		getSession().update(tc);
		getSession().flush();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMcNumber() {
		String id = "CM";
		String hql = "from TbCarMove where carMoveId like :carMoveId";
		Query query = getSession().createQuery(hql);
		query.setParameter("carMoveId", "%"+id+"%");
		List<TbCarMove> list = query.list();
		return list.size();
	}
	
	@Override
	public void updateMoveCarStateSuccess(String managerName, String carMoveId) {
		String hql = "update TbCarMove set accepter = :managerName,carMoveState = '审核通过' where carMoveId = :carMoveId";
		Query query =getSession().createQuery(hql);
		query.setParameter("managerName", managerName)
				.setParameter("carMoveId", carMoveId).executeUpdate();
		getSession().flush();
		
	}

	@Override
	public void updateMoveCarStateFail(String carMoveId) {
		String hql = "update TbCarMove set carMoveState = '待定' where carMoveId =:carMoveId";
		getSession().createQuery(hql).setParameter("carMoveId", carMoveId)
				.executeUpdate();
		getSession().flush();
		
	}

}
