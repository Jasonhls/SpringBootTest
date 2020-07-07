package com.redis.aop.jdkAop;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-10 21:07
 **/
public class AopTest {
	@Test
	public void test(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
		StudentDemoService bean = applicationContext.getBean(StudentDemoService.class);
		bean.eat();
	}
}
