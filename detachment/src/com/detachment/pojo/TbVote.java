package com.detachment.pojo;

import java.util.Set;
import java.util.TreeSet;

public class TbVote {
	private int topicId;//主题ID
	private String topicName;
	private Set<TbCandidate> candidate=new TreeSet<TbCandidate>();//候选人
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Set<TbCandidate> getCandidate() {
		return candidate;
	}
	public void setCandidate(Set<TbCandidate> candidate) {
		this.candidate = candidate;
	}

}
