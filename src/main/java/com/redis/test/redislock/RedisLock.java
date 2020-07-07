package com.redis.test.redislock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class RedisLock {
    @Autowired
    private RedisTemplate redisTemplate;

//    private Boolean lock = false;

    Lock reentrantLock = new ReentrantLock();

    public RedisLock() {
    }

    /**
     * @param timeout timeout的时间范围内轮询锁
     * @param expire 设置锁超时时间
     * @return 成功或失败
     */
    public boolean lock(long timeout,int expire,String key){
        reentrantLock.lock();
        long nanoTime = System.nanoTime();
        try {
            //在timeout的时间范围内不断轮询锁
            while (System.nanoTime() - nanoTime < timeout) {
                Boolean execute = (Boolean) redisTemplate.execute(new RedisCallback() {
                    @Override
                    public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        Boolean result = redisConnection.set(key.getBytes(), "~".getBytes(),
                                Expiration.seconds(expire), RedisStringCommands.SetOption.SET_IF_ABSENT);
                        return result;
                    }
                });
                if(execute){
                    System.out.println("成功获取锁了");
                    return true;
                }
                //短暂休眠，避免可能的活锁
                Thread.sleep(3, new Random().nextInt(30));
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            reentrantLock.unlock();
        }
    }

    public void unlock(String key) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Long del = redisConnection.del(key.getBytes());
                return del;
            }
        });
    }
}
