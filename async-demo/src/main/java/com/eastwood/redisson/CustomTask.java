package com.eastwood.redisson;

/**
 * @author 996kid@gmail.com
 * @Description CustomTask
 * @Date 2021/5/7 11:13
 */
public class CustomTask implements Runnable {


    @Override
    public void run() {
        System.out.println("task executed...");
    }
}
