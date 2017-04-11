package com.traffic.pojo;

public class TbOrganizationInfo implements java.io.Serializable{
	private Integer id;
	private String organizationName;
	private String alarmPhone;
	private String complaintPhone;
	private String address;
	private Integer showOrder;
	
	public TbOrganizationInfo(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getAlarmPhone() {
		return alarmPhone;
	}

	public void setAlarmPhone(String alarmPhone) {
		this.alarmPhone = alarmPhone;
	}

	public String getComplaintPhone() {
		return complaintPhone;
	}

	public void setComplaintPhone(String complaintPhone) {
		this.complaintPhone = complaintPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
	
	
}
