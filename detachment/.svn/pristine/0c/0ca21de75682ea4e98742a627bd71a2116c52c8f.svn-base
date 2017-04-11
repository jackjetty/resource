package com.detachment.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import com.detachment.pojo.vo.TbHistoryVo;

public interface HistoryService {
	public HashMap<String,Object> getTbHistory(String historyTypeId, Integer pageSize, Integer pageIndex);
	
	public HashMap<String,Object> changeHistoryTypeById(String ids,String historyTypeId);
	
	public HashMap<String,Object> changeValid(String ids,boolean valid);
	
	public ArrayList<TbHistoryVo> getHistoryToJsp(String historyTypeId, Integer pageSize, Integer pageIndex);
}
