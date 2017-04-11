package com.rising.management.service;

import java.util.HashMap;


public interface WinnerListService {

	public HashMap<String, Object> getWinnerListByPage(Integer pageIndex,
			Integer pageSize, String place);

	public HashMap<String, Object> deleteWinnerById(Integer id);

	public HashMap<String, Object> addWinner(Integer prizeId, Integer sortId,
			String numbers);

	public HashMap<String, Object> getWinningInfo(Integer pageSize,
			Integer pageIndex, Integer prizeId, String phoneNumber, String winTime);

	public HashMap<String, Object> getWinningInfoByPhoneNumber(
			Integer pageSize, Integer pageIndex, String phoneNumber);
}
