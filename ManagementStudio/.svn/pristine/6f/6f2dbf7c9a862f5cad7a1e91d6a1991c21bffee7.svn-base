package com.rising.management.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import com.rising.management.bean.WinnerList;
import com.rising.management.common.DateUtil;
import com.rising.management.dao.WinnerListDao;

@Component("winnerListDao")
public class WinnerListDaoImpl extends BaseDaoImpl implements WinnerListDao{

	@Override
	public void addWinner(WinnerList w) {
		getSession().save(w);
		getSession().flush();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<WinnerList> getWinnerList(String place, Integer start,
			Integer pageSize) {
		String hql = "from WinnerList";
		Query query = null;
		if(place == null){
			 query = getSession().createQuery(hql);
		}else{
			hql = hql + " where place = :place";
			query = getSession().createQuery(hql);
			query.setParameter("place",place);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<WinnerList> aw = (ArrayList<WinnerList>) query.list();
		return aw;
	}

	@Override
	public Integer getWinnerListListSize(String place) {
			String hql = "select count(*) from WinnerList";
			Query query = null;
			if(place == null){
				 query = getSession().createQuery(hql);
			}else{
				hql = hql + " where place = :place";
				query = getSession().createQuery(hql);
				query.setParameter("place",place);
			}
			Number num = (Number) query.list().get(0);
			return num.intValue();
	}

	@Override
	public void deleteById(Integer id) {
		String hql = "delete WinnerList where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getWinnerPhoneNumberByPrize(Integer prizeId) {
		DateUtil d = new DateUtil();
		HashMap<String,Date> map = d.getThisYearTime();
		String hql = "select distinct a.phoneNumber from WinnerList a,Prize b where a.prize = b.name and b.prizeId = :prizeId and a.winTime >= :start and a.winTime <= :end";
		Query query = getSession().createQuery(hql);
		query.setParameter("prizeId",prizeId);
		query.setParameter("start", map.get("Start"));
		query.setParameter("end",map.get("End"));
		ArrayList<String> as = (ArrayList<String>) query.list();
		return as;
	}

	@Override
	public Integer getWinningInfoList(String phoneNumber, String winTime,Integer prizeId) {
		String hql = "select count(*) from WinningInfo";
		Query query = null;
		if(phoneNumber==null){
			if(prizeId !=null){
				hql = hql + " where lotteryId = :lotteryId";
				if(winTime==null){
					hql = hql+" order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("lotteryId", prizeId);
				}else{
					hql = hql + " and to_char(winTime,'yyyy-MM-dd') = :winTime order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("lotteryId", prizeId);
					query.setParameter("winTime", winTime);
				}
			}else{
				if(winTime==null){
					hql = hql+" order by winTime desc";
					query = getSession().createQuery(hql);
				}else{
					hql = hql + " where to_char(winTime,'yyyy-MM-dd') = :winTime order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("winTime", winTime);
				}
			}
			
		}else{
			hql = hql + " where phoneNumber = :phoneNumber";
			if(prizeId !=null){
				hql = hql + " and lotteryId = :lotteryId";
				if(winTime==null){
					hql = hql+" order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("phoneNumber", phoneNumber);
					query.setParameter("lotteryId", prizeId);
				}else{
					hql = hql + " and to_char(winTime,'yyyy-MM-dd') = :winTime order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("phoneNumber", phoneNumber);
					query.setParameter("lotteryId", prizeId);
					query.setParameter("winTime", winTime);
				}
			}else{
				if(winTime==null){
					hql = hql+" order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("phoneNumber", phoneNumber);
				}else{
					hql = hql + " and to_char(winTime,'yyyy-MM-dd') = :winTime order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("winTime", winTime);
					query.setParameter("phoneNumber", phoneNumber);
				}
			}
		}
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object[]> getWinningInfo(Integer pageSize,
			Integer start, String phoneNumber, String winTime,Integer prizeId) {
		String hql = "select w.phoneNumber,p.name,w.winTime,p.describe,w.hasSendMessage from WinningInfo w,Prize p";
		Query query = null;
		if(phoneNumber==null){
			if(prizeId !=null){
				hql = hql + " where w.lotteryId = p.prizeId and lotteryId = :lotteryId";
				if(winTime==null){
					hql = hql+" order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("lotteryId", prizeId);
				}else{
					hql = hql + " and to_char(winTime,'yyyy-MM-dd') = :winTime order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("lotteryId", prizeId);
					query.setParameter("winTime", winTime);
				}
			}else{
				if(winTime==null){
					hql = hql+" where w.lotteryId = p.prizeId order by winTime desc";
					query = getSession().createQuery(hql);
				}else{
					hql = hql + " where w.lotteryId = p.prizeId and to_char(winTime,'yyyy-MM-dd') = :winTime order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("winTime", winTime);
				}
			}
			
		}else{
			hql = hql + " where phoneNumber = :phoneNumber and w.lotteryId = p.prizeId";
			if(prizeId !=null){
				hql = hql + " and lotteryId = :lotteryId";
				if(winTime==null){
					hql = hql+" order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("phoneNumber", phoneNumber);
					query.setParameter("lotteryId", prizeId);
				}else{
					hql = hql + " and to_char(winTime,'yyyy-MM-dd') = :winTime order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("phoneNumber", phoneNumber);
					query.setParameter("lotteryId", prizeId);
					query.setParameter("winTime", winTime);
				}
			}else{
				if(winTime==null){
					hql = hql+" order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("phoneNumber", phoneNumber);
				}else{
					hql = hql + " and to_char(winTime,'yyyy-MM-dd') = :winTime order by winTime desc";
					query = getSession().createQuery(hql);
					query.setParameter("winTime", winTime);
					query.setParameter("phoneNumber", phoneNumber);
				}
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<Object[]> w = (ArrayList<Object[]>) query.list();
		return w;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<HashMap<String, Object>> getUserWinningInfo(
			ArrayList<String> phoneNumbers) {
		ArrayList<HashMap<String, Object>> ahso = new ArrayList<HashMap<String,Object>>();
		String sql = "select phoneNumber,max(winTime) as winningTime FROM RS_WinningInfo where phoneNumber in :phoneNumbers group by phoneNumber";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameterList("phoneNumbers",phoneNumbers);
		ArrayList<Object[]> ao = (ArrayList<Object[]>) query.list();
		for (Object[] objects : ao) {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("phoneNumber",objects[0]);
			Date d = (Date) objects[1];
			if(d==null){
				map.put("winningTime", "");
			}else{
				map.put("winningTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
			}
			ahso.add(map);
		}
		ArrayList<String> newNumbers = getPhoneNumbers(ahso);
		for (String phoneNumber : phoneNumbers) {
			if(!newNumbers.contains(phoneNumber)){
				HashMap<String,Object> map2 = new HashMap<String, Object>();
				map2.put("phoneNumber", phoneNumber);
				map2.put("winningTime", "");
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
	public Integer getWinningInfoList2(String phoneNumber) {
		String hql = "select count(*) from WinningInfo where phoneNumber = :phoneNumber";
		Query query = getSession().createQuery(hql);
		query.setParameter("phoneNumber", phoneNumber);
		Number num = (Number) query.list().get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object[]> getWinningInfoList2(Integer pageSize,
			Integer start, String phoneNumber) {
		String hql = "select w.phoneNumber,p.name,w.winTime,p.describe,w.hasSendMessage from WinningInfo w,Prize p where w.phoneNumber = :phoneNumber and w.lotteryId = p.prizeId order by w.winTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("phoneNumber", phoneNumber);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<Object[]> w = (ArrayList<Object[]>) query.list();
		return w;
	}
	
}
