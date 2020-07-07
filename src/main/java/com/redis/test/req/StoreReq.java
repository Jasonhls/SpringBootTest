package com.redis.test.req;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-12 09:48
 **/
@Data
public class StoreReq {
	private Integer id;
	private String storeName;
	private String lat;
	private String lon;
	private List<StoreSpuReq> spus;

}
