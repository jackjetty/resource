package com.rising.mobilepayment.mapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
 
import com.rising.mobilepayment.bean.QQAgentOrder;

@Component("qqAgentOrderMapper")
public interface QQAgentOrderMapper { 
	
	public QQAgentOrder findById(String agentOrderId);
	
	public List<QQAgentOrder> getUnPayList(HashMap<String, Object> map);
	
	public void add(QQAgentOrder qqAgentOrder);
	
	public void update(QQAgentOrder qqAgentOrder);
	
	public List<QQAgentOrder> getPayFailList(HashMap<String, Object> map); 
	
	
	public List<QQAgentOrder> getListByBatchNo(String batchNo); 
	
	public Integer getSumNumByBathNo(HashMap<String, Object> map);
}