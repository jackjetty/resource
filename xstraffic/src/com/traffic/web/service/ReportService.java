package com.traffic.web.service;
import java.util.List;

import com.traffic.rpt.FormalAccidentRpt;
public interface ReportService {
	public List<FormalAccidentRpt> generateFormalAccidentCollection(String accidentId,String[] arrPicIndex); 
}