package com.eastwood.async;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 996kid
 * @desription
 * @date 2019/12/31
 */
@Slf4j
public class BaseController {

    static class A {
        String a = "1";
    }


    public static void main(String[] args) {
        Set<A> set = new HashSet<>();
        set.add(new A());
        System.out.println(set);
        log.debug("{}",set);
    }
}
