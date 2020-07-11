package com.redis.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description:demo1在特定场合例如有十分耗时的业务但有依赖于其他业务不一定非要执行的，可以尝试使用。
 * demo2多线程并发执行并结果归集，这里多套一层FutureTask比较鸡肋（直接返回Future简单明了）不建议使用。
 * @author: helisen
 * @create: 2020-07-11 15:49
 **/
public class FutureTaskDemo2 {
	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		System.out.println("开始时间=" + new Date());
		ExecutorService exs = Executors.newFixedThreadPool(10);
		try {
			List<Integer> list = new ArrayList<>();
			List<Future<Integer>> futureList = new ArrayList<>();
			//高速提交10个任务，每个任务返回一个Future入list
			int count = 10;
			for (int i = 0; i < count; i++) {
				FutureTask<Integer> futureTask = new FutureTask<>(new CallableTask(i + 1));
				//提交任务，添加返回
				//Runnable特性
				exs.submit(futureTask);
				//Future特性
				futureList.add(futureTask);

			}
			Long getResultStart = System.currentTimeMillis();
			System.out.println("结果归集开始时间=" + new Date());

			//结果集归集，遍历futureList，高速轮询（模拟实现了并发）获取future状态成功完成后获取结果，退出当前循环
			for (Future<Integer> future : futureList) {
				while(true) {
					//获取future成功完成状态，如果想要限制每个任务的超时时间，取消本行的状态判断
					if(future.isDone() && !future.isCancelled()) {
						//获取结果
						Integer i = future.get();
						System.out.println("任务i=" + i + "获取完成！" + new Date());
						list.add(i);
						//当前future获取完毕，跳出while
						break;
					}else {
						//避免CPU高速轮询，可以休息一下
						Thread.sleep(1);
					}
				}
			}
			System.out.println("list=" + list);
			System.out.println("总耗时=" + (System.currentTimeMillis() - start) + ",取结果集归集耗时=" + (System.currentTimeMillis() - getResultStart));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			exs.shutdown();
		}
	}

	static class CallableTask implements Callable<Integer> {
		Integer i;

		public CallableTask(Integer i) {
			this.i = i;
		}

		@Override
		public Integer call() throws Exception {
			if(i == 1) {
				Thread.sleep(3000);
			}else if(i == 5) {
				Thread.sleep(5000);
			}else {
				Thread.sleep(1000);
			}
			System.out.println("task线程：" + Thread.currentThread().getName() + "任务i=" + i + "，完成！");
			return i;
		}
	}
}
