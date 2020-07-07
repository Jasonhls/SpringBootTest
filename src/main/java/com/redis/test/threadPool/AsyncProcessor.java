package com.redis.test.threadPool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @description: 自定义线程池
 * @author: helisen
 * @create: 2020-06-08 17:18
 **/
public class AsyncProcessor {
	static final Logger logger = LoggerFactory.getLogger(AsyncProcessor.class);
	/**
	 * 默认最大并发数
	 */
	private static final int DEFAULT_MAX_CONCURRENT = Runtime.getRuntime().availableProcessors() * 2;
	/**
	 * 线程池名称格式
	 */
	private static final String THREAD_POOL_NAME = "ExternalAsyncProcessPool-%d";
	/**
	 * 线程池名称
	 */
	private static final ThreadFactory FACTORY = new BasicThreadFactory.Builder().namingPattern(THREAD_POOL_NAME)
			.daemon(true).build();
	/**
	 * 默认队列大小
	 */
	private static final int DEFAULT_SIZE = 500;
	/**
	 * 默认线程存活时间
	 */
	private static final long DEFAULT_KEEP_ALIVE = 60L;
	private static ExecutorService executorService;
	/**
	 * 执行队列
	 */
	private static BlockingQueue<Runnable> executeQueue = new ArrayBlockingQueue<>(DEFAULT_SIZE);

	static {
		try {
			//创建Executor
			//此处默认最大值改为处理器数量的4倍
			executorService = new ThreadPoolExecutor(DEFAULT_MAX_CONCURRENT, DEFAULT_MAX_CONCURRENT * 4,
					DEFAULT_KEEP_ALIVE, TimeUnit.SECONDS, executeQueue, FACTORY);
			//关闭事件的挂钩
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				@Override
				public void run() {
					AsyncProcessor.logger.info("AsyncProcessor shutting down.");
					executorService.shutdown();

					try {
						if(!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
							AsyncProcessor.logger.error("AsyncProcessor shutdown immediately due to wait timeout.");
							executorService.shutdownNow();
						}
					} catch (InterruptedException e) {
						AsyncProcessor.logger.error("AsyncProcessor shutdown interrupted.");
						e.printStackTrace();
					}

					AsyncProcessor.logger.info("AsyncProcessor shutdown complete.");
				}
			}));
		} catch (Exception e) {
			AsyncProcessor.logger.error("AsyncProcessor init error.", e);
			e.printStackTrace();
		}
	}

	private AsyncProcessor(){
	}

	public static boolean executeTask(Runnable task) {
		try {
			executorService.execute(task);
		} catch (Exception e) {
			logger.error("Task executing was rejected.", e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static <T> Future<T> submitTask(Callable<T> task) {
		try {
			return executorService.submit(task);
		} catch (Exception e) {
			logger.error("Task executing was rejected.", e);
			e.printStackTrace();
			throw new UnsupportedOperationException("Unable to submit the task, rejected.", e);
		}
	}
}
