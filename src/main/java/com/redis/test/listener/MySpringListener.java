package com.redis.test.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @description:
 * @author: helisen
 * @create: 2020-04-23 21:57
 **/
public class MySpringListener implements SpringApplicationRunListener {
	public MySpringListener(SpringApplication application, String[]  args){
		System.out.println("constructor");
	}
	// 在run()方法开始执行时，该方法就立即被调用，可用于在初始化最早期时做一些工作
	@Override
	public void starting() {
		System.out.println("starting。。。");
	}

	// 当environment构建完成，ApplicationContext创建之前，该方法被调用
	@Override
	public void environmentPrepared(ConfigurableEnvironment environment) {
		System.out.println("enviromentPrepared。。。");
	}

	// 当ApplicationContext构建完成时，该方法被调用
	@Override
	public void contextPrepared(ConfigurableApplicationContext context) {
		System.out.println("contextPrepared。。。");
	}

	// 在ApplicationContext完成加载，但没有被刷新前，该方法被调用
	@Override
	public void contextLoaded(ConfigurableApplicationContext context) {
		System.out.println("contextLoaded。。。");
	}

	// 在ApplicationContext刷新并启动后，CommandLineRunners和ApplicationRunner未被调用前，该方法被调用
	@Override
	public void started(ConfigurableApplicationContext context) {
		System.out.println("started。。。");
	}

	// 在run()方法执行完成前该方法被调用
	@Override
	public void running(ConfigurableApplicationContext context) {
		System.out.println("running。。。");
	}

	// 当应用运行出错时该方法被调用
	@Override
	public void failed(ConfigurableApplicationContext context, Throwable exception) {
		System.out.println("failed。。。");
	}

}
