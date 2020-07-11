package com.redis.thread;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-11 15:49
 **/
public class FutureTaskDemo {
	public static void main(String[] args) {
		try {
			System.out.println("一个统计公司总部和分布的总利润是否达标100万");
			//利润
			Integer count = 0;
			FutureTask<Integer> futureTask = new FutureTask<>(new CallableTask());
			Thread futureTaskThread = new Thread(futureTask);
			futureTaskThread.start();
			System.out.println("futureTaskThread start ! " + new Date());
			//2.主线程先做点其他的事
			System.out.println("主线程查询总部公司利润开始时间：" + new Date());
			Thread.sleep(5000);
			//总部利润计算
			count += 10;
			System.out.println("主线程查询总部公司利润结果时间：" + new Date());
			//总部已达标100万利润，就不再执行获取分公司业绩任务了
			if(count >= 100) {
				System.out.println("总部公司利润达标，取消futureTask !" + new Date());
				futureTask.cancel(true);
			}else {
				System.out.println("总部公司利润未达标，进入阻塞查询分公司利润！" + new Date());
				//3.总部未达标，阻塞获取各个分公司结果
				//真正执行CallableTask
				Integer i = futureTask.get();
				System.out.println("i=" + i + "获取结果！" + new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 一个十分耗时的任务
	 */
	static class CallableTask implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			System.out.println("CallableTask-call，查询分公司利润，执行开始！" + new Date());
			Thread.sleep(10000);
			System.out.println("CallableTask-call，查询分公司利润，执行完毕！" + new Date());
			return 10;
		}
	}
}
