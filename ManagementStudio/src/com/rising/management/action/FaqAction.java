package com.rising.management.action;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;

import com.rising.management.bean.Faq;
import com.rising.management.service.FaqService;

@Controller("faqAction")
public class FaqAction {
	private Integer id;
	private String title;
	private String content;
	private String show;
	private String rank;
	private String type;
	private String remark;
	private Integer rows;
	private Integer page;
	private String order;
	private String sort;
	private Integer curRowId;
	private Integer repRowId;
	private HashMap<String, Object> resultMap;

	@Autowired
	FaqService faqService;

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

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getCurRowId() {
		return curRowId;
	}

	public void setCurRowId(Integer curRowId) {
		this.curRowId = curRowId;
	}

	public Integer getRepRowId() {
		return repRowId;
	}

	public void setRepRowId(Integer repRowId) {
		this.repRowId = repRowId;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String doFaqPageData() {

		Faq faq = new Faq();
		faq.setTitle(this.title);
		faq.setContent(this.content);
		faq.setShow(this.show);

		resultMap = faqService.getPageDate(this.page, this.rows, this.order,
				this.sort, faq);
		return "success";
	}

	public String doFaq() {
		return "success";
	}

	public String doFaqById() {
		this.resultMap = faqService.getFaqById(this.id);
		return "success";
	}

	public String doUpdateFaq() {
		Faq faq = new Faq();
		faq.setContent(this.content);
		faq.setId(id);
		faq.setRank(rank);
		faq.setRemark(remark);
		faq.setShow(show);
		faq.setTitle(title);
		faq.setType(type);
		this.resultMap = faqService.updateFaq(faq);
		return "success";
	}

	public String doReplaceFaqRand() {
		this.resultMap = faqService.replaceFaqRand(this.getCurRowId(),
				this.getRepRowId());
		return "success";
	}

}
