package com.detachment.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.detachment.pojo.TbHtml;
import com.detachment.pojo.vo.TbHtmlVo;

public interface TbHtmlService {
	public HashMap<String,Object> getTbHtml(String startTime, String endTime,
			String htmlType, Integer pageSize, Integer pageIndex);
	public HashMap<String,Object> addTbHtml(TbHtml th);
	public TbHtmlVo getTbHtmlByHtmlId(Integer htmlId);
	
	public HashMap<String,Object> updateHtml(TbHtml th);
	
	public TbHtml getTbHtmlById(Integer htmlId);
	
	public HashMap<String,Object> removeHtml(String ids);
	
	public ArrayList<TbHtmlVo> getTbHtmlJsp(String htmlTypeId, Integer pageSize, Integer pageIndex);
	
}
