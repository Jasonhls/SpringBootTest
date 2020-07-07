package com.redis.test.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-12 09:48
 **/
@Data
public class StoreSkuReq {
	private Integer id;
	private String skuName;
	private Integer spuId;
	private BigDecimal price;
}
