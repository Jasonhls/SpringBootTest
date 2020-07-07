package com.redis.test.mapper;

import com.redis.test.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Jason on 2019/3/18.
 */
public interface UserMapper {

    int add(@Param("user") User user);

    User getUserByUsername(String username);
}
