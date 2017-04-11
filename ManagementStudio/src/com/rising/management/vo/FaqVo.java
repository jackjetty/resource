package com.rising.management.vo; 
import com.rising.management.bean.Faq;
import com.rising.management.common.RequestUtil;
public class FaqVo{
	private Integer id;
	private String title;
	private String content;
	private String show;
	private String rank;
	private String type;
	private String remark;
	private String op1;
	private String op2;
	public FaqVo(Faq faq) {
		 this.id=faq.getId();
		 this.title=RequestUtil.doInit(faq.getTitle());
		 this.content=RequestUtil.doInit(faq.getContent());
		 this.show=RequestUtil.doInit(faq.getShow()).equalsIgnoreCase("显示")?"显示":"隐藏";
		 this.rank=RequestUtil.doInit(faq.getRank());
		 this.type=RequestUtil.doInit(faq.getType());
		 this.remark=RequestUtil.doInit(faq.getRemark());
		 this.op1="";
		 this.op2="";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOp1() {
		return op1;
	}
	public void setOp1(String op1) {
		this.op1 = op1;
	}
	public String getOp2() {
		return op2;
	}
	public void setOp2(String op2) {
		this.op2 = op2;
	}
	
	
}