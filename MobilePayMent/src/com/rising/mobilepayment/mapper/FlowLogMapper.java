package com.rising.mobilepayment.mapper;
 
import org.springframework.stereotype.Component; 
 
import com.rising.mobilepayment.bean.FlowLog;  

@Component("flowLogMapper")
public interface FlowLogMapper { 
	public void add(FlowLog flowLog); 

}