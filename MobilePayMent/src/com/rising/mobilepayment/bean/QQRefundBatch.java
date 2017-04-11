package com.rising.mobilepayment.bean;
import java.util.Date;
public class QQRefundBatch{
	
	private String batchNo;
	private String batchNum;
	private Date refundDate;
	private String detailData; 
	private Date createDate;
	private String status; 
	private String remark;
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	public String getDetailData() {
		return detailData;
	}
	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}