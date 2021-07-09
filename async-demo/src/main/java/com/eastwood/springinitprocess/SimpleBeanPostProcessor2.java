package com.eastwood.springinitprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author 996kid
 * @Description SimpleBeanPostProcessor2
 * @Date 2020/12/2 11:35
 */
//@Component
public class SimpleBeanPostProcessor2 implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before initial : 2 " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before initial : 2 " + beanName);
        return bean;
    }
}
