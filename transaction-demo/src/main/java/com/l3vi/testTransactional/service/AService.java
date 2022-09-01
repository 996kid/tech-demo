package com.l3vi.testTransactional.service;

import com.l3vi.testTransactional.mapper.CourseMapper;
import com.l3vi.testTransactional.mapper.UserMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

/**
 * @author 996kid@gmail.com
 * @Description AService
 * @Date 2022/8/9 14:04
 */
@Service
public class AService {

    @Autowired
    private BService bService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Transactional
    public void saveUser() {
            userMapper.insert(UUID.randomUUID().toString().replace("-", ""), "uuu");
//            throw new Exception("err");
        bService.saveCourse2();
//        AService aServiceProxy = (AService) AopContext.currentProxy();
//        aServiceProxy.saveCourse2();
//
//
//        this.saveCourse2();
        throw new RuntimeException("err");
    }

    public void saveCourse() {
        courseMapper.insert(UUID.randomUUID().toString().replace("-", ""), "cccc");
        throw new RuntimeException("err");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCourse1() {
        courseMapper.insert(UUID.randomUUID().toString().replace("-", ""), "cccc");
        throw new RuntimeException("err");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveCourse2() {
        courseMapper.insert(UUID.randomUUID().toString().replace("-", ""), "cccc");
//        throw new RuntimeException("err");
    }

}
