package com.eastwood.feigh;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @InterfaceName feigh.Bank2Client
 * @Description feigh.Bank2Client
 * @Author 996kid
 * @Date 2020/5/25 18:02
 */
@FeignClient(value = "bank2", fallbackFactory = Bank2ClientFallbackFactory.class)
public interface Bank2Client {

    @GetMapping("/add")
    String add(@RequestParam("id") String id, @RequestParam("amount") double amount);
}
