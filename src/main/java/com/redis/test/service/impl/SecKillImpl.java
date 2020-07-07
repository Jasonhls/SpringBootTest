package com.redis.test.service.impl;

import com.redis.test.mapper.MiaoshaMapper;
import com.redis.test.redislock.RedisLock;
import com.redis.test.service.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SecKillImpl implements Seckill {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private MiaoshaMapper miaoshaMapper;

    @Override
    public Integer secKill(String userId, String goodsCode) {
        System.out.println("开始秒杀："+ userId);
        miaoshaMapper.update(goodsCode);
        return miaoshaMapper.getGoodsNumByGoodsCode(goodsCode);
    }

    @Autowired
    private RedisLock redisLock;
    @Override
    public void add(String goodsCode, Integer goodsNums) {
        boolean lock = redisLock.lock(100, 500, "redis_lock:" + goodsCode);
        if(lock){
            try {
                System.out.println();
                Integer goodsNumByGoodsCode = miaoshaMapper.getGoodsNumByGoodsCode(goodsCode);
                if(goodsNumByGoodsCode == null || goodsNumByGoodsCode == 0){
                    miaoshaMapper.add(goodsCode,goodsNums);
                }else {
                    System.out.println("数据库已经存在");
                }
            } finally {
                System.out.println("释放锁");
                redisLock.unlock("redis_lock:" + goodsCode);
            }
        }else {
            System.out.println("获取锁失败");
        }
    }
}
