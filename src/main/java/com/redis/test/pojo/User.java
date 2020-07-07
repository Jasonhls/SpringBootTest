package com.redis.test.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-09 17:10
 **/
@Data
public class User {
	private Integer id;
	private String username;
	private String password;
	private Integer score;
	private Boolean enable;
	private Date createdTime;
}
