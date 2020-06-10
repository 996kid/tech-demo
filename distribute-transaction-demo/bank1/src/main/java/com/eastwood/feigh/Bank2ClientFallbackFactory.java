package com.eastwood.feigh;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName Bank2ClientFallbackFactory
 * @Description Bank2ClientFallbackFactory
 * @Author 996kid
 * @Date 2020/5/25 18:45
 */
@Component
public class Bank2ClientFallbackFactory implements FallbackFactory<Bank2Client> {
    @Override
    public Bank2Client create(Throwable throwable) {
        return (id, amount) -> "服务暂时无法使用，请稍后重试";
    }
}
