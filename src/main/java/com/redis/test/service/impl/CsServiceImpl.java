package com.redis.test.service.impl;

import com.redis.test.controller.CsController;
import com.redis.test.service.CsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 测试controller与service能相互依赖吗，能够相互依赖的
 * @author: helisen
 * @create: 2020-05-10 23:33
 **/
@Service
public class CsServiceImpl implements CsService {
	@Autowired
	private CsController csController;


	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}

	@Override
	public String getName(String name) {
		return csController.getName(name);
	}
}
