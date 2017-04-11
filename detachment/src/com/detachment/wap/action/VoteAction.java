package com.detachment.wap.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.rising.wei.bean.OauthAccessTokenBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.detachment.pojo.TbCandidate;
import com.detachment.wap.json.VoteRes;
import com.detachment.web.service.VoteService; 

@Controller("voteAction")
@Scope("prototype")
public class VoteAction implements SessionAware{
	private Set<TbCandidate> candidates;
	@Autowired
	private VoteService voteService;
	private TbCandidate candidate;
	private int topID;
	private String[] hobbys;
	private List<VoteRes> result;
	private String code;
	Map<String,String> map;
	private String url="https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";
	OauthAccessTokenBean oauth;
	private int id;
	String openId;
	private Map<String, Object> session;
	
	public String getAllCandidate() throws Exception{
		candidates=voteService.getAllCandidate(topID);
		return "success";
	}
	
	public String submitVote(){
		map=voteService.submitVote(hobbys,(String)session.get("OPENID"));
//		session.remove("OPENID");
		if(map.containsKey("error")){
			return "input";
		}else
		return "success";
	}
	
	public String voteIntro() throws Exception{
/*		Map<String,String> params=new HashMap<String,String>();
		params.put("appid", "wx687156ff2c631bcc");
		params.put("secret", "4832cb4ecf2973716eb08d8a97fdea67");
		params.put("code", code);
		String  jsonStr = HttpKit.get(url, params);
		if(StringUtils.isNotEmpty(jsonStr)){
			JSONObject obj = JSONObject.parseObject(jsonStr);
			if(obj.get("errcode") != null){
//				throw new Exception(obj.getString("errmsg"));
			}else{
			oauth=JSONObject.toJavaObject(obj, OauthAccessTokenBean.class);
//			System.out.println(obj);
			session.put("OPENID", oauth.getOpenid());
			}
		}*/
		session.put("OPENID", openId);
		return "success";
	}
	
	public String getCandidateDetail(){
		candidate=voteService.getCandidateById(id);
		return "success";
	}

	public Set<TbCandidate> getCandidates() {
		return candidates;
	}
	
	
	public String getVoteRes(){
		result=voteService.getVoteRes();
		return "success";
	}

	public void setCandidates(Set<TbCandidate> candidates) {
		this.candidates = candidates;
	}

	public int getTopID() {
		return topID;
	}

	public void setTopID(int topID) {
		this.topID = topID;
	}

	public List<VoteRes> getResult() {
		return result;
	}

	public void setResult(List<VoteRes> result) {
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public OauthAccessTokenBean getOauth() {
		return oauth;
	}

	public void setOauth(OauthAccessTokenBean oauth) {
		this.oauth = oauth;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getHobbys() {
		return hobbys;
	}

	public void setHobbys(String[] hobbys) {
		this.hobbys = hobbys;
	}

	public TbCandidate getCandidate() {
		return candidate;
	}

	public void setCandidate(TbCandidate candidate) {
		this.candidate = candidate;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}

}
