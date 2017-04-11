package com.traffic.pojo;

/**
 * 
 * @description 被选举人表
 * @author pl
 * @createTime 2016年4月22日
 */

public class TbElector implements java.io.Serializable {

	// Fields

	private String id;
	private String voteId;//投票主题Id
	private Integer number;//当前编号
	private String name;//名称
	private String introduce;//介绍
	private Integer votes;//被投票数量
	private String img;//小图
	private Double percent;//百分比
	private String workUnit;//工作单位
	private String achievement;//参评事迹
	private String area;//户籍所属中队

	// Constructors

	/** default constructor */
	public TbElector() {
	}

	/** minimal constructor */
	public TbElector(String id, String voteId, Double percent) {
		this.id = id;
		this.voteId = voteId;
		this.percent = percent;
	}

	/** full constructor */
	public TbElector(String id, String voteId, Integer number, String name,
			String introduce, Integer votes, String img, Double percent, String workUnit, String achievement, String area) {
		this.id = id;
		this.voteId = voteId;
		this.number = number;
		this.name = name;
		this.introduce = introduce;
		this.votes = votes;
		this.img = img;
		this.percent = percent;
		this.workUnit = workUnit;
		this.achievement = achievement;
		this.area = area;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVoteId() {
		return this.voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getVotes() {
		return this.votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Double getPercent() {
		return this.percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}