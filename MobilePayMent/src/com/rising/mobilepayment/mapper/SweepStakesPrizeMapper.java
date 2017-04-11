package com.rising.mobilepayment.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.SweepStakesPrize;


@Component("sweepStakesPrizeMapper")
public interface SweepStakesPrizeMapper {

	public SweepStakesPrize findPrizeByLotteryId(Integer prizeId);

	public ArrayList<SweepStakesPrize> findBySweepStakes(Integer id);

	public void updateStakesPrize(SweepStakesPrize sweepStakesPrize);

	public SweepStakesPrize findSendMinScore();

}
