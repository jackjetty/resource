package com.rising.mobilepayment.mapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component; 
import com.rising.mobilepayment.bean.QQRefundBatch; 

@Component("qqRefundBatchMapper")
public interface QQRefundBatchMapper { 
	
	public QQRefundBatch findById(String batchNo);
	
	public void update(QQRefundBatch qqRefundBatch);
	public void add(QQRefundBatch qqRefundBatch);
	
	public QQRefundBatch findByTradeNo(String tradeNo);
	
	
	public List<QQRefundBatch> getUnSubmitList(HashMap<String, Object> map);
	
}