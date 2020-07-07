package com.redis.test.service;

import com.redis.test.redislock.CacheLock;
import com.redis.test.redislock.LockedObject;

public interface Seckill {
    /**
     *现在暂时只支持在接口方法上注解
     */
    //cacheLock注解可能产生并发的方法
    @CacheLock(lockedPrefix="TEST_PREFIX")
    //最简单的秒杀方法，参数是用户ID和商品ID。可能有多个线程争抢一个商品，所以商品ID加上LockedObject注解
    Integer secKill(String userId, @LockedObject String goodsCode);

    @CacheLock(lockedPrefix = "REDIS_TEST")
    void add(@LockedObject String goodsCode, Integer goodsNums);
}
