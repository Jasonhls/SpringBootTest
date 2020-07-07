package com.redis.test.aop;

import com.redis.test.redislock.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class RedisLockAspectJ {
    @Autowired
    private RedisLock redisLock;
    @Pointcut("@annotation(com.redis.test.redislock.CacheLock)")
    public void cut(){}

    @Around("cut()")
    public Object run1(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("进入了aop了");
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
        CacheLock cacheLock = method.getAnnotation(CacheLock.class);
        if(cacheLock == null){
            return joinPoint.proceed(args);
        }else {
            //获得方法中参数的注解
            Annotation[][] annotations = method.getParameterAnnotations();
            //根据获取到的参数注解和参数列表获得加锁的参数
            Object lockedObject = getLockedObject(annotations, args);
            String objectValue = lockedObject.toString();
            //新建一个锁
            String key = cacheLock.lockedPrefix() + objectValue;
            //加锁
            try {
                boolean result = redisLock.lock(cacheLock.timeOut(),cacheLock.expireTime(),key);
                if(!result){
                    throw new RedisLockException("get lcok failed");
                }else {
                    System.out.println("获取锁成功");
                    return joinPoint.proceed(args);
                }
            } finally {
                redisLock.unlock(key);
            }
        }
    }

    private Object getLockedObject(Annotation[][] annotations,Object[] args){
        if(null == annotations || annotations.length == 0){
            throw new RedisLockException("没有被注解的参数");
        }
        if(null == args || args.length == 0){
            throw new RedisLockException("方法参数为空，没有被锁定的对象");
        }
        //不支持多个参数加锁，只支持一个注解为lockedObject或lockedComplexObject的参数
        int index = -1;//标记参数的位置指针
        for (int i = 0;i < annotations.length;i++){
            for (int j = 0;j < annotations[i].length;j++){
                if(annotations[i][j] instanceof LockedComplexObject){
                    index = 1;
                    try {
                        return args[i].getClass().getField(((LockedComplexObject)annotations[i][j]).field());
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
                if(annotations[i][j] instanceof LockedObject){
                    index = i;
                    break;
                }
            }
            //找到第一个后直接break，不支持多参数加锁
            if(index != -1){
                break;
            }
        }
        if(index == -1){
            throw new RedisLockException("请指定被锁定参数");
        }
        return args[index];
    }
}
