package com.rising.mobilepayment.service;

import java.util.HashMap;

import com.rising.mobilepayment.bean.Prize;
import com.rising.mobilepayment.bean.SweepStakes;

public interface LotteryService {

	public String getInfo(String phoneNumber);

	//public String addWinningInfo(HashMap<String, String> map);

	public String checkWinning(HashMap<String, String> map);

	public Prize getPrizeByImageName(String string);

	public SweepStakes getSweepStakesByImageName(String string);
	
	/*public HashMap<String, String> sendOneQB(String PhoneNumber,
			String QQNumber);*/

}
