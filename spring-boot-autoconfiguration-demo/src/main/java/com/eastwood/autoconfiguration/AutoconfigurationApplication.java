package com.eastwood.autoconfiguration;

import com.eastwood.autoconfiguration.config.DemoConfiguration;
import com.eastwood.autoconfiguration.entity.Kid;
import com.eastwood.autoconfiguration.entity.User;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 996kid
 * @desription
 * @date 2019/10/31
 */
//@SpringBootApplication
@EnableCaching
public class AutoconfigurationApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoConfiguration.class);
        User user = applicationContext.getBean(User.class);
        Kid kid = applicationContext.getBean(Kid.class);
        System.out.println(user);
        System.out.println(kid);
    }
}
