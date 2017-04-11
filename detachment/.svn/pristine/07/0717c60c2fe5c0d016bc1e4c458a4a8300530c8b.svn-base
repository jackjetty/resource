package com.detachment.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.CandidateDao;
import com.detachment.dao.VoteDao;
import com.detachment.dao.VoteResDao;
import com.detachment.dao.WeiUserDao;
import com.detachment.pojo.TbCandidate;
import com.detachment.pojo.TbVote;
import com.detachment.pojo.TbVoteRes;
import com.detachment.pojo.TbWeiUser;
import com.detachment.util.CommonUtil;
import com.detachment.wap.json.VoteRes;
import com.detachment.web.service.VoteService;
@Service("voteService")
public class VoteServiceImpl implements VoteService {
	@Autowired
	private VoteDao voteDao;
	@Autowired
	private VoteResDao voteResDao;
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private WeiUserDao weiUserDao;
	
	private static final String[] color={"#E9967A","#00FFFF","#000000","#A52A2A","#00FF00","#008080","#FFFF00","#1874CD","#FF7F50","#6495ED","#DC143C"};

	@Override
	public void insertVote(TbVote vote) {
		voteDao.save(vote);		
	}

	@Override
	public TbVote getVoteById(int id) {
		return voteDao.findById(id);
	}

	@Override
	public Set<TbCandidate> getAllCandidate(int topID) {
		return getVoteById(topID).getCandidate();
	}

	@Override
	public Map<String,String> submitVote(String[] hobby,String openId) {
		Map<String,String> result=new HashMap<String,String>();
		if(openId!=null&&!"".equals(openId)){
			TbWeiUser weiUser=weiUserDao.findById(openId);
			if(weiUser==null){
				result.put("error", "投票失败，请先关注绍兴交警官方微信（微信号：shaoxingjiaojing)再投，谢谢！");
			}else{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String s=sdf.format(new Date());
				Date date=null;
				try {
					date =  sdf.parse(s) ;
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String hql="select count(*) from TbVoteRes res where res.voterID=? and res.voteTime=?";
				int count=voteResDao.findCount(hql, openId,date);
				if(count>0){
					result.put("error", "投票失败，一天只能投一次。");
				}else{
					for(String h:hobby){
						TbVoteRes res=new TbVoteRes();
						res.setCandidate(candidateDao.findById(Integer.valueOf(h)));
						res.setVoterID(openId);
						res.setVoteTime(date);
						voteResDao.save(res);
					}
					result.put("success", "投票成功。");
				}
			}
		}else{
			result.put("error", "投票失败，请先关注绍兴交警官方微信（微信号：shaoxingjiaojing)再投，谢谢！");
		}
		return result;
	}

	@Override
	public List<VoteRes> getVoteRes() {
		String hql="select b.name,count(*)  from TbVoteRes a,TbCandidate b,TbVote c "
				+ "where a.candidate.c_id=b.c_id and c.topicId=2 and b= any elements(c.candidate) GROUP BY a.candidate.c_id,b.name";
		List list=voteDao.findVoteRes(hql);
		Object[] rows;
		List<VoteRes> voteList=new ArrayList<VoteRes>();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
				rows=(Object[]) iter.next();
				VoteRes res=new VoteRes();
				res.setName(CommonUtil.trim(rows[0]));
				res.setData(CommonUtil.trim(rows[1]));
				res.setColor(color[new Random().nextInt(10)]);
				voteList.add(res);
		}
		return voteList;
	}

	@Override
	public TbCandidate getCandidateById(int id) {
		return candidateDao.findById(id);
	}

}
