package com.redis.test.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.redis.test.pojo.User;
import com.redis.test.req.UserReq;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-09 17:14
 **/
public interface UserService {
	int add(User user);

	void createOrUpdateIndex(User user);

	IPage<User> getUserList(UserReq userReq);
}
