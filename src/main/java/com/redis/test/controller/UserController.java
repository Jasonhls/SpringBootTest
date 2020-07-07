package com.redis.test.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.redis.test.pojo.User;
import com.redis.test.req.UserReq;
import com.redis.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-09 17:09
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping
	public void addStudent(@RequestBody User user){
		userService.add(user);
	}

	@GetMapping
	public IPage<User> getUserList(UserReq userReq){
		return userService.getUserList(userReq);
	}
}
