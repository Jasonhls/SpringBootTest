package com.redis.test.redislock;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheLock {
    String lockedPrefix() default ""; //redis 锁key的前缀
    long timeOut() default 2000;//轮询锁的时间
    int expireTime() default 1000;//key在redis里存在的时间
}
