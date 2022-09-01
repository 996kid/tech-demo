package com.l3vi.testTransactional.service;

import com.l3vi.testTransactional.mapper.CourseMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author 996kid@gmail.com
 * @Description BService
 * @Date 2022/8/9 14:17
 */
@Service
public class BService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 1. A
     */

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveCourse() {
        courseMapper.insert(UUID.randomUUID().toString().replace("-", ""), "cccc");
//        int i = 10/0;
//        throw new RuntimeException("error");
    }


    public void saveCourse1() {
        BService bService = (BService) AopContext.currentProxy();
//        this.saveCourse();
        bService.saveCourse();
    }

    public void saveCourse2() {
        courseMapper.insert(UUID.randomUUID().toString().replace("-", ""), "cccc");
    }
}
