package com.redis.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description: 此种方法可实现基本目标，任务并行且按照提交顺序获取结果
 * 测试情况:
 * 1.开启定长为10的线程池，ExecutorService exs = Executors.newFixedThreadPool(10);+任务1耗时3秒，任务5耗时5秒，其他1秒。
 * 		总耗时=5006,取结果归集耗时=5002---》符合逻辑，10个任务，定长10线程池，其中一个任务耗时3秒，一个任务耗时5秒，由于并发高速轮训，耗时取最长5秒
 * 2.开启定长为5的线程池，ExecutorService exs = Executors.newFixedThreadPool(5);+任务1耗时3秒，任务5耗时5秒，其他1秒。
 * 		总耗时=5006,取结果归集耗时=5002------》符合逻辑，10个任务，定长5的线程池，由于线程1阻塞3秒，线程5阻塞5秒，由于并发高速轮训，总耗时取最长5秒
 * 3.开启定长为5的线程池且把线程sleep时间全部设定为1秒，即ExecutorService exs = Executors.newFixedThreadPool(5);Thread.sleep(1000)。
 * 		总耗时=2006,取结果归集耗时=2002------》符合逻辑，10个任务，定长5的线程池，执行2波，耗时2*1秒=2秒
 * 4.如果开启定长为10的线程池且每个任务耗时一秒，即ExecutorService exs = Executors.newFixedThreadPool(10);Thread.sleep(1000)。
 * 		总耗时=1009,取结果归集耗时=1005------》符合逻辑，10个任务，定长10的线程池，执行1波，耗时1秒
 * @author: helisen
 * @create: 2020-07-11 15:07
 **/
public class FutureDemo {
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
				futureList.add(exs.submit(new CallableTask(i + 1)));

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
