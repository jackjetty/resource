package com.rising.mobilepayment.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.mobilepayment.commom.BaseAction;
import com.rising.mobilepayment.service.DownloadRecordService;

@Controller
@RequestMapping("/download")
public class DownloadAction extends BaseAction {
	
	@Autowired DownloadRecordService downloadRecordService;

	@RequestMapping("/write")
	public void write(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, String> map = getParameter(request);
		downloadRecordService.record(map.get("From"),map.get("Ip"));
	}
	
}
