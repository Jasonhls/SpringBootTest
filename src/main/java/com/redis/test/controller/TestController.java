package com.redis.test.controller;

import com.redis.test.service.CsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试controller与service能相互依赖吗，能够相互依赖的
 * @author: helisen
 * @create: 2020-05-10 23:36
 **/
@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private CsService service;

	@GetMapping(value = "/getName")
	public String name(String name){
		return service.getName(name);
	}
}
