package com.redis.test.req;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-12 09:48
 **/
@Data
public class StoreSpuReq {
	private Integer id;
	private String spuName;
	private Integer storeId;
	private List<StoreSkuReq> skus;
}
