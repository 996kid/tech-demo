package com.eastwood.redisson;

import java.io.Serializable;

/**
 * @author 996kid@gmail.com
 * @Description CustomTask
 * @Date 2021/5/7 11:13
 */
public class CustomTask implements Runnable, Serializable {

    private static final long serialVersionUID = -8193920383968460660L;

    @Override
    public void run() {
        System.out.println("sasssssssssssssssssssssssssssssss");
    }
}
