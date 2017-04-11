package com.rising.mobilepayment.service;
import java.util.HashMap;

public interface FlowService { 
	public String query(HashMap<String, String> map);
	public String recommend(HashMap<String, String> map);
	public String apply(HashMap<String, String> map);
	public String handle(HashMap<String, String> map);
	public String orderQuery(HashMap<String, String> map);
    public void flowOrderInfoQuery();
}