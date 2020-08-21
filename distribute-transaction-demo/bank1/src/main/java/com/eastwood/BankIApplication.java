package com.eastwood;

import com.eastwood.service.AccountService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName com.eastwood.BankIApplication
 * @Description com.eastwood.BankIApplication
 * @Author 996kid
 * @Date 2020/5/25 17:44
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@RestController
@MapperScan("com.eastwood.dao")
@EnableHystrix
@EnableTransactionManagement
public class BankIApplication {

    @Autowired
    private AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(BankIApplication.class, args);
    }


    @GetMapping("/decrease")
    public String decrease(String id,double amount) {
        accountService.decrease(id, amount);
        return "success";
    }
}
