package com.redis.test.controller;

import com.redis.test.service.CsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试controller与service能相互依赖吗，能够相互依赖的
 * @author: helisen
 * @create: 2020-05-10 23:32
 **/
@RestController
@RequestMapping(value = "/cs")
public class CsController {
	@Autowired
	private CsService service;

	@GetMapping(value = "/test")
	public String getName(){
		return "hello";
	}

	@GetMapping(value = "/getName")
	public String getName(String name){
		return "My name is " + name;
	}

	@GetMapping(value = "/getName2")
	public String getName2(String name){
		return service.getName(name);
	}
}
