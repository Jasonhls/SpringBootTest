package com.redis.test.designMode.responsible;

/**
 * @description: 系主任
 * @author: helisen
 * @create: 2020-04-21 22:27
 **/
public class DepartmentHead extends Leader{

	@Override
	public void handleRequest(int leaveDays) {
		if(leaveDays <= 7){
			System.out.println("系主任批准你请假" + leaveDays + "天");
		}else {
			if(getNext() != null){
				getNext().handleRequest(leaveDays);
			}else {
				System.out.println("请假天数太多，没有人批准该假条！");
			}
		}
	}
}
