package com.rising.mobilepayment.mapper;

import org.springframework.stereotype.Component;

import com.rising.mobilepayment.bean.FreeProduct;


@Component("freeProductMapper")
public interface FreeProductMapper {

	FreeProduct findByFreeProductId(String freeProductId);

}
