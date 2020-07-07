package com.redis.test.redislock;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class RedisLockException extends RuntimeException {
    @Getter
    private int code;
    @Getter
    private String[] arguments;
    @Getter
    private HttpStatus httpStatus;

    public RedisLockException(String message){
        super(message);
    }

    public RedisLockException(int code, String message){
        super(message);
        this.code = code;
        this.arguments = new String[]{message};
    }

    public RedisLockException(String message, int code, HttpStatus httpStatus){
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
