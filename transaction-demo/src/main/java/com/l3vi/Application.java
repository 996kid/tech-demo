package com.l3vi;

import com.l3vi.testTransactional.service.AService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 996kid@gmail.com
 * @Description Application
 * @Date 2022/8/10 16:15
 */
@SpringBootApplication
@MapperScan(basePackages = "com.l3vi.testTransactional.mapper")
@RestController
@EnableAspectJAutoProxy(exposeProxy = true)
public class Application {

    @Autowired
    private AService aService;

    @GetMapping("/testTran")
    public String testTransactional() throws Exception {
        aService.saveUser();
        // AService的代理类 com.l3vi.testTransactional.service.AService$$EnhancerBySpringCGLIB$$e94f9163
        System.out.println(aService.getClass().getName());
        return "success";
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
