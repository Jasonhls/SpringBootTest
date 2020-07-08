package com.redis.spi;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-08 13:36
 **/
public class Bumblebee implements Robot{
    @Override
    public void sayHello() {
        System.out.println("i an a bumblebee robot");
    }
}
