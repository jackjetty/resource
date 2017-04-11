package com.rising.mobilepayment.mapper;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.SweepStakes;

@Component("sweepStakesMapper")
public interface SweepStakesMapper {

	public SweepStakes getSweepStakes() ;

	public SweepStakes getSweepStakesByImgName(String imgName);

}
