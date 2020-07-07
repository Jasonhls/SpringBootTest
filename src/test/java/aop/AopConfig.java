package aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-10 21:32
 **/
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.redis.aop.jdkAop")
public class AopConfig {
	/**
	 * 如果添加了扫描，就直接在各个类上添加@Component @Service，就可以注入到spring中
	 * 如果没有添加扫描，就需要用下面的方式，使用@Bean，将对象注入到spring中来
	 */
//	@Bean
//	public StudentDemoService studentService(){
//		return new StudentDemoServiceImpl();
//	}
//
//	@Bean
//	public MyAop myAop(){
//		return new MyAop();
//	}
}
