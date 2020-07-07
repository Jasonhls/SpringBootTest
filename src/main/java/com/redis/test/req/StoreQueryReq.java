package com.redis.test.req;

import lombok.Data;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-12 11:01
 **/
@Data
public class StoreQueryReq {
	private Integer current;
	private Integer size;
	/**
	 * 关键字
	 */
	private String keyWord;
	/**
	 * 半径范围
	 */
	private String distance;
	/**
	 * 半径范围单位，m，km
	 */
	private String distanceUnit;
	/**
	 * 纬度
	 */
	private String lat;
	/**
	 * 经度
	 */
	private String lon;
}
