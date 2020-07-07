package com.redis.test.designMode.responsible;

/**
 * @description: 测试责任链
 * @author: helisen
 * @create: 2020-04-21 22:33
 **/
public class TestDemo {
	public static void main(String[] args) {
		Leader leader1 = new ClassAdviser();
		Leader leader2 = new DepartmentHead();
		Leader leader3 = new Dean();
		leader1.setNext(leader2);
		leader2.setNext(leader3);
		leader1.handleRequest(7);
	}
}
