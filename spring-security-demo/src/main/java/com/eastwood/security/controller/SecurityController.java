package com.eastwood.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 996kid
 * @desription
 * @date 2019/10/31
 */
@RestController
public class SecurityController {

    @GetMapping("helloWorld")
    public String helloWorld() {
        return "hello world";
    }
}
