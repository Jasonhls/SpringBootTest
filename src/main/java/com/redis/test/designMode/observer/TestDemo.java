package com.redis.test.designMode.observer;

/**
 * @description: 测试观察者模式
 * @author: helisen
 * @create: 2020-04-21 22:46
 **/
public class TestDemo {
	public static void main(String[] args) {
		Rate rate = new RMBRate();
		CompanyOne co = new CompanyOne();
		CompanyTwo ct = new CompanyTwo();
		rate.observers.add(co);
		rate.observers.add(ct);
		rate.change(10);
		rate.change(-9);
	}
}
