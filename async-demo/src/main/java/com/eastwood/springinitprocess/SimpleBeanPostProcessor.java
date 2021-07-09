package com.eastwood.springinitprocess;

import com.eastwood.springevent.SmsService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author yyh
 * @Description SimpleBean 简单地在所有bean初始化前后打印 beanname
 *              常见的ApplicationContextAware aware类接口都存在相应的processor接口
 * @Date 2020/11/20 17:30
 */
//@Component
public class SimpleBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SmsService) {
            System.out.println("before initial : " + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after initial : " + beanName);
        return bean;
    }
}
