package com.traffic.wap.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.traffic.wap.service.VoteWapService;
@Scope("prototype")
@Controller("voteWapAction")
public class VoteWapAction {
	
	@Autowired
	private VoteWapService voteWapService;
	private HashMap<String,Object> result;
	 
	private String code;
	private String state;
	private String voteId;
	private String electorStr;
	private String id;
	
	
	
	
	 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getElectorStr() {
		return electorStr;
	}

	public void setElectorStr(String electorStr) {
		this.electorStr = electorStr;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getVoteId() {
		return voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId; 
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String voteWapPage(){ 
		return  "error";
		/* 
		HttpServletRequest request = ServletActionContext.getRequest();  
		String url=request.getRequestURL()+"?"+request.getQueryString();
		this.result= voteWapService.voteWapPage(code,voteId,url);
		System.out.println(((TbVote)this.result.get("tbVote")).getContent());
		if(((Boolean)this.result.get("success")).booleanValue()){
			
		}
		 
		return "success";*/
	}
	
	public String voteWapElectors(){ 
		HttpServletRequest request = ServletActionContext.getRequest();  
		String url=request.getRequestURL()+"?"+request.getQueryString();
		this.result= voteWapService.voteWapElectors(voteId,url);
		/*System.out.println(((TbVote)this.result.get("tbVote")).getContent());
		if(((Boolean)this.result.get("success")).booleanValue()){
			
		}*/
		 
		return "success";
	}
	
	public String voteWapDoIntroduce(){ 
		HttpServletRequest request = ServletActionContext.getRequest();  
		String url=request.getRequestURL()+"?"+request.getQueryString();
		this.result= voteWapService.voteWapElectoDetail(id,url);
		/*System.out.println(((TbVote)this.result.get("tbVote")).getContent());
		if(((Boolean)this.result.get("success")).booleanValue()){
			
		}*/
		 
		return "success";
	}
	
	public String voteWapRank(){ 
		HttpServletRequest request = ServletActionContext.getRequest();  
		String url=request.getRequestURL()+"?"+request.getQueryString();
		this.result= voteWapService.voteWapTopElectors(voteId,url);
		/*System.out.println(((TbVote)this.result.get("tbVote")).getContent());
		if(((Boolean)this.result.get("success")).booleanValue()){
			
		}*/
		 
		return "success";
	}
	
	public String voteWapCheckTender(){ 
		this.result= voteWapService.voteWapCheckTender(voteId);
		/*System.out.println(((TbVote)this.result.get("tbVote")).getContent());
		if(((Boolean)this.result.get("success")).booleanValue()){
			
		}*/
		 
		return "success";
	}
	
	
	
	
	public String voteWapDoVote(){ 
		this.result= voteWapService.voteWapDoVote(voteId, electorStr);
		/*System.out.println(((TbVote)this.result.get("tbVote")).getContent());
		if(((Boolean)this.result.get("success")).booleanValue()){
			
		}*/
		 
		return "success";
	}

}
