package com.kid.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kid.guice.module.GuiceDemoModule;
import com.kid.guice.service.GuiceDemoService;

/**
 * @author 996kid
 * @desription
 * @date 2020/1/3
 */
public class GuiceDemoApplication {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new GuiceDemoModule());
        GuiceDemoService guciceDemoService = injector.getInstance(GuiceDemoService.class);
        guciceDemoService.test();
    }
}
