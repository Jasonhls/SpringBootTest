package com.redis.test.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.redis.test.entity.Person;
import com.redis.test.mapper.PersonMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helisen
 * @since 2020-06-10
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IService<Person> {

}
