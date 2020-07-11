package com.redis.thread;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-11 16:32
 **/
public class CompletableFutureDemo {
	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		System.out.println("开始时间=" + new Date());
		List<String> list = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		//定长3线程池
		ExecutorService exs = Executors.newFixedThreadPool(10);
		List<CompletableFuture<String>> futureList = new ArrayList<>();
		List<Integer> taskList = Lists.newArrayList(2,1,3,4,5,6,7,8,9,10);
		try {
			//方法一：循环创建CompletableFuture list，调用sequence()组装返回一个有返回值对的CompletableFuture，返回结果get()获取
			/*for (int i = 0; i < taskList.size(); i++) {
				final int j = i + 1;
				CompletableFuture<String> future = CompletableFuture.supplyAsync( () -> calculate(j), exs)
						.thenApply(e -> String.valueOf(e))
						//如需获取任务完成先手顺序，此处代码即可
						.whenComplete((v, e) -> {
							System.out.println("任务"+v+"完成!result="+v+"，异常 e="+e+","+new Date());
							list2.add(v);
						});
				futureList.add(future);
			}
			//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]此处不理解为什么是这样的顺序？谁知道求告知
			list = sequence(futureList).get();*/
			//方法二：全流式处理转换成CompletableFuture[] + 组装成一个无返回值CompletableFuture，join等待
			//执行完毕。返回结果whenComplete获取
			CompletableFuture[] cfs = taskList.stream().map(a -> CompletableFuture.supplyAsync( () -> calculate(a), exs)
					.thenApply( h -> String.valueOf(h))
					//如需获取任务完成先手顺序，此处代码即可
					.whenComplete((v, e) -> {
						System.out.println("任务"+v+"完成!result="+v+"，异常 e="+e+","+new Date());
						list2.add(v);
					})).toArray(CompletableFuture[]::new);
			//封装后无返回值，必须自己whenComplete()获取
			CompletableFuture.allOf(cfs).join();
			System.out.println("list2="+list2+"list="+list+",耗时="+(System.currentTimeMillis()-start));
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			exs.shutdown();
		}
	}

	public static Integer calculate(Integer i){
 		try {
			 if(i==1){
				 //任务1耗时3秒
				Thread.sleep(3000);
				}else if(i==5){
				 //任务5耗时5秒
					Thread.sleep(5000);
				}else{
				 //其它任务耗时1秒
					Thread.sleep(1000);
				}
				System.out.println("task线程："+Thread.currentThread().getName()+"任务i="+i+",完成！+"+new Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 组合多个CompletableFuture为一个CompletableFuture,所有子任务全部完成，
	 * 组合后的任务才会完成。带返回值，可直接get.
	 * @param futures
	 * @param <T>
	 * @return
	 */
	public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
		//1.构造一个空CompletableFuture，子任务为入参任务list size
		CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
		//2.流式(每个子任务join操作后转换为list)，往空CompletableFuture中添加结果
		return allDoneFuture.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));
	}

	/**
	 * Stream流式类型futures转换成一个CompletableFuture,所有子任务全部完成，
	 * 组合后的任务才会完成。带返回值，可直接get.
	 * @param futures
	 * @param <T>
	 * @return
	 */
	public static <T> CompletableFuture<List<T>> sequence(Stream<CompletableFuture<T>> futures) {
		List<CompletableFuture<T>> futureList = futures.filter(f -> f != null).collect(Collectors.toList());
		return sequence(futureList);
	}
}
