package com.redis.spi;

import java.util.ServiceLoader;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-08 13:39
 **/
public class JavaSPITest {
    public static void main(String[] args) {
        ServiceLoader<Robot> loader = ServiceLoader.load(Robot.class);
        System.out.println("haha");
        loader.forEach(Robot::sayHello);
    }
}
