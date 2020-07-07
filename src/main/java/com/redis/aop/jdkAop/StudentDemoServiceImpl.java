package com.redis.aop.jdkAop;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-10 21:13
 **/
@Component
public class StudentDemoServiceImpl implements StudentDemoService {
	@Override
	public void eat() {
		System.out.println("学生吃东西");
	}
}
