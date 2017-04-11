package com.detachment.web.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detachment.dao.ElePoliceDao;
import com.detachment.pojo.TbElectronicPolice;
import com.detachment.web.service.ElePoliceService;

@Service
public class ElePoliceServiceImpl implements ElePoliceService{
	
	@Autowired
	ElePoliceDao elePoliceDao;
	
	@Override
	public ArrayList<TbElectronicPolice> getElePolices(String eleType,
			String eleAddress, Integer pageSize, Integer pageIndex) {
		if("".equals(eleType)){
			eleType=null;
		}
		if("".equals(eleAddress)){
			eleAddress=null;
		}
		Integer start = (pageIndex - 1) * pageSize;
		ArrayList<TbElectronicPolice> tes=elePoliceDao.getElePolice(eleType, eleAddress, start, pageSize);
		return tes;
	}

}
