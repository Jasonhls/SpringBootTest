package com.redis.test.designMode.observer;

/**
 * @description: 人名币
 * @author: helisen
 * @create: 2020-04-21 22:45
 **/
public class RMBRate extends Rate{
	@Override
	public void change(int number) {
		for (Company company : observers){
			company.response(number);
		}
	}
}
