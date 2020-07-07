package com.redis.test.designMode.responsible;

/**
 * @description: 领导抽象类
 * @author: helisen
 * @create: 2020-04-21 22:24
 **/
public abstract class Leader {
	private Leader next;

	public Leader getNext() {
		return next;
	}

	public void setNext(Leader next) {
		this.next = next;
	}

	public abstract void handleRequest(int leaveDays);
}
