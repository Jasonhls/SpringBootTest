package com.redis.test.designMode.responsible;

/**
 * @description: 院长
 * @author: helisen
 * @create: 2020-04-21 22:27
 **/
public class Dean extends Leader{
	@Override
	public void handleRequest(int leaveDays) {
		if(leaveDays <= 10){
			System.out.println("院长批准你请假" + leaveDays + "天");
		}else {
			System.out.println("请假天数太多，没有人批准该假条！");
		}
	}
}
