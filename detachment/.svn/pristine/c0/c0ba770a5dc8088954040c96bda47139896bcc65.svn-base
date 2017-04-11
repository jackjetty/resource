package com.detachment.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.detachment.dao.VoteDao;
import com.detachment.pojo.TbVote;
@Repository
public class VoteDaoImpl extends BaseDaoImpl<TbVote> implements VoteDao {

	@Override
	public List findVoteRes(String hql) {
		return this.getSession().createQuery(hql).list();
	}


}
