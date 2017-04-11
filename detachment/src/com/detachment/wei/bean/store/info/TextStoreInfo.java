package com.detachment.wei.bean.store.info;
import java.io.Serializable;
import java.util.Date;
public class  TextStoreInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7001487806810950253L;
	private Date date;
	private String content;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	} 
}
 