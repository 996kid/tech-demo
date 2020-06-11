package com.eastwood;

import com.eastwood.service.AccountService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BankIApplication
 * @Description BankIApplication
 * @Author 996kid
 * @Date 2020/5/25 17:44
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@MapperScan("com.eastwood.dao")
@EnableHystrix
public class BankIIApplication {

    @Autowired
    private AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(BankIIApplication.class, args);
    }


    @GetMapping("/add")
    public String add(String id, double amount) {
        accountService.add(id, amount);
        return "add success";
    }
}
