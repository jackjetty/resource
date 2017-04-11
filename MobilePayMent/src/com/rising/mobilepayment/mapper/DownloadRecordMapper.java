package com.rising.mobilepayment.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.DownloadRecord;

@Component("downloadRecordMapper")
public interface DownloadRecordMapper {

	public void add(DownloadRecord record);

	public ArrayList<DownloadRecord> find(HashMap<String, Object> map);

}
