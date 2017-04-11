package com.detachment.pojo;

import java.util.Comparator;
import java.util.Set;

public class TbCandidate implements Comparator<TbCandidate>{
	private int c_id;
	private String name;
	private String sex;
	private String birth;
	private String politicsStatus;//政治面貌
	private String nation;//民族
	private String degree;
	private String workUnit;
	private String photo;
	private String startWork;
	private String tel;
	private Set<TbAward> awards;
	private String feeling;
	private String intro;
	private Set<TbVoteRes> voteRes;
	private Set<TbVote> votes;
	private int sortId;
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPoliticsStatus() {
		return politicsStatus;
	}
	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getWorkUnit() {
		return workUnit;
	}
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFeeling() {
		return feeling;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getStartWork() {
		return startWork;
	}
	public void setStartWork(String startWork) {
		this.startWork = startWork;
	}
	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Set<TbVoteRes> getVoteRes() {
		return voteRes;
	}
	public void setVoteRes(Set<TbVoteRes> voteRes) {
		this.voteRes = voteRes;
	}
	public Set<TbVote> getVotes() {
		return votes;
	}
	public void setVotes(Set<TbVote> votes) {
		this.votes = votes;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	@Override
	public int compare(TbCandidate o, TbCandidate o1) {
		if(o.getSortId()>o1.getSortId()){
			return 1;
		}
		if(o.getSortId()<o1.getSortId()){
			return -1;
		}
		return 0;
	}
	public Set<TbAward> getAwards() {
		return awards;
	}
	public void setAwards(Set<TbAward> awards) {
		this.awards = awards;
	}

}
