package com.redis.test.req;

import lombok.Data;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-10 09:59
 **/
@Data
public class UserReq {
	private Integer current;
	private Integer size;
	private String username;
	private Boolean enable;
}
