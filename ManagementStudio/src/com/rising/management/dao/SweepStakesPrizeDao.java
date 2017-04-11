package com.rising.management.dao;

import java.util.ArrayList;

import com.rising.management.bean.SweepStakesPrize;

public interface SweepStakesPrizeDao {
	public Integer getSweepStakesPrizeSize(Integer activityId,Integer prizeId);
	public ArrayList<SweepStakesPrize> getSweepStakesPrize(Integer activityId,Integer prizeId,
			Integer start,Integer pageSize);
	public void addSweepStakesPrize(SweepStakesPrize sp);
	public void updateSweepStakesPrize(SweepStakesPrize sp);
	public void removeSweepStakesPrize(ArrayList<Integer> ids);
	public Integer SumToPrizeNumber(Integer prizeId);
	public SweepStakesPrize getSweepStakesPrizeById(Integer id);
}
