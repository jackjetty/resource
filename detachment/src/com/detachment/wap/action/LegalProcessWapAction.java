package com.detachment.wap.action;
import java.util.ArrayList;
import java.util.HashMap; 
import org.springframework.beans.factory.annotation.Autowired; 
import com.detachment.util.CommonUtil;
import com.detachment.wap.service.LegalProcessWapService;  
import org.springframework.stereotype.Controller;
@Controller("legalProcessWapAction")
public class LegalProcessWapAction {
	private HashMap<String,Object> result; 
	private String hpzl;
	private String hphm;
	private String cjhm;
	private String sfzmhm;
	private String xh;
	private String xm;
	private String captcha_rand_flag_refersh;
	private String mobilecode;
	
	
	private String jdsbh;
	private String orderId;
	private String orderTime;
	
	
	@Autowired
	private LegalProcessWapService legalProcessWapService; 
	
	public String announcePageLegal(){
		return "success";
	}
	
	public String vehiclePageLegal(){
		return "success";
	}
	
	public String vehicleLegal(){ 
		result=legalProcessWapService.vehicleLegal(hpzl,hphm,cjhm);
		return "success";
	}
    public String pendPageLegal(){ 
		result=legalProcessWapService.pendPageLegal();
		return "success";
	}
	
    public String confirmPageLegal(){
    	result=legalProcessWapService.confirmPageLegal(xh);
		return "success";
    }
    
    
    public String verifyPageLegal(){
    	result=legalProcessWapService.verifyPageLegal(xh,captcha_rand_flag_refersh);
		return "success";
    }
    
    public String sendMsgLegal(){
    	result=legalProcessWapService.sendMsgLegal(xm,sfzmhm);
		return "success";
    }
    public String checkMsgLegal(){ 
    	result=legalProcessWapService.checkMsgLegal(mobilecode);
		return "success";  
    }
    public String  payPageLegal(){
    	result=legalProcessWapService.payPageLegal();
		return "success";
    }
    public String  paidPageLegal(){ 
    	result=legalProcessWapService.paidPageLegal(jdsbh,orderId,orderTime);
		return "success";
    }
	
	public HashMap<String, Object> getResult() {
		return result;
	}
	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}
	public String getHpzl() {
		return hpzl;
	}
	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}
	public String getHphm() {
		return hphm;
	}
	public void setHphm(String hphm) {
		this.hphm = hphm;
	}
	public String getCjhm() {
		return cjhm;
	}
	public void setCjhm(String cjhm) {
		this.cjhm = cjhm;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getCaptcha_rand_flag_refersh() {
		return captcha_rand_flag_refersh;
	}
	public void setCaptcha_rand_flag_refersh(String captcha_rand_flag_refersh) {
		this.captcha_rand_flag_refersh = captcha_rand_flag_refersh;
	}
	public String getSfzmhm() {
		return sfzmhm;
	}
	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getMobilecode() {
		return mobilecode;
	}
	public void setMobilecode(String mobilecode) {
		this.mobilecode = mobilecode;
	}

	public String getJdsbh() {
		return jdsbh;
	}

	public void setJdsbh(String jdsbh) {
		this.jdsbh = jdsbh;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	
	
	
	
}

