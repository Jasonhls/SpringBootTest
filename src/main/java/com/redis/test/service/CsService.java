package com.redis.test.service;

/**
 * @description: 测试controller与service能相互依赖吗
 * @author: helisen
 * @create: 2020-05-10 23:33
 **/
public interface CsService {
	String sayHello(String name);

	String getName(String name);
}
