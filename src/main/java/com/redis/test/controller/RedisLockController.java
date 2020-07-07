package com.redis.test.controller;

import com.redis.test.service.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping(value = "/redisLock")
public class RedisLockController {
    @Autowired
    private Seckill seckill;

    @GetMapping(value = "/miaosha222/{goodsCode}")
    public void miaosha222(@PathVariable String goodsCode,Integer goodsNums){
        int threadCount = 5000;
        CountDownLatch cd = new CountDownLatch(threadCount);
        Thread[] threads = new Thread[threadCount];
        //起500个线程，秒杀第一个商品
        for(int i= 0;i < threadCount;i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //等待在一个信号量上，挂起
                        cd.await();
                        seckill.add(goodsCode,goodsNums);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
            cd.countDown();
        }
        long startTime = System.currentTimeMillis();
        System.out.println("total cost " + (System.currentTimeMillis() - startTime));
    }
}
