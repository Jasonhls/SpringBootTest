package com.redis.test.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.redis.test.entity.Student;
import com.redis.test.mapper.StudentMapper;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IService<Student> {

}
