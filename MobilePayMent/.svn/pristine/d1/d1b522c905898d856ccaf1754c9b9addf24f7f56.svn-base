package com.rising.mobilepayment.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.WinningInfo;


@Component("winningInfoMapper")
public interface WinningInfoMapper {

	public int addWinningInfo(WinningInfo info);

	public ArrayList<WinningInfo> findByPhoneNumber(HashMap<String, Object> paraMap);

	public Integer getAllSize(String phoneNumber);

	public ArrayList<WinningInfo> getWinningInfo(HashMap<String, Object> paraMap);

	public ArrayList<WinningInfo> getWinningInfo2(
			HashMap<String, Object> paraMap);

	public void updateWinningInfo(Integer id, String messageSendResult);

}
