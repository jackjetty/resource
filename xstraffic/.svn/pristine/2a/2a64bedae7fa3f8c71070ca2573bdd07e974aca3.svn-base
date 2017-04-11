package com.traffic.dao.impl;

import java.util.ArrayList;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.traffic.dao.ProblemsDao;
import com.traffic.pojo.TbProblems;

@Repository("problemsDao")
public class ProblemsDaoImpl extends BaseDaoImpl<TbProblems> implements ProblemsDao{

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbProblems> getProblemsByNum() {
		String hql=" from TbProblems order by newId()";
		Query query = getSession().createQuery(hql);
		ArrayList<TbProblems> pb=(ArrayList<TbProblems>) query.list();
		return pb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getProblemsSize(String problem) {
		String hql="from TbProblems ";
		Query query=null;
		if(problem==null){
			query = getSession().createQuery(hql);
		}else{
			hql +=" where problem like :problem";
			query = getSession().createQuery(hql);
			query.setParameter("problem", "%"+problem+"%");
		}
		ArrayList<TbProblems> ta = (ArrayList<TbProblems>) query.list();
		return ta.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbProblems> getProblems(String problem, Integer start,
			Integer pageSize) {
		Query query=null;
		String hql = "from TbProblems ";
		if(problem==null){
			query = getSession().createQuery(hql);
			
		}else{
			hql+=" where problem like :problem ";
			query = getSession().createQuery(hql);
			query.setParameter("problem", "%"+problem+"%");
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		ArrayList<TbProblems> ta = (ArrayList<TbProblems>) query.list();
		return ta;
	}

	@Override
	public void addProblem(TbProblems pb) {
		getSession().save(pb);
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbProblems> getProblemsByChoiceType(String choiceType) {
		String hql=" from TbProblems where choiceType = :choiceType order by newId()";
		Query query = getSession().createQuery(hql);
		query.setParameter("choiceType", choiceType);
		ArrayList<TbProblems> pb=(ArrayList<TbProblems>) query.list();
		return pb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TbProblems getTbProblemsById(Integer id) {
		String hql=" from TbProblems where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		ArrayList<TbProblems> pb=(ArrayList<TbProblems>) query.list();
		if(pb!=null){
			return pb.get(0);
		}else{
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TbProblems> getAllProblems() {
		String hql=" from TbProblems";
		Query query = getSession().createQuery(hql);
		ArrayList<TbProblems> pb=(ArrayList<TbProblems>) query.list();
		return pb;
	}

}
