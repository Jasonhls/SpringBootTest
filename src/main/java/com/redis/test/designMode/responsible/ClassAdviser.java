package com.redis.test.designMode.responsible;

/**
 * @description: 班主任
 * @author: helisen
 * @create: 2020-04-21 22:26
 **/
public class ClassAdviser extends Leader{
	@Override
	public void handleRequest(int leaveDays) {
		if(leaveDays <= 2){
			System.out.println("班主任批准你请假" + leaveDays + "天");
		}else {
			if(getNext() != null){
				getNext().handleRequest(leaveDays);
			}else {
				System.out.println("请假天数太多，没有人批准该假条！");
			}
		}
	}
}
